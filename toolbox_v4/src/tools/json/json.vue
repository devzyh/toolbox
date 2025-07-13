<script setup lang="ts">
import JSONEditor from 'jsoneditor';
import 'jsoneditor/dist/jsoneditor.css';
import 'ace-builds/src-min-noconflict/ace';
import 'ace-builds/src-min-noconflict/mode-json';
import 'ace-builds/src-min-noconflict/ext-searchbox';
import 'ace-builds/src-min-noconflict/ext-language_tools';
import { onMounted, ref } from 'vue';
import { translate } from '@/plugins/i18n.plugin';

const editorRef = ref<HTMLElement | null>(null);
let editor: JSONEditor | null = null;
const key = 'dz-tools:json';

/**
 * JSON 字符串处理
 * @param type 处理类型
 * 2: 转义 JSON 字符串
 * 4: 去除 JSON 字符串转义符
 */
function optJson(type: number) {
  if (!editor) {
    return;
  }

  let text = editor.getText();
  text = text.replace(/[\r\n]/g, ''); // 去除换行符

  if (type === 2) {
    // 转义 JSON 字符串
    text = text.replace(/\\/g, '\\\\').replace(/\"/g, '\\"');
  }
  else if (type === 4) {
    // 去除 JSON 字符串转义符
    text = text.replace(/\\\\/g, '\\').replace(/\\\"/g, '"');
  }

  editor.setText(text);
}

/**
 * 附加功能按钮
 */
function attachButtons() {
  if (editorRef.value == null) {
    return;
  }
  const buttons = editorRef.value.querySelectorAll('button.jsoneditor-redo');
  buttons.forEach((button) => {
    const clearButton = document.createElement('button');
    clearButton.type = 'button';
    clearButton.className = 'jsoneditor-clear';
    clearButton.title = translate('tools.json.clearButton');
    clearButton.addEventListener('click', () => {
      if (editor) {
        editor.setText('');
        localStorage.setItem(key, '');
      }
    });
    button.after(clearButton);

    const escapeButton = document.createElement('button');
    escapeButton.type = 'button';
    escapeButton.className = 'jsoneditor-escape';
    escapeButton.title = translate('tools.json.escapeButton');
    escapeButton.addEventListener('click', () => {
      optJson(2);
    });
    button.after(escapeButton);

    const restoreButton = document.createElement('button');
    restoreButton.type = 'button';
    restoreButton.className = 'jsoneditor-restore';
    restoreButton.title = translate('tools.json.restoreButton');
    restoreButton.addEventListener('click', () => {
      optJson(4);
    });
    button.after(restoreButton);
  });
}

onMounted(() => {
  if (editorRef.value) {
    editor = new JSONEditor(editorRef.value, {
      mode: 'code',
      onChange: () => {
        if (editor) {
          localStorage.setItem(key, editor.getText());
        }
      },
    });

    editor.aceEditor.setOption('useWorker', false);

    attachButtons();

    const savedData = localStorage.getItem(key) || '';
    editor.setText(savedData);
  }
});
</script>

<template>
  <div ref="editorRef" class="jsoneditor-container" />
</template>

<style lang="less" scoped>
.jsoneditor-container {
  width: 100%;
  height: 82.5vh;
}

::v-deep(.jsoneditor) {
  border-color: #2e9968;

  .jsoneditor-menu {
    background-color: #2e9968;
    border-bottom: 1px solid #2e9968;

    button {
      cursor: pointer;
    }

    .jsoneditor-restore {
      background-position: -72px 0px;
    }

    .jsoneditor-escape {
      background-position: -144px 0px;
    }

    .jsoneditor-clear {
      background-position: -24px 0px;
    }

    @media (max-width: 768px) {
      .jsoneditor-poweredBy {
        display: none;
      }
    }
  }

  .jsoneditor-outer {
    .ace-jsoneditor .ace_text-layer {
      color: rgb(51, 54, 57);
    }
  }
}
</style>
