<template>
  <div class="wrap-blue">
    <h1>발표자 {{ participants[index] }}</h1>
    <div class="round-time-bar" data-style="smooth" style="--duration: 5">
      <div></div>
    </div>
    <h2>{{ keyword }}</h2>
    <p>{{ participants }}</p>
    <p>{{index}}</p>
    <p>totalNum = {{totalNum}}</p>
 
  </div>
</template>

<script>
import router from "@/router";

import { ref, getCurrentInstance } from "vue";
export default {
  setup() {
    // const app = getCurrentInstance();
    // const $socket = app.appContext.config.globalProperties.$socket;
    const Timer = 5;
    const keyword = ref();
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const participants = ref([]);
    const totalNum = ref('')
    const index = ref('')

    //keyword값 요청
    const getKeyword = () => {
      $socket.emit("callKeyword")
    }
    getKeyword()

    const resKeyword = () => {
      $socket.on("resKeyword", (data) => {
        keyword.value = data
      })
    }
    resKeyword()
    //index값 요청
    const getRoomClientsNick = () => {
      $socket.emit("callTeamMember");
    };
    getRoomClientsNick();

    const resTeamMember = () => {
      $socket.on("resTeamMember", (teammember, teamlength, idx) => {
        participants.value = teammember
        totalNum.value = teamlength - 1
        index.value = idx

      if (idx === teamlength - 1 ) {
        setTimeout(() => {    
        router.push({
          name: "Step1Outro",
        });
      }, Timer * 1000);
      } else {
        console.log(false)
        $socket.emit("callPlusIndex", (index.value + 1))    
        setTimeout(() => {
        router.push({
          name: "Step1Count",
        });
        
      }, Timer * 1000);
      }
      })
    }
    resTeamMember()
    return {
      keyword,
      participants,
      totalNum,
      Timer,
      index
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