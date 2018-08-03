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
  export default {
    name: 'App',
    created() {
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
