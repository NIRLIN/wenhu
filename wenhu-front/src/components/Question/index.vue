<template>
  <div id="question">
    <el-row>
      <el-col :span="4">
        <div class="grid-content " />
      </el-col>
      <el-col :span="14">
        <div class="grid-content ">
          <div>
            <br>
            <span class="question_title"> {{ question_name }} </span>
          </div>

        </div>
      </el-col>
      <el-col :span="2">
        <div class="grid-content " />
      </el-col>
      <el-col :span="1">
        <div class="grid-content ">
          <div class=" follow_browsing">
            <el-link :underline="false">
              <span class="follow_number_font">
                关注者
              </span>
              <br>
              <span class="follow_number_count">
                {{ question_follow_number }}
              </span>
            </el-link>
          </div>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="grid-content ">
          <div class=" div_divider_left follow_browsing">
            <el-link :underline="false" style="margin-left: 20px;">
              <span class="follow_number_font">
                浏览数
              </span>
              <br>
              <span class="follow_number_count">
                {{ question_browse_number }}
              </span>
            </el-link>
          </div>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content " />
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="4">
        <div class="grid-content " />
      </el-col>
      <el-col :span="10">
        <div class="grid-content ">
          <br>
          <div>
            <span class="question_description"> {{ question_description }} </span>
          </div>
        </div>
      </el-col>
      <el-col :span="10">
        <div class="grid-content " />
      </el-col>
    </el-row>
    <el-row class="question_function">

      <el-col :span="4">
        <div class="grid-content " />
      </el-col>
      <el-col :span="12">
        <div class="grid-content ">
          <el-button class="button_height" type="primary">关注问题</el-button>
          <el-button icon="el-icon-edit" plain type="primary">写回答</el-button>
          <el-button class="no_border_outline" icon="el-icon-s-comment " plain>评论</el-button>
          <el-button class="no_border_outline" icon="el-icon-s-promotion " plain>分享</el-button>
          <el-button class="no_border_outline" plain>
            <i class="el-icon-more question_more " />
          </el-button>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Question',
  data() {
    return {
      question_name: '这里是问题',
      question_description: '这里是描述；这里是描述；这里是描述；这里是描述；这里是描述；这里是描述；这里是描述；',
      question_follow_number: 666,
      question_browse_number: 6666
    }
  }

}
</script>

<style scoped>
#question {
  background: #ffffff;
}

.grid-content {
  border-radius: 10px;
  min-height: 36px;
  /*background: #ee9e9e;*/
}

.question_title {
  font-weight: bold;
  font-size: 25px;
}

.question_description {
  font-size: 15px;
}

.follow_number_font {
  font-size: 14px;
  color: #8590a6;
}

.button_height {

}

.follow_number_count {
  font-size: 18px;
  font-weight: bold;
  color: #121212;
}

.div_divider_left {
  border-left: solid 1px #8590a6;
}

.question_function {
  margin-top: 1%;
  height: 50px;
}

.question_more {
  font-size: 15px;
  font-weight: bold;
}

.no_border_outline {
  outline: none;
  border: none;
}

.follow_browsing {
  margin-top: 10px;
}
</style>
