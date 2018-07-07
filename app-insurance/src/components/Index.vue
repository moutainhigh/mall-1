<template>
  <div style="width: 100%">
    <div class="i-banner">
      <img src="../assets/img/banner.png">
    </div>

    <div class="i-list" style="margin-top: 0">
      <div style="display: inline-block;">
        <img src="../assets/img/product1.png">
      </div>
      <div class="i-list-detail">
        <div class="dt-title">生命福星高照终身寿险（分红型）</div>
        <div class="dt-intro">福相随综合保障计划（2017版）</div>
        <div class="dt-content">爱相伴、心相知、福相随</div>
        <div class="dt-price">
          <div>
            <div class="dt-price-pro">
              2万/5万/10万
            </div>
            <button @click="goToPro(1)">
             立即投保
            </button>
          </div>
        </div>
      </div>
    </div>
    <div class="i-list">
      <div style="display: inline-block;height: 100%;">
        <img src="../assets/img/product2.png">
      </div>
      <div class="i-list-detail">
        <div class="dt-title">生命尊养无忧老年防癌疾病保险</div>
        <div class="dt-intro">老年专属防癌险</div>
        <div class="dt-content">给爸妈一个安心的晚年</div>
        <div class="dt-price">
          <div>
            <div class="dt-price-pro">
              ￥20000.00元
            </div>
            <button @click="goToPro(2)">
              立即投保
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  // import {testGet,sendMobile} from "../service/getData";
  import storage from '../store/storage'

  export default {
    name: "index",
    methods: {
      goToPro(val){
        if (val === 1){
          this.$router.push({
            path:'pro-detail',
            query: {
              id:val,
              title:"生命福星高照终身寿险（分红型）",
            }
          });
        }

        if (val === 2){
          this.$router.push({
            path:'/pro-detail',
            query:{
              id:val,
              title:"生命尊养无忧老年防癌疾病保险"
            }
          });
        }
      }
    },
    created(){
      let query = this.$route.query;
      storage.save('token',query.token);
    }
  }
</script>

<style scoped>
  button {
    height: 24px;
    background-color: #e1bb3a;
    float: right;
    font-size: 15px;
    color: #ffffff;
    font-weight: normal;
    border: unset;
    position: relative;
    top: 5px;
  }
</style>
