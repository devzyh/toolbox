<script setup lang="ts">
import _ from 'lodash';
import {
  convertCelsiusToKelvin,
  convertDelisleToKelvin,
  convertFahrenheitToKelvin,
  convertKelvinToCelsius,
  convertKelvinToDelisle,
  convertKelvinToFahrenheit,
  convertKelvinToNewton,
  convertKelvinToRankine,
  convertKelvinToReaumur,
  convertKelvinToRomer,
  convertNewtonToKelvin,
  convertRankineToKelvin,
  convertReaumurToKelvin,
  convertRomerToKelvin,
} from './temperature-converter.models';
import { translate } from '@/plugins/i18n.plugin';

type TemperatureScale = 'kelvin' | 'celsius' | 'fahrenheit' | 'rankine' | 'delisle' | 'newton' | 'reaumur' | 'romer';

const units = reactive<
    Record<
        string | TemperatureScale,
        { title: string; unit: string; ref: number; toKelvin: (v: number) => number; fromKelvin: (v: number) => number }
    >
          >({
            kelvin: {
              title: translate('tools.temperature-converter.kelvin'),
              unit: 'K',
              ref: 0,
              toKelvin: _.identity,
              fromKelvin: _.identity,
            },
            celsius: {
              title: translate('tools.temperature-converter.celsius'),
              unit: '°C',
              ref: 0,
              toKelvin: convertCelsiusToKelvin,
              fromKelvin: convertKelvinToCelsius,
            },
            fahrenheit: {
              title: translate('tools.temperature-converter.fahrenheit'),
              unit: '°F',
              ref: 0,
              toKelvin: convertFahrenheitToKelvin,
              fromKelvin: convertKelvinToFahrenheit,
            },
            rankine: {
              title: translate('tools.temperature-converter.rankine'),
              unit: '°R',
              ref: 0,
              toKelvin: convertRankineToKelvin,
              fromKelvin: convertKelvinToRankine,
            },
            delisle: {
              title: translate('tools.temperature-converter.delisle'),
              unit: '°De',
              ref: 0,
              toKelvin: convertDelisleToKelvin,
              fromKelvin: convertKelvinToDelisle,
            },
            newton: {
              title: translate('tools.temperature-converter.newton'),
              unit: '°N',
              ref: 0,
              toKelvin: convertNewtonToKelvin,
              fromKelvin: convertKelvinToNewton,
            },
            reaumur: {
              title: translate('tools.temperature-converter.reaumur'),
              unit: '°Ré',
              ref: 0,
              toKelvin: convertReaumurToKelvin,
              fromKelvin: convertKelvinToReaumur,
            },
            romer: {
              title: translate('tools.temperature-converter.romer'),
              unit: '°Rø',
              ref: 0,
              toKelvin: convertRomerToKelvin,
              fromKelvin: convertKelvinToRomer,
            },
          });

function update(key: TemperatureScale) {
  const { ref: value, toKelvin } = units[key];

  const kelvins = toKelvin(value) ?? 0;

  _.chain(units)
    .omit(key)
    .forEach(({ fromKelvin }, index) => {
      units[index].ref = Math.floor((fromKelvin(kelvins) ?? 0) * 100) / 100;
    })
    .value();
}

update('kelvin');
</script>

<template>
  <div class="mw-600">
    <n-input-group v-for="[key, { title, unit }] in Object.entries(units)" :key="key" mb-3 w-full>
      <n-input-group-label style="width: 100px">
        {{ title }}
      </n-input-group-label>

      <n-input-number
        v-model:value="units[key].ref"
        style="flex: 1"
        @update:value="() => update(key as TemperatureScale)"
      />

      <n-input-group-label style="width: 50px">
        {{ unit }}
      </n-input-group-label>
    </n-input-group>
  </div>
</template>
