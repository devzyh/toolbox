<script lang="ts" setup>
import { onBeforeUnmount, onMounted, ref, watch } from 'vue';
import * as ace from 'ace-builds';
import 'ace-builds/src-min-noconflict/mode-xml';
import 'ace-builds/src-min-noconflict/theme-sqlserver';
import 'ace-builds/src-min-noconflict/ext-searchbox';

const props = withDefaults(defineProps<{
  modelValue?: string
}>(),
{
  modelValue: '',
});

const emit = defineEmits(['update:modelValue']);
const modelValue = useVModel(props, 'modelValue', emit);

const editor = ref<HTMLElement | null>(null);
let aceInstance: ace.Ace.Editor | null = null;

watch(() => props.modelValue, (newValue) => {
  if (aceInstance && newValue !== aceInstance.getValue()) {
    aceInstance.setValue(newValue);
  }
});

onMounted(() => {
  aceInstance = ace.edit(editor.value, {
    value: modelValue.value,
    mode: 'ace/mode/xml',
    theme: 'ace/theme/sqlserver',
    useWorker: false,
    wrap: 'free',
    showPrintMargin: false,
    fontSize: '14px',
  });

  aceInstance.on('change', () => {
    if (aceInstance) {
      modelValue.value = aceInstance.getValue();
    }
  });
});

onBeforeUnmount(() => {
  if (aceInstance) {
    aceInstance.destroy();
    aceInstance = null;
  }
});
</script>

<template>
  <div ref="editor" />
</template>
