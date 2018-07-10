import Vue from 'vue'
import Router from 'vue-router'
import Index from '../components/Index'
import ProDetail from '../components/ProDetail'
import HolderBasic from '../components/HolderBasic'
import HolderDetail from '../components/HolderDetail'
import Autograph from '../components/Autograph'
import UploadData from '../components/UploadData'
import InfoMatters from '../components/InfoMatters'
import Payment from '../components/Payment'
import Policy from '../components/Policy'
import myOrders from '../components/myOrders'
import Insured from '../components/Insured'
import Vuex from 'vuex'

Vue.use(Router)
Vue.use(Vuex)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    }, {
      path: '/pro-detail',
      name: 'pro-detail',
      component: ProDetail
    }, {
      path: '/holder-basic',
      name: 'holder-basic',
      component: HolderBasic
    }, {
      path: '/holder-detail',
      name: 'holder-detail',
      component: HolderDetail
    }, {
      path: '/autograph',
      name: 'autograph',
      component: Autograph
    }, {
      path: '/upload-data',
      name: 'upload-data',
      component: UploadData
    }, {
      path: '/infoMatters',
      name: 'info-matters',
      component: InfoMatters
    }, {
      path: '/payment',
      name: 'payment',
      component: Payment
    }, {
      path: '/policy',
      name: 'policy',
      component: Policy
    }, {
      path: '/my-orders',
      name: 'my-orders',
      component: myOrders
    }, {
      path: '/insured',
      name: 'insured',
      component: Insured
    }
  ]
})
