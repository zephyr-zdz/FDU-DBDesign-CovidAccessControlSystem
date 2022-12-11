<template>
  <el-card class="box-card">
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
        school: '',
        className: ''
      }
    }
  },
  mounted () {
    this.getStudentEnterAuth(this.getStudentEnterAuthForm.school, this.getStudentEnterAuthForm.className)
  },
  methods: {
    getStudentEnterAuth (school, className) {
      ;
    }
  }
}
</script>

<style scoped>

</style>
