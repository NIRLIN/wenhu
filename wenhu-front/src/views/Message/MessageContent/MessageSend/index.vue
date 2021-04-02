<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
          <label>
            <el-input
              v-model="send_message"
              type="textarea"
              :rows="4"
              placeholder="请输入内容"
              class="message_input_text"
            />

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
export default {
  name: 'MessageSend',
  data() {
    return {
      send_message: ''
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 8px;
  min-height: 10px;
}
.message_input_text {
  width: 98%;
  border: none;
  resize: none;
  cursor: pointer;
  margin-left: 1%;
  margin-right: 1%;
}

.send_button {
  margin-left: 91%;
  margin-top: 10px;
}
</style>
