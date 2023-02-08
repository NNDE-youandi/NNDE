export const userStore = {
  namespaced: true,
  state: () => ({
    isLogin: false,
  }),

  getters: {},
  mutations: {
    SET_IS_LOGIN(state) {
      state.isLogin = true;
      // console.log(state.isLogin)
    },
  },
  actions: {},
};
