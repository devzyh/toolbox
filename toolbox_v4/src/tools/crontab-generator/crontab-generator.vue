<script setup lang="ts">
import cronstrue from 'cronstrue';
import 'cronstrue/locales/zh_CN';
import { isValidCron } from 'cron-validator';
import { useStyleStore } from '@/stores/style.store';
import { translate } from '@/plugins/i18n.plugin';

function isCronValid(v: string) {
  return isValidCron(v, { allowBlankDay: true, alias: true, seconds: true });
}

const styleStore = useStyleStore();

const cron = ref('40 * * * *');
const cronstrueConfig = reactive({
  locale: 'zh_CN',
  verbose: true,
  dayOfWeekStartIndexZero: true,
  use24HourTimeFormat: true,
  throwExceptionOnParseError: true,
});

const helpers = [
  {
    symbol: '*',
    meaning: translate('tools.crontab-generator.meaning-any-value'),
    example: '* * * *',
    equivalent: translate('tools.crontab-generator.equivalent-every-minute'),
  },
  {
    symbol: '-',
    meaning: translate('tools.crontab-generator.meaning-range-of-values'),
    example: '1-10 * * *',
    equivalent: translate('tools.crontab-generator.equivalent-minutes-1-through-10'),
  },
  {
    symbol: ',',
    meaning: translate('tools.crontab-generator.meaning-list-of-values'),
    example: '1,10 * * *',
    equivalent: translate('tools.crontab-generator.equivalent-at-minutes-1-and-10'),
  },
  {
    symbol: '/',
    meaning: translate('tools.crontab-generator.meaning-step-values'),
    example: '*/10 * * *',
    equivalent: translate('tools.crontab-generator.equivalent-every-10-minutes'),
  },
  {
    symbol: '@yearly',
    meaning: translate('tools.crontab-generator.meaning-once-yearly'),
    example: '@yearly',
    equivalent: '0 0 1 1 *',
  },
  {
    symbol: '@annually',
    meaning: translate('tools.crontab-generator.meaning-same-as-yearly'),
    example: '@annually',
    equivalent: '0 0 1 1 *',
  },
  {
    symbol: '@monthly',
    meaning: translate('tools.crontab-generator.meaning-once-monthly'),
    example: '@monthly',
    equivalent: '0 0 1 * *',
  },
  {
    symbol: '@weekly',
    meaning: translate('tools.crontab-generator.meaning-once-weekly'),
    example: '@weekly',
    equivalent: '0 0 * * 0',
  },
  {
    symbol: '@daily',
    meaning: translate('tools.crontab-generator.meaning-once-daily'),
    example: '@daily',
    equivalent: '0 0 * * *',
  },
  {
    symbol: '@midnight',
    meaning: translate('tools.crontab-generator.meaning-same-as-daily'),
    example: '@midnight',
    equivalent: '0 0 * * *',
  },
  {
    symbol: '@hourly',
    meaning: translate('tools.crontab-generator.meaning-once-hourly'),
    example: '@hourly',
    equivalent: '0 * * * *',
  },
  {
    symbol: '@reboot',
    meaning: translate('tools.crontab-generator.meaning-run-at-startup'),
    example: '',
    equivalent: '',
  },
];

const cronString = computed(() => {
  if (isCronValid(cron.value)) {
    return cronstrue.toString(cron.value, cronstrueConfig);
  }
  return ' ';
});

const cronValidationRules = [
  {
    validator: (value: string) => isCronValid(value),
    message: translate('tools.crontab-generator.message-invalid-cron'),
  },
];
</script>

<template>
  <c-card class="mw-600">
    <div mx-auto max-w-sm>
      <c-input-text
        v-model:value="cron"
        size="large"
        :placeholder="translate('tools.crontab-generator.placeholder-cron')"
        :validation-rules="cronValidationRules"
        mb-3
      />
    </div>

    <div class="cron-string">
      {{ cronString }}
    </div>

    <n-divider />

    <div flex justify-center>
      <n-form :show-feedback="false" label-width="170" label-placement="left">
        <n-form-item :label="translate('tools.crontab-generator.label-verbose')">
          <n-switch v-model:value="cronstrueConfig.verbose" />
        </n-form-item>
        <n-form-item :label="translate('tools.crontab-generator.label-use-24-hour-time-format')">
          <n-switch v-model:value="cronstrueConfig.use24HourTimeFormat" />
        </n-form-item>
        <n-form-item :label="translate('tools.crontab-generator.label-days-start-at-0')">
          <n-switch v-model:value="cronstrueConfig.dayOfWeekStartIndexZero" />
        </n-form-item>
      </n-form>
    </div>
  </c-card>

  <c-card class="mw-600">
    <pre>
┌──────────── [optional] {{ translate('tools.crontab-generator.seconds') }} (0 - 59)
| ┌────────── {{ translate('tools.crontab-generator.minute') }} (0 - 59)
| | ┌──────── {{ translate('tools.crontab-generator.hour') }} (0 - 23)
| | | ┌────── {{ translate('tools.crontab-generator.day-of-month') }} (1 - 31)
| | | | ┌──── {{ translate('tools.crontab-generator.month') }} (1 - 12) OR jan,feb,mar,apr ...
| | | | | ┌── {{ translate('tools.crontab-generator.day-of-week') }} (0 - 6, sunday=0) OR sun,mon ...
| | | | | |
* * * * * * command</pre>

    <div v-if="styleStore.isSmallScreen">
      <c-card v-for="{ symbol, meaning, example, equivalent } in helpers" :key="symbol" mb-3 important:border-none>
        <div>
          {{ translate('tools.crontab-generator.symbol') }}: <strong>{{ symbol }}</strong>
        </div>
        <div>
          {{ translate('tools.crontab-generator.meaning') }}: <strong>{{ meaning }}</strong>
        </div>
        <div>
          {{ translate('tools.crontab-generator.example') }}:
          <strong><code>{{ example }}</code></strong>
        </div>
        <div>
          {{ translate('tools.crontab-generator.equivalent') }}: <strong>{{ equivalent }}</strong>
        </div>
      </c-card>
    </div>

    <c-table v-else :data="helpers" />
  </c-card>
</template>

<style lang="less" scoped>
::v-deep(input) {
  font-size: 30px;
  font-family: monospace;
  padding: 5px;
  text-align: center;
}

.cron-string {
  text-align: center;
  font-size: 22px;
  opacity: 0.8;
  margin: 5px 0 15px;
}

pre {
  overflow: auto;
  padding: 10px 0;
}
</style>
