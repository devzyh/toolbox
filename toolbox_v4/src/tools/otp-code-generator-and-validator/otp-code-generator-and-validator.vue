<script setup lang="ts">
import { useTimestamp } from '@vueuse/core';
import { useThemeVars } from 'naive-ui';
import { useQRCode } from '../qr-code-generator/useQRCode';
import { base32toHex, buildKeyUri, generateSecret, generateTOTP, getCounterFromTime } from './otp.service';
import TokenDisplay from './token-display.vue';
import { useStyleStore } from '@/stores/style.store';
import InputCopyable from '@/components/InputCopyable.vue';
import { computedRefreshable } from '@/composable/computedRefreshable';

import { translate } from '@/plugins/i18n.plugin';

const now = useTimestamp();
const interval = computed(() => (now.value / 1000) % 30);
const theme = useThemeVars();
const styleStore = useStyleStore();

const secret = ref(generateSecret());

function refreshSecret() {
  secret.value = generateSecret();
}

const [tokens] = computedRefreshable(
  () => ({
    previous: generateTOTP({ key: secret.value, now: now.value - 30000 }),
    current: generateTOTP({ key: secret.value, now: now.value }),
    next: generateTOTP({ key: secret.value, now: now.value + 30000 }),
  }),
  { throttle: 500 },
);

const keyUri = computed(() => buildKeyUri({ secret: secret.value }));

const { qrcode } = useQRCode({
  text: keyUri,
  color: {
    background: computed(() => (styleStore.isDarkTheme ? '#ffffff' : '#00000000')),
    foreground: '#000000',
  },
  options: { width: 210 },
});

const secretValidationRules = [
  {
    message: translate('tools.otp-generator.secret-validation-rules.base32'),
    validator: (value: string) => value.toUpperCase().match(/^[A-Z234567]+$/),
  },
  {
    message: translate('tools.otp-generator.secret-validation-rules.required'),
    validator: (value: string) => value !== '',
  },
];
</script>

<template>
  <div style="max-width: 350px">
    <c-input-text
      v-model:value="secret"
      :label="translate('tools.otp-generator.secret-label')"
      :placeholder="translate('tools.otp-generator.secret-placeholder')"
      mb-5
      :validation-rules="secretValidationRules"
    >
      <template #suffix>
        <c-tooltip :tooltip="translate('tools.otp-generator.generate-new-secret-tooltip')">
          <c-button circle variant="text" size="small" @click="refreshSecret">
            <icon-mdi-refresh />
          </c-button>
        </c-tooltip>
      </template>
    </c-input-text>

    <div>
      <TokenDisplay :tokens="tokens" />

      <n-progress :percentage="(100 * interval) / 30" :color="theme.primaryColor" :show-indicator="false" />
      <div style="text-align: center">
        {{ translate('tools.otp-generator.next-in').replace('{seconds}', String(Math.floor(30 - interval)).padStart(2, '0')) }}
      </div>
    </div>
    <div mt-4 flex flex-col items-center justify-center gap-3>
      <n-image :src="qrcode" />
      <c-button :href="keyUri" target="_blank">
        {{ translate('tools.otp-generator.open-key-uri-new-tab') }}
      </c-button>
    </div>
  </div>
  <div style="max-width: 350px">
    <InputCopyable
      :label="translate('tools.otp-generator.secret-in-hex-label')"
      :value="base32toHex(secret)"
      readonly
      :placeholder="translate('tools.otp-generator.secret-in-hex-placeholder')"
      mb-5
    />

    <InputCopyable
      :label="translate('tools.otp-generator.epoch-label')"
      :value="Math.floor(now / 1000).toString()"
      readonly
      mb-5
      :placeholder="translate('tools.otp-generator.epoch-placeholder')"
    />

    <p>{{ translate('tools.otp-generator.iteration-count-label') }}</p>

    <InputCopyable
      :value="String(getCounterFromTime({ now, timeStep: 30 }))"
      readonly
      :label="translate('tools.otp-generator.iteration-count-label')"
      label-position="left"
      label-width="90px"
      label-align="right"
      :placeholder="translate('tools.otp-generator.iteration-count-placeholder')"
    />

    <InputCopyable
      :value="getCounterFromTime({ now, timeStep: 30 }).toString(16).padStart(16, '0')"
      readonly
      :placeholder="translate('tools.otp-generator.padded-hex-placeholder')"
      label-position="left"
      label-width="90px"
      label-align="right"
      :label="translate('tools.otp-generator.padded-hex-label')"
    />
  </div>
</template>

<style lang="less" scoped>
.n-progress {
  margin-top: 10px;
  ::v-deep(.n-progress-graph-line-fill) {
    transition-duration: 0.05s !important;
  }
}

.token {
  text-align: center;
  &.token-current {
    font-size: 20px;
  }
}
</style>
