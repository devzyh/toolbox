<script setup lang="ts">
import InputCopyable from '../../components/InputCopyable.vue';
import { convertBase } from './integer-base-converter.model';
import { getErrorMessageIfThrows } from '@/utils/error';
import { translate } from '@/plugins/i18n.plugin';

const inputProps = {
  'labelPosition': 'left',
  'labelWidth': '170px',
  'labelAlign': 'right',
  'readonly': true,
  'mb-2': '',
} as const;

const input = ref('42');
const inputBase = ref(10);
const outputBase = ref(42);

function errorlessConvert(...args: Parameters<typeof convertBase>) {
  try {
    return convertBase(...args);
  }
  catch (err) {
    return '';
  }
}

const error = computed(() =>
  getErrorMessageIfThrows(() =>
    convertBase({ value: input.value, fromBase: inputBase.value, toBase: outputBase.value }),
  ),
);
</script>

<template>
  <div class="mw-600">
    <c-card>
      <c-input-text
        v-model:value="input"
        :label="translate('tools.base-converter.input-number')"
        :placeholder="translate('tools.base-converter.put-your-number-here')"
        label-position="left"
        label-width="110px"
        mb-2
        label-align="right"
      />

      <n-form-item :label="translate('tools.base-converter.input-base')" label-placement="left" label-width="110" :show-feedback="false">
        <n-input-number
          v-model:value="inputBase"
          max="64"
          min="2"
          :placeholder="translate('tools.base-converter.put-your-input-base-here')"
          w-full
        />
      </n-form-item>

      <n-alert v-if="error" style="margin-top: 25px" type="error">
        {{ error || translate('tools.base-converter.error-message') }}
      </n-alert>
      <n-divider />

      <InputCopyable
        :label="translate('tools.base-converter.binary')"
        v-bind="inputProps"
        :value="errorlessConvert({ value: input, fromBase: inputBase, toBase: 2 })"
        :placeholder="translate('tools.base-converter.base-will-be-here', { base: 2 })"
      />

      <InputCopyable
        :label="translate('tools.base-converter.octal')"
        v-bind="inputProps"
        :value="errorlessConvert({ value: input, fromBase: inputBase, toBase: 8 })"
        :placeholder="translate('tools.base-converter.base-will-be-here', { base: 8 })"
      />

      <InputCopyable
        :label="translate('tools.base-converter.decimal')"
        v-bind="inputProps"
        :value="errorlessConvert({ value: input, fromBase: inputBase, toBase: 10 })"
        :placeholder="translate('tools.base-converter.base-will-be-here', { base: 10 })"
      />

      <InputCopyable
        :label="translate('tools.base-converter.hexadecimal')"
        v-bind="inputProps"
        :value="errorlessConvert({ value: input, fromBase: inputBase, toBase: 16 })"
        :placeholder="translate('tools.base-converter.base-will-be-here', { base: 16 })"
      />

      <InputCopyable
        :label="translate('tools.base-converter.base64')"
        v-bind="inputProps"
        :value="errorlessConvert({ value: input, fromBase: inputBase, toBase: 64 })"
        :placeholder="translate('tools.base-converter.base-will-be-here', { base: 64 })"
      />

      <div flex items-baseline>
        <n-input-group style="width: 160px; margin-right: 10px">
          <n-input-group-label>{{ translate('tools.base-converter.custom') }}:</n-input-group-label>
          <n-input-number v-model:value="outputBase" max="64" min="2" />
        </n-input-group>

        <InputCopyable
          flex-1
          v-bind="inputProps"
          :value="errorlessConvert({ value: input, fromBase: inputBase, toBase: outputBase })"
          :placeholder="translate('tools.base-converter.base-will-be-here')"
        />
      </div>
    </c-card>
  </div>
</template>

<style lang="less" scoped>
.n-input-group:not(:first-child) {
  margin-top: 5px;
}
</style>
