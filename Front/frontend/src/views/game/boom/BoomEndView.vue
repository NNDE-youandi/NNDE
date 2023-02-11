<template>
  <div class="wrap-blue">
    <h1>폭탄 터짐</h1>
    <div class="boom-boom-emoji">💥💣</div>
    <h4>{{ boomedSocket }}</h4>
    <img
      class="btn-img"
      @click="moveSelectGame"
      src="./../../../assets/back_btn.png"
      alt="back-btn"
    />
  </div>
</template>

<script>
export default {
  name: "BoomEndView",
  data() {
    return {
      boomedSocket: this.$route.params.boomedSocket,
    };
  },
  methods: {
    moveSelectGame() {
      this.$router.push({
        name: "Home",
      });
    },
  },
};
</script>

<style scoped>
.boom-boom-emoji {
  width: 60%;
  margin: 0 auto;
  font-size: 100px;
}
</style>
