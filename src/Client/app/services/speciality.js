import axiosInstance from '../../plugins/axios';
import endpoints from './endpoint';

const getAllSpecialities = async () => {
    try {
        return await axiosInstance.get(endpoints.specialities.getAll);
    } catch (error) {
        console.error('Error while fetching all specialities: ', error);
        throw error;
    }
};

const createSpeciality = async (formData) => {
    try {
        return await axiosInstance.post(endpoints.specialities.create, formData);
    } catch (error) {
        console.error('Error while creating new speciality: ', error);
        throw error;
    }
};

const updateSpeciality = async (id, formData) => {
    try {
        return await axiosInstance.put(endpoints.specialities.update.replace('{id}', id), formData);
    } catch (error) {
        console.error('Error while updating speciality: ', error);
        throw error;
    }
};

export { getAllSpecialities, createSpeciality, updateSpeciality };
