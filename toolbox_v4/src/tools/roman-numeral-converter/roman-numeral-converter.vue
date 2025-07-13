<script setup lang="ts">
import {
  MAX_ARABIC_TO_ROMAN,
  MIN_ARABIC_TO_ROMAN,
  arabicToRoman,
  isValidRomanNumber,
  romanToArabic,
} from './roman-numeral-converter.service';
import { useCopy } from '@/composable/copy';
import { useValidation } from '@/composable/validation';
import { translate } from '@/plugins/i18n.plugin';

const inputNumeral = ref(42);
const outputRoman = computed(() => arabicToRoman(inputNumeral.value));

const { attrs: validationNumeral } = useValidation({
  source: inputNumeral,
  rules: [
    {
      validator: value => value >= MIN_ARABIC_TO_ROMAN && value <= MAX_ARABIC_TO_ROMAN,
      message: translate('tools.roman-numeral-converter.we-can-only-convert-numbers-between', [MIN_ARABIC_TO_ROMAN.toLocaleString(), MAX_ARABIC_TO_ROMAN.toLocaleString()]),
    },
  ],
});

const inputRoman = ref('XLII');
const outputNumeral = computed(() => romanToArabic(inputRoman.value));

const validationRoman = useValidation({
  source: inputRoman,
  rules: [
    {
      validator: value => isValidRomanNumber(value),
      message: translate('tools.roman-numeral-converter.the-input-you-entered-is-not-a-valid-roman-number'),
    },
  ],
});

const { copy: copyRoman } = useCopy({
  source: outputRoman,
  text: translate('tools.roman-numeral-converter.copy-roman-number'),
});
const { copy: copyArabic } = useCopy({
  source: () => String(outputNumeral),
  text: translate('tools.roman-numeral-converter.copy-arabic-number'),
});
</script>

<template>
  <div class="mw-600">
    <c-card :title="translate('tools.roman-numeral-converter.arabic-to-roman')">
      <div flex items-center justify-between>
        <n-form-item v-bind="validationNumeral as any">
          <n-input-number
            v-model:value="inputNumeral"
            :min="1"
            style="width: 200px"
            :show-button="false"
            :placeholder="translate('tools.roman-numeral-converter.input-arabic-number')"
          />
        </n-form-item>
        <div class="result">
          {{ outputRoman }}
        </div>
        <c-button autofocus :disabled="validationNumeral.validationStatus === 'error'" @click="copyRoman()">
          {{ translate('tools.roman-numeral-converter.copy-roman-number') }}
        </c-button>
      </div>
    </c-card>
    <c-card :title="translate('tools.roman-numeral-converter.roman-to-arabic')" mt-5>
      <div flex items-center justify-between>
        <c-input-text
          v-model:value="inputRoman"
          style="width: 200px"
          :validation="validationRoman"
          :placeholder="translate('tools.roman-numeral-converter.input-arabic-number')"
        />

        <div class="result">
          {{ outputNumeral }}
        </div>
        <c-button :disabled="!validationRoman.isValid" @click="copyArabic()">
          {{ translate('tools.roman-numeral-converter.copy-arabic-number') }}
        </c-button>
      </div>
    </c-card>
  </div>
</template>

<style lang="less" scoped>
.result {
  font-size: 22px;
}
</style>
