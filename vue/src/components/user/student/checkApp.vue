<template>
  <el-card class="box-card">
    <el-table :data="checkAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="type"
        label="申请类型"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.checkAppTable.type}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="申请时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.checkAppTable.time}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template v-slot="scope">
          <span>{{ scope.row.checkAppTable.status }}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'checkApp',
  data () {
    return {
      checkAppTable: [],
      checkAppForm: {
        schoolId: '',
        classId: '',
        studentId: ''
      }
    }
  },
  mounted () {
    this.checkApp()
  },
  methods: {
    checkApp () {
      var param = new FormData()
      param.append('schoolId', this.checkAppForm.schoolId)
      param.append('classId', this.checkAppForm.classId)
      param.append('studentId', this.checkAppForm.studentId)
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.checkAppTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
