<template>
  <div class="wrap-blue">
    <h4>회원가입</h4>
    <div class="wrap-inputs-signup">
      <div class="input-with-label">
        <label for="user_email">이메일 </label>
        <div>
          <input
            type="email"
            class="input-email"
            id="user_email"
            v-model="state.credentials.email"
          />
          <div class="input-btn" @click="btnCheckEmail">중복확인</div>
        </div>
        <p v-show="state.valid.email" class="input-error">
          이메일 주소를 정확히 입력해주세요.
        </p>
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
          <div class="input-btn" @click="checkNick">중복확인</div>
        </div>
      </div>
      <div class="input-with-label">
        <label for="user_password"
          >비밀번호
          <div>
            <input
              type="password"
              class="input-signup-password"
              id="user_password"
              v-model="state.credentials.password"
            />
          </div>
          <p v-show="state.valid.password" class="input-error">
            비밀번호는 숫자와 특수문자를 포함해야 합니다.
          </p>
        </label>
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
    <img src="../../assets/join2_btn.png" @click="checkTotal" class="btn-img" />
  </div>
</template>

<script>
import router from "@/router";
import * as EmailValidator from "email-validator";
import { ref } from "vue";
import {
  requestJoin,
  requestBtnCheckEmail,
  requestBtnCheckNick,
} from "../../api/userApi.js";
import { requestRandomNick } from "@/api/gameApi";

export default {
  setup() {
    const state = ref({
      credentials: {
        email: null,
        password: null,
        checkedpassword: null,
        nickname: null,
      },
      valid: {
        email: false,
        checkemail: ref(false),
        nickname: ref(false),
        password: ref(false),
        totalCheck: ref(false),
      },
      error: {
        email: false,
        password: false,
        nickname: false,
        passwordConfirm: false,
        term: false,
      },
    });
    const randomNick = () => {
      requestRandomNick((res) => {
        state.value.credentials.nickname = "익명의 " + res.data.randomNickname;
      });
    };
    randomNick()
    const checkEmail = () => {
      let isValid = EmailValidator.validate(state.value.credentials.email);
      state.value.valid.email = !isValid;
    };

    const isJoin = ref(false);
    const join = () => {};
    const btnCheckEmail = () => {
      requestBtnCheckEmail(state.value.credentials.email, (res) => {
        if (res.data) {
          alert("이미 존재하는 이메일입니다.");
        } else {
          alert("사용할 수 있는 이메일입니다.");
          state.value.valid.checkemail = true;
        }
      });
    };
    const checkNick = () => {
      requestBtnCheckNick(state.value.credentials.nickname, (res) => {
        if (res.data) {
          alert("이미 존재하는 닉네임입니다.");
        } else {
          alert("사용할 수 있는 닉네임입니다.");
          state.value.valid.nickname = true;
        }
      });
    };
    const checkPasswordTool = (value) => {
      const regex =
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,16}$/;
      if (regex.test(value)) {
        return true;
      } else {
        return false;
      }
    };
    const checkPassword = () => {
      let isValid = checkPasswordTool(state.value.credentials.password);
      state.value.valid.password = !isValid;
    };
    const checkTotal = () => {
      if (
        state.value.valid.checkemail &&
        state.value.valid.nickname &&
        state.value.credentials.password ===
          state.value.credentials.checkedpassword
      ) {
        state.value.valid.totalCheck = true;
        requestJoin(state.value.credentials, () => {
          router.push({ name: "Login" });
        });
      } else if (!state.value.valid.checkemail) {
        alert("이메일 중복체크를 해주세요.");
      } else if (!state.value.valid.nickname) {
        alert("닉네임 중복체크를 해주세요.");
      } else {
        alert("비밀번호가 다릅니다.");
      }
    };

    return {
      state,
      join,
      checkEmail,
      btnCheckEmail,
      checkNick,
      isJoin,
      checkTotal,
      checkPassword,
      randomNick,
    };
  },
  watch: {
    "state.credentials.email": {
      handler() {
        this.checkEmail();
      },
      deep: true,
    },
    "state.credentials.password": {
      handler() {
        this.checkPassword();
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
.input-btn {
  display: inline-block;
  font-size: 14px;
  color: white;
  text-shadow: 1px 2px 1px black;
  border: 2px solid black;
  background-color: #0c8ecf;
  padding: 8px;
  margin: 0 4px;
}
</style>
