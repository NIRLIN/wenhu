<template>
  <div id="peopleHeader">
    <el-row>
      <el-col :span="4">
        <div class="grid-content ">
          <div id="peopleHeader_image">
            <el-image
                :src="peopleHeader_image_url"
                fit="fill"
                style="width: 150px; height: 150px"
            />
          </div>
        </div>
      </el-col>
      <el-col :span="14">
        <div class="grid-content ">
          <div id="peopleHeader_user_name">
            <span>{{ peopleHeader_user_name }}</span>
          </div>
          <br>
          <div id="peopleHeader_user_resume">
            <span class="peopleHeader_user_resume_name">个人简介&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <span>{{ peopleHeader_user_resume }}</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="grid-content ">
          <div id="follow_message">
            <br>
            <br>
            <br>
            <br>
            <br>
            <el-button class="button_width" type="info">关注</el-button>
            <el-button class="button_width" plain type="info">发私信</el-button>
            <br>
            <br>

          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'PeopleHeader',
  data() {
    return {
      peopleHeader_image_url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      peopleHeader_user_name: 'NIRLIN',
      peopleHeader_user_resume: '人生达命岂暇愁，且饮美酒登高楼。',
      activeName: 'second'
    }
  }
}
</script>

<style scoped>
#peopleHeader {
  background: rgb(255, 255, 255);
  min-height: 100px;
}

#peopleHeader_user_name {
  font-size: 26px;
  font-weight: bold;
}

.peopleHeader_user_resume_name {
  font-weight: bold;
}

#peopleHeader_user_resume {
  font-size: 14px;
}

.button_width {
  width: 100px;
}

.grid-content {
  border-radius: 4px;
  min-height: 30px;
}

</style>
