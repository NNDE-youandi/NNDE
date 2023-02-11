<template>
  <div class="wrap-blue">
<<<<<<< HEAD
    <div class="wrap-mode-btn">
      <img src="../../assets/ice_btn.png" @click="goMakeRoomIce">
      <img src="../../assets/game_btn.png" @click="goSelectGame">
=======
    <div>
      <!-- <img src="../../assets/ice_btn.png" @click="goMakeRoomIce" />
      <img src="../../assets/game_btn.png" @click="goSelectGame" /> -->
      <div @click="goMakeRoomIce" class="game-btn mode-btn mode-btn-ice">
        ICE BREAKING
      </div>
      <div @click="goSelectGame" class="game-btn mode-btn mode-btn-game">
        GAME
      </div>
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  setup() {
    const goMakeRoomIce = () => {
      router.push({ name: "MakeRoom", params: { modeName: "Survey" } });
    };
    const goSelectGame = () => {
      router.push({ name: "SelectGame" });
    };
    return {
      goMakeRoomIce,
      goSelectGame,
    };
  },
};
</script>

<style scoped>
.mode-btn {
  margin: 10vh auto;
  width: 60%;
}
.mode-btn-ice {
  font-size: 48px;
}
.mode-btn-game {
  font-size: 56px;
}
</style>
