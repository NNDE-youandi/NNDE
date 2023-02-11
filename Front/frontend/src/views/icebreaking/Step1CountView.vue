<template>
  <div class="wrap-blue">
    <h2>Step1</h2>
    <h2>나를소개해봐</h2>
    <p class="count">{{ timerCount }}</p>
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