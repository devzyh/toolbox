<script setup lang="ts">
import {
  camelCase,
  capitalCase,
  constantCase,
  dotCase,
  headerCase,
  noCase,
  paramCase,
  pascalCase,
  pathCase,
  sentenceCase,
  snakeCase,
} from 'change-case';
import InputCopyable from '../../components/InputCopyable.vue';
import { translate } from '@/plugins/i18n.plugin';

const baseConfig = {
  stripRegexp: /[^A-Za-zÀ-ÖØ-öø-ÿ]+/gi,
};

const input = ref('lorem ipsum dolor sit amet');

const formats = computed(() => [
  {
    label: translate('tools.case-converter.lowercase'),
    value: input.value.toLocaleLowerCase(),
  },
  {
    label: translate('tools.case-converter.uppercase'),
    value: input.value.toLocaleUpperCase(),
  },
  {
    label: translate('tools.case-converter.camelcase'),
    value: camelCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.capitalcase'),
    value: capitalCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.constantcase'),
    value: constantCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.dotcase'),
    value: dotCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.headercase'),
    value: headerCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.nocase'),
    value: noCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.paramcase'),
    value: paramCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.pascalcase'),
    value: pascalCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.pathcase'),
    value: pathCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.sentencecase'),
    value: sentenceCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.snakecase'),
    value: snakeCase(input.value, baseConfig),
  },
  {
    label: translate('tools.case-converter.mockingcase'),
    value: input.value
      .split('')
      .map((char, index) => (index % 2 === 0 ? char.toUpperCase() : char.toLowerCase()))
      .join(''),
  },
]);

const inputLabelAlignmentConfig = {
  labelPosition: 'left',
  labelWidth: '120px',
  labelAlign: 'right',
};
</script>

<template>
  <c-card class="mw-600">
    <c-input-text
      v-model:value="input"
      :label="translate('tools.common.input-label')"
      :placeholder="translate('tools.common.input-placeholder')"
      raw-text
      v-bind="inputLabelAlignmentConfig"
    />

    <div my-16px divider />

    <InputCopyable
      v-for="format in formats"
      :key="format.label"
      :value="format.value"
      :label="format.label"
      v-bind="inputLabelAlignmentConfig"
      mb-1
    />
  </c-card>
</template>
