<script setup lang="ts">
import { evaluate } from 'mathjs';
import { withDefaultOnError } from '@/utils/defaults';
import { translate } from '@/plugins/i18n.plugin';

const expression = ref('');

const result = computed(() => withDefaultOnError(() => evaluate(expression.value) ?? '', ''));
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="expression"
      rows="1"
      multiline
      :placeholder="translate('tools.math-evaluator.your-math-expression')"
      raw-text
      monospace
      autofocus
      autosize
    />

    <c-card v-if="result !== ''" :title="translate('tools.math-evaluator.result')" mt-5>
      {{ result }}
    </c-card>
  </div>
</template>
