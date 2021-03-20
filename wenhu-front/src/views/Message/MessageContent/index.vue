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

    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
          <label>
            <textarea class="message_input_text" placeholder="" />
          </label>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
          <div class="send_button">
            <el-button type="primary">发送</el-button>

          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MessageItem from './MessageItem'

export default {
  name: 'MessageContent',
  components: { MessageItem },
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
}

.grid-content {
  border-radius: 8px;
  min-height: 36px;
}

.messageContent_sender_name {
  font-weight: bold;
  font-size: 15px;
  color: #121212;
  text-align: center;
}

.messageContent_main {
  height: 400px;
  overflow-y: auto;
}

.message_input_text {
  width: 90%;
  height: 50px;
  border: none;
  resize: none;
  cursor: pointer;
  margin-top: 20px;
}

.send_button {
  margin-left: 80%;
}
</style>
