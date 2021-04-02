<template>
  <div id="messageItem">
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple">
          <div class="messageItem_time">
            <span>
              {{ send_time }}
            </span>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="1" style="margin-left: 5px;">
        <div class="grid-content bg-purple">
          <el-avatar :src="sender_image_url" />
        </div>
      </el-col>
      <el-col :span="20">
        <div class="grid-content bg-purple">

          <div>
            <div class="bubble">
              <span class="messageItem_content_font">
                <div class="messageItem_content_font">
                  <el-popover
                    placement="right"
                    trigger="hover"
                  >
                    <el-button type="text">删除</el-button>
                    <el-button type="text">举报</el-button>
                    <span slot="reference">
                      {{ send_message_content }}
                    </span>
                  </el-popover>

                </div>
              </span>
            </div>
          </div>

        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="grid-content bg-purple" />
      </el-col>
    </el-row>
  </div>
</template>

<script>

export default {
  name: 'MessageItem',
  data() {
    return {
      sender_image_url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      send_time: '2017',
      send_message_content: '升级后的专栏支持收录更多形式的内容，如回答、视频等；升级后的专栏支持收录更多形式的内容，如回答、视频等；升级后的专栏支持收录更多形式的内容，如回答、视频等；升级后的专栏支持收录更多形式的内容，如回答、视频等；被收录的新内容会自带专栏入口，增加专栏的曝光度；新专栏可以快速创建，审核周期大大缩短，管理专栏的体验也做了升级优化。也欢迎你创建新专栏，体验新功能。',
      data: [
        { label: 'a' },
        { label: 'b' }
      ]

    }
  },
  methods: {
    getCheckedNodes(data) {
      console.log(data)
    }
  }
}
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.messageItem_time {
  text-align: center;
  color: #999999;
  font-size: 12px;
}

.messageItem_content {
  background: #f6f6f6;
  width: 400px;
  border-radius: 5px;
}

.messageItem_content_font {
  font-size: 14px;
  text-align: center;
  margin-left: 10px;
  margin-right: 10px;
}

.bubble {
  width: 350px;
  border-radius: 10px;
  background-color: #f6f6f6;
  position: relative;
  margin-left: 20px;
}

.bubble::before {
  position: absolute;
  top: 10px;
  left: -18px;
  content: '';
  width: 0;
  height: 0;
  border-right: 10px solid #f6f6f6;
  border-bottom: 10px solid transparent;
  border-left: 10px solid transparent;
  border-top: 10px solid transparent;
}

</style>
