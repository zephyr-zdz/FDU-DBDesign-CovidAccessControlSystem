<template>
  <div>
    <nav-menu></nav-menu>
    <body>
      <el-container style="border: 0px solid #fff">
        <el-aside width="300px">
          <el-menu style="border: white">
<!--            <el-submenu index="1">-->
<!--              <template slot="title"><i class="el-icon-user"></i>人员信息维护</template>-->
<!--              <el-menu-item @click="handleChange('addAdmin')">增加院系管理员</el-menu-item>-->
<!--              <el-menu-item @click="handleChange('addCounsellor')">增加辅导员</el-menu-item>-->
<!--              <el-menu-item @click="handleChange('addStudent')">增加学生</el-menu-item>-->
<!--            </el-submenu>-->
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-setting"></i>基本查询要求</template>
              <el-menu-item @click="handleChange('getDailyInfo')">过去n天的每日填报信息</el-menu-item>
              <el-menu-item @click="handleChange('getStudentEnterAuth')">学生的入校权限</el-menu-item>
              <el-menu-item @click="handleChange('getStudentEnterApp')">学生的入校申请</el-menu-item>
              <el-menu-item @click="handleChange('getStudentOutApp')">学生的出校申请</el-menu-item>
              <el-menu-item @click="handleChange('getStudentTotalOutTime')">学生过去一年离校总时长</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-menu"></i>进阶查询要求</template>
              <el-menu-item @click="handleChange('getUnapprovedEnter')">过去n天尚未审批的入校申请</el-menu-item>
              <el-menu-item @click="handleChange('getUnapprovedOut')">过去n天尚未审批的出校申请</el-menu-item>
              <el-menu-item @click="handleChange('getMostSubmit')">前n个提交入校申请最多的学生</el-menu-item>
              <el-menu-item @click="handleChange('getLongest')">前n个平均离校时间最长的学生</el-menu-item>
              <el-menu-item @click="handleChange('getNotBack')">已出校但尚未返回的学生</el-menu-item>
              <el-menu-item @click="handleChange('getMoreThan24')">未提交出校申请但离校超过24h的学生</el-menu-item>
              <el-menu-item @click="handleChange('getNotOut')">已提交出校申请但未离校的学生</el-menu-item>
              <el-menu-item @click="handleChange('getAlwaysIn')">过去n天一直在校的学生</el-menu-item>
              <el-menu-item @click="handleChange('getSameTime')">连续n天填写时间一致的学生</el-menu-item>
              <el-menu-item @click="handleChange('getMostCampus')">过去n天产生最多出入校记录的校区</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>

        <el-container>
          <el-header style="background: white" height="0px">
            <div class="welcome" style="margin-top: 10px">
            <span>
              欢迎你！超级管理员
            </span>
            </div>
          </el-header>
          <el-main
            style="margin-top: 20px"
            >
            <add-admin
              id="addAdmin"
              label="addAdmin"
              v-show="showRecord === 'addAdmin'"
              ref="addAdmin"></add-admin>
            <add-student
              id="addStudent"
              label="addStudent"
              v-show="showRecord === 'addStudent'"
              ref="addStudent"></add-student>
            <add-counsellor
              id="addCounsellor"
              label="addCounsellor"
              v-show="showRecord === 'addCounsellor'"
              ref="addCounsellor"></add-counsellor>
            <get-daily-info
              id="getDailyInfo"
              label="getDailyInfo"
              v-show="showRecord === 'getDailyInfo'"
              ref="getDailyInfo"></get-daily-info>
            <get-student-enter-auth
              id="getStudentEnterAuth"
              label="getStudentEnterAuth"
              v-show="showRecord === 'getStudentEnterAuth'"
              ref="getStudentEnterAuth"></get-student-enter-auth>
            <get-student-enter-app
              id="getStudentEnterApp"
              label="getStudentEnterApp"
              v-show="showRecord === 'getStudentEnterApp'"
              ref="getStudentEnterApp"></get-student-enter-app>
            <get-student-out-app
              id="getStudentOutApp"
              label="getStudentOutApp"
              v-show="showRecord === 'getStudentOutApp'"
              ref="getStudentOutApp"></get-student-out-app>
            <get-student-total-out-time
              id="getStudentTotalOutTime"
              label="getStudentTotalOutTime"
              v-show="showRecord === 'getStudentTotalOutTime'"
              ref="getStudentTotalOutTime"></get-student-total-out-time>
            <get-unapproved-enter
              id="getUnapprovedEnter"
              label="getUnapprovedEnter"
              v-show="showRecord === 'getUnapprovedEnter'"
              ref="getUnapprovedEnter"></get-unapproved-enter>
            <get-unapproved-out
              id="getUnapprovedOut"
              lable="getUnapprovedOut"
              v-show="showRecord === 'getUnapprovedOut'"
              ref="getUnapprovedOut"></get-unapproved-out>
            <get-most-submit
              id="getMostSubmit"
              label="getMostSubmit"
              v-show="showRecord === 'getMostSubmit'"
              ref="getMostSubmit"></get-most-submit>
            <get-longest
              id="getLongest"
              label="getLongest"
              v-show="showRecord === 'getLongest'"
              ref="getLongest"></get-longest>
            <get-not-back
              id="getNotBack"
              label="getNotBack"
              v-show="showRecord === 'getNotBack'"
              ref="getNotBack"></get-not-back>
            <get-more-than24
              id="getMoreThan24"
              label="getMoreThan24"
              v-show="showRecord === 'getMoreThan24'"
              ref="getMoreThan24"></get-more-than24>
            <get-not-out
              id="getNotOut"
              label="getNotOut"
              v-show="showRecord === 'getNotOut'"
              ref="getNotOut"></get-not-out>
            <get-always-in
              id="getAlwaysIn"
              label="getAlwaysIn"
              v-show="showRecord === 'getAlwaysIn'"
              ref="getAlwaysIn"></get-always-in>
            <get-same-time
              id="getSameTime"
              label="getSameTime"
              v-show="showRecord === 'getSameTime'"
              ref="getSameTime"></get-same-time>
            <get-most-campus
              id="getMostCampus"
              label="getMostCampus"
              v-show="showRecord === 'getMostCampus'"
              ref="getMostCampus"></get-most-campus>
          </el-main>
        </el-container>
      </el-container>
    </body>
  </div>
</template>

<script>
import NavMenu from '../../common/NavMenu'
import AddAdmin from './users/admins/addAdmin'
import AddStudent from './users/students/addStudent'
import AddCounsellor from './users/counsellor/addCounsellor'

import GetDailyInfo from '../query/getDailyInfo'
import GetStudentEnterAuth from '../query/getStudentEnterAuth'
import GetStudentEnterApp from '../query/getStudentEnterApp'
import GetStudentOutApp from '../query/getStudentOutApp'
import GetStudentTotalOutTime from '../query/getStudentTotalOutTime'
import GetUnapprovedEnter from '../advancedQuery/getUnapprovedEnter'
import GetUnapprovedOut from '../advancedQuery/getUnapprovedOut'
import GetMostSubmit from '../advancedQuery/getMostSubmit'
import GetLongest from '../advancedQuery/getLongest'
import GetNotBack from '../advancedQuery/getNotBack'
import GetMoreThan24 from '../advancedQuery/getMoreThan24'
import GetNotOut from '../advancedQuery/getNotOut'
import GetAlwaysIn from '../advancedQuery/getAlwaysIn'
import GetSameTime from '../advancedQuery/getSameTime'
import GetMostCampus from '../advancedQuery/getMostCampus'
export default {
  data () {
    return {
      showRecord: ''
    }
  },
  methods: {
    showGetNotOut () {
      this.$refs.getNotOut.getNotOut()
    },
    handleChange (value) {
      switch (value) {
        case 'addAdmin' :
          this.showRecord = value
          break
        case 'addStudent' :
          this.showRecord = value
          break
        case 'addCounsellor' :
          this.showRecord = value
          break
        case 'getDailyInfo' :
          this.$refs.getDailyInfo.getDailyInfoForm.schoolId = -1
          this.$refs.getDailyInfo.getDailyInfoForm.classId = -1
          this.showRecord = value
          break
        case 'getStudentEnterAuth' :
          this.$refs.getStudentEnterAuth.getStudentEnterAuthForm.schoolId = -1
          this.$refs.getStudentEnterAuth.getStudentEnterAuthForm.classId = -1
          this.showRecord = value
          break
        case 'getStudentEnterApp' :
          this.$refs.getStudentEnterApp.getStudentEnterAppForm.schoolId = -1
          this.$refs.getStudentEnterApp.getStudentEnterAppForm.classId = -1
          this.showRecord = value
          break
        case 'getStudentOutApp' :
          this.$refs.getStudentOutApp.getStudentOutAppForm.schoolId = -1
          this.$refs.getStudentOutApp.getStudentOutAppForm.classId = -1
          this.showRecord = value
          break
        case 'getStudentTotalOutTime' :
          this.$refs.getStudentTotalOutTime.getStudentTotalOutTimeForm.schoolId = -1
          this.$refs.getStudentTotalOutTime.getStudentTotalOutTimeForm.classId = -1
          this.showRecord = value
          break
        case 'getUnapprovedEnter' :
          this.$refs.getUnapprovedEnter.getUnapprovedEnterForm.schoolId = -1
          this.$refs.getUnapprovedEnter.getUnapprovedEnterForm.classId = -1
          this.showRecord = value
          break
        case 'getUnapprovedOut' :
          this.$refs.getUnapprovedOut.getUnapprovedOutForm.schoolId = -1
          this.$refs.getUnapprovedOut.getUnapprovedOutForm.classId = -1
          this.showRecord = value
          break
        case 'getMostSubmit' :
          this.$refs.getMostSubmit.getMostSubmitForm.schoolId = -1
          this.$refs.getMostSubmit.getMostSubmitForm.classId = -1
          this.showRecord = value
          break
        case 'getLongest' :
          this.$refs.getLongest.getLongestForm.schoolId = -1
          this.$refs.getLongest.getLongestForm.classId = -1
          this.showRecord = value
          break
        case 'getNotBack' :
          this.$refs.getNotBack.getNotBackForm.schoolId = -1
          this.$refs.getNotBack.getNotBackForm.classId = -1
          this.showRecord = value
          break
        case 'getMoreThan24' :
          this.$refs.getMoreThan24.getMoreThan24Form.schoolId = -1
          this.$refs.getMoreThan24.getMoreThan24Form.classId = -1
          this.showRecord = value
          break
        case 'getNotOut' :
          this.showGetNotOut()
          this.$refs.getNotOut.getNotOutForm.schoolId = -1
          this.$refs.getNotOut.getNotOutForm.classId = -1
          this.showRecord = value
          break
        case 'getAlwaysIn' :
          this.$refs.getAlwaysIn.getAlwaysInForm.schoolId = -1
          this.$refs.getAlwaysIn.getAlwaysInForm.classId = -1
          this.showRecord = value
          break
        case 'getSameTime' :
          this.$refs.getSameTime.getSameTimeForm.schoolId = -1
          this.$refs.getSameTime.getSameTimeForm.classId = -1
          this.showRecord = value
          break
        case 'getMostCampus' :
          this.$refs.getMostCampus.getMostCampusForm.schoolId = -1
          this.$refs.getMostCampus.getMostCampusForm.classId = -1
          this.showRecord = value
          break
        default :
          this.showRecord = ''
          break
      }
    }
  },
  components: {
    'add-student': AddStudent,
    'add-counsellor': AddCounsellor,
    'nav-menu': NavMenu,
    'add-admin': AddAdmin,
    'get-daily-info': GetDailyInfo,
    'get-student-enter-auth': GetStudentEnterAuth,
    'get-student-enter-app': GetStudentEnterApp,
    'get-student-out-app': GetStudentOutApp,
    'get-student-total-out-time': GetStudentTotalOutTime,
    'get-unapproved-enter': GetUnapprovedEnter,
    'get-unapproved-out': GetUnapprovedOut,
    'get-most-submit': GetMostSubmit,
    'get-longest': GetLongest,
    'get-not-back': GetNotBack,
    'get-more-than24': GetMoreThan24,
    'get-not-out': GetNotOut,
    'get-always-in': GetAlwaysIn,
    'get-same-time': GetSameTime,
    'get-most-campus': GetMostCampus
  }
}

</script>

<style scoped>
.welcome{
  text-align: right;
}
</style>
