// path: lowercase, name: PascalCase, component: kamelCase
import { createRouter, createWebHistory } from "vue-router";
// intro
import HomeView from "../views/intro/HomeView.vue";
import SelectModeView from "../views/intro/SelectModeView.vue";
import MakeRoomView from "../views/intro/MakeRoomView.vue";
import RoomWaitingView from "../views/intro/RoomWaitingView.vue";
import SurveyView from "../views/intro/SurveyView.vue";
// user
import LoginView from "../views/user/LoginView.vue";
import SignupView from "../views/user/SignupView.vue";
// ice breaking
import IceBreakingStartView from "../views/icebreaking/IceBreakingStartView.vue";
import Step1CountView from "../views/icebreaking/Step1CountView.vue";
import KeyWordView from "../views/icebreaking/KeyWordView.vue";
import NonKeyWordView from "../views/icebreaking/NonKeyWordView.vue";
import Step1OutroView from "../views/icebreaking/Step1OutroView.vue";
import Step2OutroView from "../views/icebreaking/Step2OutroView.vue";
import Step2StartView from "../views/icebreaking/Step2StartView.vue";
import Step2CountView from "../views/icebreaking/Step2CountView.vue";
import Step2QuizView from "../views/icebreaking/Step2QuizView.vue";
// game: intro
import SelectGameView from "../views/game/SelectGameView.vue";
// game: boom
import BoomSetupView from "../views/game/boom/BoomSetupView.vue";
import BoomStageView from "../views/game/boom/BoomStageView.vue";
import BoomEndView from "../views/game/boom/BoomEndView.vue";
// game: balance
import BalanceView from "../views/game/BalanceView.vue";
// game: liar
import LiarStageView from "../views/game/liargame/LiarStageView.vue";
import LiarThemeListView from "../views/game/liargame/LiarThemeListView.vue";
import LiarDetectSuccessView from "../views/game/liargame/LiarDetectSuccessView.vue";
import LiarDetectFailView from "../views/game/liargame/LiarDetectFailView.vue";
import LiarResultView from "../views/game/liargame/LiarResultView.vue";

const routes = [
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
    path: "/makeroom/:modeName",
    name: "MakeRoom",
    component: MakeRoomView,
  },
  {
    path: "/roomwaiting/:modeName",
    name: "RoomWaiting",
    component: RoomWaitingView,
  },
  {
    path: "/survey",
    name: "Survey",
    component: SurveyView,
  },
  // user
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
    path: "/step2outro",
    name: "Step2Outro",
    component: Step2OutroView,
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
  // game: intro
  {
    path: "/selectgame",
    name: "SelectGame",
    component: SelectGameView,
  },
  // game: balance
  {
    path: "/balance",
    name: "Balance",
    component: BalanceView,
  },
  // game: boom
  {
    path: "/boomsetup",
    name: "BoomSetup",
    component: BoomSetupView,
  },
  {
    path: "/boomstage/:boomTime",
    name: "BoomStage",
    component: BoomStageView,
  },
  {
    path: "/boomend/:boomedSocket",
    name: "BoomEnd",
    component: BoomEndView,
  },
  // game: liar
  {
    path: "/liarstage",
    name: "LiarStage",
    component: LiarStageView,
  },
  {
    path: "/liarthemelist/:modeName",
    name: "LiarThemeList",
    component: LiarThemeListView,
  },
  {
    path: "/liardetectsuccess",
    name: "LiarDetectSuccess",
    component: LiarDetectSuccessView,
  },
  {
    path: "/liardetectfail",
    name: "LiarDetectFail",
    component: LiarDetectFailView,
  },
  {
    path: "/liarresult",
    name: "LiarResult",
    component: LiarResultView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
