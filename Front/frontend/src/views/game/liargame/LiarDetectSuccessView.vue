<template>
  <div class="wrap-blue">
    <h3>게임 결과</h3>
    <div v-if="userId === liarId">
      <h3>당신은 라이어입니다</h3>
      <h4>생각하는 단어를 입력해주세요!</h4>
      <input type="text" v-model="liarAnswer" />

      <button @click="checkAnswer">입력</button>
    </div>
    <div v-else>
      <h4>라이어를 찾았습니다!!</h4>
      <h3>라이어: {{liarId}}</h3>
    </div>
  </div>
  
</template>

<script>
import router from "@/router";
import {ref, getCurrentInstance } from "vue";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const userId = ref('')
    const liarId = ref('')
    const liarWord = ref('')
    const whoAmI = ref('')
    const liarAnswer = ref('')
  
    //라이어게임 결과 데이터 받아오기
    const requestLiar = () => {
      
      $socket.emit('requestId')
    }
    
    //liarid, liarword, userid를 통해 user
    const receiveLiar = () => {
      //liarId, liarWord를 받아옴.
        $socket.on("LiarIdData", (liarid, liarword) => {
            liarWord.value = liarword
            liarId.value = liarid
        })
        //
        $socket.on("sendId", (data) => {
        userId.value = data
      })
      }
    requestLiar()
    receiveLiar()

    //checkAnswer => 마지막 페이지에 liarWord, liarAnswer, liarId를 보낼 수 있음.
    const checkAnswer = () => {
      $socket.emit('checkAnswer', liarWord.value, liarAnswer.value, liarId.value)
    }

    //liarResult를 통해 router push를 해줌.
    const goLastPage = () => {
      $socket.on('liarResult', (url) => {
        router.push({name: url })
      })
    }
    goLastPage()
    
    

    
    return {
      userId,
      liarId,
      liarWord,
      whoAmI,
      liarAnswer,
      checkAnswer,
  
    }
  }
}
</script>

<style>

</style>