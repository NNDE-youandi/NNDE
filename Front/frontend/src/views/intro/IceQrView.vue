<template>
  <div class="wrap-blue">
    <h1>큐알,핀</h1>
    <h2>PIN : {{ roomNumber }}</h2>
    <h2>{{ numberOfParticipant }} / {{ limitMember }}</h2>
    <img src="../../assets/next_btn.png" class="btn-img" @click="goSurvey" />
    <button @click="goBalance">Balance Game</button>
    <button v-if="isHost">방장보기</button>
    <button v-else>손님보기</button>
    <button @click="goLiar">라이어 게임 가기</button>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const roomNumber = ref();
    const numberOfParticipant = ref();
    const limitMember = ref();
    const isHost = ref();
    const checkParticipants = () => {
      $socket.on("checkParticipant", (data) => {
        roomNumber.value = data.roomNumber;
        limitMember.value = data.limitMember;
        numberOfParticipant.value = [...data.participant].length;
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
    // Balance Game
    const goBalance = () => {
      $socket.emit("goBalance");
    };
    $socket.on("moveBalancePage", (url) => {
      router.push({ name: url });
    });
    const checkHost = () => {
      $socket.emit("getId");
    };
    const receiveId = () => {
      //host면 true, guest면 false를 받아옴
      $socket.on("receiveId", (data) => {
        isHost.value = data;
      });
    };
    const goLiar = () => {
      $socket.emit("goLiar");
    };
    $socket.on("moveLiarPage", (url) => {
      router.push({ name: url });
    });
    checkHost();
    receiveId();
    //여기까지
    return {
      isHost,
      goSurvey,
      roomNumber,
      numberOfParticipant,
      limitMember,
      goBalance,
      goLiar,
    };
  },
};
</script>

<style></style>
