<script setup lang="ts">
import {
  EAPMethods,
  EAPPhase2Methods,
  useWifiQRCode,
} from './useQRCode';
import { useDownloadFileFromBase64 } from '@/composable/downloadBase64';
import { translate } from '@/plugins/i18n.plugin';

const foreground = ref('#000000ff');
const background = ref('#ffffffff');

const ssid = ref();
const password = ref();
const eapMethod = ref();
const isHiddenSSID = ref(false);
const eapAnonymous = ref(false);
const eapIdentity = ref();
const eapPhase2Method = ref();

const { qrcode, encryption } = useWifiQRCode({
  ssid,
  password,
  eapMethod,
  isHiddenSSID,
  eapAnonymous,
  eapIdentity,
  eapPhase2Method,
  color: {
    background,
    foreground,
  },
  options: { width: 1024 },
});

const { download } = useDownloadFileFromBase64({ source: qrcode, filename: 'qr-code.png' });
</script>

<template>
  <c-card class="mw-600">
    <div grid grid-cols-1 gap-12>
      <div>
        <c-select
          v-model:value="encryption"
          mb-4
          :label="translate('tools.wifi-qrcode-generator.encryption-method')"
          default-value="WPA"
          label-position="left"
          label-width="130px"
          label-align="right"
          :options="[
            {
              label: translate('tools.wifi-qrcode-generator.no-password'),
              value: 'nopass',
            },
            {
              label: translate('tools.wifi-qrcode-generator.wpa-wpa2'),
              value: 'WPA',
            },
            {
              label: translate('tools.wifi-qrcode-generator.wep'),
              value: 'WEP',
            },
            {
              label: translate('tools.wifi-qrcode-generator.wpa2-eap'),
              value: 'WPA2-EAP',
            },
          ]"
        />
        <div class="mb-6 flex flex-row items-center gap-2">
          <c-input-text
            v-model:value="ssid"
            label-position="left"
            label-width="130px"
            label-align="right"
            :label="translate('tools.wifi-qrcode-generator.ssid')"
            rows="1"
            autosize
            :placeholder="translate('tools.wifi-qrcode-generator.your-wifi-ssid')"
            mb-6
          />
          <n-checkbox v-model:checked="isHiddenSSID">
            {{ translate('tools.wifi-qrcode-generator.hidden-ssid') }}
          </n-checkbox>
        </div>
        <c-input-text
          v-if="encryption !== 'nopass'"
          v-model:value="password"
          label-position="left"
          label-width="130px"
          label-align="right"
          :label="translate('tools.wifi-qrcode-generator.password')"
          rows="1"
          autosize
          type="password"
          :placeholder="translate('tools.wifi-qrcode-generator.your-wifi-password')"
          mb-6
        />
        <c-select
          v-if="encryption === 'WPA2-EAP'"
          v-model:value="eapMethod"
          :label="translate('tools.wifi-qrcode-generator.eap-method')"
          label-position="left"
          label-width="130px"
          label-align="right"
          :options="EAPMethods.map((method) => ({ label: method, value: method }))"
          searchable
          mb-4
        />
        <div v-if="encryption === 'WPA2-EAP'" class="mb-6 flex flex-row items-center gap-2">
          <c-input-text
            v-model:value="eapIdentity"
            label-position="left"
            label-width="130px"
            label-align="right"
            :label="translate('tools.wifi-qrcode-generator.identity')"
            rows="1"
            autosize
            :placeholder="translate('tools.wifi-qrcode-generator.your-eap-identity')"
            mb-6
          />
          <n-checkbox v-model:checked="eapAnonymous">
            {{ translate('tools.wifi-qrcode-generator.anonymous') }}
          </n-checkbox>
        </div>
        <c-select
          v-if="encryption === 'WPA2-EAP'"
          v-model:value="eapPhase2Method"
          :label="translate('tools.wifi-qrcode-generator.eap-phase2-method')"
          label-position="left"
          label-width="130px"
          label-align="right"
          :options="EAPPhase2Methods.map((method) => ({ label: method, value: method }))"
          searchable
          mb-4
        />
        <n-form label-width="130" label-placement="left">
          <n-form-item :label="`${translate('tools.wifi-qrcode-generator.foreground-color')}:`">
            <n-color-picker v-model:value="foreground" :modes="['hex']" />
          </n-form-item>
          <n-form-item :label="`${translate('tools.wifi-qrcode-generator.background-color')}:`">
            <n-color-picker v-model:value="background" :modes="['hex']" />
          </n-form-item>
        </n-form>
      </div>
      <div v-if="qrcode">
        <div flex flex-col items-center gap-3>
          <img alt="wifi-qrcode" :src="qrcode" width="200">
          <c-button @click="download">
            {{ translate('tools.wifi-qrcode-generator.download-qr-code') }}
          </c-button>
        </div>
      </div>
    </div>
  </c-card>
</template>
