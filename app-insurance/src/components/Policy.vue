<template>
  <div>
    <div class="r-frame">
      <div style="top: 140px;position: relative;">
        <div style="font-size: 13px;">
          投保单号：2548456812346
        </div>
        <div style="font-size: 15px;margin-top: 10px;">
          自核通过，待保险公司收费
        </div>
      </div>
      <img src="../assets/img/risk-frame.png" width="322">
      <div>
        <div class="r-tip">
          温馨提示：感谢使用水晶球生命微信投保功能，本单已自核通过。您可通过关注水晶球生命人寿在线公众号，享受更多保单服务。
        </div>
      </div>
    </div>
    <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;color: #e1bb3a">
      <router-link to="/">返回首页</router-link>
    </button>
    <button class="i-footer" style="width: 50%;right: 0">
      <router-link to="/">继续投保关联产品</router-link>
    </button>
  </div>
</template>

<script>
  export default {
    name: "policy"
  }
</script>

<style scoped>

</style>
