<template>
  <div class="wrap-blue">
    <div>
      <h4>Balance Game</h4>
    </div>
    <div>
      <h6 class="balance_1">
        {{ Ateam[currentPage - 1] }}
      </h6>
    </div>
    <h4 class="vs">VS</h4>
    <div>
      <h6 class="balance_2">
        {{ Bteam[currentPage - 1] }}
      </h6>
    </div>
    <div class="wrap-arrow" v-if="isHost === true">
      <img src="../../assets/left_btn.png" @click="prevPage" alt="left" cl class="btn-arrow">
      <div class="num-page">{{ currentPage }}</div>
      <img src="../../assets/right_btn.png" @click="nextPage" alt="left" class="btn-arrow">
    </div>
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
    const currentPage = ref("");
    const roomType = ref("");
    const isHost = ref("");
    const totalPage = 3;
    const Ateam = ref([]);
    const Bteam = ref([]);

    const getRoomType = () => {
      $socket.emit("callRoomMode");
    };
    getRoomType();

    const resRoomType = () => {
      $socket.on("resRoomType", (data) => {
        roomType.value = data;
      });
    };
    resRoomType();

    const checkHost = () => {
      $socket.emit("getIsHost");
    };
    const receiveId = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
  
    checkHost();
    receiveId();

    const goStep4Start = () => {
      $socket.emit("callStep4Start");
    };
    const getStep4StartUrl = () => {
      $socket.on("resStep4Start", (url) => {
        router.push({ name: url });
      });
    };
    getStep4StartUrl();

    const moveSelectGame = () => {
      $socket.emit("exitRoom");
      router.push({
        name: "Home",
      });
    };

    const startBalance = () => {
      $socket.emit("startBalance");
      $socket.once("startBalanceGame", (data) => {
        currentPage.value = data;
      });
    };
    startBalance();
    const balanceGame = () => {
      requestBalanceGame((res) => {
        let a_array = []
        let b_array = []
        for (let idx = 0; idx < res.data.length; idx++) {
          a_array.push(res.data[idx].bgQuestion1);
          b_array.push(res.data[idx].bgQuestion2);
        }
        if (isHost.value===true) {
          $socket.emit("getBalanceData", a_array, b_array)
        }
      });
    };
    balanceGame();

    const sendBalanceData = () => {
      $socket.on("sendBalanceData", (ateam, bteam) => {
        Ateam.value = ateam
        Bteam.value = bteam
      })
    }
    sendBalanceData()

     const nextPage = () => {
      if ((currentPage.value < totalPage) && isHost.value) {
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
      if ((currentPage.value > 1) && isHost.value) {
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
      moveSelectGame,
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
}
.wrap-arrow {
  margin-top: 20px;
  display: flex;
  justify-content: space-evenly;
}
.btn-arrow {
  width: 40px;
}
.num-page {
  font-family: bitbit;
  font-size: 40px;
}
</style>
