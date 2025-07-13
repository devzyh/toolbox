<script setup lang="ts">
import type { lib } from 'crypto-js';
import { MD5, RIPEMD160, SHA1, SHA224, SHA256, SHA3, SHA384, SHA512, enc } from 'crypto-js';

import InputCopyable from '../../components/InputCopyable.vue';
import { convertHexToBin } from './hash-text.service';
import { useQueryParam } from '@/composable/queryParams';
import { translate } from '@/plugins/i18n.plugin';

const MD5_16 = (value: string) => MD5(value).toString(enc.Hex).slice(8, 24);

const algos = {
  MD5,
  MD5_16,
  SHA1,
  SHA256,
  SHA224,
  SHA512,
  SHA384,
  SHA3,
  RIPEMD160,
} as const;

type AlgoNames = keyof typeof algos;
type Encoding = keyof typeof enc | 'Bin';
const algoNames = Object.keys(algos) as AlgoNames[];
const encoding = useQueryParam<Encoding>({ defaultValue: 'Hex', name: 'encoding' });
const clearText = ref('');
const isUppercase = ref(false);

function formatWithEncoding(words: lib.WordArray, encoding: Encoding) {
  let result = '';
  if (encoding === 'Bin') {
    result = convertHexToBin(words.toString(enc.Hex));
  }
  else {
    result = words.toString(enc[encoding]);
  }

  // 新增：根据 isUppercase 的值转换大小写
  return isUppercase.value ? result.toUpperCase() : result.toLowerCase();
}

const hashText = (algo: AlgoNames, value: string) => formatWithEncoding(algos[algo](value), encoding.value);
</script>

<template>
  <div class="mw-600">
    <c-card>
      <c-input-text
        v-model:value="clearText" multiline raw-text
        :placeholder="translate('tools.hash-text.placeholder')"
        rows="3" autosize autofocus
        :label="translate('tools.hash-text.label')"
      />

      <n-divider />

      <c-select
        v-model:value="encoding"
        mb-4
        :label="translate('tools.hash-text.select.label')"
        :options="[
          {
            label: translate('tools.hash-text.select.bin'),
            value: 'Bin',
          },
          {
            label: translate('tools.hash-text.select.hex'),
            value: 'Hex',
          },
          {
            label: translate('tools.hash-text.select.base64'),
            value: 'Base64',
          },
          {
            label: translate('tools.hash-text.select.base64-url'),
            value: 'Base64url',
          },
        ]"
      />

      <c-button mb-4 @click="isUppercase = !isUppercase">
        {{
          isUppercase ? translate('tools.hash-text.select.lowercase') : translate('tools.hash-text.select.uppercase')
        }}
      </c-button>

      <div v-for="algo in algoNames" :key="algo" style="margin: 5px 0">
        <n-input-group>
          <n-input-group-label style="flex: 0 0 120px">
            {{ algo }}
          </n-input-group-label>
          <InputCopyable :value="hashText(algo, clearText)" readonly />
        </n-input-group>
      </div>
    </c-card>
  </div>
</template>

<style lang="less" scoped>

</style>
