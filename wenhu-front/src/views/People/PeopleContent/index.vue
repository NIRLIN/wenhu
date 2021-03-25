<template>
  <div id="peopleContent">
    <el-card class="two-el-card-css">
      <el-tabs v-model="activeName" @tab-click="handleClick">
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
                  <el-button type="text" size="mini" @click="setSortMethod">
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
                    <div v-for="item in items" :key="item" class="people_content_list ">
                      <div class="answer_item">
                        <Answer>{{ item }}</Answer>
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
              <el-col :span="1">
                <div class="grid-content " />
              </el-col>
              <el-col :span="22">
                <div class="grid-content ">
                  <div class="">
                    <div v-for="item in items" :key="item" class="people_content_list ">
                      <div class="answer_item">
                        <QuestionTitle>{{ item }}</QuestionTitle>
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
                  <el-button type="text" size="mini" @click="setSortMethod">
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
                    <div v-for="item in items" :key="item" class="people_content_list ">
                      <div class="answer_item">
                        <Answer>{{ item }}</Answer>
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
              <el-col :span="22" :offset="1">
                <div class="grid-content people_content_fun_font_margin">
                  <span class="people_content_fun_font">
                    他的收藏
                  </span>
                </div>
              </el-col>
            </el-row>
            <el-row class="">
              <el-col :span="1">
                <div class="grid-content " />
              </el-col>
              <el-col :span="22">
                <div class="grid-content ">
                  <div class="">
                    <div v-for="item in items" :key="item" class="people_content_list ">
                      <div class="answer_item">
                        <QuestionTitle>{{ item }}</QuestionTitle>
                      </div>
                    </div>
                  </div>
                </div>

              </el-col>
            </el-row>
          </div>

        </el-tab-pane>
        <el-tab-pane v-if="homepage.follow" label="关注" name="fifth">
          <el-row>
            <el-col :span="22" :offset="1">
              <el-tabs v-model="activeName1">
                <el-tab-pane label="他关注的人" name="first">他关注的人</el-tab-pane>
                <el-tab-pane label="关注他的人" name="second">关注他的人</el-tab-pane>
              </el-tabs>

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
import { getHomepageByUserId, getUserHomepageDataByUserId } from '@/api/people'

export default {
  name: 'PeopleContent',
  components: { Answer, QuestionTitle },
  data() {
    return {
      activeName: 'first',
      activeName1: 'first',
      items: ['a', 'b', 'c'],
      sort_button_value: '时间排序',
      homepage: {
        answer: 1,
        question: 1,
        article: 1,
        collect: 1,
        follow: 1
      }

    }
  },
  created() {
    const submitData = { 'id': this.$route.params.id }
    getHomepageByUserId(submitData).then((response) => {
      this.homepage.answer = response.data.answer
      this.homepage.question = response.data.question
      this.homepage.article = response.data.article
      this.homepage.collect = response.data.collect
      this.homepage.follow = response.data.follow
    })
    getUserHomepageDataByUserId(submitData).then((response) => {
      console.log(response)
    })
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event)
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
