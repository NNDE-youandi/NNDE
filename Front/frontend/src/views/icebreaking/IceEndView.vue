<template>
  <div class="wrap-blue">
    <h3>결과</h3>
    <img src="../../assets/animals.png" alt="animals" class="animals" />
    <div>
      <div class="wrap-result-main">
        <div class="donation-meter">
          <span class="glass">
            <strong class="total">36.7 ℃</strong>
            <span class="amount"></span>
          </span>
          <div class="bulb">
            <span class="red-circle"></span>
            <span class="filler">
              <span></span>
            </span>
          </div>
        </div>

        <div class="wrap-member">
          <div v-for="(member, idx) in teamMembers" :key="idx">{{ member }}</div>
        </div>
        <!-- <div class="wrap-member">
          <div>닉네임 1</div>
          <div>닉네임 2</div>
        </div> -->
      </div>
      <h4>{{ duringTime }}초 동안 활동을 했어요!</h4>
    </div>

    <!-- 음악 플레이어 -->
    <!-- <audio autoplay>
    <source src="../../../public/You_Are_My_Girl.mp3" type="audio/mpeg">
    이 문장은 audio 태그를 지원되지 않을 경우 화면에 표시됩니다.
  </audio>
   -->
    <img src="../../assets/exit_btn.png" alt="exit" class="btn-exit" @click="goRealEnd" />
  </div>
</template>

<script>
import router from "@/router";
// import { useStore } from "vuex";
// import {bgm} from "@/assets/ICE/You_Are_My_Girl.mp3";
import { getCurrentInstance, ref } from "vue";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const teamMembers = ref([]);
    const teamMembersData = { a: "" };

    const callendPageTeamMember = () => {
      $socket.emit("callendPageTeamMember");
    };
    callendPageTeamMember();

    const resendPageTeamMember = () => {
      $socket.on("resendPageTeamMember", (data) => {
        teamMembers.value = data;
      });
    };
    resendPageTeamMember();
    // 홈 화면으로 이동
    const goHomeView = () => {
      $socket.emit("exitRoom");
      router.push({ name: "Home" });
    };
    const duringTime = ref(0);
    const sendTime = () => {
      $socket.on("sendTime", (time) => {
        duringTime.value = time;
      });
      $socket.emit("endTime");
    };
    sendTime();
    const goRealEnd = (() => {
      router.push({ name: "IceRealEnd"})
    })
    // 음악
    // const store = useStore();
    // const bgm =() => store.dispatch("iceBreakingStore/PLAY_ICE_END");

    return {
      teamMembers,
      teamMembersData,
      goHomeView,
      duringTime,
      goRealEnd
      // onPlay,
      // bgm,
    };
  },
};
</script>

<style>
.wrap-member {
  background-color: rgba(255, 255, 255, 0.4);
  padding: 20px 40px;
  margin: 40px 40px 40px 10px;
  line-height: 30px;
  font-size: 20px;
  font-family: bitbit;
}
.wrap-result-main {
  display: flex;
}
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

.caffe {
  height: 125px;
  width: 600px;
}
.animals {
  width: 80%;
  display: block;
  margin: 10px auto;
}
.btn-exit {
  width: 35%;
  display: block;
  margin: 0 auto;
}
</style>