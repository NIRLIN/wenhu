import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import app from '../App'

Vue.use(Router)

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
    {
        path: '/',
        component: app,
        redirect: 'hot'
    },
    {
        path: '/hot',
        component: () => import('@/views/Home/Hot')
    },
    {
        path: '/follow',
        component: () => import('@/views/Home/Follow')
    },
    {
        path: '/recommend',
        component: () => import('@/views/Home/Recommend')
    },
    {
        path: '/question/:id',
        component: () => import('@/views/QuestionAnswer')
    },
    {
        path: '/login',
        component: () => import('@/views/Password/Login')
    },
    {
        path: '/register',
        component: () => import('@/views/Password/Register')
    },
    {
        path: '/people/:id',
        component: () => import('@/views/People')
    },
    {
        path: '/message',
        component: () => import('@/views/Message')
    },
    {
        path: '/setting',
        component: () => import('@/views/Setting')
    },
    {
        path: '/forgetPassword',
        component: () => import('@/views/Password/ForgetPassword')
    },
    {
        path: '/test',
        component: () => import('@/views/Message/MessageContent/MessageItem')
    },

    // 404 page must be placed at the end !!!
    {path: '*', component: () => import('@/views/NotFound')}
]

const createRouter = () => new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({y: 0}),
    routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // reset router
}

export default router