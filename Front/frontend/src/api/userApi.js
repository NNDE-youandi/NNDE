/*
 User API 예시
 */
 import { apiInstance } from "./index";

 const api = apiInstance();
 
 function requestLogin(data, callback, errorCallback) {
     api.post(`/user/login`, JSON.stringify(data))
     .then(callback)
     .catch(errorCallback)
 }
 
 function requestJoin(data, callback, errorCallback) {
     api.post(`/user/join`, JSON.stringify(data))
     .then(callback)
     .catch(errorCallback)
 }
 //subin 
 function requestBtnCheckEmail(email,callback,errorCallback){
    api.post(`/user/checkEmail`,JSON.stringify(email))
    .then(callback)
    .catch(errorCallback)
 }
 
 function requestBtnCheckNick(nickname, callback, errorCallback) {
    api.post(`/user/checkNickname`,JSON.stringify(nickname))
    .then(callback)
    .catch(errorCallback)
 }

 function requestLogout(logoutdata,callback,errorCallback){
    api.post(`/user/logout`,JSON.stringify(logoutdata))
    .then(callback)
    .catch(errorCallback)
 }

 // kakao login [인가코드]
 function requestKakaoLogin(callback,errorCallback){
   api.get(`/social/login`)
   .then(callback)
   .catch(errorCallback)
 }

 function requestKakaoCode(code,callback,errorCallback){
   api.get(`/social/login/kakao/callback/${code}`)
   .then(callback)
   .catch(errorCallback)
 }
 
 export {requestJoin, requestLogin,requestBtnCheckEmail, requestBtnCheckNick,requestLogout,requestKakaoLogin,requestKakaoCode};