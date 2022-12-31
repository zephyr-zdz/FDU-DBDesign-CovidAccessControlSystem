<template>
  <body id="poster">
    <el-form class="login-container" :model="counsellorLoginForm" :rules="rules" ref="counsellorLoginForm">
      <h2 class="login_title">验证</h2>

      <el-form-item label="辅导员工号" prop="number">
        <el-input placeholder="请输入工号" type="text" v-model="counsellorLoginForm.number"></el-input>
      </el-form-item>

      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">进入</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>

<script>
export default {
  name: 'counsellorLogin',
  data () {
    return {
      counsellorLoginForm: {
        type: 'counsellor',
        number: ''
      },
      rules: {
        number: [
          { required: true, message: '请输入辅导员工号', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    login () {
      // this.$router.replace('/counsellor')
      this.$refs.counsellorLoginForm.validate((valid) => {
        if (valid) {
          const getPath = 'api/counsellor/' + this.counsellorLoginForm.number
          this.$axios
            .get(getPath)
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定'
                })
                this.$store.state.user.classId = res.data.data.myClass.id
                this.$store.state.user.schoolId = res.data.data.major.id
                this.$router.replace('/counsellor')
              } else {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.counsellorLoginForm.resetFields()
                  }
                })
              }
            })
            .catch(failResponse => {
            })
        }
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
