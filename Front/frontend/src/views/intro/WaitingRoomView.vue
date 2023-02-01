<template>
  <div class="wrap-blue">
    <h1>대기방</h1>
    <img
      src="../../assets/start_btn.png"
      class="btn-img"
      @click="goIceBreakingStart"
    />
  </div>
</template>

<script>
import router from "@/router";
import { useRoute } from "vue-router";
import { computed } from "vue";

export default {
  setup() {
    const route = useRoute()
    const testt = computed(() => route.params.test)
    console.log(testt.value)
    const goIceBreakingStart = () => {
      router.push({ name: "IceBreakingStart" });
    };
    return {
      goIceBreakingStart,
    };
  },
};
</script>

<style>
</style>