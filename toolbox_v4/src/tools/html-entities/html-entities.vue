<script setup lang="ts">
import { escape, unescape } from 'lodash';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const escapeInput = ref('<title>IT Tool</title>');
const escapeOutput = computed(() => escape(escapeInput.value));
const { copy: copyEscaped } = useCopy({ source: escapeOutput });

const unescapeInput = ref('&lt;title&gt;IT Tool&lt;/title&gt;');
const unescapeOutput = computed(() => unescape(unescapeInput.value));
const { copy: copyUnescaped } = useCopy({ source: unescapeOutput });
</script>

<template>
  <c-card class="mw-600" :title="translate('tools.html-entities.escape-html-entities')">
    <n-form-item :label="translate('tools.html-entities.your-string')">
      <c-input-text
        v-model:value="escapeInput"
        multiline
        :placeholder="translate('tools.html-entities.placeholder-string-to-escape')"
        rows="3"
        autosize
        raw-text
      />
    </n-form-item>

    <n-form-item :label="translate('tools.html-entities.your-string-escaped')">
      <c-input-text
        multiline
        readonly
        :placeholder="translate('tools.html-entities.your-string-escaped')"
        :value="escapeOutput"
        rows="3"
        autosize
      />
    </n-form-item>

    <div flex justify-center>
      <c-button @click="copyEscaped()">
        {{ translate('tools.common.copy') }}
      </c-button>
    </div>
  </c-card>

  <c-card class="mw-600" :title="translate('tools.html-entities.unescape-html-entities')">
    <n-form-item :label="translate('tools.html-entities.your-escaped-string')">
      <c-input-text
        v-model:value="unescapeInput"
        multiline
        :placeholder="translate('tools.html-entities.placeholder-string-to-unescape')"
        rows="3"
        autosize
        raw-text
      />
    </n-form-item>

    <n-form-item :label="translate('tools.html-entities.your-string-unescaped')">
      <c-input-text
        :value="unescapeOutput"
        multiline
        readonly
        :placeholder="translate('tools.html-entities.your-string-unescaped')"
        rows="3"
        autosize
      />
    </n-form-item>

    <div flex justify-center>
      <c-button @click="copyUnescaped()">
        {{ translate('tools.common.copy') }}
      </c-button>
    </div>
  </c-card>
</template>
