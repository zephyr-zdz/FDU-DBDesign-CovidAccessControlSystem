<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getStudentOutAppForm" ref="getDailyInfoForm" label-width="0">
      <el-form-item prop="status">
        <el-select style="width: 20%" placeholder="请选择申请状态" v-model="getStudentOutAppForm.status">
          <el-option
            v-for="item in appOption"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="studentId">
        <el-input style="width: 20%" placeholder="请输入学号，为空则查询全部" v-model="getStudentOutAppForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentOutApp()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentOutAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.studentId}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="destination"
        label="目的地"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.destination}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="timeOut"
        label="预计离校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.timeOut}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="timeIn"
        label="预计入校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.timeIn}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="reason"
        label="离校原因"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.reason}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="appTime"
        label="提交时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.appTime}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="申请状态">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.status}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getStudentOutApp',
  data () {
    return {
      appOption: [{
        value: 'waiting',
        label: '待审核'
      }, {
        value: 'passed',
        label: '已同意'
      }, {
        value: 'rejected',
        label: '已拒绝'
      }, {
        value: '',
        label: '全部'
      }],
      getStudentOutAppTable: [],
      getStudentOutAppForm: {
        schoolId: '',
        className: '',
        status: '',
        studentId: ''
      }
    }
  },
  methods: {
    getStudentOutApp () {
      var param = new FormData()
      param.append('schoolId', this.getStudentOutAppForm.schoolId)
      param.append('className', this.getStudentOutAppForm.className)
      param.append('status', this.getStudentOutAppForm.status)
      if (this.getStudentOutAppForm.studentId === '') {
        param.append('studentId', -1)
      } else {
        param.append('studentId', this.getStudentOutAppForm.studentId)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getStudentOutAppTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
