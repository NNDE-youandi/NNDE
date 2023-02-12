<template>
  <div class="wrap-blue">
    <h1>Step2 날 찾아봐!</h1>
    <div v-if="isHost">
      <img src="../../assets/start_btn.png" class="btn-img" @click="goStep2CountView">
    </div>
  </div>
</template>

<script>
import router from '@/router';
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
    const goStep2CountView = () => {
      $socket.emit("callStep2Count")
			
		}
    const getStep2CounttUrl = () => {
      $socket.on("resStep2Count", (url) => {
        router.push({name:url})
      })
    }
    getStep2CounttUrl()
    return {
      isHost,
      goStep2CountView,
      
    }
  }

}
</script>

<style>

</style>