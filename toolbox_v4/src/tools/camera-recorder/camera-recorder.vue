<script setup lang="ts">
import _ from 'lodash';

import { useMediaRecorder } from './useMediaRecorder';
import { translate } from '@/plugins/i18n.plugin';

interface Media { type: 'image' | 'video'; value: string; createdAt: Date }

const {
  videoInputs: cameras,
  audioInputs: microphones,
  permissionGranted,
  isSupported,
  ensurePermissions,
} = useDevicesList({
  requestPermissions: true,
  constraints: { video: true, audio: true },
  onUpdated() {
    refreshCurrentDevices();
  },
});

const video = ref<HTMLVideoElement>();
const medias = ref<Media[]>([]);
const currentCamera = ref(cameras.value[0]?.deviceId);
const currentMicrophone = ref(microphones.value[0]?.deviceId);
const permissionCannotBePrompted = ref(false);

const {
  stream,
  start,
  stop,
  enabled: isMediaStreamAvailable,
} = useUserMedia({
  constraints: computed(() => ({
    video: { deviceId: currentCamera.value },
    ...(currentMicrophone.value ? { audio: { deviceId: currentMicrophone.value } } : {}),
  })),
  autoSwitch: true,
});

const {
  isRecordingSupported,
  onRecordAvailable,
  startRecording,
  stopRecording,
  pauseRecording,
  recordingState,
  resumeRecording,
} = useMediaRecorder({
  stream,
});

onRecordAvailable((value) => {
  medias.value.unshift({ type: 'video', value, createdAt: new Date() });
});

function refreshCurrentDevices() {
  if (_.isNil(currentCamera) || !cameras.value.find(i => i.deviceId === currentCamera.value)) {
    currentCamera.value = cameras.value[0]?.deviceId;
  }

  if (_.isNil(microphones) || !microphones.value.find(i => i.deviceId === currentMicrophone.value)) {
    currentMicrophone.value = microphones.value[0]?.deviceId;
  }
}

function takeScreenshot() {
  if (!video.value) {
    return;
  }

  const canvas = document.createElement('canvas');
  canvas.width = video.value.videoWidth;
  canvas.height = video.value.videoHeight;
  canvas.getContext('2d')?.drawImage(video.value, 0, 0);
  const image = canvas.toDataURL('image/png');

  medias.value.unshift({ type: 'image', value: image, createdAt: new Date() });
}

watchEffect(() => {
  if (video.value && stream.value) {
    video.value.srcObject = stream.value;
  }
});

onBeforeUnmount(() => stop());

async function requestPermissions() {
  try {
    await ensurePermissions();
  }
  catch (e) {
    permissionCannotBePrompted.value = true;
  }
}

function downloadMedia({ type, value, createdAt }: Media) {
  const link = document.createElement('a');
  link.href = value;
  link.download = `${type}-${createdAt.getTime()}.${type === 'image' ? 'png' : 'webm'}`;
  link.click();
}
</script>

<template>
  <div class="mw-600">
    <c-card v-if="!isSupported">
      {{ translate('tools.camera-recorder.not-supported') }}
    </c-card>

    <c-card v-else-if="!permissionGranted" text-center>
      {{ translate('tools.camera-recorder.permission-needed') }}

      <c-alert v-if="permissionCannotBePrompted" mt-4 text-left>
        {{ translate('tools.camera-recorder.permission-blocked') }}
      </c-alert>

      <div v-else mt-4 flex justify-center>
        <c-button @click="requestPermissions">
          {{ translate('tools.camera-recorder.grant-permission') }}
        </c-button>
      </div>
    </c-card>

    <c-card v-else>
      <div flex flex-col gap-2>
        <c-select
          v-model:value="currentCamera"
          label-position="left"
          label-width="60px"
          :label="translate('tools.camera-recorder.video')"
          :options="cameras.map(({ deviceId, label }) => ({ value: deviceId, label }))"
        />
        <c-select
          v-if="currentMicrophone && microphones.length > 0"
          v-model:value="currentMicrophone"
          label-position="left"
          label-width="60px"
          :label="translate('tools.camera-recorder.audio')"
          :options="microphones.map(({ deviceId, label }) => ({ value: deviceId, label }))"
        />
      </div>

      <div v-if="!isMediaStreamAvailable" mt-3 flex justify-center>
        <c-button type="primary" @click="start">
          {{ translate('tools.camera-recorder.start-webcam') }}
        </c-button>
      </div>

      <div v-else>
        <div my-2>
          <video ref="video" autoplay controls playsinline max-h-full w-full />
        </div>

        <div flex items-center justify-between gap-2>
          <c-button :disabled="!isMediaStreamAvailable" @click="takeScreenshot">
            <span mr-2> <icon-mdi-camera /></span>
            {{ translate('tools.camera-recorder.take-screenshot') }}
          </c-button>

          <div v-if="isRecordingSupported" flex justify-center gap-2>
            <c-button v-if="recordingState === 'stopped'" @click="startRecording">
              <span mr-2> <icon-mdi-video /></span>
              {{ translate('tools.camera-recorder.start-recording') }}
            </c-button>

            <c-button v-if="recordingState === 'recording'" @click="pauseRecording">
              <span mr-2> <icon-mdi-pause /></span>
              {{ translate('tools.camera-recorder.pause') }}
            </c-button>

            <c-button v-if="recordingState === 'paused'" @click="resumeRecording">
              <span mr-2> <icon-mdi-play /></span>
              {{ translate('tools.camera-recorder.resume') }}
            </c-button>

            <c-button v-if="recordingState !== 'stopped'" type="error" @click="stopRecording">
              <span mr-2> <icon-mdi-record /></span>
              {{ translate('tools.camera-recorder.stop') }}
            </c-button>
          </div>
          <div v-else italic op-60>
            {{ translate('tools.camera-recorder.recording-not-supported') }}
          </div>
        </div>
      </div>
    </c-card>

    <div grid grid-cols-2 mt-5 gap-2>
      <c-card v-for="({ type, value, createdAt }, index) in medias" :key="index">
        <img v-if="type === 'image'" :src="value" max-h-full w-full alt="screenshot">

        <video v-else :src="value" controls max-h-full w-full />

        <div flex items-center justify-between>
          <div font-bold>
            {{ type === 'image' ? translate('tools.camera-recorder.screenshot') : translate('tools.camera-recorder.video') }}
          </div>

          <div flex gap-2>
            <c-button @click="downloadMedia({ type, value, createdAt })">
              <icon-mdi-download />
            </c-button>

            <c-button @click="medias = medias.filter((_ignored, i) => i !== index)">
              <icon-mdi-delete-outline />
            </c-button>
          </div>
        </div>
      </c-card>
    </div>
  </div>
</template>
