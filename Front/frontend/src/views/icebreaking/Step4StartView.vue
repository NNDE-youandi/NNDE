<template>
    <div class="wrap-blue">
      <h2>Step4 라이어게임</h2>
      <img
        src="../../assets/start_btn.png"
        class="btn-img"
        @click="goLiar"
      />
    </div>
  </template>
    
  <script>
  import router from "@/router";
  
  export default {
    setup() {
      const goLiar = () => {
        router.push({ name: "LiarThemeList" });
      };
      return {
        goLiar,
      };
    },
  };
  </script>
    
    <style>
  </style>