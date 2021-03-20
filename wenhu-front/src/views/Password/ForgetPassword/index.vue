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
                    忘记密码
                  </span>
                </div>
              </el-col>
            </el-row>
            <el-row>
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
            <el-row>
              <el-col :span="24">
                <div class="grid-content input_margin">
                  <el-input
                    v-model="user_verify_code_user"
                    class="input-with-select "
                    maxlength="6"
                    placeholder="请输入短信验证码"
                  >
                    <el-button v-if="getCodeButtonNumber===60" slot="append" type="primary" @click="timeMinus">{{
                      getCodeButtonValue }}
                    </el-button>
                    <el-button v-if="getCodeButtonNumber!==60" slot="append" :disabled="true">{{ getCodeButtonValue }}
                    </el-button>
                  </el-input>
                </div>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple">
                  <div class="register_back_gauge">
                    <el-button class="register_button" type="primary" @click="will_register">下一步</el-button>
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
import axios from 'axios'

export default {
  name: 'ForgetPassword',
  data() {
    return {
      user_phone_number: '',
      user_verify_code_user: '',
      user_verify_code_system: '',
      getCodeButtonNumber: 60,
      getCodeButtonValue: '点击获取',
      startTime: 0
    }
  },

  methods: {
    timeMinus: function() {
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

    will_register: function() {
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
    register_method: function() {
      // const people = require('@/api/password.js')
      const people = 'aaa'
      console.log('people.register=' + people.register)
      const register_json = axios.post(people.login, {
        'username': this.user_name,
        'email': this.user_email,
        'phoneNumber': this.user_phone_number,
        'password': this.user_password_one
      })
        .then(function(response) {
          return response.data
        })
        .catch(function(error) {
          console.log(error)
        })
      console.log('register_json=' + register_json)
      console.log('register_json=' + JSON.stringify(register_json))
      // eslint-disable-next-line no-unused-vars,no-empty
      for (const i in register_json) {
      }
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
