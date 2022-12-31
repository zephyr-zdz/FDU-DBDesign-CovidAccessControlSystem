<template>
  <el-card class="box-card">
    <el-table :data="counsellorExamineOutAppTable"
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
        prop="destination"
        label="目的地"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.destination }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="outTime"
        label="预计离校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.leaveTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="backTime"
        label="预计返校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.returnTime }}</span>
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
  name: 'counsellorExamineOutApp',
  data () {
    return {
      counsellorExamineOutAppTable: [],
      counsellorExamineOutAppForm: {
        schoolId: '',
        classId: ''
      },
      rejectForm: []
    }
  },
  mounted () {
    this.getLeaveApp()
  },
  methods: {
    approve (index) {
      const postPath = '/api/leave-application/counsellor/approve'
      var data = new FormData()
      data.append('applicationId', this.counsellorExamineOutAppTable[index].id)
      this.$axios
        .post(postPath, data)
        .then(res => {
          if (res.data.code === 0) {
            this.$alert(res.data.msg, '提示', {
              confirmButtonText: '确定'
            })
            this.getLeaveApp()
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
        const postPath = '/api/leave-application/counsellor/reject'
        var data = new FormData()
        data.append('applicationId', this.counsellorExamineOutAppTable[index].id)
        data.append('reason', this.rejectForm[index].rejectReason)
        this.$axios
          .post(postPath, data)
          .then(res => {
            if (res.data.code === 0) {
              this.$alert(res.data.msg, '提示', {
                confirmButtonText: '确定'
              })
              this.getLeaveApp()
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
    getLeaveApp () {
      var param = {}
      param['counsellorId'] = this.$store.state.user.classId
      param['n'] = 9999
      var getPath = '/api/application/leave-applications/pending/counsellor'
      this.$axios
        .get(getPath, {params: param})
        .then(res => {
          console.log(res)
          if (res.data.code === 0) {
            this.counsellorExamineOutAppTable = res.data.data
            this.counsellorExamineOutAppTable.forEach(function (item) {
              item.createTime = new Date(item.createTime).toLocaleString()
              item.leaveTime = new Date(item.leaveTime).toLocaleString()
              item.returnTime = new Date(item.returnTime).toLocaleString()
            })
            for (var i = 0; i < this.counsellorExamineOutAppTable.length; i++) {
              this.rejectForm.push({
                rejectReason: ''
              })
            }
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
