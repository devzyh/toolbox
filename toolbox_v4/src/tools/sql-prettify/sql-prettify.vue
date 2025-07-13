<script setup lang="ts">
import { type FormatOptionsWithLanguage, format as formatSQL } from 'sql-formatter';
import TextareaCopyable from '@/components/TextareaCopyable.vue';
import { useStyleStore } from '@/stores/style.store';
import { translate } from '@/plugins/i18n.plugin';

const inputElement = ref<HTMLElement>();
const styleStore = useStyleStore();
const config = reactive<FormatOptionsWithLanguage>({
  keywordCase: 'upper',
  useTabs: false,
  language: 'sql',
  indentStyle: 'standard',
  tabulateAlias: true,
});

const rawSQL = ref('select field1,field2,field3 from my_table where my_condition;');
const prettySQL = computed(() => formatSQL(rawSQL.value, config));
</script>

<template>
  <div style="flex: 0 0 100%">
    <div class="mw-600" :class="{ 'flex-col': styleStore.isSmallScreen }" mx-auto mb-5 flex gap-2>
      <c-select
        v-model:value="config.language"
        flex-1
        :label="translate('tools.sql-prettify.dialect')"
        :options="[
          { label: 'GCP BigQuery', value: 'bigquery' },
          { label: 'IBM DB2', value: 'db2' },
          { label: 'Apache Hive', value: 'hive' },
          { label: 'MariaDB', value: 'mariadb' },
          { label: 'MySQL', value: 'mysql' },
          { label: 'Couchbase N1QL', value: 'n1ql' },
          { label: 'Oracle PL/SQL', value: 'plsql' },
          { label: 'PostgreSQL', value: 'postgresql' },
          { label: 'Amazon Redshift', value: 'redshift' },
          { label: 'Spark', value: 'spark' },
          { label: 'Standard SQL', value: 'sql' },
          { label: 'sqlite', value: 'sqlite' },
          { label: 'SQL Server Transact-SQL', value: 'tsql' },
        ]"
      />
      <c-select
        v-model:value="config.keywordCase"
        flex-1
        :label="translate('tools.sql-prettify.keyword-case')"
        :options="[
          { label: 'UPPERCASE', value: 'upper' },
          { label: 'lowercase', value: 'lower' },
          { label: 'Preserve', value: 'preserve' },
        ]"
      />
      <c-select
        v-model:value="config.indentStyle"
        flex-1
        :label="translate('tools.sql-prettify.indent-style')"
        :options="[
          { label: 'Standard', value: 'standard' },
          { label: 'Tabular left', value: 'tabularLeft' },
          { label: 'Tabular right', value: 'tabularRight' },
        ]"
      />
    </div>
  </div>

  <n-form-item class="mw-600" :label="translate('tools.sql-prettify.your-sql-query')">
    <c-input-text
      ref="inputElement"
      v-model:value="rawSQL"
      :placeholder="translate('tools.sql-prettify.put-your-sql-query-here')"
      rows="20"
      multiline
      autocomplete="off"
      autocorrect="off"
      autocapitalize="off"
      spellcheck="false"
      monospace
    />
  </n-form-item>
  <n-form-item class="mw-600" :label="translate('tools.sql-prettify.prettify-version-of-your-query')">
    <TextareaCopyable :value="prettySQL" language="sql" :follow-height-of="inputElement" />
  </n-form-item>
</template>

<style lang="less" scoped>
.result-card {
  position: relative;
  .copy-button {
    position: absolute;
    top: 10px;
    right: 10px;
  }
}
</style>
