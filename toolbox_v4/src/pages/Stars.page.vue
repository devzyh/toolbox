<script setup lang="ts">
import { useHead } from '@vueuse/head';
import StarCard from '../components/StarCard.vue';
import { useToolStore } from '@/tools/tools.store';
import { starsData } from '@/stars/stars.data';

const toolStore = useToolStore();

useHead({ title: '星标 - DZ Tools' });
const { t } = useI18n();
</script>

<template>
  <div class="pt-50px">
    <div v-for="starsItem in starsData" :key="starsItem.code" class="grid-wrapper">
      <h3 class="mb-5px mt-25px text-neutral-400 font-500">
        {{ starsItem.name }}
      </h3>
      <div class="grid grid-cols-1 gap-12px lg:grid-cols-3 md:grid-cols-3 sm:grid-cols-2 xl:grid-cols-4">
        <StarCard v-for="star in starsItem.stars" :key="star.href" :star="star" />
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">
.height-enter-active,
.height-leave-active {
  transition: all 0.5s ease-in-out;
  overflow: hidden;
  max-height: 500px;
}

.height-enter-from,
.height-leave-to {
  max-height: 42px;
  overflow: hidden;
  opacity: 0;
  margin-bottom: 0;
}

.ghost-favorites-draggable {
  opacity: 0.4;
  background-color: #ccc;
  border: 2px dashed #666;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  transform: scale(1.1);
  animation: ghost-favorites-draggable-animation 0.2s ease-out;
}

@keyframes ghost-favorites-draggable-animation {
  0% {
    opacity: 0;
    transform: scale(0.9);
  }
  100% {
    opacity: 0.4;
    transform: scale(1.0);
  }
}
</style>
