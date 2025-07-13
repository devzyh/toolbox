<script setup lang="ts">
import { computed, ref } from 'vue';
import { useCopy } from '@/composable/copy';
import { useValidation } from '@/composable/validation';
import { isNotThrowing } from '@/utils/boolean';
import { withDefaultOnError } from '@/utils/defaults';
import { translate } from '@/plugins/i18n.plugin';

const encodeInput = ref('Hello world :)');
const encodeOutput = computed(() => withDefaultOnError(() => encodeURIComponent(encodeInput.value), ''));

const encodedValidation = useValidation({
  source: encodeInput,
  rules: [
    {
      validator: value => isNotThrowing(() => encodeURIComponent(value)),
      message: translate('tools.url-encoder.validation-message'),
    },
  ],
});

const { copy: copyEncoded } = useCopy({ source: encodeOutput, text: translate('tools.url-encoder.copy-button-text') });

const decodeInput = ref('Hello%20world%20%3A)');
const decodeOutput = computed(() => withDefaultOnError(() => decodeURIComponent(decodeInput.value), ''));

const decodeValidation = useValidation({
  source: decodeInput,
  rules: [
    {
      validator: value => isNotThrowing(() => decodeURIComponent(value)),
      message: translate('tools.url-encoder.validation-message'),
    },
  ],
});

const { copy: copyDecoded } = useCopy({ source: decodeOutput, text: translate('tools.url-encoder.copy-button-text') });
</script>

<template>
  <c-card class="mw-600" :title="translate('tools.url-encoder.title-encode')">
    <c-input-text
      v-model:value="encodeInput"
      :label="translate('tools.url-encoder.input-label-encode')"
      :validation="encodedValidation"
      multiline
      autosize
      placeholder="The string to encode"
      rows="2"
      mb-3
    />

    <c-input-text
      :label="translate('tools.url-encoder.output-label-encode')"
      :value="encodeOutput"
      multiline
      autosize
      readonly
      placeholder="Your string encoded"
      rows="2"
      mb-3
    />

    <div flex justify-center>
      <c-button @click="copyEncoded()">
        {{ translate('tools.url-encoder.copy-button-text') }}
      </c-button>
    </div>
  </c-card>

  <c-card class="mw-600" :title="translate('tools.url-encoder.title-decode')">
    <c-input-text
      v-model:value="decodeInput"
      :label="translate('tools.url-encoder.input-label-decode')"
      :validation="decodeValidation"
      multiline
      autosize
      placeholder="The string to decode"
      rows="2"
      mb-3
    />

    <c-input-text
      :label="translate('tools.url-encoder.output-label-decode')"
      :value="decodeOutput"
      multiline
      autosize
      readonly
      placeholder="Your string decoded"
      rows="2"
      mb-3
    />

    <div flex justify-center>
      <c-button @click="copyDecoded()">
        {{ translate('tools.url-encoder.copy-button-text') }}
      </c-button>
    </div>
  </c-card>
</template>
