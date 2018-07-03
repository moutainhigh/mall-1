// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import '../static/default.css'
import Vuex from 'vuex'
import AlertPlugin from 'vux/src/plugins/Alert'
import ToastPlugin from 'vux/src/plugins/Toast'
import datetime from 'vux/src/plugins/datetime'
import { Group } from 'vux'

Vue.component('group', Group)
Vue.use(AlertPlugin)
Vue.use(ToastPlugin)
Vue.use(datetime)
Vue.config.productionTip = false

Vue.use(Vuex)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },

  template: '<App/>'
})

