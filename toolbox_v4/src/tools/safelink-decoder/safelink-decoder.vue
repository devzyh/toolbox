<script setup lang="ts">
import { decodeSafeLinksURL } from './safelink-decoder.service';
import TextareaCopyable from '@/components/TextareaCopyable.vue';
import { translate } from '@/plugins/i18n.plugin';

const inputSafeLinkUrl = ref('');
const outputDecodedUrl = computed(() => {
  try {
    return decodeSafeLinksURL(inputSafeLinkUrl.value);
  }
  catch (e: any) {
    return e.toString();
  }
});
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="inputSafeLinkUrl"
      raw-text
      :placeholder="translate('tools.safelink-decoder.input-outlook-safelink-url')"
      autofocus
      :label="translate('tools.safelink-decoder.input-outlook-safelink-url')"
    />

    <n-divider />

    <n-form-item :label="translate('tools.safelink-decoder.output-decoded-url')">
      <TextareaCopyable :value="outputDecodedUrl" :word-wrap="true" />
    </n-form-item>
  </div>
</template>
