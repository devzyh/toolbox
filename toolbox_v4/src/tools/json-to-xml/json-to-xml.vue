<script setup lang="ts">
import convert from 'xml-js';
import JSON5 from 'json5';
import { withDefaultOnError } from '@/utils/defaults';
import type { UseValidationRule } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const defaultValue = '{"a":{"_attributes":{"x":"1.234","y":"It\'s"}}}';
function transformer(value: string) {
  return withDefaultOnError(() => {
    return convert.js2xml(JSON5.parse(value), { compact: true });
  }, '');
}

const rules: UseValidationRule<string>[] = [
  {
    validator: (v: string) => v === '' || JSON5.parse(v),
    message: translate('tools.json-to-xml.provided-json-is-not-valid'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.json-to-xml.your-json-content')"
    :input-default="defaultValue"
    :input-placeholder="translate('tools.json-to-xml.placeholder-paste-your-json-content-here')"
    :output-label="translate('tools.json-to-xml.converted-xml')"
    output-language="xml"
    :transformer="transformer"
    :input-validation-rules="rules"
  />
</template>
