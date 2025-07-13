<script setup lang="ts">
import { AES, RC4, Rabbit, TripleDES, enc } from 'crypto-js';
import { computedCatch } from '@/composable/computed/catchedComputed';
import { translate } from '@/plugins/i18n.plugin';

const algos = { AES, TripleDES, Rabbit, RC4 };

const cypherInput = ref('Lorem ipsum dolor sit amet');
const cypherAlgo = ref<keyof typeof algos>('AES');
const cypherSecret = ref('my secret key');
const cypherOutput = computed(() => algos[cypherAlgo.value].encrypt(cypherInput.value, cypherSecret.value).toString());

const decryptInput = ref('U2FsdGVkX1/EC3+6P5dbbkZ3e1kQ5o2yzuU0NHTjmrKnLBEwreV489Kr0DIB+uBs');
const decryptAlgo = ref<keyof typeof algos>('AES');
const decryptSecret = ref('my secret key');
const [decryptOutput, decryptError] = computedCatch(() => algos[decryptAlgo.value].decrypt(decryptInput.value, decryptSecret.value).toString(enc.Utf8), {
  defaultValue: '',
  defaultErrorMessage: translate('tools.encryption.error-while-decrypting'),
});
</script>

<template>
  <c-card class="mw-600" :title="translate('tools.encryption.encryption')">
    <div flex gap-3>
      <c-input-text
        v-model:value="cypherInput"
        :label="translate('tools.encryption.your-text')"
        rows="4"
        multiline raw-text monospace autosize flex-1
      />
      <div flex flex-1 flex-col gap-2>
        <c-input-text
          v-model:value="cypherSecret"
          :label="translate('tools.encryption.your-secret-key')"
          clearable raw-text
        />

        <c-select
          v-model:value="cypherAlgo"
          :label="translate('tools.encryption.encryption-algorithm')"
          :options="Object.keys(algos).map((label) => ({ label, value: label }))"
        />
      </div>
    </div>
    <c-input-text
      :label="translate('tools.encryption.your-text-encrypted')"
      :value="cypherOutput"
      rows="3"
      multiline monospace readonly autosize mt-5
    />
  </c-card>
  <c-card class="mw-600" :title="translate('tools.encryption.decryption')">
    <div flex gap-3>
      <c-input-text
        v-model:value="decryptInput"
        :label="translate('tools.encryption.your-encrypted-text')"
        rows="4"
        multiline raw-text monospace autosize flex-1
      />
      <div flex flex-1 flex-col gap-2>
        <c-input-text
          v-model:value="decryptSecret"
          :label="translate('tools.encryption.your-secret-key')"
          clearable raw-text
        />

        <c-select
          v-model:value="decryptAlgo"
          :label="translate('tools.encryption.encryption-algorithm')"
          :options="Object.keys(algos).map((label) => ({ label, value: label }))"
        />
      </div>
    </div>
    <c-alert v-if="decryptError" type="error" mt-12 :title="translate('tools.encryption.error-while-decrypting')">
      {{ decryptError }}
    </c-alert>
    <c-input-text
      v-else
      :label="translate('tools.encryption.your-decrypted-text')"
      :value="decryptOutput"
      rows="3"
      multiline monospace readonly autosize mt-5
    />
  </c-card>
</template>
