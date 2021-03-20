<template>
  <div id="people">

    <el-row class="margin_nav_size">
      <el-col :span="24">
        <div class="grid-content">
          <div class="people_content">
            <people-header />
            <br>
            <people-content />
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
      a: this.$route.params.id
    }
  }
}
</script>

<style scoped>
#people {
  background: rgb(246, 246, 246);
  min-height: 800px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

</style>
