<template>
  <body id="poster">
    <el-container class="main">
      <el-main>
        <h2>欢迎使用复旦大学进出校管理系统！</h2>
        <br>
        <el-button type="primary" @click="superLogin()" >
            我是超级管理员
        </el-button>
        <el-button type="primary" onclick="window.location.href='/login/adminLogin'" >
            我是院系管理员
        </el-button>
        <el-button type="primary" onclick="window.location.href='/login/counsellorLogin'" >
            我是辅导员
        </el-button>
        <el-button type="primary" onclick="window.location.href='/login/studentLogin'" >
           我是学生
        </el-button>
        <br>
      </el-main>
    </el-container>
  </body>
</template>

<script>
export default {
  name: 'login',
  methods: {
    superLogin () {
      this.$store.state.user.classId = -1
      this.$store.state.user.schoolId = -1
      window.location.href = '/superAdmin'
    }
  }
}
</script>

<style scoped>
.main{
 text-align: center;
}
#poster {
    height: 100%;
    width: 100%;
    position: fixed;
    margin: 0;
    background-color: #ffffff00;
    overflow: hidden;
  }
  body{
    margin: 0px;
    /* background: url("~@/assets/background.png"); */
    /*background-position: top;*/
    background-size: auto;
    background-color: #ffffff;
  }
</style>
