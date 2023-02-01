import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import io from 'socket.io-client';

const socket = io('http://localhost:3001', {
  // 폴링 대신 웹소켓만 사용, 폴링 사용시 CORS 에러 발생
  transports: ["websocket"]
});
const app = createApp(App)
// Vue.prototype.$socket = socket;
// Vue.config.productionTip = false
app.use(store).use(router).mount('#app')
app.config.globalProperties.$socket = socket

