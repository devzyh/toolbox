<script setup lang="ts">
import { onMounted, ref } from 'vue';
import CodeMirror from 'codemirror';
import * as DiffMatchPatch from 'diff-match-patch';
import 'codemirror/addon/merge/merge';
import 'codemirror/lib/codemirror.css';
import 'codemirror/addon/merge/merge.css';

// 注册 diff_match_patch 到全局对象
window.diff_match_patch = DiffMatchPatch.diff_match_patch;
window.DIFF_DELETE = DiffMatchPatch.DIFF_DELETE;
window.DIFF_INSERT = DiffMatchPatch.DIFF_INSERT;
window.DIFF_EQUAL = DiffMatchPatch.DIFF_EQUAL;

const leftText = ref('');
const rightText = ref('');
const diffView = ref(null);

function initUI() {
  if (diffView.value) {
    CodeMirror.MergeView(diffView.value, {
      value: leftText.value,
      orig: rightText.value,
      lineNumbers: true,
      highlightDifferences: true,
      collapseIdentical: false,
      allowEditingOriginals: true,
    });
  }
};

onMounted(() => {
  initUI();
});
</script>

<template>
  <div class="w-full">
    <div ref="diffView" class="diff" />
  </div>
</template>

<style lang="less" scoped>
.diff {
  width: 100%;
  height: auto;

  ::v-deep(.CodeMirror),
  ::v-deep(.CodeMirror-merge) {
    height: 82.5vh;
  }
}
</style>
