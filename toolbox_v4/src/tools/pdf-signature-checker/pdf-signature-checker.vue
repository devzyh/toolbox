<script setup lang="ts">
import verifyPDF from 'pdf-signature-reader';
import { ref } from 'vue';
import type { SignatureInfo } from './pdf-signature-checker.types';
import { formatBytes } from '@/utils/convert';
import { translate } from '@/plugins/i18n.plugin';

const signatures = ref<SignatureInfo[]>([]);
const status = ref<'idle' | 'parsed' | 'error' | 'loading'>('idle');
const file = ref<File | null>(null);

async function onVerifyClicked(uploadedFile: File) {
  file.value = uploadedFile;
  const fileBuffer = await uploadedFile.arrayBuffer();
  status.value = 'loading';
  try {
    const { signatures: parsedSignatures } = verifyPDF(fileBuffer);
    signatures.value = parsedSignatures;
    status.value = 'parsed';
  }
  catch (e) {
    signatures.value = [];
    status.value = 'error';
  }
}

function translateWithReplace(key: string, replacements: Record<string, any>) {
  let translatedText = translate(key);
  Object.entries(replacements).forEach(([placeholder, value]) => {
    translatedText = translatedText.replace(new RegExp(`{${placeholder}}`, 'g'), value);
  });
  return translatedText;
}
</script>

<template>
  <div class="mw-600" style="flex: 0 0 100%">
    <div mx-auto>
      <c-file-upload
        :title="translate('tools.pdf-signature-checker.drag-and-drop')"
        accept=".pdf"
        @file-upload="onVerifyClicked"
      />
      <c-card v-if="file" mt-4 flex gap-2>
        <div font-bold>
          {{ file.name }}
        </div>
        <div>{{ formatBytes(file.size) }}</div>
      </c-card>
      <div v-if="status === 'error'">
        <c-alert mt-4>
          {{ translate('tools.pdf-signature-checker.no-signatures-found') }}
        </c-alert>
      </div>
    </div>
  </div>
  <div v-if="status === 'parsed' && signatures.length" style="flex: 0 0 100%" mt-5 flex flex-col gap-4>
    <div v-for="(signature, index) of signatures" :key="index">
      <div mb-2 font-bold>
        {{ translateWithReplace('tools.pdf-signature-checker.signature-certificates', { index: index + 1 }) }}
      </div>
      <pdf-signature-details :signature="signature" />
    </div>
  </div>
</template>
