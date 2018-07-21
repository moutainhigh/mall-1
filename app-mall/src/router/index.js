import Vue from 'vue'
import Router from 'vue-router'
import CarList from '../page/carList/CarList'
import Home from '../page/home/Home'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/car-list',
      name: 'CarList',
      component: CarList
    }
  ]
})
