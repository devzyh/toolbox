<script setup lang="ts">
import { parse as parseToml } from 'iarna-toml-esm';
import { withDefaultOnError } from '../../utils/defaults';
import { isValidToml } from './toml.services';
import type { UseValidationRule } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const transformer = (value: string) => value === '' ? '' : withDefaultOnError(() => JSON.stringify(parseToml(value), null, 3), '');

const rules: UseValidationRule<string>[] = [
  {
    validator: isValidToml,
    message: translate('tools.toml-to-json.validation-message'),
  },
];
</script>

<template>
  <format-transformer
    :input-label="translate('tools.toml-to-json.input-label')"
    :input-placeholder="translate('tools.toml-to-json.input-placeholder')"
    :output-label="translate('tools.toml-to-json.output-label')"
    output-language="json"
    :input-validation-rules="rules"
    :transformer="transformer"
  />
</template>
