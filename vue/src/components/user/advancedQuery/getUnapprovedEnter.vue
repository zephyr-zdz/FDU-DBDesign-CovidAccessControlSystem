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
          <span>{{ scope.row.getUnapprovedEnterTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getUnapprovedEnterTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="area"
        label="七日内所到地区"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getUnapprovedEnterTable.area}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="预计进校时间"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.getUnapprovedEnterTable.time}}</span>
        </template>
      </el-table-column>
      <el-table-column
      prop="appTime"
      label="提交时间">
      <template v-slot="scope">
        <span>{{ scope.row.getUnapprovedEnterTable.appTime}}</span>
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
      var param = new FormData()
      param.append('schoolId', this.getUnapprovedEnterForm.schoolId)
      param.append('classId', this.getUnapprovedEnterForm.classId)
      if (this.getUnapprovedEnterForm.day === '') {
        param.append('day', -1)
      } else {
        param.append('day', this.getUnapprovedEnterForm.day)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getUnapprovedEnterTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
