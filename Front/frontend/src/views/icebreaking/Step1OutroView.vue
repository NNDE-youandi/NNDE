<template>
  <div class="wrap-blue">
    <h3>이제 좀 친해졌니?</h3>
    <img src="../../assets/ani_1.png" alt="giraffe" class="giraffe" />
    <div v-if="isHost">
      <img
        src="../../assets/next_btn.png"
        class="btn-img"
        @click="goStep2StartView"
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
    const isHost = ref("");
    const checkHost = () => {
      $socket.emit("getIsHost");
    };
    const receiveId = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    checkHost();
    receiveId();
    const goStep2StartView = () => {
      $socket.emit("callStep2Start");
    };
    const getStep2StartUrl = () => {
      $socket.on("resStep2Start", (url) => {
        router.push({ name: url });
      });
    };
    getStep2StartUrl();
    return {
      isHost,
      goStep2StartView,
    };
  },
};
</script>

<style>
.giraffe {
  width: 130px;
  display: block;
  margin: 0 auto;
}
</style>