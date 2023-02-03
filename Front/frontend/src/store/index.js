import { createStore } from "vuex";
import { userStore } from "@/store/modules/userStore"
import { iceBreakingStore } from "./modules/iceBreakingStore";

export default createStore({
  modules: {
    userStore,iceBreakingStore
  }
})


