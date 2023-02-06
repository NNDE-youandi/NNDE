<template>
  <div class="wrap-blue">
    <div class="wrap-mode-btn">
      <img src="../../assets/ice_btn.png" @click="goMakeRoomIce" />
      <img src="../../assets/game_btn.png" @click="goSelectGame" />
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  setup() {
    const goMakeRoomIce = () => {
      router.push({ name: "MakeRoomIce", params: { modeName: "Survey" } });
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

<style></style>
