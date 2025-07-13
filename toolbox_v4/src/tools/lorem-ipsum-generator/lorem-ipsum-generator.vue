<script setup lang="ts">
import { generateLoremIpsum } from './lorem-ipsum-generator.service';
import { useCopy } from '@/composable/copy';
import { randIntFromInterval } from '@/utils/random';
import { computedRefreshable } from '@/composable/computedRefreshable';
import { translate } from '@/plugins/i18n.plugin';

const paragraphs = ref(1);
const sentences = ref([3, 8]);
const words = ref([8, 15]);
const startWithLoremIpsum = ref(true);
const asHTML = ref(false);

const [loremIpsumText, refreshLoremIpsum] = computedRefreshable(() =>
  generateLoremIpsum({
    paragraphCount: paragraphs.value,
    asHTML: asHTML.value,
    sentencePerParagraph: randIntFromInterval(sentences.value[0], sentences.value[1]),
    wordCount: randIntFromInterval(words.value[0], words.value[1]),
    startWithLoremIpsum: startWithLoremIpsum.value,
  }),
);

const { copy } = useCopy({ source: loremIpsumText, text: translate('tools.lorem-ipsum-generator.copy') });
</script>

<template>
  <c-card class="mw-600">
    <n-form-item :label="translate('tools.lorem-ipsum-generator.paragraphs')" :show-feedback="false" label-width="200" label-placement="left">
      <n-slider v-model:value="paragraphs" :step="1" :min="1" :max="20" />
    </n-form-item>
    <n-form-item :label="translate('tools.lorem-ipsum-generator.sentences-per-paragraph')" :show-feedback="false" label-width="200" label-placement="left">
      <n-slider v-model:value="sentences" range :step="1" :min="1" :max="50" />
    </n-form-item>
    <n-form-item :label="translate('tools.lorem-ipsum-generator.words-per-sentence')" :show-feedback="false" label-width="200" label-placement="left">
      <n-slider v-model:value="words" range :step="1" :min="1" :max="50" />
    </n-form-item>
    <n-form-item :label="translate('tools.lorem-ipsum-generator.start-with-lorem-ipsum')" :show-feedback="false" label-width="200" label-placement="left">
      <n-switch v-model:value="startWithLoremIpsum" />
    </n-form-item>
    <n-form-item :label="translate('tools.lorem-ipsum-generator.as-html')" :show-feedback="false" label-width="200" label-placement="left">
      <n-switch v-model:value="asHTML" />
    </n-form-item>

    <c-input-text :value="loremIpsumText" multiline :placeholder="translate('tools.lorem-ipsum-generator.your-lorem-ipsum')" readonly mt-5 rows="5" />

    <div mt-5 flex justify-center gap-3>
      <c-button autofocus @click="copy()">
        {{ translate('tools.lorem-ipsum-generator.copy') }}
      </c-button>
      <c-button @click="refreshLoremIpsum">
        {{ translate('tools.lorem-ipsum-generator.refresh') }}
      </c-button>
    </div>
  </c-card>
</template>
