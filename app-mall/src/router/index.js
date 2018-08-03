import Vue from 'vue'
import Router from 'vue-router'
import CarList from '../page/carList/CarList'
import CarDetail from '../page/carDetail/CarDetail'
import OrderComfirm from '../page/order/OrderComfirm'
import OrderSuccess from '../page/order/OrderSuccess'
import OrderList from '../page/order/OrderList'
import OrderDetail from '../page/order/OrderDetail'
import Home from '../page/home/Home'
import Search from '../page/search/Search'
import Location from '../page/location/Location'
import ChooseCarType from '../page/carList/ChooseCarType'
import ServeDetail from '../page/serve/ServeDetail'
import Refund from '../page/serve/Refund'
import ServerList from '../page/serve/ServerList'
import MyAddress from '../page/address/MyAddress'
import AddAddress from '../page/address/AddAddress'
import EditAddress from '../page/address/EditAddress'
import ChooseAddress from '../page/address/ChooseAddress'
import CollectList from '../page/collect/CollectList'
import MyBrowse from '../page/collect/MyBrowse'
import Login from '../page/Login'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/search',
      name: 'Search',
      component: Search,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/location',
      name: 'Location',
      component: Location,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/car-list',
      name: 'CarList',
      component: CarList,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/car-detail',
      name: 'CarDetail',
      component: CarDetail,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/choose-type',
      name: 'ChooseCarType',
      component: ChooseCarType,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/order-comfirm',
      name: 'OrderComfirm',
      component: OrderComfirm,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/order-success',
      name: 'OrderSuccess',
      component: OrderSuccess,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },{
      path: '/order-detail',
      name: 'OrderDetail',
      component: OrderDetail,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/order-list',
      name: 'OrderList',
      component: OrderList,
      meta: {
        keepAlive: false // 需要被缓存
      },
    }, {
      path: '/serve-detail',
      name: 'ServeDetail',
      component: ServeDetail,
      meta: {
        keepAlive: false // 需要被缓存
      },
    }, {
      path: '/refund',
      name: 'Refund',
      component: Refund,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/serve-list',
      name: 'ServerList',
      component: ServerList,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/my-address',
      name: 'MyAddress',
      component: MyAddress,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/add-address',
      name: 'AddAddress',
      component: AddAddress,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/edit-address',
      name: 'EditAddress',
      component: EditAddress,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/choose-address',
      name: 'ChooseAddress',
      component: ChooseAddress,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/collect-list',
      name: 'CollectList',
      component: CollectList,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
    {
      path: '/my-browse',
      name: 'MyBrowse',
      component: MyBrowse,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },{
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        keepAlive: false // 需要被缓存
      },
    },
  ]
})
