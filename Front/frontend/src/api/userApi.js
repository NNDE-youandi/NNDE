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
//  function requestUpdate(data, callback, errorCallback) {
//      api.post(`/account/update, JSON.stringify(data)`)
//      .then(callback)
//      .catch(errorCallback)
//  }
 
 // const requestLogin = (data,callback,errorCallback) => {
 //     //백앤드와 로그인 통신하는 부분
 //     callback();
 
 // }
 
 // const UserApi = {
 //     requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback)
 // }
 
 // export default UserApi
 
 export {requestJoin, requestLogin,requestBtnCheckEmail, requestBtnCheckNick};