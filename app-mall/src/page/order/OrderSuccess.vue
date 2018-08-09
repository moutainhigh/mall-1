<template>
  <div style="height: 100vh; background: #ffffff">
    <div class="orderSuccess-img">
      <img src="../../assets/img/common/ic_order_succeed.png">
    </div>
    <div class="success-text">
      <p class="success-text-title">恭喜你，订单提交成功！</p>
      <p class="success-text-content">您的贷款信息我们正在审核，请您耐心等候！</p>
    </div>
    <div style="height: 1px; background: #ECECEC; margin: 0 0.93rem"></div>
    <div class="tip">
      <p>请保持电话畅通，以便商家及时与您联系</p>
    </div>
    <div style="text-align: center">
      <button class="checkOrder" @click='viewOrder'>查看订单</button>
      <p style="font-size: 1.16rem; padding-top: 1.5rem" @click="returnHome">返回首页</p>
    </div>
  </div>
</template>

<script>
  export default {
    name: "OrderSuccess",
    methods: {
      returnHome() {
        this.$router.push("/");
      },
      viewOrder(){
        location.href ='yunxi://yunxi.com?url_action={"from":"webview","url":"","action":1,"data":'+this.$route.query.productId+'}'
      }
    },

  }
</script>

<style scoped>
  .orderSuccess-img {
    position: relative;
    height: 11rem
  }

  .orderSuccess-img img {
    margin: auto;
    position: absolute;
    left: 0;
    right: 0;
    top: 4.3rem;
    width: 5rem
  }

  .success-text {
    text-align: center;
    height: 5rem
  }

  .success-text-title {
    font-size: 1.25rem;
    font-weight: bold
  }

  .success-text-content {
    font-size: 0.93rem;
    padding-top: 10px
  }

  .tip {
    height: 5.93rem;
    background: #ffffff;
    text-align: center
  }

  .tip p {
    color: #999999;
    font-size: 0.875rem;
    padding-top: 0.93rem
  }

  .checkOrder {
    width: 91vw;
    background: #f5ca1d;
    border: 0;
    border-radius: 5px;
    height: 3rem;
    color: #ffffff;
    font-size: 1.16rem;
    outline: none;
  }
</style>
