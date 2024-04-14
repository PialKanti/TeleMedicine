import axiosInstance from '../../plugins/axios';

const getBloodGroups = async () => {
    try {
        return await axiosInstance.get('/lookups/bloodgroup');
    } catch (error) {
        console.error('Error fetching blood groups: ', error);
        throw error;
    }
};

const getGenders = async () => {
    try {
        return await axiosInstance.get('/lookups/gender');
    } catch (error) {
        console.error('Error fetching genders: ', error);
        throw error;
    }
};

export { getBloodGroups, getGenders };
