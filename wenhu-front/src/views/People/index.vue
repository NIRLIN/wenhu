<template>
  <div id="people">

    <el-row class="margin_nav_size">
      <el-col :span="24">
        <div class="grid-content">
          <div class="people_content">
            <PeopleHeader />
            <br>
            <PeopleContent />
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import PeopleHeader from './PeopleHeader'
import PeopleContent from './PeopleContent'

export default {
  name: 'People',
  components: { PeopleContent, PeopleHeader },
  data() {
    return {
      user_id: this.$route.params.id
    }
  },
  created() {
    // console.log(this.user_id)
  }
}
</script>

<style scoped>
#people {
  min-height: 800px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
