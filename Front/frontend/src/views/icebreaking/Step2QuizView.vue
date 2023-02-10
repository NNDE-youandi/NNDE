<template>
  <div class="wrap-blue">
    <h1>Step2. 나를 맞춰봐 (설문관련퀴즈)</h1>
    <h2> nickname : {{quizSurvey.QS.nickname  }}</h2>
    <h2>randomNick : {{ randomNick }} , teamMember :{{ teamMember }}</h2>
    <!-- <button @click="quiz"></button> -->
  </div>
</template>

<script>
import {ref,getCurrentInstance} from "vue";
import {requestSurveyQuiz} from "@/api/quizApi.js";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;

    const teamMember = ref()
    const randomNick = ref()

    const quizSurvey = ref({
      QS:{
        nickname : "", // 정답 닉네임 
        answer : "", // 정답 답변 
        survey : "", // 정답 설문
      }
    })
    
// teamMember 요청
    const getTeamMember = () => {
      $socket.emit("getTeamMember")
    }
    getTeamMember()

    const sendTeamMember = () => {
      $socket.on("sendTeamMember", (teammember, randomid) => {
        teamMember.value = teammember
        randomNick.value = randomid
      })
    }
    sendTeamMember()

    // const output = ref([]);

    const quiz =() =>{
      console.log("randomNick : ",randomNick);
      console.log("randomNick.value : ",randomNick.value);
      const nickname = {nickname : randomNick};
      requestSurveyQuiz(nickname,(res)=>{
        console.log("step2QuizView : ",res);

        quizSurvey.value.QS.nickname =res.data.nickname;
        quizSurvey.value.QS.answer = res.data.answer;
        quizSurvey.value.QS.survey = res.data.survey;

        console.log("nickname =",quizSurvey.value.QS.nickname);
        console.log("answer =",quizSurvey.value.QS.answer);
        console.log("survey =",quizSurvey.value.QS.survey);


        an.value =res.data.nickname;
        nic.value = res.data.answer;
        sur.value = res.data.survey;


        console.log("an =",an.value);
        console.log("nic =",nic.value);
        console.log("sur =",sur.value);
        // output.value.push(quizSurvey.value.QS.nickname);
        // output.value.push(quizSurvey.value.QS.answer);
        // output.value.push(quizSurvey.value.QS.survey);

      })
      const an = ref()
      const nic = ref()
      const sur = ref()

      console.log("__nickname =",quizSurvey.value.QS.nickname);
      console.log("__answer =",quizSurvey.value.QS.answer);
      console.log("__survey =",quizSurvey.value.QS.survey);

      console.log("==================================");
      console.log("an =",an.value);
      console.log("nic =",nic.value);
      console.log("sur =",sur.value);
      
    }
    quiz();
    return{
      quiz,
      quizSurvey,

    };
  },

}
</script>

<style>

</style>