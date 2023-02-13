<template>
  <div class="wrap-blue">
    <h4 class="title">모두가 설문이 끝날때까지 기다려주세요!</h4>
    <div class="wrap-numberofmember">
      <img src="../../assets/cat.png" alt="cat" />
      <h3>{{ numberOfMemberSurvey }} / {{ numberOfFullMember }}</h3>
    </div>
    <div v-if="!isHost"></div>
    <img
      v-else-if="isHost && numberOfMemberSurvey !== numberOfFullMember"
      src="../../assets/next-inactive_btn.png"
      class="btn-img"
    />
    <img
      v-else
      src="../../assets/next-active_btn.png"
      class="btn-img"
      @click="goStep1"
    />
  </div>
</template>

<script>
import router from "@/router";
// 송섭
import { getCurrentInstance, ref } from "vue";
export default {
  setup() {
    // 송섭
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;

    const numberOfMemberSurvey = ref(0);
    const numberOfFullMember = ref(0);
    const isHost = ref();
    const getNumberOfMemberSurvey = () => {
      $socket.on("sendNumberOfMemberSurvey", (data) => {
        numberOfMemberSurvey.value = data.numberOfMemberSurvey;
        numberOfFullMember.value = data.numberOfFullMember.length;
      });
      $socket.emit("getNumberOfMemberSurvey");
    };
    getNumberOfMemberSurvey();
    const checkHost = () => {
      $socket.emit("getIsHost");
    };
    const receiveId = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    receiveId();
    checkHost();

    //웅기
    const goStep1 = () => {
      $socket.emit("startTime");
      $socket.emit("callIceBreakingStart");
    };

    const getIceBreakingStartUrl = () => {
      $socket.on("resIceBreakingStart", (url) => {
        router.push({ name: url });
      });
    };
    getIceBreakingStartUrl();
    return {
      goStep1,
      // 송섭
      isHost,
      numberOfMemberSurvey,
      numberOfFullMember,
    };
  },
  unmounted() {},
  methods: {},
};
</script>
<style>
.title {
  margin-left: 30px;
  margin-right: 30px;
}
</style>