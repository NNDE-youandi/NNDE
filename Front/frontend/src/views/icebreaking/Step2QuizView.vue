<template>
  <div class="wrap-blue">
    <h4>Step2</h4>
    <h3>나를 찾아봐</h3>
    <h4> ____ 님이 {{ survey }} 무엇입니까 ? 의 
      대답을 {{ answer }} 로 하셨습니다.
      ____ 님은 누구일까요 ?
    </h4>
    <!-- css 어케해 ? -->
    <h3 v-for="(item,index) in teamMember" :key="index"> {{item}} </h3>
    <!-- <div>
      <ul>
        <li v-for="(item,index) in teamMember" :key="index">
          {{ item }}
        </li>
      </ul>
    </div> -->
  
    <!-- <h2> 정답 nickname : {{quizSurvey.QS.nickname  }}</h2> -->
    <!-- 설문관련 퀴즈 정답 알아보는 창으로 넘어가기  -->
    <div v-if="isHost">
      <button @click="goStep2Outro"> 정답 알아보기</button>
    </div>
  </div>
</template>

<script>
import {ref,getCurrentInstance} from "vue";
import router from "@/router";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;

    const teamMember = ref()
    const randomNick = ref()
    const survey = ref()
    const answer = ref()
    const nick = ref()
    const quizIndex = ref()
    const isHost = ref()

    // 방장 받아오기
    const getIsHost = () => {
      $socket.emit("getIsHost")
    }
    getIsHost()

    const sendIsHost = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data
      })
    }
    sendIsHost()

    // 통합 surveydata 받기
    const getSurveyQuizData = () => {
      $socket.emit("callSurveyQuizData")
    }
    getSurveyQuizData()
   
    const resSurveyQuizData = () => {
      $socket.on("resSurveyQuizData", (data) => {
        console.log(data)
        nick.value = data[0]
        answer.value = data[1]
        survey.value = data[2]
      })
    }
    resSurveyQuizData()

    // 인덱스 값 변경해주기
    const getSurveyIndex = () => {
      $socket.emit("callSurveyIndex")
    }
    getSurveyIndex()
    const resSurveyIndex = () => {
      $socket.on("resSurveyIndex", (index) => {
        quizIndex.value = index+1
      })
    }
    resSurveyIndex()
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

    const goStep2Outro = () =>{
      $socket.emit("callStep2Outro")
      $socket.emit("plusSurveyIndex", (quizIndex.value))
    }
    const resStep2OutroUrl = () => {
      $socket.on("resStep2Outro", (url) => {
        router.push({name:url})
      })
    }
    resStep2OutroUrl()
    return{
      teamMember,
      randomNick,
      goStep2Outro,
      survey,
      answer,
      isHost

    };
  },

}
</script>

<style>

</style>