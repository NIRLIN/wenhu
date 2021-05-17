<template>
  <div id="messageContent">
    <el-row>
      <el-col :span="24">
        <div class="grid-content  messageContent_sender_name">
          <span class="messageContent_sender_name">
            {{ messageList[0].receiveUsername }}
          </span>
        </div>
      </el-col>

    </el-row>
    <el-divider />
    <el-row>
      <el-col :span="24">
        <div class="grid-content ">
          <div class="messageContent_main">
            <div v-for="(item,index) in messageList" :key="index">
              <el-row v-if="userId===item.receiveId">
                <el-col :span="2"><div class="grid-content ">
                  <div style="margin-top: 20px;margin-left: 10px;">
                    <span>
                      <el-avatar :src="item.sendHeadImage" />
                    </span>
                  </div>
                </div></el-col>
                <el-col :span="10"><div class="grid-content ">
                  <div style="background-color: #f6f6f6;margin-top: 25px;margin-left: 10px;">
                    <span>
                      {{ item.content }}
                    </span>
                  </div>
                </div></el-col>
              </el-row>

              <el-row v-if="userId!==item.receiveId">

                <el-col :span="10" :offset="12"><div class="grid-content ">
                  <div style="background-color: #f6f6f6;margin-top: 25px;margin-left: 10px;">
                    <span>
                      {{ item.content }}
                    </span>
                  </div>
                </div></el-col>
                <el-col :span="2"><div class="grid-content ">
                  <div style="width: 50%;margin-top: 20px;margin-left: 10px;">
                    <span>
                      <el-avatar :src="item.sendHeadImage" />
                    </span>
                  </div>
                </div></el-col>
              </el-row>

            </div>

          </div>
        </div>
      </el-col>
    </el-row>
    <el-divider />
    <div style="margin-left: 10px;">
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
            <div style="float: right;margin-top: 10px;">
              <el-button type="primary" @click="sendMessageMethod">发送</el-button>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

  </div>
</template>

<script>
import { getCookie } from '@/utils/login-status'
import { getHeadImageByUserId, saveMessage } from '@/api/people'
import { Message } from 'element-ui'

export default {
  name: 'MessageContent',
  props: {
    // eslint-disable-next-line vue/require-default-prop
    messageList: Array,
    // eslint-disable-next-line vue/require-default-prop
    receiveId: String
  },
  data() {
    return {
      sender_name: '用户姓名',
      userId: getCookie(),
      head_image_url: '',
      html: '',
      send_message: ''

    }
  },
  created() {
    if (getCookie() !== undefined) {
      const submitData = { 'userId': getCookie() }
      getHeadImageByUserId(submitData).then((response) => {
        this.head_image_url = response.data
      })
    }
  },
  methods: {
    sendMessageMethod() {
      if (getCookie() !== undefined) {
        if (this.send_message === '') {
          Message.warning({
            message: '请输入内容~',
            center: true
          })
          return
        }
        const submitData = { 'userId': getCookie(), 'sendMessage': this.send_message, 'receiveId': this.receiveId }
        saveMessage(submitData).then((response) => {
          const tmp = { 'sendHeadImage': this.head_image_url, 'content': this.send_message }
          this.messageList.push(tmp)
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    }
  }
}
</script>

<style scoped>
#messageContent {
  background: rgb(255, 255, 255);
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
