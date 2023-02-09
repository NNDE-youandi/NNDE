<template>
  <div class="wrap-blue">
    <h1>👩‍🏫</h1>
    <div class="wrap-qr-code">
      <img class="qr-code" src="./../../assets/home_QR.png" alt="qr-code" />
    </div>
    <h3>PIN : {{ roomNumber }}</h3>
    <h3>🐧 {{ numberOfParticipant }} / {{ limitMember }}</h3>
    <h2 v-if="!isHost">인원 수를 모아주세요!</h2>
    <img
      v-else-if="isHost && numberOfParticipant !== limitMember"
      src="../../assets/next_btn_disable.png"
      class="btn-img"
    />
    <img
      v-else
      src="../../assets/next_btn.png"
      class="btn-img"
      @click="goNext"
    />
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
      $socket.on("resCheckParticipant", (data) => {
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
      $socket.on("resMoveNextRoom", () => {
        router.push({ name: route.params.modeName });
      });
    };
    moveNextRoom();
    const goNext = () => {
      $socket.emit("callMoveNextRoom");
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
      $socket.emit("getIsHost");
    };
    const receiveId = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    // [subin] keyword introduce
    const goKeywordIntroduce = () => {
      $socket.emit("goKeywordIntroduce");
    };
    $socket.on("moveKeywordPage", (url) => {
      router.push({ name: url });
    });

    // [subin] step2Start : 나를 맞춰봐
    const goStep2Start = () => {
      $socket.emit("goStep2Start");
    };
    $socket.on("moveStep2Start", (url) => {
      router.push({ name: url });
    });
    checkHost();
    receiveId();
    return {
      isHost,
      roomNumber,
      numberOfParticipant,
      limitMember,
      goNext,
      goBalance,
      goKeywordIntroduce,
      goStep2Start,
    };
  },
};
</script>

<style scoped>
.wrap-qr-code {
  margin: 20px auto;
}
.qr-code {
  display: block;
  margin: 0 auto;
  width: 30vh;
  height: 30vh;
  border: solid black 4px;
  border-radius: 10%;
}
</style>
