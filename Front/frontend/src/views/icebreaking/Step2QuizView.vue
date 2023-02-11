<template>
  <div class="wrap-blue">
    <h1>Step2. 나를 맞춰봐 (설문관련퀴즈)</h1>
    <h2> ____ 님이 {{ quizSurvey.QS.survey }} 무엇입니까 ? 의 
      대답을 {{ quizSurvey.QS.answer }} 로 하셨습니다.
      ____ 님을 맞춰보세요 !
    </h2>
    <!-- css 어케해 ? -->
    <!-- <h2 v-for="(item,index) in teamMember" :key="index"> {{item}} </h2> -->
    <div>
      <ul>
        <li v-for="(item,index) in teamMember" :key="index">
          {{ item }}
        </li>
      </ul>
    </div>
  
    <h2> 정답 nickname : {{quizSurvey.QS.nickname  }}</h2>
    <!-- 설문관련 퀴즈 정답 알아보는 창으로 넘어가기  -->
    <!-- <button @click="goStep2Qutro"> 정답 알아보기</button> -->
  </div>
</template>

<script>
import {ref,getCurrentInstance} from "vue";
import {requestSurveyQuiz} from "@/api/quizApi.js";
import { useStore } from "vuex";
// import router from "@/router";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const store = useStore();

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

    const clearQuizResult = () => store.commit("iceBreakingStore/CLEAR_QUIZ_RESULT");



    const quiz =() =>{
      $socket.on("sendTeamMember", (teammember, randomid) => {
        teamMember.value = teammember
        randomNick.value = randomid
     
        // console.log("randomNick.value : ",randomid);
        clearQuizResult();
        const nickname = {nickname : randomid};
        requestSurveyQuiz(nickname,(res)=>{
          
          quizSurvey.value.QS.nickname =res.data.nickname;
          quizSurvey.value.QS.answer = res.data.answer;
          quizSurvey.value.QS.survey = res.data.survey;

          // iceBreakingStore , QuizResult 값 저장
          const setQuizResult = () => store.commit("iceBreakingStore/SET_QUIZ_RESULT",quizSurvey.value.QS.nickname);

          setQuizResult();
        
        })
     })
    }
    quiz();

    // const goStep2Qutro = () =>{
    //   router.push({name : "Step2Outro"});
    // }
    return{
      quiz,
      quizSurvey,
      teamMember,
      randomNick,
      // goStep2Qutro

    };
  },

}
</script>

<style>

</style>