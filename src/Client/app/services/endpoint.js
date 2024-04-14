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
        bloodGroup: '/lookups/bloodgroup'
    }
};

export default endpoints;
