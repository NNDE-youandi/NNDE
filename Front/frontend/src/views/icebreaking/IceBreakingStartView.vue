<template>
  <div class="wrap-blue">
    <h2>Step1</h2>
    <h2>나를소개해봐</h2>
<<<<<<< HEAD
    <p class="explanation"> 한명씩 돌아가며 30초씩 자기소개를  해보아요!  자신의 차례에 화면에 보이는 키워드를 넣어서 자기소개를 하면 된답니다. 시간이 남는다면 자신의 다른 부분을 소개해도 좋아요! </p>
=======
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
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