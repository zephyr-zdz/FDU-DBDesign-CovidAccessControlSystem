<template>
  <el-card class="box-card">
    <el-table :data="adminExamineEnterAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.student.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.student.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="area"
        label="七日内所到地区"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.passingAreas }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="backTime"
        label="预计返校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.enterTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="提交时间">
        <template v-slot="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column>
        <template v-slot="scope">
          <el-button size="mini" @click="approve(scope.$index)">同意</el-button>
          <el-button size="mini" @click="reject(scope.$index)">拒绝</el-button>
        </template>
      </el-table-column>
      <el-table-column>
        <template v-slot="scope">
          <el-button size="mini" @click="approve(scope.$index)">同意</el-button>
          <el-form v-model="rejectForm" ref="rejectForm">
            <el-form-item>
              <el-form-item label="拒绝理由">
                <el-input prop="reject" v-model="rejectForm[scope.$index].rejectReason"></el-input>
              </el-form-item>
              <el-button size="mini" @click="reject(scope.$index)">拒绝</el-button>
            </el-form-item>
          </el-form>

        </template>
      </el-table-column>

    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'adminExamineEnterApp',
  data () {
    return {
      adminExamineEnterAppTable: [{studentId: 1}, {studentId: 2}],
      adminExamineEnterAppForm: {
        schoolId: '',
        classId: ''
      },
      rejectForm: [
        {rejectReason: ''},
        {rejectReason: ''},
        {rejectReason: ''},
        {rejectReason: ''}
      ]
    }
  },
  mounted () {
    this.getEnterApp()
  },
  methods: {
    approve (index) {
      const postPath = '/api/enter-application/manager/approve'
      var data = new FormData()
      data.append('applicationId', this.adminExamineEnterAppTable[index].id)
      this.$axios
        .post(postPath, data)
        .then(res => {
          if (res.data.code === 0) {
            this.$alert(res.data.msg, '提示', {
              confirmButtonText: '确定'
            })
            window.location.reload()
          } else {
            this.$alert(res.data.msg, '提示', {
              confirmButtonText: '确定'
            })
          }
        })
        .catch(failResponse => {
        })
    },
    reject (index) {
      if (this.rejectForm[index].rejectReason === '') {
        this.$alert('请填写拒绝理由')
      } else {
        const postPath = '/api/enter-application/manager/reject'
        var data = new FormData()
        data.append('applicationId', this.adminExamineEnterAppTable[index].id)
        data.append('reason', this.rejectForm[index].rejectReason)
        this.$axios
          .post(postPath, data)
          .then(res => {
            if (res.data.code === 0) {
              this.$alert(res.data.msg, '提示', {
                confirmButtonText: '确定'
              })
              window.location.reload()
            } else {
              this.$alert(res.data.msg, '提示', {
                confirmButtonText: '确定'
              })
            }
          })
          .catch(failResponse => {
          })
      }
    },
    getEnterApp () {
      var param = {}
      param['managerId'] = this.$store.state.user.schoolId
      param['n'] = 9999
      var getPath = '/api/application/enter-applications/pending/manager'
      this.$axios
        .get(getPath, {params: param})
        .then(res => {
          console.log(res)
          if (res.data.code === 0) {
            this.adminExamineEnterAppTable = res.data.data
            this.adminExamineEnterAppTable.forEach((item, index) => {
              item.createTime = new Date(item.createTime).toLocaleString()
              item.enterTime = new Date(item.enterTime).toLocaleString()
              this.rejectForm.push({rejectReason: ''})
            })
          } else if (res.data.code === 1) {
            this.$alert(res.data.msg, '提示', {
              confirmButtonText: '确定'
            })
          }
        })
        .catch(failResponse => {
        })
    }
  }
}
</script>

<style scoped>

</style>
