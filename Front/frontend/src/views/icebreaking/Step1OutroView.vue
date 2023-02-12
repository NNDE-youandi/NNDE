<template>
  <div class="wrap-blue">
    <h1>이제 좀 친해졌니?</h1>
    <img
      src="../../assets/next_btn.png"
      class="btn-img"
      @click="goStep2StartView"
    />
  </div>
</template>

<script>
import router from "@/router";
import { getCurrentInstance } from "vue";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
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
      goStep2StartView,
    };
  },
};
</script>

<style>
</style>