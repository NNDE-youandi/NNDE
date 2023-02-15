<template>
  <div class="wrap-violet">
    <div class="wrap-tyson">
      <p>“Everyone has a plan, 
        until they get punched in the mouth”</p>
        <p class="tyson">- MIKE TYSON -</p>
    </div>
    <div class="wrap-dev">
      <p>Developer</p>
      <div class="dev-name">고대석</div>
      <div class="dev-name">김송섭</div>
      <div class="dev-name">김수빈</div>
      <div class="dev-name">나웅기</div>
      <div class="dev-name">정무남</div>
      <div class="dev-name">윤호운</div>
    </div>

    <img src="../../assets/home_btn.png" alt="home" @click="goTest" class="btn-img">
    <img src="../../assets/ICE/52_caffe_.jpg" alt="52_caffe" class="caffe">
  </div>
</template>
<script>
import router from "@/router";
import { onBeforeUnmount, onMounted } from "@vue/runtime-core";
export default {
  setup() {
    const goTest = () => {
      router.push({ name: "Home" });
    };

    onMounted(() => {
      const realEndElement = document.querySelector(".wrap-violet");
      const appElement = realEndElement.parentElement;
      const headerView = appElement.querySelector("header");
      headerView.style.backgroundColor = "#C2D8FF";
    });
    onBeforeUnmount(() => {
      const realEndElement = document.querySelector(".wrap-violet");
      const appElement = realEndElement.parentElement;
      const headerView = appElement.querySelector("header");
      headerView.style.backgroundColor = "#65DDF2";
    });
    return {
      goTest,
    };
  },
};
</script>

<style scoped>
header {
  display: none;
}
.wrap-tyson {
  background-color: rgba(255, 255, 255, 0.5);
  margin: 30px 20%;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  padding: 10px;
}
.wrap-dev {
  margin: 30px 20%;
  text-align: center;
  font-size: 22px;
  font-weight: bold;
}
.dev-name {
  margin: 10px 0;
}
.tyson {
  margin: 20px 20%;
  font-size: 15px;
}
.caffe {
  width: 100%;
  height: 100px;
  position: absolute;
  top: calc(90vh - 100px);
}
</style>
