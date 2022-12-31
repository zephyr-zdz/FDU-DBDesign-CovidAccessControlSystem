<template>
  <el-card>
    <body id="post">
    <el-form class="loginForm" :model="inOutForm" :rules="rules" ref="inOutForm">
      <h2 class="login_title">进出校</h2>
      <el-form-item>
        <el-select style="width: 20%" placeholder="选择校区" v-model="inOutForm.campusId">
          <el-option
            v-for="item in campusList"
            :key="item.name"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item style="width: 20%">
        <el-button type="primary" style="width: 50%;background: #505458;border: none" v-if="status === 'out'" v-on:click="enter()">进校</el-button>
        <el-button type="primary" style="width: 50%;background: #505458;border: none" v-if="status === 'in'" v-on:click="out()">出校</el-button>
      </el-form-item>
    </el-form>
    </body>
  </el-card>
</template>

<script>
export default {
  name: 'inOut',
  data () {
    return {
      inOutForm: {
        campusId: '2',
        studentId: this.$store.state.user.studentId
      },
      campusList: [{
        value: '1',
        label: '张江'
      }, {
        value: '2',
        label: '邯郸'
      }, {
        value: '3',
        label: '枫林'
      }, {
        value: '4',
        label: '江湾'
      }],
      status: 'in',
      rules: {
        studentId: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ],
        temperature: [
          { required: true, message: '请输入体温', trigger: 'change' }
        ],
        location: [
          { required: true, message: '请输入位置', trigger: 'change' }
        ]
      }
    }
  },
  mounted () {
    this.getStatus()
  },
  methods: {
    enter () {
      this.$refs.inOutForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/gate/gate'
          var data = {
            studentId: this.inOutForm.studentId,
            campusId: this.inOutForm.campusId,
            direction: 'in'
          }
          this.$axios
            .post(postPath, data)
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.$alert('提交成功', '提示', {
                  confirmButtonText: '确定'
                })
                this.status = 'in'
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定'
                })
              }
            })
            .catch(failResponse => {
            })
        }
      })
    },
    out () {
      this.$refs.inOutForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/gate/gate'
          var data = {
            studentId: this.inOutForm.studentId,
            campusId: this.inOutForm.campusId,
            direction: 'out'
          }
          this.$axios
            .post(postPath, data)
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.$alert('提交成功', '提示', {
                  confirmButtonText: '确定'
                })
                this.status = 'out'
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定'
                })
              }
            })
            .catch(failResponse => {
            })
        }
      })
    },
    getStatus () {
      var getPath = '/api/student/' + this.inOutForm.studentId
      this.$axios.get(getPath)
        .then(response => {
          if (response.data.code === 0) {
            console.log(response.data.data)
            this.status = response.data.data.status
          } else {
            this.$alert(response.data.msg, '系统提示', {confirmButtonText: '确定'})
          }
        })
    }
  }
}

</script>

<style scoped>

</style>
