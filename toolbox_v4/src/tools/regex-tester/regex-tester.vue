<script setup lang="ts">
import RandExp from 'randexp';
import { render } from '@regexper/render';
import type { ShadowRootExpose } from 'vue-shadow-dom';
import { matchRegex } from './regex-tester.service';
import { useValidation } from '@/composable/validation';
import { useQueryParamOrStorage } from '@/composable/queryParams';
import { translate } from '@/plugins/i18n.plugin';

const regex = useQueryParamOrStorage({ name: 'regex', storageName: 'regex-tester:regex', defaultValue: '' });
const text = ref('');
const global = ref(true);
const ignoreCase = ref(false);
const multiline = ref(false);
const dotAll = ref(true);
const unicode = ref(true);
const unicodeSets = ref(false);
const visualizerSVG = ref<ShadowRootExpose>();

const regexValidation = useValidation({
  source: regex,
  rules: [
    {
      message: translate('tools.regex-tester.invalid-regex'),
      validator: value => new RegExp(value),
      getErrorMessage: (value) => {
        const _ = new RegExp(value);
        return '';
      },
    },
  ],
});

const results = computed(() => {
  let flags = 'd';
  if (global.value) {
    flags += 'g';
  }
  if (ignoreCase.value) {
    flags += 'i';
  }
  if (multiline.value) {
    flags += 'm';
  }
  if (dotAll.value) {
    flags += 's';
  }
  if (unicode.value) {
    flags += 'u';
  }
  else if (unicodeSets.value) {
    flags += 'v';
  }

  try {
    return matchRegex(regex.value, text.value, flags);
  }
  catch (_) {
    return [];
  }
});

const sample = computed(() => {
  try {
    const randexp = new RandExp(new RegExp(regex.value.replace(/\(\?\<[^\>]*\>/g, '(?:')));
    return randexp.gen();
  }
  catch (_) {
    return '';
  }
});

watchEffect(async () => {
  const regexValue = regex.value;
  const visualizer = visualizerSVG.value?.shadow_root;
  if (visualizer) {
    while (visualizer.lastChild) {
      visualizer.removeChild(visualizer.lastChild);
    }
    const svg = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
    try {
      await render(regexValue, svg);
    }
    catch (_) {
      // Ignore rendering errors
    }
    visualizer.appendChild(svg);
  }
});
</script>

<template>
  <div class="mw-600">
    <c-card :title="translate('tools.regex-tester.regex-to-test')" mb-1>
      <c-input-text
        v-model:value="regex"
        :label="translate('tools.regex-tester.put-the-regex-to-test')"
        :placeholder="translate('tools.regex-tester.put-the-regex-to-test')"
        multiline
        rows="3"
        :validation="regexValidation"
      />
      <n-space>
        <n-checkbox v-model:checked="global">
          <span :title="translate('tools.regex-tester.global-search')">{{
            translate('tools.regex-tester.global-search')
          }}</span>
        </n-checkbox>
        <n-checkbox v-model:checked="ignoreCase">
          <span :title="translate('tools.regex-tester.case-insensitive-search')">{{
            translate('tools.regex-tester.case-insensitive-search')
          }}</span>
        </n-checkbox>
        <n-checkbox v-model:checked="multiline">
          <span :title="translate('tools.regex-tester.multiline')">{{
            translate('tools.regex-tester.multiline')
          }}</span>
        </n-checkbox>
        <n-checkbox v-model:checked="dotAll">
          <span :title="translate('tools.regex-tester.singleline')">{{
            translate('tools.regex-tester.singleline')
          }}</span>
        </n-checkbox>
        <n-checkbox v-model:checked="unicode">
          <span :title="translate('tools.regex-tester.unicode')">{{ translate('tools.regex-tester.unicode') }}</span>
        </n-checkbox>
        <n-checkbox v-model:checked="unicodeSets">
          <span :title="translate('tools.regex-tester.unicode-sets')">{{
            translate('tools.regex-tester.unicode-sets')
          }}</span>
        </n-checkbox>
      </n-space>

      <n-divider />

      <c-input-text
        v-model:value="text"
        :label="translate('tools.regex-tester.text-to-match')"
        :placeholder="translate('tools.regex-tester.put-the-text-to-match')"
        multiline
        rows="5"
      />
    </c-card>

    <c-card :title="translate('tools.regex-tester.matches')" mb-1 mt-3>
      <n-table v-if="results?.length > 0">
        <thead>
          <tr>
            <th scope="col">
              {{ translate('tools.regex-tester.index-in-text') }}
            </th>
            <th scope="col">
              {{ translate('tools.regex-tester.value') }}
            </th>
            <th scope="col">
              {{ translate('tools.regex-tester.captures') }}
            </th>
            <th scope="col">
              {{ translate('tools.regex-tester.groups') }}
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="match of results" :key="match.index">
            <td>{{ match.index }}</td>
            <td>{{ match.value }}</td>
            <td>
              <ul>
                <li v-for="capture in match.captures" :key="capture.name">
                  "{{ capture.name }}" = {{ capture.value }} [{{ capture.start }} - {{ capture.end }}]
                </li>
              </ul>
            </td>
            <td>
              <ul>
                <li v-for="group in match.groups" :key="group.name">
                  "{{ group.name }}" = {{ group.value }} [{{ group.start }} - {{ group.end }}]
                </li>
              </ul>
            </td>
          </tr>
        </tbody>
      </n-table>
      <c-alert v-else>
        {{ translate('tools.regex-tester.no-match') }}
      </c-alert>
    </c-card>

    <c-card :title="translate('tools.regex-tester.sample-matching-text')" mt-3>
      <pre style="white-space: pre-wrap; word-break: break-all;">{{ sample }}</pre>
    </c-card>

    <c-card :title="translate('tools.regex-tester.regex-diagram')" style="overflow-x: scroll;" mt-3>
      <shadow-root ref="visualizerSVG">
        &#xa0;
      </shadow-root>
    </c-card>
  </div>
</template>
