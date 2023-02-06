<template>
  <div class="wrap-blue">
    <h1>폭탄돌리기</h1>
    <div class="wrap-boom">
      <div class="case boom"></div>
    </div>
    <button @click="handleBoom" class="game-btn">폭탄 돌리기</button>
  </div>
</template>

<script>
export default {
  name: "BoomGameView",
  data() {
    return {
      nextBoomLocation: 1,
    };
  },
  mounted() {
    this.$socket.emit("getBoomNumber");
  },
  created() {
    this.$socket.on("moveBoom", () => {
      this.moveBoom();
    });
  },
  methods: {
    handleBoom() {
      this.$socket.emit("handleBoom");
    },
    moveBoom() {
      const currnetBoomElement = document.querySelector(".boom");
      const nextBoomElemnt =
        document.querySelectorAll(".case")[this.nextBoomLocation % 6];
      currnetBoomElement.classList.remove("boom");
      nextBoomElemnt.classList.add("boom");
      this.nextBoomLocation += 1;
    },
  },
};
</script>

<style scoped>
.wrap-boom {
  display: grid;
  justify-items: center;
  align-items: center;
  grid-template-rows: 1fr 1fr;
  grid-template-columns: 1fr 1fr 1fr;
  width: 70%;
  height: 50vh;
  margin: 0 auto;
  background-color: rgb(225, 216, 216);
}
.case {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: black 3px solid;
  background-color: white;
}
.boom {
  background-color: rgb(43, 76, 226);
}
</style>
