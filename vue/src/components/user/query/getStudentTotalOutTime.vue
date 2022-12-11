<template>
  <el-card class="box-card">
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
        school: '',
        className: ''
      }
    }
  },
  mounted () {
    this.getStudentTotalOutTime(this.getStudentTotalOutTimeForm.school, this.getStudentTotalOutTimeForm.className)
  },
  methods: {
    getStudentTotalOutTime (school, className) {
      ;
    }
  }
}
</script>

<style scoped>

</style>
