<template>
  <div v-if="question_item" class="question_margin">
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <el-link :href="'#/question/'+question_item.id" :underline="false">
            <span class="title_font">
              {{ question_item.title }}
            </span>
          </el-link>
        </div>
      </el-col>
    </el-row>
    <el-row class="title_row_margin">
      <el-col :span="22">
        <div class="grid-content ">
          <div class="title_fun_div">
            <span class="title_fun_font">
              {{ question_item.createTime | formatTimer }}
            </span>
          </div>
          <div class="title_fun_margin">
            <span>·</span>
          </div>
          <div class="title_fun_div">
            <span class="title_fun_font">
              {{ question_item.followNumber }} 个关注
            </span>
          </div>
          <div class="title_fun_margin">
            <span>·</span>
          </div>
          <div class="title_fun_div">
            <span class="title_fun_font">
              {{ question_item.browseNumber }} 个浏览
            </span>
          </div>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="grid-content " />
      </el-col>
      <el-col :span="2">
        <div class="grid-content " />
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'QuestionTitle',
  filters: {
    formatTimer: function(value) {
      const date = new Date(value)
      const y = date.getFullYear()
      let MM = date.getMonth() + 1
      MM = MM < 10 ? '0' + MM : MM
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      return y + '-' + MM + '-' + d
    }
  },
  props: {
    // eslint-disable-next-line vue/require-default-prop,vue/prop-name-casing
    question_item: Object
  },
  data() {
    return {
      question_title: '题目',
      question_id: '123456',
      question_create_time: '2021-01-01',
      question_answer_number: 99,
      question_follow_number: 99
    }
  }

}
</script>

<style scoped>
.title_font{
  font-weight: bold;
  font-size: 18px;
  color: #121212;
}
.grid-content {
  border-radius: 8px;
  min-height: 16px;
}
.title_fun_div{
  float: left;
}
.title_row_margin{
  margin-top: 5px;
}
.title_fun_margin{
  margin-left: 4px;
  margin-right: 4px;
  float: left;
}
.title_fun_font{
  font-size: 14px;
  color: #8590a6;
  text-align: center;
}
.question_margin{
  margin-top: 20px;
  margin-bottom: 5px;
}
</style>
