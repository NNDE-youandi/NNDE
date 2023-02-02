<template>
  <div class="wrap-blue">
    <h1>큐알,핀</h1>
    <h2>PIN : {{ roomNumber }}</h2>
    <h2>{{ numberOfParticipant }} / 6</h2>
    <img src="../../assets/next_btn.png" class="btn-img" @click="goSurvey" />
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";

export default {
  setup() {
    // const store = useStore();
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    // const roomNumber = ref(store.state.iceBreakingStore.roomNumber);
    const roomNumber = ref()
    const numberOfParticipant = ref()
    const checkParticipants = () => {
      $socket.on("checkParticipant", (data) => {
        roomNumber.value = data.roomNumber
        numberOfParticipant.value = [...data.participant].length
      });
    };
    const callCheckParticipant = () => {
      $socket.emit("callCheckParticipant");
    };
    checkParticipants();
    callCheckParticipant();

    const goSurvey = () => {
      router.push({ name: "Survey" });
    };
    return {
      goSurvey,
      roomNumber,
      numberOfParticipant,
    };
  },
};
</script>

<style>
</style>