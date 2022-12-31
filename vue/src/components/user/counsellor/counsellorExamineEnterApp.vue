<template>
  <el-card class="box-card">
    <el-table :data="counsellorExamineEnterAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.studentId }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="area"
        label="七日内所到地区"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.area }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="backTime"
        label="预计进校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.backTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="other"
        label="提交时间">
        <template v-slot="scope">
          <span>{{ scope.row.other }}</span>
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
  name: 'counsellorExamineEnterApp',
  data () {
    return {
      counsellorExamineEnterAppTable: [{studentId: 1}, {studentId: 2}],
      counsellorExamineEnterAppForm: {
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
    for (var i = 0; i < this.counsellorExamineEnterAppTable; i++) {
      this.rejectForm.append({rejectReason: ''})
    }
  },
  methods: {
    approve (index) {
      var param = new FormData()
      param.append('id', this.counsellorExamineEnterAppTable[index].id)
      // todo:发送
    },
    reject (index) {
      if (this.rejectForm[index].rejectReason === '') {
        this.$alert('请填写拒绝理由')
      } else {
        var param = new FormData()
        param.append('id', this.counsellorExamineEnterAppTable[index].id)
        param.append('rejectReason', this.counsellorExamineEnterAppForm[index].rejectReason)
        // todo:发送
      }
    },
    getEnterApp () {
      var data = {
        classId: this.$store.state.user.classId,
        schoolId: this.$store.state.user.schoolId
      }
      var getPath = '' // todo: 修改
      this.$axios
        .get(getPath, {params: data})
        .then(res => {
          console.log(res)
          if (res.data.code === 0) {
            this.counsellorExamineEnterAppTable = res.data.data
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
