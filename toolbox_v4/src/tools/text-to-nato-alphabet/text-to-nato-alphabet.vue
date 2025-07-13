<script setup lang="ts">
import { textToNatoAlphabet } from './text-to-nato-alphabet.service';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const input = ref('');
const natoText = computed(() => textToNatoAlphabet({ text: input.value }));
const { copy } = useCopy({ source: natoText, text: translate('tools.text-to-nato-alphabet.copied-message') });
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="input"
      :label="translate('tools.text-to-nato-alphabet.label-your-text')"
      :placeholder="translate('tools.text-to-nato-alphabet.placeholder-your-text')"
      clearable
      mb-5
    />

    <div v-if="natoText">
      <div mb-2>
        {{ translate('tools.text-to-nato-alphabet.description-nato-text') }}
      </div>
      <c-card>
        {{ natoText }}
      </c-card>

      <div mt-3 flex justify-center>
        <c-button autofocus @click="copy()">
          {{ translate('tools.text-to-nato-alphabet.copy-nato-button-text') }}
        </c-button>
      </div>
    </div>
  </div>
</template>
