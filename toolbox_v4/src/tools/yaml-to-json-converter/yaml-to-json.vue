<script setup lang="ts">
import { parse as parseYaml } from 'yaml';
import type { UseValidationRule } from '@/composable/validation';
import { isNotThrowing } from '@/utils/boolean';
import { withDefaultOnError } from '@/utils/defaults';
import { translate } from '@/plugins/i18n.plugin';

function transformer(value: string) {
  return withDefaultOnError(() => {
    const obj = parseYaml(value, { merge: true });
    return obj ? JSON.stringify(obj, null, 3) : '';
  }, '');
}

const rules: UseValidationRule<string>[] = [
  {
    validator: (value: string) => isNotThrowing(() => parseYaml(value)),
    message: translate('tools.yaml-to-json-converter.provided-yaml-is-not-valid'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.yaml-to-json-converter.your-yaml')"
    :output-label="translate('tools.yaml-to-json-converter.json-from-your-yaml')"
    output-language="json"
    :input-validation-rules="rules"
    :transformer="transformer"
  />
</template>
