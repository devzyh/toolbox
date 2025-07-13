<script setup lang="ts">
import type { Colord } from 'colord';
import { colord, extend } from 'colord';
import _ from 'lodash';
import cmykPlugin from 'colord/plugins/cmyk';
import hwbPlugin from 'colord/plugins/hwb';
import namesPlugin from 'colord/plugins/names';
import lchPlugin from 'colord/plugins/lch';
import { buildColorFormat } from './color-converter.models';
import { translate } from '@/plugins/i18n.plugin';

extend([cmykPlugin, hwbPlugin, namesPlugin, lchPlugin]);

const formats = {
  picker: buildColorFormat({
    label: translate('tools.color-converter.picker'),
    format: (v: Colord) => v.toHex(),
    type: 'color-picker',
  }),
  hex: buildColorFormat({
    label: translate('tools.color-converter.hex'),
    format: (v: Colord) => v.toHex(),
    placeholder: translate('tools.color-converter.placeholder-hex'),
  }),
  rgb: buildColorFormat({
    label: translate('tools.color-converter.rgb'),
    format: (v: Colord) => v.toRgbString(),
    placeholder: translate('tools.color-converter.placeholder-rgb'),
  }),
  hsl: buildColorFormat({
    label: translate('tools.color-converter.hsl'),
    format: (v: Colord) => v.toHslString(),
    placeholder: translate('tools.color-converter.placeholder-hsl'),
  }),
  hwb: buildColorFormat({
    label: translate('tools.color-converter.hwb'),
    format: (v: Colord) => v.toHwbString(),
    placeholder: translate('tools.color-converter.placeholder-hwb'),
  }),
  lch: buildColorFormat({
    label: translate('tools.color-converter.lch'),
    format: (v: Colord) => v.toLchString(),
    placeholder: translate('tools.color-converter.placeholder-lch'),
  }),
  cmyk: buildColorFormat({
    label: translate('tools.color-converter.cmyk'),
    format: (v: Colord) => v.toCmykString(),
    placeholder: translate('tools.color-converter.placeholder-cmyk'),
  }),
  name: buildColorFormat({
    label: translate('tools.color-converter.name'),
    format: (v: Colord) => v.toName({ closest: true }) ?? 'Unknown',
    placeholder: translate('tools.color-converter.placeholder-name'),
  }),
};

updateColorValue(colord('#1ea54c'));

function updateColorValue(value: Colord | undefined, omitLabel?: string) {
  if (value === undefined) {
    return;
  }

  if (!value.isValid()) {
    return;
  }

  _.forEach(formats, ({ value: valueRef, format }, key) => {
    if (key !== omitLabel) {
      valueRef.value = format(value);
    }
  });
}
</script>

<template>
  <c-card class="mw-600">
    <template v-for="({ label, parse, placeholder, validation, type }, key) in formats" :key="key">
      <input-copyable
        v-if="type === 'text'"
        v-model:value="formats[key].value.value"
        :test-id="`input-${key}`"
        :label="`${label}:`"
        label-position="left"
        label-width="100px"
        label-align="right"
        :placeholder="placeholder"
        :validation="validation"
        raw-text
        clearable
        mt-2
        @update:value="(v:string) => updateColorValue(parse(v), key)"
      />

      <n-form-item v-else-if="type === 'color-picker'" :label="`${label}:`" label-width="100" label-placement="left" :show-feedback="false">
        <n-color-picker
          v-model:value="formats[key].value.value"
          placement="bottom-end"
          @update:value="(v:string) => updateColorValue(parse(v), key)"
        />
      </n-form-item>
    </template>
  </c-card>
</template>
