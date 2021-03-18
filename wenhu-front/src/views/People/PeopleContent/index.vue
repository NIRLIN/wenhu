<template>
  <div id="peopleContent">
    <el-card class="two-el-card-css">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="动态" name="first">动态</el-tab-pane>
        <el-tab-pane label="回答" name="second">回答</el-tab-pane>
        <el-tab-pane label="提问" name="third">提问</el-tab-pane>
        <el-tab-pane label="文章" name="fourth">文章</el-tab-pane>
        <el-tab-pane label="收藏" name="fifth">收藏</el-tab-pane>
        <el-tab-pane label="关注" name="sixth">关注</el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'PeopleContent',
  data() {
    return {
      activeName: 'first'
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
    }
  }
}
</script>

<style scoped>

</style>
