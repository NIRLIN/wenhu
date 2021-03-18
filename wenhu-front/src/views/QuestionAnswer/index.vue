<template>
  <div id="questionAnswer">
    <el-row>
      <el-col :span="24">
        <div class="grid-content margin_nav_size">
          <Question/>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="4">
        <div class="grid-content ">
        <span class="answer_number">
          {{ answer_number }}个回答
        </span>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content "/>
      </el-col>
      <el-col :span="3">
        <div class="grid-content ">
          <el-button type="text">
          <span class="button_font_color">
            默认排序 <i class="el-icon-d-caret"/>
          </span>
          </el-button>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content answer_item">
          <Answer/>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import Question from '@/components/Question'
import Answer from '@/components/Answer'

export default {
  name: 'QuestionAnswer',
  components: {Answer, Question},
  data() {
    return {
      answer_number: 20
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 8px;
  min-height: 36px;

}

.answer_content {
  background: #ffffff;
  margin-top: 10px;
}

.answer_item {
  width: 94%;
  margin-left: 3%;
}

.answer_number {
  font-size: 15px;
  font-weight: bold;
  margin-left: 25px;
  text-align: center;
}

.button_font_color {
  color: #121212;
}
</style>
