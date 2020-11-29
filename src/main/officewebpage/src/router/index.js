import Vue from 'vue'
import Router from 'vue-router'
import Utils from '../Utils'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: () => import('@/components/Main'),
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/posts',
      name: '动态',
      component: () => import('@/components/Posts'),
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/aboutus',
      name: '关于我们',
      component: () => import('@/components/Aboutus'),
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/orderpost',
      name: '工单',
      component: () => import('@/components/Order'),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/login',
      name: '登录',
      component: () => import('@/components/Users/Login'),
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/register',
      name: '注册',
      component: () => import('@/components/Users/Register'),
      meta: {
        requireAuth: false
      }
    },
    {
      path: '/manage',
      name: '管理',
      component: () => import('@/components/Manage'),
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: 'ordermanage',
          name: '工单管理',
          component: () => import('@/components/Manage/OrderManage'),
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'postsmanage',
          name: '文章管理',
          component: () => import('@/components/Manage/PostsManage'),
          meta: {
            requireAuth: false
          }
        },
        {
          path: 'usermanage',
          name: '用户管理',
          component: () => import('@/components/Manage/UserManage'),
          meta: {
            requireAuth: false
          }
        }
      ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    Utils.methods.updateUserState(router.app.$ajax, true)
  }
  if (to.name == "Main") {
    Utils.methods.SetTitle()
  } else {
    Utils.methods.SetTitle(to.name)
  }
  next()
})

export default router
