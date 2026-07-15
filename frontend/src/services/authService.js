import API from "../api/axios";


// Login User
export const loginUser = (data) => {

    return API.post("/auth/login", data);

};


// Register New User
export const registerUser = (data) => {

    return API.post("/auth/register", data);

};