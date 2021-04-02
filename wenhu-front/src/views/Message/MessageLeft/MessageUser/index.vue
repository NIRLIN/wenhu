<template>
  <div class="MessageUser">
    <el-row>
      <el-col>

        <div class="grid-content">

          <div style="float: left;">
            <el-avatar :src="head_image_url" />
          </div>
          <div style="float: left;margin-left: 10px;">
            <div>
              <span style="font-size: 18px;">
                {{ user_name }}
              </span>
            </div>
            <div>
              <span style="font-size: 14px;color: #8590a6">
                {{ update_time }}
              </span>
            </div>
          </div>

        </div>

      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: 'MessageUser',
  data() {
    return {
      head_image_url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      user_name: '用户姓名',
      update_time: '2021年4月2日10:14:07'
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 20px;
  margin-bottom: 20px;
}

</style>
