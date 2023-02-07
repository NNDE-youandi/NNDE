<template>
  <div class="wrap-blue">
    <h1>폭탄 터짐</h1>
    <h2>{{ boomedSocket }}</h2>
    <button @click="moveSelectGame">목록으로</button>
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
        name: "SelectGame",
      });
    },
  },
};
</script>

<style>
</style>