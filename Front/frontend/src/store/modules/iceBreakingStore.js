export const iceBreakingStore ={
    namespaced: true,
    state: () => ({
        numberOfMembers: 5,
        }),

    getters: {
    },
    mutations: {
        SET_NUMBER_OF_MEMBERS(state, numOfMember){
            state.numberOfMembers = numOfMember
            console.log(state.numberOfMembers)
        }
    },
    actions: {
    },
}