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
    },
    doctors: {
        approved: '/doctors?approved=true',
        pending: '/doctors?approved=false',
        approve: '/doctors/{id}/approve',
    },
    specialities:{
        getAll: '/specialities',
        update: '/specialities/{id}'
    }
};

export default endpoints;
