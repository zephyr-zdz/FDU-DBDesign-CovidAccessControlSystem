<template>
  <div>
    <nav-menu></nav-menu>
    <router-view/>
  </div>
</template>

<script>
import NavMenu from '../common/NavMenu'
export default {
  name: 'Login',
  components: {'nave-menu': NavMenu}
}
</script>

<style>
</style>
