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
    const timerCount = ref(3);
    const isHost = ref('');
    //host 여부 조회
    const checkHost = () => {
      $socket.emit("getIsHost");
    };
    const receiveId = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    checkHost()
    receiveId()
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
    const teamMember = ref();
    const randomNick = ref();
    const getTeamMember = () => {
      $socket.emit("getTeamMember");
    };
    const sendTeamMember = () => {
      $socket.on("sendTeamMember", (teammember, randomid) => {
        teamMember.value = teammember;
        randomNick.value = randomid;
        
        // const surveyId = computed(()=>store.state.iceBreakingStore.surveyList);
        // console.log("surveyId : ",surveyId);
        let randomNumber =
        Math.floor(Math.random() * 3) + 1
        console.log(randomNumber)
        const MatchAnwserRequestDto = { nickname: randomid,surveyId:randomNumber };
        // console.log("api에 넣는 nickname" , nickname)
      requestSurveyQuiz(MatchAnwserRequestDto, (res) => {
        console.log("api res :",res.data)
        $socket.emit(
          "getSurveyQuizData",
          res.data.nickname,
          res.data.answer,
          res.data.survey,
        );
    });
      });
    };
    
    watch(isHost, () => {
      if (isHost.value) {
        getTeamMember();
        sendTeamMember();
      }
    })
    
    return {
      timerCount,
      teamMember,
      randomNick,
      isHost
    };
  },
};
</script>

<style>
</style>