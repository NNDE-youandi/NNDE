<template>
  <div class="wrap-blue">
    <h1>펑!</h1>
    <div class="boom-boom-emoji1">💥</div>
    <div class="boom-boom-emoji2">💣</div>
    <h3> 걸린사람 </h3>
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
      this.$socket.emit("exitRoom")
      this.$router.push({
        name: "Home",
      });
    },
  },
};
</script>

<style scoped>
.boom-boom-emoji1 {
  text-align: center;
  width: 60%;
  margin: 0 auto;
  font-size: 100px;
}
.boom-boom-emoji2 {
  text-align: center;
  width: 60%;
  margin: 0 60% 40px 15%;
  font-size: 100px;
}
</style>
