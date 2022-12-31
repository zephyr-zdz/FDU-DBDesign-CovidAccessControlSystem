<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getStudentEnterAppForm" ref="getDailyInfoForm" label-width="0">
      <el-form-item prop="status">
        <el-select style="width: 20%" placeholder="请选择申请状态" v-model="getStudentEnterAppForm.status">
          <el-option
            v-for="item in appOption"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="studentId">
        <el-input style="width: 20%" placeholder="请输入学号，为空则查询全部" v-model="getStudentEnterAppForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentEnterApp()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentEnterAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAppTable.studentId}}</span>
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
          <span>{{ scope.row.getStudentEnterAppTable.passingAreas}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="预计进校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAppTable.time}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="appTime"
        label="提交时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentEnterAppTable.appTime}}</span>
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
  name: 'getStudentEnterApp',
  data () {
    return {
      appOption: [{
        value: 'pending',
        label: '待审核'
      }, {
        value: 'accepted',
        label: '已同意'
      }, {
        value: 'rejected',
        label: '已拒绝'
      }, {
        value: '',
        label: '全部'
      }],
      getStudentEnterAppTable: [],
      getStudentEnterAppForm: {
        schoolId: '',
        classId: '',
        status: '',
        studentId: ''
      }
    }
  },
  methods: {
    getStudentEnterApp () {
      var param = new FormData()
      param.append('schoolId', this.getStudentEnterAppForm.schoolId)
      param.append('classId', this.getStudentEnterAppForm.classId)
      param.append('status', this.getStudentEnterAppForm.status)
      if (this.getStudentEnterAppForm.studentId === '') {
        param.append('studentId', -1)
      } else {
        param.append('studentId', this.getStudentEnterAppForm.studentId)
      }
      this.$axios.get('/application/enter-applications/', {params: param}).then(res => {
        this.getStudentEnterAppTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
