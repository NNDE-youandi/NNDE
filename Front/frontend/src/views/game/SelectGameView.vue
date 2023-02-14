<template>
  <div class="wrap-blue">
    <div class="modal">
      <div class="modal-body">
        <button @click="toggleGameInfo">X</button>
        <h3>Game Info</h3>
        <div>
          <div>밸런스</div>
          <p>
            밸런스가 맞춰진 두 가지의 선택지에 대해서 친구들과 얘기해보아요!
          </p>
        </div>
        <div>
          <div>폭탄돌리기</div>
          <p>
            폭탄을 가지고 있는 사람이 키워드를 가지고 얘기를 해보아요! 한 문장
            이상 얘기를 해야만 다음 사람에게 폭탄을 넘길 수 있어요!
          </p>
        </div>
        <div>
          <div>라이어</div>
          <p>
            누군가가 다른 단어를 가지고 있지만 같은 단어를 가지고 있다고 거짓말
            하고 있어요! 라이어를 찾아주세요! 단, 라이어에게 단어에 대한 힌트를
            너무 주면 안 돼요!
          </p>
        </div>
      </div>
    </div>
    <div>
      <button class="game-info btn-open-modal" @click="toggleGameInfo">
        ❔
      </button>
    </div>
    <h1>게임 목록</h1>
    <div>
      <div class="game-btn" @click="goBalanceGame">밸런스</div>
      <div class="game-btn" @click="goBoomGame">폭탄돌리기</div>
      <div class="game-btn" @click="goLiarGame">라이어</div>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { onMounted, ref } from "vue";
export default {
  setup() {
    const modal = ref(null);
    const body = ref(null);
    onMounted(() => {
      modal.value = document.querySelector(".modal");
      body.value = document.querySelector("body");
    });
    const goBoomGame = () => {
      router.push({ name: "MakeRoom", params: { modeName: "BoomSetup" } });
    };
    const goLiarGame = () => {
      router.push({
        name: "MakeRoom",
        params: { modeName: "LiarThemeList" },
      });
    };
    const goBalanceGame = () => {
      router.push({ name: "MakeRoom", params: { modeName: "Balance" } });
    };
    const toggleGameInfo = () => {
      modal.value.classList.toggle("show");
      if (modal.value.classList.contains("show")) {
        body.value.style.overflow = "hidden";
      } else {
        body.value.style.overflow = "auto";
      }
    };
    return {
      goBoomGame,
      toggleGameInfo,
      goLiarGame,
      goBalanceGame,
    };
  },
};
</script>

<style scoped>
.game-info {
  cursor: pointer;
  position: absolute;
  top: 10px;
  left: calc(100% - 60px);
  width: 50px;
  height: 50px;
  background-color: white;
  border-radius: 20%;
}
.game-btn {
  margin: 50px auto;
  font-size: 48px;
  text-align: center;
  width: 60%;
}
.modal {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: none;
  background-color: rgba(0, 0, 0, 0.4);
}

.show {
  display: block;
}

.modal-body {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 200px;
  height: 300px;
  padding: 40px;
  text-align: center;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
  transform: translateX(-50%) translateY(-50%);
}
</style>
