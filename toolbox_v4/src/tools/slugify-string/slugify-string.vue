<script setup lang="ts">
import slugify from '@sindresorhus/slugify';
import { withDefaultOnError } from '@/utils/defaults';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const input = ref('');
const slug = computed(() => withDefaultOnError(() => slugify(input.value), ''));
const { copy } = useCopy({ source: slug, text: translate('tools.slugify-string.copy-slug') });
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="input"
      multiline
      :placeholder="translate('tools.slugify-string.put-your-string-here')"
      :label="translate('tools.slugify-string.your-string-to-slugify')"
      autofocus
      raw-text
      mb-5
    />

    <c-input-text
      :value="slug"
      multiline
      readonly
      :placeholder="translate('tools.slugify-string.your-slug-will-be-generated-here')"
      :label="translate('tools.slugify-string.your-slug')"
      mb-5
    />

    <div flex justify-center>
      <c-button :disabled="slug.length === 0" @click="copy()">
        {{ translate('tools.slugify-string.copy-slug') }}
      </c-button>
    </div>
  </div>
</template>
