<script setup lang="ts">
import type { QRCodeErrorCorrectionLevel } from 'qrcode';
import { ref } from 'vue';
import { useQRCode } from './useQRCode';
import { useDownloadFileFromBase64 } from '@/composable/downloadBase64';
import { translate } from '@/plugins/i18n.plugin';

const foreground = ref('#000000ff');
const background = ref('#ffffffff');
const errorCorrectionLevel = ref<QRCodeErrorCorrectionLevel>('medium');

const errorCorrectionLevels = [
  { label: translate('tools.qrcode-generator.low'), value: 'low' },
  { label: translate('tools.qrcode-generator.medium'), value: 'medium' },
  { label: translate('tools.qrcode-generator.quartile'), value: 'quartile' },
  { label: translate('tools.qrcode-generator.high'), value: 'high' },
];

const text = ref('https://it-tools.tech');
const { qrcode } = useQRCode({
  text,
  color: {
    background,
    foreground,
  },
  errorCorrectionLevel,
  options: { width: 1024 },
});

const { download } = useDownloadFileFromBase64({ source: qrcode, filename: 'qr-code.png' });
</script>

<template>
  <c-card class="mw-600">
    <n-grid x-gap="12" y-gap="12" cols="1 600:3">
      <n-gi span="2">
        <c-input-text
          v-model:value="text"
          :label="translate('tools.qrcode-generator.text')"
          label-position="left"
          label-width="130px"
          label-align="right"
          multiline
          rows="1"
          autosize
          :placeholder="translate('tools.qrcode-generator.your-link-or-text')"
          mb-6
        />
        <n-form label-width="130" label-placement="left">
          <n-form-item :label="translate('tools.qrcode-generator.foreground-color')">
            <n-color-picker v-model:value="foreground" :modes="['hex']" />
          </n-form-item>
          <n-form-item :label="translate('tools.qrcode-generator.background-color')">
            <n-color-picker v-model:value="background" :modes="['hex']" />
          </n-form-item>
          <c-select
            v-model:value="errorCorrectionLevel"
            :label="translate('tools.qrcode-generator.error-resistance')"
            label-position="left"
            label-width="130px"
            label-align="right"
            :options="errorCorrectionLevels"
          />
        </n-form>
      </n-gi>
      <n-gi>
        <div flex flex-col items-center gap-3>
          <n-image :src="qrcode" width="200" />
          <c-button @click="download">
            {{ translate('tools.qrcode-generator.download-qr-code') }}
          </c-button>
        </div>
      </n-gi>
    </n-grid>
  </c-card>
</template>
