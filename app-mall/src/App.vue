<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <!--<keep-alive>-->
      <!--<router-view/>-->
    <!--</keep-alive>-->
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    <router-view v-if="!$route.meta.keepAlive"></router-view>
    <lg-preview></lg-preview>
  </div>
</template>

<script>
  import storage from "./store/storage";
  import {TOKEN} from "./config/constant";

  export default {
    name: 'App',
    created() {
      let query = this.$route.query;
      if (query.token) {
        storage.saveSession(TOKEN,query.token);
      }
      if (!window.navigator.onLine) {
        alert("网络未连接");
      }
      window.addEventListener('offline', function () {
        alert("网络未连接");
      });
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #333333;
  }

</style>
