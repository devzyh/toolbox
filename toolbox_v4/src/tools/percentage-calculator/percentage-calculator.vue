<script setup lang="ts">
import { computed, ref } from 'vue';
import { translate } from '@/plugins/i18n.plugin';

const percentageX = ref<number>();
const percentageY = ref<number>();
const percentageResult = computed(() => {
  if (percentageX.value === undefined || percentageY.value === undefined) {
    return '';
  }
  return (percentageX.value / 100 * percentageY.value).toString();
});

const numberX = ref<number>();
const numberY = ref<number>();
const numberResult = computed(() => {
  if (numberX.value === undefined || numberY.value === undefined) {
    return '';
  }
  const result = 100 * numberX.value / numberY.value;
  return (!Number.isFinite(result) || Number.isNaN(result)) ? '' : result.toString();
});

const numberFrom = ref<number>();
const numberTo = ref<number>();
const percentageIncreaseDecrease = computed(() => {
  if (numberFrom.value === undefined || numberTo.value === undefined) {
    return '';
  }
  const result = (numberTo.value - numberFrom.value) / numberFrom.value * 100;
  return (!Number.isFinite(result) || Number.isNaN(result)) ? '' : result.toString();
});
</script>

<template>
  <div style="flex: 0 0 100%">
    <div style="margin: 0 auto; max-width: 600px">
      <c-card mb-3>
        <div mb-3 sm:hidden>
          {{ translate('tools.percentage-calculator.what-is') }}
        </div>
        <div flex gap-2>
          <div hidden pt-1 sm:block style="min-width: 48px;">
            {{ translate('tools.percentage-calculator.what-is') }}
          </div>
          <n-input-number v-model:value="percentageX" data-test-id="percentageX" placeholder="X" />
          <div min-w-fit pt-1>
            {{ translate('tools.percentage-calculator.percent-of') }}
          </div>
          <n-input-number v-model:value="percentageY" data-test-id="percentageY" placeholder="Y" />
          <input-copyable v-model:value="percentageResult" data-test-id="percentageResult" readonly :placeholder="translate('tools.percentage-calculator.result')" style="max-width: 150px;" />
        </div>
      </c-card>

      <c-card mb-3>
        <div mb-3 sm:hidden>
          {{ translate('tools.percentage-calculator.x-is-what-percent-of-y') }}
        </div>
        <div flex gap-2>
          <n-input-number v-model:value="numberX" data-test-id="numberX" placeholder="X" />
          <div hidden min-w-fit pt-1 sm:block>
            {{ translate('tools.percentage-calculator.is-what-percent-of') }}
          </div>
          <n-input-number v-model:value="numberY" data-test-id="numberY" placeholder="Y" />
          <input-copyable v-model:value="numberResult" data-test-id="numberResult" readonly :placeholder="translate('tools.percentage-calculator.result')" style="max-width: 150px;" />
        </div>
      </c-card>

      <c-card mb-3>
        <div mb-3>
          {{ translate('tools.percentage-calculator.what-is-the-percentage-increase-decrease') }}
        </div>
        <div flex gap-2>
          <n-input-number v-model:value="numberFrom" data-test-id="numberFrom" :placeholder="translate('tools.percentage-calculator.from')" />
          <n-input-number v-model:value="numberTo" data-test-id="numberTo" :placeholder="translate('tools.percentage-calculator.to')" />
          <input-copyable v-model:value="percentageIncreaseDecrease" data-test-id="percentageIncreaseDecrease" readonly :placeholder="translate('tools.percentage-calculator.result')" style="max-width: 150px;" />
        </div>
      </c-card>
    </div>
  </div>
</template>
