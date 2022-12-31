<template>
  <div id="info">
    <h1>用户信息</h1>
    <el-divider></el-divider>
    <div>
      <h2>学号</h2>
      <span class="elements">{{studentId}}</span>
    </div>
    <el-divider></el-divider>
    <div>
      <h2>姓名</h2>
      <span class="elements">{{student.name}}</span>
    </div>
    <el-divider></el-divider>
    <div>
      <h2>身份证类型与身份证号</h2>
      <span class="elements">{{student.idType}}</span>
      <span class="elements">{{student.idNumber}}</span>
    </div>
    <el-divider></el-divider>
    <div>
      <h2>手机号</h2>
      <span class="elements">{{student.phoneNumber}}</span>
    </div>
    <el-divider></el-divider>
    <div>
      <h2>邮箱</h2>
      <span class="elements">{{student.email}}</span>
    </div>
    <el-divider></el-divider>
    <div>
      <h2>学生住址</h2>
      <span class="elements">{{student.dormAddress}}</span>
    </div>
    <el-divider></el-divider>
    <div>
      <h2>家庭地址</h2>
      <span class="elements">{{student.homeAddress}}</span>
    </div>
    <el-divider></el-divider>
    <div>
      <h2>入校权限</h2>
      <span class="elements">{{student.auth}}</span>
    </div>
    <el-divider></el-divider>
  </div>
</template>

<script>
export default {
  data () {
    return {
      studentId: this.$store.state.user.studentId,
      student: {
      }
    }
  },
  mounted () {
    this.getInfo()
  },
  methods: {
    getInfo () {
      var getPath = '/api/student/' + this.studentId
      this.$axios.get(getPath)
        .then(response => {
          if (response.data.code === 0) {
            console.log(response.data.data)
            this.student = response.data.data
            this.student.auth = this.student.auth === 'Y' ? '有' : '无'
          } else {
            this.$alert(response.data.msg, '系统提示', {confirmButtonText: '确定'})
          }
        })
    }
  }
}

</script>

<style scoped>
h1{
  font-size: 25px;
}
h2{
  font-size: 20px;
}
.elements{
  font-size: 15px;
}
</style>
