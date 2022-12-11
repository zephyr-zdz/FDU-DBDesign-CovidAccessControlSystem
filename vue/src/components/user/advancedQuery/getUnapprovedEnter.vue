<template>
  <el-card class="box-card">
    <el-form label-position="left" :model="getUnapprovedEnterForm" ref="getUnapprovedEnterForm" label-width="0">
      <el-form-item prop="day">
        <el-input style="width: 20%" placeholder="请输入查询天数" v-model="getUnapprovedEnterForm.day"></el-input>
      </el-form-item>
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" @click="getUnapprovedEnter()">查询</el-button>
      </el-form-item>
    </el-form>
    <h2>共计<span>{{ totalNum }}</span>条记录</h2>
    <el-table :data="getUnapprovedEnterTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="number"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.getUnapprovedEnterTable.number}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="120">
        <template v-slot="scope">
          <span>{{ scope.row.getUnapprovedEnterTable.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="area"
        label="七日内所到地区"
        width="300">
        <template v-slot="scope">
          <span>{{ scope.row.getUnapprovedEnterTable.area}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="time"
        label="预计进校时间"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.getUnapprovedEnterTable.time}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'getUnapprovedEnter',
  data () {
    return {
      getUnapprovedEnterTable: [],
      totalNum: '0',
      getUnapprovedEnterForm: {
        day: '',
        school: '',
        className: '',
        status: 'waiting'
      }
    }
  },
  mounted () {
    this.getUnapprovedEnter(this.getUnapprovedEnterForm.school, this.getUnapprovedEnterForm.className, 7)
  },
  methods: {
    getUnapprovedEnter (school, className, day) {
      ;
    }
  }
}
</script>

<style scoped>

</style>
