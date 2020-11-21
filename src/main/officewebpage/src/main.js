// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import bootstrapvue from 'bootstrap-vue'
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import GlobalVar from './Global'
import Utils from './Utils'


Vue.config.productionTip = false
Vue.use(bootstrapvue)
axios.defaults.withCredentials=true;
Vue.prototype.$ajax = axios
Vue.prototype.GlobalVar = GlobalVar
Vue.prototype.Utils = Utils

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
