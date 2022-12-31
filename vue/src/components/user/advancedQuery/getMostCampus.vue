<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getMostCampusForm" ref="getMostCampusForm" :rules="rules" label-width="50">
      <el-form-item prop="day" label="天数">
        <el-input-number style="width: 20%" :min="1" v-model="getMostCampusForm.day"></el-input-number>
      </el-form-item>
      <el-form-item style="width: 25%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getMostCampus()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="getMostCampusTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="school"
        label="院系"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getMostCampusTable.school}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="campus"
        label="校区">
        <template v-slot="scope">
          <span>{{ scope.row.getMostCampusTable.campus}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getMostCampus',
  data () {
    return {
      getMostCampusTable: [],
      getMostCampusForm: {
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
    getMostCampus () {
      var param = new FormData()
      param.append('schoolId', this.getMostCampusForm.schoolId)
      param.append('classId', this.getMostCampusForm.classId)
      if (this.getMostCampusForm.day === '') {
        param.append('day', -1)
      } else {
        param.append('day', this.getMostCampusForm.day)
      }
      this.$axios.get('/api/student/student', {params: param}).then(res => {
        this.getMostCampusTable = res.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
