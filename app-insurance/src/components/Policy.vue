<template>
  <div>
    <div class="r-frame">
      <div style="top: 140px;position: relative;">
        <div style="font-size: 13px;">
          投保单号：{{orderCode}}
        </div>
        <div style="font-size: 15px;margin-top: 10px;">
          自核通过，待保险公司收费
        </div>
      </div>
      <img src="../assets/img/risk-frame.png" width="322">
      <div>
        <div class="r-tip">
          温馨提示：感谢使用水晶球生命投保功能，本单已自核通过。您可通过关注水晶球生命人寿在线公众号，享受更多保单服务。
        </div>
      </div>
      <button class="returnButton" @click="comeBack">
        完成
      </button>
    </div>
    <!--<button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;color: #e1bb3a">-->
    <!--<router-link to="/">返回首页</router-link>-->
    <!--</button>-->
    <!--<button class="i-footer" style="width: 50%;right: 0">-->
    <!--<router-link to="/">继续投保关联产品</router-link>-->
    <!--</button>-->
    <!--<div style="height: 60px;" >-->
    <!--<div class="i-footer">-->
    <!--<button  @click="comeBack">-->
    <!--&lt;!&ndash;<div>继续投保关联产品</div>&ndash;&gt;-->
    <!--完成-->
    <!--</button>-->
    <!--</div>-->
    <!--</div>-->
  </div>
</template>

<script>
  var linstener = function () {
    history.pushState(null, null, location.href);
  }
  export default {
    name: "policy",
    data() {
      return {
        orderCode: '',
        pageNum: 0
      }
    },
    created() {
      this.orderCode = this.$route.query.orderCode;
      this.pageNum = this.$route.query.pageNum;
      this.pageNum = this.pageNum + 1;
      history.pushState(null, null, location.href);
      window.addEventListener('popstate', linstener, false);
    },
    methods: {
      comeBack() {
        window.removeEventListener('popstate', linstener, false);
        this.$router.go(-this.pageNum)
      }
    }
  }
</script>

<style scoped>
  .returnButton {
    width: 75%;
    height: 48px;
    margin-top: 45px;
    background-color: #f5ca1d;
    font-size: 18px;
    color: #ffffff;
    border: unset;
    border-radius: 50px;
  }
</style>
