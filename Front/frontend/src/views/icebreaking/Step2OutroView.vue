<template>
  <div class="wrap-blue">
    <h4>이 문제의 주인공은</h4>
    <h2>{{ result }}</h2>
    <h3>입니다</h3>
    <div v-if="isHost">
      <div v-if="isDone">
        <img
          src="../../assets/next_btn.png"
          class="btn-img"
          @click="goBalance"
        />
      </div>
      <div v-else>
        <div
          style="margin-top: 40px"
          class="check-answer"
          @click="goStep2Start"
        >
          다음 퀴즈로
        </div>
      </div>
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
    const result = ref();
    const isDone = ref(false);
    const isHost = ref();

    const getIsHost = () => {
      $socket.emit("getIsHost");
    };
    getIsHost();

    const sendIsHost = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    sendIsHost();

    const callSurveyQuizResult = () => {
      $socket.emit("callSurveyQuizResult");
    };
    callSurveyQuizResult();

    const resSurveytQuizResult = () => {
      $socket.on("resSurveytQuizResult", (data) => {
        result.value = data;
      });
    };
    resSurveytQuizResult();

    const callSurveyIndex = () => {
      $socket.emit("callSurveyIndex");
    };
    callSurveyIndex();

    const resSurveyIndex = () => {
      $socket.on("resSurveyIndex", (index) => {
        if (index === 3) {
          isDone.value = true;
        }
      });
    };
    resSurveyIndex();

    const goBalance = () => {
      $socket.emit("goBalance");
    };
    const getBalancetUrl = () => {
      $socket.on("moveBalancePage", (url) => {
        router.push({ name: url });
      });
    };
    getBalancetUrl();

    const goStep2Start = () => {
      $socket.emit("callStep2Count");
    };
    const getStep2StartUrl = () => {
      $socket.on("resStep2Count", (url) => {
        router.push({ name: url });
      });
    };
    getStep2StartUrl();
    return {
      goBalance,
      result,
      isDone,
      isHost,
      goStep2Start,
    };
  },
};
</script>

<style>
</style>