<script setup lang="ts">
import { compareSync, hashSync } from 'bcryptjs';
import { useThemeVars } from 'naive-ui';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const themeVars = useThemeVars();

const input = ref('');
const saltCount = ref(10);
const hashed = computed(() => hashSync(input.value, saltCount.value));
const { copy } = useCopy({ source: hashed, text: translate('tools.common.copied') });

const compareString = ref('');
const compareHash = ref('');
const compareMatch = computed(() => compareSync(compareString.value, compareHash.value));
</script>

<template>
  <c-card :title="translate('tools.bcrypt.hash')" class="mw-600">
    <c-input-text
      v-model:value="input"
      :placeholder="translate('tools.common.input-placeholder')"
      raw-text
      :label="translate('tools.bcrypt.string-label')"
      label-position="left"
      label-align="right"
      label-width="120px"
      mb-2
    />
    <n-form-item :label="translate('tools.bcrypt.salt-label')" label-placement="left" label-width="120">
      <n-input-number v-model:value="saltCount" :max="100" :min="0" w-full />
    </n-form-item>

    <c-input-text :value="hashed" readonly text-center />

    <div mt-5 flex justify-center>
      <c-button @click="copy()">
        {{ translate('tools.common.copy') }}
      </c-button>
    </div>
  </c-card>

  <c-card :title="translate('tools.bcrypt.compare')" class="mw-600">
    <n-form label-width="120">
      <n-form-item :label="translate('tools.bcrypt.string-label')" label-placement="left">
        <c-input-text v-model:value="compareString" raw-text />
      </n-form-item>
      <n-form-item :label="translate('tools.bcrypt.hash-label')" label-placement="left">
        <c-input-text v-model:value="compareHash" raw-text />
      </n-form-item>
      <n-form-item :label="translate('tools.bcrypt.match-label')" label-placement="left" :show-feedback="false">
        <div class="compare-result" :class="{ positive: compareMatch }">
          {{ compareMatch ? translate('tools.common.yes') : translate('tools.common.no') }}
        </div>
      </n-form-item>
    </n-form>
  </c-card>
</template>

<style lang="less" scoped>
.compare-result {
  color: v-bind('themeVars.errorColor');

  &.positive {
    color: v-bind('themeVars.successColor');
  }
}
</style>
