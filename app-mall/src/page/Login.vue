<template>
  <div>
    <div>
      <span>账号</span>
      <input v-model="accountName"/>
    </div>
    <div>
      <span>密码</span>
      <input v-model="password"/>
    </div>
    <button @click="login">登录</button>
  </div>
</template>

<script>
  import Fetch from "../config/fetch"
  import {loginByPwd} from "../service/getData";
  import storage from "../store/storage";

    export default {
        name: "Login",
        data(){
          return {
            accountName:'13670150690',
            password:'12345678'
          }
        },
        methods:{
          login(){
            let _this = this;
            let accountName = this.accountName;
            let password = this.password;
            Fetch("http://192.168.0.43:8158/api/noAuth/loginByPwd.do?accountName="+accountName + "&password="+password,{},"POST",'fetch').then(res=>{
              if (res.result == 'SUCCESS') {
                storage.saveSession('token',res.data.token);
              };
            });
          }
        },
      created(){
        Fetch("http://192.168.0.43:8159/mb-api/v1/mall/index/getIndex.do",{},"GET").then(res=>{
          console.log(res);
        });
      }
    }
</script>

<style scoped>

</style>
