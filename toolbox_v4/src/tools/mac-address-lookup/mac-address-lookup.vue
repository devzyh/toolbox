<script setup lang="ts">
import db from 'oui-data';
import { macAddressValidationRules } from '@/utils/macAddress';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const getVendorValue = (address: string) => address.trim().replace(/[.:-]/g, '').toUpperCase().substring(0, 6);

const macAddress = ref('20:37:06:12:34:56');
const details = computed<string | undefined>(() => (db as Record<string, string>)[getVendorValue(macAddress.value)]);

const { copy } = useCopy({ source: () => details.value ?? '', text: translate('tools.mac-address-lookup.copy-vendor-info') });
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="macAddress"
      :label="translate('tools.mac-address-lookup.mac-address')"
      size="large"
      :placeholder="translate('tools.mac-address-lookup.mac-address')"
      clearable
      autocomplete="off"
      autocorrect="off"
      autocapitalize="off"
      spellcheck="false"
      :validation-rules="macAddressValidationRules"
      mb-5
    />

    <div mb-5px>
      {{ translate('tools.mac-address-lookup.vendor-info') }}:
    </div>
    <c-card mb-5>
      <div v-if="details">
        <div v-for="(detail, index) of details.split('\n')" :key="index">
          {{ detail }}
        </div>
      </div>

      <div v-else italic op-60>
        {{ translate('tools.mac-address-lookup.unknown-vendor') }}
      </div>
    </c-card>

    <div flex justify-center>
      <c-button :disabled="!details" @click="copy()">
        {{ translate('tools.mac-address-lookup.copy-vendor-info') }}
      </c-button>
    </div>
  </div>
</template>
