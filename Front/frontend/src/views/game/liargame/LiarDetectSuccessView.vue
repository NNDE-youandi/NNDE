<template>
  <div class="wrap-blue">
    <h4>게임 결과</h4>
    <div v-if="userId === liarId">
      <h3 style=" margin: 5vh 0;">들켰어요!</h3>
      <h5>생각하는 단어를 입력해주세요!</h5>
      <form style=" margin-top: 5vh; display: flex; height: 50px; " action="">
        <input style="margin-left:12vw; font-size: 24px; font-weight: bold; width: 200px" type="text" v-model="liarAnswer" />
        <img style="width: 70px" src="./../../../assets/enter_btn.png" alt="enter">
      </form>
    </div>
    <div v-else>
      <h3 style=" margin: 5vh 0;">찾았어요!!</h3>
      <h4>라이어는 {{ liarId }}이에요!</h4>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const userId = ref("");
    const liarId = ref("");
    const liarWord = ref("");
    const whoAmI = ref("");
    const liarAnswer = ref("");
    //라이어게임 결과 데이터 받아오기
    const requestLiar = () => {
      $socket.emit("requestId");
    };
    const receiveLiar = () => {
      $socket.on("LiarIdData", (liarid, liarword) => {
        liarWord.value = liarword;
        liarId.value = liarid;
      });
      $socket.on("sendId", (data) => {
        userId.value = data;
      });
    };
    //checkAnswer => 마지막 페이지에 liarWord, liarAnswer, liarId를 보낼 수 있음.
    const checkAnswer = () => {
      $socket.emit(
        "checkAnswer",
        liarWord.value,
        liarAnswer.value,
        liarId.value
      );
    };
    //liarResult를 통해 router push를 해줌.
    const goLastPage = () => {
      $socket.on("liarResult", (url) => {
        router.push({ name: url });
      });
    };

    requestLiar();
    receiveLiar();
    goLastPage();
    return {
      userId,
      liarId,
      liarWord,
      whoAmI,
      liarAnswer,
      checkAnswer,
    };
  },
};
</script>

<style>
</style>