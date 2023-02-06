<template>
  <div class="wrap-blue">
    <h1>너나들이</h1>
    <img src="../../assets/ani3.png" class="img-ani3" />
    <div class="wrap-input-pin">
      <span> PIN: </span>
      <input type="number" id="pin_num" v-model="inputPin" />
      <img src="../../assets/enter_btn.png" @click="submitPin" />
    </div>
    <img src="../../assets/makeroom_btn.png" class="btn-img" @click="goLogin" />
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";

export default {
  setup() {
    const inputPin = ref("");
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    // 차후 로그인 여부에 따라 페이지 이동 변화
    const goLogin = () => {
      // if (true) {
      router.push({ name: "SelectMode" });
      // } else {
      // router.push({ name: "Login" });
      // }
    };
    const submitPin = () => {
      $socket.emit("submitPin", {
        pin: inputPin.value,
      });
    };
    const movePinRoom = () => {
      $socket.on("movePinRoom", (data) => {
        console.log(data.modeName);
        router.push({ name: "IceQr", params: { modeName: data.modeName } });
        $socket.emit("callCheckParticipant");
      });
    };
    movePinRoom();
    const noRoom = () => {
      $socket.on("noRoom", () => {
        window.alert("PIN 번호를 다시 입력해주세요");
      });
    };
    noRoom();
    return {
      goLogin,
      inputPin,
      submitPin,
    };
  },
};
</script>

<style>
</style>
