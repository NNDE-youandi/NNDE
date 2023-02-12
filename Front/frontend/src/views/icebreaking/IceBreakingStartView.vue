<template>
  <div class="wrap-blue">
    <h2>Step1</h2>
    <h2>나를소개해봐</h2>
    <p class="explanation">자신의 순서가 되면 화면에 보이는 키워드를 통해 30초씩 자기소개를  해보아요! 시간이 남는다면 자신의 다른 부분을 소개해도 좋아요! </p>
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