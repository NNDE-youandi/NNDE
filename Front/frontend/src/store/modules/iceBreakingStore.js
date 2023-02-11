export const iceBreakingStore ={
    namespaced: true,
    state: () => ({
        numberOfMembers: 5,
        roomNumber: 111111,
        surveyList:[],
        answerList:[],
<<<<<<< HEAD
        step2QuizResult:"설문관련 퀴즈 정답",
=======
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
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
<<<<<<< HEAD
        },

        // quiz_result 
        GET_QUIZ_RESULT : state =>{
            console.log("GET_QUIZ_RESULT :", state.step2QuizResult);
            return state.step2QuizResult;
        },
=======
        }
>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
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
<<<<<<< HEAD
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
=======
        }

>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
    },
    actions: {
       
    },
};