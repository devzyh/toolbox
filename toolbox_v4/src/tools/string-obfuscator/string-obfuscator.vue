<script setup lang="ts">
import { useObfuscateString } from './string-obfuscator.model';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const str = ref('Lorem ipsum dolor sit amet');
const keepFirst = ref(4);
const keepLast = ref(4);
const keepSpace = ref(true);

const obfuscatedString = useObfuscateString(str, { keepFirst, keepLast, keepSpace });
const { copy } = useCopy({ source: obfuscatedString, text: translate('tools.string-obfuscator.copy-obfuscated-string') });
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="str"
      raw-text
      :placeholder="translate('tools.string-obfuscator.enter-string-to-obfuscate')"
      :label="translate('tools.string-obfuscator.string-to-obfuscate')"
      clearable
      multiline
    />

    <div mt-4 flex gap-10px>
      <div>
        <div>{{ translate('tools.string-obfuscator.keep-first') }}:</div>
        <n-input-number v-model:value="keepFirst" min="0" />
      </div>

      <div>
        <div>{{ translate('tools.string-obfuscator.keep-last') }}:</div>
        <n-input-number v-model:value="keepLast" min="0" />
      </div>

      <div>
        <div mb-5px>
          {{ translate('tools.string-obfuscator.keep-spaces') }}:
        </div>
        <n-switch v-model:value="keepSpace" />
      </div>
    </div>

    <c-card v-if="obfuscatedString" mt-60px max-w-600px flex items-center gap-5px font-mono>
      <div break-anywhere text-wrap>
        {{ obfuscatedString }}
      </div>

      <c-button @click="copy()">
        <icon-mdi:content-copy />
        <span class="sr-only">{{ translate('tools.string-obfuscator.copy-obfuscated-string') }}</span>
      </c-button>
    </c-card>
  </div>
</template>

<style scoped>
.sr-only {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  border: 0;
}
</style>
