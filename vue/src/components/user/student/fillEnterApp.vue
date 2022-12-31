<template>
  <el-card>
    <body id="post">
    <el-form class="loginForm" :model="fillEnterAppForm" :rules="rules" ref="fillEnterAppForm" label-width="150px">
      <h2 class="login_title">进校申请</h2>

      <el-form-item label="学号" prop="studentId">
        <el-input disabled style="width: 30%" type="text" v-model="fillEnterAppForm.studentId"></el-input>
      </el-form-item>

      <el-form-item label="七日内所到地区" prop="area">
        <el-input placeholder="请输入七日内所到地区" type="text" style="width: 30%" v-model="fillEnterAppForm.area"></el-input>
      </el-form-item>

      <el-form-item label="预计返校时间" prop="date">
        <el-date-picker placeholder="请输入预计返校时间" type="date" style="width: 30%" v-model="fillEnterAppForm.date"></el-date-picker>
      </el-form-item>

      <el-form-item prop="other" label="其他">
        <el-input placeholder="请输入其他必要信息" type="text" style="width: 30%" v-model="fillEnterAppForm.other"></el-input>
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
  name: 'fillEnterApp',
  data () {
    return {
      fillEnterAppForm: {
        studentId: this.$store.state.studentId,
        classId: this.$store.state.classId,
        schoolId: this.$store.state.schoolId,
        area: '',
        date: '',
        other: ''
      },
      timeStamp: '',
      rules: {
        studentId: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ],
        area: [
          { required: true, message: '请输入七日内所到地区', trigger: 'change' }
        ],
        date: [
          { required: true, message: '请输入预计返校时间', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    submit () {
      this.$refs.fillEnterAppForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/admin/'
          this.timeStamp = Date.parse(this.fillEnterAppForm.date)
          alert(this.timeStamp)
          var data = {
            studentId: this.fillEnterAppForm.studentId,
            schoolId: this.fillEnterAppForm.schoolId,
            classId: this.fillEnterAppForm.classId,
            passingAreas: this.fillEnterAppForm.location,
            other: this.fillEnterAppForm.other
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
                    this.$refs.fillEnterAppForm.resetFields()
                  }
                })
              } else {
                this.$alert('注册失败', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.fillEnterAppForm.resetFields()
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
