<template>
  <div>
    <el-row>
      <el-col :span="24"><div class="grid-content ">
        <h3>账号管理</h3>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <div class="grid-content ">
          <el-steps :active="active" finish-status="success" :align-center="true">
            <el-step title="请输入旧手机号" />
            <el-step title="请输入新手机号" />
            <el-step title="修改完成" />
          </el-steps>
        </div>
      </el-col>
    </el-row>
    <el-row v-if="active===0">
      <el-col :span="12" :offset="6"><div class="grid-content ">
        <el-input
          v-model="phone_number"
          class="input-with-select "
          placeholder="请输入旧手机号"
        >
          <template slot="prepend">中国大陆</template>
        </el-input>
      </div></el-col>
    </el-row>
    <el-row v-if="active===0">
      <el-col :span="12" :offset="6"><div class="grid-content ">
        <el-input
          v-model="verify_code_user"
          class="input-with-select "
          maxlength="6"
          placeholder="请输入短信验证码"
        >
          <el-button slot="append" class="button" @click="oldPhoneVerifyCode">
            {{ button_content }}
          </el-button>
        </el-input>
      </div></el-col>
    </el-row>

    <el-row v-if="active===1">
      <el-col :span="12" :offset="6"><div class="grid-content ">
        <el-input v-model="new_phone_number" class="input-with-select " placeholder="请输入新手机号">
          <template slot="prepend">中国大陆</template>
        </el-input>
      </div></el-col>
    </el-row>
    <el-row v-if="active===1">
      <el-col :span="12" :offset="6"><div class="grid-content ">
        <el-input
          v-model="new_verify_code_user"
          class="input-with-select "
          maxlength="6"
          placeholder="请输入短信验证码"
        >
          <el-button slot="append" class="button" @click="newPhoneVerifyCode">
            {{ new_button_content }}
          </el-button>
        </el-input>
      </div></el-col>
    </el-row>

    <el-row>
      <el-col :span="12" :offset="6"><div class="grid-content ">
        <el-button v-if="active===0" style="width: 100%;" type="primary" @click="checkFirstVerify">下一步</el-button>
        <el-button v-if="active===1" style="width: 100%;" type="primary" @click="checkSencoedVerify">确认修改</el-button>
      </div>
      </el-col>
    </el-row>

  </div>

</template>

<script>
import { checkOldPhoneNumber, checkNewPhoneNumber, changeNewPhoneNumber } from '@/api/people'

export default {
  name: 'ChangePhone',
  data() {
    return {
      phone_number: '18798189064',
      new_phone_number: '',
      getVerifyCodePhoneNumber: '',
      verify_code_user: '',
      new_verify_code_user: '',
      verify_code_system: '',
      verify_code_system_create_time: '',
      button_content: '获取验证码',
      new_button_content: '获取验证码',
      totalTime: 120,
      new_totalTime: 120,
      new_canClick: true, // 添加canClick
      canClick: true, // 添加canClick
      active: 0
    }
  },
  methods: {
    createSystemVerifyCode() {
      let code = ''
      for (let i = 0; i < 6; i++) {
        const random = Math.floor(Math.random() * 10)
        code += random
      }
      this.verify_code_system = code
      this.verify_code_system_create_time = Date.now() / 1000
      // console.log(this.verify_code_system)
      // console.log(this.verify_code_system_create_time)
    },
    checkFirstVerify() {
      if (this.phone_number === '' || this.verify_code_user === '') {
        this.$message.error('错了哦，请保证信息完整哦~')
        return
      }
      if (this.getVerifyCodePhoneNumber !== this.phone_number) {
        this.$message.error('错了哦，请勿修改手机号~')
        return
      }
      if (((Date.now() / 1000) - this.verify_code_system_create_time) > 60 * 15) {
        this.$message.error({
          message: '验证码时间过了哦',
          center: true
        })
      }
      this.active = 1
    },
    checkSencoedVerify() {
      if (this.phone_number === '' || this.verify_code_user === '') {
        this.$message.error('错了哦，请保证信息完整哦~')
        return
      }
      if (this.getVerifyCodePhoneNumber !== this.phone_number) {
        this.$message.error('错了哦，请勿修改手机号~')
        return
      }
      if (((Date.now() / 1000) - this.verify_code_system_create_time) > 60 * 15) {
        this.$message.error({
          message: '验证码时间过了哦',
          center: true
        })
      }
      const submitData = { 'id': this.$route.params.id, 'phoneNumber': this.new_phone_number }
      changeNewPhoneNumber(submitData).then((response) => {
        this.active = 3
      })
      this.active = 2
    },
    oldPhoneVerifyCode() {
      if (!this.verifyPhoneNumber()) {
        return
      }
      this.createSystemVerifyCode()
      const submitData = { 'id': this.$route.params.id, 'code': this.verify_code_system, 'phoneNumber': this.phone_number }
      checkOldPhoneNumber(submitData).then((response) => {
      })
      this.getVerifyCodePhoneNumber = this.phone_number
      if (!this.canClick) return // 改动的是这两行代码
      this.canClick = false
      this.button_content = this.totalTime + 's后重试'
      const clock = window.setInterval(() => {
        this.totalTime--
        this.button_content = this.totalTime + 's后重试'
        if (this.totalTime < 0) {
          window.clearInterval(clock)
          this.button_content = '重新发送'
          this.totalTime = 120
          this.canClick = true // 这里重新开启
        }
      }, 1000)
    },
    newPhoneVerifyCode() {
      if (this.phone_number === this.new_phone_number) {
        this.$message.error('错了哦，请修改旧手机号')
        return
      }
      if (!this.verifyPhoneNumber()) {
        return
      }
      this.createSystemVerifyCode()
      const submitData = { 'id': this.$route.params.id, 'code': this.verify_code_system, 'phoneNumber': this.new_phone_number }
      checkNewPhoneNumber(submitData).then((response) => {
      })
      this.getVerifyCodePhoneNumber = this.phone_number
      if (!this.new_canClick) return // 改动的是这两行代码
      this.new_canClick = false
      this.new_button_content = this.new_totalTime + 's后重试'
      const clock = window.setInterval(() => {
        this.new_totalTime--
        this.new_button_content = this.new_totalTime + 's后重试'
        if (this.new_totalTime < 0) {
          window.clearInterval(clock)
          this.new_button_content = '重新发送'
          this.new_totalTime = 120
          this.new_canClick = true // 这里重新开启
        }
      }, 1000)
    },
    verifyPhoneNumber() {
      if (this.phone_number === '') {
        this.$message.error('错了哦，请输入手机号')
        return false
      }
      const FloatRegexPhone = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
      if (!FloatRegexPhone.test(this.phone_number)) {
        this.$message.error('错了哦，手机号错误~')
        return false
      }
      return true
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
