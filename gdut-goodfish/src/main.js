// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './stylus/reset.styl'
import './assets/utils/rem.js'
import Mint from 'mint-ui';
import 'mint-ui/lib/style.css';
import { Toast } from 'mint-ui'
import VueLazyload from 'vue-lazyload'
import './common/stylus/index.styl'
import store from './store'
import axios from 'axios'; // 确保导入 axios

Vue.use(VueLazyload)
Vue.use(Mint);
Vue.$toast = Vue.prototype.$toast = Toast;
Vue.config.productionTip = false
// 配置 Axios 拦截器
const token = localStorage.getItem('jwt');
if (token) {
  axios.interceptors.request.use(config => {
    config.headers.Authorization = `Bearer ${token}`;
    return config;
  }, error => {
    return Promise.reject(error);
  });
}
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
