<template>
  <el-menu
    :default-active="activeIndex2"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-menu-item index="1">AbsentM's Notes</el-menu-item>
    <el-submenu index="2">
      <template slot="title"><i class="el-icon-s-home"></i>Home</template>
      <el-menu-item index="2-1">Index1</el-menu-item>
      <el-menu-item index="2-2">Index2</el-menu-item>
      <el-menu-item index="2-3">Index3</el-menu-item>
    </el-submenu>
    <el-menu-item index="3"><i class="el-icon-s-grid"></i>Categories</el-menu-item>
    <el-menu-item index="4"><i class="el-icon-s-cooperation"></i>Archives</el-menu-item>
    <el-menu-item index="5"><i class="el-icon-s-flag"></i>Tags</el-menu-item>
    <el-menu-item index="6"><i class="el-icon-search"></i>Search</el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    name: 'IndexHeader',
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>


<style scoped>

</style>
