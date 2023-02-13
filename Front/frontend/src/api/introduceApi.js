import { apiInstance } from "./index";

const api = apiInstance();

function requestKeyword(response,error){
    api.get(`/introduce/keyword`)
    .then(response)
    .catch(error)
}

export{ requestKeyword };