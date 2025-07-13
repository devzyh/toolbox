<script setup lang="ts">
import type { lib } from 'crypto-js';
import {
  HmacMD5,
  HmacRIPEMD160,
  HmacSHA1,
  HmacSHA224,
  HmacSHA256,
  HmacSHA3,
  HmacSHA384,
  HmacSHA512,
  enc,
} from 'crypto-js';

import { convertHexToBin } from '../hash-text/hash-text.service';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const algos = {
  MD5: HmacMD5,
  RIPEMD160: HmacRIPEMD160,
  SHA1: HmacSHA1,
  SHA3: HmacSHA3,
  SHA224: HmacSHA224,
  SHA256: HmacSHA256,
  SHA384: HmacSHA384,
  SHA512: HmacSHA512,
} as const;

type Encoding = keyof typeof enc | 'Bin';

function formatWithEncoding(words: lib.WordArray, encoding: Encoding) {
  if (encoding === 'Bin') {
    return convertHexToBin(words.toString(enc.Hex));
  }
  return words.toString(enc[encoding]);
}

const plainText = ref('');
const secret = ref('');
const hashFunction = ref<keyof typeof algos>('SHA256');
const encoding = ref<Encoding>('Hex');
const hmac = computed(() =>
  formatWithEncoding(algos[hashFunction.value](plainText.value, secret.value), encoding.value),
);
const { copy } = useCopy({ source: hmac });
</script>

<template>
  <div class="mw-600 flex flex-col gap-4">
    <c-input-text
      v-model:value="plainText"
      multiline
      raw-text
      :placeholder="translate('tools.hmac-generator.placeholder-plain-text')"
      rows="3"
      autosize
      autofocus
      :label="translate('tools.hmac-generator.plain-text-to-compute-the-hash')"
    />
    <c-input-text
      v-model:value="secret"
      raw-text
      :placeholder="translate('tools.hmac-generator.placeholder-secret-key')"
      :label="translate('tools.hmac-generator.secret-key')"
      clearable
    />

    <div flex gap-2>
      <c-select
        v-model:value="hashFunction"
        :label="translate('tools.hmac-generator.hashing-function')"
        flex-1
        :placeholder="translate('tools.hmac-generator.placeholder-hashing-function')"
        :options="Object.keys(algos).map((label) => ({ label, value: label }))"
      />
      <c-select
        v-model:value="encoding"
        :label="translate('tools.hmac-generator.output-encoding')"
        flex-1
        :placeholder="translate('tools.hmac-generator.placeholder-output-encoding')"
        :options="[
          {
            label: translate('tools.hmac-generator.binary-base-2'),
            value: 'Bin',
          },
          {
            label: translate('tools.hmac-generator.hexadecimal-base-16'),
            value: 'Hex',
          },
          {
            label: translate('tools.hmac-generator.base64-base-64'),
            value: 'Base64',
          },
          {
            label: translate('tools.hmac-generator.base64-url-base-64-with-url-safe-chars'),
            value: 'Base64url',
          },
        ]"
      />
    </div>
    <input-copyable
      v-model:value="hmac"
      type="textarea"
      :placeholder="translate('tools.hmac-generator.the-result-of-the-hmac')"
      :label="translate('tools.hmac-generator.the-result-of-the-hmac')"
    />
    <div flex justify-center>
      <c-button @click="copy()">
        {{ translate('tools.hmac-generator.copy-hmac') }}
      </c-button>
    </div>
  </div>
</template>
