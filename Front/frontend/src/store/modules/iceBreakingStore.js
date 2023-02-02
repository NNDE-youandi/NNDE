export const iceBreakingStore ={
    namespaced: true,
    state: () => ({
        numberOfMembers: 5,
        roomNumber: 111111,
        }),

    getters: {
    },
    mutations: {
        SET_NUMBER_OF_MEMBERS(state, numOfMember){
            state.numberOfMembers = numOfMember
            console.log(state.numberOfMembers)
        },
        SET_ROOM_NUMBER(state, roomnum){
            state.roomNumber = roomnum
            console.log(state.roomNumber)
        }
    },
    actions: {
    },
}