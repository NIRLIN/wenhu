<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="24"><div class="grid-content">
          <el-input
            v-model="question_title"
            placeholder="请输入问题"
            clearable
          />
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="24"><div class="grid-content">
          <MavonEditor @listenToChildEvent="getMsgFromChild" />
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="2" :offset="21"><div class="grid-content">
          <el-button type="primary" style="margin-right: 1px;" @click="sendQuiz">发布问题</el-button>
        </div></el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import MavonEditor from '@/components/Markdown'
import { saveQuestion } from '@/api/question'
import { getCookie } from '@/utils/login-status'
import { Message } from 'element-ui'
export default {
  name: 'Quiz',
  components: {
    MavonEditor
  },
  data: function() {
    return {
      question_id: '',
      question_title: '',
      question_description: ''
    }
  },
  watch: {
    question_id() {
      console.log(this.question_id)
      this.$router.push('question/' + this.question_id)
    }
  },
  methods: {
    getMsgFromChild: function(listenToChildEvent) {
      // listenToChildEvent就是子组件传过来的值
      this.question_description = listenToChildEvent
      // console.log(this.question_description)
    },
    sendQuiz() {
      if (getCookie() === undefined) {
        Message.error({
          message: '未登录，请登录重试',
          center: true
        })
        return
      }
      if (this.question_title === '' || this.question_description === '') {
        this.$message.error('错了哦，填写信息不完整')
        return
      }
      const submitData = { 'title': this.question_title, 'description': this.question_description, 'mender_id': getCookie() }
      // console.log(submitData)
      saveQuestion(submitData).then((response) => {
        Message.success({
          message: '成功，正在跳转',
          center: true
        })
        this.question_id = JSON.parse(response.data).id
      })
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  margin-top: 10px;
}
</style>
