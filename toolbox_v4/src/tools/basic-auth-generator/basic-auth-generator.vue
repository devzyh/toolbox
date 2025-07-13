<script setup lang="ts">
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';
import { textToBase64 } from '@/utils/base64';

const username = ref('');
const password = ref('');
const header = computed(() => `Authorization: Basic ${textToBase64(`${username.value}:${password.value}`)}`);

const { copy } = useCopy({ source: header, text: translate('tools.common.copied') });
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="username"
      :label="translate('tools.basic-auth-generator.username')"
      :placeholder="translate('tools.common.input-placeholder')"
      clearable raw-text mb-5
    />
    <c-input-text
      v-model:value="password"
      :label="translate('tools.basic-auth-generator.password')"
      :placeholder="translate('tools.common.input-placeholder')"
      clearable
      raw-text
      mb-2
      type="password"
    />

    <c-card>
      <n-statistic :label="translate('tools.basic-auth-generator.header')" class="header">
        <n-scrollbar x-scrollable style="max-width: 550px; margin-bottom: -10px; padding-bottom: 10px" trigger="none">
          {{ header }}
        </n-scrollbar>
      </n-statistic>
    </c-card>
    <div mt-5 flex justify-center>
      <c-button @click="copy()">
        {{ translate('tools.common.copy') }}
      </c-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
::v-deep(.n-statistic-value__content) {
  font-family: monospace;
  font-size: 17px !important;
  white-space: nowrap;
}
</style>
