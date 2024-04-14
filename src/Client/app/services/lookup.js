import axiosInstance from '../../plugins/axios';
import endpoints from './endpoint';

const getBloodGroups = async () => {
    try {
        return await axiosInstance.get(endpoints.lookup.bloodGroup);
    } catch (error) {
        console.error('Error fetching blood groups: ', error);
        throw error;
    }
};

const getGenders = async () => {
    try {
        return await axiosInstance.get(endpoints.lookup.gender);
    } catch (error) {
        console.error('Error fetching genders: ', error);
        throw error;
    }
};

const getTitles = async () => {
    try {
        return await axiosInstance.get(endpoints.lookup.title);
    } catch (error) {
        console.error('Error fetching titles: ', error);
        throw error;
    }
};

const getSpecialities = async () => {
    try {
        return await axiosInstance.get(endpoints.lookup.speciality);
    }catch (error){
        console.error('Error fetching specialities: ', error);
        throw error;
    }
}

export { getBloodGroups, getGenders, getTitles, getSpecialities };
