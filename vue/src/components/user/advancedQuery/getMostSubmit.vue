<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getMostSubmitForm" :rules="rules" ref="getMostSubmitForm" label-width="50">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getMostSubmitForm.number"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 20%" v-if="this.getMostSubmitForm.schoolId === -1 && this.getMostSubmitForm.classId === -1">
        <el-radio v-model="range" label="1">按全校搜索</el-radio>
      </el-form-item>
      <el-form-item style="width: 20%" v-if="this.getMostSubmitForm.classId === -1">
        <el-radio v-model="range" label="2">按院系搜索</el-radio>
      </el-form-item>
      <el-form-item v-if="range === '2'">
        <el-select style="width: 20%" placeholder="请选择院系" v-model="getMostSubmitForm.searchSchoolId" v-if="range === '2' && this.getMostSubmitForm.schoolId === -1">
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
        <el-select style="width: 20%" placeholder="按班级搜索" v-model="getMostSubmitForm.searchClassId" v-if="range === '3'">
          <el-option
            v-for="item in classList"
            :key="item.name"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getMostSubmit()">查询</el-button>
      </el-form-item>
    </el-form>
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getMostSubmitTable"
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
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.name}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getMostSubmit',
  data () {
    return {
      range: '1',
      schoolList: [],
      classList: [],
      getMostSubmitTable: [],
      getMostSubmitForm: {
        number: '',
        schoolId: '',
        classId: '',
        searchSchoolId: '',
        searchClassId: ''
      },
      totalNum: 0,
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
          this.getMostSubmitForm.searchSchoolId = this.schoolList[0].id
        })
      }
      console.log(this.schoolList)
    },
    getClassList (classId) {
      this.classList = []
      if (classId === -1) {
        this.$axios.get('/api/classes').then(res => {
          this.classList = res.data.data
          this.getMostSubmitForm.searchClassId = this.classList[0].id
        })
      } else {
        var param = {}
        param['majorId'] = classId
        this.$axios.get('/api/classes', {params: param}).then(res => {
          this.classList = res.data.data
        })
        this.getMostSubmitForm.searchClassId = this.classList[0].id
      }
    },
    getMostSubmit () {
      var param = new FormData()
      if (this.range === '1') {
        param = {schoolId: -1,
          classId: -1}
      } else if (this.range === '2' && this.getMostSubmitForm.schoolId === -1) {
        param = {schoolId: this.getMostSubmitForm.searchSchoolId,
          classId: -1}
      } else if (this.range === '2' && this.getMostSubmitForm.schoolId !== -1) {
        param = {schoolId: this.getMostSubmitForm.schoolId,
          classId: -1}
      } else {
        param = {schoolId: this.classList.find(item => item.id === this.getMostSubmitForm.clasearchClassIdssId).majorId,
          classId: this.getMostSubmitForm.searchClassId}
      }
      param['n'] = this.getMostSubmitForm.number
      this.$axios.get('/api/student/filter/enter-most-applying', {params: param}).then(res => {
        this.getMostSubmitTable = res.data.data
        this.totalNum = this.getMostSubmitTable.length
        if (this.getMostSubmitForm.classId !== -1 && this.getMostSubmitForm.classId !== this.getMostSubmitForm.searchClassId) {
          this.getMostSubmitTable = []
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
