<template>
  <div class="wrap-blue">
    <h1>폭탄돌리기</h1>
    <h4>{{ boomWord.bombQuestion }}</h4>
    <div class="round-time-bar" data-style="smooth">
      <div></div>
    </div>
    <div class="wrap-boom">
      <div class="wrap-ani-img">
        <img src="../../../assets/ani_1.png" class="ani-img case boom" />
        {{ clientsArray[0] }}
      </div>
      <div
        class="wrap-ani-img"
        v-for="(client, idx) in clientsArray.slice(1)"
        :key="idx"
      >
        <img :src="this.images[idx].url" alt="icon" class="ani-img case" />
        {{ client }}
      </div>
    </div>
    <!-- moo -->
    <div
      v-if="
        this.clientsArray[
          (this.nextBoomLocation - 1) % this.clientsArray.length
        ] === this.myNick
      "
      class="wrap-game-boom-btn"
    >
      <h3 @click="handleBoom" class="game-btn">폭탄 돌리기</h3>
      <h3 @click="passBoom" class="game-btn">PASS</h3>
    </div>
  </div>

  
  <!-- 음악 플레이어 -->
  <audio autoplay>
    <source src="@/../public/tick-tock.mp3" type="audio/mp3">
    이 문장은 audio 태그를 지원되지 않을 경우 화면에 표시됩니다.
  </audio>

</template>

<script>
import { requestBoomGame } from "@/api/gameApi";
// import { useStore } from "vuex";
export default {
  name: "BoomGameView",
  data() {
    return {
      boomTime: parseInt(this.$route.params.boomTime),
      nextBoomLocation: 1,
      clientsArray: [],
      boomWord: "",
      // store:useStore(),
      // moo
      myNick: "",
      images: [
        {
          url: require("../../../assets/ani_2.png"),
        },
        {
          url: require("../../../assets/ani_3.png"),
        },
        {
          url: require("../../../assets/ani_4.png"),
        },
        {
          url: require("../../../assets/ani_5.png"),
        },
        {
          url: require("../../../assets/ani_6.png"),
        },
      ],
    };
  },
  created() {
    this.handleBoom();
    this.$socket.on("sendRoomClientsId", (data) => {
      this.clientsArray = data.roomClients;
    });
    this.$socket.on("resHandleBoom", (data) => {
      this.boomWord = data;
      this.moveBoom();
    });
    this.$socket.on("resPass", (data) => {
      this.boomWord = data;
    });
    this.$socket.emit("getRoomClientsId");
    setTimeout(() => {
      this.$router.push({
        name: "BoomEnd",
        params: {
          boomedSocket:
            this.clientsArray[
              (this.nextBoomLocation - 1) % this.clientsArray.length
            ],
        },
      });
    }, this.boomTime * 1000);
    // moo
    this.$socket.on("sendMyNick", (myNick) => {
      this.myNick = myNick;
    });
    this.$socket.emit("getMyNick");
    // this.bgm();
  },
  mounted() {
    this.makeBoomTimeBar();
  },
  methods: {
    makeBoomTimeBar() {
      const roundTimeBar = document.querySelector(".round-time-bar");
      roundTimeBar.setAttribute("style", `--duration: ${this.boomTime}`);
      // this.bgm();
    },
    passBoom() {
      requestBoomGame((res) => {
        this.$socket.emit("callPass", res.data);
      });
    },
    handleBoom() {
      requestBoomGame((res) => {
        this.$socket.emit("callHandleBoom", res.data);
      });
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
    
    // bgm() {
    //   this.store.dispatch("iceBreakingStore/PLAY_BOMB_SOUND")
    // },
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
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 10%;
}
.wrap-ani-img {
  text-align: center;
}
.case {
  opacity: 0.5;
  text-align: center;
  margin: 0 auto;
  /* font-size: 10px;
  font-weight: bolder;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: black 3px solid;
  background-color: white; */
}
.boom {
  background-color: rgb(255, 1, 1);
  opacity: 1 !important;
  width: 70px !important;
  height: 70px !important;
  text-align: center;
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
.ani-img {
  width: 45px;
  height: 45px;
  display: block;
}
@keyframes roundtime {
  to {
    transform: scaleX(0);
  }
}
</style>
