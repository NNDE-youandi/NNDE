<template>
  <div class="wrap-blue">
    <h1>인원수 설정</h1>
    <div class="wrap-count-num">
      <img src="../../assets/left_btn.png" @click="minusNum" />
      <p v-bind:numOfMember="numOfMember">{{ numOfMember }}</p>
      <img src="../../assets/right_btn.png" @click="plusNum" />
    </div>
    <img src="../../assets/makeroom_btn.png" class="btn-img" @click="makeRoom" />
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const store = useStore();
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;

    const numOfMember = ref(5);
    const setNumberOfMembers = () => {
      store.commit("iceBreakingStore/SET_NUMBER_OF_MEMBERS", numOfMember);
    };

    const minusNum = () => {
      if (numOfMember.value > 4) {
        numOfMember.value -= 1;
      }
    };
    const plusNum = () => {
      if (numOfMember.value < 6) {
        numOfMember.value += 1;
      }
    };
    // const goIceQr = () => {
    //   setNumberOfMembers();
    //   router.push({ name: "IceQr" });
    //   // console.log(store.state.numOfMember)
    // };

    const roomNumber = ref(111111);
    const makeRoom = () => {
      $socket.emit("makeRoom", {
        roomNumber: roomNumber.value,
      });
      setNumberOfMembers()

      router.push({
        name: "IceQr",
        params: {
          "roomNumber": roomNumber.value,
        },
      });
    };
    return {
      numOfMember,
      minusNum,
      plusNum,
      // goIceQr,
      makeRoom,
    };
  },
  methods: {},
};
</script>

<style>
</style>