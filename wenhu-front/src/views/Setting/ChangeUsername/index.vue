<template>
  <div>
    <el-row>
      <el-col :span="24"><div class="grid-content ">
        <h3>用户名管理</h3>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-input
            v-model="username"
            maxlength="100"
            placeholder="请输入内容"
          />
        </div>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-button style="width: 100%;" type="primary" @click="saveResume">保存修改</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getCookie } from '@/utils/login-status'
import { getUsernameByUserId, saveUsernameByUserId } from '@/api/people'
import { Message } from 'element-ui'

export default {
  name: 'ChangeUsername',
  props: {
    // eslint-disable-next-line vue/require-default-prop,vue/prop-name-casing
    active_name: String
  },
  data() {
    return {
      username: '用户名'
    }
  },
  watch: {
    active_name() {
      if (getCookie() !== undefined) {
        if (this.active_name === 'username') {
          const submitData = { 'userId': getCookie() }
          getUsernameByUserId(submitData).then((response) => {
            if (response.data != null) {
              this.username = response.data
            }
          })
        }
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    }

  },
  methods: {
    saveResume() {
      if (this.username === '') {
        Message.error({
          message: '用户名未不可为空哦~',
          center: true
        })
        return
      }
      const submitData = { 'userId': getCookie(), 'username': this.username }
      saveUsernameByUserId(submitData).then((response) => {
        Message.success({
          message: '修改成功',
          center: true
        })
      })
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
