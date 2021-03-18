<template>
  <div id="login">
    <el-row class="login_min_width">
      <el-col :span="7">
        <div class="grid-content"/>
      </el-col>
      <el-col :span="10">
        <div class="grid-content">
          <el-card class="login_card">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="免密码登录" name="first">
                <el-row>
                  <el-col :span="24">
                    <div class="grid-content  input_margin">
                      <el-input class="input-with-select " placeholder="请输入手机号">
                        <template slot="prepend">中国大陆</template>
                      </el-input>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div v-if="!user_is_human" class="grid-content input_margin">
                      <SlideVerify @childByValue="childByValue"/>
                    </div>
                    <div v-if="user_is_human" class="grid-content input_margin">
                      <el-input v-model="user_verify_code_user" class="input-with-select " maxlength="6"
                                placeholder="请输入短信验证码">
                        <el-button v-if="getCodeButtonNumber===60" slot="append" type="primary" @click="timeMinus">{{
                          getCodeButtonValue }}
                        </el-button>
                        <el-button v-if="getCodeButtonNumber!==60" slot="append" :disabled="true">{{ getCodeButtonValue
                          }}
                        </el-button>
                      </el-input>
                    </div>
                  </el-col>
                </el-row>

                <el-row>
                  <el-col :span="24">
                    <div class="grid-content input_margin">
                      <el-button class="enroll_button " type="primary">登录</el-button>

                    </div>
                  </el-col>
                </el-row>
                <el-row/>
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
                      <el-input v-model="user_password" :show-password="true" placeholder="请输入密码" type="password"/>
                    </div>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="8">
                    <div class="grid-content "/>
                  </el-col>
                  <el-col :span="8">
                    <div class="grid-content "/>
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
                      <el-button class="enroll_button " type="primary" @click="passwordLogin">登录</el-button>

                    </div>
                  </el-col>
                </el-row>
                <el-row/>
              </el-tab-pane>
            </el-tabs>

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
import {Message} from 'element-ui'
import {loginByPassword, loginByVerify, userRegister} from '@/api/password'
import SlideVerify from '@/components/SlideVerify'

export default {
  name: 'Login',
  components: {SlideVerify},
  data() {
    return {
      activeName: 'first',
      user_phone_number: '',
      user_phone_code: '',
      user_password: '',
      getCodeButtonNumber: 60,
      getCodeButtonValue: '点击获取',
      user_verify_code_system: '',
      user_verify_code_user: '',
      user_is_human: 0

    }
  },
  methods: {
    childByValue: function (childValue) {
      // childValue就是子组件传过来的值
      this.user_is_human = childValue
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    timeMinus: function () {
      if (this.user_phone_number === '') {
        this.$message.error('错了哦，请输入手机号')
        return
      }
      if (this.getCodeButtonNumber === 60 && this.startTime === 0) {
        this.startTime = Date.now()
      }
      const nowTime = Date.now()
      this.getCodeButtonNumber = 60 - Math.round((nowTime - this.startTime) / 1000)
      this.getCodeButtonValue = this.getCodeButtonNumber + '秒后重试'

      if (Math.round((nowTime - this.startTime) / 1000) >= 60) {
        this.getCodeButtonValue = '点击获取'
        this.getCodeButtonNumber = 60
      } else {
        setTimeout(this.timeMinus, 1000)
      }
    },
    verifyLogin() {
      if (this.user_phone_number === '' || this.user_password === '') {
        this.$message.error('请保证信息完整哦~')
        return
      }
      const FloatRegexPhone = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (!FloatRegexPhone.test(this.user_phone_number)) {
        this.$message.error('错了哦，手机号错误~')
        return
      }
      if (this.user_verify_code_system !== this.user_verify_code_user) {
        this.$message.error('错了哦，验证码不对哦~')
        return
      }
      const formData = new FormData()
      formData.append('email', 'this.loginForm.email')
      formData.append('password', 'this.loginForm.password')

      loginByVerify(formData).then(function (response) {
        Message.success(response.data)
        return response.data
      })
          .catch(function (error) {
            console.log(error)
          })
    },
    passwordLogin() {
      if (this.user_phone_number === '' || this.user_password === '') {
        this.$message.error('请保证信息完整哦~')
        return
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
      const userData = {'phoneNumber': this.user_phone_number, 'password': this.user_password}
      console.log(userData)
      loginByPassword(userData).then((response) => {
        console.log(response.data)
        Message.success('成功，正在跳转')
        document.cookie = 'user_id=123456789'
        this.$router.push({
          path: '/hot',
          component: () => import('@/views/Home/Hot')
        })
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
