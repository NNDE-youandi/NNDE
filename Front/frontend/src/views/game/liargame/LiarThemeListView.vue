<template>
  <div class="wrap-blue">
    <h3>라이어 게임</h3>
    <h4>테마 선택: {{ liarSubject }}</h4>
    <div class="wrap-themelist">
      <div
        @click="pickSubject(subject)"
        v-for="(subject, idx) in subjects"
        :key="idx"
        class="theme"
      >
        {{ subject }}
      </div>
    </div>

    <br />
    <img
      src="../../../assets/start_btn.png"
      class="start-btn"
      @click="goLiarList"
      v-if="isHost"
    />
  </div>
</template>

<script>
import router from "@/router";
import { getCurrentInstance, ref } from "vue";
import { requestLiarGameTheme, requestLiarGameWord } from "@/api/gameApi";

export default {
  //이 페이지에서 단어까지 정해서 보내주기?
  setup() {
    const app = getCurrentInstance();
    const subjects = ref([]);
    const $socket = app.appContext.config.globalProperties.$socket;
    const isHost = ref();
    //방장이 고른 주제 liarSubject에 저장
    const liarSubject = ref("");
    const liarWord = ref("");
    const getLiarTheme = () => {
      requestLiarGameTheme((res) => {
        subjects.value = res.data;
      });
    };
    getLiarTheme();

    //버튼을 누를 때 소켓으로 어떤 버튼을 눌렀는지 데이터 전송
    const pickSubject = (subject) => {
      liarSubject.value = subject;
      $socket.emit("sendLiarGame", subject);
    };

    //라이어 게임 시작
    const goLiarList = () => {
      requestLiarGameWord(liarSubject.value, (res) => {
        liarWord.value = res.data.lgWord;
        $socket.emit("goLiarList", liarSubject.value, res.data.lgWord);
      });
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
      liarWord,
      pickSubject,
      goLiarList,
    };
  },
};
</script>

<style>
.wrap-themelist {
  display: grid;
  justify-items: center;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  font-size: 20px;
  font-family: bitbit;
  margin-top: 30px;
}
.theme{
  padding-top: 20px;
}
.start-btn {
  display: block;
  margin: 23px auto;
}
</style>