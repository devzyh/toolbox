<script setup lang="ts">
import { computed, ref } from 'vue';
import { UAParser } from 'ua-parser-js';
import { Adjustments, Browser, Cpu, Devices, Engine } from '@vicons/tabler';
import UserAgentResultCards from './user-agent-result-cards.vue';
import type { UserAgentResultSection } from './user-agent-parser.types';
import { withDefaultOnError } from '@/utils/defaults';
import { translate } from '@/plugins/i18n.plugin';

const ua = ref(navigator.userAgent as string);

function getUserAgentInfo(userAgent: string) {
  return userAgent.trim().length > 0
    ? UAParser(userAgent.trim())
    : ({ ua: '', browser: {}, cpu: {}, device: {}, engine: {}, os: {} } as UAParser.IResult);
}

const userAgentInfo = computed(() => withDefaultOnError(() => getUserAgentInfo(ua.value), undefined));

const sections: UserAgentResultSection[] = [
  {
    heading: translate('tools.user-agent-parser.sections-browser-heading'),
    icon: Browser,
    content: [
      {
        label: translate('tools.user-agent-parser.sections-browser-name'),
        getValue: block => block?.browser.name || translate('tools.user-agent-parser.undefined-fallback'),
      },
      {
        label: translate('tools.user-agent-parser.sections-browser-version'),
        getValue: block => block?.browser.version || translate('tools.user-agent-parser.undefined-fallback'),
      },
    ],
  },
  {
    heading: translate('tools.user-agent-parser.sections-engine-heading'),
    icon: Engine,
    content: [
      {
        label: translate('tools.user-agent-parser.sections-engine-name'),
        getValue: block => block?.engine.name || translate('tools.user-agent-parser.undefined-fallback'),
      },
      {
        label: translate('tools.user-agent-parser.sections-engine-version'),
        getValue: block => block?.engine.version || translate('tools.user-agent-parser.undefined-fallback'),
      },
    ],
  },
  {
    heading: translate('tools.user-agent-parser.sections-os-heading'),
    icon: Adjustments,
    content: [
      {
        label: translate('tools.user-agent-parser.sections-os-name'),
        getValue: block => block?.os.name || translate('tools.user-agent-parser.undefined-fallback'),
      },
      {
        label: translate('tools.user-agent-parser.sections-os-version'),
        getValue: block => block?.os.version || translate('tools.user-agent-parser.undefined-fallback'),
      },
    ],
  },
  {
    heading: translate('tools.user-agent-parser.sections-device-heading'),
    icon: Devices,
    content: [
      {
        label: translate('tools.user-agent-parser.sections-device-model'),
        getValue: block => block?.device.model || translate('tools.user-agent-parser.undefined-fallback'),
      },
      {
        label: translate('tools.user-agent-parser.sections-device-type'),
        getValue: block => block?.device.type || translate('tools.user-agent-parser.undefined-fallback'),
      },
      {
        label: translate('tools.user-agent-parser.sections-device-vendor'),
        getValue: block => block?.device.vendor || translate('tools.user-agent-parser.undefined-fallback'),
      },
    ],
  },
  {
    heading: translate('tools.user-agent-parser.sections-cpu-heading'),
    icon: Cpu,
    content: [
      {
        label: translate('tools.user-agent-parser.sections-cpu-architecture'),
        getValue: block => block?.cpu.architecture || translate('tools.user-agent-parser.undefined-fallback'),
      },
    ],
  },
];
</script>

<template>
  <div>
    <c-input-text
      v-model:value="ua"
      :label="translate('tools.user-agent-parser.input-label')"
      multiline
      clearable
      raw-text
      rows="2"
      autosize
      monospace
      mb-3
    />

    <UserAgentResultCards :user-agent-info="userAgentInfo" :sections="sections" />
  </div>
</template>
