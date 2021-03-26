<template>
  <div id="Navbar">
    <el-menu :default-active="activeIndex" class="el-menu-demo " mode="horizontal" @select="handleSelect">
      <el-menu-item v-show="false" index="100">
        <el-link :underline="false" href="#/hot">隐藏item</el-link>
      </el-menu-item>
      <el-menu-item class="logo_margin_left">
        <el-link :underline="false" href="#/hot">
          <svg style="height: 60px;width: 90px;">
            <text fill="rgb(0, 0, 0)" font-family="Adobe Heiti Std" font-size="45px" kerning="auto" x="0px" y="40px">
              <tspan fill="#0066FF" font-family="LiSu" font-size="45px">&#38395;&#20046;</tspan>
            </text>
          </svg>
        </el-link>
      </el-menu-item>
      <el-menu-item class="hot_margin_left" index="1">
        <el-link :underline="false" href="#/hot">首页</el-link>
      </el-menu-item>
      <el-menu-item index="2">发现</el-menu-item>
      <el-menu-item index="3">更多</el-menu-item>
      <el-menu-item class="input_width">
        <el-input
          v-model="header_search_value"
          class="header_search_input"
          placeholder="请输入内容"
          size="medium"
          suffix-icon="el-icon-search"
        />
      </el-menu-item>
      <el-menu-item v-if="is_login">
        <div class="grid-content bg-purple">
          <el-link :underline="false" href="#/quiz">
            <el-button round size="medium" type="primary">提问</el-button>
          </el-link>
        </div>
      </el-menu-item>
      <el-menu-item v-if="is_login" class="message_margin_left">
        <div class="grid-content bg-purple">
          <el-link :underline="false" href="#/message">
            <el-button class="header_message" type="text">
              <el-badge :value="1" class="item" type="primary">
                <i class="el-icon-message header_message_size" />
              </el-badge>
            </el-button>
          </el-link>
        </div>
      </el-menu-item>
      <el-menu-item v-if="is_login" class="home_margin_left">
        <div class="grid-content bg-purple">
          <el-dropdown placement="bottom" trigger="click">
            <span class="el-dropdown-link">
              <el-avatar shape="square" size="large">
                <img :src="head_image_url" alt="">
              </el-avatar>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-link :underline="false" href="#/setting">
                  <el-link :href="people_url" :underline="false">我的主页</el-link>
                </el-link>
              </el-dropdown-item>
              <el-dropdown-item>创作中心</el-dropdown-item>
              <el-dropdown-item>
                <el-link :underline="false">
                  <el-link :underline="false" href="#/setting">设置</el-link>
                </el-link>
              </el-dropdown-item>
              <el-link :underline="false" @click="logout">
                <el-dropdown-item>
                  退出
                </el-dropdown-item>
              </el-link>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-menu-item>
      <el-menu-item v-if="!is_login" class="login_margin_left">
        <el-row>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <el-link :underline="false" href="#/login">
                <el-button plain type="primary">登录</el-button>
              </el-link>
            </div>
          </el-col>
        </el-row>
      </el-menu-item>
      <el-menu-item v-if="!is_login">
        <el-row>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <el-link :underline="false" href="#/register">
                <el-button type="primary">注册</el-button>
              </el-link>
            </div>
          </el-col>
        </el-row>

      </el-menu-item>
    </el-menu>

  </div>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import { getCookie, setCookie, removeCookie } from '@/utils/login-status'
import { Message } from 'element-ui'

export default {
  name: 'Navbar',
  props: {
    // eslint-disable-next-line vue/prop-name-casing,vue/require-default-prop
    home_no: Number,
    // eslint-disable-next-line vue/prop-name-casing,vue/require-default-prop
    home_url: Array
  },
  data() {
    return {
      activeIndex: '1',
      header_search_value: '',
      head_image_url: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      is_login: 0,
      user_id: '123456',
      people_url: '#/people/' + this.user_id
    }
  },
  watch: {
    home_no: function() {
      if (this.home_no === 0) {
        this.activeIndex = '100'
      } else {
        this.activeIndex = '' + this.home_no
      }
    }
  },

  created: function() {
    this.getUrlIndex()

    // setCookie(this.user_id)
    const cookie = getCookie()
    // console.log(typeof cookie)
    // console.log(cookie)
    if (cookie) {
      this.is_login = 1
      this.user_id = getCookie()
      this.people_url = '#/people/' + this.user_id
    }
    if (!cookie) {
      this.is_login = 0
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    },
    getUrlIndex() {
      let tmp = 0
      for (let i = 0; i < this.home_url.length; i++) {
        if (this.home_url[i] === this.key) {
          tmp = 1
          break
        } else {
          tmp = 0
        }
      }
      if (tmp === 0) {
        this.activeIndex = '100'
      } else {
        this.activeIndex = '' + tmp
      }
    },
    logout: function() {
      removeCookie()
      Message.success('退出成功')
      setTimeout(() => {
        this.$router.go(0)
      }, 1010)
      setTimeout(() => {
        this.$router.push('hot')
      }, 1000)
    }
  }
}
</script>

<style scoped>
.header_search_input {
  width: 100%;
}

.header_message {
  outline: none;
  border: none;
}

.input_width {
  width: 20%;
  margin-left: 2%;
}

.logo_margin_left {
  margin-left: 20%;
}

.message_margin_left {
  margin-left: 6%;
}

.login_margin_left {
  margin-left: 6%;
}

.hot_margin_left {
  margin-left: 2%;
}

.header_message_size {
  font-size: 30px;
  color: #606266;
}

.home_margin_left {
  margin-left: 1%;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
