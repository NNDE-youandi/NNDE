<template>
  <div class="wrap-blue">
    <h1>Step2Outro</h1>
    <h2>이 문제의 주인공은</h2>
    <h2>{{ result }}</h2>
    <h2>입니다</h2>
    <div v-if="isDone">
      <img
      src="../../assets/next_btn.png"
      class="btn-img"
      @click="goBalance"
    />
    </div>
    <div v-else>
      <button @click="goStep2Start">다음 퀴즈로</button>
    </div>
    
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
// import { useStore } from "vuex";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    // 수빈 
    // const store = useStore();
    
    // const getquizresult = computed(()=>store.getters["iceBreakingStore/GET_QUIZ_RESULT"]);
    const result = ref();
    const isDone = ref(false);
    //정답 가져오기
    const callSurveyQuizResult = () => {
      $socket.emit("callSurveyQuizResult")
    }
    callSurveyQuizResult()

    const resSurveytQuizResult = () => {
      $socket.on("resSurveytQuizResult", (data) => {
        result.value = data
      })
    }
    resSurveytQuizResult()

    //인덱스값 가져오기
    const callSurveyIndex = () => {
      $socket.emit("callSurveyIndex")
    }
    callSurveyIndex()

    const resSurveyIndex = () => {
      $socket.on("resSurveyIndex", (index) => {
        console.log(index)
        if (index === 3) {
          isDone.value = true
        } 
      })
    }
    resSurveyIndex()

    const goBalance = () => {
      $socket.emit("goBalance")
		}
    const getBalancetUrl = () => {
      $socket.on("moveBalancePage", (url) => {
        router.push({name:url})
      })
    }
    getBalancetUrl()

    const goStep2Start = () => {
      $socket.emit("callStep2Count")
    }
    const getStep2StartUrl = () => {
      $socket.on("resStep2Count", (url) => {
        router.push({name:url})
      })
    }
    getStep2StartUrl()
    return {
      // goStep2StartView,
      goBalance,
      result,
      isDone,
      goStep2Start
    };
  },
};
</script>

<style>
</style>