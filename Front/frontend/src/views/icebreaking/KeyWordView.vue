<template>
  <div class="wrap-blue">
    <div class="participant">발표자</div>
    <div class="participant2">{{ participants[index] }}</div>
    <div class="round-time-bar" data-style="smooth" style="--duration: 5">
      <div></div>
    </div>
    <div class="keyword">Keyword</div> 
    <div class="keyword2">{{ keyword }}</div>
    <div :class="[isHost && showButton? 'show-not-show': 'not-show']">
      <div :class="[step1count? 'show-not-show': 'not-show']">
        <img
        src="../../assets/next_btn.png"
        class="btn-img"
        @click="goStep1Count"
      />
      </div>
      <div :class="[step1outro? 'show-not-show': 'not-show']">
        <img
        src="../../assets/next_btn.png"
        class="btn-img"
        @click="goStep1Outro"
      />
      </div>
    </div>
  </div>
</template>

<script>
import router from "@/router";

import { ref, getCurrentInstance } from "vue";
export default {
  setup() {
    const timer = ref(5);
    const keyword = ref();
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const participants = ref([]);
    const totalNum = ref('')
    const index = ref('')
    const showButton = ref(false)
    const isHost = ref('')
    const step1count = ref(false)
    const step1outro = ref(false)

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

    const checkHost = () => {
      $socket.emit("getIsHost")
    }
    checkHost()

    const resCheckHost = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data
      })
    }
    resCheckHost()

    const goStep1Count = () => {
      $socket.emit("callStep1CountRoutine")
    }
    const resStep1Count = () => {
      $socket.on("resStep1CountRoutine", (url) => {
        router.push({name:url})
      })
    }
    resStep1Count()

    const goStep1Outro = () => {
      $socket.emit("callStep1Outro")
    }
    const resStep1Outro = () => {
      $socket.on("resStep1Outro", (url) => {
        router.push({name:url})
      })
    }
    resStep1Outro()

    const callTeamMember = () => {
      $socket.emit("callTeamMember")
    }
    callTeamMember()

     const resTeamMember = () => {
      $socket.on("resTeamMember", (teammember, teamlength, idx) => {
        participants.value = teammember
        index.value = idx
      if (idx === teamlength - 1 ) {
        setTimeout(() => {
          
          showButton.value = true
          step1outro.value = true    
        }, timer.value * 1000);
      } else {    
        setTimeout(() => {
          showButton.value = true
          step1count.value = true 
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
      index,
      showButton,
      isHost,
      step1count,
      step1outro,
      goStep1Count,
      goStep1Outro
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
.participant2 {
  margin-top: 10px;
  font-family: bitbit;
  text-align: center;
  font-size: 60px;
}
.participant {
  margin-top: 10px;
  color: #ffffff;
  text-align: center;
  font-size: 40px;
  font-family: bitbit;
  text-shadow: 3px 6px 5px #e62475;
  -webkit-text-stroke: 0.3px black;
}
.keyword2 {
  font-family: bitbit;
  text-align: center;
  font-size: 80px;
  margin-top: 10px;
}
.keyword {
  color: #ffffff;
  text-align: center;
  font-size: 48px;
  font-family: bitbit;
  text-shadow: 4px 7px 6px #e62475;
  -webkit-text-stroke: 0.3px black;
}
.not-show {
  display: none;
}
.show-not-show {
  display: '';
}
</style>