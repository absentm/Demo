import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '../components/AppIndex'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'AppIndex',
      component: require('../components/AppIndex').default,
      meta: {title: 'AbsentM\'s Notes'}
    },
    {
      path: '/home',
      name: 'AppIndex',
      component: require('../components/AppIndex').default,
      meta: {title: 'Home | AbsentM\'s Notes'}
    },
    {
      path: '/category',
      name: 'CategoryIndex',
      component: require('../components/CategoryIndex').default,
      meta: {title: 'Category | AbsentM\'s Notes'}
    },
    {
      path: '/archives',
      name: 'ArchivesIndex',
      component: require('../components/ArchivesIndex').default,
      meta: {title: 'Archives | AbsentM\'s Notes'}
    },
    ,
    {
      path: '/tags',
      name: 'TagsIndex',
      component: require('../components/TagsIndex').default,
      meta: {title: 'Tags | AbsentM\'s Notes'}
    },
    {
      path: '/about',
      name: 'AboutIndex',
      component: require('../components/AboutIndex').default,
      meta: {title: 'About | AbsentM\'s Notes'}
    }
  ]
})
