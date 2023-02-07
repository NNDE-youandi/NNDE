<template>
  <div class="wrap-blue">
    <h1>폭탄 터짐</h1>
    <button @click="moveSelectGame">목록으로</button>
  </div>
</template>

<script>
export default {
  name: "BoomEndView",
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