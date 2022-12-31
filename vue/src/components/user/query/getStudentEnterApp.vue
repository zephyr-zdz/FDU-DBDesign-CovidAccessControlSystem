<template>
  <el-card class="box-card">
    <el-form :model="getStudentEnterAppForm" ref="getStudentEnterAppForm" :rules="rules" label-width="100px">
      <el-form-item prop="status" label="状态">
        <el-select style="width: 20%" placeholder="请选择申请状态" v-model="getStudentEnterAppForm.status">
          <el-option
            v-for="item in appOption"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="studentId" label="学号">
        <el-input style="width: 20%" placeholder="请输入学号" v-model="getStudentEnterAppForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getStudentEnterApp()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getStudentEnterAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="150">
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
        prop="area"
        label="七日内所到地区"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.passingAreas}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="预计进校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.enterTime}}</span>
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
  name: 'getStudentEnterApp',
  data () {
    return {
      appOption: [{
        value: 'pending',
        label: '待审核'
      }, {
        value: 'accepted',
        label: '已同意'
      }, {
        value: 'rejected',
        label: '已拒绝'
      }, {
        value: '',
        label: '全部'
      }],
      getStudentEnterAppTable: [],
      getStudentEnterAppForm: {
        schoolId: '',
        classId: '',
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
    getStudentEnterApp () {
      this.$refs.getStudentEnterAppForm.validate((valid) => {
        if (valid) {
          const getPath = '/api/application/enter-applications/'
          var data = {
            studentId: this.getStudentEnterAppForm.studentId,
            classId: this.getStudentEnterAppForm.classId,
            schoolId: this.getStudentEnterAppForm.schoolId,
            status: this.getStudentEnterAppForm.status
          }
          this.$axios
            .get(getPath, {params: data})
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.getStudentEnterAppTable = res.data.data
                for (var i = 0; i < this.getDailyInfoTable.length; i++) {
                  this.getDailyInfoTable.enterTime = new Date(this.getDailyInfoTable.enterTime)
                  this.getDailyInfoTable.createTime = new Date(this.getDailyInfoTable.createTime)
                }
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.getStudentEnterAppForm.resetFields()
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
