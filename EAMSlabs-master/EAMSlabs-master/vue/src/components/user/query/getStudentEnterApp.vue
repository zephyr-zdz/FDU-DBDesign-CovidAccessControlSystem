<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getStudentEnterAppForm" ref="getDailyInfoForm" label-width="0">
      <el-form-item prop="day">
        <el-input style="width: 20%" placeholder="请输入查询天数" v-model="getStudentEnterAppForm.day"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentEnterApp()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentEnterAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAppTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAppTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="area"
        label="七日内所到地区"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAppTable.area}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="预计进校时间">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAppTable.time}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getStudentEnterApp',
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
      getStudentEnterAppTable: [],
      getStudentEnterAppForm: {
        school: '',
        className: '',
        status: ''
      }
    }
  },
  mounted () {
    this.getStudentEnterApp(this.getStudentEnterAppForm.school, this.getStudentEnterAppForm.className)
  },
  methods: {
    getStudentEnterApp (school, className) {
      ;
    }
  }
}
</script>

<style scoped>

</style>
