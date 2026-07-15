import API from "../api/axios";


const API_URL = "/notices";


export const getAllNotices = () => {
    return API.get(API_URL);
};


export const createNotice = (notice) => {
    return API.post(API_URL, notice);
};


export const deleteNotice = (id) => {
    return API.delete(`${API_URL}/${id}`);
};