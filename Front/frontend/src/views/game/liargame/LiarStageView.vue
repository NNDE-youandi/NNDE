<template>
  <div class="wrap-blue">
    <h1>라이어 게임</h1>
    <h2>{{ liarSubject }}</h2>
    <div @click="PickRandom" class="div-btn">나의 키워드 확인하기</div>
    <h4>{{ whoAmI }}</h4>

    <div class="wrap-pickuser"
      v-show="!voted"
      v-for="(user, idx) in userListKey"
      @click="pickUser(userListKey[idx])"
      :key="idx"
    >
      {{ user }}
      <div>
        득표수: 
        {{ userList[user] }}
      </div>
    </div>
    <br />
    <button @click="voteLiar" v-if="!voted">선택!</button>
    <div v-else>
      <button @click="changeLiar">변경하기</button>
    </div>
    <br />
    <br />
    <h3>전체 투표 현황</h3>
    <h3>{{ countVote }}/{{ voteTotal }}</h3>
    <div v-if="isHost">
      <button @click="goResult" v-show="voteDone">결과 보기</button>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { getCurrentInstance, ref } from "vue";
// import { useRoute } from 'vue-router';
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;

    // 라이어게임 주제
    const liarSubject = ref("");

    // 단어
    const liarWords = ref("");
    //이름은 리스트지만 딕셔너리
    //{'id': 0, 'id2': 0} 의 형태
    const userList = ref();
    //이게 리스트로 된 유저
    const userListKey = ref();
    const userId = ref("");
    const hostId = ref("");
    const liarId = ref();
    const whoAmI = ref("");
    const pickedUser = ref("");
    const isHost = ref("");
    const voted = ref(false);
    const voteTotal = ref("");
    const countVote = ref("");
    const voteDone = ref(false);
    // 내가 라이어인지 확인
    const PickRandom = () => {
      if (userId.value === liarId.value) {
        whoAmI.value = "라이어";
      } else {
        whoAmI.value = liarWords.value;
      }
    };

    // 랜덤 번호를 소캣에서 받아옴(created)
    const startLiar = () => {
      $socket.emit("pickRandom");
    };
    startLiar();

    //아이디를 요청
    const requestId = () => {
      $socket.emit("requestId");
    };

    const sendId = () => {
      $socket.on("sendId", (id) => {
        userId.value = id;
        // hostId.value = hostId
      });
    };

    const getliarWord = () => {
      $socket.on("sendLiarWord", (liarsubject, liarword) => {
        liarSubject.value = liarsubject;
        liarWords.value = liarword
      });
    };
    getliarWord();
    // userList와 랜덤 번호를 받아옴.
    const pickLiar = () => {
      $socket.on("pickLiar", (data) => {
        userList.value = data[0];
        liarId.value = data[1];
        userListKey.value = Object.keys(data[0]);
        voteTotal.value = Object.keys(userList.value).length;
      });
    };
    // 클릭한 버튼을 저장해둠
    const pickUser = (user) => {
      pickedUser.value = user;
    };
    //선택 버튼을 누르면 결과를 반영해 소캣에 보내줌
    const voteLiar = () => {
      userList.value[pickedUser.value] += 1;

      voted.value = true;
      $socket.emit("sendUserList", userList.value);
    };

    // 투표한거 변경할 때 사용
    const changeLiar = () => {
      if (userList.value[pickedUser.value] != 0) {
        userList.value[pickedUser.value] -= 1;
      }
      voted.value = false;
      $socket.emit("sendUserList", userList.value);
    };

    // 투표한 이후 유저 리스트 데이터 받아올 때
    const voteResult = () => {
      $socket.on("voteResult", (data, result) => {
        userList.value = data;
        countVote.value = result;
        if (voteTotal.value === countVote.value) {
          voteDone.value = true;
        }
      });
    };

    // 결과페이지로 이동하게끔.
    const goResult = () => {
      const values = Object.values(userList.value);

      //복수 1등이 있을 수 있으므로.
      const maxVoted = Math.max(...values);
      const votedUser = Object.keys(userList.value).find(
        (key) => userList.value[key] === maxVoted
      );
      let cnt = 0;
      for (let i = 0; i < values.length; i++) {
        if (maxVoted === values[i]) {
          cnt += 1;
        }
      }
      if (cnt === 1) {
        $socket.emit("goResult", liarId.value, liarWords.value, votedUser);
      } else {
        window.alert("한명을 골라주세요");
      }
    };

    $socket.on("goResultPage", (url) => {
      router.push({ name: url });
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
    voteResult();
    requestId();
    sendId();
    pickLiar();

    return {
      liarSubject,
      liarWords,
      userList,
      userListKey,
      userId,
      hostId,
      liarId,
      pickedUser,
      whoAmI,
      voted,
      isHost,
      voteTotal,
      countVote,
      voteDone,
      PickRandom,
      startLiar,
      pickUser,
      voteLiar,
      changeLiar,
      goResult,
    };
  },
};
</script>

<style>
.div-btn {
  font-family: bitbit;
  font-size: 30px;
  border: 2px solid black;
  border-radius: 10px;
  width: 60%;
  text-align: center;
  margin: 20px auto;
}
.wrap-pickuser {
  font-family: bitbit;
  font-size: 30px;
  text-align: center;
}

</style>