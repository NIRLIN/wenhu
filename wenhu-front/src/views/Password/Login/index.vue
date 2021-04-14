<template>
  <div id="login">
    <el-row class="login_min_width">
      <el-col :span="7">
        <div class="grid-content" />
      </el-col>
      <el-col :span="10">
        <div class="grid-content">
          <el-card class="login_card">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="免密码登录" name="first">
                <el-row>
                  <el-col :span="24">
                    <div class="grid-content  input_margin">
                      <el-input v-model="user_phone_number" class="input-with-select " placeholder="请输入手机号">
                        <template slot="prepend">中国大陆</template>
                      </el-input>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col v-if="!user_is_human" :span="24">
                    <div class="grid-content input_margin">
                      <SlideVerify @childByValue="childByValue" />
                    </div>
                  </el-col>
                  <el-col v-if="user_is_human" :span="24">
                    <div class="grid-content input_margin">
                      <el-input
                        v-model="user_verify_code_user"
                        class="input-with-select "
                        maxlength="6"
                        placeholder="请输入短信验证码"
                      >
                        <el-button slot="append" class="button" @click="getVerifyCodeTime">
                          {{ content }}
                        </el-button>
                      </el-input>
                    </div>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col :span="24">
                    <div class="grid-content input_margin">
                      <el-button class="enroll_button " type="primary" @click="byVerifyLogin">登录</el-button>

                    </div>
                  </el-col>
                </el-row>
                <el-row />
              </el-tab-pane>
              <el-tab-pane label="密码登录" name="second">
                <el-row>
                  <el-col :span="24">
                    <div class="grid-content input_margin">
                      <el-input v-model="user_phone_number" class="input-with-select " placeholder="请输入手机号">
                        <template slot="prepend">中国大陆</template>
                      </el-input>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div class="grid-content input_margin">
                      <el-input v-model="user_password" :show-password="true" placeholder="请输入密码" type="password" />
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="8">
                    <div class="grid-content " />
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content " />
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content ">
                      <el-link :underline="false" href="#/forgetPassword">忘记密码？</el-link>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div class="grid-content ">
                      <el-button class="enroll_button " type="primary" @click="byPasswordLogin">登录</el-button>

                    </div>
                  </el-col>
                </el-row>
                <el-row />
              </el-tab-pane>
            </el-tabs>

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
import { Message } from 'element-ui'
import { userLoginByPassword, userLoginByPhoneVerify } from '@/api/password'
import SlideVerify from '@/components/SlideVerify'
// eslint-disable-next-line no-unused-vars
import { getCookie, setCookie, removeCookie } from '@/utils/login-status'

export default {
  name: 'Login',
  components: { SlideVerify },
  data() {
    return {
      activeName: 'first',
      user_phone_number: '',
      user_phone_code: '',
      user_password: '',
      user_verify_code_system: '',
      user_verify_code_user: '',
      verify_code_system_create_time: '',
      user_is_human: 0,
      content: '发送验证码',
      totalTime: 120,
      canClick: true,
      user_id: { },
      user_id_verify: { }
    }
  },
  watch: {
    user_id() {
      setCookie(this.user_id.id)
    }
  },
  created() {

  },
  methods: {
    childByValue: function(childValue) {
      // childValue就是子组件传过来的值
      this.user_is_human = childValue
    },
    verifyPhoneNumber() {
      if (this.user_phone_number === '') {
        this.$message.error('错了哦，请输入手机号')
        return false
      }
      const FloatRegexPhone = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (!FloatRegexPhone.test(this.user_phone_number)) {
        this.$message.error('错了哦，手机号错误~')
        return false
      }
      return true
    },
    getVerifyCodeTime() {
      if (!this.verifyPhoneNumber()) {
        return
      }
      if (!this.canClick) return // 改动的是这两行代码
      this.canClick = false
      this.getLoginCode()
      this.content = this.totalTime + 's后重试'
      const clock = window.setInterval(() => {
        this.totalTime--
        this.content = this.totalTime + 's后重试'
        if (this.totalTime < 0) {
          window.clearInterval(clock)
          this.content = '重新发送'
          this.totalTime = 10
          this.canClick = true // 这里重新开启
        }
      }, 1000)
    },
    getLoginCode() {
      if (!this.verifyPhoneNumber()) {
        return
      }
      this.createVerifyCode()
      const submitData = { 'phoneNumber': this.user_phone_number, 'verifyCode': this.user_verify_code_system }

      userLoginByPhoneVerify(submitData).then((response) => {
        // Message.success('成功，正在跳转')
        this.user_id_verify = JSON.parse(response.data)
        console.log(response.data)
        // this.$router.push('hot')
      })
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    createVerifyCode() {
      let code = ''
      for (let i = 0; i < 6; i++) {
        const random = Math.floor(Math.random() * 10)
        code += random
      }
      this.user_verify_code_system = code
      this.verify_code_system_create_time = Date.now() / 1000
      console.log(this.user_verify_code_system)
    },
    byVerifyLogin() {
      if (this.user_verify_code_user === this.user_verify_code_system) {
        if (((Date.now() / 1000) - this.verify_code_system_create_time) > 60 * 15) {
          this.$message.error({
            message: '验证码时间过了哦',
            center: true
          })
          return
        }
        Message.success({
          message: '成功，正在跳转',
          center: true
        })
        setCookie(this.user_id_verify.id)
        setTimeout(() => {
          this.$router.go(0)
        }, 1010)
        setTimeout(() => {
          this.$router.push('hot')
        }, 1000)
      }
    },
    byPasswordLogin() {
      const FloatRegexPhone = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (!FloatRegexPhone.test(this.user_phone_number)) {
        this.$message.error('错了哦，手机号错误~')
        return
      }
      const FloatRegexPassword = /\w{6,16}/
      if (!FloatRegexPassword.test(this.user_password)) {
        this.$message.error('错了哦，密码是6-16位字母的组合，区分大小写')
        return
      }
      const submitData = { 'phoneNumber': this.user_phone_number, 'password': this.user_password }

      userLoginByPassword(submitData).then((response) => {
        Message.success({
          message: '成功，正在跳转',
          center: true
        })
        this.user_id = JSON.parse(response.data)
        setCookie(this.user_id)
        setCookie(this.user_id_verify.id)
        setTimeout(() => {
          this.$router.go(0)
        }, 1010)
        setTimeout(() => {
          this.$router.push('hot')
        }, 1000)
      })
    }
  }
}
</script>

<style scoped>
.login_min_width {
  /*width: 400px;*/
}

.login_card {
  background: #ffffff;
}

.enroll_button {
  width: 100%;
}

.input_margin {
  margin-top: 10px;
  margin-bottom: 10px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
