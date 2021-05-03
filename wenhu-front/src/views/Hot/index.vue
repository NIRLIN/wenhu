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
                    <div v-for="(item,index) in listClassify" :key="index" style="float: left;">
                      <el-link :href="'/#/hot?classify='+item.classifyName" :underline="false">
                        <el-tag :class="[index!==0 ? 'tag_margin':'',item.classifyName===chooseTagValue? 'tag_background_yes':'tag_background_no']">{{ item.classifyName }}
                        </el-tag>
                      </el-link>
                    </div>
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
    <el-backtop />

  </div>
</template>

<script>

import HotQuestion from '@/views/Hot/HotQuestion'
// eslint-disable-next-line no-unused-vars
import { listClassify, getHotList, listHotByClassify } from '@/api/creation'

export default {
  name: 'Hot',
  components: { HotQuestion },
  data() {
    return {
      items: ['a', 'b', 'c'],
      hotList: [],
      listClassify: [],
      chooseTagValue: '全站'
    }
  },
  watch: {
    $route(to, from) {
      this.chooseTagValue = this.$route.query.classify
      if (this.$route.query.classify === undefined) {
        this.chooseTagValue = '全站'
      }
      this.listHotByClassifyMethod()
    }
  },
  created() {
    if (this.$route.query.classify === undefined) {
      getHotList().then((response) => {
        for (let i = 0; i < response.data.length; i++) {
          this.hotList.push(JSON.parse(response.data[i]))
        }
        // console.log(this.hotList)
      })
    } else {
      this.chooseTagValue = this.$route.query.classify
      console.log(this.chooseTagValue)
      this.listHotByClassifyMethod()
    }
    listClassify().then((response) => {
      this.listClassify = response.data
    })
  },
  methods: {
    listHotByClassifyMethod() {
      const submitData = { 'classifyName': this.chooseTagValue }
      listHotByClassify(submitData).then((response) => {
        this.hotList = response.data
      })
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

.tag_margin {
  margin-left: 10px;
}

.tag_background_yes {
  background-color: rgb(229, 239, 255);
  color: rgb(0, 102, 255);
}

.tag_background_no {
  background-color: rgb(246, 246, 246);
  color: rgb(100, 100, 100);

}

</style>
