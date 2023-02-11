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
<<<<<<< HEAD
function requestBoomGame(callback, errorCallback) {
  api.get(`/bomb/question`)
  .then(callback)
  .catch(errorCallback)
} 
export { requestBoomGame, requestBalanceGame, requestLiarGameTheme, requestLiarGameWord };
=======
export { requestBalanceGame, requestLiarGameTheme, requestLiarGameWord };
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
