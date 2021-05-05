<template>
  <div id="questionLogItem">
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <div class="questionLogItem_edit_question" style="font-size: 15px;">
            <el-link :underline="false" :href="'/#/people/'+question_log.menderId">
              <span style="color: #20a0ff">
                {{ question_log.menderUsername }}
              </span>
            </el-link>
            <span>
              在 {{ question_log.updateTime | formatTimer(true) }}  修改了问题
            </span>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <div class="questionLogItem_edit_question">
            <span>
              问题标题：<span v-html="question_log.title" />
            </span>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <div class="questionLogItem_edit_question">
            <span>
              问题描述：<span v-html="question_log.description" />
            </span>
          </div>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: 'QuestionLogItem',
  filters: {
    formatTimer: function(value, hours) {
      const date = new Date(value)
      const y = date.getFullYear()
      let M = date.getMonth() + 1
      M = M < 10 ? '0' + M : M
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      let h = date.getHours()
      let m = date.getMinutes()
      h = h >= 10 ? h : '0' + h
      m = m >= 10 ? m : '0' + m
      if (hours) {
        return y + '-' + M + '-' + d + ' ' + h + ':' + m
      } else {
        return y + '-' + M + '-' + d
      }
    }
  },
  props: {
    // eslint-disable-next-line vue/prop-name-casing,vue/require-default-prop
    question_log: Object
  }

}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 3px;
  margin-top: 5px;
}
.questionLogItem_edit_question {
  font-size: 15px;
}
</style>
