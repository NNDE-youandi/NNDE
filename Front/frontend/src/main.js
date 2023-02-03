import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import io from 'socket.io-client';
// AWS 서버 IP
// const socket = io('http://i8c209.p.ssafy.io:3001', {
// 로컬 서버 IP
const socket = io('http://localhost:3001', {
  // 폴링 대신 웹소켓만 사용, 폴링 사용시 CORS 에러 발생
  transports: ["websocket"]
});
const app = createApp(App)
// Vue.prototype.$socket = socket;
// Vue.config.productionTip = false
app.use(store).use(router).mount('#app')
app.config.globalProperties.$socket = socket

