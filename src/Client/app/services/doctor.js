import axiosInstance from '../../plugins/axios';
import endpoints from './endpoint';

const getApprovedDoctors = async () => {
    try {
        return await axiosInstance.get(endpoints.doctors.approved);
    } catch (error) {
        console.error('Error during fetching approved doctors list : ', error);
        throw error;
    }
};

const getPendingDoctors = async () => {
    try {
        return await axiosInstance.get(endpoints.doctors.pending);
    } catch (error) {
        console.error('Error during fetching pending doctors list : ', error);
        throw error;
    }
};

const approveDoctor = async (id) => {
    try {
        return await axiosInstance.get(endpoints.doctors.approve.replace('{id}', id));
    } catch (error) {
        console.error('Error during approving a doctor : ', error);
        throw error;
    }
};

export { getApprovedDoctors, getPendingDoctors, approveDoctor };
