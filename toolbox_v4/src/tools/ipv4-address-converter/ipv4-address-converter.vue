<script setup lang="ts">
import { convertBase } from '../integer-base-converter/integer-base-converter.model';
import { ipv4ToInt, ipv4ToIpv6, isValidIpv4 } from './ipv4-address-converter.service';
import { useValidation } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const rawIpAddress = useStorage('dz-tools:ipv4-converter:ip', '192.168.1.1');

const convertedSections = computed(() => {
  const ipInDecimal = ipv4ToInt({ ip: rawIpAddress.value });

  return [
    {
      label: translate('tools.ipv4-address-converter.decimal'),
      value: String(ipInDecimal),
    },
    {
      label: translate('tools.ipv4-address-converter.hexadecimal'),
      value: convertBase({ fromBase: 10, toBase: 16, value: String(ipInDecimal) }).toUpperCase(),
    },
    {
      label: translate('tools.ipv4-address-converter.binary'),
      value: convertBase({ fromBase: 10, toBase: 2, value: String(ipInDecimal) }),
    },
    {
      label: translate('tools.ipv4-address-converter.ipv6'),
      value: ipv4ToIpv6({ ip: rawIpAddress.value }),
    },
    {
      label: translate('tools.ipv4-address-converter.ipv6-short'),
      value: ipv4ToIpv6({ ip: rawIpAddress.value, prefix: '::ffff:' }),
    },
  ];
});

const { attrs: validationAttrs } = useValidation({
  source: rawIpAddress,
  rules: [{
    message: translate('tools.ipv4-address-converter.invalid-ipv4-address'),
    validator: ip => isValidIpv4({ ip }),
  }],
});
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="rawIpAddress"
      :label="translate('tools.ipv4-address-converter.the-ipv4-address')"
      :placeholder="translate('tools.ipv4-address-converter.placeholder-the-ipv4-address')"
    />

    <n-divider />

    <input-copyable
      v-for="{ label, value } of convertedSections"
      :key="label"
      :label="label"
      label-position="left"
      label-width="100px"
      label-align="right"
      mb-2
      :value="validationAttrs.validationStatus === 'error' ? '' : value"
      :placeholder="translate('tools.ipv4-address-converter.set-a-correct-ipv4-address')"
    />
  </div>
</template>
