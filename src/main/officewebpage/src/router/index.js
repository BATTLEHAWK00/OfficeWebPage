import Vue from 'vue'
import Router from 'vue-router'
import Utils from '../Utils'
import Main from '@/components/Main'
import Posts from '@/components/Posts'
import Aboutus from '@/components/Aboutus'
import Order from '@/components/Order'
import Login from '@/components/Users/Login'
import Reg from '@/components/Users/Register'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/posts',
      name: '动态',
      component: Posts,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/aboutus',
      name: '关于我们',
      component: Aboutus,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/orderpost',
      name: '工单',
      component: Order,
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/login',
      name: '登录',
      component: Login,
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/register',
      name: '注册',
      component: Reg,
      meta: {
        requireAuth: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.name == "Main") {
    Utils.methods.SetTitle()
  } else {
    Utils.methods.SetTitle(to.name)
  }
  next()
})

export default router
