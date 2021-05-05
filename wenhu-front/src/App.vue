<template>
  <div id="app">
    <AppMain style="background-color: #f6f6f6;" />
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import AppMain from '@/views/AppMain'

export default {
  name: 'App',
  components: {
    AppMain
  }
}
</script>
<style>
#app {
  background-color: #f6f6f6;
  min-height: calc(100vh);
}
</style>
