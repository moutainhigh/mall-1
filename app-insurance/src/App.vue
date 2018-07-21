<template>
  <div id="app" style="overflow: hidden">
    <!--<img src="./assets/logo.png">-->
    <router-view/>
  </div>
</template>

<script>
  import storage from "./store/storage";
  import {setBase} from "./config/env";


  export default {
    name: 'App',
    watch: {
      $route:function(to,from){
        document.body.scrollTop = 0;
      }
    },
    created:function () {
      this.loadJson();
      console.log(process.env.VERSION);
      // alert(process.env.VERSION);
      if (storage.fetch('version').length == 0 || storage.fetch('version') != process.env.VERSION) {
        storage.clear();
      }
      if (storage.fetch('holder').length === 0){
        storage.save('holder',this.Admin.holder);
      }
      if (storage.fetch('insured').length === 0) {
        storage.save('insured', this.Admin.insured);
      }
    },
    methods:{
      async loadJson(){
        await this.$http.get('./static/config.json').then((res) => {
          setBase(res.data.baseUrl);
        });
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
