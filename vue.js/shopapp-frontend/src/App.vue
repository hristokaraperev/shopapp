<template>
  <SystemUser v-cloak />
</template>

<script>
import SystemUser from './components/SystemUser.vue'

export default {
  name: 'App',
  components: {
    SystemUser
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
