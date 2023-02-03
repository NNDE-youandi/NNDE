<template>
  <div class="wrap-blue">
    <h1>{{ $route.params.name }}</h1>
    <h1>Step2 날 찾아봐!</h1>
    <img src="../../assets/start_btn.png" class="btn-img" @click="goStep2CountView">
  </div>
</template>

<script>
import router from '@/router';

export default {

  setup() {
    
    const goStep2CountView = () => {
      router.push({name:"Step2Count"})
    }
    return {
      goStep2CountView,
      
    }
  }

}
</script>

<style>

</style>