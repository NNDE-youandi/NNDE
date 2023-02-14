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
      </div>
      <h4>{{ duringTime }}초 동안 활동을 했어요!</h4>
    </div>
    <img src="../../assets/exit_btn.png" alt="exit" class="btn-exit" @click="goRealEnd" />
  </div>
</template>

<script>
import router from "@/router";
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
    return {
      teamMembers,
      teamMembersData,
      goHomeView,
      duringTime,
      goRealEnd,
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