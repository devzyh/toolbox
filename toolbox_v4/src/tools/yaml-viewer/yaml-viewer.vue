<script setup lang="ts">
import yaml from 'yaml';
import { useStorage } from '@vueuse/core';
import { formatYaml } from './yaml-models';
import { withDefaultOnError } from '@/utils/defaults';
import { useValidation } from '@/composable/validation';
import TextareaCopyable from '@/components/TextareaCopyable.vue';
import { translate } from '@/plugins/i18n.plugin';

const inputElement = ref<HTMLElement>();

const rawYaml = useStorage('dz-tools:yaml-prettify:raw-yaml', '');
const indentSize = useStorage('dz-tools:yaml-prettify:indent-size', 2);
const sortKeys = useStorage('dz-tools:yaml-prettify:sort-keys', false);

const cleanYaml = computed(() => withDefaultOnError(() => formatYaml({ rawYaml, indentSize, sortKeys }), ''));

const rawYamlValidation = useValidation({
  source: rawYaml,
  rules: [
    {
      validator: v => v === '' || !!yaml.parse(v),
      message: translate('tools.yaml-viewer.provided-yaml-is-not-valid'),
    },
  ],
});
</script>

<template>
  <div style="flex: 0 0 100%">
    <div style="margin: 0 auto; max-width: 600px" flex justify-center gap-3>
      <n-form-item :label="translate('tools.yaml-viewer.sort-keys')" label-placement="left" label-width="100">
        <n-switch v-model:value="sortKeys" />
      </n-form-item>
      <n-form-item :label="translate('tools.yaml-viewer.indent-size')" label-placement="left" label-width="100" :show-feedback="false">
        <n-input-number v-model:value="indentSize" min="1" max="10" style="width: 100px" />
      </n-form-item>
    </div>
  </div>

  <n-form-item
    class="mw-600"
    :label="translate('tools.yaml-viewer.your-raw-yaml')"
    :feedback="rawYamlValidation.message"
    :validation-status="rawYamlValidation.status"
  >
    <c-input-text
      ref="inputElement"
      v-model:value="rawYaml"
      :placeholder="translate('tools.yaml-viewer.paste-your-raw-yaml-here')"
      rows="20"
      multiline
      autocomplete="off"
      autocorrect="off"
      autocapitalize="off"
      spellcheck="false"
      monospace
    />
  </n-form-item>
  <n-form-item
    class="mw-600"
    :label="translate('tools.yaml-viewer.prettified-version-of-your-yaml')"
  >
    <TextareaCopyable :value="cleanYaml" language="yaml" :follow-height-of="inputElement" />
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
