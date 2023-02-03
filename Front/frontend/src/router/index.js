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
import NonKeyWordView from "../views/icebreaking/NonKeyWordView.vue";
import Step1OutroView from "../views/icebreaking/Step1OutroView.vue";
import Step2StartView from "../views/icebreaking/Step2StartView.vue";
import Step2CountView from "../views/icebreaking/Step2CountView.vue";
import Step2QuizView from "../views/icebreaking/Step2QuizView.vue";
import BalanceGameView from "../views/game/BalanceGameView.vue";
import LiarGameView from "../views/game/LiarGameView.vue";

const routes = [
  // user 부분
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

  // intro
  {
    path: "/",
    name: "Home",
    component: HomeView,
  },
  {
    path: "/selectmode",
    name: "SelectMode",
    component: SelectModeView,
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
  // game
  {
    path: "/gamemain",
    name: "GameMain",
    component: GameMainView,
  },
  {
    path: "/balancegame",
    name: "Balancegame",
    component: BalanceGameView,
  },
  {
    path: "/liargame",
    name: "LiarGame",
    component: LiarGameView,
  },

  // icebreaking
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
  {
    path: "/nonkeyword",
    name: "NonKeyWord",
    component: NonKeyWordView,
  },
  {
    path: "/step1outro",
    name: "Step1Outro",
    component: Step1OutroView,
  },
  {
    path: "/step2start/",
    name: "Step2Start",
    component: Step2StartView,
  },
  {
    path: "/step2count",
    name: "Step2Count",
    component: Step2CountView,
  },
  {
    path: "/step2quiz",
    name: "Step2Quiz",
    component: Step2QuizView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
