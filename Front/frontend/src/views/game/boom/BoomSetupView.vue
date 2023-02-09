<template>
  <div class="wrap-blue">
    <h1>폭탄돌리기</h1>
    <div v-if="isHost" style="text-align: center">
      <h3 class="title-input-boom-time">폭탄 시간 입력</h3>
      <form class="wrap-input" @submit="selectBoomTime" action="submit">
        <span class="view-limit-time">5초</span>
        <input
          id="inputTime"
          type="range"
          name="favnum"
          min="5"
          max="300"
          @input="viewCurrentTime"
        />
        <span class="view-limit-time">300초</span>
        <div class="view-time" id="viewTime">{{ defaultTime }}초</div>
        <div>
          <img
            @click="selectBoomTime"
            class="btn-img"
            src="./../../../assets/next_btn.png"
            alt="next-btn"
          />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoomSetupView",
  data() {
    return {
      boomTime: 5,
      defaultTime: 60,
      isHost: false,
    };
  },
  created() {
    this.$socket.on("resMoveBoomStage", (data) => {
      this.$router.push({
        name: data.url,
        params: { boomTime: data.boomTime },
      });
    });
    this.$socket.on("sendIsHost", (isHost) => {
      this.isHost = isHost;
    });
    this.$socket.emit("getIsHost");
  },
  methods: {
    selectBoomTime() {
      this.$socket.emit("callMoveBoomStage", {
        boomTime: this.boomTime,
      });
    },
    viewCurrentTime(event) {
      const currentBoomTime = event.target.value;
      document.getElementById("viewTime").innerHTML = `${currentBoomTime}초`;
      this.boomTime = currentBoomTime;
    },
  },
};
</script>

<style scoped>
.title-input-boom-time {
  margin: 10vh 0 5vh 0;
}
.view-time {
  margin-top: 20px;
  font-size: 24px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 2px black;
}
.view-limit-time {
  margin: 10px;
  font-size: 18px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 2px black;
}
.wrap-input {
  padding: 5vh;
  margin: 0 auto;
  width: 60%;
  height: 10vh;
  background-color: rgb(171, 158, 149);
  border-radius: 5%;
  border: white 2px solid;
}
</style>
