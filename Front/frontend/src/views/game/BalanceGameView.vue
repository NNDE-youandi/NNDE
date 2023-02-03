<template>
  <div class="wrap-blue">
    <h1>BalanceGame</h1>
    <p>{{ Ateam[currentPage-1] }}</p>
    <div>VS</div>
    <p>{{Bteam[currentPage-1]}}</p>
    <button @click="nextPage">앞으로</button>
    <p>{{currentPage}}</p>
    <button @click="prevPage">뒤로</button>
  </div>
</template>

<script>
import {ref, getCurrentInstance} from "vue";
export default {
    setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const currentPage = ref(1);
    const totalPage = 10
    const Ateam = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    const Bteam = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

    const startBalance = () => {
        $socket.emit("startBalance", currentPage.value)
        $socket.once("startBalanceGame", (data) => {
            currentPage.value = data
            console.log(data)
        })
    }
    startBalance();

    const nextPage = () => {
        if (currentPage.value < totalPage) {
        $socket.emit("requestNextPage")
            }
        }
    const setNextPage = () => {
        $socket.on("sendNextPage", (plusNum) => {
            currentPage.value = plusNum
            console.log(currentPage.value)
        })
    }
    setNextPage()
    const prevPage = () => {
        if (currentPage.value > 1) {
        $socket.emit("requestPrevPage")
            }
    }
    const setPrevPage = () => {
        $socket.on("sendPrevPage", (minusNum) => {
            currentPage.value = minusNum
            console.log(currentPage.value)
        })
    }
    setPrevPage()
    return {
        currentPage,
        nextPage,
        prevPage,
        Ateam,
        Bteam
    }
    }
}
</script>

<style>

</style>