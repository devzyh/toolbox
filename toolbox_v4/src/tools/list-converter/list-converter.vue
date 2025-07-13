<script setup lang="ts">
import { useStorage } from '@vueuse/core';
import { convert } from './list-converter.models';
import type { ConvertOptions } from './list-converter.types';
import { translate } from '@/plugins/i18n.plugin';

const sortOrderOptions = [
  {
    label: translate('tools.list-converter.sort-ascending'),
    value: 'asc',
    disabled: false,
  },
  {
    label: translate('tools.list-converter.sort-descending'),
    value: 'desc',
    disabled: false,
  },
];

const conversionConfig = useStorage<ConvertOptions>('list-converter:conversionConfig', {
  lowerCase: false,
  trimItems: true,
  removeDuplicates: true,
  keepLineBreaks: false,
  itemPrefix: '',
  itemSuffix: '',
  listPrefix: '',
  listSuffix: '',
  reverseList: false,
  sortList: null,
  separator: ', ',
});

function transformer(value: string) {
  return convert(value, conversionConfig.value);
}
</script>

<template>
  <div style="flex: 0 0 100%">
    <div style="margin: 0 auto;">
      <c-card>
        <div flex>
          <div>
            <n-form-item :label="translate('tools.list-converter.trim-list-items')" label-placement="left" label-width="150" :show-feedback="false" mb-2>
              <n-switch v-model:value="conversionConfig.trimItems" />
            </n-form-item>
            <n-form-item :label="translate('tools.list-converter.remove-duplicates')" label-placement="left" label-width="150" :show-feedback="false" mb-2>
              <n-switch v-model:value="conversionConfig.removeDuplicates" data-test-id="removeDuplicates" />
            </n-form-item>
            <n-form-item :label="translate('tools.list-converter.convert-to-lowercase')" label-placement="left" label-width="150" :show-feedback="false" mb-2>
              <n-switch v-model:value="conversionConfig.lowerCase" />
            </n-form-item>
            <n-form-item :label="translate('tools.list-converter.keep-line-breaks')" label-placement="left" label-width="150" :show-feedback="false" mb-2>
              <n-switch v-model:value="conversionConfig.keepLineBreaks" />
            </n-form-item>
          </div>
          <div flex-1>
            <c-select
              v-model:value="conversionConfig.sortList"
              :label="translate('tools.list-converter.sort-list')"
              label-position="left"
              label-width="120px"
              label-align="right"
              mb-2
              :options="sortOrderOptions"
              w-full
              :disabled="conversionConfig.reverseList"
              data-test-id="sortList"
              :placeholder="translate('tools.list-converter.sort-list')"
            />

            <c-input-text
              v-model:value="conversionConfig.separator"
              :label="translate('tools.list-converter.separator')"
              label-position="left"
              label-width="120px"
              label-align="right"
              mb-2
              :placeholder="translate('tools.list-converter.separator')"
            />

            <n-form-item :label="translate('tools.list-converter.wrap-item')" label-placement="left" label-width="120" :show-feedback="false" mb-2>
              <c-input-text
                v-model:value="conversionConfig.itemPrefix"
                :placeholder="translate('tools.list-converter.item-prefix')"
                test-id="itemPrefix"
              />
              <c-input-text
                v-model:value="conversionConfig.itemSuffix"
                :placeholder="translate('tools.list-converter.item-suffix')"
                test-id="itemSuffix"
              />
            </n-form-item>
            <n-form-item :label="translate('tools.list-converter.wrap-list')" label-placement="left" label-width="120" :show-feedback="false" mb-2>
              <c-input-text
                v-model:value="conversionConfig.listPrefix"
                :placeholder="translate('tools.list-converter.list-prefix')"
                test-id="listPrefix"
              />
              <c-input-text
                v-model:value="conversionConfig.listSuffix"
                :placeholder="translate('tools.list-converter.list-suffix')"
                test-id="listSuffix"
              />
            </n-form-item>
          </div>
        </div>
      </c-card>
    </div>
  </div>
  <format-transformer
    :input-label="translate('tools.list-converter.your-input-data')"
    :input-placeholder="translate('tools.list-converter.paste-your-input-data-here')"
    :output-label="translate('tools.list-converter.your-transformed-data')"
    :transformer="transformer"
  />
</template>
