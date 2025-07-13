<script setup lang="ts">
import { isValidXML, minifyXml, prettifyXml } from './xml.service';
import { translate } from '@/plugins/i18n.plugin';

const message = useMessage();

const indentSize = useStorage('dz-tools:xml:indent-size', 2);
const inputValue = useStorage('dz-tools:xml', '');

function prettify() {
  if (!isValidXML(inputValue.value)) {
    message.error(translate('tools.xml.error-message'));
    return;
  }

  inputValue.value = prettifyXml(inputValue.value, indentSize.value);
}

function minify() {
  if (!isValidXML(inputValue.value)) {
    message.error(translate('tools.xml.error-message'));
    return;
  }

  inputValue.value = minifyXml(inputValue.value);
}
</script>

<template>
  <div class="important:flex-full important:flex-shrink-0 important:flex-grow-0">
    <div class="flex flex-wrap justify-center gap-4">
      <n-form-item
        :label="translate('tools.xml.indent-size')" label-placement="left" label-width="100"
        :show-feedback="false"
      >
        <n-input-number v-model:value="indentSize" min="0" max="10" w-100px @update:value="prettify" />
      </n-form-item>

      <c-button type="primary" @click="prettify">
        {{ translate('tools.xml.prettify-button') }}
      </c-button>

      <c-button type="warning" @click="minify">
        {{ translate('tools.xml.minify-button') }}
      </c-button>

      <c-button type="error" @click="inputValue = ''">
        {{ translate('tools.xml.clear-button') }}
      </c-button>
    </div>
  </div>

  <c-xml-editor v-model="inputValue" class="editor" />
</template>

<style lang="less" scoped>
.editor {
  width: 100%;
  height: 77.3vh;
  border: 1px solid #2e9968;
}
</style>
