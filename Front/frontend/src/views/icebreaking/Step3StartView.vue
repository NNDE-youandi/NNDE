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

export default {
  setup() {
    const goBalance = () => {
      router.push({ name: "Balance" });
    };
    return {
        goBalance,
    };
  },
};
</script>
  
  <style>
</style>