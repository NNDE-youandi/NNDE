<template>
  <div class="wrap-blue">
    <h2>Step3 밸런스게임</h2>
    <img
      src="../../assets/start_btn.png"
      class="btn-img"
      @click="goBalance"
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
    const goBalance = () => {
      $socket.emit("goBalance")
			
		}
    const getBalanceUrl = () => {
      $socket.on("moveBalancePage", (url) => {
        router.push({name: url })
      })
    }
    getBalanceUrl()
    return {
        goBalance,
    };
  },
};
</script>
  
  <style>
</style>