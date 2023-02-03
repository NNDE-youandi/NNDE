<template>
  <div class="wrap-blue">
    <h1>회원가입</h1>
    <div class="wrap-inputs-signup">
      <div class="input-with-label">
        <label for="user_email"
          >이메일
          <div>
            <input
              type="email"
              class="input-email"
              id="user_email"
              v-model="state.credentials.email"
            />
            <button>중복확인</button>
          </div>
          <p v-show="state.valid.email" class="input-error">
            이메일 주소를 정확히 입력해주세요.
          </p>
        </label>
      </div>
      <div class="input-with-label">
        <label for="user_nickname">닉네임 </label>
        <div>
          <input
            type="text"
            class="input-nickname"
            id="user_nickname"
            v-model="state.credentials.nickname"
          />
          <button>중복확인</button>
        </div>
      </div>
      <div class="input-with-label">
        <label for="user_password">비밀번호 </label>
        <div>
          <input
            type="password"
            class="input-signup-password"
            id="user_password"
            v-model="state.credentials.password"
          />
        </div>
      </div>
      <div class="input-with-label">
        <label for="user_password">비밀번호 확인 </label>
        <div>
          <input
            type="password"
            class="input-signup-password"
            id="user_password_confirm"
            v-model="state.credentials.checkedpassword"
          />
        </div>
      </div>
    </div>
    <img src="../../assets/join_btn.png" @click="join" class="btn-img">
  </div>
</template>

<script>
import router from "@/router";
import * as EmailValidator from "email-validator";
import { ref } from "vue";
import { requestJoin } from "../../api/userApi.js";

export default {
  setup() {
    const state = ref({
      credentials: {
        email: null,
        nickname: null,
        password: null,
        checkedpassword: null,
      },
      valid: {
        email: false,
      },
      error: {
        email: false,
        password: false,
        nickname: false,
        passwordConfirm: false,
        term: false,
      },
    });

    const checkEmail = () => {
      let isValid = EmailValidator.validate(state.value.credentials.email);
      state.value.valid.email = !isValid;
    };

    const join = () => {
      requestJoin(state.value.credentials, (res) => {
        console.log(res)
        router.push({ name: "Login" });
        //통신을 통해 전달받은 값 콘솔에 출력
        console.log(res);
      });
    };
    return {
      state,
      join,
      checkEmail,
    };
  },
  watch: {
    "state.credentials.email": {
      handler() {
        this.checkEmail();
      },
      deep: true,
    },
  },
};
</script>

<style>
</style>
