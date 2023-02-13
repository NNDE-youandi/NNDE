<template>
  <div class="wrap-blue">
    <h3>step2</h3>
    <h2>나를 찾아봐</h2>
    <p class="count">{{ timerCount }}</p>
  </div>
</template>

<script>
import router from "@/router";
import { requestSurveyQuiz } from "@/api/quizApi.js";
import { ref, watch, getCurrentInstance } from "vue";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;

    const timerCount = ref(5);

    watch(
      timerCount,
      () => {
        if (timerCount.value > 0) {
          setTimeout(() => {
            timerCount.value--;
          }, 1000);
        } else {
          timerCount.value = "start";
          setTimeout(() => {
            router.push({ name: "Step2Quiz" });
          }, 1000);
        }
      },
      { immediate: true }
    );
    // Step2QuizView 에 있던 api 데이터 관련
    const teamMember = ref()
    const randomNick = ref()
    // teamMember 요청
    const getTeamMember = () => {
      
      $socket.emit("getTeamMember")
    }
    getTeamMember()


    const sendTeamMember =() =>{
      $socket.on("sendTeamMember", (teammember, randomid) => {
        teamMember.value = teammember
        randomNick.value = randomid
      })
    }
    sendTeamMember()
        
    watch(teamMember, () => {
      const nickname = {nickname : randomNick.value};
      requestSurveyQuiz(nickname,(res)=>{
        console.log("api data :", res.data)
      $socket.emit("getSurveyQuizData", res.data.nickname, res.data.answer, res.data.survey)
      })  
    })
    
    return {
      timerCount,
      teamMember,
      randomNick,
    }
  },
};
</script>

<style>
</style>