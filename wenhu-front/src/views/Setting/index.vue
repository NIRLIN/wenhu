<template>
  <div id="Setting">
    <el-row class="margin_nav_size">
      <el-col :span="24">
        <div class="grid-content">
          <el-card>
            <el-tabs tab-position="left">
              <el-tab-pane label="账号管理">账号管理</el-tab-pane>
              <el-tab-pane label="密码管理">密码管理</el-tab-pane>
              <el-tab-pane label="头像管理">头像管理</el-tab-pane>
              <el-tab-pane label="登录日志">登录日志</el-tab-pane>
            </el-tabs>
          </el-card>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>

export default {
  name: 'Setting'
}
</script>

<style scoped>
.grid-content {
  min-height: 20px;
}

</style>
