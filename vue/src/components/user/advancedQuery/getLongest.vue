<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getLongestForm" :rules="rules" ref="getLongestForm" label-width="50">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getLongestForm.number"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 20%" v-if="this.getLongestForm.schoolId === -1 && this.getLongestForm.classId === -1">
        <el-radio v-model="range" label="1">按全校搜索</el-radio>
      </el-form-item>
      <el-form-item style="width: 20%" v-if="this.getLongestForm.classId === -1">
        <el-radio v-model="range" label="2">按院系搜索</el-radio>
      </el-form-item>
      <el-form-item v-if="range === '2'">
        <el-select style="width: 20%" placeholder="请选择院系" v-model="getLongestForm.searchSchoolId" v-if="range === '2' && this.getLongestForm.schoolId === -1">
          <el-option
            v-for="item in schoolList"
            :key="item.name"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-radio v-model="range" label="3">请选择班级</el-radio>
      </el-form-item>
      <el-form-item v-if="range === '3'">
        <el-select style="width: 20%" placeholder="按班级搜索" v-model="getLongestForm.searchClassId" v-if="range === '3'">
          <el-option
            v-for="item in classList"
            :key="item.name"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getLongest()">查询</el-button>
      </el-form-item>
    </el-form>
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getLongestTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.id}}</span>
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
  name: 'getLongest',
  mounted () {
    this.getSchoolList(this.getLongestForm.schoolId)
    this.getClassList(this.getLongestForm.classId)
  },
  data () {
    return {
      range: '1',
      schoolList: [],
      classList: [],
      getLongestTable: [],
      getLongestForm: {
        number: '',
        schoolId: '',
        classId: '',
        searchSchoolId: '',
        searchClassId: ''
      },
      rules: {
        number: [
          { required: true, message: '请输入人数', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    getSchoolList (schoolId) {
      this.schoolList = []
      if (schoolId === -1) {
        this.$axios.get('/api/majors').then(res => {
          this.schoolList = res.data.data
          this.getLongestForm.searchSchoolId = this.schoolList[0].id
        })
      }
      console.log(this.schoolList)
    },
    getClassList (classId) {
      this.classList = []
      if (classId === -1) {
        this.$axios.get('/api/classes').then(res => {
          this.classList = res.data.data
          this.getLongestForm.searchClassId = this.classList[0].id
        })
      } else {
        var param = {}
        param['majorId'] = classId
        this.$axios.get('/api/classes', {params: param}).then(res => {
          this.classList = res.data.data
          this.getLongestForm.searchClassId = this.classList[0].id
        })
      }
    },
    getLongest () {
      var param = new FormData()
      if (this.range === '1') {
        param = {schoolId: -1,
          classId: -1}
      } else if (this.range === '2' && this.getLongestForm.schoolId === -1) {
        param = {schoolId: this.getLongestForm.searchSchoolId,
          classId: -1}
      } else if (this.range === '2' && this.getLongestForm.schoolId !== -1) {
        param = {schoolId: this.getLongestForm.schoolId,
          classId: -1}
      } else {
        param = {schoolId: this.classList.find(item => item.id === this.getLongestForm.searchClassId).majorId,
          classId: this.getLongestForm.searchClassId}
      }
      param['n'] = this.getLongestForm.number
      this.$axios.get('/api/student/filter/avg-outside-longest', {params: param}).then(res => {
        this.getLongestTable = res.data.data
        this.totalNum = this.getLongestTable.length
        if (this.getLongestForm.classId !== -1 && this.getLongestForm.classId !== this.getLongestForm.searchClassId) {
          this.getLongestTable = []
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
