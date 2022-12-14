/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from '@/components/home/AppIndex'
import Home from '@/components/home/Home'

import StudentLogin from '../components/user/student/studentLogin'
import CounsellorLogin from '../components/user/counsellor/counsellorLogin'
import AdminLogin from '../components/user/admin/adminLogin'
import SuperAdm from '../components/user/superAdmin/superAdmin'
import Student from '../components/user/student/student'
import Admin from '../components/user/admin/admin'
import Counsellor from "../components/user/counsellor/counsellor";
import {orderBy} from "element-ui/packages/table/src/util";

Vue.use(Router)

export const constantRouterMap = [
  {path: '/', redirect: '/home'},
  {
    path: '/home',
    name: 'Home',
    component: Home,
    // home页面并不需要被访问
    children: [
      {
        path: '/',
        name: 'AppIndex',
        component: AppIndex,
      }
    ]
  },
  { path: '/404', name:'404', component: () => import('@/components/home/404'), hidden: true },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/components/login/Login'),
    children: [
      {
        path: '',
        name: 'login',
        component: () => import('@/components/login/index')
      },
      {
        path: 'studentLogin',
        name: 'studentLogin',
        component: StudentLogin
      },
      {
        path: 'counsellorLogin',
        name: 'counsellorLogin',
        component: CounsellorLogin
      },
      {
        path: 'adminLogin',
        name: 'adminLogin',
        component: AdminLogin
      }
    ]
  },
  {
    path: '/superAdmin',
    name: 'superAdmin',
    component: SuperAdm
  },
  {
    path: '/student',
    name: 'student',
    component: Student
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin
  },
  {
    path: '/counsellor',
    name: 'counsellor',
    component: Counsellor
  },
  // 在这之上添加新路由，保证notFound跳转404在最后
  {
    path: '*',
    redirect: {path: '/404'}
  }
]

export default new Router({
  mode: "history",
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

