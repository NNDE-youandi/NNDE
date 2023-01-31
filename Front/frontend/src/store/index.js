import { createStore } from "vuex";
import { userStore } from "@/store/modules/userStore"

export default createStore({
  modules: {
    userStore,
  }
})


