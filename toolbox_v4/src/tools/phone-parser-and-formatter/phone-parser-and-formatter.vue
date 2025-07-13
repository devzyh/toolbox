<script setup lang="ts">
import { computed, ref } from 'vue';
import { getCountries, getCountryCallingCode, parsePhoneNumber } from 'libphonenumber-js/max';
import lookup from 'country-code-lookup';
import {
  formatTypeToHumanReadable,
  getDefaultCountryCode,
  getFullCountryName,
} from './phone-parser-and-formatter.models';
import { withDefaultOnError } from '@/utils/defaults';
import { booleanToHumanReadable } from '@/utils/boolean';
import { useValidation } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const rawPhone = ref('');
const defaultCountryCode = ref(getDefaultCountryCode());
const validation = useValidation({
  source: rawPhone,
  rules: [
    {
      validator: value => value === '' || /^[0-9 +\-()]+$/.test(value),
      message: translate('tools.phone-parser-and-formatter.invalid-phone-number'),
    },
  ],
});

const parsedDetails = computed(() => {
  if (!validation.isValid) {
    return undefined;
  }

  const parsed = withDefaultOnError(() => parsePhoneNumber(rawPhone.value, defaultCountryCode.value), undefined);

  if (!parsed) {
    return undefined;
  }

  return [
    {
      label: translate('tools.phone-parser-and-formatter.country'),
      value: getFullCountryName(parsed.country),
    },
    {
      label: translate('tools.phone-parser-and-formatter.country-calling-code'),
      value: parsed.countryCallingCode,
    },
    {
      label: translate('tools.phone-parser-and-formatter.is-valid'),
      value: booleanToHumanReadable(parsed.isValid(), { yes: translate('tools.phone-parser-and-formatter.yes'), no: translate('tools.phone-parser-and-formatter.no') }),
    },
    {
      label: translate('tools.phone-parser-and-formatter.is-possible'),
      value: booleanToHumanReadable(parsed.isPossible(), { yes: translate('tools.phone-parser-and-formatter.yes'), no: translate('tools.phone-parser-and-formatter.no') }),
    },
    {
      label: translate('tools.phone-parser-and-formatter.type'),
      value: formatTypeToHumanReadable(parsed.getType()),
    },
    {
      label: translate('tools.phone-parser-and-formatter.international-format'),
      value: parsed.formatInternational(),
    },
    {
      label: translate('tools.phone-parser-and-formatter.national-format'),
      value: parsed.formatNational(),
    },
    {
      label: translate('tools.phone-parser-and-formatter.e164-format'),
      value: parsed.format('E.164'),
    },
    {
      label: translate('tools.phone-parser-and-formatter.rfc3966-format'),
      value: parsed.format('RFC3966'),
    },
  ];
});

const countriesOptions = getCountries().map(code => ({
  label: `${lookup.byIso(code)?.country || code} (+${getCountryCallingCode(code)})`,
  value: code,
}));
</script>

<template>
  <div class="mw-600">
    <c-select
      v-model:value="defaultCountryCode"
      :label="translate('tools.phone-parser-and-formatter.default-country-code')"
      :options="countriesOptions"
      searchable
      mb-5
    />

    <c-input-text
      v-model:value="rawPhone"
      :placeholder="translate('tools.phone-parser-and-formatter.phone-number')"
      :label="translate('tools.phone-parser-and-formatter.phone-number')"
      :validation="validation"
      mb-5
    />

    <n-table v-if="parsedDetails">
      <tbody>
        <tr v-for="{ label, value } in parsedDetails" :key="label">
          <td font-bold>
            {{ label }}
          </td>
          <td>
            <span-copyable v-if="value" :value="value" />
            <span v-else op-70>
              {{ translate('tools.phone-parser-and-formatter.unknown') }}
            </span>
          </td>
        </tr>
      </tbody>
    </n-table>
  </div>
</template>
