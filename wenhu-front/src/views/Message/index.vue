<template>
  <div id="Message">
    <el-card>
      <el-container>
        <el-aside width="260px">
          <MessageLeft />
        </el-aside>
        <el-main style="border-left: 1px solid rgb(231, 231, 231);">
          <MessageContent />
        </el-main>
      </el-container>
    </el-card>
  </div>
</template>

<script>

import MessageLeft from '@/views/Message/MessageLeft'
import MessageContent from '@/views/Message/MessageContent'
export default {
  name: 'Message',
  components: { MessageContent, MessageLeft }
}
</script>

<style scoped>

</style>
