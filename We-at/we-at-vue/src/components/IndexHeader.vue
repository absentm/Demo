<template>
  <el-menu
    :default-active="$route.path"
    router
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-menu-item index="/">AbsentM's Notes</el-menu-item>
    <el-menu-item index="/home"><i class="el-icon-s-home"></i>Home</el-menu-item>
    <el-menu-item index="/category"><i class="el-icon-s-grid"></i>Categories</el-menu-item>
    <el-menu-item index="/archives"><i class="el-icon-s-cooperation"></i>Archives</el-menu-item>
    <el-menu-item index="/tags"><i class="el-icon-s-flag"></i>Tags</el-menu-item>
    <el-menu-item index="/about"><i class="el-icon-user-solid"></i>About</el-menu-item>
    <!--    <el-menu-item>-->
    <el-input id="headerInput" placeholder="Please input your search content" v-model="search_input">
      <el-button slot="append" icon="el-icon-search"></el-button>
    </el-input>
    <!--    </el-menu-item>-->
  </el-menu>
</template>

<script>
  export default {
    name: 'IndexHeader',
    data () {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        search_input: ''
      }
    },
    methods: {
      handleSelect (key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>


<style sloped>
  .el-input {
    width: 400px;
    align-content: center;
    margin-left: 32px;
    margin-top: 10px;
    vertical-align: center;
  }
</style>
