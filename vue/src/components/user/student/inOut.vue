<template>
  <el-card>
    <body id="post">
    <el-form class="loginForm" :model="inOutForm" :rules="rules" ref="inOutForm">
      <h2 class="login_title">进出校</h2>

      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-if="status === 'out'" v-on:click="enter()">进校</el-button>
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-if="status === 'in'" v-on:click="out()">出校</el-button>
      </el-form-item>
    </el-form>
    </body>
  </el-card>
</template>

<script>
export default {
  name: 'inOut',
  data () {
    return {
      inOutForm: {
      },
      status: 'in',
      rules: {
        studentId: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ],
        temperature: [
          { required: true, message: '请输入体温', trigger: 'change' }
        ],
        location: [
          { required: true, message: '请输入位置', trigger: 'change' }
        ]
      }
    }
  },
  mounted () {
    this.getStatus()
  },
  methods: {
    enter () {
      this.status = 'in'
      this.$refs.inOutForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/admin/'
          var data = {
            studentId: this.inOutForm.studentId,
            schoolId: this.inOutForm.schoolId,
            classId: this.inOutForm.classId,
            location: this.inOutForm.location,
            temperature: this.inOutForm.temperature,
            other: this.inOutForm.other
          }
          this.$axios
            .post(postPath, data)
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.$alert('提交成功', '提示', {
                  confirmButtonText: '确定'
                })
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.inOutForm.resetFields()
                  }
                })
              } else {
                this.$alert('注册失败', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.inOutForm.resetFields()
                  }
                })
              }
            })
            .catch(failResponse => {
            })
        }
      })
    },
    out () {
      this.status = 'out'
      this.$refs.inOutForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/admin/'
          var data = {
            studentId: this.inOutForm.studentId,
            schoolId: this.inOutForm.schoolId,
            classId: this.inOutForm.classId,
            location: this.inOutForm.location,
            temperature: this.inOutForm.temperature,
            other: this.inOutForm.other
          }
          this.$axios
            .post(postPath, data)
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.$alert('提交成功', '提示', {
                  confirmButtonText: '确定'
                })
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.inOutForm.resetFields()
                  }
                })
              } else {
                this.$alert('注册失败', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.inOutForm.resetFields()
                  }
                })
              }
            })
            .catch(failResponse => {
            })
        }
      })
    },
    getStatus () {
    }
  }
}

</script>

<style scoped>

</style>
