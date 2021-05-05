<template>
  <div id="questionAnswer">
    <el-row class="">
      <el-col :span="24">
        <div class="grid-content margin_nav_size">
          <Question :question_item="question_item" @listenToChildEvent="childSendEditAnswerStatus" />
        </div>
      </el-col>
    </el-row>
    <el-row class="answer_list_background no_question_div " style="margin-top: 5px;">
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
      <el-col v-if="answer_item" :span="22">
        <div class="grid-content ">
          <div class="">
            <div class="answer_list ">
              <div class="answer_item_div">
                <el-row>
                  <el-col>
                    <Markdown :value="answer_item.content" @listenToChildEvent="getMsgFromChild" />
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :offset="19" :span="3">
                    <el-link :underline="false" :href="'/#/answer/'+answer_item.id">
                      <el-button type="info" size="small" style="margin-top: 10px;">取消修改</el-button>
                    </el-link>
                  </el-col>
                  <el-col :span="2">
                    <el-button type="primary" size="small" style="margin-top: 10px;" @click="editAnswerMethod">保存修改</el-button>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>
        </div>

      </el-col>
      <el-col v-if="!answer_item" :span="6" :offset="7" class="no_question_div" style="margin-bottom: 10px;">
        <div>

          <div class="grid-content ">
            <div>
              <svg width="250" height="220" viewBox="-5 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
            </div>
            <div style="text-align: center;">
              <span class="no_question_span">不能修改别人的回答哦~</span>
            </div>

          </div>
        </div>

      </el-col>
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
    </el-row>

  </div>
</template>

<script>
import Question from '@/components/Question'

import { getAnswerByAnswerId, updateAnswer } from '@/api/creation'
// eslint-disable-next-line no-unused-vars
import { Message } from 'element-ui'
import Markdown from '@/components/Markdown'
import { getCookie } from '@/utils/login-status'

export default {
  name: 'OneAnswer',
  components: { Question, Markdown },
  data() {
    return {
      answer_number: 0,
      is_edit_answer: 0,
      edit_answer_item: '',
      content: '',
      question_item: {
        browseNumber: '',
        description: '',
        followNumber: '',
        id: '',
        title: '',
        userId: ''
      },
      answer_item: ''
    }
  },
  created() {
    const submitData = { 'answerId': this.$route.params.id }
    getAnswerByAnswerId(submitData).then((response) => {
      if (response.data.answer.userId === getCookie()) {
        this.answer_item = response.data.answer
        this.question_item = response.data.question
      }
    })
  },
  methods: {
    childSendEditAnswerStatus: function(listenToChildEvent) {
      // listenToChildEvent就是子组件传过来的值
      this.is_edit_answer = listenToChildEvent
      console.log(this.is_edit_answer)
      if (this.is_edit_answer) {
        this.$router.push('/question/' + this.question_item.id)
      }
    },
    getMsgFromChild: function(listenToChildEvent) {
      // listenToChildEvent就是子组件传过来的值
      // console.log(listenToChildEvent)
      this.content = listenToChildEvent
    },
    editAnswerMethod() {
      const submitData = { 'id': this.answer_item.id, 'content': this.content }
      console.log(submitData)
      updateAnswer(submitData).then((response) => {
        if (response.data === 1) {
          this.$message.success({
            message: '修改成功',
            center: true
          })
          setTimeout(() => {
            window.location.href = '/#/answer/' + this.answer_item.id
          }, 1100)
        } else {
          this.$message.error({
            message: '修改失败',
            center: true
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 8px;
  min-height: 36px;
}
.answer_item_div {
  margin-top: 10px;
  margin-bottom: 10px;
}
.button_margin_edit{
  margin-top: 15px;
  margin-bottom: 10px;
}
.answer_number {
  font-size: 15px;
  font-weight: bold;
  margin-top: 8px;
}
.answer_list_background{
  background-color: #ffffff;
}
.no_question_div{
  border-top: 1px solid rgb(231, 231, 231);
  min-height: 35px;
}
.no_question_span{
  color: rgb(133,144,166);
  font-size: 15px;
}
.answer_list {
  border-top: 1px solid rgb(231, 231, 231);
}
.button_font_color {
  color: #121212;
}
.question_margin_answer{
  margin-top: 8px;
  background-color: #ffffff;
}
</style>
