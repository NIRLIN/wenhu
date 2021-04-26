<template>
  <div class="main_min_width">
    <el-row class="nav_margin_bottom">
      <el-col :span="24">
        <div class="grid-content ">
          <Navbar :home_no="isInHome" :home_url="homeUrl" />
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5">
        <div class="grid-content " />
      </el-col>
      <el-col :span="14">
        <div class="grid-content">
          <router-view :key="key" />
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content " />
      </el-col>
    </el-row>
    <el-backtop />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'

export default {
  name: 'Main',
  components: { Navbar },
  data() {
    return {
      homeUrl: [
        '/hot',
        '/follow',
        '/recommend'
      ],
      isInHome: 0
    }
  },
  computed: {
    key() {
      return this.$route.path
    }
  },
  watch: {
    key: function() {
      this.getUrlIndex()
    }
  },
  methods: {
    getUrlIndex() {
      let tmp = 0
      for (let i = 0; i < this.homeUrl.length; i++) {
        if (this.homeUrl[i] === this.key) {
          tmp = 1
          break
        } else {
          tmp = 0
        }
      }
      this.isInHome = tmp
    }

  }

}
</script>

<style scoped>
.grid-content {
  min-height: 8px;
  border-radius: 50px;
}

.main_min_width {
  min-width: calc(180vh);
}

.nav_margin_bottom {
  margin-bottom: 10px;
}
</style>
