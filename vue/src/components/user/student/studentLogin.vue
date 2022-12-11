<template>
  <body id="poster">
    <el-form class="login-container" :model="studentLoginForm" :rules="rules" ref="studentLoginForm">
      <h2 class="login_title">验证</h2>

      <el-form-item label="学号" prop="number">
        <el-input placeholder="请输入学号" type="text" v-model="studentLoginForm.number"></el-input>
      </el-form-item>

      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">进入</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: 'studentLogin',
  data () {
    return {
      studentLoginForm: {
        type: 'student',
        number: ''
      },
      rules: {
        number: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    login () {
      this.$router.replace('/student')
      var param = new FormData()
      param.append('type', this.studentLoginForm.type)
      param.append('username', this.studentLoginForm.number)
      this.$axios
        .post('/api/user/login', param)
        .then(successResponse => {
          if (successResponse.data.data === 'true') {
            this.$store.commit('login', this.studentLoginForm)
            this.$router.replace('/user')
          } else {
            this.$alert(successResponse.data.msg, '系统提示', {
              confirmButtonText: '确定',
              callback: action => {
                this.studentLoginForm.number = ''
              }
            })
          }
        })
        .catch(failResponse => {
        })
    }
  }
}

</script>

<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: auto;
    text-align: center;
    color: #505458;
  }
</style>
