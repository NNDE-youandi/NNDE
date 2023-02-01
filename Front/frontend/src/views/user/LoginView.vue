<template>
  <div class="wrap-blue">
    <h1>로그인</h1>
    <div class="wrap-inputs-login">
      <div class="input-with-label">
        <label for="user_id"
          >ID:
          <input
            type="text"
            class="input-id"
            id="user_id"
            v-model="state.credentials.email"
          />
        </label>
      </div>
      <div class="input-with-label pt10">
        <label for="user_password"
          >PW:
          <input
            type="password"
            class="input-login-password"
            id="user_password"
            v-model="state.credentials.password"
          />
        </label>
      </div>
    </div>
    <img src="../../assets/in_btn.png" class="btn-img" @click="login" />
    <div class="wrap-signup-btn">
      <router-link to="/signup"> 아이디가 없으신가요? </router-link>
      <img
        src="../../assets/signup_btn.png"
        class="btn-img-signup"
        @click="goSignUp"
      />
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref } from "vue";
import { requestLogin } from "../../api/userApi.js";
import { useStore } from "vuex";
// import { computed } from "vue";

export default {
  setup() {
    const store = useStore();
    // const isLogin = computed(() => store.state.userStore.isLogin)
    const setIsLogin = () => store.commit("userStore/SET_IS_LOGIN");

    const state = ref({
      credentials: {
        email: null,
        password: null,
      },
    });
    const login = () => {
      console.log(state.value.credentials);
      requestLogin(state.value.credentials, (res) => {
        router.push({ name: "SelectMode" });
        //통신을 통해 전달받은 값 콘솔에 출력
        console.log(res);
        setIsLogin(); // 스토어에 로그인 여부 변경
      });
    };
    // const isLogin = computed(() => store.state.useStore.isLogin)
    // console.log(isLogin)
    const goSignUp = () => {
      router.push({ name: "Signup" });
    };
    return {
      login,
      state,
      goSignUp,
    };
  },
};
</script>

<style>
</style>
