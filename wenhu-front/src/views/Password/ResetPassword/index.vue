<template>
  <div id="register">
    <el-row>
      <el-col :span="7">
        <div class="grid-content" />
      </el-col>
      <el-col :span="10">
        <div class="grid-content">
          <el-card class="register_card">
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple register_font">
                  <span class="register_font">
                    重置密码
                  </span>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content input_margin">
                  <label>
                    <el-input v-model="user_phone_number" class="input-with-select " disabled placeholder="请输入手机号">
                      <template slot="prepend">中国大陆</template>
                    </el-input>
                  </label>
                </div>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content input_margin">
                  <el-input
                    v-model="user_password"
                    :show-password="true"
                    placeholder="密码（6-16位字母的组合，区分大小写）"
                    type="password"
                  />
                </div>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple">
                  <div class="register_back_gauge">
                    <el-button class="register_button" type="primary" @click="resetPassword">重置</el-button>
                  </div>
                </div>
              </el-col>
            </el-row>

          </el-card>

        </div>
      </el-col>
      <el-col :span="7">
        <div class="grid-content" />
      </el-col>
    </el-row>

  </div>

</template>

<script>
import { resetPassword } from '@/api/people'
import { Message } from 'element-ui'

export default {
  name: 'ResetPassword',
  data() {
    return {
      user_phone_number: '',
      user_password: '',
      getCodeButtonNumber: 60,
      getCodeButtonValue: '点击获取',
      startTime: 0
    }
  },
  created() {
    // const people = require('@/api/people')
    // console.log(people.login)
  },

  methods: {

    resetPassword() {
      if (this.user_phone_number === '' || this.user_password === '') {
        this.$message.error('请保证信息完整哦~')
        return false
      }
      const FloatRegexPassword = /\w{6,16}/
      if (!FloatRegexPassword.test(this.user_password)) {
        this.$message.error('错了哦，密码是6-16位字母的组合，区分大小写')
        return
      }
      const formData = new FormData()
      formData.append('email', 'this.loginForm.email')
      formData.append('password', 'this.loginForm.password')

      resetPassword(formData).then(function(response) {
        Message.success(response.data)
        return response.data
      })
        .catch(function(error) {
          console.log(error)
        })
    }

  }
}
</script>

<style scoped>

.register_card {
  background: #ffffff;

}

.register_font {
  text-align: center;
  font-size: 30px;
  color: #0066FF;

}

.register_back_gauge {
  margin-top: 10px;
}

.bg-purple {
  background: #ffffff;
}

.input_margin {
  margin-top: 10px;
  margin-bottom: 10px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.register_button {
  width: 100%;
}

</style>
