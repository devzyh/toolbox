<script setup lang="ts">
import JSON5 from 'json5';
import { convertArrayToCsv } from './json-to-csv.service';
import type { UseValidationRule } from '@/composable/validation';
import { withDefaultOnError } from '@/utils/defaults';
import { translate } from '@/plugins/i18n.plugin';

function transformer(value: string) {
  return withDefaultOnError(() => {
    if (value === '') {
      return '';
    }
    return convertArrayToCsv({ array: JSON5.parse(value) });
  }, '');
}

const rules: UseValidationRule<string>[] = [
  {
    validator: (v: string) => v === '' || JSON5.parse(v),
    message: translate('tools.json-to-csv.provided-json-is-not-valid'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.json-to-csv.your-raw-json')"
    :input-placeholder="translate('tools.json-to-csv.placeholder-paste-your-raw-json-here')"
    :output-label="translate('tools.json-to-csv.csv-version-of-your-json')"
    :input-validation-rules="rules"
    :transformer="transformer"
  />
</template>
