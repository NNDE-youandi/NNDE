import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/intro/HomeView.vue";
import LoginView from "../views/user/LoginView.vue";
import SignupView from "../views/user/SignupView.vue";
import SelectModeView from "../views/intro/SelectModeView.vue";
import GameMainView from "../views/game/GameMainView.vue";
import MakeRoomIceView from "../views/intro/MakeRoomIceView.vue";
import IceQrView from "../views/intro/IceQrView.vue";
import SurveyView from "../views/intro/SurveyView.vue";
import WaitingRoomView from "../views/intro/WaitingRoomView.vue";
import IceBreakingStartView from "../views/icebreaking/IceBreakingStartView.vue";
import Step1CountView from "../views/icebreaking/Step1CountView.vue";
import KeyWordView from "../views/icebreaking/KeyWordView.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
  },
  {
    path: "/login",
    name: "Login",
    component: LoginView,
  },
  {
    path: "/signup",
    name: "Signup",
    component: SignupView,
  },
  {
    path: "/selectmode",
    name: "SelectMode",
    component: SelectModeView,
  },
  {
    path: "/gamemain",
    name: "GameMain",
    component: GameMainView,
  },
  {
    path: "/makeroomice",
    name: "MakeRoomIce",
    component: MakeRoomIceView,
  },
  {
    path: "/iceqr",
    name: "IceQr",
    component: IceQrView,
  },
  {
    path: "/survey",
    name: "Survey",
    component: SurveyView,
  },
  {
    path: "/waitingroom",
    name: "WaitingRoom",
    component: WaitingRoomView,
  },
  {
    path: "/icebreakingstart",
    name: "IceBreakingStart",
    component: IceBreakingStartView,
  },
  {
    path: "/step1count",
    name: "Step1Count",
    component: Step1CountView,
  },
  {
    path: "/keyword",
    name: "KeyWord",
    component: KeyWordView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
