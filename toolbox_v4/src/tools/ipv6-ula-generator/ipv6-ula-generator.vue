<script setup lang="ts">
import { SHA1 } from 'crypto-js';
import InputCopyable from '@/components/InputCopyable.vue';
import { macAddressValidation } from '@/utils/macAddress';
import { translate } from '@/plugins/i18n.plugin';

const macAddress = ref('20:37:06:12:34:56');

const calculatedSections = computed(() => {
  const timestamp = new Date().getTime();
  const hex40bit = SHA1(timestamp + macAddress.value)
    .toString()
    .substring(30);

  const ula = `fd${hex40bit.substring(0, 2)}:${hex40bit.substring(2, 6)}:${hex40bit.substring(6)}`;

  return [
    {
      label: translate('tools.ipv6-ula-generator.ipv6-ula'),
      value: `${ula}::/48`,
    },
    {
      label: translate('tools.ipv6-ula-generator.first-routable-block'),
      value: `${ula}:0::/64`,
    },
    {
      label: translate('tools.ipv6-ula-generator.last-routable-block'),
      value: `${ula}:ffff::/64`,
    },
  ];
});

const addressValidation = macAddressValidation(macAddress);
</script>

<template>
  <div class="mw-600">
    <n-alert :title="translate('tools.ipv6-ula-generator.info')" type="info">
      {{ translate('tools.ipv6-ula-generator.random-ula') }}
    </n-alert>

    <c-input-text
      v-model:value="macAddress"
      :placeholder="translate('tools.ipv6-ula-generator.type-a-mac-address')"
      clearable
      :label="translate('tools.ipv6-ula-generator.mac-address')"
      raw-text
      my-8
      :validation="addressValidation"
    />

    <div v-if="addressValidation.isValid">
      <InputCopyable
        v-for="{ label, value } in calculatedSections"
        :key="label"
        :value="value"
        :label="label"
        label-width="160px"
        label-align="right"
        label-position="left"
        readonly
        mb-2
      />
    </div>
  </div>
</template>
