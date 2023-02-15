<template>
  <div class="wrap-blue">
    <h4>Step1</h4>
    <h3>나를 소개해봐</h3>
    <div class="explanation">
      <p>
        자신의 순서가 되면 화면에 보이는 키워드를 통해 30초씩 자기소개를
        해보아요! 시간이 남는다면 자신의 다른 부분을 소개해도 좋아요!
      </p>
    </div>
    <div v-if="isHost">
      <img
        src="../../assets/start_btn.png"
        class="btn-img"
        @click="startStep1"
      />
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const isHost = ref();

    const getIsHost = () => {
      $socket.emit("getIsHost");
    };
    getIsHost();
    const sendIsHost = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    sendIsHost();
    const startStep1 = () => {
      $socket.emit("callStep1Count");
    };
    const getStep1CountUrl = () => {
      $socket.on("resStep1Count", (url) => {
        router.push({ name: url });
      });
    };
    getStep1CountUrl();

    return {
      startStep1,
      isHost,
    };
  },
};
</script>

<style>
</style>