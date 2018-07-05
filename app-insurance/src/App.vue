<template>
  <div id="app" style="overflow: hidden">
    <!--<img src="./assets/logo.png">-->
    <router-view/>
  </div>
</template>

<script>
  import storage from "./store/storage";


  export default {
    name: 'App',
    watch: {
      $route:function(to,from){
        document.body.scrollTop = 0;
      }
    },
    created:function () {
      if (storage.fetch('holder').length === 0){
        let holder = this.Admin.holder;
        storage.save('holder',holder);
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
  }
  /*@import url("../static/default.css");*/
</style>
