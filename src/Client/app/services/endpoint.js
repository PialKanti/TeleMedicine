const endpoints = {
    auth: {
        register: {
            patient: '/auth/register',
            doctor: '/auth/register/doctor'
        },
        login: '/auth/login'
    },
    lookup: {
        gender: '/lookups/gender',
        bloodGroup: '/lookups/bloodgroup',
        title: '/lookups/title',
        speciality: '/specialities?page=0&pageSize=100'
    }
};

export default endpoints;
