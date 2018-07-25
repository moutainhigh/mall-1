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
        <dt style="border-top: 0;padding-left: 15px">
          <span style="color: #000;float: none; font-size: 0.91rem">订单编号：1549856</span>
          <span style="color: red;float: right;padding-right: 15px;width: auto; font-size: 0.91rem">等待付款</span>
        </dt>
        <dd class="myorderList">
          <img class="imgs" src="../../assets/logo.png">
          <div>
            <p style="white-space: normal; font-size: 1rem">2018款 240TURBO自动两驱舒适版</p>
            <p style="font-size: 0.91rem;color: #999">颜色：天漠金<span style="float: right; font-size: 0.91rem">x1</span></p>
            <p style="font-size: 0.91rem;color: #999">深圳东通上汽大众  <span style="background: #f5ca1d; color: #ffffff; padding: 0 5px; border-radius: 2px">4S</span></p>
          </div>
        </dd>
        <dt style="border-top: 0">
          <p style="float: right; font-size: 0.91rem; margin-right: 15px">共1件商品 合计：￥<span
            style="font-size: 1rem; font-weight: bold">23</span>.98万</p>
        </dt>
        <div>
          <button class="order-button">取消订单</button>
        </div>
      </dl>
    </div>
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
        tab: 1
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
    border-bottom: solid 1px #E0E0E0;
  }

  .user-order-list dl dt {
    border-top: solid 1px #E0E0E0;
    line-height: 2.5;
    overflow: hidden;
  }

  .user-order-list .myorderList {
    overflow: hidden;
    padding: 8px 15px;
    position: relative;
    background: #f9f9f9;
    margin-left: 0;
  }

  .user-order-list .myorderList .imgs {
    float: left;
    width: 110px;
    height: 80px;
    margin-right: 10px;
  }

  .order-button {
    background: none;
    border-radius: 50px;
    padding: 3px 10px 3px 10px;
    border: solid 1px #999;
    font-size: 1rem;
    color: #999;
    float: right;
    margin: 10px 1rem;
  }
</style>
