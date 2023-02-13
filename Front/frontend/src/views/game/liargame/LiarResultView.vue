<template>
  <div class="wrap-blue">
    <h3>게임 결과</h3>
    <div v-if="liarWord === liarAnswer">
        <h3>라이어 승리!!!</h3>
    </div>
    <div v-else>
        <div v-if="userId === liarId">
            <h3>패배!!</h3>
            <h4>정답은 {{liarWord}} 였습니다!</h4>
        </div>
        <div v-else>
            <h3>승리!!</h3>
            <h4>라이어가 {{liarAnswer}}을 입력했습니다!</h4>
        </div>
    </div>
    <p>{{roomType}}</p>
    <div v-if="roomType === 'LiarThemeList'">
      <img
      class="btn-img"
      @click="moveSelectGame"
      src="../../../assets/back_btn.png"
      alt="back-btn"
    />
    </div>
    <div v-else>
      <p>{{isHost}}</p>
      <div v-if="isHost">
        <img
        src="../../../assets/next_btn.png"
        class="btn-img"
        @click="goLastPage"
        />
      </div>
    </div>
  </div>
</template>

<script>
import router from '@/router';
import {ref, getCurrentInstance} from "vue";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const liarWord = ref('');
    const liarAnswer = ref('');
    const liarId = ref('');
    const liarWin = ref('');
    const amILiar = ref('');
    const userId = ref('');
    const roomType = ref('');
    const isHost = ref('');
    
    //roomtype 요청
    const getRoomType = () => {
      $socket.emit("callRoomMode")
    }
    getRoomType()

    const resRoomType = () => {
      $socket.on("resRoomType", (data) => {
        roomType.value = data
      })
    }
    resRoomType()

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

    //home으로 이동
    const moveSelectGame = () => {
      $socket.emit("exitRoom")
      router.push({
        name: "Home",
      });
    }

    //ice에서 라이어게임으로 이동
    const goLastPage = () => {
      $socket.emit("callIceLastPage")
			
		}
    const getIceLastPageUrl = () => {
      $socket.on("resIceLastPage", (url) => {
        router.push({name: url })
      })
    }
    getIceLastPageUrl()

    const receiveAnswer = () => {
      $socket.on("liarLastPage", (liarword, liaranswer, liarid) => {
        liarWord.value = liarword
        liarAnswer.value = liaranswer
        liarId.value = liarid
      })
    }
    receiveAnswer()

    //아이디 요청
    const callId = () => {
      $socket.emit('requestId')
    }
    //아이디 받음
    const sendId = () => {
      $socket.on("sendId", (data) => {
        userId.value = data
      })
    }
    callId()
    sendId()

    return {
        liarWord,
        liarAnswer,
        liarWin,
        liarId,
        userId,
        amILiar,
        roomType,
        isHost,
        goLastPage,
        moveSelectGame
    }
  }
}
</script>

<style>

</style>