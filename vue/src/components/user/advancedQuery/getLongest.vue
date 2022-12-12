<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getLongestForm" ref="getLongestForm" label-width="0">
      <el-form-item prop="number">
        <el-input style="width: 20%" placeholder="请输入人数，为空则查询全部" v-model="getLongestForm.number"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
      <el-radio v-model="range" label="1">按全校搜索</el-radio>
      </el-form-item>
      <el-form-item style="width: 20%">
      <el-radio v-model="range" label="2">按院系搜索</el-radio>
      </el-form-item>
      <el-form-item>
        <el-select style="width: 20%" placeholder="请选择院系" v-model="getLongestForm.schoolId" v-if="range === '2'">
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
      <el-form-item>
        <el-select style="width: 20%" placeholder="按班级搜索" v-model="getLongestForm.classId" v-if="range === '3'">
          <el-option
            v-for="item in classList"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getLongest()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getLongestTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getLongestTable.studentId}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getLongestTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="leaveTime"
        label="离校时长">
        <template v-slot="scope">
          <span>{{ scope.row.getLongestTable.leaveTime}}</span>
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
      range: '',
      schoolList: [],
      classList: [],
      getLongestTable: [],
      getLongestForm: {
        number: '',
        schoolId: '',
        classId: ''
      }
    }
  },
  methods: {
    getSchoolList (schoolId) {
      ;
    },
    getClassList (classId) {
      ;
    },
    getLongest () {
      var param = new FormData()
      if (this.range === '1') {
        param.append('schoolId', -1)
        param.append('classId', -1)
      } else if (this.range === '2') {
        param.append('schoolId', this.getLongestForm.schoolId)
        param.append('classId', -1)
      } else {
        param.append('schoolId', -1)
        param.append('classId', this.getLongestForm.classId)
      }
      if (this.getLongestForm.number === '') {
        param.append('number', -1)
      } else {
        param.append('number', this.getLongestForm.number)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getLongestTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
