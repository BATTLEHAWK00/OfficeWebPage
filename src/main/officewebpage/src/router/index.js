import Vue from 'vue'
import Router from 'vue-router'
import Utils from '../Utils'
import Main from '@/components/Main'
import Posts from '@/components/Posts'
import Aboutus from '@/components/Aboutus'
import OrderPost from '@/components/OrderPost'
import Login from '@/components/Users/Login'
import Reg from '@/components/Users/Register'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/posts',
      name: '动态',
      component: Posts
    },
    {
      path: '/aboutus',
      name: '关于我们',
      component: Aboutus
    },
    {
      path: '/orderpost',
      name: '工作预约',
      component: OrderPost
    },
    {
      path: '/login',
      name: '登录',
      component: Login
    },
    {
      path: '/register',
      name: '注册',
      component: Reg
    }
  ]
})

router.afterEach((to, from) => {
  if (to.name == "Main") {
    Utils.SetTitle()
    return
  }
  Utils.SetTitle(to.name)
})

export default router
