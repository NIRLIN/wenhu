<template>
  <div id="Search">
    <el-row>
      <el-col :span="16" :offset="4"><div class="grid-content" style="background-color: #ffffff">
        <div v-if="answer_list.length===0" style="text-align: center;">
          <span>暂时没有结果哦~</span>
        </div>
        <div v-for="(item,index) in answer_list" :key="index" style="border-top: 1px solid rgb(231, 231, 231);">
          <div class="" style="margin-top: 10px;">
            <el-link :href="'#/question/'+item.questionId" :underline="false">
              <span style="font-size: 16px;font-weight: bold;margin:5px;" v-html="item.title">
                {{ item.title }}
              </span>
            </el-link>
            <Answer :answer_item="item" style="margin-top: 10px;" />
          </div>
        </div>
      </div></el-col>
    </el-row>

  </div>
</template>

<script>
import Answer from '@/components/Answer'
import { getBySearch } from '@/api/creation'
export default {
  name: 'Search',
  components: { Answer },
  data() {
    return {
      answer_list: [],
      search: ''
    }
  },
  watch: {
    $route(to, from) {
      this.search = this.$route.query.search
      console.log(this.$route.query.search)
      this.getSearchMethod()
    }
  },
  created() {
    this.getSearchMethod()
  },
  methods: {
    getSearchMethod() {
      this.search = this.$route.query.search
      getBySearch(this.search).then((response) => {
        this.answer_list = response.data
      })
    }
  }

}
</script>

<style scoped>

</style>
