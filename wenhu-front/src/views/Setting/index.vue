<template>
  <div id="Setting">
    <el-card>
      <el-row class="margin_nav_size">
        <el-col :span="24">
          <div class="grid-content">
            <el-tabs v-model="activeName" tab-position="left" @tab-click="handleClick">
              <el-tab-pane label="账号管理" name="username"><ChangePhone /></el-tab-pane>
              <el-tab-pane label="密码管理" name="password"><ChangePassword /></el-tab-pane>
              <el-tab-pane label="主页管理" name="homepage"><ChangeHomepage :active_name="activeName" /></el-tab-pane>
              <el-tab-pane label="头像管理" name="headImage"><ChangeHeadImage :active_name="activeName" /></el-tab-pane>
              <el-tab-pane label="个性签名" name="resume"><ChangeResume :active_name="activeName" /> </el-tab-pane>
              <el-tab-pane label="登录日志" name="loginLog"><LoginLog :active_name="activeName" /> </el-tab-pane>
            </el-tabs>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>

import ChangePhone from '@/views/Setting/ChangePhone'
import ChangePassword from '@/views/Setting/ChangePassword'
import ChangeHeadImage from '@/views/Setting/ChangeHeadImage'
import ChangeResume from '@/views/Setting/ChangeResume'
import LoginLog from '@/views/Setting/LoginLog'
import ChangeHomepage from '@/views/Setting/ChangeHomepage'

export default {
  name: 'Setting',
  components: { ChangeHomepage, LoginLog, ChangeResume, ChangeHeadImage, ChangePassword, ChangePhone },
  data() {
    return {
      activeName: 'username'
    }
  },
  methods: {
    handleClick(tab, event) {
      if (this.activeName === 'homepage') {
        console.log('homepage')
      }
      if (this.activeName === 'headImage') {
        console.log('headImage')
      }
      if (this.activeName === 'loginLog') {
        console.log('loginLog')
      }
    }
  }
}
</script>

<style scoped>
.grid-content {
  min-height: 20px;
  margin-top: 10px;
}

</style>
