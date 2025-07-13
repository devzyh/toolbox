<script setup lang="ts">
import { addMilliseconds, formatRelative } from 'date-fns';
import { zhCN } from 'date-fns/locale';
import { formatMsDuration } from './eta-calculator.service';
import { translate } from '@/plugins/i18n.plugin';

const unitCount = ref(3 * 62);
const unitPerTimeSpan = ref(3);
const timeSpan = ref(5);
const timeSpanUnitMultiplier = ref(60000);
const startedAt = ref(Date.now());

const durationMs = computed(() => {
  const timeSpanMs = timeSpan.value * timeSpanUnitMultiplier.value;
  return unitCount.value / (unitPerTimeSpan.value / timeSpanMs);
});

const endAt = computed(() =>
  formatRelative(addMilliseconds(startedAt.value, durationMs.value), Date.now(), { locale: zhCN }),
);
</script>

<template>
  <div class="mw-600">
    <div text-justify op-70>
      {{ translate('tools.eta-calculator.with-a-concrete-example') }}
    </div>
    <n-divider />
    <div flex gap-2>
      <n-form-item :label="translate('tools.eta-calculator.amount-of-element-to-consume')" flex-1>
        <n-input-number v-model:value="unitCount" :min="1" />
      </n-form-item>
      <n-form-item :label="translate('tools.eta-calculator.the-consumption-started-at')" flex-1>
        <n-date-picker v-model:value="startedAt" type="datetime" />
      </n-form-item>
    </div>

    <p>{{ translate('tools.eta-calculator.amount-of-unit-consumed-by-time-span') }}</p>
    <div flex flex-col items-baseline gap-y-2 md:flex-row>
      <n-input-number v-model:value="unitPerTimeSpan" :min="1" />
      <div flex items-baseline gap-2>
        <span ml-2>{{ translate('tools.eta-calculator.in') }}</span>
        <n-input-number v-model:value="timeSpan" min-w-130px :min="1" />
        <c-select
          v-model:value="timeSpanUnitMultiplier"
          min-w-130px
          :options="[
            { label: translate('tools.eta-calculator.milliseconds'), value: 1 },
            { label: translate('tools.eta-calculator.seconds'), value: 1000 },
            { label: translate('tools.eta-calculator.minutes'), value: 1000 * 60 },
            { label: translate('tools.eta-calculator.hours'), value: 1000 * 60 * 60 },
            { label: translate('tools.eta-calculator.days'), value: 1000 * 60 * 60 * 24 },
          ]"
        />
      </div>
    </div>

    <n-divider />
    <c-card mb-2>
      <n-statistic :label="translate('tools.eta-calculator.total-duration')">
        {{ formatMsDuration(durationMs) }}
      </n-statistic>
    </c-card>
    <c-card>
      <n-statistic :label="translate('tools.eta-calculator.it-will-end')">
        {{ endAt }}
      </n-statistic>
    </c-card>
  </div>
</template>

<style lang="less" scoped>
.n-input-number,
.n-date-picker {
  width: 100%;
}
</style>
