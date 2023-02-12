<template>
  <div class="wrap-blue">
    <h1>Step2Outro</h1>

    <h2>이 문제의 주인공은</h2>
    <h2>{{ result }}</h2>
    <h2>입니다</h2>
    <img
      src="../../assets/next_btn.png"
      class="btn-img"
      @click="goIceEnd"
    />
  </div>
</template>

<script>
import router from "@/router";
import { ref, computed } from "vue";
import { useStore } from "vuex";
export default {
  setup() {
    // const app = getCurrentInstance();
    // const $socket = app.appContext.config.globalProperties.$socket;
    // 수빈 
    const store = useStore();
    
    const getquizresult = computed(()=>store.getters["iceBreakingStore/GET_QUIZ_RESULT"]);
    const result = ref(getquizresult);
    console.log(result.value);
  
    const goIceEnd = () =>{
      router.push({name:"IceEnd"})
    }

    // const goStep2StartView = () => {
    //   $socket.emit("callStep2Start")
			
		// }
    // const getStep2StartUrl = () => {
    //   $socket.on("resStep2Start", (url) => {
    //     router.push({name:url})
    //   })
    // }
    // getStep2StartUrl()
    return {
      // goStep2StartView,
      goIceEnd,
      getquizresult,
      result
    };
  },
};
</script>

<style>
</style>