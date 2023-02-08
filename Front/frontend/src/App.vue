<template>
  <div id="app">
    <!-- <HeaderView /> -->
    <div>나는 로그인 상태야? {{ isLogin }}</div>
    <router-view> </router-view>
  </div>
</template>

<script>
import "./components/css/style.scss";
import { useStore } from "vuex";
import { ref } from "vue";

// import HeaderView from "./components/common/HeaderView.vue";
export default {
  setup() {
    const store = useStore();
    const isLogin = ref(store.state.userStore.isLogin);
    return {
      isLogin,
    };
  },
};
</script>

