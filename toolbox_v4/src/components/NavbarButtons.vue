<script setup lang="ts">
import { IconBrandGit, IconStar, IconWorld } from '@tabler/icons-vue';
import { useStyleStore } from '@/stores/style.store';

const styleStore = useStyleStore();
const { isDarkTheme } = toRefs(styleStore);
</script>

<template>
  <c-tooltip :tooltip="$t('home.star')" position="bottom">
    <c-button to="/stars" circle variant="text" :aria-label="$t('home.star')">
      <n-icon size="25" :component="IconStar" />
    </c-button>
  </c-tooltip>

  <c-tooltip :tooltip="$t('home.nav.blog')" position="bottom">
    <c-button
      circle
      variant="text"
      href="https://juejin.cn/user/4182968134539128/posts"
      rel="noopener"
      target="_blank"
      :aria-label="$t('home.nav.blog')"
    >
      <n-icon size="25" :component="IconWorld" />
    </c-button>
  </c-tooltip>

  <c-tooltip :tooltip="$t('home.nav.git')" position="bottom">
    <c-button
      circle
      variant="text"
      href="https://gitee.com/devzyh/dz-tools"
      target="_blank"
      rel="noopener noreferrer"
      :aria-label="$t('home.nav.git')"
    >
      <n-icon size="25" :component="IconBrandGit" />
    </c-button>
  </c-tooltip>

  <c-tooltip v-if="false" :tooltip="isDarkTheme ? $t('home.nav.lightMode') : $t('home.nav.darkMode')" position="bottom">
    <c-button circle variant="text" :aria-label="$t('home.nav.mode')" @click="() => styleStore.toggleDark()">
      <n-icon v-if="isDarkTheme" size="25" :component="IconSun" />
      <n-icon v-else size="25" :component="IconMoon" />
    </c-button>
  </c-tooltip>
</template>

<style lang="less" scoped>
.n-button {
  &:not(:last-child) {
    margin-right: 5px;
  }
}
</style>
