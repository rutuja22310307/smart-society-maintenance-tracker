import API from "../api/axios";

const API_URL = "/users";

export const getAllUsers = () => {
    return API.get(API_URL);
};

export const getUserById = (id) => {
    return API.get(`${API_URL}/${id}`);
};

export const updateUser = (id, user) => {
    return API.put(`${API_URL}/${id}`, user);
};

export const deleteUser = (id) => {
    return API.delete(`${API_URL}/${id}`);
};