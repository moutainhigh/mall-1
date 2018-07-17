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
import MyOrders from '../components/MyOrders'
import Insured from '../components/Insured'
import DifferentPlaces from '../components/DifferentPlaces'
import CareerSelect from '../components/CareerSelect'
import InsurancePolicy from '../components/InsurancePolicy'
import InsureNote from '../components/InsureNote'
import LifeLongClause from '../components/LifeLongClause'
import Clause from '../components/Clause'
import OrderDetail from '../components/OrderDetail'
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
      meta: {
        title: '我的保单'
      },
      component: MyOrders
    }, {
      path: '/insured',
      name: 'insured',
      component: Insured
    }, {
      path: '/differentPlaces',
      name: 'differentPlaces',
      component: DifferentPlaces
    }, {
      path: '/insurancePolicy',
      name: 'insurancePolicy',
      component: InsurancePolicy
    }, {
      path: '/lifeLongClause',
      name: 'lifeLongClause',
      component: LifeLongClause
    }, {
      path: '/clause',
      name: 'clause',
      component: Clause
    }, {
      path: '/insureNote',
      name: 'insureNote',
      component: InsureNote
    }, {
      path: '/careerSelect',
      name: 'careerSelect',
      meta: {
        title: '选择职业'
      },
      component: CareerSelect
    }, {
      path: '/order-detail',
      name: 'order-detail',
      meta: {
        title: '保单详情'
      },
      component: OrderDetail
    },{ /* Not Found 路由，必须是最后一个路由 */
      path: '*',
      // component: NotFound,
      meta: {
        title: '找不到页面'
      }
    }
  ]

})

