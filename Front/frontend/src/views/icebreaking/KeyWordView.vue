<template>
  <div class="wrap-blue">
    <div class="participant">발표자</div>
    <div class="participant2">{{ participants[index] }}</div>
    <div class="round-time-bar" data-style="smooth" style="--duration: 5">
      <div></div>
    </div>
    <div class="keyword">Keyword</div> 
    <div class="keyword2">{{ keyword }}</div>
 
  </div>
</template>

<script>
import router from "@/router";

import { ref, getCurrentInstance } from "vue";
export default {
  setup() {
    // const app = getCurrentInstance();
    // const $socket = app.appContext.config.globalProperties.$socket;
    const timer = ref(5);
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
      }, timer.value * 1000);
      } else {
        $socket.emit("callPlusIndex", (index.value + 1))    
        setTimeout(() => {
        router.push({
          name: "Step1Count",
        });
        
      }, timer.value * 1000);
      }
      })
    }
    resTeamMember()
    return {
      keyword,
      participants,
      totalNum,
      timer,
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
.participant {
  margin-top: 10px;
  font-family: bitbit;
  text-align: center;
  font-size: 28px;
}
.participant2 {
  margin-top: 10px;
  color: #ffffff;
  text-align: center;
  font-size: 60px;
  font-family: bitbit;
  text-shadow: 3px 6px 5px #e62475;
  -webkit-text-stroke: 0.3px black;
  
}

.keyword {
  font-family: bitbit;
  text-align: center;
  font-size: 48px;
}
.keyword2 {
  margin-top: 10px;
  color: #ffffff;
  text-align: center;
  font-size: 70px;
  font-family: bitbit;
  text-shadow: 4px 7px 6px #e62475;
  -webkit-text-stroke: 0.3px black;
  
}
</style>