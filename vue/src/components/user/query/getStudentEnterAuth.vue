<template>
  <el-card class="box-card">
    <el-form label-position="right" :model="getStudentEnterAuthForm" ref="getStudentEnterAuthForm" :rules="rules" label-width="100px">
      <el-form-item prop="studentId" label="学号">
        <el-input style="width: 20%" placeholder="请输入学号" v-model="getStudentEnterAuthForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentEnterAuth()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentEnterAuthTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="auth"
        label="入校权限">
        <template v-slot="scope">
          <span>{{ scope.row.auth}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getStudentEnterAuth',
  data () {
    return {
      getStudentEnterAuthTable: [],
      getStudentEnterAuthForm: {
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
    getStudentEnterAuth () {
      this.$refs.getStudentEnterAuthForm.validate((valid) => {
        if (valid) {
          const getPath = '/api/auth/'
          var data = {
            studentId: this.getStudentEnterAuthForm.studentId,
            classId: this.getStudentEnterAuthForm.classId,
            schoolId: this.getStudentEnterAuthForm.schoolId
          }
          this.$axios
            .get(getPath, {params: data})
            .then(res => {
              console.log(res.data.code)
              if (res.data.code === 0) {
                this.getStudentEnterAuthTable = [res.data.data]
                this.getStudentEnterAuthTable[0].auth = this.getStudentEnterAuthTable[0].auth === 'Y' ? '有' : '无'
                console.log(this.getStudentEnterAuthTable)
              } else {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.getStudentEnterAuthForm.resetFields()
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
