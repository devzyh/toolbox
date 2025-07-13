<script setup lang="ts">
import { convertAsciiBinaryToText, convertTextToAsciiBinary } from './text-to-binary.models';
import { withDefaultOnError } from '@/utils/defaults';
import { useCopy } from '@/composable/copy';
import { isNotThrowing } from '@/utils/boolean';
import { translate } from '@/plugins/i18n.plugin';

const inputText = ref('');
const binaryFromText = computed(() => convertTextToAsciiBinary(inputText.value));
const { copy: copyBinary } = useCopy({ source: binaryFromText });

const inputBinary = ref('');
const textFromBinary = computed(() => withDefaultOnError(() => convertAsciiBinaryToText(inputBinary.value), ''));
const inputBinaryValidationRules = [
  {
    validator: (value: string) => isNotThrowing(() => convertAsciiBinaryToText(value)),
    message: translate('tools.text-to-binary.validation-message'),
  },
];
const { copy: copyText } = useCopy({ source: textFromBinary });
</script>

<template>
  <c-card class="mw-600" :title="translate('tools.text-to-binary.title-text-to-binary')">
    <c-input-text
      v-model:value="inputText"
      multiline
      :placeholder="translate('tools.text-to-binary.placeholder-text')"
      :label="translate('tools.text-to-binary.label-enter-text')"
      autosize
      autofocus
      raw-text
      test-id="text-to-binary-input"
    />
    <c-input-text
      v-model:value="binaryFromText"
      :label="translate('tools.text-to-binary.label-binary-from-text')"
      multiline
      raw-text
      readonly
      mt-2
      :placeholder="translate('tools.text-to-binary.placeholder-binary')"
      test-id="text-to-binary-output"
    />
    <div mt-2 flex justify-center>
      <c-button :disabled="!binaryFromText" @click="copyBinary()">
        {{ translate('tools.text-to-binary.copy-binary-button-text') }}
      </c-button>
    </div>
  </c-card>

  <c-card class="mw-600" :title="translate('tools.text-to-binary.title-binary-to-text')">
    <c-input-text
      v-model:value="inputBinary"
      multiline
      :placeholder="translate('tools.text-to-binary.placeholder-binary')"
      :label="translate('tools.text-to-binary.label-enter-binary')"
      autosize
      raw-text
      :validation-rules="inputBinaryValidationRules"
      test-id="binary-to-text-input"
    />
    <c-input-text
      v-model:value="textFromBinary"
      :label="translate('tools.text-to-binary.label-text-from-binary')"
      multiline
      raw-text
      readonly
      mt-2
      :placeholder="translate('tools.text-to-binary.placeholder-text')"
      test-id="binary-to-text-output"
    />
    <div mt-2 flex justify-center>
      <c-button :disabled="!textFromBinary" @click="copyText()">
        {{ translate('tools.text-to-binary.copy-text-button-text') }}
      </c-button>
    </div>
  </c-card>
</template>
