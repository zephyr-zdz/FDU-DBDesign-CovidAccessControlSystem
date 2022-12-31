<template>
  <el-menu
    :default-active="this.$route.path"
    router
    mode="horizontal"
    background-color="white"
    text-color="#303133"
    active-text-color="#409EFF"
    style="width:100%;">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
    <span style="position: absolute;padding-top: 20px;right: 45%;font-size: 20px;font-weight: bold">复旦大学进出校管理系统</span>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    var theNavList = [{name: '/home', navItem: '欢迎'}]
    theNavList.push({name: '/login', navItem: '首页'})
    return {navList: theNavList}
  }
}
</script>

<style scoped>
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }
</style>
