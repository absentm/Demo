<template>
  <div id="showHomeContent">
    <IndexHeader></IndexHeader>
  </div>
</template>

<script>
  import IndexHeader from './IndexHeader'

  export default {
    name: 'CategoryIndex',
    components: {IndexHeader},
    data () {
      return {
        contentList: []
      }
    },
    created () {
    },
    methods: {}
  }
</script>


<style scoped>

</style>
