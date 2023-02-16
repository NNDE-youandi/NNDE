<template>
  <div class="wrap-blue">
    <h4>테마: {{ liarSubject }}</h4>
    <div @click="PickRandom" class="div-btn">나의 키워드 확인하기</div>
    <div class="who-am-i" v-if="isVisible">{{ whoAmI }}</div>
    <h4 style="margin-top: 40px">투표</h4>
    <div
      class="wrap-pickuser"
      v-show="!voted"
      v-for="(user, idx) in userListKey"
      @click="pickUser($event, userListKey[idx])"
      :key="idx"
    >
      {{ user }}: {{ userList[user] }}표
    </div>
    <br />
    <div @click="voteLiar" v-if="!voted" class="vote">투표하기</div>
    <div v-else>
      <div class="vote" @click="changeLiar">다시 투표하기</div>
    </div>
    <br />
    <br />
    <h4>전체 투표 현황</h4>
    <h4>{{ countVote }} / {{ voteTotal }}</h4>
    <div v-if="isHost">
      <div @click="goResult" class="change-liar" v-show="voteDone">
        결과확인
      </div>
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
    const countVote = ref("0");
    const voteDone = ref(false);
    const isVisible = ref(false);
    // 내가 라이어인지 확인
    const PickRandom = () => {
      if (userId.value === liarId.value) {
        whoAmI.value = "라이어";
      } else {
        whoAmI.value = liarWords.value;
      }
      isVisible.value = !isVisible.value;
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
        liarWords.value = liarword;
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
    const pickUser = (event, user) => {
      pickedUser.value = user;
      const voteList = document.getElementsByClassName("wrap-pickuser");
      for (let i = 0; i < voteList.length; i++) {
        if (voteList[i].classList.contains("clicked")) {
          voteList[i].classList.remove("clicked");
        }
      }
      event.target.classList.add("clicked");
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
        window.alert("동률입니다 다시 골라주세요");
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
      isVisible,
    };
  },
};
</script>

<style>
.div-btn {
  font-family: bitbit;
  font-size: 20px;
  border: 2px solid black;
  border-radius: 10px;
  width: 50%;
  text-align: center;
  margin: 20px auto;
  padding: 5px;
}
.wrap-pickuser {
  font-family: bitbit;
  font-size: 24px;
  text-align: center;
  border: 3px solid white;
  color: white;
  text-shadow: 2px 2px 2px black;
  border-radius: 10px;
  width: 50%;
  margin: 10px auto;
  padding: 10px;
  background-color: rgb(72, 120, 223);
}
.vote {
  font-family: bitbit;
  font-size: 35px;
  border: 2px solid crimson;
  border-radius: 10px;
  color: crimson;
  margin: 10px auto;
  width: 50%;
  text-align: center;
  padding: 5px;
}

.clicked {
  background-color: rgb(0, 21, 255);
}
.change-liar {
  font-family: bitbit;
  font-size: 24px;
  text-align: center;
  border: 2px solid black;
  border-radius: 10px;
  width: 50%;
  margin: 10px auto;
  padding: 5px;
}
.who-am-i {
  font-family: bitbit;
  font-size: 28px;
  text-align: center;
  margin: 20px auto;
}
</style>