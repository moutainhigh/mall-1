<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <router-view/>
  </div>
</template>

<script>
  import storage from "./store/storage";

  if (storage.fetch('holder').length === 0){
    let holder = {
      policyholderName: '',
      policyholderGender: true,
      policyholderBirthday:'',
      policyholderCareer:'',
      policyholderCardType:[],
      policyholderCardNo:'',
      policyholderCountry:'',
      policyholderCardPeroid:'',
      policyholderHeight:'',
      policyholderBodyWeight:'',
      policyholderIncome:'',
      policyholderMarriage:[],
      policyholderTel:'',
      policyholderMobile:'',
      policyholderEmail:'',
      policyholderProvince:'',
      policyholderCity:'',
      policyholderDistrict:'',
      policyholderAddress:'',
      policyholderTaxRelated:[],
      policyholderSign:'',
      cardPositiveImg:'',
      cardNegativeImg:'',
      documentNum:'',
      marital:[]
    };
    storage.save('holder',holder);
  }
  export default {
    name: 'App',
    watch: {
      $route:function(to,from){
        document.body.scrollTop = 0;
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    /*text-align: center;*/
    color: #2c3e50;
    /*margin-top: 60px;*/
  }

  /*@import url("../static/default.css");*/
</style>
