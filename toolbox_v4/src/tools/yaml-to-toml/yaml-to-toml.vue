<script setup lang="ts">
import { stringify as stringifyToml } from 'iarna-toml-esm';
import { parse as parseYaml } from 'yaml';
import { withDefaultOnError } from '../../utils/defaults';
import type { UseValidationRule } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const convertYamlToToml = (value: string) => [stringifyToml(parseYaml(value))].flat().join('\n').trim();

const transformer = (value: string) => value.trim() === '' ? '' : withDefaultOnError(() => convertYamlToToml(value), '');

const rules: UseValidationRule<string>[] = [
  {
    validator: (v: string) => v === '' || !!parseYaml(v),
    message: translate('tools.yaml-to-toml.provided-yaml-is-not-valid'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.yaml-to-toml.your-yaml')"
    :input-placeholder="translate('tools.yaml-to-toml.paste-your-yaml-here')"
    :output-label="translate('tools.yaml-to-toml.toml-from-your-yaml')"
    output-language="toml"
    :input-validation-rules="rules"
    :transformer="transformer"
  />
</template>
