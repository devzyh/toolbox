<script setup lang="ts">
import _ from 'lodash';
import { generateRandomMacAddress } from './mac-adress-generator.models';
import { computedRefreshable } from '@/composable/computedRefreshable';
import { useCopy } from '@/composable/copy';
import { usePartialMacAddressValidation } from '@/utils/macAddress';
import { translate } from '@/plugins/i18n.plugin';

const amount = useStorage('dz-tools:mac-address-generator:amount', 1);
const macAddressPrefix = useStorage('dz-tools:mac-address-generator:prefix', '64:16:7F');

const prefixValidation = usePartialMacAddressValidation(macAddressPrefix);

const casesTransformers = [
  { label: translate('tools.mac-address-generator.uppercase'), value: (value: string) => value.toUpperCase() },
  { label: translate('tools.mac-address-generator.lowercase'), value: (value: string) => value.toLowerCase() },
];
const caseTransformer = ref(casesTransformers[0].value);

const separators = [
  {
    label: ':',
    value: ':',
  },
  {
    label: '-',
    value: '-',
  },
  {
    label: '.',
    value: '.',
  },
  {
    label: translate('tools.mac-address-generator.none'),
    value: '',
  },
];
const separator = useStorage('dz-tools:mac-address-generator:separator', separators[0].value);

const [macAddresses, refreshMacAddresses] = computedRefreshable(() => {
  if (!prefixValidation.isValid) {
    return '';
  }

  const ids = _.times(amount.value, () => caseTransformer.value(generateRandomMacAddress({
    prefix: macAddressPrefix.value,
    separator: separator.value,
  })));
  return ids.join('\n');
});

const { copy } = useCopy({ source: macAddresses, text: translate('tools.mac-address-generator.copy') });
</script>

<template>
  <div class="mw-600 flex flex-col justify-center gap-2">
    <div flex items-center>
      <label w-150px pr-12px text-right>{{ translate('tools.mac-address-generator.quantity') }}:</label>
      <n-input-number v-model:value="amount" min="1" max="100" flex-1 />
    </div>

    <c-input-text
      v-model:value="macAddressPrefix"
      :label="translate('tools.mac-address-generator.mac-address-prefix')"
      :placeholder="translate('tools.mac-address-generator.mac-address-prefix')"
      clearable
      label-position="left"
      spellcheck="false"
      :validation="prefixValidation"
      raw-text
      label-width="150px"
      label-align="right"
    />

    <c-buttons-select
      v-model:value="caseTransformer"
      :options="casesTransformers"
      :label="translate('tools.mac-address-generator.case')"
      label-width="150px"
      label-align="right"
    />

    <c-buttons-select
      v-model:value="separator"
      :options="separators"
      :label="translate('tools.mac-address-generator.separator')"
      label-width="150px"
      label-align="right"
    />

    <c-card mt-5 flex data-test-id="ulids">
      <pre m-0 m-x-auto>{{ macAddresses }}</pre>
    </c-card>

    <div flex justify-center gap-2>
      <c-button data-test-id="refresh" @click="refreshMacAddresses()">
        {{ translate('tools.mac-address-generator.refresh') }}
      </c-button>
      <c-button @click="copy()">
        {{ translate('tools.mac-address-generator.copy') }}
      </c-button>
    </div>
  </div>
</template>
