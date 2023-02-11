import { apiInstance } from "./index";

const api = apiInstance();
function requestSurveyQuiz(nickname,callback, errorCallback){
    api.post(`/surveyQuiz/get`,JSON.stringify(nickname))
    .then(callback)
    .catch(errorCallback);
}
export {requestSurveyQuiz}; 