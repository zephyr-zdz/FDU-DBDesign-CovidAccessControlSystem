<template>
  <body id="poster">
    <el-form class="login-container" :model="studentLoginForm" :rules="rules" ref="studentLoginForm">
      <h2 class="login_title">验证</h2>

      <el-form-item label="学号" prop="studentId">
        <el-input placeholder="请输入学号" type="text" v-model="studentLoginForm.studentId"></el-input>
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
        studentId: ''
      },
      stateForm: {
        studentId: '',
        classId: '',
        schoolId: ''
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
      this.$router.replace('/student') // todo
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
