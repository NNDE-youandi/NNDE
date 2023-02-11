<template>
  <div class="wrap-blue">
<<<<<<< HEAD
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
=======
    <h2>BalanceGame</h2>
    <div>{{ Ateam[currentPage - 1] }}</div>
    <div>VS</div>
    <div>{{ Bteam[currentPage - 1] }}</div>
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
    <button @click="nextPage">앞으로</button>
    <div>{{ currentPage }}</div>
    <button @click="prevPage">뒤로</button>
  </div>
</template>

<script>
import { ref, getCurrentInstance } from "vue";
import { requestBalanceGame } from "@/api/gameApi";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const currentPage = ref(1);
    const totalPage = 10;
    const Ateam = ref([]);
    const Bteam = ref([]);

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
      currentPage,
      Ateam,
      Bteam,
    };
  },
};
</script>

<<<<<<< HEAD
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
=======
<style></style>
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
