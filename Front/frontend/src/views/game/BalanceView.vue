<template>
  <div class="wrap-blue">
    <h2>BalanceGame</h2>
    <div>{{ Ateam[currentPage - 1] }}</div>
    <div>VS</div>
    <div>{{ Bteam[currentPage - 1] }}</div>
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

<style></style>
