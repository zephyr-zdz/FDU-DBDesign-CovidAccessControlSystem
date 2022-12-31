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
      },
      tableEnter: [],
      tableLeave: []
    }
  },
  mounted () {
    this.checkApp()
  },
  methods: {
    checkApp () {
      var data = {
        studentId: this.$store.state.user.studentId,
        classId: -1,
        schoolId: -1,
        status: ''
      }
      this.checkAppTable = []
      this.$axios.get('/api/application/enter-applications/', {params: data}).then(res => {
        console.log(res)
        res.data.data.map(item => {
          this.checkAppTable.push({
            type: '入校申请',
            createTime: new Date(item.createTime).toLocaleString(),
            status: item.status
          })
        })
      })
      this.$axios.get('/api/application/leave-applications/', {params: data}).then(res => {
        console.log(res)
        res.data.data.map(item => {
          this.checkAppTable.push({
            type: '离校申请',
            createTime: new Date(item.createTime).toLocaleString(),
            status: item.status
          })
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
