import axiosInstance from '../../plugins/axios';
import endpoints from './endpoint';

const registerAsPatient = async (formData) => {
    try {
        return await axiosInstance.post(endpoints.auth.register.patient, formData);
    } catch (error) {
        console.error('Error registering as patient: ', error);
        throw error;
    }
};

const registerAsDoctor = async (formData) => {
    try {
        return await axiosInstance.post(endpoints.auth.register.doctor, formData);
    } catch (error) {
        console.error('Error registering as doctor: ', error);
        throw error;
    }
};

const login = async (username, password) => {
    try {
        const data = {
            username: username,
            password: password
        };
        return await axiosInstance.post(endpoints.auth.login, data);
    } catch (error) {
        console.error('Error during login : ', error);
        throw error;
    }
};

export { registerAsPatient, registerAsDoctor, login };
