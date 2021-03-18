<template>
  <div id="questionLog">
    <el-row>
      <el-col :offset="4" :span="10">
        <div class="grid-content bg-purple">
          <questionLogItem></questionLogItem>
          <el-divider></el-divider>
          <questionLogItem></questionLogItem>
          <el-divider></el-divider>

          <questionLogItem></questionLogItem>
          <el-divider></el-divider>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content bg-purple">
          <el-button type="primary">关注问题</el-button>
          <br>
          38891 人关注该问题
          <el-divider></el-divider>
          问题状态
          <br>
          最近活动于 2021-01-12 • 查看问题日志
          被浏览 3274096 次，相关话题关注者 30613022 人
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import questionLogItem from './questionLogItem'

export default {
  name: "questionLog",
  components: {
    questionLogItem
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;

&
:last-child {
  margin-bottom: 0;
}

}
.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>