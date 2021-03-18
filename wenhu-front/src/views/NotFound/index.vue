<template>
  <div id="notFound">
    <el-row>
      <el-col :span="8">
        <div class="grid-content"/>
      </el-col>
      <el-col :span="8">
        <div class="grid-content">
        <span class="notFoundNumber">
          404
        </span>
          <br>
          <br>
          <span class="notFoundFont">
          你似乎来到了没有知识存在的荒原
        </span>
          <br>
          <br>
          <el-link :underline="false" href="#/hot">
            <el-button type="primary">去往首页</el-button>
          </el-link>
          <br>
          <br>
          <el-button class="goBeforePage" type="text" @click="prev">或者 返回上页 <i class="el-icon-arrow-right"/></el-button>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content">
          <img src="@/assets/svg/liukanshan_wire.svg">
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'NotFound',
  methods: {
    prev() {
      this.$router.go(-1)
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  /*background: #d3dbee;*/
  margin-top: 40%;
}

.notFoundNumber {
  font-size: 40px;
  color: rgb(100, 100, 100);
  font-weight: bold;
}

.notFoundFont {
  font-size: 18px;
  color: #646464;
}

.goBeforePage {
  color: #0084ff;
  font-weight: bold;
  font-size: 14px;
}
</style>
