<template>
  <div id="Message">
    <el-card>
      <el-row>
        <el-col :span="22" :offset="1">
          <div class="grid-content ">
            <el-tabs v-model="editableTabsValue" tab-position="left" type="card" closable @tab-remove="removeTab">
              <el-tab-pane
                v-for="(item, index) in editableTabs"
                :key="index"
                :label="item.receiveUsername"
                :name="item.id"
              >
                <MessageContent :receive-id="item.receiveId" :message-list="item.messageList" style="border-left: solid 1px #b0b5c2;" />
              </el-tab-pane>
            </el-tabs>
          </div>
        </el-col>
      </el-row>

    </el-card>
  </div>
</template>

<script>

import MessageContent from '@/views/Message/MessageContent'
import { getCookie } from '@/utils/login-status'
import { userMessageList } from '@/api/people'
import { Message } from 'element-ui'

export default {
  name: 'Message',
  components: { MessageContent },
  data() {
    return {
      editableTabsValue: '1',
      editableTabs: []
    }
  },
  created() {
    this.userMessageListMethod()
  },
  methods: {
    userMessageListMethod() {
      if (getCookie() !== undefined) {
        const submitData = 'userId=' + getCookie()
        console.log(submitData)
        userMessageList(submitData).then((response) => {
          this.editableTabs = response.data
        })
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    },
    removeTab(targetName) {
      const tabs = this.editableTabs
      let activeName = this.editableTabsValue
      if (activeName === targetName) {
        tabs.forEach((tab, index) => {
          if (tab.name === targetName) {
            const nextTab = tabs[index + 1] || tabs[index - 1]
            if (nextTab) {
              activeName = nextTab.name
            }
          }
        })
      }
      this.editableTabsValue = activeName
      this.editableTabs = tabs.filter(tab => tab.name !== targetName)
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
