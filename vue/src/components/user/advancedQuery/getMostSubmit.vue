<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getMostSubmitForm" :rules="rules" ref="getMostSubmitForm" label-width="50">
      <el-form-item prop="number" label="人数">
        <el-input-number style="width: 20%" :min="1" v-model="getMostSubmitForm.number"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 20%" v-if="this.getMostSubmitForm.schoolId === -1 && this.getMostSubmitForm.classId === -1">
      <el-radio v-model="range" label="1">按全校搜索</el-radio>
      </el-form-item>
      <el-form-item style="width: 20%">
      <el-radio v-model="range" label="2" v-if="this.getMostSubmitForm.classId === -1">按院系搜索</el-radio>
      </el-form-item>
      <el-form-item v-if="range === '2'">
        <el-select style="width: 20%" placeholder="请选择院系" v-model="getMostSubmitForm.schoolId" v-if="range === '2'">
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
        <el-select style="width: 20%" placeholder="按班级搜索" v-model="getMostSubmitForm.classId" v-if="range === '3'">
          <el-option
            v-for="item in classList"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getMostSubmit()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getMostSubmitTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getMostSubmitTable.studentId}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getMostSubmitTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="appNum"
        label="提交数量">
        <template v-slot="scope">
          <span>{{ scope.row.getMostSubmitTable.appNum}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getMostSubmit',
  mounted () {
    this.getSchoolList(this.getMostSubmitForm.schoolId)
    this.getClassList(this.getMostSubmitForm.classId)
  },
  data () {
    return {
      range: '1',
      schoolList: [],
      classList: [],
      getMostSubmitTable: [],
      getMostSubmitForm: {
        number: '',
        schoolId: '',
        classId: ''
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
      this.schoolId = this.schoolList[0]
    },
    getClassList (classId) {
      this.classList = []
      this.classList = this.classList[0]
    },
    getMostSubmit () {
      var param = new FormData()
      if (this.range === '1') {
        param.append('schoolId', '*')
        param.append('classId', '*')
      } else if (this.range === '2') {
        param.append('schoolId', this.getMostSubmitForm.schoolId)
        param.append('classId', '*')
      } else {
        param.append('schoolId', '*')
        param.append('classId', this.getMostSubmitForm.classId)
      }
      if (this.getMostSubmitForm.number === '') {
        param.append('number', '*')
      } else {
        param.append('number', this.getMostSubmitForm.number)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getMostSubmitTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
