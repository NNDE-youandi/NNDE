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
export default {
  setup() {
    const goStep2StartView = () => {
      router.push({
        name: "Step2Start",
      });
    };
    return {
      goStep2StartView,
    };
  },
};
</script>

<style>
</style>