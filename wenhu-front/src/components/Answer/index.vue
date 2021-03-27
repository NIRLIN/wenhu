<template>
  <div v-if="answer_item" id="answer" class="class_margin">
    <el-row>
      <el-col :span="22">
        <div class="grid-content">
          <div style="width: 50px; float:left">
            <el-link :underline="false">
              <el-avatar :size="40" :src="answer_item.headImage" shape="square" />
            </el-link>
          </div>
          <div style="float: left;">
            <div>
              <el-link :underline="false">
                <span class="answer_font_name">{{ answer_item.username }}</span>
              </el-link>
            </div>
            <div>
              <span class="answer_font_resume">{{ answer_item.resume }}</span>
            </div>
          </div>
        </div>
      </el-col>

    </el-row>
    <el-row class="answer_margin">
      <el-col :span="24">
        <div class="grid-content">
          <div>
            <span class="answer_content_font" v-html="answer_item.content" />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="answer_margin">
      <el-col :span="24">
        <div class="grid-content">
          <div class="answer_edit_time">
            <el-tooltip class="item" :content="answer_item.updateTime | formatTimer(true)" effect="dark" placement="top">
              <span>发布于 {{ answer_item.updateTime | formatTimer }}</span>
            </el-tooltip>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row class="answer_margin">

      <el-col :span="24">
        <div class="grid-content ">
          <el-button plain size="small" type="primary"><i class=" el-icon-caret-top icon_size" />赞同 {{ answer_item.approvalNumber }}</el-button>
          <el-button plain size="small" type="primary"><i class=" el-icon-caret-bottom icon_size" /></el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text"><i class="el-icon-chat-round icon_size" />评论</el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text"><i class="el-icon-s-promotion icon_size" />分享</el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text"><i class="el-icon-star-off icon_size" />收藏</el-button>
          <el-button class="no_border_outline button_margin_left button_color" type="text">
            <el-dropdown trigger="click" :placement="'bottom'">
              <span class="el-dropdown-link">
                <i class="el-icon-more question_more button_color " />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>举报</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-button>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: 'Answer',
  filters: {
    formatTimer: function(value, hours) {
      const date = new Date(value)
      const y = date.getFullYear()
      let M = date.getMonth() + 1
      M = M < 10 ? '0' + M : M
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      var h = date.getHours()
      var m = date.getMinutes()
      d = d >= 10 ? d : '0' + d
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
    answer_item: Object
  },
  data() {
    return {
      answer_head_image: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      answer_name: '用户姓名',
      answer_resume: '千里之行始于足下；千里之行始于足下；千里之行始于足下；',
      answer_content: '作者：阡陌\n' +
          '链接：https://zhuanlan.zhihu.com/p/351817891\n' +
          '来源：知乎\n' +
          '著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。\n' +
          '\n' +
          '何为幸福呀，幸福的本质又是什么？ 我们总是会听到许多被奉为经典的噪音：一分钱难倒英雄汉，分贱夫妻百事哀……又或者大名鼎鼎的鲁迅先生也曾经说过那么一句话：金钱可以解决我们生活上百分之八九十的问题……乃至于许多人把幸福跟物质挂上钩了：幸福就是有车有房，就是所爱之人同样爱着自己！  倒不是说这个观点一定是错的，只是多少有失偏颇了……金钱确实可以解决生活中的百分之八九十的问题，让一个人的生活少受许多世欲的困扰，但真正决定一个人幸福感的因素却并非物质与金钱可以解决的那百分八九十的问题产生的心理满足，而是余下的金钱与物质无法解决的的百分之10到20之间的内心的贫乏和孤独！   但凡那些过份地看重物质的人，乃至于把物质与金钱直接挂钩的人无外乎内心都是贫乏的。当然这份贫乏跟小时候是否得到来自于父母饱满、持续、无条件的爱有很大的关系。人都是有情感需求的，都希望自己身边的人是爱自己的，可偏偏教育却是一门极其复杂且高深的艺术，而大部分父母要么是文盲，要么就是自身内心亦是非常的贫乏……  好大一部分农村的父母认为：教育嘛，就是吃好喝好穿暖有书读，至于谈到所谓的孩子的感受，或者心理健康那都是一种矫情，敢情在他眼里人好像是没有感情的宠物；  好大一部分父母，他们似乎懂得教育，可他们内心却是贫乏的，长期处于一种歇斯底里的状态……不仅他们没有爱人的能力，相反他们还不自觉地向着自己的孩子，那些根本就没有任何反抗能力的人索取着价值感。   由于内心长期处于歇斯底里的紧绷态——小朋友稍微犯了点错，哪怕是不犯错，只是坐在他们旁边都是一种罪！有那么一些时候，小朋友掉了一个调羹，摔破一个碗，又或者稍微不按他们的意思去做某件事情，他们便是大发雷霆的指责……平和的沟通从来不是一件可能的事情，从来只有暴力沟通……不犯错也会受到莫名的指责——那就是一种迁怒，久而久之孩子就会形成一种近乎颠覆自身的认知：我的存在本身就是一种错误，没有人会喜欢我……这是一种多么强烈，多么可笑，又多么无辜，多么可怕的一种认知啊？   其实自卑倒不是一件多么可怕的事情，顶多在人际关系上不自信——不属于我的东西，美好的来自于父母的关爱，友好的人际关系，我不要了便是——压缩自身的欲望，同样可以让一个人内心平和，脚踏实地——接纳自己的卑微和不足，更好的掌控自己的时间和精力——不焦虑，也不过份恐惧。'
    }
  }
}
</script>

<style scoped>
.class_margin {
  margin-left: 8px;
  margin-right: 8px;
}

.grid-content {
  border-radius: 10px;
  min-height: 20px;
}

.answer_font_name {
  font-size: 15px;
  font-weight: bold;
  color: #444444;
}

.answer_font_resume {
  font-size: 14px;
  color: #333333;
}

.answer_edit_time {
  color: #8590a6;
  font-size: 14px;
}

.answer_content_font {
  font-size: 15px;
  color: #121212;
}

.no_border_outline {
  outline: none;
  border: none;
}
.button_color{
  color: #8590a6;
}
.answer_margin {
  margin-top: 5px;
}
.button_margin_left{
  margin-left: 30px;
}
.icon_size{
  font-size: 15px;
}
</style>
