<template>
  <div class="wrap-blue">
    <h1>큐알,핀</h1>
    <!-- <h2>{{ $route.params.roomNumber }}</h2> -->
    <img src="../../assets/next_btn.png" class="btn-img" @click="goSurvey" />
  </div>
</template>

<script>
import router from "@/router";
import { useRoute } from "vue-router";
import { computed } from "vue";
export default {
  setup() {
    const route = useRoute()
    const goSurvey = () => {
      router.push({ name: "Survey" });
    };
    const roomNumber = computed(() => route.params.roomNumber)
    console.log(roomNumber.value)
    return {
      goSurvey,
      roomNumber,
    };
  },
};
</script>

<style>
</style>