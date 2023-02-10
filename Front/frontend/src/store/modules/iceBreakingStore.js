export const iceBreakingStore ={
    namespaced: true,
    state: () => ({
        numberOfMembers: 5,
        roomNumber: 111111,
        surveyList:[],
        answerList:[],
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
        }
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
        }

    },
    actions: {
       
    },
};