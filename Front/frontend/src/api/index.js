import axios from "axios";

// local vue api axios instance
function apiInstance() {
    const instance = axios.create({
        baseURL: "http://localhost:3000/",
        headers: {
        "Content-Type": "application/json",
        },
    });
    return instance;
}

export {apiInstance};