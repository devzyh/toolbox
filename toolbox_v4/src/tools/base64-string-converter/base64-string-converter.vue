<script setup lang="ts">
import { useCopy } from '@/composable/copy';
import { base64ToText, isValidBase64, textToBase64 } from '@/utils/base64';
import { withDefaultOnError } from '@/utils/defaults';
import { translate } from '@/plugins/i18n.plugin';

const encodeUrlSafe = useStorage('dz-tools:base64-string-converter:encode-url-safe', false);
const decodeUrlSafe = useStorage('dz-tools:base64-string-converter:decode-url-safe', false);

const textInput = ref('');
const base64Output = computed(() => textToBase64(textInput.value, { makeUrlSafe: encodeUrlSafe.value }));
const { copy: copyTextBase64 } = useCopy({ source: base64Output, text: translate('tools.common.copied') });

const base64Input = ref('');
const textOutput = computed(() =>
  withDefaultOnError(() => base64ToText(base64Input.value.trim(), { makeUrlSafe: decodeUrlSafe.value }), ''),
);
const { copy: copyText } = useCopy({ source: textOutput, text: translate('tools.common.copied') });
const b64ValidationRules = [
  {
    message: translate('tools.base64-string-converter.invalid-base64'),
    validator: (value: string) => isValidBase64(value.trim(), { makeUrlSafe: decodeUrlSafe.value }),
  },
];
const b64ValidationWatch = [decodeUrlSafe];
</script>

<template>
  <c-card :title="translate('tools.base64-string-converter.to-base64-title')" class="mw-600">
    <n-form-item :label="translate('tools.base64-string-converter.url-safe')" label-placement="left">
      <n-switch v-model:value="encodeUrlSafe" />
    </n-form-item>
    <c-input-text
      v-model:value="textInput"
      multiline
      :placeholder="translate('tools.common.input-placeholder')"
      rows="5"
      :label="translate('tools.common.input-label')"
      raw-text
      mb-5
    />

    <c-input-text
      :label="translate('tools.common.output-label')"
      :value="base64Output"
      multiline
      readonly
      :placeholder="translate('tools.common.output-placeholder')"
      rows="5"
      mb-5
    />

    <div flex justify-center>
      <c-button @click="copyTextBase64()">
        {{ translate('tools.common.copy') }}
      </c-button>
    </div>
  </c-card>

  <c-card :title="translate('tools.base64-string-converter.to-string-title')" class="mw-600">
    <n-form-item :label="translate('tools.base64-string-converter.url-safe')" label-placement="left">
      <n-switch v-model:value="decodeUrlSafe" />
    </n-form-item>
    <c-input-text
      v-model:value="base64Input"
      multiline
      :placeholder="translate('tools.common.input-placeholder')"
      rows="5"
      :validation-rules="b64ValidationRules"
      :validation-watch="b64ValidationWatch"
      :label="translate('tools.common.input-label')"
      mb-5
    />

    <c-input-text
      v-model:value="textOutput"
      :label="translate('tools.common.output-label')"
      :placeholder="translate('tools.common.output-placeholder')"
      multiline
      rows="5"
      readonly
      mb-5
    />

    <div flex justify-center>
      <c-button @click="copyText()">
        {{ translate('tools.common.copy') }}
      </c-button>
    </div>
  </c-card>
</template>
