<script setup lang="ts">
import { translate } from '@/plugins/i18n.plugin';
import { convert } from '@/tools/xslt/xslt.service';
import type CInputText from '@/ui/c-input-text/c-input-text.vue';

const xml = useStorage('dz-tools:xslt:xml', '');
const xslt = useStorage('dz-tools:xslt:xslt', '');

const inputElement = ref<typeof CInputText>();
const result = computed(() => convert(xml.value, xslt.value));
</script>

<template>
  <div class="w-full">
    <label class="label"> {{ translate('tools.xslt.xml-label') }} </label>
    <c-xml-editor v-model="xml" class="xml-editor" />
    <br>

    <label class="label"> {{ translate('tools.xslt.xslt-label') }} </label>
    <c-xml-editor v-model="xslt" class="xslt-editor" />
    <br>

    <div overflow-auto>
      <div mb-5px>
        {{ translate('tools.xslt.result-label') }}
      </div>
      <textarea-copyable :value="result" :follow-height-of="inputElement?.inputWrapperRef" />
    </div>
  </div>
</template>

<style lang="less" scoped>
.xml-editor {
  width: 100%;
  height: 20vh;
}

.xslt-editor {
  width: 100%;
  height: 40vh;
}
</style>
