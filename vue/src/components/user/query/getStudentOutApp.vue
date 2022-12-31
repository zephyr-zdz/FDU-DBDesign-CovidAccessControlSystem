<template>
  <el-card class="box-card">
    <el-form :model="getStudentOutAppForm" ref="getStudentOutAppForm" :rules="rules" label-width="100px">
      <el-form-item prop="status" label="状态">
        <el-select style="width: 20%" placeholder="请选择申请状态" v-model="getStudentOutAppForm.status">
          <el-option
            v-for="item in appOption"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="studentId" label="学号">
        <el-input style="width: 20%" placeholder="请输入学号" v-model="getStudentOutAppForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentOutApp()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentOutAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.student.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.student.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="destination"
        label="目的地"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.destination}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="timeOut"
        label="预计离校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.leaveTime}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="timeIn"
        label="预计入校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.returnTime}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="reason"
        label="离校原因"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.reason}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="appTime"
        label="提交时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="申请状态">
        <template v-slot="scope">
          <span>{{ scope.row.status}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getStudentOutApp',
  data () {
    return {
      appOption: [{
        value: 'waiting',
        label: '待审核'
      }, {
        value: 'passed',
        label: '已同意'
      }, {
        value: 'rejected',
        label: '已拒绝'
      }, {
        value: '',
        label: '全部'
      }],
      getStudentOutAppTable: [],
      getStudentOutAppForm: {
        schoolId: '',
        className: '',
        status: '',
        studentId: ''
      },
      rules: {
        studentId: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    getStudentOutApp () {
      this.$refs.getStudentOutAppForm.validate((valid) => {
        if (valid) {
          const getPath = '/api/application/leave-applications/'
          var data = {
            studentId: this.getStudentOutAppForm.studentId,
            classId: this.getStudentOutAppForm.classId,
            schoolId: this.getStudentOutAppForm.schoolId,
            status: this.getStudentOutAppForm.status
          }
          this.$axios
            .get(getPath, {params: data})
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.getStudentOutAppTable = res.data.data
                for (var i = 0; i < this.getStudentOutAppTable.length; i++) {
                  this.getStudentOutAppTable.createTime = new Date(this.getStudentOutAppTable.createTime)
                  this.getStudentOutAppTable.leaveTime = new Date(this.getStudentOutAppTable.leaveTime)
                  this.getStudentOutAppTable.returnTime = new Date(this.getStudentOutAppTable.returnTime)
                }
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.getStudentOutAppForm.resetFields()
                  }
                })
              }
            })
            .catch(failResponse => {
            })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
