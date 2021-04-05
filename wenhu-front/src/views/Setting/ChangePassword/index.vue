<template>
  <div>
    <el-row>
      <el-col :span="24"><div class="grid-content ">
        <h3>密码管理</h3>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-input v-model="oldPassword" show-password class="input-with-select " placeholder="请输入原密码" />
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-input v-model="newPassword" show-password class="input-with-select " placeholder="请输入新密码" />
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-button style="width: 100%;" type="primary" @click="changePassword">修改密码</el-button>
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import { changePassword } from '@/api/password'
import { getCookie } from '@/utils/login-status.js'
import { Message } from 'element-ui'

export default {
  name: 'ChangePassword',
  data() {
    return {
      oldPassword: '',
      newPassword: ''
    }
  },
  methods: {
    checkPassword() {
      const FloatRegexPassword = /\w{6,16}/
      if (!FloatRegexPassword.test(this.oldPassword)) {
        this.$message.error('错了哦，密码是6-16位字母的组合，区分大小写')
        return false
      }
      if (!FloatRegexPassword.test(this.newPassword)) {
        this.$message.error('错了哦，密码是6-16位字母的组合，区分大小写')
        return false
      }
      return true
    },
    changePassword() {
      if (getCookie() !== undefined) {
        if (this.checkPassword()) {
          const submitData = { 'userId': getCookie(), 'oldPassword': this.oldPassword, 'newPassword': this.newPassword }
          changePassword(submitData).then((response) => {
            Message.success({
              message: '密码修改成功哦~',
              center: true
            })
          })
        } else {
          Message.success({
            message: '请登录哦~',
            center: true
          })
        }
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
