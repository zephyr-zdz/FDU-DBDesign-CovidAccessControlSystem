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
      counsellorExamineOutAppTable: [{studentId: 1}, {studentId: 2}],
      counsellorExamineOutAppFrom: {
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
    for (var i = 0; i < this.counsellorExamineOutAppTable; i++) {
      this.rejectForm.append({rejectReason: ''})
    }
  },
  methods: {
    approve (index) {
      const postPath = '/leave-application/counsellor/approve'
      var data = {
        applicationId: this.counsellorExamineOutAppTable[index].id
      }
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
        const postPath = '/leaver-application/counsellor/reject'
        var data = {
          applicationId: this.counsellorExamineOutAppTable[index].id,
          reason: this.rejectForm[index].rejectReason
        }
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
      param['counsellorId'] = this.$store.state.user.classId
      param['n'] = -1
      var getPath = '/application/leave-applications/pending/counsellor'
      this.$axios
        .get(getPath, {params: param})
        .then(res => {
          console.log(res)
          if (res.data.code === 0) {
            this.counsellorExamineOutAppTable = res.data.data
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
