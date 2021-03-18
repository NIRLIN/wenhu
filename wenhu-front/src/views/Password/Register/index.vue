<template>
  <div id="register">
    <el-row>
      <el-col :span="7">
        <div class="grid-content"/>
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
                <div class="grid-content  "/>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content  ">
                  <el-steps :active="active" align-center finish-status="success">
                    <el-step title="验证手机号"/>
                    <el-step title="输入信息"/>
                    <el-step title="完成注册"/>
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
                  <SlideVerify @childByValue="childByValue"/>
                </div>
              </el-col>
              <el-col v-if="user_is_human" :span="24">
                <div class="grid-content input_margin">
                  <el-input v-model="user_verify_code_user" class="input-with-select " maxlength="6"
                            placeholder="请输入短信验证码">
                    <el-button slot="append" class="button" @click="countDown">
                      {{ content }}
                    </el-button>
                  </el-input>
                </div>
              </el-col>
            </el-row>
            <el-row v-if="active===1">
              <el-col :span="24">
                <div class="grid-content input_margin">
                  <el-input v-model="user_name" maxlength="20" placeholder="请输入用户名"/>
                </div>
              </el-col>
            </el-row>
            <el-row v-if="active===1">
              <el-col :span="24">
                <div class="grid-content input_margin">
                  <el-input v-model="user_password" :show-password="true" placeholder="密码（6-16位字母的组合，区分大小写）"
                            type="password"/>
                </div>
              </el-col>
            </el-row>
            <el-row v-if="active===2">
              <el-col :span="24">
                <div class="grid-content input_margin"/>
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
                    <el-button class="register_button" type="primary" @click="nextFunction">同意注册</el-button>
                  </div>
                </div>
              </el-col>
            </el-row>

          </el-card>

        </div>
      </el-col>
      <el-col :span="7">
        <div class="grid-content"/>
      </el-col>
    </el-row>

  </div>

</template>

<script>

import SlideVerify from '@/components/SlideVerify'
import {checkPhoneIsExist, userRegister} from '@/api/password'
import {getHotList} from '@/api/home'
import {Message} from 'element-ui'

export default {
  name: 'Register',
  components: {SlideVerify},
  data() {
    return {
      user_name: '',
      user_phone_number: '',
      user_verify_code_user: '',
      user_verify_code_system: '',
      user_password: '',
      content: '发送验证码',
      totalTime: 10,
      canClick: true, // 添加canClick
      user_is_human: 0,
      active: 0
    }
  },
  created() {

  },
  methods: {

    nextFunction() {
      if (this.active++ > 2) this.active = 0
    },
    checkPhoneIsExist() {
      const tmp = '{"phoneNumber":"' + this.user_phone_number + '"}'
      console.log(tmp)
      const formData = new FormData()
      formData.append('phoneNumber', this.user_phone_number)
      checkPhoneIsExist(formData).then((response) => {
        console.log(response.data)
      })
    },
    getRegisterVerifyCode() {
      let code = ''
      for (let i = 0; i < 6; i++) {
        const random = Math.floor(Math.random() * 10)
        code += random
      }
      this.user_verify_code_system = code
      console.log(code)
    },
    childByValue: function (childValue) {
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
    countDown() {
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
    will_register: function () {
      if (this.user_name === '' || this.user_verify_code_user === '' || this.user_phone_number === '' || this.user_password === '') {
        this.$message.error('请保证信息完整哦~')
        return false
      }
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
      if (this.user_verify_code_system !== this.user_verify_code_user) {
        this.$message.error('错了哦，验证码不对哦~')
        return
      }
      this.register_method()
    },
    register_method: function () {
      const userData = {
        'username': this.user_name,
        'phoneNumber': this.user_phone_number,
        'user_password': this.user_password
      }
      userRegister(userData).then((response) => {
        console.log(response.data)
        Message.success('注册成功，请登录')
        this.$router.push({path: 'http://localhost:10000/#/login'})
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
