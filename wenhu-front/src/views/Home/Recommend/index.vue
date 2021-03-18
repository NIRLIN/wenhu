<template>
  <div id="Recommend">
    <el-row>
      <el-col :span="4">
        <div class="grid-content "/>
      </el-col>
      <el-col :span="16">
        <div class="grid-content ">
          <el-card>
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple">
                  <nav>
                    <el-link :underline="false" class="nav_font change_font_color" href="#/recommend">推荐</el-link>
                    <el-link :underline="false" class="nav_font" href="#/follow">关注</el-link>
                    <el-link :underline="false" class="nav_font" href="#/hot">热榜</el-link>
                  </nav>
                </div>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple"/>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple">
                  <div id="hotList">
                    <div v-for="item in items" :key="item" class="hot_question_background ">
                      <div class="follow_margin">
                        <answer>{{ item }}</answer>

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
        <div class="grid-content "/>
      </el-col>
    </el-row>
  </div>

</template>

<script>
import Answer from '@/components/Answer'

export default {
  name: 'Recommend',
  components: {Answer},
  data() {
    return {
      items: ['a', 'b', 'c']
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
