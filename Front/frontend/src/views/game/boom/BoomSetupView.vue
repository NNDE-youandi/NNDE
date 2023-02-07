<template>
  <div class="wrap-blue">
    <h1>폭탄돌리기</h1>
    <div style="text-align: center">
      <h3>폭탄 시간 입력</h3>
      <form @submit="selectBoomTime" action="submit">
        5<input type="range" name="favnum" min="5" max="300" />300(초)
        <div>
          <button>선택 후 시작</button>
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
    };
  },
  created() {
    this.$socket.on("sendBoomStage", (data) => {
      this.$router.push({
        name: data.url,
        params: { boomTime: data.boomTime },
      });
    });
  },
  methods: {
    selectBoomTime(event) {
      event.preventDefault();
      this.boomTime = event.target[0].value;
      this.$socket.emit("goBoomStage", {
        boomTime: this.boomTime,
      });
    },
  },
};
</script>

<style></style>
