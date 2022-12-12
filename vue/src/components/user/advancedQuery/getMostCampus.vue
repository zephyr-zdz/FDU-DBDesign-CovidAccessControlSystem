<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getMostCampusForm" ref="getMostCampusForm" label-width="0">
      <el-form-item prop="day">
        <el-input style="width: 20%" placeholder="请输入天数，为空则查询全部" v-model="getMostCampusForm.day"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
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
