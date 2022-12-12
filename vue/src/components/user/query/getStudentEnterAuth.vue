<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getStudentEnterAuthForm" ref="getStudentEnterAuthForm" label-width="0">
      <el-form-item prop="studentId">
        <el-input style="width: 20%" placeholder="请输入学号，为空则查询全部" v-model="getStudentEnterAuthForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
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
          <span>{{ scope.row.getStudentEnterAuthTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAuthTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="auth"
        label="入校权限">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAuthTable.auth}}</span>
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
      }
    }
  },
  methods: {
    getStudentEnterAuth () {
      var param = new FormData()
      param.append('schoolId', this.getStudentEnterAuthForm.schoolId)
      param.append('classId', this.getStudentEnterAuthForm.classId)
      if (this.getStudentEnterAuthForm.studentId === '') {
        param.append('studentId', '*')
      } else {
        param.append('studentId', this.getStudentEnterAuthForm.studentId)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getStudentEnterAuthTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
