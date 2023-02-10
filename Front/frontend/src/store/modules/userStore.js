export const userStore = {
    namespaced: true,
    state: () => ({
        isLogin: false,
        userInfo:[],
        }),

    getters: {
        // subin
        GET_USER_INFO : state=>{
            console.log("GET_USER_INFO : ",state.userInfo);
            return state.userInfo;
        },
    },
    mutations: {
        SET_IS_LOGIN(state) {
            state.isLogin = true
            // console.log(state.isLogin)
        },
        // subin - logout 에서 사용 
        CLEAR_USER_INFO(state){
            state.userInfo=[];
        }
        ,
        SET_USER_INFO(state,userInfo){
            state.userInfo = userInfo;
            console.log("SET_USER_INFO : ",state.userInfo[0]);
        }
    },
    actions: {
    },
};
