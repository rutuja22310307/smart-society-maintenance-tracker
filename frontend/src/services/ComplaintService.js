import API from "../api/axios";


const API_URL = "/complaints";


export const getAllComplaints = () => {
    return API.get(API_URL);
};


export const createComplaint = (complaint) => {
    return API.post(API_URL, complaint);
};


export const getComplaintById = (id) => {
    return API.get(`${API_URL}/${id}`);
};


export const updateComplaintStatus = (id, status) => {
    return API.put(
        `${API_URL}/${id}/status`,
        {
            status: status
        }
    );
};