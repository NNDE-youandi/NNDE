<template>
  <div class="wrap-blue">
    <h2>너나들이</h2>
    <img src="../../assets/ani3.png" class="img-ani3" />
    <div v-if="isLogin">
      <div class="wrap-input-pin">
        <span> PIN:</span>
        <input
          class="input-pin"
          type="number"
          id="pinNumber"
          v-model="inputPin"
        />
        <img src="../../assets/enter_btn.png" @click="submitPin" />
      </div>
      <img
        src="../../assets/makeroom_btn.png"
        class="btn-img"
        @click="goSelectMode"
      />
    </div>
    <div v-else>
      <img src="../../assets/login_btn.png" @click="goLogin" class="btn-img" />
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { ref, getCurrentInstance, computed, onMounted, onBeforeUnmount } from "vue";
import { useStore } from "vuex";

export default {
  setup() {
    const inputPin = ref("");
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const store = useStore();
    const isLogin = computed(() => {
      return store.getters["userStore/GET_IS_LOGIN"];
    });
    const getUserInfo = computed(() => {
      return store.getters["userStore/GET_USER_INFO"];
    });
    // 차후 로그인 여부에 따라 페이지 이동 변화
    const goSelectMode = () => {
      router.push({ name: "SelectMode" });
    };
    const goLogin = () => {
      router.push({ name: "Login" });
    };
    const submitPin = () => {
      $socket.emit("submitPin", {
        pin: inputPin.value,
      });
    };
    const movePinRoom = () => {
      $socket.on("movePinRoom", (data) => {
        router.push({
          name: "RoomWaiting",
          params: { modeName: data.modeName },
        });
        $socket.emit("callCheckParticipant");
      });
    };
    const noRoom = () => {
      $socket.on("noRoom", () => {
        alert("PIN 번호를 다시 입력해주세요");
      });
    };
    const fullRoom = () => {
      $socket.on("fullRoom", () => {
        window.alert("방이 꽉 찼습니다.");
      });
    };
    const getNickId = () => {
      if (isLogin.value === true) {
        $socket.emit("getUserNick", getUserInfo.value[0].nickname);
      }
    }; 
    onMounted(() => {
      const backElement = document.querySelector(".back-btn");
      backElement.style.display = "none"
      const fakeDivElement = document.querySelector(".fake-div");
      fakeDivElement.style.display = "block"
    })
    onBeforeUnmount(() => {
      const backElement = document.querySelector(".back-btn");
      backElement.style.display = "block"
      const fakeDivElement = document.querySelector(".fake-div");
      fakeDivElement.style.display = "none"
    });

    getNickId();
    movePinRoom();
    noRoom();
    fullRoom();

    return {
      goLogin,
      goSelectMode,
      inputPin,
      submitPin,
      isLogin,
      getUserInfo,
    };
  },
};
</script>

<style scoped>
.input-pin {
  width: 60%;
  margin-left: 5px;
  font-size: 24px;
  font-weight: bold;
}
.wrap-input-pin {
  display: flex;
  padding: 15%;
  justify-content: center;
  font-family: bitbit;
  font-size: 30px;
}
.wrap-input-pin > span {
  margin-top: 7px;
}
.wrap-input-pin > input {
  height: 40px;
  text-align: center;
  width: 50%;
}

.wrap-input-pin > img {
  width: 60px;
  height: 35px;
  margin-top: 6px;
  padding: 0;
}
</style>
