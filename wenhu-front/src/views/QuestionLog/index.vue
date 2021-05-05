<template>
  <div id="questionLog">
    <el-card>
      <el-row>
        <el-col :offset="3" :span="16">
          <div class="grid-content ">
            <el-link :underline="false" :href="'/#/question/'+question_item.id">
              <span style="color: rgb(0,102,255);font-size: 18px;font-weight: bold">
                {{ question_item.title }}
              </span>
            </el-link>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content ">
            <el-button v-if="!editQuestion" type="primary" size="small" @click="editQuestionMethod">修改问题</el-button>
          </div>
        </el-col>
      </el-row>
      <el-row v-if="editQuestion">
        <el-col :offset="3" :span="16">
          <div class="grid-content ">
            <el-row>
              <el-col :span="24"><div class="grid-content">
                <el-input
                  v-model="title"
                  clearable
                >
                  <el-select slot="prepend" v-model="classifyId" style="width: 80px;" placeholder="分类">
                    <el-option v-for="(item,index) in listClassify" :key="index" :label="item.classifyName" :value="item.id" />
                  </el-select>
                </el-input>
              </div></el-col>
            </el-row>
            <el-row>
              <el-col :span="24"><div class="grid-content" style="margin-top: 10px;">
                <Markdown :value="description" @listenToChildEvent="getMsgFromChild" />
              </div></el-col>
            </el-row>
            <el-row>
              <el-col :span="9" :offset="15"><div class="grid-content" style="float:right;">
                <el-button type="info" size="small" style="margin-top: 10px;" @click="editQuestion=false">取消修改</el-button>
                <el-button type="primary" size="small" style="margin-top: 10px;" @click="updateQuestionMethod">保存修改</el-button>
              </div></el-col>
            </el-row>
          </div>
        </el-col>
      </el-row>
      <el-row v-if="!editQuestion">
        <el-col :offset="3" :span="15">
          <div class="grid-content ">
            <div v-for="(item,index) in listQuestionLog" :key="index" style="border-top: 1px solid rgb(231, 231, 231);margin-top: 10px;">
              <questionLogItem :question_log="item" style="margin-top: 20px;" />
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import questionLogItem from './questionLogItem'
import { getQuestionById, listClassify, listQuestionLog, updateQuestion } from '@/api/creation'
import { getCookie } from '@/utils/login-status'
import Markdown from '@/components/Markdown'

export default {
  name: 'QuestionLog',
  components: {
    Markdown,
    questionLogItem
  },
  data() {
    return {
      question_item: {},
      listQuestionLog: [],
      editQuestion: false,
      listClassify: [],
      title: '',
      description: '',
      classifyId: ''
    }
  },
  created() {
    const submitData = { 'id': this.$route.params.id }
    getQuestionById(submitData).then((response) => {
      this.question_item = response.data
      this.title = this.question_item.title
      this.description = this.question_item.description
    })
    this.listQuestionLogMethod()
  },
  methods: {
    listQuestionLogMethod() {
      const submitData = { 'id': this.$route.params.id }
      listQuestionLog(submitData).then((response) => {
        console.log(response.data)
        this.listQuestionLog = response.data
      })
    },
    getMsgFromChild: function(listenToChildEvent) {
      // listenToChildEvent就是子组件传过来的值
      // console.log(listenToChildEvent)
      this.description = listenToChildEvent
    },
    editQuestionMethod() {
      this.editQuestion = true
      if (this.listClassify.length === 0) {
        this.getClassifyMethod()
      }
    },
    getClassifyMethod() {
      listClassify().then((response) => {
        this.listClassify = response.data
      })
    },
    updateQuestionMethod() {
      this.editQuestion = false
      const submitData = { 'id': this.question_item.id, 'userId': getCookie(), 'title': this.title, 'description': this.description }
      updateQuestion(submitData).then((response) => {
        this.listQuestionLogMethod()
      })
    }
  }
}
</script>

<style scoped>

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
