// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import router from './router'
import bootstrapvue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import GlobalVar from './Global'
import Utils from './Utils'

// Axios配置
axios.defaults.withCredentials = true;

// Vue配置
Vue.config.productionTip = false
Vue.use(bootstrapvue)

// 全局原型配置
Vue.prototype.$ajax = axios
Vue.prototype.GlobalVar = GlobalVar
Vue.prototype.Utils = Utils

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {
    App: () => import('./App')
  },
  template: '<App/>'
})
