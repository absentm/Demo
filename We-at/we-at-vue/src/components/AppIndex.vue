<template>
  <div id="showHomeContent">
    <IndexHeader></IndexHeader>
    <div v-for="item in contentList">
      <el-card :body-style="{ padding: '1px' }">
        <el-link id="item_title" :href="item.url" target="_blank">
          <p><strong>
            <span id="type" v-if="item.type === 'iOS'"><i class="el-icon-apple"></i></span>
            <span v-else-if="item.type === 'Android'"><i class="el-icon-cherry"></i></span>
            <span v-else-if="item.type === '前端'"><i class="el-icon-orange"></i></span>
            <span v-else-if="item.type === '瞎推荐'"><i class="el-icon-grape"></i></span>
            <span v-else-if="item.type === 'App'"><i class="el-icon-watermelon"></i></span>
            <span v-else><i class="el-icon-pear"></i></span>
            {{ item.desc }}
          </strong></p>
        </el-link>

        <div id="item_content">
          <span id="create_time"><i class="el-icon-data-board"></i>
            <el-link type="info">{{ item.createdAt | dateFrm}}</el-link>
          </span>
          <span id="update_time"><i class="el-icon-data-line"></i>
            <el-link type="info">{{ item.publishedAt | dateFrm}}</el-link>
          </span>
          <span id="who"><i class="el-icon-user"></i>
            <el-link type="info">{{ item.who}}</el-link>
          </span>
        </div>
      </el-card>
    </div>
    <el-divider></el-divider>
    <el-pagination
      @current-change="handleCurrentChange"
      :page-size="10"
      layout="prev, pager, next, jumper"
      :total="9500">
    </el-pagination>
    <el-divider></el-divider>
  </div>
</template>

<script>
  import IndexHeader from './IndexHeader'

  var moment = require('moment')

  export default {
    name: 'AppIndex',
    components: {IndexHeader},
    data () {
      return {
        contentList: []
      }
    },
    created () {
      this.getDataList(1)
    },
    methods: {
      getDataList: function (pageNum) {
        const loading = this.openLoading()
        this.$axios.get('api/data/all/10/' + pageNum.toString())
          .then(response => {
            loading.close()
            console.log(response.data)
            this.contentList = response.data['results']
          })
          .catch(error => {
            console.log(error)
            loading.close()
          })
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getDataList(`${val}`)
      }
    },
    filters: {
      dateFrm: function (dateValue) {
        let localTime = moment.utc(dateValue).toDate()
        return moment(localTime).format('YYYY-MM-DD HH:MM:DD')
      }
    }
  }
</script>


<style scoped>
  ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 0em;
    margin-block-end: 0em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 0px;
  }

  span {
    font-size: smaller;
  }

  i {
    margin-right: 8px;
  }

  el-pagination {
    margin-top: 32px;
    margin-bottom: 32px;
  }

  #item_title {
    font-size: large;
    margin-left: 16px
  }

  #item_content {
    margin-left: 16px;
    margin-bottom: 16px;
    margin-top: 16px
  }

  #who, #update_time {
    margin-left: 48px;
  }

</style>
