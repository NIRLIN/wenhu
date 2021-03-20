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
                <div class="grid-content  register_font">
                  <span class="register_font">
                    注册
                  </span>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content  " />
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content  ">
                  <el-steps :active="active" align-center finish-status="success">
                    <el-step title="验证手机号" />
                    <el-step title="输入信息" />
                    <el-step title="完成注册" />
                  </el-steps>
                </div>
              </el-col>
            </el-row>

            <el-row v-if="active===0">
              <el-col :span="24">
                <div class="grid-content input_margin">
                  <label>
                    <el-input v-model="user_phone_number" class="input-with-select " placeholder="请输入手机号">
                      <template slot="prepend">中国大陆</template>
                    </el-input>
                  </label>
                </div>
              </el-col>
            </el-row>
            <el-row v-if="active===0">
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
            <el-row v-if="active===1">
              <el-col :span="24">
                <div class="grid-content input_margin">
                  <el-input v-model="user_name" maxlength="20" placeholder="用户名（6-16位字母的组合，区分大小写）" />
                </div>
              </el-col>
            </el-row>
            <el-row v-if="active===1">
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
            <el-row v-if="active===2">
              <el-col :span="24">
                <div class="grid-content input_margin" />
              </el-col>
            </el-row>
            <el-row v-if="active===2">
              <el-col :span="24">
                <div class="grid-content ">
                  <div class="register_back_gauge">
                    <span class="register_content">
                      注册即代表同意以下协议<el-link class="register_content" href="#/" underline>《闻乎协议》</el-link><el-link
                        class="register_content"
                        href="#/"
                        underline
                      >《隐私保护指引》</el-link>
                    </span>
                  </div>
                </div>
              </el-col>
            </el-row>
            <el-row v-if="active<=1">
              <el-col :span="24">
                <div class="grid-content ">
                  <div class="register_back_gauge">
                    <el-button class="register_button" type="primary" @click="nextFunction">下一步</el-button>
                  </div>
                </div>
              </el-col>
            </el-row>
            <el-row v-if="active===2">
              <el-col :span="24">
                <div class="grid-content ">
                  <div class="register_back_gauge">
                    <el-button class="register_button" type="primary" @click="userRegisterButton">同意注册</el-button>
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

import SlideVerify from '@/components/SlideVerify'
import { checkPhoneExistAndSendSms, userRegister } from '@/api/password'
import { Message } from 'element-ui'

export default {
  name: 'Register',
  components: { SlideVerify },
  data() {
    return {
      user_name: '',
      user_phone_number: '',
      user_verify_code_user: '',
      user_verify_code_system: '',
      verify_code_system_create_time: '',
      user_password: '',
      content: '发送验证码',
      totalTime: 10,
      canClick: true, // 添加canClick
      user_is_human: 0,
      active: 0,
      phoneNumberCheck: 0
    }
  },
  watch: {
    user_verify_code_user() {
      if (this.user_verify_code_user.length === 6) {
        if (this.user_verify_code_user === this.user_verify_code_system) {
          if (((Date.now() / 1000) - this.verify_code_system_create_time) > 60 * 15) {
            this.$message.error({
              message: '验证码时间过了哦',
              center: true
            })
            return
          }
          this.phoneNumberCheck = 1
        } else {
          this.phoneNumberCheck = 0
        }
      }
    }

  },

  methods: {
    nextFunction() {
      if (this.phoneNumberCheck === 0) {
        this.$message.error({
          message: '请验证手机号哦',
          center: true
        })
        return
      }
      if (this.phoneNumberCheck === 1) {
        this.active = 1
      }
      const FloatRegexUserName = /\w{6,16}/
      if (!FloatRegexUserName.test(this.user_name)) {
        this.$message.error('错了哦，用户名是6-16位字母的组合，区分大小写')
        return
      }
      const FloatRegexPassword = /\w{6,16}/
      if (!FloatRegexPassword.test(this.user_password)) {
        this.$message.error('错了哦，密码是6-16位字母的组合，区分大小写')
        return
      }
      if (this.active++ > 2) this.active = 3
    },
    createVerifyCode() {
      let code = ''
      for (let i = 0; i < 6; i++) {
        const random = Math.floor(Math.random() * 10)
        code += random
      }
      this.user_verify_code_system = code
      this.verify_code_system_create_time = Date.now() / 1000
      // console.log(this.user_verify_code_system)
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
    checkPhoneExistAndSendSms() {
      this.createVerifyCode()
      const checkAndSend = { 'phoneNumber': this.user_phone_number, 'verifyCode': this.user_verify_code_system }
      checkPhoneExistAndSendSms(checkAndSend).then((response) => {
        // console.log(response)
      })
    },
    userRegisterButton() {
      const submitData = { 'username': this.user_name, 'password': this.password, 'phoneNumber': this.user_phone_number }
      userRegister(submitData).then((response) => {
        Message.success({
          message: '注册成功，请登录',
          center: true
        })
      })
      this.$router.push('login')
    },

    getVerifyCodeTime() {
      if (!this.verifyPhoneNumber()) {
        return
      }
      this.checkPhoneExistAndSendSms()
      if (!this.canClick) return // 改动的是这两行代码
      this.canClick = false
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
    childByValue(childValue) {
      // childValue就是子组件传过来的值
      this.user_is_human = childValue
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

. {
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

.register_content {
  text-decoration: none;
  font-size: 13px;
  color: rgb(145, 145, 145);
}

.register_button {
  width: 100%;
}

</style>
