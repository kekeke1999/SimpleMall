import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import Vuex from 'vuex' //引入状态管理
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import echarts from "echarts";
Vue.prototype.$echarts = echarts;
Vue.prototype.$axios= axios ;
Vue.use(Vuex) ;

Vue.config.productionTip = false
Vue.use(ElementUI)

//这里要导入store
import store from "./store";

// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(
    config => {
      if (localStorage.getItem('Authorization')) {
        config.headers.Authorization = localStorage.getItem('Authorization');
      }
      return config;
    },
    error => {
      return Promise.reject(error);
    });


new Vue({
  //这里要配置store
  router, store:store,
  render: function (h) { return h(App) }
}).$mount('#app')
