<template>
<div class="wrap-blue">
  <h1>ICE 총 정리</h1>
  <div v-for="(member, idx) in teamMembers" :key="idx">
    <div>{{member}}</div>
  </div>
  <div>
    <div class="thermometer">
        <div class="mercury"></div>
    </div>
    <p>시간 보여주는 칸</p>
    <p>이 시간이면 얼음이 전부 녹을 시간입니다.</p>
  </div>
  <button>내려받기</button>
  <button @click="goHomeView">나가기</button>
</div>
</template>

<script>
import router from '@/router';
// import { getCurrentInstance } from "vue";
export default {
    setup() {
        // const app = getCurrentInstance();
        // const $socket = app.appContext.config.globalProperties.$socket;
        const teamMembers = ['a', 'b', 'c', 'd', 'e']
        const teamMembersData = {'a':''}

        // 홈 화면으로 이동
        const goHomeView = () => {
            router.push({name:"Home"})
        }
        return {
            teamMembers,
            teamMembersData,
            goHomeView
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
</style>