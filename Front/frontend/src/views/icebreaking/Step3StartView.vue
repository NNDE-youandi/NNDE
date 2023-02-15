<template>
  <div class="wrap-blue">
    <h4>Step3</h4>
    <h3>밸런스게임</h3>
    <div class="explanation">
      <p>고르기 어려운 두 가지의 선택지에 대해서 친구들과 얘기해보아요!</p>
    </div>
    <img src="../../assets/start_btn.png" class="btn-img" @click="goBalance" />
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
      $socket.emit("goBalanceStage");
    };
    const getBalanceUrl = () => {
      $socket.on("moveBalanceStagePage", (url) => {
        router.push({ name: url });
      });
    };
    getBalanceUrl();
    return {
      goBalance,
    };
  },
};
</script>
  
<style>
</style>