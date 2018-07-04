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
            <button>
              <router-link to="/pro-detail">立即投保</router-link>
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
            <button>
              <router-link to="/pro-detail">立即投保</router-link>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "index"
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
