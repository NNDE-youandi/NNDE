export const iceBreakingStore ={
    namespaced: true,
    state: () => ({
        numberOfMembers: 5,
        roomNumber: 111111,
        surveyList:[],
        answerList:[],
        step2QuizResult:"설문관련 퀴즈 정답",
        // ice-end :bgm
        // audio : new Audio(require('../../../public/You_Are_My_Girl.mp3')),
        // bombAudio : new Audio(require('../../../public/tick-tock.mp3')),
        }),

    getters: {
        // subin
        GET_SURVEY_LIST : state=>{
            console.log("GET_SURVEY_LIST :", state.surveyList);
            return state.surveyList;
        },
        GET_ANWSER_LIST : state=>{
            console.log("GET_ANWSER_LIST :", state.answerList);
            return state.answerList;
        },

        // quiz_result 
        GET_QUIZ_RESULT : state =>{
            console.log("GET_QUIZ_RESULT :", state.step2QuizResult);
            return state.step2QuizResult;
        },
    },
    mutations: {
        SET_NUMBER_OF_MEMBERS(state, numOfMember){
            state.numberOfMembers = numOfMember
            console.log(state.numberOfMembers)
        },
        SET_ROOM_NUMBER(state, roomnum){
            state.roomNumber = roomnum
            console.log(state.roomNumber)
        },
        // 수빈 수정
        CLEAR_SURVEY_LIST(state){
            state.surveyList =[];
        },
        SET_SURVEY_LIST(state,surveyList){
            state.surveyList = surveyList
            console.log("SET_SURVEY_LIST :",state.surveyList)
        },
        SET_ANSWER(state,answer){
            state.answerList=answer;
            console.log("SET_ANSWER :",state.answerList)
        },

        // step2QuizView - result 값 없애기
        CLEAR_QUIZ_RESULT(state){
            state.step2QuizResult ="초기화";
            console.log("CLEAR_QUIZ_RESULT :",state.step2QuizResult);
        },
        // step2QuizView - result 설정하기
        SET_QUIZ_RESULT(state,quizResult){
            state.step2QuizResult=quizResult;
            console.log("SET_QUIZ_RESULT :",state.step2QuizResult);
        },
    },
    actions: {
    //    PLAY_ICE_END:(state)=>{
    //     state.audio.play();
    //    },
    //    PLAY_BOMB_SOUND:(state)=>{
    //     state.audio.play();
    //    },
    //    PLAY_BOMB_SOUND:(state)=>{
    //     state.audio.play();
    //    }
    
    },
};