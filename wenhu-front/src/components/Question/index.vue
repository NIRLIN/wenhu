<template>
  <div id="question">
    <el-row>
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
      <el-col :span="20">
        <div class="grid-content ">
          <div>
            <h1 class="question_title"> {{ question_item.title }} </h1>
          </div>
        </div>
      </el-col>
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
      <el-col :span="1">
        <div class="grid-content ">
          <div class=" follow_browsing">
            <el-link :underline="false">
              <span class="follow_number_font">
                关注者
              </span>
              <br>
              <span class="follow_number_count">
                {{ question_item.followNumber }}
              </span>
            </el-link>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
      <el-col :span="18">
        <div class="grid-content ">
          <div>
            <span class="question_description" v-html="question_item.description">jj </span>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="question_function">

      <el-col :span="1">
        <div class="grid-content " />
      </el-col>
      <el-col :span="18">
        <div class="grid-content  ">
          <el-button size="small" :type="question_is_follow===0? 'primary':'info'" @click="followQuestionMethod">
            {{ question_is_follow === 0 ? '关注问题' : '取消关注' }}
          </el-button>
          <el-button size="small" icon="el-icon-edit" plain type="primary" @click="childSendEditAnswerStatus">写回答
          </el-button>
          <el-button
            class="no_border_outline button_color button_margin_left"
            type="text"
            icon="el-icon-s-promotion "
            @click="shareButton"
          >分享
          </el-button>
          <el-button class="no_border_outline button_color button_margin_left" type="text">
            <el-dropdown trigger="click" :placement="'bottom'">
              <span class="el-dropdown-link">
                <i class="el-icon-more question_more button_color " />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-link :underline="false" :href="'/#/questionLog/'+question_item.id">
                  <el-dropdown-item>问题日志</el-dropdown-item>
                </el-link>
              </el-dropdown-menu>
            </el-dropdown>
          </el-button>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { getCookie } from '@/utils/login-status'
import { followQuestion, userFollowQuestion } from '@/api/creation'

export default {
  name: 'Question',
  props: {
    // eslint-disable-next-line vue/prop-name-casing,vue/require-default-prop
    question_item: Object
  },
  data() {
    return {
      question_name: '这里是问题',
      question_description: '这里是描述；这里是描述；这里是描述；这里是描述；这里是描述；这里是描述；这里是描述；',
      question_follow_number: 666,
      childSendEditAnswerStatusNumber: 0,
      question_is_follow: 1
    }
  },
  watch: {
    question_item() {
      this.userFollowQuestionMethod()
    }
  },
  methods: {
    userFollowQuestionMethod() {
      const submitData = { 'userId': getCookie(), 'questionId': this.question_item.id }
      // console.log(this.question_item)
      // console.log(this.question_item.id)
      userFollowQuestion(submitData).then((response) => {
        this.question_is_follow = response.data
        // console.log(response.data)
      })
    },
    followQuestionMethod() {
      const submitData = { 'userId': getCookie(), 'questionId': this.question_item.id }
      followQuestion(submitData).then((response) => {
        this.question_is_follow = response.data
        // console.log(response.data)
      })
    },
    childSendEditAnswerStatus() {
      this.childSendEditAnswerStatusNumber = !this.childSendEditAnswerStatusNumber
      this.$emit('listenToChildEvent', this.childSendEditAnswerStatusNumber)
    },
    shareButton() {
      const aux = document.createElement('input')
      aux.setAttribute('value', this.question_item.title + '  -  闻乎  ' + window.location.href)
      document.body.appendChild(aux)
      aux.select()
      document.execCommand('copy')
      document.body.removeChild(aux)
      Message.info({
        message: '已复制链接',
        center: true
      })
    }
  }

}
</script>

<style scoped>
#question {
  background: #ffffff;
}

.grid-content {
  border-radius: 10px;
  min-height: 16px;
}

.question_title {
  margin-bottom: 0;
}

.follow_number_font {
  font-size: 14px;
  color: #8590a6;
}

.follow_number_count {
  font-size: 18px;
  font-weight: bold;
  color: #121212;
  text-align: center;
}

.div_divider_left {
  border-left: solid 1px #8590a6;
}

.question_function {
  margin-top: 1%;
  height: 50px;
}
.question_description{
  margin-top: 0;
}
.button_margin_left{
  margin-left: 30px;
}
.button_color{
  color: #8590a6;
}
.no_border_outline {
  outline: none;
  border: none;
}

.follow_browsing {
  margin-top: 10px;
  text-align: center;
}
</style>
