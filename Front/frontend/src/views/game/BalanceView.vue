<template>
  <div class="wrap-blue">
    <div>
      <h2>Balance</h2>
      <h2>Game</h2>
    </div>
    <div>
      <h4 class="balance_1">
        {{ Ateam[currentPage - 1] }}
      </h4>
    </div>
    <h4 class="vs">VS</h4>
    <div>
      <h4 class="balance_2">
        {{ Bteam[currentPage - 1] }}
      </h4>
    </div>
    <button @click="prevPage">앞으로</button>
    <div>{{ currentPage }}</div>
    <button @click="nextPage">뒤로</button>
    <div v-if="roomType === 'Balance'">
      <img
      class="btn-img"
      @click="moveSelectGame"
      src="../../assets/back_btn.png"
      alt="back-btn"
    />
    </div>
    <div v-else>
      <div v-if="isHost">
        <img
        src="../../assets/next_btn.png"
        class="btn-img"
        @click="goStep4Start"
        />
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
import { requestBalanceGame } from "@/api/gameApi";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const currentPage = ref(1);
    const roomType = ref('');
    const isHost = ref('');
    const totalPage = 3;
    const Ateam = ref([]);
    const Bteam = ref([]);

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

    //ice에서 라이어게임으로 이동
    const goStep4Start = () => {
      $socket.emit("callStep4Start")
			
		}
    const getStep4StartUrl = () => {
      $socket.on("resStep4Start", (url) => {
        router.push({name: url })
      })
    }
    getStep4StartUrl()

    //home으로 이동
    const moveSelectGame = () => {
      router.push({
        name: "Home",
      });
    }

    const startBalance = () => {
      $socket.emit("startBalance", currentPage.value);
      $socket.once("startBalanceGame", (data) => {
        currentPage.value = data;
      });
    };
    startBalance();
    const balanceGame = () => {
      requestBalanceGame((res) => {
        for (let idx = 0; idx < res.data.length; idx++) {
          console.log(res.data[idx]);
          Ateam.value.push(res.data[idx].bgQuestion1);
          Bteam.value.push(res.data[idx].bgQuestion2);
        }
      });
    };
    balanceGame();
    const nextPage = () => {
      if (currentPage.value < totalPage) {
        $socket.emit("requestNextPage");
      }
    };
    const setNextPage = () => {
      $socket.on("sendNextPage", (plusNum) => {
        currentPage.value = plusNum;
      });
    };
    setNextPage();
    const prevPage = () => {
      if (currentPage.value > 1) {
        $socket.emit("requestPrevPage");
      }
    };
    const setPrevPage = () => {
      $socket.on("sendPrevPage", (minusNum) => {
        currentPage.value = minusNum;
      });
    };
    setPrevPage();
    return {
      nextPage,
      prevPage,
      roomType,
      isHost,
      currentPage,
      Ateam,
      Bteam,
      goStep4Start,
      moveSelectGame
    };
  },
};
</script>

<style>
.vs {
  color: black;
  text-align: center;
  margin: 10px 0;
  font-size: 28px;
  font-family: bitbit;
  text-shadow: none;
  -webkit-text-stroke: 0.3px black;
}</style>
