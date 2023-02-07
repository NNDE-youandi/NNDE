<template>
  <div class="wrap-blue">
    <h1>라이어 게임</h1>
    <div v-if="liarWord === liarAnswer">
        <h1>라이어 승리!!!</h1>
    </div>
    <div v-else>

        <div v-if="userId === liarId">
            <h1>패배!!</h1>
            <h2>정답은 {{liarWord}} 였습니다!</h2>
        </div>
        <div v-else>
            <h1>승리!!</h1>
            <h2>라이어가 {{liarAnswer}}을 입력했습니다!</h2>
        </div>
    </div>
  </div>
</template>

<script>
import {ref, getCurrentInstance} from "vue";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const liarWord = ref('')
    const liarAnswer = ref('')
    const liarId = ref('')
    const liarWin = ref('')
    const amILiar = ref('')
    const userId = ref('')    
    const receiveAnswer = () => {
      $socket.on("liarLastPage", (liarword, liaranswer, liarid) => {
        liarWord.value = liarword
        liarAnswer.value = liaranswer
        liarId.value = liarid
      })
    }
    receiveAnswer()

    //아이디 요청
    const receiveId = () => {
      $socket.emit('requestId')
    }
    //아이디 받음
    const sendId = () => {
      $socket.on("sendId", (data) => {
        userId.value = data
      })
    }
    receiveId()
    sendId()
    
    return {
        liarWord,
        liarAnswer,
        liarWin,
        liarId,
        userId,
        amILiar
    }
  }
}
</script>

<style>

</style>