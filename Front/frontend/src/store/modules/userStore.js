export const userStore = {
    namespaced: true,
    state: () => ({
        isLogin: false,
        }),

    getters: {
    },
    mutations: {
        SET_IS_LOGIN(state) {
            state.isLogin = !state.isLogin
        }
    },
    actions: {
    },
};
