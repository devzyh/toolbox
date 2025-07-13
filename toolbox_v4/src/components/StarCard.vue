<script setup lang="ts">
import { useThemeVars } from 'naive-ui';
import type { Star } from '@/stars/stars.types';

const props = defineProps<{ star: Star }>();
const { star } = toRefs(props);
const theme = useThemeVars();
</script>

<template>
  <a :href="star.href" target="_blank" class="decoration-none">
    <c-card class="h-full transition transition-duration-0.5s !border-2px !hover:border-primary">
      <div class="truncat my-5px text-lg text-black dark:text-white">
        {{ star.name }}
      </div>

      <div class="line-clamp-2 text-neutral-500 dark:text-neutral-400">
        {{ star.description }}
      </div>
    </c-card>
  </a>
</template>
