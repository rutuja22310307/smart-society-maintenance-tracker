import API from "../api/axios";

const API_URL = "/maintenance";

export const getAllMaintenance = () => {
    return API.get(API_URL);
};

export const createMaintenance = (maintenance) => {
    return API.post(API_URL, maintenance);
};

export const getMaintenanceById = (id) => {
    return API.get(`${API_URL}/${id}`);
};

export const deleteMaintenance = (id) => {
    return API.delete(`${API_URL}/${id}`);
};