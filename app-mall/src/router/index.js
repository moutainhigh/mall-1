import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'
import CarList from '../page/carList/CarList'
import CarDetail from '../page/carDetail/CarDetail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/car-list',
      name: 'CarList',
      component: CarList
    },
    {
      path: '/car-detail',
      name: 'CarDetail',
      component: CarDetail
    }
  ]
})
