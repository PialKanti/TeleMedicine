import { create } from 'zustand';
import { persist } from 'zustand/middleware';

const useAuthStore = create(
    persist(
        (set) => ({
            token: '',
            setToken: (newToken) => set((state) => ({ ...state, token: newToken }))
        }),
        {
            name: 'token'
        }
    )
);

export default useAuthStore;
