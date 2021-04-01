<template>
  <div>
    <el-row>
      <el-col :span="24"><div class="grid-content ">
        <h3>登录日志</h3>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="10" :offset="6">
        <div class="grid-content ">
          <el-table
            :data="loginLogs"
            style="width: 100%"
          >
            <el-table-column
              prop="createTime"
              label="登录时间"
              width="180"
            />
            <el-table-column
              prop="registerIp"
              label="登录ip"
              width="180"
            />

          </el-table>

        </div>
      </el-col>

    </el-row>

  </div>
</template>

<script>
import { getCookie } from '@/utils/login-status'
import { getLoginLogByUserId } from '@/api/people'

export default {
  name: 'LoginLog',
  props: {
    // eslint-disable-next-line vue/require-default-prop,vue/prop-name-casing
    active_name: String
  },
  data() {
    return {
      loginLogs: [

      ]
    }
  },
  watch: {
    active_name() {
      if (this.active_name === 'loginLog') {
        const submitData = { 'id': getCookie() }
        getLoginLogByUserId(submitData).then((response) => {
          this.loginLogs = response.data
        })
      }
    }

  }
}
</script>

<style scoped>
.grid-content {
  min-height: 20px;
  margin-top: 10px;
}

</style>
