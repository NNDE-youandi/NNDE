import { apiInstance } from "./index";

const api = apiInstance();

function requestSurvey(callback,errorCallback){
    api.get(`/survey/random`)
    .then(callback)
    .catch(errorCallback)
}
function requestAnswer(answer,callback,errorCallback){
    api.post(`/answer/save`,JSON.stringify(answer))
    .then(callback)
    .catch(errorCallback)
}
export {requestSurvey, requestAnswer};