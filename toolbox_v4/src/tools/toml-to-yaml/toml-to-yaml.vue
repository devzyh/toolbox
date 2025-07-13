<script setup lang="ts">
import { parse as parseToml } from 'iarna-toml-esm';
import { stringify as stringifyToYaml } from 'yaml';
import { withDefaultOnError } from '../../utils/defaults';
import { isValidToml } from '../toml-to-json/toml.services';
import type { UseValidationRule } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const transformer = (value: string) => value.trim() === '' ? '' : withDefaultOnError(() => stringifyToYaml(parseToml(value)), '');

const rules: UseValidationRule<string>[] = [
  {
    validator: isValidToml,
    message: translate('tools.toml-to-yaml.validation-message'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.toml-to-yaml.input-label')"
    :input-placeholder="translate('tools.toml-to-yaml.input-placeholder')"
    :output-label="translate('tools.toml-to-yaml.output-label')"
    output-language="yaml"
    :input-validation-rules="rules"
    :transformer="transformer"
  />
</template>
