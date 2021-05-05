<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="24"><div class="grid-content">
          <el-input
            v-model="question_title"
            placeholder="请输入问题"
            clearable
          >
            <el-select slot="prepend" v-model="question_select_id" style="width: 80px;" placeholder="分类">
              <el-option v-for="(item,index) in listClassify" :key="index" :label="item.classifyName" :value="item.id" />
            </el-select>
          </el-input>
        </div></el-col>
      </el-row>
      <el-row>
        <el-col :span="24"><div class="grid-content">
          <Markdown @listenToChildEvent="getMsgFromChild" />
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
import Markdown from '@/components/Markdown'
import { saveQuestion, listClassify } from '@/api/creation'
import { getCookie } from '@/utils/login-status'
import { Message } from 'element-ui'
export default {
  name: 'Quiz',
  components: {
    Markdown
  },
  data: function() {
    return {
      question_id: '',
      question_title: '',
      question_description: '',
      question_select_id: '',
      listClassify: []
    }
  },
  watch: {
    question_id() {
      console.log(this.question_id)
      this.$router.push('question/' + this.question_id)
    }
  },
  created() {
    this.getClassifyMethod()
  },
  methods: {
    getMsgFromChild: function(listenToChildEvent) {
      // listenToChildEvent就是子组件传过来的值
      this.question_description = listenToChildEvent
      // console.log(this.question_description)
    },
    sendQuiz() {
      if (getCookie() === undefined) {
        this.$message.error({
          message: '未登录，请登录重试',
          center: true
        })
        return
      }
      if (this.question_select_id === '') {
        this.$message.error({
          message: '请选择分类',
          center: true
        })
        return
      }
      if (this.question_title === '' || this.question_description === '') {
        this.$message.error({
          message: '错了哦，填写信息不完整',
          center: true
        })
        return
      }
      const submitData = { 'classifyId': this.question_select_id, 'title': this.question_title, 'description': this.question_description, 'menderId': getCookie() }
      // console.log(submitData)
      saveQuestion(submitData).then((response) => {
        Message.success({
          message: '成功，正在跳转',
          center: true
        })
        this.question_id = JSON.parse(response.data).id
      })
    },
    getClassifyMethod() {
      listClassify().then((response) => {
        this.listClassify = response.data
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
