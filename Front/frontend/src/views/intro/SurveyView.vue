<template>
  <div class="wrap-blue">
    <h1>설문</h1>
    <!-- subin 수정 : 설문조사 질문 , 답변 각각 저장하기 -->
    <div v-for="(item,index) in surveyList" :key="index">
      <h3>Q. 당신의 {{ surveyList[index] }} 무엇입니까 ?</h3>
      <input
        v-model="viewAnswer[index]"
        type="text"
        name="answer"
        id="answer"
        placeholder="답변을 작성해주세요."/>
    </div>
    <div>
      <p class="survey-answer" v-for="(item, index) in viewSurvey" :key="index" :data-answer-number="index"></p>
    </div>
    <!-- <p>설문조사 답안 : {{ viewAnswer }}</p>
    <p>{{userinfo[0].userId}}</p> -->
    <img
      src="../../assets/submit_btn.png"
      class="btn-img"
      @click="goWaitingRoom"
    />
  </div>
</template>
<script>
import router from "@/router";
import { requestSurvey, requestAnswer } from '@/api/SurveyApi';
import {computed, ref} from 'vue';
import { useStore } from "vuex";


export default {
  components: {},
  setup() {

    const goWaitingRoom = () => {
      for (let i = 0; i < viewAnswer.value.length; i++) {
      
        state.value.surveydata.answer = viewAnswer.value[i]
        state.value.surveydata.userId= userinfo.value[0].userId
        state.value.surveydata.surveyId = surveyNum.value[i]
    
        console.log(viewAnswer.value[i])
        requestAnswer(state.value.surveydata, (res) => {
          console.log(res)
        })
        answerList.value.push(state.value.surveydata)
        state.value.surveydata={};

      }

      SET_ANSWER()
      //이건 꼭 바꾸기 (surveywaitingview로 가야됨)
      router.push({ name: "Step2Start"});
    };
    
    //[subin]
    const store = useStore();

    const state = ref({
      surveydata : {
        answer:null,
        userId:null,
        surveyId:null,
      },
      userSurvey:{
        surveyId:null,
        survey:null,
      },
    });

    const answerList=ref([]);
    
    const survey = ref([]);
    const viewSurvey = ref([]);
    const surveyNum = ref([]);
    const surveyList = ref([]);
    const viewAnswer = ref([])
    const SET_SURVEY_LIST = ()=> store.commit("iceBreakingStore/SET_SURVEY_LIST",surveyList);

    
    const SET_ANSWER=()=>{store.commit("iceBreakingStore/SET_ANSWER",answerList)};
    const getSurvey=()=>{
      requestSurvey((data)=>{
        console.log("data.data : ",data.data)
        for (let i = 0; i < data.data.length; i++) {
          surveyNum.value.push(data.data[i].surveyId)
          surveyList.value.push(data.data[i].survey)
        }
        SET_SURVEY_LIST();
      })
    };

    const userinfo = computed(() => store.state.userStore.userInfo);
    getSurvey();
    

    return {
      goWaitingRoom,
      survey,
      viewSurvey,
      state,
      userinfo,
      viewAnswer,
      surveyNum,
      surveyList,

    };
  },
};
</script>
<style scoped>
.survey-answer {
  border-radius: 30%;
  display: inline-block;
  font-size: 18px;
  font-weight: bolder;
  color: aliceblue;
  background-color: rgb(240, 11, 221);
}
</style>