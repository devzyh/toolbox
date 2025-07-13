<script setup lang="ts">
import { ulid } from 'ulid';
import _ from 'lodash';
import { computedRefreshable } from '@/composable/computedRefreshable';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const amount = useStorage('dz-tools:ulid-generator:amount', 1);
const formats = [
  { label: translate('dz-tools:tools.ulid-generator:raw-format-label'), value: 'raw' },
  { label: translate('dz-tools:tools.ulid-generator:json-format-label'), value: 'json' },
] as const;
const format = useStorage<typeof formats[number]['value']>('ulid-generator-format', formats[0].value);

const [ulids, refreshUlids] = computedRefreshable(() => {
  const ids = _.times(amount.value, () => ulid());

  if (format.value === 'json') {
    return JSON.stringify(ids, null, 2);
  }

  return ids.join('\n');
});

const { copy } = useCopy({ source: ulids, text: translate('tools.ulid-generator.copy-button-text') });
</script>

<template>
  <div class="mw-600 flex flex-col justify-center gap-2">
    <div flex items-center>
      <label w-75px>{{ translate('tools.ulid-generator.quantity-label') }}</label>
      <n-input-number v-model:value="amount" min="1" max="100" flex-1 />
    </div>

    <c-buttons-select
      v-model:value="format"
      :options="formats"
      :label="translate('tools.ulid-generator.format-label')"
      label-width="75px"
    />

    <c-card mt-5 flex data-test-id="ulids">
      <pre m-0 m-x-auto>{{ ulids }}</pre>
    </c-card>

    <div flex justify-center gap-2>
      <c-button data-test-id="refresh" @click="refreshUlids()">
        {{ translate('tools.ulid-generator.refresh-button-text') }}
      </c-button>
      <c-button @click="copy()">
        {{ translate('tools.ulid-generator.copy-button-text') }}
      </c-button>
    </div>
  </div>
</template>
