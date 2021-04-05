<template>
  <div>
    <el-row>
      <el-col :span="24"><div class="grid-content ">
        <h3>主页管理</h3>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6" :offset="6">
        <div class="grid-content ">
          <el-switch
            v-model="homepage.answer"
            active-text="展示回答"
            inactive-text="隐藏回答"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ff4949"
          />
        </div>
      </el-col>
      <el-col :span="6" :offset="1">
        <div class="grid-content ">
          <el-switch
            v-model="homepage.question"
            active-text="展示问题"
            inactive-text="隐藏问题"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ff4949"
          />
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6" :offset="6">
        <div class="grid-content ">
          <el-switch
            v-model="homepage.article"
            active-text="展示文章"
            inactive-text="隐藏文章"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ff4949"
          />
        </div>
      </el-col>
      <el-col :span="6" :offset="1">
        <div class="grid-content ">
          <el-switch
            v-model="homepage.collect"
            active-text="展示收藏"
            inactive-text="隐藏收藏"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ff4949"
          />
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6" :offset="6">
        <div class="grid-content ">
          <el-switch
            v-model="homepage.follow"
            active-text="展示关注"
            inactive-text="隐藏关注"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#ff4949"
          />

        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6"><div class="grid-content " /> </el-col>
    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-button style="width: 100%;" type="primary" @click="saveChangeHomepage">保存修改</el-button>
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import { getHomepageByUserId, saveChangeHomepage } from '@/api/people'
import { getCookie } from '@/utils/login-status.js'
import { Message } from 'element-ui'

export default {
  name: 'ChangeHomepage',
  props: {
    // eslint-disable-next-line vue/require-default-prop,vue/prop-name-casing
    active_name: String
  },
  data() {
    return {
      homepage: {
        answer: 1,
        article: 1,
        question: 1,
        collect: 1,
        follow: 1
      }
    }
  },
  watch: {
    active_name() {
      if (this.active_name === 'homepage') {
        const submitData = { 'id': getCookie() }
        getHomepageByUserId(submitData).then((response) => {
          if (response.data !== null) {
            this.homepage = response.data
          }
        })
      }
    }
  },
  methods: {
    saveChangeHomepage() {
      if (getCookie() !== undefined) {
        const submitData = {
          'id': getCookie(),
          'answer': this.homepage.answer,
          'article': this.homepage.article,
          'question': this.homepage.question,
          'collect': this.homepage.collect,
          'follow': this.homepage.follow
        }
        saveChangeHomepage(submitData).then((response) => {
          if (response.data !== null) {
            this.homepage = response.data
            Message.success({
              message: '修改成功',
              center: true
            })
          }
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
  min-height: 20px;
  margin-top: 10px;
}
</style>
