import Vue from 'vue'
import Router from 'vue-router'
import Utils from '../Utils'
import Main from '@/components/Main'
import Posts from '@/components/Posts'
import Aboutus from '@/components/Aboutus'
import OrderPost from '@/components/OrderPost'

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
