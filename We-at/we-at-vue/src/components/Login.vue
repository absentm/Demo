<el-card>
  <div>
    User name: <input type="text" v-model="loginForm.username" placeholder="Please input your name">
    <br><br>
    User Password: <input type="text" v-model="loginForm.password" placeholder="Please input your password">
    <br><br>
    <button v-on:click="login">Login</button>
  </div>

</el-card>

<script>
export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      this.$axios
        .post('/login', {
          username: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(successResponse => {
          if (successResponse.data.code === 200) {
            this.$router.replace({path: '/index'})
          }
        }).catch(failResponse => {

      })
    }
  }
}
</script>

<style scoped>

</style>
