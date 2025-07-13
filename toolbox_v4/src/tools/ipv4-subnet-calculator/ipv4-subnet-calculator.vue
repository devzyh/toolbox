<script setup lang="ts">
import { Netmask } from 'netmask';
import { useStorage } from '@vueuse/core';
import { ArrowLeft, ArrowRight } from '@vicons/tabler';
import { getIPClass } from './ipv4-subnet-calculator.models';
import { withDefaultOnError } from '@/utils/defaults';
import { isNotThrowing } from '@/utils/boolean';
import SpanCopyable from '@/components/SpanCopyable.vue';
import { translate } from '@/plugins/i18n.plugin';

const ip = useStorage('dz-tools:ipv4-subnet-calculator:ip', '192.168.0.1/24');

const getNetworkInfo = (address: string) => new Netmask(address.trim());

const networkInfo = computed(() => withDefaultOnError(() => getNetworkInfo(ip.value), undefined));

const ipValidationRules = [
  {
    message: translate('tools.ipv4-subnet-calculator.we-cannot-parse-this-address-check-the-format'),
    validator: (value: string) => isNotThrowing(() => getNetworkInfo(value.trim())),
  },
];

const sections = [
  {
    label: translate('tools.ipv4-subnet-calculator.netmask'),
    getValue: block => block.toString(),
  },
  {
    label: translate('tools.ipv4-subnet-calculator.network-address'),
    getValue: ({ base }) => base,
  },
  {
    label: translate('tools.ipv4-subnet-calculator.network-mask'),
    getValue: ({ mask }) => mask,
  },
  {
    label: translate('tools.ipv4-subnet-calculator.network-mask-in-binary'),
    getValue: ({ bitmask }) => ('1'.repeat(bitmask) + '0'.repeat(32 - bitmask)).match(/.{8}/g)?.join('.') ?? '',
  },
  {
    label: translate('tools.ipv4-subnet-calculator.cidr-notation'),
    getValue: ({ bitmask }) => `/${bitmask}`,
  },
  {
    label: translate('tools.ipv4-subnet-calculator.wildcard-mask'),
    getValue: ({ hostmask }) => hostmask,
  },
  {
    label: translate('tools.ipv4-subnet-calculator.network-size'),
    getValue: ({ size }) => String(size),
  },
  {
    label: translate('tools.ipv4-subnet-calculator.first-address'),
    getValue: ({ first }) => first,
  },
  {
    label: translate('tools.ipv4-subnet-calculator.last-address'),
    getValue: ({ last }) => last,
  },
  {
    label: translate('tools.ipv4-subnet-calculator.broadcast-address'),
    getValue: ({ broadcast }) => broadcast,
    undefinedFallback: translate('tools.ipv4-subnet-calculator.no-broadcast-address-with-this-mask'),
  },
  {
    label: translate('tools.ipv4-subnet-calculator.ip-class'),
    getValue: ({ base: ip }) => getIPClass({ ip }),
    undefinedFallback: translate('tools.ipv4-subnet-calculator.unknown-class-type'),
  },
];

function switchToBlock({ count = 1 }: { count?: number }) {
  const next = networkInfo.value?.next(count);

  if (next) {
    ip.value = next.toString();
  }
}
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="ip"
      :label="translate('tools.ipv4-subnet-calculator.an-ipv4-address-with-or-without-mask')"
      :placeholder="translate('tools.ipv4-subnet-calculator.placeholder-the-ipv4-address')"
      :validation-rules="ipValidationRules"
      mb-4
    />

    <div v-if="networkInfo">
      <n-table>
        <tbody>
          <tr v-for="{ getValue, label, undefinedFallback } in sections" :key="label">
            <td font-bold>
              {{ label }}
            </td>
            <td>
              <SpanCopyable v-if="getValue(networkInfo)" :value="getValue(networkInfo)" />
              <span v-else op-70>
                {{ undefinedFallback }}
              </span>
            </td>
          </tr>
        </tbody>
      </n-table>

      <div mt-3 flex items-center justify-between>
        <c-button @click="switchToBlock({ count: -1 })">
          <n-icon :component="ArrowLeft" />
          {{ translate('tools.ipv4-subnet-calculator.previous-block') }}
        </c-button>
        <c-button @click="switchToBlock({ count: 1 })">
          {{ translate('tools.ipv4-subnet-calculator.next-block') }}
          <n-icon :component="ArrowRight" />
        </c-button>
      </div>
    </div>
  </div>
</template>
