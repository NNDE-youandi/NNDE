<template>
  <div class="wrap-blue">
    <h2>step2. 나를 맞춰봐</h2>
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
          router.push({name:'Step2Quiz'})
        }, 1000)
      }
      },
      {immediate: true}
    )
    return {
      timerCount,
    }
  },

}
</script>

<style>

</style>