<script setup lang="ts">
import { computed, ref } from 'vue';
import { getPasswordCrackTimeEstimation } from './password-strength-analyser.service';

import { translate } from '@/plugins/i18n.plugin';

const password = ref('');
const crackTimeEstimation = computed(() => getPasswordCrackTimeEstimation({ password: password.value }));

const details = computed(() => [
  {
    label: translate('tools.password-strength-analyser.password-length'),
    value: crackTimeEstimation.value.passwordLength,
  },
  {
    label: translate('tools.password-strength-analyser.entropy'),
    value: Math.round(crackTimeEstimation.value.entropy * 100) / 100,
  },
  {
    label: translate('tools.password-strength-analyser.character-set-size'),
    value: crackTimeEstimation.value.charsetLength,
  },
  {
    label: translate('tools.password-strength-analyser.score'),
    value: `${Math.round(crackTimeEstimation.value.score * 100)} / 100`,
  },
]);
</script>

<template>
  <div class="mw-600 flex flex-col gap-3">
    <c-input-text
      v-model:value="password"
      type="password"
      :placeholder="translate('tools.password-strength-analyser.placeholder')"
      clearable
      autofocus
      raw-text
      test-id="password-input"
    />

    <c-card text-center>
      <div op-60>
        {{ translate('tools.password-strength-analyser.crack-duration-title') }}
      </div>
      <div text-2xl data-test-id="crack-duration">
        {{ crackTimeEstimation.crackDurationFormatted }}
      </div>
    </c-card>
    <c-card>
      <div v-for="({ label, value }) of details" :key="label" flex gap-3>
        <div flex-1 text-right op-60>
          {{ label }}
        </div>
        <div flex-1 text-left>
          {{ value }}
        </div>
      </div>
    </c-card>
    <div op-70>
      <span font-bold>{{ translate('tools.password-strength-analyser.note') }} </span>
      {{ translate('tools.password-strength-analyser.computed-strength-note') }}
    </div>
  </div>
</template>
