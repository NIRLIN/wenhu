<template>
  <div id="HotQuestion">
    <el-row>
      <div class="hot_content">

        <el-col :span="1">
          <div class="grid-content ">
            <div id="hot_num" class="hot_question_number ">
              <span v-if="hot_number_no===1" class="hot_number_no_font_one">{{ hot_number_no }}</span>
              <span v-if="hot_number_no===2" class="hot_number_no_font_two">{{ hot_number_no }}</span>
              <span v-if="hot_number_no===3" class="hot_number_no_font_three">{{ hot_number_no }}</span>
              <span v-if="hot_number_no > 3">{{ hot_number_no }}</span>
            </div>
          </div>
        </el-col>
        <el-col :span="23">
          <div class="grid-content bg-purple-light">
            <div id="title_url">
              <el-link id="el_link" :href="hot_question_url" :underline="false" target="_blank">{{
                hot_question.questionTitle }}
              </el-link>
            </div>
          </div>
        </el-col>
      </div>

    </el-row>
    <el-row>
      <el-col :span="1">
        <div class="grid-content bg-purple" />
      </el-col>
      <el-col :span="23">
        <div class="grid-content bg-purple-light">
          <div id="title_description">
            <el-link id="el_link_content" :href="hot_question_url" :underline="false">
              <span v-html="hot_question.questionDescription">{{ hot_question.questionDescription }}</span>
            </el-link>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="1">
        <div class="grid-content bg-purple" />
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple">
          <div id="footer_hot">
            <span>
              <i class="el-icon-data-line" /> {{ hot_question.questionHeat }}万热度
            </span>
          </div>
        </div>
      </el-col>
      <el-col :span="2">
        <div class="grid-content bg-purple">
          <div id="footer_share">
            <span @click="copy_link">
              <i class="el-icon-s-promotion" />分享
            </span>
          </div>
        </div>
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple" />
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: 'HotQuestion',
  props: {
    // eslint-disable-next-line vue/prop-name-casing,vue/require-default-prop,no-undef
    hot_question: Object,
    // eslint-disable-next-line vue/prop-name-casing,vue/require-default-prop
    hot_number_no: Number
  },
  data() {
    return {
      hot_question_url: ''
    }
  },
  created() {
    this.hot_question_url = '#/question/' + this.hot_question.questionId
  },
  methods: {
    copy_link: function() {
      alert(window.location.href)
    }
  }
}
</script>

<style scoped>

#el_link {
  font-weight: bold;
  font-size: 18px;
  color: #333333;
}

#el_link_content {
  font-size: 15px;
  color: #333333;
}

#title_description {
  font-size: 15px;
}

#footer_hot {
  float: left;
  font-size: 14px;
  color: rgb(159, 173, 199);
}

#footer_share {
  margin-left: 10%;
  font-size: 14px;
  color: rgb(159, 173, 199);

}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  margin-bottom: 5px;

}

.hot_question_number {
  font-size: 18px;
  font-weight: bold;
  color: rgb(153, 153, 153);
  margin-left: 30%;
}

.hot_content {
  margin-top: 20px;
}

.hot_number_no_font_one {
  color: rgb(255, 0, 0);
}

.hot_number_no_font_two {
  color: rgb(255, 80, 0);
}

.hot_number_no_font_three {
  color: rgb(255, 160, 0);
}
</style>
