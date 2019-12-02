import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '../components/HelloWorld'

import AppIndex from '../components/home/AppIndex'
import Login from '../components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component:  require('../components/HelloWorld').default
    },
    {
      path: '/index',
      name: 'AppIndex',
      component: require('../components/home/AppIndex').default
    },
    {
      path: '/login',
      name: 'Login',
      component: require('../components/Login').default
    }
  ]
})
