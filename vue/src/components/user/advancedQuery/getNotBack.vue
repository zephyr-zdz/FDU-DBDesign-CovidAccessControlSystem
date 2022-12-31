<template>
  <el-card class="box-card">
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getNotBackTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.student.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.student.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.student.email}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="phone"
        label="手机"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.student.phoneNumber}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="dorm"
        label="宿舍/校外住址"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.student.dormAddress}}</span>
        </template>
      </el-table-column>
      <el-table-column
      prop="address"
      label="家庭地址"
      width="300">
      <template v-slot="scope">
        <span>{{ scope.row.student.homeAddress}}</span>
      </template>
      </el-table-column>
      <el-table-column
        prop="idType"
        label="身份证件类型"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.student.idType}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="idNumber"
        label="身份证件号码"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.student.idNumber}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="authority"
        label="入校权限"
        width="100">
        <template v-slot="scope">
          <span>{{ scope.row.student.auth}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="leaveTime"
        label="离校时间">
        <template v-slot="scope">
          <span>{{ scope.row.leaveTimeStr}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getNotBack',
  data () {
    return {
      getNotBackTable: [],
      totalNum: '0',
      getNotBackForm: {
        schoolId: '',
        classId: ''
      }
    }
  },
  mounted () {
    this.getNotBack()
  },
  methods: {
    getNotBack () {
      var param = new FormData()
      param.append('schoolId', this.getNotBackForm.schoolId)
      param.append('classId', this.getNotBackForm.classId)
      this.$axios.get('/api/student/filter/outside/', {params: param}).then(res => {
        this.getNotBackTable = res.data.data
        this.totalNum = this.getNotBackTable.length
      })
    }
  }
}
</script>

<style scoped>

</style>
