<template>
  <el-card class="box-card">
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getNotOutTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getNotOutTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getNotOutTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getNotOutTable.email}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="dorm"
        label="宿舍/校外住址"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getNotOutTable.dorm}}</span>
        </template>
      </el-table-column>
      <el-table-column
      prop="address"
      label="家庭地址"
      width="300">
      <template v-slot="scope">
        <span>{{ scope.row.getNotOutTable.address}}</span>
      </template>
      </el-table-column>
      <el-table-column
        prop="idType"
        label="身份证件类型"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getNotOutTable.idType}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="idNumber"
        label="身份证件号码"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getNotOutTable.idNumber}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="authority"
        label="入校权限">
        <template v-slot="scope">
          <span>{{ scope.row.getNotOutTable.authority}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getNotOut',
  data () {
    return {
      getNotOutTable: [],
      totalNum: '0',
      getNotOutForm: {
        schoolId: '',
        classId: ''
      }
    }
  },
  mounted () {
    this.getNotOut()
  },
  methods: {
    getNotOut () {
      var param = new FormData()
      param.append('schoolId', this.getNotOutForm.schoolId)
      param.append('classId', this.getNotOutForm.classId)
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getNotOutTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
