<template>
  <el-card>
    <body id="post">
    <el-form class="loginForm" :model="fillOutAppForm" :rules="rules" ref="fillOutAppForm" label-width="150px">
      <h2 class="login_title">出校申请</h2>

      <el-form-item label="学号" prop="studentId">
        <el-input disabled style="width: 30%" type="text" v-model="fillOutAppForm.studentId"></el-input>
      </el-form-item>

      <el-form-item label="目的地" prop="destination">
        <el-input placeholder="请输入目的地" type="text" style="width: 30%" v-model="fillOutAppForm.destination"></el-input>
      </el-form-item>

      <el-form-item label="预计离校时间" prop="outDate">
        <el-date-picker placeholder="请输入预计离校时间" type="date" style="width: 30%" v-model="fillOutAppForm.outDate"></el-date-picker>
      </el-form-item>

      <el-form-item label="预计返校时间" prop="inDate">
        <el-date-picker placeholder="请输入预计返校时间" type="date" style="width: 30%" v-model="fillOutAppForm.inDate"></el-date-picker>
      </el-form-item>

      <el-form-item prop="other">
        <div slot="label" style="margin-left: 10px">其他</div>
        <el-input placeholder="请输入其他必要信息" type="text" style="width: 30%" v-model="fillOutAppForm.other"></el-input>
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
  name: 'fillOutApp',
  data () {
    return {
      fillOutAppForm: {
        studentId: this.$store.getters.studentId,
        classId: this.$store.getters.classId,
        schoolId: this.$store.getters.schoolId,
        destination: '',
        outDate: '',
        inDate: '',
        other: ''
      },
      rules: {
        studentId: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ],
        destination: [
          { required: true, message: '请输入目的地', trigger: 'change' }
        ],
        outDate: [
          { required: true, message: '请输入预计离校时间', trigger: 'change' }
        ],
        inDate: [
          { required: true, message: '请输入预计返校时间', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    submit () {
      this.$refs.fillOutAppForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/admin/'
          var data = {
            studentId: this.fillOutAppForm.studentId,
            schoolId: this.fillOutAppForm.schoolId,
            classId: this.fillOutAppForm.classId,
            location: this.fillOutAppForm.location,
            temperature: this.fillOutAppForm.temperature,
            other: this.fillOutAppForm.other
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
                    this.$refs.fillOutAppForm.resetFields()
                  }
                })
              } else {
                this.$alert('注册失败', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.fillOutAppForm.resetFields()
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
