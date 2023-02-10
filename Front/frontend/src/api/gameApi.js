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
export { requestBalanceGame, requestLiarGameTheme, requestLiarGameWord };
