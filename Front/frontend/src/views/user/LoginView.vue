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
      <!-- <img
        src="../../assets/signup_btn.png"
        class="btn-img-signup"
        @click="goSignUp"
        /> -->
      </div>
    <img src="../../assets/login_btn.png" class="btn-img-signup" @click="login" />
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance } from "vue";
import { requestLogin } from "../../api/userApi.js";
import { useStore } from "vuex";
// import { computed } from "vue";

export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const store = useStore();
    // const isLogin = computed(() => store.state.userStore.isLogin)
    const setIsLoginTrue = () => store.commit("userStore/SET_IS_LOGIN_TRUE");
    // subin 
    const userinfo=ref([]);
    const setUserInfo =()=> store.commit("userStore/SET_USER_INFO",userinfo);


    const state = ref({
      credentials: {
        email: null,
        password: null,
      },
    });
    const login = () => {
      requestLogin(state.value.credentials, (res) => {
        // 로그인 실패 여부 test
        // console.log("requestLogin [로그인] : ",res);
        // console.log(typeof(res.data.message));
        
        // 로그인이  false
        if(!res.data.message){
          alert("로그인에 실패하셨습니다.")

        }else{  
          // 로그인이 true
          userinfo.value.push(res.data);
          setUserInfo(); // 스토어에 유저 정보 저장하기
          setIsLoginTrue(); // 스토어에 로그인 여부 변경
          $socket.emit('getUserNick', res.data.nickname)
          router.push({ name: "Home" });
        }
        
      });
      
      
    }; 
   
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
