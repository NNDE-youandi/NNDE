import { apiInstance } from "./index";

const api = apiInstance();

function requestBalanceGame(callback,errorCallback){
    api.get(`/balance/question`)
    .then(callback)
    .catch(errorCallback)
}
export {requestBalanceGame};