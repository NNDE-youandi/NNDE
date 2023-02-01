<template>
  <div class="wrap-blue">
    <h1>너나들이</h1>
    <img src="../../assets/ani3.png" class="img-ani3" />
    <div class="wrap-input-pin">
      <span> PIN: </span>
      <input type="number" id="pin_num" v-model="inputPin" />
      <img src="../../assets/enter_btn.png" @click="submitPin" />
    </div>
    <img src="../../assets/makeroom_btn.png" class="btn-img" @click="goLogin" />
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
// import { useStore } from "vuex";

export default {
  setup() {
    // const store = useStore();
    // const isLogin = computed(() => store.state.userStore.isLogin);
    const inputPin = ref("");
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const goLogin = () => {
      // if (true) {
        router.push({ name: "SelectMode" });
      // } else {
        // router.push({ name: "Login" });
      // }
    };
    const submitPin = () => {
      $socket.emit("submitPin", {
        pin: inputPin.value,
      });
    };
    return {
      goLogin,
      inputPin,
      submitPin,
    };
  },
};
</script>

<style>
</style>
