import axios from 'axios';
import useAuthStore from '../stores/store';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1'
});

axiosInstance.interceptors.request.use((config) => {
    if (!config.url.includes('/login') && !config.url.includes('/register') && !config.url.includes('/lookups')) {
        const { token } = useAuthStore.getState();
        config.headers = {
            Authorization: 'Bearer ' + token
        };
    }

    return config;
});

export default axiosInstance;
