<template>
  <div class="wrap-blue">
    <h1>Step2. 나를 맞춰봐 (설문관련퀴즈)</h1>
<<<<<<< HEAD
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
=======
    <h2> nickname : {{quizSurvey.QS.nickname  }}</h2>
    <h2>randomNick : {{ randomNick }} , teamMember :{{ teamMember }}</h2>
    <!-- <button @click="quiz"></button> -->
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
  </div>
</template>

<script>
import {ref,getCurrentInstance} from "vue";
import {requestSurveyQuiz} from "@/api/quizApi.js";
<<<<<<< HEAD
import { useStore } from "vuex";
// import router from "@/router";

=======
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
<<<<<<< HEAD
    const store = useStore();
=======
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3

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
<<<<<<< HEAD
      
=======
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
      $socket.emit("getTeamMember")
    }
    getTeamMember()

<<<<<<< HEAD
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
=======
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
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3

    };
  },

}
</script>

<style>

</style>