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

export { registerAsPatient };
