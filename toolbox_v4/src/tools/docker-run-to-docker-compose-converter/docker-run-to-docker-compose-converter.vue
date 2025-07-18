<script setup lang="ts">
import { MessageType, composerize } from 'composerize-ts';
import { withDefaultOnError } from '@/utils/defaults';
import { useDownloadFileFromBase64 } from '@/composable/downloadBase64';
import { textToBase64 } from '@/utils/base64';
import TextareaCopyable from '@/components/TextareaCopyable.vue';
import { translate } from '@/plugins/i18n.plugin';

const dockerRun = ref(
  'docker run -p 80:80 -v /var/run/docker.sock:/tmp/docker.sock:ro --restart always --log-opt max-size=1g nginx',
);

const conversionResult = computed(() =>
  withDefaultOnError(() => composerize(dockerRun.value.trim()), { yaml: '', messages: [] }),
);
const dockerCompose = computed(() => conversionResult.value.yaml);
const notImplemented = computed(() =>
  conversionResult.value.messages.filter(msg => msg.type === MessageType.notImplemented).map(msg => msg.value),
);
const notComposable = computed(() =>
  conversionResult.value.messages.filter(msg => msg.type === MessageType.notTranslatable).map(msg => msg.value),
);
const errors = computed(() =>
  conversionResult.value.messages
    .filter(msg => msg.type === MessageType.errorDuringConversion)
    .map(msg => msg.value),
);
const dockerComposeBase64 = computed(() => `data:application/yaml;base64,${textToBase64(dockerCompose.value)}`);
const { download } = useDownloadFileFromBase64({ source: dockerComposeBase64, filename: 'docker-compose.yml' });
</script>

<template>
  <div class="mw-600">
    <c-input-text
      v-model:value="dockerRun"
      :label="translate('tools.docker-run-to-docker-compose-converter.label-docker-run-command')"
      style="font-family: monospace"
      multiline
      raw-text
      monospace
      :placeholder="translate('tools.docker-run-to-docker-compose-converter.placeholder-your-docker-run-command')"
      rows="3"
    />

    <n-divider />

    <TextareaCopyable :value="dockerCompose" language="yaml" />

    <div mt-5 flex justify-center>
      <c-button :disabled="dockerCompose === ''" secondary @click="download">
        {{ translate('tools.docker-run-to-docker-compose-converter.download-docker-compose-yml') }}
      </c-button>
    </div>

    <div v-if="notComposable.length > 0">
      <n-alert
        :title="translate('tools.docker-run-to-docker-compose-converter.this-options-are-not-translatable')"
        type="info" mt-5
      >
        <ul>
          <li v-for="(message, index) of notComposable" :key="index">
            {{ message }}
          </li>
        </ul>
      </n-alert>
    </div>

    <div v-if="notImplemented.length > 0">
      <n-alert
        :title="translate('tools.docker-run-to-docker-compose-converter.this-options-are-not-implemented')"
        type="warning"
        mt-5
      >
        <ul>
          <li v-for="(message, index) of notImplemented" :key="index">
            {{ message }}
          </li>
        </ul>
      </n-alert>
    </div>

    <div v-if="errors.length > 0">
      <n-alert
        :title="translate('tools.docker-run-to-docker-compose-converter.the-following-errors-occured')"
        type="error" mt-5
      >
        <ul>
          <li v-for="(message, index) of errors" :key="index">
            {{ message }}
          </li>
        </ul>
      </n-alert>
    </div>
  </div>
</template>
