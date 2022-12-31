<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getUnapprovedOutForm" :rules="rules" ref="getUnapprovedOutForm" label-width="50">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getUnapprovedOutForm.day"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getUnapprovedOut()">查询</el-button>
      </el-form-item>
    </el-form>
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getUnapprovedOutTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.studentId}}</span>
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
        prop="destination"
        label="目的地"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.destination}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="timeOut"
        label="预计离校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.timeOut}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="timeIn"
        label="预计入校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.timeIn}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="reason"
        label="离校原因"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.reason}}</span>
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
  name: 'getUnapprovedOut',
  data () {
    return {
      getUnapprovedOutTable: [],
      totalNum: '0',
      getUnapprovedOutForm: {
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
    getUnapprovedOut () {
      var param = new FormData()
      param.append('schoolId', this.getUnapprovedOutForm.schoolId)
      param.append('classId', this.getUnapprovedOutForm.classId)
      if (this.getUnapprovedOutForm.day === '') {
        param.append('day', -1)
      } else {
        param.append('day', this.getUnapprovedOutForm.day)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getUnapprovedOutTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
