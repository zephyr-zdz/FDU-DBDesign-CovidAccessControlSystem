<template>
  <el-card>
    <body id="post">
    <el-form label-position="left" :model="regForm" :rules="rules" ref="regForm" label-width="150px">
      <h3 class="reg_title">增加院系管理员</h3>

      <el-form-item label="学院" prop="school">
        <el-select placeholder="请选择学院" v-model="regForm.school" @change="getMajorOptions(regForm.school)">
          <el-option
            v-for="item in schoolOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="院系管理员工号" prop="number">
        <el-input placeholder="请输入工号" v-model="regForm.number" style="width: 300px"></el-input>
      </el-form-item>

      <el-form-item label="姓名" prop="name">
        <el-input placeholder="请输入姓名" v-model="regForm.name" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 60%;background: #505458;border: none" @click="reg(regForm)">注册</el-button>
      </el-form-item>
    </el-form>
    </body>
  </el-card>
</template>

<script>
export default {
  name: 'addAdmin',
  data () {
    return {
      regForm: {
        name: '',
        number: '',
        school: ''
      },
      schoolOptions: [],
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'change' }
        ],
        school: [
          { required: true, message: '请选择学院', trigger: 'change' }
        ],
        number: [
          { required: true, message: '请输入管理员工号', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    reg (formname) {
      this.$refs.regForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/admin/' + this.regForm.type
          var user = this.regForm
          var data = {
            name: user.name,
            number: user.number,
            school: user.school
          }
          this.$axios
            .post(postPath, data)
            .then(res => {
              console.log(res)
              if (res.data.code === 0) {
                this.$alert('注册成功', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.regForm.resetFields()
                  }
                })
              } else if (res.data.code === 1) {
                this.$alert(res.data.msg, '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.regForm.resetFields()
                  }
                })
              } else {
                this.$alert('注册失败', '提示', {
                  confirmButtonText: '确定',
                  callback: action => {
                    this.$refs.regForm.resetFields()
                  }
                })
              }
            })
            .catch(failResponse => {
            })
        }
      })
    }
  }
}
</script>

<style>

#post {
  height: auto;
  width: auto;
  position: relative;
  margin: 0;
  overflow: hidden;
}
body{
  margin: 0px;
}
</style>
