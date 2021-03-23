<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="24"><div class="grid-content">
          <el-input
            v-model="question_topic"
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
import { saveQuestion } from '@/api/quiz'
export default {
  name: 'Quiz',
  components: {
    MavonEditor
  },
  data: function() {
    return {
      question_topic: '',
      question_description: ''
    }
  },
  methods: {
    getMsgFromChild: function(listenToChildEvent) {
      // listenToChildEvent就是子组件传过来的值
      this.question_description = listenToChildEvent
      console.log(this.question_description)
    },
    sendQuiz() {
      if (this.question_topic === '' || this.question_description === '') {
        this.$message.error('错了哦，填写信息不完整')
        return
      }
      const submitData = { 'topic': this.question_topic, 'description': this.question_description }
      console.log(submitData)
      saveQuestion(submitData).then((response) => {
        console.log(response.data)
      }).catch(() => {
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
