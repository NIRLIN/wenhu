<template>
  <div id="questionLogItem">
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <div class="questionLogItem_edit_question">
            <a href="aaa">用户</a> 编辑了问题
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <div class="questionLogItem_change_content">
            修改前:<span>sdfdsasda阿所发生的根深蒂固</span>
            <br>
            修改后:<span>sdfdsasda阿所发生的根深蒂固</span>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content  ">
          <span class="font_add_bold questionLogItem_edit_cause">修改理由：</span>
          <span class="questionLogItem_edit_cause">
          sdfdsasda阿所发生的根深蒂固
        </span>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
        <span class="questionLogItem_edit_id_time">
         # 78109715400 • 2021-01-12 16:31:19

        </span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "questionLogItem"
}
</script>

<style scoped>
.questionLogItem_change_content {
  font-size: 13px;
  background: rgb(246, 246, 246);
}

.grid-content {
  border-radius: 4px;
  min-height: 3px;
  margin-top: 5px;
}

.font_add_bold {
  font-weight: bold;
}

.questionLogItem_edit_question {
  font-size: 15px;
}

.questionLogItem_edit_cause {
  font-size: 13px;
}

.questionLogItem_edit_id_time {
  font-size: 13px;

}

a:link {
  color: rgb(34, 85, 153);
}

a:visited {
  color: rgb(34, 85, 153);
}
</style>