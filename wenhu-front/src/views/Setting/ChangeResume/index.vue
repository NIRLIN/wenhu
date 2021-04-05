<template>
  <div>
    <el-row>
      <el-col :span="24"><div class="grid-content ">
        <h3>签名管理</h3>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-input
            v-model="resume"
            type="textarea"
            :rows="2"
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
import { getResumeByUserId, saveResumeByUserId } from '@/api/people'
import { Message } from 'element-ui'

export default {
  name: 'ChangeResume',
  props: {
    // eslint-disable-next-line vue/require-default-prop,vue/prop-name-casing
    active_name: String
  },
  data() {
    return {
      resume: ''
    }
  },
  watch: {
    active_name() {
      if (this.active_name === 'resume') {
        const submitData = { 'userId': getCookie() }
        getResumeByUserId(submitData).then((response) => {
          this.resume = response.data
        })
      }
    }

  },
  methods: {
    saveResume() {
      if (this.resume === '') {
        Message.error({
          message: '签名未不可为空哦~',
          center: true
        })
        return
      }
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'resume': this.resume }
        saveResumeByUserId(submitData).then((response) => {
          if (response.data !== null) {
            this.homepage = response.data
            Message.success({
              message: '修改成功',
              center: true
            })
          }
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
</script>

<style scoped>
.grid-content {
  min-height: 20px;
  margin-top: 10px;
}

</style>
