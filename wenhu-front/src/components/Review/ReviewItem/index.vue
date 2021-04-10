<template>
  <div id="Review" style="background-color: #ffffff;margin-top: 10px;margin-bottom: 10px;">
    <el-row>
      <el-col :span="1">
        <el-link href="" :underline="false">
          <el-avatar :size="30" :src="review.headImage" shape="square" />
        </el-link>
      </el-col>
      <el-col :span="9">
        <el-link href="" :underline="false">
          <div class="grid-content" style="min-width: 100px;">
            <div>
              <el-link :underline="false" :href="'#/people/'+review.reviewer_id">
                <span style="font-size: 15px;">{{ review.username }}</span>
              </el-link>
            </div>
          </div>
        </el-link>
      </el-col>
      <el-col :span="6" :offset="16">
        <div style="margin-bottom: 10px;margin-top: -10px;">
          <el-tooltip class="item" :content="review.time | formatTimer(true)" effect="dark" placement="top">
            <span style="font-size: 14px;color: #8590a6;">{{ review.time | formatTimer }}</span>
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
    <el-row>
      <el-col :span="22" :offset="1">
        <div class="grid-content">
          <div v-for="(item,index) in reply_list" :key="index">
            <div style="border-top: 1px solid rgb(231, 231, 231);">
              <ReviewReviewItem :reply_item="item" @submitDataMethod="childSubmitDataMethod" />
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ReviewReviewItem from '@/components/Review/ReviewItem/ReviewReviewItem'
import { getReplyByReviewId, saveReply } from '@/api/answer'
import { Message } from 'element-ui'
import { getCookie } from '@/utils/login-status'
export default {
  name: 'ReviewItem',
  components: { ReviewReviewItem },
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
    review_item: Object
  },
  data() {
    return {
      reply_list: [],
      review: {
        review_id: this.review_item.id,
        reviewer_id: this.review_item.reviewerId,
        headImage: this.review_item.reviewerImage,
        username: this.review_item.reviewerName,
        time: this.review_item.updateTime,
        content: this.review_item.reviewContent,
        reviewInputShow: false,
        reviewContent: '',
        reviewPlaceholder: ''
      }
    }
  },
  beforeMount() {
    const submitData = { 'id': this.review.review_id }
    getReplyByReviewId(submitData).then((response) => {
      this.reply_list = response.data
      console.log(this.reply_list)
    })
  },
  methods: {
    reviewInputShowButton() {
      this.review.reviewInputShow = !this.review.reviewInputShow
      this.review.reviewPlaceholder = '回复' + this.review.username
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
        const submitData = { 'id': this.review.review_id, 'userId': getCookie(), 'byReplierId': this.review.reviewer_id, 'replyContent': this.review.reviewContent }
        saveReply(submitData).then((response) => {
          this.reply_list = response.data
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    },
    childSubmitDataMethod(submitData) {
      saveReply(submitData).then((response) => {
        this.reply_list = response.data
      })
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
