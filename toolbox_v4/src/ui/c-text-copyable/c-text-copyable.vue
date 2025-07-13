<script setup lang="ts">
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const props = withDefaults(defineProps<{ value?: string; displayedValue?: string; showIcon?: boolean }>(), { value: '', displayedValue: undefined, showIcon: true });
const { value, displayedValue, showIcon } = toRefs(props);

const { copy, isJustCopied } = useCopy({ source: value, createToast: false });
</script>

<template>
  <c-tooltip :tooltip="isJustCopied ? translate('ui.text-copyable.copied') : translate('ui.text-copyable.copy-to-clipboard')" cursor-pointer @click="copy">
    <span flex items-center gap-2>
      {{ displayedValue ?? value }}
      <icon-mdi-content-copy v-if="showIcon" op-40 />
    </span>
  </c-tooltip>
</template>
