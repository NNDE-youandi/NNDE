<template>
<div class="wrap-blue">
  <h3>ICE 총 정리</h3>
  <div v-for="(member, idx) in teamMembers" :key="idx">
    <div>{{member}}</div>
  </div>
  <h3>{{ duringTime }}초 동안이나 했어요!!</h3>
  <div>
    <div class="thermometer">
        <div class="mercury"></div>
    </div>
    <p>시간 보여주는 칸</p>
    <p>이 시간이면 얼음이 전부 녹을 시간입니다.</p>
  </div>
  
  <div>
    <img src="@/assets/ICE/52_caffe_.jpg"
    class="caffe"
    />
  </div>

<!-- 음악 플레이어 -->
  <audio autoplay>
    <source src="../../../public/You_Are_My_Girl.mp3" type="audio/mpeg">
    이 문장은 audio 태그를 지원되지 않을 경우 화면에 표시됩니다.
  </audio>
  
  <!-- <button>내려받기</button> -->
  <button @click="goHomeView">나가기</button>
</div>
</template>

<script>
import router from '@/router';
// import { useStore } from "vuex";
// import {bgm} from "@/assets/ICE/You_Are_My_Girl.mp3";
import { getCurrentInstance, ref } from "vue";

export default {
    setup() {
        const app = getCurrentInstance();
        const $socket = app.appContext.config.globalProperties.$socket;
        const teamMembers = ['a', 'b', 'c', 'd', 'e']
        const teamMembersData = {'a':''}

        // 홈 화면으로 이동
        const goHomeView = () => {
            $socket.emit("exitRoom")
            router.push({name:"Home"})
        }
        const duringTime = ref(0)
        const sendTime = () => {
          $socket.on("sendTime", (time) => {
            duringTime.value = time
          })
          $socket.emit("endTime")
        }
        sendTime()
        // 음악 
        // const store = useStore();
        // const bgm =() => store.dispatch("iceBreakingStore/PLAY_ICE_END");

        return {
            teamMembers,
            teamMembersData,
            goHomeView,
            duringTime,
            // onPlay,
            // bgm,
        }
    }
}
</script>

<style>
    .thermometer {
    height: 200px;
    width: 50px;
    background: linear-gradient(#ccc, #ddd);
    border-radius: 25px 25px 0 0;
    position: relative;
    overflow: hidden;
    }

    .mercury {
    height: 150px;
    width: 50px;
    background: red;
    border-radius: 0 0 25px 25px;
    position: absolute;
    bottom: 0;
    }

    .caffe{
      height: 125px;
      width: 600px;
    }
</style>