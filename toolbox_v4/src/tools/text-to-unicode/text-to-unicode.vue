<script setup lang="ts">
import { convertTextToUnicode, convertUnicodeToText } from './text-to-unicode.service';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const inputText = ref('');
const unicodeFromText = computed(() => inputText.value.trim() === '' ? '' : convertTextToUnicode(inputText.value));
const { copy: copyUnicode } = useCopy({ source: unicodeFromText });

const inputUnicode = ref('');
const textFromUnicode = computed(() => inputUnicode.value.trim() === '' ? '' : convertUnicodeToText(inputUnicode.value));
const { copy: copyText } = useCopy({ source: textFromUnicode });
</script>

<template>
  <c-card class="mw-600" :title="translate('tools.text-to-unicode.title-text-to-unicode')">
    <c-input-text
      v-model:value="inputText"
      multiline
      :placeholder="translate('tools.text-to-unicode.placeholder-enter-text')"
      :label="translate('tools.text-to-unicode.label-enter-text')"
      autosize
      autofocus
      raw-text
      test-id="text-to-unicode-input"
    />
    <c-input-text
      v-model:value="unicodeFromText"
      :label="translate('tools.text-to-unicode.label-unicode-from-text')"
      multiline
      raw-text
      readonly
      mt-2
      :placeholder="translate('tools.text-to-unicode.placeholder-unicode-from-text')"
      test-id="text-to-unicode-output"
    />
    <div mt-2 flex justify-center>
      <c-button :disabled="!unicodeFromText" @click="copyUnicode()">
        {{ translate('tools.text-to-unicode.copy-unicode-button-text') }}
      </c-button>
    </div>
  </c-card>

  <c-card class="mw-600" :title="translate('tools.text-to-unicode.title-unicode-to-text')">
    <c-input-text
      v-model:value="inputUnicode"
      multiline
      :placeholder="translate('tools.text-to-unicode.placeholder-enter-unicode')"
      :label="translate('tools.text-to-unicode.label-enter-unicode')"
      autosize
      raw-text
      test-id="unicode-to-text-input"
    />
    <c-input-text
      v-model:value="textFromUnicode"
      :label="translate('tools.text-to-unicode.label-text-from-unicode')"
      multiline
      raw-text
      readonly
      mt-2
      :placeholder="translate('tools.text-to-unicode.placeholder-text-from-unicode')"
      test-id="unicode-to-text-output"
    />
    <div mt-2 flex justify-center>
      <c-button :disabled="!textFromUnicode" @click="copyText()">
        {{ translate('tools.text-to-unicode.copy-text-button-text') }}
      </c-button>
    </div>
  </c-card>
</template>
