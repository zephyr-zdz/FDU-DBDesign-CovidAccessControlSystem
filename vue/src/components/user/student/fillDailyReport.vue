<template>
  <el-card>
    <body id="post">
    <el-form class="loginForm" :model="fillDailyReportForm" :rules="rules" ref="fillDailyReportForm" label-width="100px">
      <h2 class="login_title">每日填报</h2>

      <el-form-item label="学号" prop="studentId">
        <el-input disabled style="width: 30%" type="text" v-model="fillDailyReportForm.studentId"></el-input>
      </el-form-item>

      <el-form-item label="体温" prop="temperature">
        <el-input-number placeholder="请输入体温" :min="35.0" :precision="1" :step="0.1" v-model="fillDailyReportForm.temperature"></el-input-number>
      </el-form-item>

      <el-form-item label="位置" prop="location">
        <el-input placeholder="请输入位置" type="text" style="width: 30%" v-model="fillDailyReportForm.location"></el-input>
      </el-form-item>

      <el-form-item prop="other" label="其他">
        <el-input placeholder="请输入其他必要信息" type="text" style="width: 30%" v-model="fillDailyReportForm.other"></el-input>
      </el-form-item>

      <el-form-item style="width: 35%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="submit()">填报</el-button>
      </el-form-item>
    </el-form>
    </body>
  </el-card>
</template>

<script>
export default {
  name: 'fillDailyReport',
  data () {
    return {
      fillDailyReportForm: {
        studentId: this.$store.getters.studentId,
        classId: this.$store.getters.classId,
        schoolId: this.$store.getters.schoolId,
        temperature: '',
        location: '',
        other: ''
      },
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
  methods: {
    submit () {
      this.$refs.fillDailyReportForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/admin/'
          var data = {
            studentId: this.fillDailyReportForm.studentId,
            schoolId: this.fillDailyReportForm.schoolId,
            classId: this.fillDailyReportForm.classId,
            location: this.fillDailyReportForm.location,
            temperature: this.fillDailyReportForm.temperature,
            other: this.fillDailyReportForm.other
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
                    this.$refs.fillDailyReportForm.resetFields()
                  }
                })
              } else {
                this.$alert('注册失败', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.fillDailyReportForm.resetFields()
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

<style scoped>

</style>
