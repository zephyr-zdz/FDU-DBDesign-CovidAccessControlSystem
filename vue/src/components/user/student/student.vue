<template>
  <div>
    <nav-menu></nav-menu>
    <el-container style="border: 1px solid #fff">
      <el-aside width="200px">
        <el-menu style="border: white">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>健康日报</template>
              <el-menu-item @click="handleChange('fillDailyReport')">填写健康日报</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-user"></i>学生进出校</template>
            <el-menu-item @click="handleChange('inOut')">进出校门</el-menu-item>
            <el-menu-item @click="handleChange('fillEnterApp')">进校申请</el-menu-item>
            <el-menu-item @click="handleChange('fillOutApp')">出校申请</el-menu-item>
            <el-menu-item @click="handleChange('checkApp')">查看我的申请</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-search"></i>查询系统</template>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="background: white" height="1">
          <div class="welcome" style="margin-top: 10px">
          <span>
            欢迎你！{{ this.studentId }}同学
          </span>
          </div>
        </el-header>

        <el-main>
          <fill-daily-report
            id="fillDailyReport"
            label="fillDailyReport"
            v-show="showRecord === 'fillDailyReport'"
            ref="fillDailyReport"></fill-daily-report>
          <in-out
            id="inOut"
            label="inOut"
            v-show="showRecord === 'inOut'"
            ref="inOut"></in-out>
          <fill-enter-app
            id="fillEnterApp"
            label="fillEnterApp"
            v-show="showRecord === 'fillEnterApp'"
            ref="fillEnterApp"></fill-enter-app>
          <fill-out-app
            id="fillOutApp"
            label="fillOutApp"
            v-show="showRecord === 'fillOutApp'"
            ref="fillOutApp"></fill-out-app>
          <check-app
            id="checkApp"
            label="checkApp"
            v-show="showRecord === 'checkApp'"
            ref="checkApp"></check-app>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import NavMenu from '../../common/NavMenu'
import FillDailyReport from './fillDailyReport'
import InOut from './inOut'
import FillEnterApp from './fillEnterApp'
import FillOutApp from './fillOutApp'
import CheckApp from './checkApp'

export default {
  data () {
    return {
      studentId: this.$store.getters.studentId,
      showRecord: ''
    }
  },
  methods: {
    handleChange (value) {
      switch (value) {
        case 'fillDailyReport' :
          this.showRecord = value
          break
        case 'inOut' :
          this.showRecord = value
          break
        case 'fillEnterApp' :
          this.showRecord = value
          break
        case 'fillOutApp' :
          this.showRecord = value
          break
        case 'checkApp' :
          this.showRecord = value
          break
        default :
          this.showRecord = ''
          break
      }
    }
  },
  components: {
    'nav-menu': NavMenu,
    'fill-daily-report': FillDailyReport,
    'in-out': InOut,
    'fill-enter-app': FillEnterApp,
    'fill-out-app': FillOutApp,
    'check-app': CheckApp
  }
}

</script>

<style scoped>
.welcome{
  text-align: right;
}
h1{
  font-size: 25px;
}
h2{
  font-size: 20px;
}
.elements{
  font-size: 15px;
  color: gray;
}
</style>
