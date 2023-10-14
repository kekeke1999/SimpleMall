import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);
const store = new Vuex.Store({
   state: {
        // 存储token
        token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
    },
    mutations: {
        // 修改token，并将token存入localStorage
        setToken (state, token) {
            window.console.log("已储存token");
            state.token =token;
            window.console.log("token:"+token.token);
            localStorage.setItem("token",token.token);     //存储token
        },
        delToken (state) {
            state.token = '';
            localStorage.removeItem("token");    //删除token
        }
    }

});

export default store;
