<template>
  <div id="Hot">
    <el-row class="margin_nav_size">
      <el-col :span="4">
        <div class="grid-content " />
      </el-col>
      <el-col :span="16">
        <div class="grid-content ">
          <el-card>
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple">
                  <nav>
                    <el-link :underline="false" class="nav_font" href="#/recommend">推荐</el-link>
                    <el-link :underline="false" class="nav_font" href="#/follow">关注</el-link>
                    <el-link :underline="false" class="nav_font change_font_color" href="#/hot">热榜</el-link>
                  </nav>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple" />
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple">
                  <div id="hotList">
                    <div v-for="(hot,index) in hotList" :key="index" class="hot_question_background ">
                      <div>
                        <HotQuestion :hot_number_no="index+1" :hot_question="hot" />
                      </div>
                    </div>
                  </div>
                </div>
              </el-col>
            </el-row>
          </el-card>

        </div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content " />
      </el-col>
    </el-row>
  </div>

</template>

<script>

import HotQuestion from '@/views/Home/Hot/HotQuestion'
import { getHotList } from '@/api/hot'
// eslint-disable-next-line no-unused-vars
import { Message } from 'element-ui'

export default {
  name: 'Hot',
  components: { HotQuestion },
  data() {
    return {
      items: ['a', 'b', 'c'],
      hotList: []
    }
  },
  created() {
    getHotList().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        this.hotList.push(JSON.parse(response.data[i]))
      }
      // console.log(this.hotList)
    })
  },
  mounted() {
    if (this.hotList === null) {
      // alert('aaa')
    }
  }
}
</script>

<style scoped>
.hot_question_background {
  border-top: 1px solid rgb(231, 231, 231);
}

.follow_margin {
  margin-top: 20px;
  margin-bottom: 20px;
}

.nav_font {
  font-size: 16px;
  margin-left: 40px;
}

.grid-content {
  min-height: 20px;
  border-radius: 10px;
}

.change_font_color {
  color: #0066FF;
}
</style>
