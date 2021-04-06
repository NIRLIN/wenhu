<template>
  <el-card>
    <div v-if="articleBool">
      <Article :answer_item="article" />
    </div>
    <div v-if="!articleBool">
      <NotFound />
    </div>
  </el-card>
</template>

<script>
import Article from '@/components/Article'
import { getArticleByArticleId } from '@/api/answer'
import NotFound from '@/views/NotFound'

export default {
  name: 'ArticleView',
  components: { NotFound, Article },
  data() {
    return {
      agreeButtonBool: false,
      articleBool: false,
      article: ''
    }
  },
  created() {
    const submitData = { 'articleId': this.$route.params.id }
    getArticleByArticleId(submitData).then((response) => {
      this.article = response.data
      this.articleBool = true
    })
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

.article_font_name {
  font-size: 15px;
  font-weight: bold;
  color: #444444;
}

.article_font_resume {
  font-size: 14px;
  color: #333333;
}

.article_edit_time {
  color: #8590a6;
  font-size: 14px;
}

.article_content_font {
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
.article_margin {
  margin-top: 5px;
}
.button_margin_left{
  margin-left: 30px;
}
.icon_size{
  font-size: 15px;
}
</style>
