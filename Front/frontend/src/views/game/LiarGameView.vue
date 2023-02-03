<template>
  <div class="wrap-blue">
    <h1>Liar Game</h1>
    <h2>아이디 : {{userid}}</h2>
    <button @click="startLiar">시작</button>
    <button @click="PickRandom">확인</button>
    <h1>{{whoAmI}}</h1>
    <p>유저 목록 : {{userlist}}</p>
    <p>{{randomNum}}</p>
    
  </div>
</template>

<script>
export default {
    name: "LiarGameView",
    data() {
        return {
            LiarWords: "더 글로리",
            userlist: '',
            userid: '',
            hostid:'',
            randomNum: '',
            whoAmI: ''
        }
    },
    methods: {
        PickRandom() {
            if (this.userid === this.userlist[this.randomNum]) {
                this.whoAmI = '라이어'
            } else {
                this.whoAmI = this.LiarWords
            }
        },
        startLiar() {
            this.$socket.emit('pickRandom')
        
        }
    },
    created() {
        // 이 부분은 userdata를 vue에 저장하면 사라지게 됨.
        this.$socket.emit('requestId')
        this.$socket.on('sendId', (id, hostId) => {
            this.userid = id
            this.hostid = hostId
        })

        this.$socket.on('pickLiar', (data) => {
            this.userlist = data[0]
            this.randomNum = data[1]
        })
    }  

}
</script>

<style>

</style>