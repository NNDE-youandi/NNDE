export const userStore = {
    namespaced: true,
    state: () => ({
      isLogin: false,
      userInfo: [],
    }),
  
    getters: {
      GET_USER_INFO: (state) => {
        // console.log("GET_USER_INFO : ", state.userInfo);
        return state.userInfo;
      },
      GET_IS_LOGIN: (state) => {
        return state.isLogin;
      }
    },
    mutations: {
      SET_IS_LOGIN_TRUE(state) {
        state.isLogin = true;
      },
      SET_IS_LOGIN_FALSE(state) {
        state.isLogin = false;
      },
      CLEAR_USER_INFO(state) {
        state.userInfo = [];
      },
      SET_USER_INFO(state, userInfo) {
        state.userInfo = userInfo;
        // console.log("SET_USER_INFO : ", state.userInfo[0]);
        // console.log(state.userInfo[0].accessToken)
      },
    },
    actions: {},
  };