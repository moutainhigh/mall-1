<template>
  <div>
    <div class="i-list" style="margin-top: 10px">
      <div class="list-title">
        <div style="margin-left: 16px">保单号：1111111</div>
        <div style="float: right;margin-right: 16px;color: #c01212;">支付完成</div>
      </div>
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
            <button>
              立即投保
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "myOrders",
        meta: {
          title: '我的保单'
        },
      methods: {

      },
      created () {
          console.log("test")
      }
    }
</script>

<style scoped>
  .list-title div{
    display: inline-block;
    margin-top: 10px;
    font-size: 14px;
  }
</style>
