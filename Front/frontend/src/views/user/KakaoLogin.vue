<template>
  <div>
    {{ $route.query.code }}
  </div>
</template>

<script>
import router from "@/router";
import { ref } from "vue";
import { requestKakaoCode } from "../../api/userApi.js";
import { useStore } from "vuex";

export default {
    
    created() {
        const code = ref("");
        const store = useStore();
        const setIsLoginTrue = () => store.commit("userStore/SET_IS_LOGIN_TRUE");
        const userinfo = ref([]);
        const setUserInfo = () => store.commit("userStore/SET_USER_INFO", userinfo);
        code.value = this.$route.query.code
        console.log("route.query.code : ",code.value);
        requestKakaoCode(code.value,(res)=>{
            console.log("res : ",res);
            // userInfo 저장 
            userinfo.value.push(res.data);
            setUserInfo();
            setIsLoginTrue();
            // $socket.emit("getUserNick", res.data.nickname);
            // redirect로 홈으로 보내면 로그인이 되어잇겠쬬 ?
            router.push({ name: "Home" });
        })
    }
}
</script>

<style>

</style>