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
import DifferentPlaces from '../components/DifferentPlaces'
import CareerSelect from '../components/CareerSelect'
import InsurancePolicy from '../components/InsurancePolicy'
import InsureNote from '../components/InsureNote'
import LifeLongClause from '../components/LifeLongClause'
import Clause from '../components/Clause'
import Vuex from 'vuex'

Vue.use(Router)
Vue.use(Vuex)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index,
      meta: {
        title: '保险'
      },
    }, {
      path: '/pro-detail',
      name: 'pro-detail',
      component: ProDetail,
      meta: {
        title: '保险'
      },
    }, {
      path: '/holder-basic',
      name: 'holder-basic',
      component: HolderBasic,
      meta: {
        title: '保险'
      },
    }, {
      path: '/holder-detail',
      name: 'holder-detail',
      component: HolderDetail,
      meta: {
        title: '保险'
      },
    }, {
      path: '/autograph',
      name: 'autograph',
      component: Autograph,
      meta: {
        title: '保险'
      },
    }, {
      path: '/upload-data',
      name: 'upload-data',
      component: UploadData,
      meta: {
        title: '保险'
      },
    }, {
      path: '/infoMatters',
      name: 'info-matters',
      component: InfoMatters,
      meta: {
        title: '保险'
      },
    }, {
      path: '/payment',
      name: 'payment',
      component: Payment,
      meta: {
        title: '保险'
      },
    }, {
      path: '/policy',
      name: 'policy',
      component: Policy,
      meta: {
        title: '保险'
      },
    }, {
      path: '/my-orders',
      name: 'my-orders',
      meta: {
        title: '我的保单'
      },
      component: myOrders
    }, {
      path: '/insured',
      name: 'insured',
      component: Insured,
      meta: {
        title: '保险'
      },
    }, {
      path: '/differentPlaces',
      name: 'differentPlaces',
      component: DifferentPlaces,
      meta: {
        title: '保险'
      },
    }, {
      path: '/insurancePolicy',
      name: 'insurancePolicy',
      component: InsurancePolicy,
      meta: {
        title: '保险'
      },
    }, {
      path: '/lifeLongClause',
      name: 'lifeLongClause',
      component: LifeLongClause,
      meta: {
        title: '保险'
      },
    }, {
      path: '/clause',
      name: 'clause',
      component: Clause,
      meta: {
        title: '保险'
      },
    }, {
      path: '/insureNote',
      name: 'insureNote',
      component: InsureNote,
      meta: {
        title: '保险'
      },
    }, {
      path: '/careerSelect',
      name: 'careerSelect',
      meta: {
        title: '选择职业'
      },
      component: CareerSelect
    },{ /* Not Found 路由，必须是最后一个路由 */
      path: '*',
      // component: NotFound,
      meta: {
        title: '找不到页面'
      }
    }
  ]

})

