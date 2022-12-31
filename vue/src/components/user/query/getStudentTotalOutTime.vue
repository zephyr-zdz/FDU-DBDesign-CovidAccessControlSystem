<template>
  <el-card class="box-card">
    <el-form label-position="right" :model="getStudentTotalOutTimeForm" :rules="rules" ref="getStudentTotalOutTimeForm" label-width="100px">
      <el-form-item prop="studentId" label="学号">
        <el-input style="width: 20%" placeholder="请输入学号" v-model="getStudentTotalOutTimeForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentTotalOutTime()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentTotalOutTimeTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="120">
        <template>
          <span>{{ this.getStudentTotalOutTimeForm.studentId }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="totalTime"
        label="过去一年的离校总时长">
        <template>
          <span>{{ this.totalTime }}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getStudentTotalOutTime',
  data () {
    return {
      totalTime: '',
      getStudentTotalOutTimeForm: {
        schoolId: '',
        classId: '',
        studentId: ''
      },
      rules: {
        studentId: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    getStudentTotalOutTime () {
      this.$refs.getStudentTotalOutTimeForm.validate((valid) => {
        if (valid) {
          const getPath = '/api/student/outside-duration/'
          var data = {
            studentId: this.getStudentTotalOutTimeForm.studentId,
            classId: this.getStudentTotalOutTimeForm.classId,
            schoolId: this.getStudentTotalOutTimeForm.schoolId
          }
          this.$axios
            .get(getPath, {params: data})
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.totalTime = res.data.data
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.getStudentTotalOutTimeForm.resetFields()
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
