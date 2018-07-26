<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>

    <div class="user-order-list">
      <dl>
        <dt style="border-top: 0;padding-left: 0.875rem">
          <span class="orderNo">服务单号：19801426</span>
          <span class="orderState">等待付款</span>
        </dt>
        <dd class="myorderList">
          <img class="imgs" src="../../assets/logo.png">
          <div>
            <p style="white-space: normal; font-size: 0.93rem">宝马 宝马X1 2018款 sDrive18Li 尊享型轿车</p>
            <p style="font-size: 0.81rem;color: #999">颜色：天漠金<span style="float: right; font-size: 0.81rem">x1</span></p>
            <p style="font-size: 0.81rem;color: #999">深圳中升汇宝宝马4S店</p>
          </div>
        </dd>
        <dt style="border-top: 0">
          <p style="margin-left: 0.875rem;">
            <img style="width: 1.125rem; float: left; margin-top: 0.93rem; margin-right: 0.46rem" src="../../assets/img/common/ic_aftersale_underway.png">
            退款申请中
            <img style="width: 1.125rem; float: right; margin-right: 0.875rem; margin-top: 0.93rem" src="../../assets/img/common/ic_right.png"></p>
        </dt>
      </dl>
    </div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "ServerList",
    components: {
      headTop
    },
    data() {
      return {
        headTitle: '售后记录'
      }
    }
  }
</script>

<style scoped>
  .user-order-list {
    background: #ffffff;
    overflow: hidden;
    margin-top: 3.875rem;
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
    width: 4.875rem;
    height: 4.875rem;
    margin-right: 0.625rem;
  }

  .orderNo {
    color: #888888;
    float: none;
    font-size: 0.875rem
  }

  .orderState {
    float: right;
    padding-right: 15px;
    width: auto;
    font-size: 0.875rem
  }
</style>
