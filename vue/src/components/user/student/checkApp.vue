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
          <span>{{ scope.row.type}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="申请时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template v-slot="scope">
          <span>{{ scope.row.status }}</span>
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
      var data = {
        studentId: -1,
        classId: -1,
        schoolId: this.checkAppForm.schoolId
      }
      this.$axios.get('/api/application/enter-applications', {params: data}).then(res => {
        this.checkAppTable = res.data.data
      })
      var i = 0
      for (;i < this.checkAppTable.length; i++) {
        this.checkAppTable[i].type = '进校申请'
      }
      this.$axios.get('/api/application/leave-applications', {params: data}).then(res => {
        this.checkAppTable.append(res.data.data)
      })
      for (;i < this.checkAppTable.length; i++) {
        this.checkAppTable[i].t = '出校申请'
      }
    }
  }
}
</script>

<style scoped>

</style>
