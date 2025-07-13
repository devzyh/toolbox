<script setup lang="ts">
import convert from 'xml-js';
import { isValidXML } from '../xml/xml.service';
import { withDefaultOnError } from '@/utils/defaults';
import type { UseValidationRule } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const defaultValue = '<a x="1.234" y="It\'s"/>';
function transformer(value: string) {
  return withDefaultOnError(() => {
    return JSON.stringify(convert.xml2js(value, { compact: true }), null, 2);
  }, '');
}

const rules: UseValidationRule<string>[] = [
  {
    validator: isValidXML,
    message: translate('tools.xml-to-json.provided-xml-is-not-valid'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.xml-to-json.your-xml-content')"
    :input-default="defaultValue"
    :input-placeholder="translate('tools.xml-to-json.paste-your-xml-content-here')"
    :output-label="translate('tools.xml-to-json.converted-json')"
    output-language="json"
    :transformer="transformer"
    :input-validation-rules="rules"
  />
</template>
