<template>
  <div class="wrap-blue">
    <h1>폭탄돌리기</h1>
    <div class="round-time-bar" data-style="smooth">
      <div></div>
    </div>
    <div class="wrap-boom">
      <div ref="boom" class="case boom"></div>
    </div>
    <button @click="handleBoom" class="game-btn">폭탄 돌리기</button>
    <button @click="handleBoom" class="game-btn">PASS</button>
  </div>
</template>

<script>
export default {
  name: "BoomGameView",
  data() {
    return {
      boomTime: parseInt(this.$route.params.boomTime),
      nextBoomLocation: 1,
      clientsArray: [],
    };
  },
  created() {
    this.$socket.on("moveBoom", () => {
      this.moveBoom();
    });
    this.$socket.emit("getRoomClientsId");
  },
  mounted() {
    this.makeBoomTimeBar();
    this.$socket.on("sendRoomClientsId", (data) => {
      this.clientsArray = data.roomClients;
      this.clientsArray.forEach((client) => {
        const newBoom = `<div class="case" data-id=${client}>${client}</div>`;
        this.$refs.boom.insertAdjacentHTML("afterend", newBoom);
      });
    });
  },
  methods: {
    makeBoomTimeBar() {
      const roundTimeBar = document.querySelector(".round-time-bar");
      roundTimeBar.setAttribute("style", `--duration: ${this.boomTime}`);
    },
    handleBoom() {
      this.$socket.emit("handleBoom");
    },
    moveBoom() {
      const currnetBoomElement = document.querySelector(".boom");
      const nextBoomElemnt =
        document.querySelectorAll(".case")[
          this.nextBoomLocation % this.clientsArray.length
        ];
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
.round-time-bar {
  margin: 3rem;
  overflow: hidden;
}
.round-time-bar div {
  height: 20px;
  animation: roundtime calc(var(--duration) * 1s) steps(var(--duration))
    forwards;
  transform-origin: left center;
  background: linear-gradient(to bottom, rgb(255, 64, 0), rgb(59, 143, 239));
  border-radius: 30px;
}

.round-time-bar[data-style="smooth"] div {
  animation: roundtime calc(var(--duration) * 1s) linear forwards;
}

@keyframes roundtime {
  to {
    transform: scaleX(0);
  }
}
</style>
