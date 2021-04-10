<template>
  <div v-if="answer_item" class="class_margin">
    <el-row>
      <el-col :span="22">
        <el-link :href="'#/people/'+answer.userId" :underline="false">
          <div class="grid-content" style="min-width: 100px;">
            <div style="width: 50px; float:left">
              <el-avatar :size="40" :src="answer.head_image" shape="square" />
            </div>
            <div style="float: left;">
              <div>
                <el-link :underline="false">
                  <span class="answer_font_name">{{ answer.username }}</span>
                </el-link>
              </div>
              <div>
                <span class="answer_font_resume">{{ answer.resume }}</span>
              </div>
            </div>
          </div>
        </el-link>

      </el-col>

    </el-row>
    <el-row class="answer_margin">
      <el-col :span="24">
        <div class="grid-content">
          <div>
            <span class="answer_content_font" v-html="answer.content" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="answer_margin">
      <el-col :span="24">
        <div class="grid-content">
          <div class="answer_edit_time">
            <el-tooltip class="item" :content="answer.updateTime | formatTimer(true)" effect="dark" placement="top">
              <span>发布于 {{ answer.updateTime | formatTimer }}</span>
            </el-tooltip>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="answer_margin">

      <el-col :span="24">
        <div class="grid-content ">
          <el-button v-if="agreeButtonBool" size="small" type="primary" @click="agreeAnswerButton"><i class=" el-icon-caret-top icon_size" />已赞同 {{ answer.approvalNumber }}</el-button>
          <el-button v-if="!agreeButtonBool" plain size="small" type="primary" @click="agreeAnswerButton"><i class=" el-icon-caret-top icon_size" />赞同 {{ answer.approvalNumber }}</el-button>
          <el-button plain size="small" type="primary" @click="opposeButton"><i class=" el-icon-caret-bottom icon_size" /></el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text" @click="showReviewMethod"><i class="el-icon-s-comment icon_size" />{{ reviewButtonValue }}</el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text" @click="shareButton"><i class="el-icon-s-promotion icon_size" />分享</el-button>
          <el-button v-if="collectButtonBool" class="no_border_outline button_margin_left button_color" type="text" @click="collectAnswerButton"><i class="el-icon-star-on icon_size" style="font-size: 18px;" />收藏</el-button>
          <el-button v-if="!collectButtonBool" class="no_border_outline button_margin_left button_color" type="text" @click="collectAnswerButton"><i class="el-icon-star-off icon_size" style="font-size: 18px;" />收藏</el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text">
            <el-dropdown trigger="click" :placement="'bottom'">
              <span class="el-dropdown-link">
                <i class="el-icon-more question_more button_color " />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>举报</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-button>
        </div>
      </el-col>
    </el-row>
    <el-row v-show="reviewButtonBool" class="answer_margin">
      <el-col :span="24">
        <div class="grid-content ">
          <Review ref="reviewValue" class="review-height" />
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getCookie } from '@/utils/login-status'
import { userOpposeAnswer, userCollectAnswer, userAgreeAnswer, getUserAgreeAndCollectAnswer } from '@/api/answer'
import { Message } from 'element-ui'
import Review from '@/components/Review'

export default {
  name: 'Answer',
  components: { Review },
  filters: {
    formatTimer: function(value, hours) {
      const date = new Date(value)
      const y = date.getFullYear()
      let M = date.getMonth() + 1
      M = M < 10 ? '0' + M : M
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      let h = date.getHours()
      let m = date.getMinutes()
      h = h >= 10 ? h : '0' + h
      m = m >= 10 ? m : '0' + m
      if (hours) {
        return y + '-' + M + '-' + d + ' ' + h + ':' + m
      } else {
        return y + '-' + M + '-' + d
      }
    }
  },
  props: {
    // eslint-disable-next-line vue/prop-name-casing,vue/require-default-prop
    answer_item: Object
  },
  data() {
    return {
      agreeButtonBool: false,
      collectButtonBool: false,
      reviewButtonBool: false,
      reviewButtonValue: ' 评论',
      answer: {
        id: this.answer_item.id,
        head_image: this.answer_item.headImage,
        username: this.answer_item.username,
        resume: this.answer_item.resume,
        content: this.answer_item.content,
        userId: this.answer_item.userId,
        updateTime: this.answer_item.updateTime,
        approvalNumber: this.answer_item.approvalNumber,
        questionId: this.answer_item.questionId
      }
    }
  },
  watch: {
    answer_item() {
      this.answer.id = this.answer_item.id
      this.answer.head_image = this.answer_item.headImage
      this.answer.username = this.answer_item.username
      this.answer.resume = this.answer_item.resume
      this.answer.content = this.answer_item.content
      this.answer.userId = this.answer_item.userId
      this.answer.updateTime = this.answer_item.updateTime
      this.answer.approvalNumber = this.answer_item.approvalNumber
      this.answer.questionId = this.answer_item.questionId
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.answer.id }
        // console.log(this.answer_item)
        getUserAgreeAndCollectAnswer(submitData).then((response) => {
          this.agreeButtonBool = response.data.boolAgree
          this.collectButtonBool = response.data.boolCollect
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    }
  },
  mounted() {
    if (getCookie() !== undefined) {
      const submitData = { 'userId': getCookie(), 'answerId': this.answer.id }
      // console.log(this.answer_item)
      getUserAgreeAndCollectAnswer(submitData).then((response) => {
        this.agreeButtonBool = response.data.boolAgree
        this.collectButtonBool = response.data.boolCollect
      })
    } else {
      Message.success({
        message: '请登录哦~',
        center: true
      })
    }
  },
  methods: {
    agreeAnswerButton() {
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.answer.id }
        userAgreeAnswer(submitData).then((response) => {
          // console.log(response.data)
          this.agreeButtonBool = response.data.agreeBool
          this.answer.approvalNumber = response.data.approval_number
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    },
    opposeButton() {
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.answer.id }
        // 需修改
        userOpposeAnswer(submitData).then((response) => {
          this.agreeButtonBool = response.data.agreeBool
          this.answer.approvalNumber = response.data.approval_number
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    },
    shareButton() {
      const aux = document.createElement('input')
      const href = window.location.href
      const hrefPrefix = href.split('#')[0]

      aux.setAttribute('value', '  闻乎 - ' + hrefPrefix + '#/answer/' + this.answer.id)
      document.body.appendChild(aux)
      aux.select()
      document.execCommand('copy')
      document.body.removeChild(aux)
      Message.info({
        message: '已复制链接',
        center: true
      })
    },
    collectAnswerButton() {
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.answer.id }
        userCollectAnswer(submitData).then((response) => {
          // console.log(response.data)
          this.collectButtonBool = response.data.collectResult
          Message.success({
            message: '操作成功',
            center: true
          })
        })
      } else {
        Message.error({
          message: '未登录，请登录重试',
          center: true
        })
      }
    },
    showReviewMethod() {
      this.reviewButtonBool = !this.reviewButtonBool
      if (this.reviewButtonBool) {
        this.reviewButtonValue = ' 收起评论'
        this.$refs.reviewValue.getListReviewMethod(this.answer.id)
      } else {
        this.reviewButtonValue = ' 评论'
      }
    }
  }
}
</script>

<style scoped>
.class_margin {
  margin-left: 8px;
  margin-right: 8px;
}

.grid-content {
  border-radius: 10px;
  min-height: 16px;
}

.answer_font_name {
  font-size: 15px;
  font-weight: bold;
  color: #444444;
}

.answer_font_resume {
  font-size: 14px;
  color: #333333;
}

.answer_edit_time {
  color: #8590a6;
  font-size: 14px;
}

.answer_content_font {
  font-size: 15px;
  color: #121212;
}

.no_border_outline {
  outline: none;
  border: none;
}
.button_color{
  color: #8590a6;
}
.answer_margin {
  margin-top: 5px;
}
.button_margin_left{
  margin-left: 30px;
}
.icon_size{
  font-size: 15px;
}
.review-height{
  max-height: 800px;
  overflow-y: auto;
}
</style>
