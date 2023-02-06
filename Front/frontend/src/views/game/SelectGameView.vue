<template>
  <div class="wrap-blue">
    <div class="modal">
      <div class="modal-body">
        <button @click="toggleGameInfo">X</button>
        <h3>Game Info</h3>
        <div>
          <div>밸런스</div>
          <p>밸런스 게임이란...</p>
        </div>
        <div>
          <div>폭탄돌리기</div>
          <p>폭탄돌리기 게임이란...</p>
        </div>
        <div>
          <div>라이어</div>
          <p>라이어 게임이란...</p>
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
      <div class="game-btn">밸런스</div>
      <div class="game-btn" @click="goBoomGame">폭탄돌리기</div>
      <div class="game-btn">라이어</div>
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
      router.push({ name: "MakeRoomIce", params: { modeName: "BoomSetup" } });
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
    };
  },
};
</script>

<style scoped>
.game-info {
  margin-left: calc(100% - 50px);
  width: 50px;
  height: 50px;
}
.game-btn {
  margin: 50px auto;
  border: black 2px solid;
  background-color: rgb(224, 224, 104);
  color: white;
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
