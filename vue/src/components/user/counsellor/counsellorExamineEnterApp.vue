<template>
  <el-card class="box-card">
    <el-table :data="counsellorExamineEnterAppTable"
              style="width: 100%"
              pager="page">
      <el-table-column
        prop="studentId"
        label="学号"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.counsellorExamineEnterAppTable.studentId }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="area"
        label="七日内所到地区"
        width="200">
        <template v-slot="scope">
          <span>{{ scope.row.counsellorExamineEnterAppTable.area }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="backTime"
        label="预计返校时间"
        width="150">
        <template v-slot="scope">
          <span>{{ scope.row.counsellorExamineEnterAppTable.backTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="other"
        label="其他">
        <template v-slot="scope">
          <span>{{ scope.row.counsellorExamineEnterAppTable.other }}</span>
        </template>
      </el-table-column>
      <el-table-column>
        <template v-slot="scope">
          <el-button size="mini" @click="approve(scope.$index)">同意</el-button>
          <el-button size="mini" @click="reject(scope.$index)">拒绝</el-button>
        </template>
      </el-table-column>

    </el-table>
  </el-card>
</template>

<script>
export default {
  name: 'counsellorExamineEnterApp',
  data () {
    return {
      counsellorExamineEnterAppTable: []
    }
  },
  mounted () {
    this.getEnterApp()
  },
  methods: {
    approve (index) {
    },
    reject (index) {
    },
    getEnterApp () {
    }
  }
}
</script>

<style scoped>

</style>
