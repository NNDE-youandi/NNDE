<template>
    <div class="wrap-blue">
      <h2>Step4 라이어게임</h2>
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

    //ice에서 라이어게임으로 이동
    const goLiarGame = () => {
      $socket.emit("callLiarGame")
			
		}
    const getLiarGameUrl = () => {
      $socket.on("resLiarGame", (url) => {
        router.push({name: url , params: {modeName: "LiarThemeList"} })
      })
    }
    getLiarGameUrl()


      return {
        isHost,
        goLiarGame

      };
    },
  };
  </script>
    
    <style>
  </style>