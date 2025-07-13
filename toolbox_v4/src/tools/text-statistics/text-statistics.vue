<script setup lang="ts">
import { getStringSizeInBytes } from './text-statistics.service';
import { formatBytes } from '@/utils/convert';
import { translate } from '@/plugins/i18n.plugin';

const text = ref('');
</script>

<template>
  <c-card class="mw-600">
    <c-input-text
      v-model:value="text"
      multiline
      :placeholder="translate('tools.text-statistics.placeholder')"
      rows="20"
    />

    <div mt-5 flex>
      <n-statistic :label="translate('tools.text-statistics.character-count')" :value="text.length" flex-1 />
      <n-statistic
        :label="translate('tools.text-statistics.wordCount')"
        :value="text === '' ? 0 : text.split(/\s+/).length"
        flex-1
      />
      <n-statistic
        :label="translate('tools.text-statistics.lineCount')"
        :value="text === '' ? 0 : text.split(/\r\n|\r|\n/).length"
        flex-1
      />
      <n-statistic
        :label="translate('tools.text-statistics.byteSize')"
        :value="formatBytes(getStringSizeInBytes(text))"
        flex-1
      />
    </div>
  </c-card>
</template>
