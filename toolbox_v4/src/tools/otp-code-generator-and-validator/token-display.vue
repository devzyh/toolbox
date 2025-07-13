<script setup lang="ts">
import { toRefs } from 'vue';
import { useCopy } from '@/composable/copy';

import { translate } from '@/plugins/i18n.plugin';

const props = defineProps<{ tokens: { previous: string; current: string; next: string } }>();
const { copy: copyPrevious, isJustCopied: previousCopied } = useCopy({ createToast: false });
const { copy: copyCurrent, isJustCopied: currentCopied } = useCopy({ createToast: false });
const { copy: copyNext, isJustCopied: nextCopied } = useCopy({ createToast: false });

const { tokens } = toRefs(props);
</script>

<template>
  <div>
    <div mb-5px w-full flex items-center>
      <div flex-1 text-left>
        {{ translate('tools.otp-generator.previous') }}
      </div>
      <div flex-1 text-center>
        {{ translate('tools.otp-generator.current-otp') }}
      </div>
      <div flex-1 text-right>
        {{ translate('tools.otp-generator.next') }}
      </div>
    </div>
    <div flex items-center>
      <c-tooltip :tooltip="previousCopied ? translate('tools.otp-generator.copied') : translate('tools.otp-generator.copy-previous-otp')" position="bottom" flex-1>
        <c-button data-test-id="previous-otp" w-full important:h-12 important:rounded-r-none important:font-mono @click.prevent="copyPrevious(tokens.previous)">
          {{ tokens.previous }}
        </c-button>
      </c-tooltip>
      <c-tooltip :tooltip="currentCopied ? translate('tools.otp-generator.copied') : translate('tools.otp-generator.copy-current-otp')" position="bottom" flex-1 flex-basis-5xl>
        <c-button
          data-test-id="current-otp" w-full important:border-x="1px solid gray op-40" important:h-12 important:rounded-0 important:text-22px @click.prevent="copyCurrent(tokens.current)"
        >
          {{ tokens.current }}
        </c-button>
      </c-tooltip>
      <c-tooltip :tooltip="nextCopied ? translate('tools.otp-generator.copied') : translate('tools.otp-generator.copy-next-otp')" position="bottom" flex-1>
        <c-button data-test-id="next-otp" w-full important:h-12 important:rounded-l-none @click.prevent="copyNext(tokens.next)">
          {{ tokens.next }}
        </c-button>
      </c-tooltip>
    </div>
  </div>
</template>
