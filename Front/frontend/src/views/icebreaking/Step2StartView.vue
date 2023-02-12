<template>
  <div class="wrap-blue">
    <h1>Step2 날 찾아봐!</h1>
    <img src="../../assets/start_btn.png" class="btn-img" @click="goStep2CountView">
  </div>
</template>

<script>
import router from '@/router';
import { getCurrentInstance } from "vue";
export default {

  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
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
      goStep2CountView,
      
    }
  }

}
</script>

<style>

</style>