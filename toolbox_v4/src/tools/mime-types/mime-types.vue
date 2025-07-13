<script setup lang="ts">
import { types as extensionToMimeType, extensions as mimeTypeToExtension } from 'mime-types';
import { computed, ref } from 'vue';
import { translate } from '@/plugins/i18n.plugin';

const mimeInfos = Object.entries(mimeTypeToExtension).map(([mimeType, extensions]) => ({ mimeType, extensions }));
const mimeToExtensionsOptions = Object.keys(mimeTypeToExtension).map(label => ({ label, value: label }));
const selectedMimeType = ref<string | undefined>(undefined);
const extensionsFound = computed(() => (selectedMimeType.value ? mimeTypeToExtension[selectedMimeType.value] : []));
const extensionToMimeTypeOptions = Object.keys(extensionToMimeType).map(label => ({ label: `.${label}`, value: label }));
const selectedExtension = ref<string | undefined>(undefined);
const mimeTypeFound = computed(() => (selectedExtension.value ? extensionToMimeType[selectedExtension.value] : []));
</script>

<template>
  <c-card class="mw-600">
    <n-h2 style="margin-bottom:0">
      {{ translate('tools.mime-types.mime-type-to-extension') }}
    </n-h2>
    <div style="opacity:0.8">
      {{ translate('tools.mime-types.know-extensions-associated-with-mime-type') }}
    </div>
    <c-select
      v-model:value="selectedMimeType"
      searchable
      my-4
      :options="mimeToExtensionsOptions"
      :placeholder="translate('tools.mime-types.select-your-mimetype-here')"
    />
    <div v-if="extensionsFound.length > 0">
      {{ translate('tools.mime-types.extensions-of-files-with-mime-type').replace('<mimeType>', selectedMimeType) }}
      <div style="margin-top:10px">
        <n-tag
          v-for="extension of extensionsFound"
          :key="extension"
          round
          :bordered="false"
          type="primary"
          style="margin-right:10px"
        >
          .{{ extension }}
        </n-tag>
      </div>
    </div>
  </c-card>

  <c-card class="mw-600">
    <n-h2 style="margin-bottom:0">
      {{ translate('tools.mime-types.file-extension-to-mime-type') }}
    </n-h2>
    <div style="opacity:0.8">
      {{ translate('tools.mime-types.know-mime-type-associated-with-extension') }}
    </div>
    <c-select
      v-model:value="selectedExtension"
      searchable
      my-4
      :options="extensionToMimeTypeOptions"
      :placeholder="translate('tools.mime-types.select-your-extension-here')"
    />
    <div v-if="selectedExtension">
      {{ translate('tools.mime-types.mime-type-associated-with-extension').replace('<extension>', selectedExtension) }}
      <div style="margin-top:10px">
        <n-tag round :bordered="false" type="primary" style="margin-right:10px">
          {{ mimeTypeFound }}
        </n-tag>
      </div>
    </div>
  </c-card>

  <div>
    <n-table>
      <thead>
        <tr>
          <th>{{ translate('tools.mime-types.mime-types') }}</th>
          <th>{{ translate('tools.mime-types.extensions') }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="{ mimeType, extensions } of mimeInfos" :key="mimeType">
          <td>{{ mimeType }}</td>
          <td>
            <n-tag
              v-for="extension of extensions"
              :key="extension"
              round
              :bordered="false"
              style="margin-right:10px"
            >
              .{{ extension }}
            </n-tag>
          </td>
        </tr>
      </tbody>
    </n-table>
  </div>
</template>
