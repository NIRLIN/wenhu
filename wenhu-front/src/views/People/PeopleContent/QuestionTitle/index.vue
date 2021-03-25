<template>
  <div class="question_margin">
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <el-link :href="'#/question/'+question_id" :underline="false">
            <span class="title_font">
              {{ question_title }}
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
              {{ question_create_time }}
            </span>
          </div>
          <div class="title_fun_margin">
            <span>·</span>
          </div>
          <div class="title_fun_div">
            <span class="title_fun_font">
              {{ question_answer_number }} 个回答
            </span>
          </div>
          <div class="title_fun_margin">
            <span>·</span>
          </div>
          <div class="title_fun_div">
            <span class="title_fun_font">
              {{ question_follow_number }} 个关注
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
