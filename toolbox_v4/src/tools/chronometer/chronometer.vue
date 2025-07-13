<script setup lang="ts">
import { useRafFn } from '@vueuse/core';
import { formatMs } from './chronometer.service';
import { translate } from '@/plugins/i18n.plugin';

const isRunning = ref(false);
const counter = ref(0);

let previousRafDate = Date.now();
const { pause: pauseRaf, resume: resumeRaf } = useRafFn(
  () => {
    const deltaMs = Date.now() - previousRafDate;
    previousRafDate = Date.now();
    counter.value += deltaMs;
  },
  { immediate: false },
);

function resume() {
  previousRafDate = Date.now();
  resumeRaf();
  isRunning.value = true;
}

function pause() {
  pauseRaf();
  isRunning.value = false;
}
</script>

<template>
  <div class="mw-600">
    <c-card>
      <div class="duration">
        {{ formatMs(counter) }}
      </div>
    </c-card>
    <div mt-5 flex justify-center gap-3>
      <c-button v-if="!isRunning" type="primary" @click="resume">
        {{ translate('tools.chronometer.start') }}
      </c-button>
      <c-button v-else type="warning" @click="pause">
        {{ translate('tools.chronometer.stop') }}
      </c-button>

      <c-button @click="counter = 0">
        {{ translate('tools.chronometer.reset') }}
      </c-button>
    </div>
  </div>
</template>

<style lang="less" scoped>
.duration {
  text-align: center;
  font-size: 40px;
  font-family: monospace;
  margin: 20px 0;
}
</style>
