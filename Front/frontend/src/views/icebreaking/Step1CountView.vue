<template>
  <div class="wrap-blue">
    <h3>Step1</h3>
    <h2>나를 소개해봐</h2>
    <p class="count">{{ timerCount }}</p>
  </div>
</template>

<script>
import router from '@/router';
import { ref, watch, getCurrentInstance } from 'vue';
import { requestKeyword } from "@/api/introduceApi";
export default {
  setup() {
    const app = getCurrentInstance();
    const $socket = app.appContext.config.globalProperties.$socket;
    const timerCount = ref(3)
    const KeywordIntroduce = () => {
      requestKeyword((data) => {
        $socket.emit("getKeyword", data.data.keyword)
      });
    };
    KeywordIntroduce();

    watch(timerCount, () => {
      if (timerCount.value > 0) {
        setTimeout(() => {
            timerCount.value-- ;
          }, 1000);
        }
      else {
        timerCount.value = 'start'
        setTimeout(() => {
          router.push({name:'KeyWord'})
        }, 1000)
      }
      },
      {immediate: true}
    )
    return {
      timerCount,
    }
  },
};
</script>

<style>
</style>