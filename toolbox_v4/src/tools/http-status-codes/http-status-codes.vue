<script setup lang="ts">
import { codesByCategories } from './http-status-codes.constants';
import { useFuzzySearch } from '@/composable/fuzzySearch';
import { translate } from '@/plugins/i18n.plugin';

const search = ref('');

const { searchResult } = useFuzzySearch({
  search,
  data: codesByCategories.flatMap(({ codes, category }) => codes.map(code => ({ ...code, category }))),
  options: {
    keys: [{ name: 'code', weight: 3 }, { name: 'name', weight: 2 }, 'description', 'category'],
  },
});

const codesByCategoryFiltered = computed(() => {
  if (!search.value) {
    return codesByCategories;
  }

  return [{ category: translate('tools.http-status-codes.search-results'), codes: searchResult.value }];
});
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="search"
      :placeholder="translate('tools.http-status-codes.search-http-status')"
      autofocus
      raw-text
      mb-10
    />

    <div v-for="{ codes, category } of codesByCategoryFiltered" :key="category" mb-8>
      <div mb-2 text-xl>
        {{ category }}
      </div>

      <c-card v-for="{ code, description, name, type } of codes" :key="code" mb-2>
        <div text-lg font-bold>
          {{ code }} {{ name }}
        </div>
        <div op-70>
          {{ description }}
          <template v-if="type !== 'HTTP'">
            {{ translate('tools.http-status-codes.for') }} {{ type }}.
          </template>
        </div>
      </c-card>
    </div>
  </div>
</template>
