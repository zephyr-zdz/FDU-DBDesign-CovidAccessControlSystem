<template>
  <el-card class="box-card">
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getMoreThan24Table"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getMoreThan24Table.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getMoreThan24Table.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getMoreThan24Table.email}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="dorm"
        label="宿舍/校外住址"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getMoreThan24Table.dorm}}</span>
        </template>
      </el-table-column>
      <el-table-column
      prop="address"
      label="家庭地址"
      width="300">
      <template v-slot="scope">
        <span>{{ scope.row.getMoreThan24Table.address}}</span>
      </template>
      </el-table-column>
      <el-table-column
        prop="idType"
        label="身份证件类型"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getMoreThan24Table.idType}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="idNumber"
        label="身份证件号码"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getMoreThan24Table.idNumber}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="authority"
        label="入校权限">
        <template v-slot="scope">
          <span>{{ scope.row.getMoreThan24Table.authority}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getMoreThan24',
  data () {
    return {
      getMoreThan24Table: [],
      totalNum: '0',
      getMoreThan24Form: {
        schoolId: '',
        classId: ''
      }
    }
  },
  mounted () {
    this.getMoreThan24()
  },
  methods: {
    getMoreThan24 () {
      var param = new FormData()
      param.append('schoolId', this.getMoreThan24Form.schoolId)
      param.append('classId', this.getMoreThan24Form.classId)
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getMoreThan24Table = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
