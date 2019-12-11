<template>
  <div id="showHomeContent">
    <IndexHeader></IndexHeader>
    <div v-html="aboutHtml"></div>
  </div>
</template>

<script>
  import IndexHeader from './IndexHeader'
  import showdown from 'showdown'
  import about from '../assets/md/about.md'

  console.log(about)

  export default {
    name: 'AboutIndex',
    components: {IndexHeader, showdown},
    data () {
      return {
        md: about,
        aboutHtml: '',
        markdownOption: {
          bold: true
        }
      }
    },
    mounted () {
      let converter = new showdown.Converter()
      let text = this.md.toString()
      this.aboutHtml = converter.makeHtml(text)
    },
    created () {
    },
    methods: {}
  }
</script>


<style>
  @import "../assets/markdown.css";
</style>
