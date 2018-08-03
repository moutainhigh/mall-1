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
import ChooseCarBrand from '../page/carList/ChooseCarBrand'
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
      component: Home
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
      component: Location
    },
    {
      path: '/car-list',
      name: 'CarList',
      component: CarList,
      meta: {
        keepAlive: true // 需要被缓存
      },
    },
    {
      path: '/car-detail',
      name: 'CarDetail',
      component: CarDetail
    },
    {
      path: '/choose-type',
      name: 'ChooseCarType',
      component: ChooseCarType
    },{
      path: '/choose-brand',
      name: 'ChooseCarBrand',
      component: ChooseCarBrand
    },
    {
      path: '/order-comfirm',
      name: 'OrderComfirm',
      component: OrderComfirm
    },
    {
      path: '/order-success',
      name: 'OrderSuccess',
      component: OrderSuccess
    },{
      path: '/order-detail',
      name: 'OrderDetail',
      component: OrderDetail
    },
    {
      path: '/order-list',
      name: 'OrderList',
      component: OrderList
    }, {
      path: '/serve-detail',
      name: 'ServeDetail',
      component: ServeDetail
    }, {
      path: '/refund',
      name: 'Refund',
      component: Refund
    },
    {
      path: '/serve-list',
      name: 'ServerList',
      component: ServerList
    },
    {
      path: '/my-address',
      name: 'MyAddress',
      component: MyAddress
    },
    {
      path: '/add-address',
      name: 'AddAddress',
      component: AddAddress
    },
    {
      path: '/edit-address',
      name: 'EditAddress',
      component: EditAddress
    },
    {
      path: '/choose-address',
      name: 'ChooseAddress',
      component: ChooseAddress
    },
    {
      path: '/collect-list',
      name: 'CollectList',
      component: CollectList
    },
    {
      path: '/my-browse',
      name: 'MyBrowse',
      component: MyBrowse
    },{
      path: '/login',
      name: 'Login',
      component: Login
    },
  ]
})
