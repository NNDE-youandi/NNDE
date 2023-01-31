<template>
  <div class="wrap-blue">
    <h1>설문</h1>
    <img
      src="../../assets/submit_btn.png"
      class="btn-img"
      @click="goWaitingRoom"
    />
  </div>
</template>
<script>
import router from "@/router";

export default {
  components: {},
  setup() {
    const goWaitingRoom = () => {
      router.push({ name: "WaitingRoom" });
    };
    return {
      goWaitingRoom,
    };
  },
};
</script>