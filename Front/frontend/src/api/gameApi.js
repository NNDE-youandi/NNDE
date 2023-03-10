import { apiInstance } from "./index";

const api = apiInstance();

function requestBalanceGame(callback, errorCallback) {
  api.get(`/balance/question`).then(callback).catch(errorCallback);
}
// 라이어 게임 (themelist 받아오기)
function requestLiarGameTheme(callback, errorCallback) {
  api.get(`/liar/type`).then(callback).catch(errorCallback);
}
// 라이어 게임 (theme 보내고 word 받기)
function requestLiarGameWord(data, callback, errorCallback) {
  api.get(`/liar/word/${data}`).then(callback).catch(errorCallback);
}
function requestBoomGame(callback, errorCallback) {
  api.get(`/bomb/question`)
  .then(callback)
  .catch(errorCallback)
} 
function requestRandomNick(callback,errorCallback){
  api.get(`/randomnick/name`)
  .then(callback)
  .catch(errorCallback)
}
export { requestBoomGame, requestBalanceGame, requestLiarGameTheme, requestLiarGameWord,requestRandomNick };
