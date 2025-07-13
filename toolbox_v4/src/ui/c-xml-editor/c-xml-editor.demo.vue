<script setup lang="ts">
const inputValue = useStorage('dz-tools:xml', '');
</script>

<template>
  <div>
    <c-xml-editor v-model="inputValue" class="editor" />
  </div>
</template>

<style lang="less" scoped>
.editor {
  width: 100%;
  height: 50vh;}
</style>
