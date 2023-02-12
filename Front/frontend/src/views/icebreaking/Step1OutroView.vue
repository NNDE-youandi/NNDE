<template>
  <div class="wrap-blue">
    <h1>이제 좀 친해졌니?</h1>
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
    const isHost = ref('')

    //host 여부 조회
    const checkHost = () => {
      $socket.emit("getIsHost");
    };
    const receiveId = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    checkHost()
    receiveId()
    const goStep2StartView = () => {
      $socket.emit("callStep2Start")
			
		}
    const getStep2StartUrl = () => {
      $socket.on("resStep2Start", (url) => {
        router.push({name:url})
      })
    }
    getStep2StartUrl()
    return {
      isHost,
      goStep2StartView,
    };
  },
};
</script>

<style>
</style>