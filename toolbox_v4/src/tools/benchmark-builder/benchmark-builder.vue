<script setup lang="ts">
import { Plus, Trash } from '@vicons/tabler';
import { useStorage } from '@vueuse/core';
import _ from 'lodash';

import { arrayToMarkdownTable, computeAverage, computeVariance } from './benchmark-builder.models';
import DynamicValues from './dynamic-values.vue';
import { translate } from '@/plugins/i18n.plugin';
import { useCopy } from '@/composable/copy';

const suites = useStorage('dz-tools:benchmark-builder:suites', [
  { title: 'Suite 1', data: [5, 10] },
  { title: 'Suite 2', data: [8, 12] },
]);

const unit = useStorage('dz-tools:benchmark-builder:unit', '');

const round = (v: number) => Math.round(v * 1000) / 1000;

const results = computed(() => {
  return suites.value
    .map(({ data: dirtyData, title }) => {
      const data = dirtyData.filter(_.isNumber);

      return {
        title,
        size: data.length,
        mean: computeAverage({ data }),
        variance: computeVariance({ data }),
      };
    })
    .sort((a, b) => a.mean - b.mean)
    .map(({ mean, variance, size, title }, index, suites) => {
      const cleanUnit = unit.value.trim();
      const bestMean: number = suites[0].mean;
      const deltaWithBestMean = mean - bestMean;
      const ratioWithBestMean = bestMean === 0 ? '∞' : round(mean / bestMean);

      const comparisonValues: string
            = (index !== 0 && bestMean !== mean) ? ` (+${round(deltaWithBestMean)}${cleanUnit} ; x${ratioWithBestMean})` : '';

      return {
        position: index + 1,
        title,
        mean: `${round(mean)}${cleanUnit}${comparisonValues}`,
        variance: `${round(variance)}${cleanUnit}${cleanUnit ? '²' : ''}`,
        size,
      };
    });
});

const { copy } = useCopy({ createToast: false });

const header = {
  position: translate('tools.benchmark-builder.position'),
  title: translate('tools.benchmark-builder.suite'),
  size: translate('tools.benchmark-builder.samples'),
  mean: translate('tools.benchmark-builder.mean'),
  variance: translate('tools.benchmark-builder.variance'),
};

function copyAsMarkdown() {
  copy(arrayToMarkdownTable({ data: results.value, headerMap: header }));
}

function copyAsBulletList() {
  const bulletList = results.value
    .flatMap(({ title, ...sections }) => {
      return [
          ` - ${title}`,
          ...Object.entries(sections).map(
            ([key, value]) => `    - ${header[key as keyof typeof header] ?? key}: ${value}`,
          ),
      ];
    })
    .join('\n');

  copy(bulletList);
}
</script>

<template>
  <n-scrollbar style="flex: 1" x-scrollable>
    <div mb-5 flex flex-1 flex-nowrap justify-center gap-12px>
      <div v-for="(suite, index) of suites" :key="index">
        <c-card style="width: 294px">
          <c-input-text
            v-model:value="suite.title"
            label-position="left"
            :label="translate('tools.benchmark-builder.suite-name')"
            :placeholder="translate('tools.benchmark-builder.suite-name')"
            clearable
          />

          <n-divider />
          <n-form-item :label="translate('tools.benchmark-builder.suite-values')" :show-feedback="false">
            <DynamicValues v-model:values="suite.data" />
          </n-form-item>
        </c-card>

        <div flex justify-center>
          <c-button v-if="suites.length > 1" variant="text" @click="suites.splice(index, 1)">
            <n-icon :component="Trash" depth="3" mr-2 size="18" />
            {{ translate('tools.benchmark-builder.delete-suite') }}
          </c-button>
          <c-button
            variant="text"
            @click="suites.splice(index + 1, 0, { data: [0], title: `Suite ${suites.length + 1}` })"
          >
            <n-icon :component="Plus" depth="3" mr-2 size="18" />
            {{ translate('tools.benchmark-builder.add-suite') }}
          </c-button>
        </div>
      </div>
    </div>
  </n-scrollbar>

  <div style="flex: 0 0 100%">
    <div style="max-width: 600px; margin: 0 auto">
      <div mx-auto max-w-sm flex justify-center gap-3>
        <c-input-text
          v-model:value="unit" placeholder="eg: ms" :label="translate('tools.benchmark-builder.unit')"
          label-position="left" mb-4
        />

        <c-button
          @click="
            suites = [
              { title: 'Suite 1', data: [] },
              { title: 'Suite 2', data: [] },
            ]
          "
        >
          {{ translate('tools.benchmark-builder.reset-suites') }}
        </c-button>
      </div>

      <c-table :data="results" :headers="header" />

      <div mt-5 flex justify-center gap-3>
        <c-button @click="copyAsMarkdown()">
          {{ translate('tools.benchmark-builder.copy-table') }}
        </c-button>
        <c-button @click="copyAsBulletList()">
          {{ translate('tools.benchmark-builder.copy-list') }}
        </c-button>
      </div>
    </div>
  </div>
</template>
