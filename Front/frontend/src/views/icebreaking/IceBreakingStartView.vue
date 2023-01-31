<template>
  <div class="wrap-blue">
    <h2>Step1</h2>
    <h2>나를소개해봐</h2>
    <img
      src="../../assets/start_btn.png"
      class="btn-img"
      @click="startStep1"
    />
  </div>
</template>

<script>
import router from '@/router';

export default {
	setup() {
		const startStep1 = () => {
			router.push({name:"Step1Count"})
		}

		return {
			startStep1
		}
	},
};
</script>

<style>
</style>