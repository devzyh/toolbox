<script setup lang="ts">
import { format } from 'prettier';
import htmlParser from 'prettier/plugins/html';
import { useStorage } from '@vueuse/core';
import Editor from './editor/editor.vue';
import TextareaCopyable from '@/components/TextareaCopyable.vue';

const html = useStorage('dz-tools:html-wysiwyg-editor:html', '<h1>Hey!</h1><p>Welcome to this html wysiwyg editor</p>');

const formattedHtml = asyncComputed(() => format(html.value, { parser: 'html', plugins: [htmlParser] }), '');
</script>

<template>
  <Editor v-model:html="html" class="h-50vh w-full" />
  <TextareaCopyable :value="formattedHtml" language="html" class="w-full" />
</template>
