<template>
  <div class="wrap-blue">
    <div class="wrap-mode-btn">
      <img src="../../assets/ice_btn.png" @click="goMakeRoomIce">
      <img src="../../assets/game_btn.png" @click="goSelectGame">
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
