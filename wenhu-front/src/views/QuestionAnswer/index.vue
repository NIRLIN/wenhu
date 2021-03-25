<template>
  <div id="questionAnswer">
    <el-row class="">
      <el-col :span="24">
        <div class="grid-content margin_nav_size">
          <Question />
        </div>
      </el-col>
    </el-row>
    <el-row class="question_margin_answer">
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
      <el-col :span="3">
        <div class="grid-content ">
          <div class="">
            <p class="answer_number">
              {{ answer_number }}个回答
            </p>
          </div>
        </div>
      </el-col>
      <el-col :span="17">
        <div class="grid-content " />
      </el-col>
      <el-col :span="2">
        <div class="grid-content ">
          <el-button type="text" @click="setSortMethod">
            <span class="button_font_color">
              {{ sort_button_value }} <i class="el-icon-d-caret" />
            </span>
          </el-button>
        </div>
      </el-col>
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>

    </el-row>
    <el-row class="answer_list_background">
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
      <el-col :span="22">
        <div class="grid-content ">
          <div class="">
            <div v-for="item in items" :key="item" class="answer_list ">
              <div class="answer_item">
                <Answer>{{ item }}</Answer>
              </div>
            </div>
          </div>
        </div>

      </el-col>
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
    </el-row>
    <el-backtop />
  </div>
</template>

<script>
import Question from '@/components/Question'
import Answer from '@/components/Answer'
import { getAnswerListByQuestionId } from '@/api/question'
// eslint-disable-next-line no-unused-vars
import { Message } from 'element-ui'

export default {
  name: 'QuestionAnswer',
  components: { Answer, Question },
  data() {
    return {
      answer_number: 20,
      sort_button_value: '默认排序',
      items: ['a', 'b', 'c'],
      question_item: ''
    }
  },
  created() {
    const submitData = { 'id': this.$route.params.id }
    getAnswerListByQuestionId(submitData).then((response) => {
      this.question_item = response.data
    }).catch(() => {
    })
  },
  methods: {
    setSortMethod() {
      if (this.sort_button_value === '默认排序') {
        this.sort_button_value = '时间排序'
        return
      }
      if (this.sort_button_value === '时间排序') {
        this.sort_button_value = '默认排序'
        return
      }
    }
  }
}
</script>

<style scoped>

.grid-content {
  border-radius: 8px;
  min-height: 36px;
}
.answer_item {
  margin-top: 10px;
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

.answer_list {
  border-top: 1px solid rgb(231, 231, 231);
}
.button_font_color {
  color: #121212;
}
.question_margin_answer{
  margin-top: 5px;
  background-color: #ffffff;
}
</style>
