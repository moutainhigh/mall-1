<template>
  <div style="width: 100%">
    <div class="i-banner">
      <img src="../assets/img/banner.png">
    </div>

    <div class="i-list" style="margin-top: 0" v-for="(product, index) in products" @click="goToPro(product)">
      <div style="display: inline-block;">
        <img style="height: 85px" :src="product.prodImg">
      </div>
      <div class="i-list-detail">
        <div class="dt-title">{{product.prodName}}</div><!--生命福星高照终身寿险（分红型）-->
        <div class="dt-intro">{{product.description}}</div><!--福相随综合保障计划（2017版）-->
        <div class="dt-content">{{product.tags}}</div><!--爱相伴、心相知、福相随-->
        <div class="dt-price">
          <div>
            <div class="dt-price-pro">
              2万/5万/10万
            </div>
            <button >
             立即投保
            </button>
          </div>
        </div>
      </div>
    </div>
    <!--<div class="i-list">-->
      <!--<div style="display: inline-block;height: 100%;">-->
        <!--<img src="../assets/img/product2.png">-->
      <!--</div>-->
      <!--<div class="i-list-detail">-->
        <!--<div class="dt-title">生命鑫福来年金保险（分红型）</div>-->
        <!--<div class="dt-intro">生命鑫福来年金保险计划</div>-->
        <!--<div class="dt-content">从富贵金生开始美好生活</div>-->
        <!--<div class="dt-price">-->
          <!--<div>-->
            <!--<div class="dt-price-pro">-->
              <!--2万/5万/10万-->
            <!--</div>-->
            <!--<button @click="goToPro(2)">-->
              <!--立即投保-->
            <!--</button>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
    <!--<div><button @click="clear">清除缓存</button></div>-->
  </div>
</template>

<script>
  import storage from '../store/storage'
  import Admin from '../admin/Admin'
  import {getCustomerInfo, getProducts} from "../service/getData";

  export default {
    name: "index",
    data() {
      return {
        products: [],
      }
    },
    methods: {
      goToPro(pro){
        storage.save('packetId', pro.prodId);
        this.$router.push({
          path:'/pro-detail',
          query:{
            id:pro.prodId,
            title:pro.prodName
          }
        });
      },
      clear(){
        storage.clear();
        storage.save('holder',Admin.holder);
        storage.save('insured',Admin.insured);
      }
    },
    created(){
      let query = this.$route.query;
      if (query.token) {
        storage.save('token',query.token);
      }
      if (storage.fetch('holder').length ==0){
        storage.save('holder',Admin.holder);
      }
      if (storage.fetch('insured').length ==0){
        storage.save('insured',Admin.insured);
      }
      this.$vux.loading.show({
        text: 'Loading'
      });
      getProducts().then(res => {
        if (res.result == 'SUCCESS') {
          this.products = res.data;
          this.$vux.loading.hide();
        }
      });
      getCustomerInfo().then(res => {
        if (res.result == 'SUCCESS') {
          let customerData = res.data;
          let holder = storage.fetch("holder");
          holder.policyholderName = customerData.realName;
          holder.policyholderCardType = customerData.cardType;
          holder.policyholderCardNo = customerData.customerCardNo;
          holder.policyholderMobile = customerData.mobile;
          holder.cardPositiveImg = customerData.cardPositiveImg;
          holder.cardNegativeImg = customerData.cardNegativeImg;
          holder.bankCardImg = customerData.bankCardImg;
          storage.save('holder',holder);
        }
      })
    }

  }
</script>

<style scoped>
  button {
    height: 24px;
    background-color: #f5ca1d;
    float: right;
    font-size: 15px;
    color: #ffffff;
    font-weight: normal;
    border: unset;
    position: relative;
    top: 5px;
  }
</style>
