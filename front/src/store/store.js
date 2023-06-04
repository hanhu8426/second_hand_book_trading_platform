import Vuex from 'vuex';

export default new Vuex.Store({
    state: {
        token: '',
        userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
        selectedCategory: null, // 存储选中的分类项索引
        // userInfo: null,
    },
    mutations: {
        // set
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem("token", token)
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        REMOVE_INFO: (state) => {
            state.token = ''
            state.userInfo = {}
            localStorage.setItem("token", '')
            sessionStorage.setItem("userInfo", JSON.stringify(''))
        },
        setSelectedCategory(state, index) {
            state.selectedCategory = index;
        },
    },
    getters: {
        // get
        getUser: state => {
            return state.userInfo
        }

    },
    actions: {
    },
    modules: {
    }
})
