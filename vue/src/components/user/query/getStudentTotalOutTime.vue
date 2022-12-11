<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getStudentTotalOutTimeForm" ref="getStudentTotalOutTimeForm" label-width="0">
      <el-form-item prop="studentId">
        <el-input style="width: 20%" placeholder="请输入学号，为空则查询全部" v-model="getStudentTotalOutTimeForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentTotalOutTime()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentTotalOutTimeTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentTotalOutTimeTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentTotalOutTimeTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="totalTime"
        label="过去一年的离校总时长">
        <template v-slot="scope">
          <span>{{ scope.row.getStudentTotalOutTimeTable.totalTime}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getStudentTotalOutTime',
  data () {
    return {
      getStudentTotalOutTimeTable: [],
      getStudentTotalOutTimeForm: {
        schoolId: '',
        classId: '',
        studentId: ''
      }
    }
  },
  methods: {
    getStudentTotalOutTime () {
      var param = new FormData()
      param.append('schoolId', this.getStudentTotalOutTimeForm.schoolId)
      param.append('className', this.getStudentTotalOutTimeForm.className)
      if (this.getStudentTotalOutTimeForm.studentId === '') {
        param.append('studentId', -1)
      } else {
        param.append('studentId', this.getStudentTotalOutTimeForm.studentId)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getStudentEnterAppTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
