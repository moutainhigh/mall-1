<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>
    <div class="tab">
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 1}" @click="select(1)">全部</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 2}" @click="select(2)">待付款</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 3}" @click="select(3)">待收货</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 4}" @click="select(4)">已完成</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 5}" @click="select(5)">已取消</p></div>
    </div>

    <div class="user-order-list">
      <dl>
        <dt style="border-top: 0;padding-left: 0.875rem">
          <span class="orderNo">订单编号：1549856</span>
          <span class="orderState">等待付款</span>
        </dt>
        <dd class="myorderList">
          <img class="imgs" src="../../assets/logo.png">
          <div>
            <p style="white-space: normal; font-size: 0.93rem">2018款 240TURBO自动两驱舒适版</p>
            <p style="font-size: 0.81rem;color: #999">颜色：天漠金<span style="float: right; font-size: 0.81rem">x1</span></p>
            <p style="font-size: 0.81rem;color: #999">深圳东通上汽大众 <span
              style="background: #f5ca1d; color: #ffffff; padding: 0 5px; border-radius: 2px; font-size: 0.75rem">4s</span>
            </p>
          </div>
        </dd>
        <dt style="border-top: 0">
          <p style="float: right; font-size: 0.81rem; margin-right: 15px">共1件商品 合计：￥<span
            style="font-size: 0.93rem; font-weight: bold">23</span>.98万</p>
        </dt>
        <div>
          <button class="order-button" @click="cancelOrder = true">取消订单</button>
        </div>
      </dl>
    </div>

    <div class="carType" v-if="cancelOrder">
      <div style="height: 40vh; background: #ffffff; position: fixed; bottom: 0.875rem; left: 0.875rem; right: 0.875rem; border-radius: 10px">
        <div style="text-align: center; line-height: 3; border-bottom: 1px solid #ECECEC; position: relative">
          <img style="float: right; width: 0.93rem; position: absolute; right: 1rem; top: 1rem;" src="../../assets/img/common/ic_screen_close.png" @click="cancelOrder = false">
          取消订单
        </div>
        <p style="padding: 0.875rem; font-size: 0.93rem">取消订单后，本单享有的优惠会一并取消，是否继续？</p>
        <p style="font-size: 0.81rem; color: #999999; padding: 0.81rem 0.875rem">请选择取消订单的原因（必选）：</p>
        <div style="font-size: 0.81rem; color: #999999; padding-left: 0.875rem; line-height: 2">
          发票信息有误
        </div>
        <div style="font-size: 0.81rem; color: #999999; padding-left: 0.875rem; line-height: 2">
          商品买错了
        </div>
        <div style="font-size: 0.81rem; color: #999999; padding-left: 0.875rem; line-height: 2">
          重复下单/误下单
        </div>
        <div style="color: #F54E4E; text-align: center; border-top: 1px solid #ECECEC; line-height: 2">
          确定
        </div>
      </div>
    </div>
    <!--<div style="height: 50px">-->
    <!--<div class="all">全部</div>-->
    <!--</div>-->
  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "OrderList",
    components: {
      headTop
    },
    data() {
      return {
        headTitle: '我的订单',
        tab: 1,
        cancelOrder: false
      }
    },
    methods: {
      select(index) {
        this.tab = index;
      }
    }
  }
</script>

<style scoped>
  .tab {
    position: relative;
    width: 100vw;
    background: #ffffff;
    text-align: center;
    height: 42px;
    border-bottom: 1px solid #ececec;
    margin-top: 3rem;
  }

  .tabItem {
    float: left;
    width: 20vw;
    background: #ffffff
  }

  .active {
    color: #f5ca1d;
    border-bottom: 3px solid #f5ca1d;
    margin: 0 20px
  }

  .itemButton {
    font-size: 0.91rem;
    padding: 10px 0;
    margin: 0;
    display: inline-block;
  }

  .user-order-list {
    background: #ffffff;
    overflow: hidden;
  }

  .user-order-list dl {
    background: #fff;
    padding: 0;
    margin-bottom: 0;
    margin-top: 0;
    border-bottom: solid 1px #ECECEC;
  }

  .user-order-list dl dt {
    border-top: solid 1px #E0E0E0;
    line-height: 3;
    overflow: hidden;
  }

  .user-order-list .myorderList {
    overflow: hidden;
    padding: 0.625rem 0.875rem;
    position: relative;
    background: #f9f9f9;
    margin-left: 0;
  }

  .user-order-list .myorderList .imgs {
    float: left;
    width: 7.75rem;
    height: 5.52rem;
    margin-right: 0.625rem;
  }

  .orderNo {
    color: #000;
    float: none;
    font-size: 0.91rem
  }

  .orderState {
    color: #F54E4E;
    float: right;
    padding-right: 15px;
    width: auto;
    font-size: 0.91rem
  }

  .order-button {
    background: none;
    border-radius: 29px;
    padding: 3px 1rem;
    border: solid 1px #DCDCDC;
    font-size: 0.93rem;
    color: #666666;
    float: right;
    margin: 10px 1rem;
    outline: none;
  }

  .all {
    position: fixed;
    bottom: 0;
    text-align: center;
    width: 100%;
    background-color: #ffffff;
    height: 44px;
    color: #F54E4E;
    line-height: 44px
  }

  .carType {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    z-index: 999;
  }
</style>
