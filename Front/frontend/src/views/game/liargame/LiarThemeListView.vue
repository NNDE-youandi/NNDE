<template>
  <div class="wrap-blue">
    <h1>라이어 게임</h1>
    <h1>{{ liarSubject }}</h1>
    <div>
      <button
        @click="pickSubject(subject)"
        v-for="(subject, idx) in subjects"
        :key="idx"
      >
        {{ subject }}
      </button>
    </div>

    <br />
    <button @click="goLiarList" v-if="isHost">시작!</button>
  </div>
</template>

<script>
import router from "@/router";
import { getCurrentInstance, ref } from "vue";

export default {
  //이 페이지에서 단어까지 정해서 보내주기?
  setup() {
    const app = getCurrentInstance();
    const subjects = ["동물", "영화", "인물", "음식", "과일"];
    const $socket = app.appContext.config.globalProperties.$socket;
    const isHost = ref();

    //방장이 고른 주제 liarSubject에 저장
    const liarSubject = ref("");

    //버튼을 누를 때 소켓으로 어떤 버튼을 눌렀는지 데이터 전송
    const pickSubject = (subject) => {
      liarSubject.value = subject;
      console.log(liarSubject.value);
      $socket.emit("sendLiarGame", subject);
    };

    //라이어 게임 시작
    const goLiarList = () => {
      $socket.emit("goLiarList", liarSubject.value);
    };

    //router로 이동시킴.
    $socket.on("goLiarPage", (url) => {
      router.push({ name: url });
    });

    //방장이 누른 버튼 모두가 볼 수 있게 받음
    $socket.on("pickLiarSubject", (data) => {
      liarSubject.value = data;
    });

    //host면 true, guest면 false를 받아옴
    const checkHost = () => {
      $socket.emit("getIsHost");
    };
    const receiveId = () => {
      $socket.on("sendIsHost", (data) => {
        isHost.value = data;
      });
    };
    checkHost();
    receiveId();
    return {
      subjects,
      liarSubject,
      isHost,
      pickSubject,
      goLiarList,
    };
  },
};
</script>

<style>
</style>