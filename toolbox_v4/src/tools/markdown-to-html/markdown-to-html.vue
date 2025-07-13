<script setup lang="ts">
import markdownit from 'markdown-it';
import TextareaCopyable from '@/components/TextareaCopyable.vue';
import { translate } from '@/plugins/i18n.plugin';

const inputMarkdown = ref('');
const outputHtml = computed(() => {
  const md = markdownit();
  return md.render(inputMarkdown.value);
});

function printHtml() {
  const w = window.open();
  if (w === null) {
    return;
  }
  w.document.body.innerHTML = outputHtml.value;
  w.print();
}
</script>

<template>
  <div class="w-full">
    <c-input-text
      v-model:value="inputMarkdown"
      multiline raw-text
      :placeholder="translate('tools.markdown-to-html.your-markdown-to-convert')"
      rows="8"
      autofocus
      :label="translate('tools.markdown-to-html.your-markdown-to-convert')"
    />

    <n-divider />

    <n-form-item :label="translate('tools.markdown-to-html.output-html')">
      <TextareaCopyable :value="outputHtml" :word-wrap="true" language="html" />
    </n-form-item>

    <div flex justify-center>
      <n-button @click="printHtml">
        {{ translate('tools.markdown-to-html.print-as-pdf') }}
      </n-button>
    </div>
  </div>
</template>
