<template>
  <div class="wrap-blue">
    <h1>큐알,핀</h1>
    <h2>PIN : {{ roomNumber }}</h2>
    <h2>{{ numberOfParticipant }} / {{ limitMember }}</h2>
    <img
      v-if="isHost"
      src="../../assets/next_btn.png"
      class="btn-img"
      @click="goNext"
    />
    <button @click="goBalance">Balance Game</button>
    <button v-if="isHost">방장보기</button>
    <button v-else>손님보기</button>
    <button @click="goLiar">라이어 게임 가기</button>
    <!-- subin 수정  -->
    <button @click="goKeywordIntroduce">키워드 자기소개 가기</button>
    <button @click="goStep2Start">step2. 나를 맞춰봐 가기</button>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
import { useRoute } from "vue-router";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const route = useRoute();
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
    const moveNextRoom = () => {
      $socket.on("moveNextRoom", () => {
        router.push({ name: route.params.modeName });
      });
    };
    moveNextRoom();
    const goNext = () => {
      $socket.emit("goNextRoom");
      router.push({ name: route.params.modeName });
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
    // [subin] keyword introduce
    const goKeywordIntroduce =()=>{
      $socket.emit("goKeywordIntroduce");
    }
    $socket.on("moveKeywordPage",(url)=>{
      router.push({name :url});
    })

    // [subin] step2Start : 나를 맞춰봐
    const goStep2Start =()=>{
      $socket.emit("goStep2Start");
    }
    $socket.on("moveStep2Start",(url)=>{
      router.push({name :url});
    })
    checkHost();
    receiveId();
    //여기까지
    return {
      isHost,
      goNext,
      roomNumber,
      numberOfParticipant,
      limitMember,
      goBalance,
      goLiar,
      // subin
      goKeywordIntroduce,
      goStep2Start,
    };
  },
};
</script>

<style></style>
