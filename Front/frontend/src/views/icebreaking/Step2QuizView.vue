<template>
  <div class="wrap-blue">
    <h5>Step2</h5>
    <h4>나를 찾아봐</h4>
    <h4 style="margin: 50px 30px; text-align: left">
      {{ survey }} 이(가) {{ answer }} 인 사람은 누구일까요?
    </h4>
    <div v-if="isHost">
      <div @click="goStep2Outro" class="check-answer">정답 확인</div>
    </div>
    
  </div>
</template>

<script>
import { ref, getCurrentInstance } from "vue";
import router from "@/router";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const teamMember = ref();
    const randomNick = ref();
    const survey = ref();
    const answer = ref();
    const nick = ref();
    const quizIndex = ref();
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

    const getSurveyQuizData = () => {
      $socket.emit("callSurveyQuizData");
    };
    getSurveyQuizData();

    const resSurveyQuizData = () => {
      $socket.on("resSurveyQuizData", (data) => {
        nick.value = data[0];
        answer.value = data[1];
        survey.value = data[2];
      });
    };
    resSurveyQuizData();

    const getSurveyIndex = () => {
      $socket.emit("callSurveyIndex");
    };
    getSurveyIndex();
    const resSurveyIndex = () => {
      $socket.on("resSurveyIndex", (index) => {
        quizIndex.value = index + 1;
      });
    };
    resSurveyIndex();
    const goStep2Outro = () => {
      $socket.emit("callStep2Outro");
      $socket.emit("plusSurveyIndex", quizIndex.value);
    };
    const resStep2OutroUrl = () => {
      $socket.on("resStep2Outro", (url) => {
        router.push({ name: url });
      });
    };
    resStep2OutroUrl();
    return {
      teamMember,
      randomNick,
      goStep2Outro,
      survey,
      answer,
      isHost,
    };
  },
};
</script>

<style>
.check-answer {
  font-size: 30px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 1px black;
  background-color: rgb(77, 155, 224);
  text-align: center;
  width: 200px;
  margin: 0 auto;
  padding: 20px 0 20px 0;
  border-radius: 5%;
  border: 5px solid rgb(234, 229, 229);
}
</style>