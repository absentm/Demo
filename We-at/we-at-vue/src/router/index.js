import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '../components/AppIndex'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'AppIndex',
      component: require('../components/AppIndex').default
    },
    {
      path: '/home',
      name: 'AppIndex',
      component: require('../components/AppIndex').default
    },
    {
      path: '/category',
      name: 'CategoryIndex',
      component: require('../components/CategoryIndex').default
    },
    {
      path: '/archives',
      name: 'ArchivesIndex',
      component: require('../components/ArchivesIndex').default
    },
    ,
    {
      path: '/tags',
      name: 'TagsIndex',
      component: require('../components/TagsIndex').default
    },
    {
      path: '/about',
      name: 'AboutIndex',
      component: require('../components/AboutIndex').default
    }
  ]
})
