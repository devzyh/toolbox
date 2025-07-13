<script setup lang="ts">
import { useEventListener } from '@vueuse/core';
import { computed, ref } from 'vue';
import InputCopyable from '../../components/InputCopyable.vue';
import { translate } from '@/plugins/i18n.plugin';

const event = ref<KeyboardEvent>();

useEventListener(document, 'keydown', (e) => {
  event.value = e;
});

const fields = computed(() => {
  if (!event.value) {
    return [];
  }

  return [
    {
      label: `${translate('tools.keycode-info.key')}:`,
      value: event.value.key,
      placeholder: translate('tools.keycode-info.placeholder-key-name'),
    },
    {
      label: `${translate('tools.keycode-info.keycode')}:`,
      value: String(event.value.keyCode),
      placeholder: translate('tools.keycode-info.placeholder-keycode'),
    },
    {
      label: `${translate('tools.keycode-info.code')}:`,
      value: event.value.code,
      placeholder: translate('tools.keycode-info.placeholder-code'),
    },
    {
      label: `${translate('tools.keycode-info.location')}:`,
      value: String(event.value.location),
      placeholder: translate('tools.keycode-info.placeholder-location'),
    },
    {
      label: `${translate('tools.keycode-info.modifiers')}:`,
      value: [
        event.value.metaKey && 'Meta',
        event.value.shiftKey && 'Shift',
        event.value.ctrlKey && 'Ctrl',
        event.value.altKey && 'Alt',
      ]
        .filter(Boolean)
        .join(' + '),
      placeholder: translate('tools.keycode-info.placeholder-modifiers'),
    },
  ];
});
</script>

<template>
  <div class="mw-600">
    <c-card mb-5 text-center important:py-12>
      <div v-if="event" mb-2 text-3xl>
        {{ event.key }}
      </div>
      <span lh-1 op-70>
        {{ translate('tools.keycode-info.press-key-instruction') }}
      </span>
    </c-card>

    <n-input-group v-for="({ label, value, placeholder }, i) of fields" :key="i" style="margin-bottom: 5px">
      <n-input-group-label style="flex: 0 0 150px">
        {{ label }}
      </n-input-group-label>
      <InputCopyable :value="value" readonly :placeholder="placeholder" />
    </n-input-group>
  </div>
</template>
