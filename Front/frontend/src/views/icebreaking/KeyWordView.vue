<template>
  <div class="wrap-blue">
    <h1>발표자 {{ participants[0] }}</h1>
    <div class="round-time-bar" data-style="smooth" style="--duration: 15">
      <div></div>
    </div>
    <h2>{{ keyword }}</h2>
  </div>
</template>

<script>
import router from "@/router";
import { requestKeyword } from "@/api/introduceApi";
import { ref, getCurrentInstance } from "vue";
export default {
  setup() {
    // const app = getCurrentInstance();
    // const $socket = app.appContext.config.globalProperties.$socket;
    const Timer = 15;
    const keyword = ref();
    const KeywordIntroduce = () => {
      requestKeyword((data) => {
        console.log(data.data.keyword);
        keyword.value = data.data.keyword;
      });
    };
    KeywordIntroduce();

    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const participants = ref([]);
    const getRoomClientsNick = () => {
      $socket.on("sendRoomClientsId", (data) => {
        participants.value = data.roomClients;
      });
      $socket.emit("getRoomClientsId");
    };
    getRoomClientsNick();

    const setTimer = () => {
      setTimeout(() => {    
        router.push({
          name: "Step1Outro",
        });
      }, Timer * 1000);
      }
    setTimer()
    return {
      keyword,
      participants,
      Timer
    };
  
  },
};
</script>

<style>
.round-time-bar {
  margin: 3rem;
  overflow: hidden;
}
.round-time-bar div {
  height: 20px;
  animation: roundtime calc(var(--duration) * 1s) steps(var(--duration))
    forwards;
  transform-origin: left center;
  background: linear-gradient(to bottom, rgb(0, 0, 255), rgb(59, 143, 239));
  border-radius: 30px;
}

.round-time-bar[data-style="smooth"] div {
  animation: roundtime calc(var(--duration) * 1s) linear forwards;
}

@keyframes roundtime {
  to {
    transform: scaleX(0);
  }
}
</style>