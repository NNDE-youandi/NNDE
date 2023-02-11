<template>
  <header>
<<<<<<< HEAD
    <img src="../../assets/back_btn2.png" alt="Back" @click="goBack">
    <div @click="goHome">🏠</div>
    <button v-if="isLogin" @click="logOut">logout</button>
    <button v-else @click="goLogin">login</button>
=======
    <div>{{ loginMsg }}</div>
    <button v-if="isLogin" @click="logOut">logout</button>
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
  </header>
</template>

<script>
import { onMounted, computed, watch } from "@vue/runtime-core";
import { useStore } from "vuex";
import { requestLogout } from "@/api/userApi.js";
import { ref } from "vue";
import router from "@/router";
// import isLoginView from "./isLogin.vue";

export default {
  // components: {
  //   isLoginView
  // },
  setup() {
    const store = useStore();
    const isLogin = computed(() => {
      return store.getters["userStore/GET_IS_LOGIN"]
    })
    const loginMsg = ref("로그인안됨");
    const getIsLogin = () => {
      if (isLogin.value) {
        loginMsg.value = "로그인됨";
      } else {
        loginMsg.value = "로그인안됨";
      }
    };
    const logOut = (() => {
      
      const userinfo = computed(() => store.state.userStore.userInfo);
      const logoutdata = ref({
        accessToken: userinfo.value[0].accessToken,
        refreshToken: userinfo.value[0].refreshToken,
      })
      // console.log(isLogin)
      requestLogout(logoutdata.value, (res) => {
        console.log(res)
        store.commit("userStore/SET_IS_LOGIN_FALSE")
        store.commit("userStore/CLEAR_USER_INFO")
        router.push({ name: "Home"})
      } )
    })
<<<<<<< HEAD

    const goHome = (() => {
      router.push({ name: "Home"})
    })
    const goLogin = (() => {
      router.push({ name: "Login"})
    })

    const goBack = (() => {
      router.go(-1)
    })
=======
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
    watch(isLogin, () => {
      getIsLogin()
    })
    onMounted(() => {
      getIsLogin();
    });
    return {
      loginMsg,
      isLogin,
<<<<<<< HEAD
      logOut,
      goHome,
      goBack,
      goLogin
=======
      logOut
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
    };
  },
};
</script>

<style>
</style>