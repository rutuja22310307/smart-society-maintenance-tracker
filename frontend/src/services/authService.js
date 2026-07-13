import API from "../api/axios";


export const loginUser = (data) => {

    return API.post("/auth/login", data);

};