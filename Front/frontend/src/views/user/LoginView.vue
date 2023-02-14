<template>
  <div class="wrap-blue">
    <h2>로그인</h2>
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
    <div class="wrap-signup-btn">
      <router-link to="/signup"> 아이디가 없으신가요? </router-link>
    </div>
    <img
      src="../../assets/login_btn.png"
      class="btn-img-signup"
      @click="login"
    />

    <div>
      <img 
      src="../../assets/kakao_login_medium_narrow.png"
      @click="onKakaoLogin"/>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
import { requestLogin,requestKakaoLogin } from "../../api/userApi.js";
import { useStore } from "vuex";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const store = useStore();
    const setIsLoginTrue = () => store.commit("userStore/SET_IS_LOGIN_TRUE");
    const userinfo = ref([]);
    const setUserInfo = () => store.commit("userStore/SET_USER_INFO", userinfo);
    const state = ref({
      credentials: {
        email: null,
        password: null,
      },
    });
    const login = () => {
      requestLogin(state.value.credentials, (res) => {
        if (!res.data.message) {
          alert("로그인에 실패하셨습니다.");
        } else {
          userinfo.value.push(res.data);
          setUserInfo();
          setIsLoginTrue();
          $socket.emit("getUserNick", res.data.nickname);
          router.push({ name: "Home" });
        }
      });
    };
    const goSignUp = () => {
      router.push({ name: "Signup" });
    };

    // kakao login
    const onKakaoLogin=() =>{
      requestKakaoLogin((res)=>{
        console.log("res.data : ",res.data);
        window.location.href = res.data;
      })
    };
    return {
      login,
      state,
      goSignUp,
      onKakaoLogin
    };
  },
};
</script>

<style>
</style>
