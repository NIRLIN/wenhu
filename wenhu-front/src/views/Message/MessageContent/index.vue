<template>
  <div id="messageContent">
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple messageContent_sender_name">
          <span class="messageContent_sender_name">
            {{ sender_name }}
          </span>
        </div>
      </el-col>

    </el-row>
    <el-divider />

    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
          <div class="messageContent_main">
            <MessageItem />
            <MessageItem />
          </div>
        </div>
      </el-col>
    </el-row>
    <el-divider />
    <MessageSend />
  </div>
</template>

<script>
import MessageItem from './MessageItem'
import MessageSend from '@/views/Message/MessageContent/MessageSend'

export default {
  name: 'MessageContent',
  components: { MessageSend, MessageItem },
  data() {
    return {
      sender_name: '用户姓名',
      content: '',
      html: ''
    }
  },
  methods: {}
}
</script>

<style scoped>
#messageContent {
  background: rgb(255, 255, 255);
  margin-top: -20px;
}

.grid-content {
  border-radius: 8px;
  min-height: 10px;
}

.messageContent_sender_name {
  font-weight: bold;
  font-size: 20px;
  color: #121212;
  text-align: center;
  margin-top: 15px;
}

.messageContent_main {
  height: 520px;
  overflow-y: auto;
}
</style>
