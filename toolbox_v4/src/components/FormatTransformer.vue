<script setup lang="ts">
import _ from 'lodash';
import type { UseValidationRule } from '@/composable/validation';
import CInputText from '@/ui/c-input-text/c-input-text.vue';
import { translate } from '@/plugins/i18n.plugin';

const props = withDefaults(
  defineProps<{
    transformer?: (v: string) => string
    inputValidationRules?: UseValidationRule<string>[]
    inputLabel?: string
    inputPlaceholder?: string
    inputDefault?: string
    outputLabel?: string
    outputLanguage?: string
  }>(),
  {
    transformer: _.identity,
    inputValidationRules: () => [],
    inputLabel: translate('ui.format-transformer.input-label'),
    inputDefault: '',
    inputPlaceholder: translate('ui.format-transformer.input-placeholder'),
    outputLabel: translate('ui.format-transformer.output-label'),
    outputLanguage: '',
  },
);

const { transformer, inputValidationRules, inputLabel, outputLabel, outputLanguage, inputPlaceholder, inputDefault }
    = toRefs(props);

const inputElement = ref<typeof CInputText>();

const input = ref(inputDefault.value);
const output = computed(() => transformer.value(input.value));
</script>

<template>
  <CInputText
    ref="inputElement"
    v-model:value="input"
    :placeholder="inputPlaceholder"
    :label="inputLabel"
    rows="20"
    autosize
    raw-text
    multiline
    test-id="input"
    :validation-rules="inputValidationRules"
    monospace
  />

  <div w-full overflow-auto>
    <div mb-5px>
      {{ outputLabel }}
    </div>
    <textarea-copyable :value="output" :language="outputLanguage" :follow-height-of="inputElement?.inputWrapperRef" />
  </div>
</template>
