<template>
  <div class="wrap-blue">
    <h4>Step4</h4>
    <h3>라이어게임</h3>
    <div class="explanation">
      <p>
        누군가가 다른 단어를 가지고 있지만 같은 단어를 가지고 있다고 거짓말 하고
        있어요! 라이어를 찾아주세요! 단, 라이어에게 단어에 대한 힌트를 너무 주면
        안 돼요!
      </p>
    </div>
    <div v-if="isHost">
      <img
        src="../../assets/start_btn.png"
        class="btn-img"
        @click="goLiarGame"
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

    const goLiarGame = () => {
      $socket.emit("callLiarGame");
    };
    const getLiarGameUrl = () => {
      $socket.on("resLiarGame", (url) => {
        router.push({ name: url, params: { modeName: "LiarThemeList" } });
      });
    };
    getLiarGameUrl();

    return {
      isHost,
      goLiarGame,
    };
  },
};
</script>
    
<style>
</style>