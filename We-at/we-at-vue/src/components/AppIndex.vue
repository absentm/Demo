<template>
  <div id="showHomeContent">
    <IndexHeader></IndexHeader>
    <ul>
      <el-card :body-style="{ padding: '1px' }">
        <div v-for="item in contentList" @click="changeId(item.id)" >
          <div id="text"><p>{{ item.title}}</p></div>
          <div id="image"><img :src="item.images[0]"></div>
        </div>
      </el-card>
    </ul>

  </div>
</template>

<script>
  import IndexHeader from './IndexHeader'

  export default {
    name: 'AppIndex',
    components: {IndexHeader},
    data () {
      return {
        contentList: []
      }
    },
    created () {
      const loading = this.openLoading()
      this.$axios.get('api/data/all/20/2')
        .then(response => {
          loading.close()
          console.log(response.data)
          console.log(response.data['error'])
          console.log(response.data['results'])
          this.contentList = response.data['results']
        })
        .catch(error => {
          console.log(error)
          loading.close()
        })
    },
    methods: {}
  }
</script>


<style scoped>

</style>
