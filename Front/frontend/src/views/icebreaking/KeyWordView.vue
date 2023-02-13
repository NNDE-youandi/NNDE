<template>
  <div class="wrap-blue">
    <h3>발표자</h3>
    <h2 class="participant">{{ participants[index] }}</h2>
    <div class="round-time-bar" data-style="smooth" style="--duration: 5">
      <div></div>
    </div>
    <h3>Keyword</h3> 
    <h2 class="keyword">{{ keyword }}</h2>
 
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
.participant {
  color: rgb(0, 0, 218) ;
}
.keyword {
  color: blue;
  font-size: 80px;
}
</style>