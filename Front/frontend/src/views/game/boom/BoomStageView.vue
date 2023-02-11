<template>
  <div class="wrap-blue">
    <h1>폭탄돌리기</h1>
    <h3>{{boomWord.bombQuestion}}</h3>
    <div class="round-time-bar" data-style="smooth">
      <div></div>
    </div>
    <div class="wrap-boom">
      <div class="case boom">{{ clientsArray[0] }}</div>
      <div
        v-for="(client, idx) in clientsArray.slice(1)"
        :key="idx"
        class="case"
      >
        {{ client }}
      </div>
    </div>
    <div class="wrap-game-boom-btn">
      <h3 @click="handleBoom" class="game-btn">폭탄 돌리기</h3>
      <h3 @click="passBoom" class="game-btn">PASS</h3>
    </div>
  </div>
</template>

<script>
import { requestBoomGame } from "@/api/gameApi"
export default {
  name: "BoomGameView",
  data() {
    return {
      boomTime: parseInt(this.$route.params.boomTime),
      nextBoomLocation: 1,
      clientsArray: [],
      boomWord: ""
    };
  },
  created() {
    this.handleBoom();
    this.$socket.on("sendRoomClientsId", (data) => {
      this.clientsArray = data.roomClients;
    });
    this.$socket.on("resHandleBoom", (data) => {
      this.boomWord = data
      this.moveBoom();
    });
    this.$socket.on("resPass", (data) => {
      this.boomWord = data
    });
    this.$socket.emit("getRoomClientsId");
    setTimeout(() => {
      console.log(this.clientsArray[this.nextBoomLocation - 1])
      console.log(this.clientsArray)
      console.log(this.nextBoomLocation - 1)
      
      this.$router.push({
        name: "BoomEnd",
        params: { boomedSocket: this.clientsArray[(this.nextBoomLocation - 1) % (this.clientsArray.length)] },
      });
    }, this.boomTime * 1000);
  },
  mounted() {
    this.makeBoomTimeBar();
  },
  methods: {
    makeBoomTimeBar() {
      const roundTimeBar = document.querySelector(".round-time-bar");
      roundTimeBar.setAttribute("style", `--duration: ${this.boomTime}`);
    },
    passBoom() {
      requestBoomGame((res) => {
         this.$socket.emit("callPass", res.data);
      })
    },
    handleBoom() {
       requestBoomGame((res) => {
         this.$socket.emit("callHandleBoom", res.data);
      })
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
.wrap-game-boom-btn {
  display: flex;
}
.wrap-boom {
  display: grid;
  justify-items: center;
  align-items: center;
  grid-template-rows: 1fr 1fr;
  grid-template-columns: 1fr 1fr 1fr;
  width: 70%;
  height: 50vh;
  margin: 0 auto;
  background-color: rgb(255, 255, 255);
  border-radius: 10%;
}
.case {
  font-size: 10px;
  font-weight: bolder;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: black 3px solid;
  background-color: white;
}
.boom {
  background-color: rgb(226, 83, 43);
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
