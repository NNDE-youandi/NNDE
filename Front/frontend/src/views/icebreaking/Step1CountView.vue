<template>
  <div class="wrap-blue">
<<<<<<< HEAD
    <h2>Step1</h2>
    <h2>나를소개해봐</h2>
    <p class="count">{{ timerCount }}</p>
=======
    <h1>카운트다운</h1>
    <h1>{{ timerCount }}</h1>
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
  </div>
</template>

<script>
import router from '@/router';
import { ref, watch } from 'vue';
export default {
  setup() {
    const timerCount = ref(5)

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