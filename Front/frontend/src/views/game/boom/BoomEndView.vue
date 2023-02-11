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
<<<<<<< HEAD
        name: "Home",
=======
        name: "SelectGame",
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
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
