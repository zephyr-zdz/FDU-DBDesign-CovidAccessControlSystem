<template>
  <el-card>
    <body id="post">
    <el-form style="position: center" class="reg-container" label-position="left" :model="regForm" :rules="rules" ref="regForm" label-width="150px">
      <h3 class="reg_title">增加学生</h3>

      <el-form-item label="学号" prop="number">
        <el-input style="width: 300px" placeholder="请输入学号" v-model="regForm.number" ></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
      <el-input style="width: 300px" placeholder="请输入学生姓名" v-model="regForm.name"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input style="width: 400px" placeholder="请输入学生邮箱" v-model="regForm.email"></el-input>
      </el-form-item>
      <el-form-item label="宿舍或居住住址" prop="dorm">
        <el-input style="width: 400px" placeholder="请输入学生宿舍或居住地址" v-model="regForm.dorm"></el-input>
      </el-form-item>
      <el-form-item label="家庭地址" prop="address">
        <el-input style="width: 400px" placeholder="请输入学生家庭地址" v-model="regForm.address"></el-input>
      </el-form-item>
      <el-form-item label="身份证件类型" prop="idType">
        <el-select placeholder="请选择身份证件类型" v-model="regForm.idType">
          <el-option
            v-for="item in idTypeOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证件号" prop="idNumber">
        <el-input style="width: 300px"  v-model="regForm.idNumber" placeholder="请输入学生证件号"></el-input>
      </el-form-item>
      <el-form-item label="学院" prop="school">
        <el-select placeholder="请选择学院" v-model="regForm.school">
          <el-option
            v-for="item in schoolOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="班级" prop="class">
        <el-select placeholder="请选择班级" v-model="regForm.class">
          <el-option
            v-for="item in classOptions"
            :key="item.name"
            :label="item.name"
            :value="item.name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input style="width: 300px"  v-model="regForm.mobile" placeholder="请输入学生手机号"></el-input>
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
        number: '',
        name: '',
        email: '',
        dorm: '',
        address: '',
        idType: '',
        idNumber: '',
        school: '',
        class: '',
        mobile: ''
      },
      schoolOptions: [],
      idTypeOptions: [],
      classOptions: [],
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'change' }
        ],
        school: [
          { required: true, message: '请选择学院', trigger: 'change' }
        ],
        number: [
          { required: true, message: '请输入学号', trigger: 'change' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'change' }
        ],
        dorm: [
          { required: true, message: '请输入宿舍或住址', trigger: 'change' }
        ],
        address: [
          { required: true, message: '请输入家庭地址', trigger: 'change' }
        ],
        idType: [
          { required: true, message: '请选择证件类型', trigger: 'change' }
        ],
        idNumber: [
          { required: true, message: '请输入证件号', trigger: 'change' }
        ],
        mobile: [
          { required: true, message: '请输入手机号', trigger: 'change' }
        ],
        class: [
          { required: true, message: '请选择班级', trigger: 'change' }
        ]
      }
    }
  },
  mounted () {
    this.getSchoolAndClass()
    this.getIdType()
  },
  methods: {
    getSchoolAndClass () {
      this.$axios.get('/api/admin/institute/all')
        .then(response => {
          console.log(response.data)
          this.schoolOptions = response.data.data
          this.classOptions = response.data.data
        })
        .catch(error => {
          console.log(error)
        })
    },
    getIdType () {
      this.$axios.get('/api/admin/institute/all')
        .then(response => {
          console.log(response.data)
          this.idTypeOptions = response.data.data
        })
        .catch(error => {
          console.log(error)
        })
    },
    reg (formname) {
      this.$refs.regForm.validate((valid) => {
        if (valid) {
          const postPath = '/api/admin/' + this.regForm.type
          var user = this.regForm
          var data = {
            number: user.number,
            name: user.name,
            email: user.email,
            dorm: user.dorm,
            address: user.address,
            idType: user.idType,
            idNumber: user.idNumber,
            school: user.school,
            class: user.class,
            mobile: user.mobile
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
