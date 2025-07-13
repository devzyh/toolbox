<script setup lang="ts">
import { normalizeEmail } from 'email-normalizer';
import { withDefaultOnError } from '@/utils/defaults';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const emails = ref('');
const normalizedEmails = computed(() => {
  if (!emails.value) {
    return '';
  }

  return emails.value
    .split('\n')
    .map((email) => {
      return withDefaultOnError(() => normalizeEmail({ email }), `Unable to parse email: ${email}`);
    })
    .join('\n');
});

const { copy } = useCopy({
  source: normalizedEmails,
  text: 'Normalized emails copied to the clipboard',
  createToast: true,
});
</script>

<template>
  <div class="mw-600">
    <div class="mb-2">
      {{ translate('tools.email-normalizer.raw-emails-to-normalize') }}:
    </div>
    <c-input-text
      v-model:value="emails"
      :placeholder="translate('tools.email-normalizer.placeholder-your-emails-here')"
      rows="3"

      autocomplete="off"
      autocorrect="off"
      autocapitalize="off"
      spellcheck="false"

      autofocus multiline monospace
    />

    <div class="mb-2 mt-4">
      {{ translate('tools.email-normalizer.normalized-emails') }}:
    </div>
    <c-input-text
      :value="normalizedEmails"
      :placeholder="translate('tools.email-normalizer.placeholder-normalized-emails-will-appear-here')"
      rows="3"
      autocomplete="off"
      autocorrect="off"
      autocapitalize="off"
      spellcheck="false"

      multiline monospace readonly
    />
    <div class="mt-4 flex justify-center gap-2">
      <c-button @click="emails = ''">
        {{ translate('tools.email-normalizer.clear-emails') }}
      </c-button>
      <c-button :disabled="!normalizedEmails" @click="copy()">
        {{ translate('tools.email-normalizer.copy-normalized-emails') }}
      </c-button>
    </div>
  </div>
</template>
