<template>
  <div class="messageLeft_main">
    <el-row>
      <el-col>
        <div style="margin-bottom: 10px;">
          <span style="font-size: 18px;font-weight: bold;">
            最近联系
          </span>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col>

        <div v-for="(item,index) in items" :key="index" style="border-top: 1px solid rgb(231, 231, 231);">
          <div style="margin-top: 15px;margin-bottom: 15px;">
            <el-popover
              placement="right"
              trigger="hover"
            >
              <el-button type="text">删除</el-button>
              <el-button type="text">举报</el-button>
              <div slot="reference">
                <MessageUser />
              </div>
            </el-popover>
          </div>

        </div>
      </el-col>
    </el-row>

  </div>

</template>

<script>
import MessageUser from '@/views/Message/MessageLeft/MessageUser'
export default {
  name: 'MessageLeft',
  components: { MessageUser },
  data() {
    return {
      items: ['a', 'b', 'c', 'b', 'c', 'b', 'c', 'b', 'c', 'b', 'c', 'b', 'c', 'b', 'c', 'b', 'c', 'b', 'c']
    }
  }
}
</script>

<style scoped>
.messageLeft_main {
  height: 800px;
  overflow-y: auto;
}
</style>
