<script setup lang="ts">
import { extractIBAN, friendlyFormatIBAN, isQRIBAN, validateIBAN } from 'ibantools';
import { getFriendlyErrors } from './iban-validator-and-parser.service';
import type { CKeyValueListItems } from '@/ui/c-key-value-list/c-key-value-list.types';
import { translate } from '@/plugins/i18n.plugin';

const rawIban = ref('');

const ibanInfo = computed<CKeyValueListItems>(() => {
  const iban = rawIban.value.toUpperCase().replace(/\s/g, '').replace(/-/g, '');

  if (iban === '') {
    return [];
  }

  const { valid: isIbanValid, errorCodes } = validateIBAN(iban);
  const { countryCode, bban } = extractIBAN(iban);
  const errors = getFriendlyErrors(errorCodes);

  return [
    {
      label: translate('tools.iban-validator-and-parser.is-iban-valid'),
      value: isIbanValid,
      showCopyButton: false,
    },
    {
      label: translate('tools.iban-validator-and-parser.iban-errors'),
      value: errors.length === 0 ? undefined : errors,
      hideOnNil: true,
      showCopyButton: false,
    },
    {
      label: translate('tools.iban-validator-and-parser.is-iban-a-qr-iban'),
      value: isQRIBAN(iban),
      showCopyButton: false,
    },
    {
      label: translate('tools.iban-validator-and-parser.country-code'),
      value: countryCode,
    },
    {
      label: translate('tools.iban-validator-and-parser.bban'),
      value: bban,
    },
    {
      label: translate('tools.iban-validator-and-parser.iban-friendly-format'),
      value: friendlyFormatIBAN(iban),
    },
  ];
});

const ibanExamples = [
  'FR7630006000011234567890189',
  'DE89370400440532013000',
  'GB29NWBK60161331926819',
];
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="rawIban"
      :placeholder="translate('tools.iban-validator-and-parser.enter-an-iban-to-check-for-validity')"
      test-id="iban-input"
    />

    <c-card v-if="ibanInfo.length > 0" mt-5>
      <c-key-value-list :items="ibanInfo" data-test-id="iban-info" />
    </c-card>

    <c-card :title="translate('tools.iban-validator-and-parser.valid-iban-examples')" mt-5>
      <div v-for="iban in ibanExamples" :key="iban">
        <c-text-copyable
          :value="iban"
          font-mono
          :displayed-value="friendlyFormatIBAN(iban)"
        />
      </div>
    </c-card>
  </div>
</template>
