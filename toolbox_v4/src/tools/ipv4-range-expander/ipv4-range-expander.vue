<script setup lang="ts">
import { Exchange } from '@vicons/tabler';
import { isValidIpv4 } from '../ipv4-address-converter/ipv4-address-converter.service';
import type { Ipv4RangeExpanderResult } from './ipv4-range-expander.types';
import { calculateCidr } from './ipv4-range-expander.service';
import ResultRow from './result-row.vue';
import { useValidation } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const rawStartAddress = useStorage('dz-tools:ipv4-range-expander:startAddress', '192.168.1.1');
const rawEndAddress = useStorage('dz-tools:ipv4-range-expander:endAddress', '192.168.6.255');

const result = computed(() => calculateCidr({ startIp: rawStartAddress.value, endIp: rawEndAddress.value }));

const calculatedValues = [
  {
    label: translate('tools.ipv4-range-expander.start-address-label'),
    getOldValue: () => rawStartAddress.value,
    getNewValue: (result: Ipv4RangeExpanderResult | undefined) => result?.newStart,
  },
  {
    label: translate('tools.ipv4-range-expander.end-address-label'),
    getOldValue: () => rawEndAddress.value,
    getNewValue: (result: Ipv4RangeExpanderResult | undefined) => result?.newEnd,
  },
  {
    label: translate('tools.ipv4-range-expander.addresses-in-range'),
    getOldValue: (result: Ipv4RangeExpanderResult | undefined) => result?.oldSize?.toLocaleString(),
    getNewValue: (result: Ipv4RangeExpanderResult | undefined) => result?.newSize?.toLocaleString(),
  },
  {
    label: translate('tools.ipv4-range-expander.cidr'),
    getOldValue: () => '',
    getNewValue: (result: Ipv4RangeExpanderResult | undefined) => result?.newCidr,
  },
];

const startIpValidation = useValidation({
  source: rawStartAddress,
  rules: [{
    message: translate('tools.ipv4-range-expander.invalid-ipv4-address'),
    validator: ip => isValidIpv4({ ip }),
  }],
});
const endIpValidation = useValidation({
  source: rawEndAddress,
  rules: [{
    message: translate('tools.ipv4-range-expander.invalid-ipv4-address'),
    validator: ip => isValidIpv4({ ip }),
  }],
});

const showResult = computed(() => endIpValidation.isValid && startIpValidation.isValid && result.value !== undefined);

function onSwitchStartEndClicked() {
  const tmpStart = rawStartAddress.value;
  rawStartAddress.value = rawEndAddress.value;
  rawEndAddress.value = tmpStart;
}
</script>

<template>
  <div class="mw-600">
    <div mb-4 flex gap-4>
      <c-input-text
        v-model:value="rawStartAddress"
        :label="translate('tools.ipv4-range-expander.start-address')"
        :placeholder="translate('tools.ipv4-range-expander.placeholder-start-ipv4-address')"
        :validation="startIpValidation"
        clearable
      />

      <c-input-text
        v-model:value="rawEndAddress"
        :label="translate('tools.ipv4-range-expander.end-address')"
        :placeholder="translate('tools.ipv4-range-expander.placeholder-end-ipv4-address')"
        :validation="endIpValidation"
        clearable
      />
    </div>

    <n-table v-if="showResult" data-test-id="result">
      <thead>
        <tr>
          <th scope="col">
          &nbsp;
          </th>
          <th scope="col">
            {{ translate('tools.ipv4-range-expander.old-value') }}
          </th>
          <th scope="col">
            {{ translate('tools.ipv4-range-expander.new-value') }}
          </th>
        </tr>
      </thead>
      <tbody>
        <ResultRow
          v-for="{ label, getOldValue, getNewValue } in calculatedValues"
          :key="label"
          :label="label"
          :old-value="getOldValue(result)"
          :new-value="getNewValue(result)"
        />
      </tbody>
    </n-table>
    <n-alert
      v-else-if="startIpValidation.isValid && endIpValidation.isValid"
      :title="translate('tools.ipv4-range-expander.invalid-combination-of-start-and-end-ipv4-address')"
      type="error"
    >
      <div my-3 op-70>
        {{ translate('tools.ipv4-range-expander.the-end-ipv4-address-is-lower-than-the-start-ipv4-address') }}
        {{ translate('tools.ipv4-range-expander.this-is-not-valid-and-no-result-could-be-calculated') }}
        {{ translate('tools.ipv4-range-expander.in-the-most-cases-the-solution-to-solve-this-problem-is-to-change-start-and-end-address') }}
      </div>

      <c-button @click="onSwitchStartEndClicked">
        <n-icon mr-2 :component="Exchange" depth="3" size="22" />
        {{ translate('tools.ipv4-range-expander.switch-start-and-end-ipv4-address') }}
      </c-button>
    </n-alert>
  </div>
</template>
