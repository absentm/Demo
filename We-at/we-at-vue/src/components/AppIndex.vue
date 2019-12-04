<template>
  <div id="showHomeContent">
    <IndexHeader></IndexHeader>
    <el-card :body-style="{ padding: '0px' }">
      <img style="align-right" src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
      <div style="padding: 14px;">
        <span>好吃的汉堡</span>
        <div class="bottom clearfix">
          <time class="time">{{ currentDate }}</time>
          <el-button type="text" class="button">操作按钮</el-button>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  import IndexHeader from './IndexHeader'
  export default {
    name: 'AppIndex',
    components: {IndexHeader}
  }
</script>


<style scoped>

</style>
