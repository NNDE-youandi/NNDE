<template>
  <div class="wrap-blue">
    <h1>라이어 게임</h1>
  <h2>라이어의 승리입니다.</h2>
  <h2>라이어는 {{liarId}} 입니다.</h2>
  </div>
</template>

<script>
import {ref, getCurrentInstance} from "vue";
export default {
    setup() {
        const app = getCurrentInstance();
        const $socket = app.appContext.config.globalProperties.$socket;
        const liarWord = ref('')
        const liarId = ref('')
        
        const receiveLiar = () => {
            $socket.on("LiarIdData", (liarid, liarword) => {
                liarWord.value = liarword
                liarId.value = liarid
            })
        }
        receiveLiar()
        return {
            liarId,
            liarWord
        }
    }
}
</script>

<style>

</style>