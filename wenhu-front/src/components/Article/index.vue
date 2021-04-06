<template>
  <div v-if="answer_item" class="class_margin">
    <el-row>
      <el-col :span="22">
        <div class="grid-content">
          <div style="width: 50px; float:left">
            <el-link :underline="false">
              <el-avatar :size="40" :src="article_head_image" shape="square" />
            </el-link>
          </div>
          <div style="float: left;">
            <div>
              <el-link :underline="false">
                <span class="article_font_name">{{ article_username }}</span>
              </el-link>
            </div>
            <div>
              <span class="article_font_resume">{{ article_resume }}</span>
            </div>
          </div>
        </div>
      </el-col>

    </el-row>
    <el-row class="article_margin">
      <el-col :span="24">
        <div class="grid-content">
          <div>
            <span class="article_content_font" v-html="article_content" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="article_margin">
      <el-col :span="24">
        <div class="grid-content">
          <div class="article_edit_time">
            <el-tooltip class="item" :content="article_updateTime | formatTimer(true)" effect="dark" placement="top">
              <span>发布于 {{ article_updateTime | formatTimer }}</span>
            </el-tooltip>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="article_margin">

      <el-col :span="24">
        <div class="grid-content ">
          <el-button v-if="agreeArticleButtonBool" size="small" type="primary" @click="agreeArticleButton"><i class=" el-icon-caret-top icon_size" />赞同 {{ article_approvalNumber }}</el-button>
          <el-button v-if="!agreeArticleButtonBool" plain size="small" type="primary" @click="agreeArticleButton"><i class=" el-icon-caret-top icon_size" />赞同 {{ article_approvalNumber }}</el-button>
          <el-button plain size="small" type="primary" @click="opposeArticleButton"><i class=" el-icon-caret-bottom icon_size" /></el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text"><i class="el-icon-s-comment icon_size" />评论</el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text"><i class="el-icon-s-promotion icon_size" />分享</el-button>
          <el-button v-if="collectArticleButtonBool" class="no_border_outline button_margin_left button_color" type="text" @click="collectArticleButton"><i class="el-icon-star-on icon_size" style="font-size: 18px;" />收藏</el-button>
          <el-button v-if="!collectArticleButtonBool" class="no_border_outline button_margin_left button_color" type="text" @click="collectArticleButton"><i class="el-icon-star-off icon_size" style="font-size: 18px;" />收藏</el-button>
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
  </div>
</template>

<script>
import { getCookie } from '@/utils/login-status'
import { userOpposeArticle, userAgreeArticle, getUserAgreeArticle, userCollectArticle, getUserCollectArticle } from '@/api/answer'
import { Message } from 'element-ui'

export default {
  name: 'Article',
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
      agreeArticleButtonBool: false,
      collectArticleButtonBool: false,
      article_id: this.answer_item.id,
      article_head_image: this.answer_item.headImage,
      article_username: this.answer_item.username,
      article_resume: this.answer_item.resume,
      article_content: this.answer_item.content,
      article_userId: this.answer_item.userId,
      article_updateTime: this.answer_item.updateTime,
      article_approvalNumber: this.answer_item.approvalNumber
    }
  },
  beforeMount() {
    if (this.answer_item !== undefined) {
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.article_id }
        getUserAgreeArticle(submitData).then((response) => {
          this.agreeArticleButtonBool = response.data
        })
        getUserCollectArticle(submitData).then((response) => {
          console.log(response.data.collectResult)
          this.collectArticleButtonBool = response.data.collectResult
        })
      }
    }
  },
  methods: {
    agreeArticleButton() {
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.article_id }
        userAgreeArticle(submitData).then((response) => {
          this.agreeArticleButtonBool = response.data.agreeBool
          this.article_approvalNumber = response.data.approval_number
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    },
    opposeArticleButton() {
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.article_id }
        userOpposeArticle(submitData).then((response) => {
          this.agreeArticleButtonBool = response.data.agreeBool
          this.article_approvalNumber = response.data.approval_number
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    },
    collectArticleButton() {
      if (getCookie() !== undefined) {
        const submitData = { 'userId': getCookie(), 'answerId': this.article_id }
        userCollectArticle(submitData).then((response) => {
          // console.log(response.data)
          this.collectArticleButtonBool = response.data.collectResult
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
  min-height: 20px;
}

.article_font_name {
  font-size: 15px;
  font-weight: bold;
  color: #444444;
}

.article_font_resume {
  font-size: 14px;
  color: #333333;
}

.article_edit_time {
  color: #8590a6;
  font-size: 14px;
}

.article_content_font {
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
.article_margin {
  margin-top: 5px;
}
.button_margin_left{
  margin-left: 30px;
}
.icon_size{
  font-size: 15px;
}
</style>
