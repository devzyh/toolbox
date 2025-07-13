<script setup lang="ts">
import { computed, ref } from 'vue';
import { generateNumeronym } from './numeronym-generator.service';

import { translate } from '@/plugins/i18n.plugin';

const word = ref('');

const numeronym = computed(() => generateNumeronym(word.value));
</script>

<template>
  <div class="mw-600 flex flex-col items-center gap-4">
    <c-input-text
      v-model:value="word"
      :placeholder="translate('tools.numeronym-generator.placeholder-input')"
      size="large"
      clearable
      test-id="word-input"
    />

    <icon-mdi-arrow-down text-30px />

    <input-copyable
      :value="numeronym"
      size="large"
      readonly
      :placeholder="translate('tools.numeronym-generator.placeholder-output')"
      test-id="numeronym"
    />
  </div>
</template>
