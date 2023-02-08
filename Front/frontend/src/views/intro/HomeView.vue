<template>
  <div class="wrap-blue">
    <h1>너나들이</h1>
    <img src="../../assets/ani3.png" class="img-ani3" />
    <div v-if="isLogin">
      <div class="wrap-input-pin">
        <span> PIN: </span>
        <input type="number" id="pin_num" v-model="inputPin" />
        <img src="../../assets/enter_btn.png" @click="submitPin" />
      </div>
      <img
        src="../../assets/makeroom_btn.png"
        class="btn-img"
        @click="goSelectMode"
      />
    </div>
    <div v-else>
      <div @click="goLogin" class="game-btn">Login</div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const inputPin = ref("");
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const store = useStore();
    const isLogin = ref(store.state.userStore.isLogin);
    // 차후 로그인 여부에 따라 페이지 이동 변화
    const goSelectMode = () => {
      router.push({ name: "SelectMode" });
    };
    const goLogin = () => {
      router.push({ name: "Login" });
    };
    const submitPin = () => {
      $socket.emit("submitPin", {
        pin: inputPin.value,
      });
    };
    const movePinRoom = () => {
      $socket.on("movePinRoom", (data) => {
        router.push({
          name: "RoomWaiting",
          params: { modeName: data.modeName },
        });
        $socket.emit("callCheckParticipant");
      });
    };
    const noRoom = () => {
      $socket.on("noRoom", () => {
        window.alert("PIN 번호를 다시 입력해주세요");
      });
    };
    movePinRoom();
    noRoom();
    return {
      goLogin,
      goSelectMode,
      inputPin,
      submitPin,
      isLogin,
    };
  },
};
</script>

<style scoped>
.game-btn {
  padding: 100px;
  margin: 50px auto;
  background-color: rgb(225, 217, 51);
  font-size: 48px;
  text-align: center;
  width: 60%;
  color: #ffffff;
  font-family: bitbit;
  text-shadow: 0px 3px 2px #f73c89;
}
</style>
