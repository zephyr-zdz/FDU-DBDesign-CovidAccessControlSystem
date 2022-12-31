<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getUnapprovedEnterForm" :rules="rules" ref="getUnapprovedEnterForm" label-width="50">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getUnapprovedEnterForm.day"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getUnapprovedEnter()">查询</el-button>
      </el-form-item>
    </el-form>
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getUnapprovedEnterTable"
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
        prop="area"
        label="七日内所到地区"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.passingAreas}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="预计进校时间"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.enterTime}}</span>
        </template>
      </el-table-column>
      <el-table-column
      prop="appTime"
      label="提交时间">
      <template v-slot="scope">
        <span>{{ scope.row.createTime}}</span>
      </template>
    </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getUnapprovedEnter',
  data () {
    return {
      getUnapprovedEnterTable: [],
      totalNum: '0',
      getUnapprovedEnterForm: {
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
    getUnapprovedEnter () {
      var param = {}
      if (this.getUnapprovedEnterForm.schoolId === -1) {
        param['managerId'] = this.getUnapprovedEnterForm.schoolId
        param['n'] = this.getUnapprovedEnterForm.day
        this.$axios.get('/api/application/enter-applications/pending/manager', {params: param}).then(res => {
          this.getUnapprovedEnterTable = res.data.data
          this.getUnapprovedEnterTable.forEach(item => {
            item.createTime = new Date(item.createTime).toLocaleString()
            item.leaveTime = new Date(item.leaveTime).toLocaleString()
            item.returnTime = new Date(item.returnTime).toLocaleString()
          })
        })
      } else {
        param['counsellor'] = this.getUnapprovedEnterForm.classId
        param['n'] = this.getUnapprovedEnterForm.day
        this.$axios.get('/api/application/enter-applications/pending/counsellor', {params: param}).then(res => {
          this.getUnapprovedEnterTable = res.data.data
          this.getUnapprovedEnterTable.forEach(item => {
            item.createTime = new Date(item.createTime).toLocaleString()
            item.leaveTime = new Date(item.leaveTime).toLocaleString()
            item.returnTime = new Date(item.returnTime).toLocaleString()
          })
        })
      }
      this.totalNum = this.getUnapprovedEnterTable.length
    }
  }
}
</script>

<style scoped>

</style>
