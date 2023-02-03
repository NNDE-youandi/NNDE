<template>
  <div class="wrap-blue">
    <h1>카운트다운</h1>
    <h1>{{ timerCount }}</h1>
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