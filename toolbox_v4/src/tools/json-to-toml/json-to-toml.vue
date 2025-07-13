<script setup lang="ts">
import { stringify as stringifyToml } from 'iarna-toml-esm';
import JSON5 from 'json5';
import { withDefaultOnError } from '../../utils/defaults';
import type { UseValidationRule } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const convertJsonToToml = (value: string) => [stringifyToml(JSON5.parse(value))].flat().join('\n').trim();

const transformer = (value: string) => value.trim() === '' ? '' : withDefaultOnError(() => convertJsonToToml(value), '');

const rules: UseValidationRule<string>[] = [
  {
    validator: (v: string) => v === '' || JSON5.parse(v),
    message: translate('tools.json-to-toml.provided-json-is-not-valid'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.json-to-toml.your-json')"
    :input-placeholder="translate('tools.json-to-toml.placeholder-paste-your-json-here')"
    :output-label="translate('tools.json-to-toml.toml-from-your-json')"
    output-language="toml"
    :input-validation-rules="rules"
    :transformer="transformer"
  />
</template>
