<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>
    <div style="position: relative; background: #ffffff; margin-bottom: 10px">
      <div style="margin: 0 14px; border-bottom: 1px solid #ECECEC">
        <span style="font-size: 15px; padding: 17px 0;">姓名</span>
        <input type="text" style="border: 0; margin-left: 20px; padding: 17px 0" placeholder="请填写真实姓名">
      </div>
      <div style="margin: 0 14px; border-bottom: 1px solid #ECECEC">
        <span style="font-size: 15px; padding: 17px 0;">手机号</span>
        <input type="text" style="border: 0; margin-left: 20px; padding: 17px 0" placeholder="请填写联系手机号">
      </div>
      <div style="margin: 0 14px; border-bottom: 1px solid #ECECEC;">
        <span style="font-size: 15px; padding: 17px 0;">提车地址</span>
        <!--<input type="text" style="border: 0; margin-left: 20px; padding: 17px 0" placeholder="请填写联系手机号">-->
        <span
          style="margin-left: 20px; padding: 17px 0;display: inline-block; width: 230px">广东省深圳市龙华区油松社区中裕冠大道1号1018号</span>
      </div>
    </div>

    <div class="buyMode" @click="checkType = true">
      <p class="selectItem-title">支付方式</p>
      <p class="selectItem-detail" style="color: #f5ca1d">{{buytype}}</p>
      <img src="../../assets/img/cardetail/ic_right.png">
    </div>

    <div style="background: #ffffff">
      <p style="font-size: 16px; padding: 12px 14px">深圳中升汇宝宝马4S店</p>
      <div style="height: 106px; line-height: 1.5; background: #FAFAFA">
        <img style="width: 78px; height: 78px; padding: 14px; float: left; border-radius: 6px"
             src="../../assets/logo.png">
        <p style="margin: 0; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; padding-top: 14px">宝马 宝马X1
          2018款 sDrive18Li 尊享型尊享型</p>
        <span style="font-size: 14px; color: #888888">雪山白 2.0L 自动</span>
        <p style="color: #F54E4E">￥<span style="font-size: 25px; font-weight: bold">26.98</span>万</p>
      </div>
      <div style="padding: 2px 0">
        <p style="padding: 7px 15px; font-size: 15px">车型<span style="font-weight: bold; float: right">轿车</span></p>
        <p style="padding: 7px 15px; font-size: 15px">坐席<span style="font-weight: bold; float: right">4-5座</span></p>
        <p style="padding: 7px 15px; font-size: 15px">燃料<span style="font-weight: bold; float: right">汽油</span></p>
      </div>
    </div>
    <p style="color: #888888; font-size: 13px; padding: 8px 14px">点击提交即视为同意<span style="color: #333333">《隐私政策》</span>
    </p>
    <div style="height: 70px">
      <div class="i-footer">
        <button @click="">
          <div>提交</div>
        </button>
      </div>
    </div>

    <div class="type-mode" v-if="checkType">
      <div class="modeTitle">
        <span>支付方式</span>
        <img @click="checkType = false" src="../../assets/img/cardetail/ic_sku_close.png">
      </div>
      <div style="margin-left: 14px">
        <button v-for="(buyMode, index) in buyModes" class="carColor" :class="{'activeColor': index == activeMode}"
                @click="checkMode(index)">
          {{buyMode}}
        </button>
      </div>
      <div style="height: 70px">
        <div class="i-footer">
          <button @click="checkType = false">
            <div>确认</div>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "OrderComfirm",
    components: {
      headTop
    },
    data() {
      return {
        headTitle: '订单确认',
        buytype: '贷款支付',
        buyModes: ['贷款支付', '在线支付'],
        checkType: false,
        activeMode: 0,
      }
    },
    methods: {
      checkMode(index) {
        this.activeMode = index;
        this.buytype = this.buyModes[index];
      },
    }
  }
</script>

<style scoped>
  .buyMode {
    position: relative;
    height: 40px;
    background: #ffffff;
    margin-bottom: 10px;
  }

  .buyMode img {
    width: 18px;
    float: right;
    margin: 10px 14px
  }

  .selectItem-title {
    padding: 10px 14px;
    float: left;
    font-size: 15px
  }

  .selectItem-detail {
    padding: 10px;
    float: left;
    font-weight: bold
  }

  .type-mode {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 250px;
    background: #fff;
  }

  .modeTitle {
    padding: 15px;
    position: relative;
    border-bottom: 1px solid #f3f3f3
  }

  .modeTitle span {
    font-weight: bold
  }

  .modeTitle img {
    width: 14px;
    float: right
  }

  .carColor {
    padding: 4px 14px;
    border-radius: 20px;
    border: 1px solid #ffffff;
    background: #f1f3f5;
    margin: 7px 14px 7px 0;
    outline: none;
  }

  .activeColor {
    color: #f5ca1d;
    border: 1px solid #f5ca1d;
    background: #fffbeb;
  }
</style>
