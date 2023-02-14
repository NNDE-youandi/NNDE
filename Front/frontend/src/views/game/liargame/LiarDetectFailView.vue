<template>
  <div class="wrap-blue">
    <h4>게임 결과</h4>
  <h4 style="margin: 5vh 0;">라이어의 승리입니다.</h4>
  <h4>라이어는 {{liarId}}였습니다.!</h4>

  <div v-if="roomType === 'LiarThemeList'">
      <img
      class="btn-img"
      @click="moveSelectGame"
      src="../../../assets/back_btn.png"
      alt="back-btn"
    />
    </div>
    <div v-else>
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
import router from "@/router";
import {ref, getCurrentInstance} from "vue";
export default {
    setup() {
        const app = getCurrentInstance();
        const $socket = app.appContext.config.globalProperties.$socket;
        const liarWord = ref('')
        const liarId = ref('')
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


        const receiveLiar = () => {
            $socket.on("LiarIdData", (liarid, liarword) => {
                liarWord.value = liarword
                liarId.value = liarid
            })
        }
        receiveLiar()
        return {
            liarId,
            liarWord,
            roomType,
            isHost,
            moveSelectGame,
            goLastPage
        }
    }
}
</script>

<style>

</style>