<script setup lang="ts">
import { useWindowSize } from '@vueuse/core';
import { translate } from '@/plugins/i18n.plugin';

const { width, height } = useWindowSize();

const sections = [
  {
    name: translate('tools.device-information.screen-section'),
    information: [
      {
        label: translate('tools.device-information.screen-size'),
        value: computed(() => `${window.screen.availWidth} x ${window.screen.availHeight}`),
      },
      {
        label: translate('tools.device-information.orientation'),
        value: computed(() => window.screen.orientation.type),
      },
      {
        label: translate('tools.device-information.orientation-angle'),
        value: computed(() => `${window.screen.orientation.angle}°`),
      },
      {
        label: translate('tools.device-information.color-depth'),
        value: computed(() => `${window.screen.colorDepth} bits`),
      },
      {
        label: translate('tools.device-information.pixel-ratio'),
        value: computed(() => `${window.devicePixelRatio} dppx`),
      },
      {
        label: translate('tools.device-information.window-size'),
        value: computed(() => `${width.value} x ${height.value}`),
      },
    ],
  },
  {
    name: translate('tools.device-information.device-section'),
    information: [
      {
        label: translate('tools.device-information.browser-vendor'),
        value: computed(() => navigator.vendor),
      },
      {
        label: translate('tools.device-information.languages'),
        value: computed(() => navigator.languages.join(', ')),
      },
      {
        label: translate('tools.device-information.platform'),
        value: computed(() => navigator.platform),
      },
      {
        label: translate('tools.device-information.user-agent'),
        value: computed(() => navigator.userAgent),
      },
    ],
  },
];
</script>

<template>
  <c-card v-for="{ name, information } in sections" :key="name" class="mw-600" :title="name">
    <n-grid cols="1 400:2" x-gap="12" y-gap="12">
      <n-gi v-for="{ label, value } in information" :key="label" class="information">
        <div class="label">
          {{ label }}
        </div>

        <div class="value">
          <n-ellipsis v-if="value">
            {{ value }}
          </n-ellipsis>
          <div v-else class="undefined-value">
            {{ translate('tools.device-information.unknown-value') }}
          </div>
        </div>
      </n-gi>
    </n-grid>
  </c-card>
</template>

<style lang="less" scoped>
.information {
  padding: 14px 16px;
  border-radius: 4px;
  background-color: #aaaaaa11;

  .label {
    font-size: 14px;
    opacity: 0.8;
    line-height: 1;
    margin-bottom: 5px;
  }

  .value {
    font-size: 20px;
    font-weight: 400;
  }

  .undefined-value {
    opacity: 0.8;
  }
}
</style>
