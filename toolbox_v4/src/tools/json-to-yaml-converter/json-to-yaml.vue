<script setup lang="ts">
import { stringify } from 'yaml';
import JSON5 from 'json5';
import type { UseValidationRule } from '@/composable/validation';
import { isNotThrowing } from '@/utils/boolean';
import { withDefaultOnError } from '@/utils/defaults';
import { translate } from '@/plugins/i18n.plugin';

const transformer = (value: string) => withDefaultOnError(() => stringify(JSON5.parse(value)), '');

const rules: UseValidationRule<string>[] = [
  {
    validator: (value: string) => value === '' || isNotThrowing(() => stringify(JSON5.parse(value))),
    message: translate('tools.json-to-yaml-converter.provided-json-is-not-valid'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.json-to-yaml-converter.your-json')"
    :input-placeholder="translate('tools.json-to-yaml-converter.placeholder-paste-your-json-here')"
    :output-label="translate('tools.json-to-yaml-converter.yaml-from-your-json')"
    output-language="yaml"
    :input-validation-rules="rules"
    :transformer="transformer"
  />
</template>
