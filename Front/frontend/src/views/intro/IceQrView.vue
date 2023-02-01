<template>
  <div class="wrap-blue">
    <h1>큐알,핀</h1>
    <!-- <h2>{{ $route.params.roomNumber }}</h2> -->
    <img src="../../assets/next_btn.png" class="btn-img" @click="goSurvey" />
  </div>
</template>

<script>
import router from "@/router";
export default {
  setup() {
    const goSurvey = () => {
      router.push({ name: "Survey" });
    };
    return {
      goSurvey,
    };
  },
};
</script>

<style>
</style>