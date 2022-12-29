import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      counsellorId: '',
      adminId: '',
      studentId: '',
      schoolId: '',
      classId: ''
    },
    day: ''
  },
  getters: {
    studentId: state => state.user.studentId,
    schoolId: state => state.user.schoolId,
    classId: state => state.user.classId
  },
  mutations: {
    studentLogin (state, user) {
      state.user.role = (user.type === undefined) ? user.role : user.type
      state.user.username = (user.username === undefined) ? user.number : user.username
      state.isLogin = true
    }
  }
})
