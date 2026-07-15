import API from "../api/axios";

export const getDashboardData = () => {
    return API.get("/dashboard");
};