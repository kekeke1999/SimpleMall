import Vue from 'vue';
import App from './App';
import axios from 'axios';
import { router } from './router';

Vue.config.productionTip = false
Vue.prototype.$axios= axios ;

axios.defaults.withCredentials = true


//这里要导入store
import store from "./store";

// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(
    config => {
      if (localStorage.getItem('token')) {
        config.headers.Authorization = localStorage.getItem('token');
      }
      window.console.log("请求头携带token："+localStorage.getItem('token'));
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
