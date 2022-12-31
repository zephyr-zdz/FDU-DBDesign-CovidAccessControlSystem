<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getDailyInfoForm" :rules="rules" ref="getDailyInfoForm" label-width="100">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getDailyInfoForm.day"></el-input-number>
      </el-form-item>
      <el-form-item prop="studentId" label="学号">
        <el-input style="width: 20%" placeholder="请输入学号" v-model="getDailyInfoForm.studentId"></el-input>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getDailyInfo()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getDailyInfoTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="date"
        label="日期"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getDailyInfoTable.date}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getDailyInfoTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getDailyInfoTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="temperature"
        label="体温"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getDailyInfoTable.temperature}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="position"
        label="位置">
        <template v-slot="scope">
          <span>{{ scope.row.getDailyInfoTable.position }}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getDailyInfo',
  data () {
    return {
      getDailyInfoTable: [],
      getDailyInfoForm: {
        schoolId: '',
        classId: '',
        day: '',
        studentId: ''
      },
      rules: {
        day: [
          { required: true, message: '请输入天数', trigger: 'change' }
        ],
        studentId: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    getDailyInfo () {
      this.$refs.getDailyInfoForm.validate((valid) => {
        if (valid) {
          const getPath = 'api/daily-report/recent'
          var data = {
            studentId: this.getDailyInfoForm.studentId,
            classId: this.getDailyInfoForm.classId,
            schoolId: this.getDailyInfoForm.schoolId,
            n: this.getDailyInfoForm.day
          }
          this.$axios
            .get(getPath, {params: data})
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.getDailyInfoTable = res.data.data
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.getDailyInfoForm.resetFields()
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
