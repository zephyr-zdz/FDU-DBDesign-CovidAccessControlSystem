<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getAlwaysInForm" :rules="rules" ref="getAlwaysInForm" label-width="50">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getAlwaysInForm.day"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 20%">
      <el-radio v-model="range" label="1">按全校搜索</el-radio>
      </el-form-item>
      <el-form-item style="width: 20%">
      <el-radio v-model="range" label="2">按院系搜索</el-radio>
      </el-form-item>
      <el-form-item v-if="range === '2'">
        <el-select style="width: 20%" placeholder="请选择院系" v-model="getAlwaysInForm.schoolId" v-if="range === '2'">
          <el-option
            v-for="item in schoolList"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
      <el-radio v-model="range" label="3">请选择班级</el-radio>
      </el-form-item>
      <el-form-item v-if="range === '3'">
        <el-select style="width: 20%" placeholder="按班级搜索" v-model="getAlwaysInForm.classId" v-if="range === '3'">
          <el-option
            v-for="item in classList"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getAlwaysIn()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getAlwaysInTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.studentId}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名">
        <template v-slot="scope">
          <span>{{ scope.row.name}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getAlwaysIn',
  mounted () {
    this.getSchoolList(this.getAlwaysInForm.schoolId)
    this.getClassList(this.getAlwaysInForm.classId)
  },
  data () {
    return {
      range: '1',
      schoolList: [],
      classList: [],
      getAlwaysInTable: [],
      getAlwaysInForm: {
        day: '',
        schoolId: '',
        classId: ''
      },
      rules: {
        day: [
          { required: true, message: '请输入天数', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    getSchoolList (schoolId) {
      this.schoolList = []
      this.getAlwaysInForm.schoolId = this.schoolList[0]
    },
    getClassList (classId) {
      this.classList = []
      this.getAlwaysInForm.classId = this.classList[0]
    },
    getAlwaysIn () {
      var param = new FormData()
      if (this.range === '1') {
        param = {schoolId: -1,
          classId: -1}
      } else if (this.range === '2') {
        param = {schoolId: this.getAlwaysInForm.schoolId,
          classId: -1}
      } else {
        param = {schoolId: -1,
          classId: this.getAlwaysInForm.classId}
      }
      if (this.getAlwaysInForm.day === '') {
        param['n'] = -1
      } else {
        param['n'] = this.getAlwaysInForm.day
      }
      this.$axios.get('/api/student/filter/otaku/', {params: param}).then(res => {
        this.getAlwaysInTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
