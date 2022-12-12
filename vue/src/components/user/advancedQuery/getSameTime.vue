<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getSameTimeForm" :rules="rules" ref="getSameTimeForm" label-width="50">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getSameTimeForm.day"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getSameTime()">查询</el-button>
      </el-form-item>
    </el-form>
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getSameTimeTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.email}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="dorm"
        label="宿舍/校外住址"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.dorm}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="address"
        label="家庭地址"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.address}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="idType"
        label="身份证件类型"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.idType}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="idNumber"
        label="身份证件号码"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.idNumber}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="authority"
        label="入校权限">
        <template v-slot="scope">
          <span>{{ scope.row.getSameTimeTable.authority}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getSameTime',
  data () {
    return {
      getSameTimeTable: [],
      totalNum: '0',
      getSameTimeForm: {
        day: '',
        schoolId: '',
        classId: ''
      },
      rules: {
        day: [
          { required: true, message: '请输入天数', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    getSameTime () {
      var param = new FormData()
      param.append('schoolId', this.getSameTimeForm.schoolId)
      param.append('classId', this.getSameTimeForm.classId)
      if (this.getSameTimeForm.day === '') {
        param.append('day', -1)
      } else {
        param.append('day', this.getSameTimeForm.day)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getSameTimeTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
