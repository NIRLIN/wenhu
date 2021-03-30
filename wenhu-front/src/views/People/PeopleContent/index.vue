<template>
  <div id="peopleContent" v-loading.fullscreen.lock="fullscreenLoading">
    <el-card class="two-el-card-css">
      <el-tabs v-model="activeNameHome" @tab-click="getHomeData">
        <el-tab-pane v-if="homepage.answer" label="回答" name="first">
          <div>
            <el-row>
              <el-col :span="20" :offset="1">
                <div class="grid-content ">
                  <span class="people_content_fun_font">
                    他的回答
                  </span>
                </div>
              </el-col>
              <el-col :span="2">
                <div class="grid-content ">
                  <el-button type="text" size="mini" @click="getUserAnswerByUserId">
                    <span class="button_font_color">
                      {{ sort_button_value }} <i class="el-icon-d-caret" />
                    </span>
                  </el-button>
                </div>
              </el-col>
            </el-row>
            <el-row class="people_content_row_margin">
              <el-col :span="1">
                <div class="grid-content " />
              </el-col>
              <el-col :span="22">
                <div class="grid-content ">
                  <div>
                    <div v-for="(item,index) in homeData.answer" :key="index" class="people_content_list ">
                      <div class="answer_item">
                        <Answer :answer_item="item" />
                      </div>
                    </div>
                  </div>
                </div>

              </el-col>
            </el-row>
          </div>

        </el-tab-pane>
        <el-tab-pane v-if="homepage.question" label="提问" name="second">
          <div>
            <el-row class="">
              <el-col :span="22" :offset="1">
                <div class="grid-content people_content_fun_font_margin">
                  <span class="people_content_fun_font">
                    他的提问
                  </span>
                </div>
              </el-col>
            </el-row>
            <el-row class="">
              <el-col :span="22" :offset="1">
                <div class="grid-content ">
                  <div class="">
                    <div v-for="(item,index) in homeData.question" :key="index" class="people_content_list ">
                      <div class="answer_item">
                        <QuestionTitle :question_item="item" />
                      </div>
                    </div>
                  </div>
                </div>

              </el-col>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane v-if="homepage.article" label="文章" name="third">
          <div>
            <el-row>
              <el-col :span="20" :offset="1">
                <div class="grid-content ">
                  <span class="people_content_fun_font">
                    他的文章
                  </span>
                </div>
              </el-col>
              <el-col :span="2">
                <div class="grid-content ">
                  <el-button type="text" size="mini" @click="getUserArticleByUserId">
                    <span class="button_font_color">
                      {{ sort_button_value }} <i class="el-icon-d-caret" />
                    </span>
                  </el-button>
                </div>
              </el-col>
            </el-row>
            <el-row class="people_content_row_margin">
              <el-col :span="1">
                <div class="grid-content " />
              </el-col>
              <el-col :span="22">
                <div class="grid-content ">
                  <div class="">
                    <div v-for="(item,index) in homeData.article" :key="index" class="people_content_list ">
                      <div class="answer_item">
                        <Answer :answer_item="item" />
                      </div>
                    </div>
                  </div>
                </div>

              </el-col>
            </el-row>
          </div>

        </el-tab-pane>
        <el-tab-pane v-if="homepage.collect" label="收藏" name="fourth">
          <div>
            <el-row class="">
              <el-col :span="1">
                <div class="grid-content " />
              </el-col>
              <el-col :span="22">
                <div class="grid-content">
                  <el-tabs v-model="activeNameCollect">
                    <el-tab-pane label="他收藏的回答" name="first">他收藏的回答</el-tab-pane>
                    <el-tab-pane label="他收藏的文章" name="second">他收藏的文章</el-tab-pane>
                  </el-tabs>
                </div>

              </el-col>
            </el-row>
          </div>

        </el-tab-pane>
        <el-tab-pane v-if="homepage.follow" label="关注" name="fifth">
          <el-row>
            <el-col :span="22" :offset="1">
              <div class="grid-content ">
                <el-tabs v-model="activeNameFollow">
                  <el-tab-pane label="他关注的人" name="first">他关注的人</el-tab-pane>
                  <el-tab-pane label="关注他的人" name="second">关注他的人</el-tab-pane>
                </el-tabs>
              </div>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
import QuestionTitle from '@/views/People/PeopleContent/QuestionTitle'
import Answer from '@/components/Answer'
import { getHomepageByUserId, listAnswerByUserId, listArticleByUserId, listQuestionByUserId, listCollectByUserId, listFollowByUserId } from '@/api/people'

export default {
  name: 'PeopleContent',
  components: { Answer, QuestionTitle },
  data() {
    return {
      activeNameHome: 'first',
      activeNameCollect: 'first',
      activeNameFollow: 'first',
      items: ['a', 'b', 'c'],
      sort_button_value: '时间排序',
      homepage: {
        answer: 1,
        question: 1,
        article: 1,
        collect: 1,
        follow: 1
      },
      homeData: {
        answer: [],
        question: [],
        article: [],
        collect: [],
        follow: []
      },
      fullscreenLoading: false
    }
  },
  created() {
    this.fullscreenLoading = true
    const submitData = { 'id': this.$route.params.id }
    getHomepageByUserId(submitData).then((response) => {
      if (response.data != null) {
        this.homepage = response.data
      }
      this.fullscreenLoading = false
    })
    // 默认时间排序
    const submitData1 = { 'id': this.$route.params.id, 'type': 'time' }
    listAnswerByUserId(submitData1).then((response) => {
      this.homeData.answer = response.data
      this.fullscreenLoading = false
    })
  },
  methods: {
    getHomeData(tab, event) {
      if (this.activeNameHome === 'first') {
        this.getUserAnswerByUserId()
      }
      if (this.activeNameHome === 'second') {
        this.getUserQuestionByUserId()
      }
      if (this.activeNameHome === 'third') {
        this.getUserArticleByUserId()
      }
      if (this.activeNameHome === 'fourth') {
        this.getUserCollectByUserId()
      }
      if (this.activeNameHome === 'fifth') {
        this.getUserFollowByUserId()
      }
    },
    getUserCollectByUserId() {
      this.fullscreenLoading = true
      const submitData = { 'id': this.$route.params.id }
      listCollectByUserId(submitData).then((response) => {
        this.homeData.collect = response.data
        console.log(response.data)
        this.fullscreenLoading = false
      })
    },
    getUserFollowByUserId() {
      this.fullscreenLoading = true
      const submitData = { 'id': this.$route.params.id }
      listFollowByUserId(submitData).then((response) => {
        this.homeData.follow = response.data
        console.log(response.data)
        this.fullscreenLoading = false
      })
    },
    getUserQuestionByUserId() {
      this.fullscreenLoading = true
      const submitData = { 'id': this.$route.params.id }
      listQuestionByUserId(submitData).then((response) => {
        this.homeData.question = response.data
        // console.log(response.data)
        this.fullscreenLoading = false
      })
    },
    getUserAnswerByUserId() {
      this.fullscreenLoading = true
      if (this.sort_button_value === '时间排序') {
        this.sort_button_value = '赞同排序'
        const submitData = { 'id': this.$route.params.id, 'type': 'heat' }
        listAnswerByUserId(submitData).then((response) => {
          this.homeData.answer = response.data
          // console.log(response.data)
          this.fullscreenLoading = false
        })

        return
      }
      if (this.sort_button_value === '赞同排序') {
        this.sort_button_value = '时间排序'
        const submitData = { 'id': this.$route.params.id, 'type': 'time' }
        listAnswerByUserId(submitData).then((response) => {
          this.homeData.answer = response.data
          this.fullscreenLoading = false
        })
      }
    },
    getUserArticleByUserId() {
      this.fullscreenLoading = true

      if (this.sort_button_value === '时间排序') {
        this.sort_button_value = '赞同排序'
        const submitData = { 'id': this.$route.params.id, 'type': 'heat' }
        listArticleByUserId(submitData).then((response) => {
          this.homeData.article = response.data
          // console.log(response.data)
          this.fullscreenLoading = false
        })
        return
      }
      if (this.sort_button_value === '赞同排序') {
        this.sort_button_value = '时间排序'
        const submitData = { 'id': this.$route.params.id, 'type': 'time' }

        listArticleByUserId(submitData).then((response) => {
          this.homeData.article = response.data
          this.fullscreenLoading = false
        })
      }
    },
    setSortMethod() {
      if (this.sort_button_value === '时间排序') {
        this.sort_button_value = '赞同排序'
        return
      }
      if (this.sort_button_value === '赞同排序') {
        this.sort_button_value = '时间排序'
        return
      }
    }

  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 3px;
}
.people_content_fun_font{
  font-weight: bold;
  font-size: 15px;
}
.people_content_list {
  border-top: 1px solid rgb(231, 231, 231);
}

.people_content_fun_font_margin{
  margin-bottom: 10px;
}
.answer_item {
  margin-top: 10px;
  margin-bottom: 10px;
}
.button_font_color {
  color: #121212;
}
</style>
