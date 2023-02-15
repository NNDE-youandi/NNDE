<template>
  <div class="wrap-blue">
  </div>
</template>

<script>
import router from "@/router";
import { ref } from "vue";
import { requestKakaoCode } from "../../api/userApi.js";
import { useStore } from "vuex";

export default {
    
    created() {
        // const app = getCurrentInstance();
        // const $socket = app.appContext.config.globalProperties.$socket;
        const code = ref("");
        const store = useStore();
        const setIsLoginTrue = () => store.commit("userStore/SET_IS_LOGIN_TRUE");
        const userinfo = ref([]);
        const setUserInfo = () => store.commit("userStore/SET_USER_INFO", userinfo);
        code.value = this.$route.query.code

        requestKakaoCode(code.value,(res)=>{
            userinfo.value.push(res.data);
            setUserInfo();
            setIsLoginTrue();
            // $socket.emit("getUserNick", res.data.nickname);
            router.push({ name: "Home" });
        })
    }
}
</script>

<style>
</style>