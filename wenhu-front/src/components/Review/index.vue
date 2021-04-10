<template>
  <div id="Review" style="background-color: #ffffff;border-top: 1px solid rgb(231, 231, 231);">
    <el-row>
      <el-col :span="4"><div class="grid-content">
        <div style="margin-top: 8px;">
          <span v-if="reviewCount===0" style="font-size: 16px;color: #121212;font-weight: bold;">还没有评论</span>
          <span v-if="reviewCount!==0" style="font-size: 16px;color: #121212;font-weight: bold;">{{ reviewCount }}条评论</span>
        </div>
      </div></el-col>
      <el-col :span="4" :offset="16"><div class="grid-content">
        <el-button type="text" style="color: rgb(118,131,155);" icon="el-icon-sort">时间排序</el-button>
      </div></el-col>
    </el-row>
    <el-row>
      <el-col :span="24"><div class="grid-content">
        <div v-for="(item,index) in reviewList" :key="index">
          <div style="border-top: 1px solid rgb(231, 231, 231);">
            <ReviewItem :review_item="item" />
          </div>
        </div>
      </div>
      </el-col>
    </el-row>
    <el-row v-show="reviewInputShow">
      <el-col :span="20">
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
          <el-button type="primary" @click="saveReviewMethod">发布</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ReviewItem from '@/components/Review/ReviewItem'
import { getReviewById, getReviewCount, saveReview } from '@/api/answer'
import { Message } from 'element-ui'
import { getCookie } from '@/utils/login-status.js'

export default {
  name: 'Review',
  components: { ReviewItem },
  data() {
    return {
      reviewList: [],
      reviewCount: -1,
      reviewInputShow: false,
      answer_id: '',
      review: {
        reviewContent: '',
        reviewPlaceholder: '写下你的评论'
      }
    }
  },
  watch: {
    reviewCount() {
      if (this.reviewCount === 0) {
        this.reviewInputShow = true
      }
    }
  },
  methods: {
    getListReviewMethod(answerId) {
      this.answer_id = answerId
      const submitData = { 'id': answerId }
      this.reviewCount = 0
      getReviewCount(submitData).then((response) => {
        this.reviewCount = response.data
      }).catch(() => {
        this.reviewCount = 0
      })
      getReviewById(submitData).then((response) => {
        this.reviewList = response.data
      })
    },
    saveReviewMethod() {
      if (this.answer_id === '' || this.review.reviewContent === '') {
        Message.error({
          message: '评论为空哦~',
          center: true
        })
        return
      }
      if (getCookie() !== undefined) {
        const submitData = { 'id': this.answer_id, 'userId': getCookie(), 'reviewContent': this.review.reviewContent }
        saveReview(submitData).then((response) => {
          this.reviewList = response.data
          // console.log(this.reviewList)
        })
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
