import { createStore } from "vuex";
import { userStore } from "@/store/modules/userStore"
import { iceBreakingStore } from "./modules/iceBreakingStore";
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  modules: {
    userStore,
    iceBreakingStore
  },
  plugins: [ 
    createPersistedState({
      paths: ["userStore"]
    })
  ]
})