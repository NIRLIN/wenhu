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
                    <div v-if="homeData.answer.length===0" class="people_content_list ">
                      <div class="answer_item" style="margin-left: 35%;">
                        <div>
                          <svg width="250" height="220" viewBox="-5 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
                        </div>
                        <div>
                          <span style="color: rgb(133,144,166);font-size: 15px;">暂时还没有回答，快来写第一个回答吧</span>
                        </div>
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
                    <div v-if="homeData.question.length===0" class="people_content_list ">
                      <div class="answer_item" style="margin-left: 35%;">
                        <div>
                          <svg width="250" height="220" viewBox="-5 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
                        </div>
                        <div>
                          <span style="color: rgb(133,144,166);font-size: 15px;">暂时还没有提问，快来写第一个提问吧</span>
                        </div>
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
                        <Article :answer_item="item" />
                      </div>
                    </div>
                    <div v-if="homeData.article.length===0" class="people_content_list ">
                      <div class="answer_item" style="margin-left: 35%;">
                        <div>
                          <svg width="250" height="220" viewBox="-5 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
                        </div>
                        <div>
                          <span style="color: rgb(133,144,166);font-size: 15px;">暂时还没有文章，快来写第一个文章吧</span>
                        </div>
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
                    <el-tab-pane label="他收藏的回答" name="first">
                      <div>
                        <div v-for="(item,index) in homeData.collect.answer" :key="index" class="people_content_list ">
                          <div class="answer_item">
                            <Answer :answer_item="item" />
                          </div>
                        </div>
                        <div v-if="homeData.collect.answer.length===0">
                          <div class="answer_item" style="margin-left: 40%;">
                            <div>
                              <svg width="250" height="220" viewBox="28 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
                            </div>
                            <div>
                              <span style="color: rgb(133,144,166);font-size: 15px;">暂时还没有收藏的回答~</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </el-tab-pane>
                    <el-tab-pane label="他收藏的文章" name="second">
                      <div>
                        <div v-for="(item,index) in homeData.collect.article" :key="index" class="people_content_list ">
                          <div class="answer_item">
                            <Article :answer_item="item" />
                          </div>
                        </div>
                        <div v-if="homeData.collect.article.length===0">
                          <div class="answer_item" style="margin-left: 40%;">
                            <div>
                              <svg width="250" height="220" viewBox="28 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
                            </div>
                            <div>
                              <span style="color: rgb(133,144,166);font-size: 15px;">暂时还没有收藏的文章~</span>
                            </div>
                          </div>
                        </div>
                      </div>
                    </el-tab-pane>
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
                  <el-tab-pane label="他关注的人" name="first">
                    <div>
                      <div v-for="(item,index) in homeData.follow.follows" :key="index" class="people_content_list ">
                        <div class="answer_item">
                          <div style="width: 50px; float:left">
                            <el-link :underline="false">
                              <el-avatar :size="40" :src="item.headImage" shape="square" />
                            </el-link>
                          </div>
                          <div style="float: left;">
                            <div>
                              <el-link :underline="false">
                                <span class="answer_font_name">{{ item.username }}</span>
                              </el-link>
                            </div>
                            <div>
                              <span class="answer_font_resume">{{ item.resume }}</span>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div v-if="homeData.follow.follows.length===0">
                        <div class="answer_item" style="margin-left: 40%;">
                          <div>
                            <svg width="250" height="220" viewBox="20 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
                          </div>
                          <div>
                            <span style="color: rgb(133,144,166);font-size: 15px;">用户暂时还没有关注的人哦~</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </el-tab-pane>
                  <el-tab-pane label="关注他的人" name="second">
                    <div>
                      <div v-for="(item,index) in homeData.follow.fans" :key="index" class="people_content_list ">
                        <div class="answer_item">
                          <div style="width: 50px; float:left">
                            <el-link :underline="false">
                              <el-avatar :size="40" :src="item.headImage" shape="square" />
                            </el-link>
                          </div>
                          <div style="float: left;">
                            <div>
                              <el-link :underline="false">
                                <span class="answer_font_name">{{ item.username }}</span>
                              </el-link>
                            </div>
                            <div>
                              <span class="answer_font_resume">{{ item.resume }}</span>
                            </div>
                          </div>
                        </div>

                      </div>
                      <div v-if="homeData.follow.fans.length===0">
                        <div class="answer_item" style="margin-left: 40%;">
                          <div>
                            <svg width="250" height="220" viewBox="20 0 150 120" fill="currentColor"><g fill="none" fill-rule="evenodd"><path fill="#EBEEF5" d="M67.757 83H45c-1.66 0-3-1.338-3-2.998V37.998A3.003 3.003 0 0145 35h53.42v-3H45c-3.31 0-6 2.686-6 5.998v42.004A5.994 5.994 0 0045 86h21.515l6.853 6.854a2.99 2.99 0 004.234 0L84.456 86H105c3.31 0 6-2.686 6-5.998v-36.54h-3v36.54A3.003 3.003 0 01105 83H83.214l-7.728 7.728L67.756 83z" fill-rule="nonzero" /><path fill="#F7F8FA" d="M55 48.5c0-.828.67-1.5 1.508-1.5h26.984a1.5 1.5 0 110 3H56.508A1.5 1.5 0 0155 48.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 59.5zm0 11c0-.828.677-1.5 1.495-1.5h37.01c.826 0 1.495.666 1.495 1.5 0 .828-.677 1.5-1.495 1.5h-37.01A1.494 1.494 0 0155 70.5z" /><path fill="#EBEEF5" d="M94.868 50.46l18.92-18.92-2.83-2.827-18.918 18.92-2.12-2.123 18.917-18.918a3.005 3.005 0 014.245-.002l2.828 2.828a3.004 3.004 0 01-.002 4.245L96.99 52.58l-2.122-2.12zm-7.193 2.377l2.244-7.327 7.07 7.07-7.328 2.245c-1.575.482-2.473-.405-1.988-1.988z" fill-rule="nonzero" /></g></svg>
                          </div>
                          <div>
                            <span style="color: rgb(133,144,166);font-size: 15px;">暂时还没有关注用户的人哦~</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </el-tab-pane>
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
import Article from '@/components/Article'

export default {
  name: 'PeopleContent',
  components: { Article, Answer, QuestionTitle },
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
        collect: {
          answer: [],
          article: []
        },
        follow: {
          follows: [],
          fans: []
        }
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
        this.homeData.collect.answer = response.data.answer
        this.homeData.collect.article = response.data.article
        // console.log(response.data)
        this.fullscreenLoading = false
      })
    },
    getUserFollowByUserId() {
      this.fullscreenLoading = true
      const submitData = { 'id': this.$route.params.id }
      listFollowByUserId(submitData).then((response) => {
        this.homeData.follow.follows = response.data.follows
        this.homeData.follow.fans = response.data.fans
        // console.log(response.data)
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
