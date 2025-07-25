<script setup lang="ts">
import { useThemeVars } from 'naive-ui';
import InputCopyable from '../../components/InputCopyable.vue';
import { computeChmodOctalRepresentation, computeChmodSymbolicRepresentation } from './chmod-calculator.service';
import type { Group, Scope } from './chmod-calculator.types';
import { translate } from '@/plugins/i18n.plugin';

const themeVars = useThemeVars();

const scopes: { scope: Scope; title: string }[] = [
  { scope: 'read', title: translate('tools.chmod-calculator.read') },
  { scope: 'write', title: translate('tools.chmod-calculator.write') },
  { scope: 'execute', title: translate('tools.chmod-calculator.execute') },
];
const groups: Group[] = ['owner', 'group', 'public'];

const permissions = ref({
  owner: { read: false, write: false, execute: false },
  group: { read: false, write: false, execute: false },
  public: { read: false, write: false, execute: false },
});

const octal = computed(() => computeChmodOctalRepresentation({ permissions: permissions.value }));
const symbolic = computed(() => computeChmodSymbolicRepresentation({ permissions: permissions.value }));
</script>

<template>
  <div>
    <n-table :bordered="false" :bottom-bordered="false" single-column class="permission-table">
      <thead>
        <tr>
          <th class="text-center" scope="col" />
          <th class="text-center" scope="col">
            {{ translate('tools.chmod-calculator.owner') }}(u)
          </th>
          <th class="text-center" scope="col">
            {{ translate('tools.chmod-calculator.group') }}(g)
          </th>
          <th class="text-center" scope="col">
            {{ translate('tools.chmod-calculator.public') }}(o)
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="{ scope, title } of scopes" :key="scope">
          <td class="line-header">
            {{ title }}
          </td>
          <td v-for="group of groups" :key="group" class="text-center">
            <n-checkbox v-model:checked="permissions[group][scope]" size="large" />
          </td>
        </tr>
      </tbody>
    </n-table>

    <div class="octal-result">
      {{ octal }}
    </div>
    <div class="octal-result">
      {{ symbolic }}
    </div>

    <InputCopyable :value="`chmod ${octal} path`" readonly />
  </div>
</template>

<style lang="less" scoped>
.octal-result {
  text-align: center;
  font-size: 50px;
  font-family: monospace;
  color: v-bind('themeVars.primaryColor');
  margin: 20px 0;
}
.permission-table {
  td,
  th {
    padding: 15px;

    @media screen and (max-width: 600px) {
      padding: 5px;
    }
  }
}
.line-header {
  font-weight: bold;
  text-align: right;
  max-width: 80px;
}
.text-center {
  text-align: center;
}
</style>
