<template>
  <div id="app">
     <router-view />
  </div>
</template>

<script>


export default {
  name: 'App',
  components: {

  },
  data() {
    // this.url=url
    return {
    };
  },

  methods: {

  },
  mounted:function(){
    // this.$store.commit('clear')
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
</style>
