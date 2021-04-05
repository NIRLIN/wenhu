<template>
  <div>
    <el-row>
      <el-col :span="24"><div class="grid-content ">
        <h3>主页管理</h3>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="5" :offset="9">
        <div class="grid-content ">
          <el-upload
            ref="upload"
            class="avatar-uploader"
            :show-file-list="true"
            list-type="picture"
            action=""
            :limit="1"
            :auto-upload="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :http-request="uploadMethod"
          >
            <img :src="headImage" alt="" class="avatar">
          </el-upload>
        </div>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="12" :offset="6">
        <div class="grid-content ">
          <el-button style="width: 90%;" type="primary" @click="submitUpload">确认更换</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getCookie } from '@/utils/login-status'
import { getHeadImageByUserId, saveHeadImageByUserId } from '@/api/people'
import { Message } from 'element-ui'

export default {
  name: 'ChangeHeadImage',
  props: {
    // eslint-disable-next-line vue/require-default-prop,vue/prop-name-casing
    active_name: String
  },
  data() {
    return {
      headImage: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
    }
  },
  watch: {
    active_name() {
      if (getCookie() !== undefined) {
        if (this.active_name === 'headImage') {
          const submitData = { 'userId': getCookie() }
          getHeadImageByUserId(submitData).then((response) => {
            this.headImage = response.data
          })
        }
      } else {
        Message.success({
          message: '请登录哦~',
          center: true
        })
      }
    }
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.headImage = URL.createObjectURL(file.raw)
      console.log(URL.createObjectURL(file.raw))
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isLt2M
    },
    uploadMethod(file) {
      const submitData = new FormData()
      submitData.append('userId', getCookie())
      submitData.append('image', file.file)
      saveHeadImageByUserId(submitData).then((response) => {
        this.headImage = response.data
        Message.success({
          message: '头像修改成功~',
          center: true
        })
        this.$refs.upload.clearFiles()
      })
    },
    submitUpload() {
      this.$refs.upload.submit()
    }
  }
}
</script>

<style scoped>
.grid-content {
  min-height: 20px;
  margin-top: 10px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 100%;
  height: 100%;
  display: block;
}
</style>
