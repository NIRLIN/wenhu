<template>
  <div id="Review" style="background-color: #ffffff;margin-top: 10px;margin-bottom: 10px;">
    <el-row>
      <el-col :span="1">
        <el-link :href="'#/people/'+review.replierId" :underline="false">
          <el-avatar :size="30" :src="review.replierHeadImage" shape="square" />
        </el-link>
      </el-col>
      <el-col :span="9">
        <div class="grid-content" style="min-width: 100px;">
          <div>
            <el-link :href="'#/people/'+review.replierId" :underline="false">
              <span style="font-size: 15px;">{{ review.replierUsername }}</span>
            </el-link>
            <span style="font-size: 15px;"> 回复 </span>
            <el-link :href="'#/people/'+review.byReplierId" :underline="false">
              <span style="font-size: 15px;">{{ review.byReplierIdUsername }}</span>
            </el-link>
          </div>
        </div>
      </el-col>
      <el-col :span="6" :offset="16">
        <div style="margin-bottom: 10px;margin-top: -10px;">
          <el-tooltip class="item" :content="review.replyTime | formatTimer(true)" effect="dark" placement="top">
            <span style="font-size: 14px;color: #8590a6;">{{ review.replyTime | formatTimer }}</span>
          </el-tooltip>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="22" :offset="1">
        <div class="grid-content">
          <span>{{ review.content }}</span>
        </div>

      </el-col>
    </el-row>
    <el-row>
      <el-col :span="22" :offset="1">
        <div class="grid-content">
          <el-button type="text" style="color: #8590a6;font-size: 12px;" @click="reviewInputShowButton">
            <svg class="Zi Zi--Reply" fill="currentColor" viewBox="0 0 24 24" width="12" height="12" style="margin-right: 5px;"><path d="M22.959 17.22c-1.686-3.552-5.128-8.062-11.636-8.65-.539-.053-1.376-.436-1.376-1.561V4.678c0-.521-.635-.915-1.116-.521L1.469 10.67a1.506 1.506 0 0 0-.1 2.08s6.99 6.818 7.443 7.114c.453.295 1.136.124 1.135-.501V17a1.525 1.525 0 0 1 1.532-1.466c1.186-.139 7.597-.077 10.33 2.396 0 0 .396.257.536.257.892 0 .614-.967.614-.967z" fill-rule="evenodd" /></svg>
            回复</el-button>
          <el-button type="text" style="color: #8590a6;font-size: 12px;" icon="el-icon-s-flag">举报</el-button>
        </div>
      </el-col>
    </el-row>
    <el-row v-show="review.reviewInputShow">
      <el-col :span="20" :offset="1">
        <div class="grid-content">
          <el-input
            v-model="review.reviewContent"
            :placeholder="review.reviewPlaceholder"
            clearable
          />
        </div>
      </el-col>
      <el-col :span="2" :offset="1">
        <div class="grid-content">
          <el-button type="primary" @click="saveReplyMethod">发布</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import { Message } from 'element-ui'
import { getCookie } from '@/utils/login-status'

export default {
  name: 'ReviewReviewItem',
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
    reply_item: Object
  },
  data() {
    return {
      review: {
        byReplierId: this.reply_item.byReplierId,
        byReplierIdUsername: this.reply_item.byReplierIdUsername,
        byReplierIdHeadImage: this.reply_item.byReplierIdHeadImage,
        replierId: this.reply_item.replierId,
        replierUsername: this.reply_item.replierUsername,
        replierHeadImage: this.reply_item.replierHeadImage,
        parentReviewId: this.reply_item.parentReviewId,
        replyTime: this.reply_item.updateTime,
        content: this.reply_item.replyContent,
        reviewInputShow: false,
        reviewContent: '',
        reviewPlaceholder: ''
      }
    }
  },

  methods: {
    reviewInputShowButton() {
      this.review.reviewInputShow = !this.review.reviewInputShow
      this.review.reviewPlaceholder = '回复' + this.review.replierUsername
    },
    saveReplyMethod() {
      if (this.review.review_id === '' || this.review.reviewContent === '') {
        Message.error({
          message: '评论为空哦~',
          center: true
        })
        return
      }
      if (getCookie() !== undefined) {
        // id为parentReviewId，父评论id
        const submitData = { 'id': this.review.parentReviewId, 'userId': getCookie(), 'byReplierId': this.review.replierId, 'replyContent': this.review.reviewContent }
        this.$emit('submitDataMethod', submitData)
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
  border-radius: 4px;
  min-height: 16px;
}
</style>
