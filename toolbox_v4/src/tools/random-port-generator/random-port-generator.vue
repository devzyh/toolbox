<script setup lang="ts">
import { generatePort } from './random-port-generator.model';
import { computedRefreshable } from '@/composable/computedRefreshable';
import { useCopy } from '@/composable/copy';
import { translate } from '@/plugins/i18n.plugin';

const [port, refreshPort] = computedRefreshable(() => String(generatePort()));

const { copy } = useCopy({ source: port, text: translate('tools.random-port-generator.port-copied-to-clipboard') });
</script>

<template>
  <c-card class="mw-600">
    <div class="port">
      {{ port }}
    </div>
    <div flex justify-center gap-3>
      <c-button @click="copy()">
        {{ translate('tools.random-port-generator.copy') }}
      </c-button>
      <c-button @click="refreshPort">
        {{ translate('tools.random-port-generator.refresh') }}
      </c-button>
    </div>
  </c-card>
</template>

<style lang="less" scoped>
.port {
  text-align: center;
  font-size: 26px;
  font-weight: 400;
  margin: 10px 0 25px;
}
</style>
