<template>
  <div class="wrapA">
    <div class="wrap-mode-btn">
      <img src="../../assets/ice_btn.png" @click="goMakeRoomIce">
      <img src="../../assets/game_btn.png">
    </div>
  </div>
</template>

<script>
import router from "@/router";
import { useStore } from "vuex"
import { computed } from 'vue';

export default {
  setup() {
    const store = useStore()
    const test = computed(() => store.state.useStore.test)
    const goMakeRoomIce = () => {
      router.push({ name: "MakeRoomIce" });
      console.log(test)
    }
    return {
      goMakeRoomIce,
    }
  },
  methods: {
   
  }
};
</script>

<style>
</style>