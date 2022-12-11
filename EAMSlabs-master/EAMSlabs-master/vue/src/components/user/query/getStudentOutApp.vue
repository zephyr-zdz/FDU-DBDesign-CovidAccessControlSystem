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
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentOutApp()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentOutAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentOutAppTable.number}}</span>
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
        prop="timeOut"
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
        value: 'all',
        label: '全部'
      }],
      getStudentOutAppTable: [],
      getStudentOutAppForm: {
        school: '',
        className: '',
        status: ''
      }
    }
  },
  mounted () {
    this.getStudentOutApp(this.getStudentOutAppForm.school, this.getStudentOutAppForm.className)
  },
  methods: {
    getStudentOutApp (school, className) {
      ;
    }
  }
}
</script>

<style scoped>

</style>
