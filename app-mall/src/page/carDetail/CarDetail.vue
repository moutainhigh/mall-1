<template>
  <div>
    <div style="width: 100%; height: 40vh">
      <img src="../../assets/logo.png" style="height: 100%; width: 100%; background: #999">
    </div>
    <div class="carPrice">
      <div class="price">
        <p class="presentPrice">￥<span
          style="">23.98</span>万</p>
        <p class="guidePrice" style="">指导价：￥19.88 - 36.98万</p>
      </div>
      <div class="collect" @click="isCollect = !isCollect">
        <div style="position: relative;">
          <img v-if="!isCollect" src="../../assets/img/cardetail/ic_collect_nor.png">
          <img v-if="isCollect" src="../../assets/img/cardetail/ic_collect_sele.png">
        </div>
        <p>收藏</p>
      </div>
    </div>
    <div class="carIntro">
      <p>宝马 宝马X1 2018款 sDrive18Li 尊享型</p>
    </div>

    <div style="background: #f3f3f3; height: 1px; margin-left: 10px; margin-right: 10px;"></div>

    <div class="rank">
      <p class="rank-title">级别</p>
      <p class="rank-detail">紧凑型车</p>
    </div>
    <div class="selectItem" @click="checkType = 'standard'">
      <p class="selectItem-title">规格选择</p>
      <p v-if="standard[0] == ''" class="selectItem-detail">请选择</p>
      <img src="../../assets/img/cardetail/ic_right.png">
    </div>
    <div class="buyMode" @click="checkType = 'mode'">
      <p class="selectItem-title">支付方式</p>
      <p class="selectItem-detail">请选择</p>
      <img src="../../assets/img/cardetail/ic_right.png">
    </div>
    <div style="background: #f3f3f3; height: 1px; margin-left: 10px; margin-right: 10px"></div>

    <div style="position: relative; height: 80px; background: #ffffff;">
      <p style="padding: 10px 10px 0 10px;">深圳中升汇宝宝马4S店<span style="float: right; font-size: 14px; color: #f5ca1d">【现货】</span></p>
      <p style="padding: 0 10px; color: #999; font-size: 14px">广东省深圳市龙华区油松社区中裕冠大道1号1018号</p>
      <p style="padding: 0 10px; color: #f5ca1d; font-size: 14px">联系电话：0755-88962645</p>
    </div>
    <div style="position: relative; height: 40px; background: #FAFAFA; margin-bottom: 10px">
      <p style="padding: 10px; font-size: 14px; color: #999; float: left"><img style="width: 13px; margin-right: 5px"
                                                                               src="../../assets/img/cardetail/ic_particulars_service.png">店铺发货
      </p>
      <p style="padding: 10px; font-size: 14px; color: #999; float: left"><img style="width: 13px; margin-right: 5px"
                                                                               src="../../assets/img/cardetail/ic_particulars_service.png">全国联保
      </p>
      <p style="padding: 10px; font-size: 14px; color: #999; float: left"><img style="width: 13px; margin-right: 5px"
                                                                               src="../../assets/img/cardetail/ic_particulars_service.png">认证商家
      </p>
    </div>
    <div
      style="position: relative; height: 20px; background: #ffffff; border-bottom: 1px solid #f3f3f3; padding: 10px 0">
      <p style="padding: 0 10px; border-left: 4px solid #f5ca1d;">商品介绍</p>
    </div>
    <div style="height: 40px; background: #fff"></div>
    <div style="height: 70px">
      <div class="i-footer">
        <button>
          <div>立即抢购</div>
        </button>
      </div>
    </div>

    <div v-if="checkType != 'none'" class="carType">
      <div v-if="checkType == 'standard'" style="position: fixed; bottom: 0; width: 100%; height: 450px; background: #fff;">
        <div style="padding: 15px; position: relative; height: 100px">
          <img style="height: 100%; border-radius: 4px; border: 1px solid #f3f3f3; float: left"
               src="../../assets/logo.png">
          <img style="width: 14px; float: right" src="../../assets/img/cardetail/ic_sku_close.png" @click="checkType = 'none'">
          <p style="color: red; font-size: 16px; padding-top: 45px; margin-left: 110px">￥<span
            style="color: red; font-size: 24px; font-weight: bold">23.98</span>万</p>
          <p style="margin-left: 110px; font-size: 14px">商品编号：1212454878</p>
        </div>
        <p class="carType-title">颜色</p>
        <div>
          <button v-for="(carColor, index) in carColors" class="carColor" :class="{'activeColor': index == activeColor}"
                  @click="checkColor(index)">
            {{carColor}}
          </button>
        </div>
        <p class="carType-title">排量</p>
        <button v-for="(displacement, index) in displacements" class="carColor"
                :class="{'activeColor': index == activeVolume}"
                @click="checkVolume(index)">
          {{displacement}}
        </button>
        <p class="carType-title">变速箱</p>
        <button v-for="(am, index) in autoManual" class="carColor" :class="{'activeColor': index == activeAM}"
                @click="checkAM(index)">
          {{am}}
        </button>
        <div style="height: 70px">
          <div class="i-footer">
            <button @click="selectStandard()">
              <div>确认</div>
            </button>
          </div>
        </div>
      </div>

      <div v-if="checkType == 'mode'" style="position: fixed; bottom: 0; width: 100%; height: 250px; background: #fff;">
        <div style="padding: 15px; position: relative; border-bottom: 1px solid #f3f3f3">
          <span style="font-weight: bold">支付方式</span>
          <img style="width: 14px; float: right" src="../../assets/img/cardetail/ic_sku_close.png" @click="checkType = 'none'">
        </div>
        <button v-for="(buyMode, index) in buyModes" class="carColor" :class="{'activeColor': index == activeMode}"
                @click="checkMode(index)">
          {{buyMode}}
        </button>
        <div style="height: 70px">
          <div class="i-footer">
            <button>
              <div>确认</div>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "CarDetail",
    data() {
      return {
        checkType: 'none',
        isCollect: false,
        carColors: ['雪山白', '墨尔本红', '雪松灰', '矿石白', '勃艮第红'],
        displacements: ['1.5L', '2.0L', '3.0L'],
        autoManual: ['手动', '自动'],
        buyModes: ['贷款支付','在线支付'],
        activeColor: null,
        activeVolume: null,
        activeAM: null,
        activeMode: null,
        standard: ['','','']
      }
    },
    methods: {
      checkColor(index) {
        this.activeColor = index;
      },
      checkVolume(index) {
        this.activeVolume = index;
      },
      checkAM(index) {
        this.activeAM = index;
      },
      checkMode(index) {
        this.activeMode = index;
      },
      selectStandard() {
        this.standard[0] = this.activeColor;
        this.standard[1] = this.activeVolume;
        this.standard[2] = this.activeAM;
      }
    }
  }
</script>

<style scoped>
  .carPrice {
    width: 100%;
    height: 70px;
    position: relative;
    background: #fff
  }

  .price {
    width: 80%;
    height: 50px;
    float: left;
    padding: 3%
  }

  .presentPrice {
    color: red;
    font-size: 16px
  }

  .presentPrice p {
    color: red;
    font-size: 24px;
    font-weight: bold
  }

  .guidePrice {
    color: #999;
    font-size: 14px
  }

  .collect {
    width: 14%;
    height: 50px;
    float: left
  }

  .collect img {
    width: 35%;
    left: 0;
    right: 0;
    top: 15px;
    margin: auto;
    position: absolute
  }

  .collect p {
    text-align: center;
    padding-top: 35px;
    font-size: 12px;
    color: #999999
  }

  .carIntro {
    position: relative;
    height: 35px;
    padding: 0 10px;
    background: #fff;
  }

  .carIntro p {
    font-size: 17px;
    float: left;
    font-weight: bold;
  }

  .rank {
    position: relative;
    height: 40px;
    background: #fff;
    margin-bottom: 10px
  }

  .rank-title {
    padding: 10px;
    color: #999;
    float: left;
    font-size: 14px
  }

  .rank-detail {
    padding: 10px;
    float: left
  }

  .selectItem {
    position: relative;
    height: 40px;
    background: #ffffff;
    margin-bottom: 10px
  }

  .selectItem img {
    width: 20px;
    float: right;
    margin: 10px
  }

  .selectItem-title {
    padding: 10px;
    color: #999;
    float: left;
    font-size: 14px
  }

  .selectItem-detail {
    padding: 10px;
    float: left;
    font-weight: bold
  }

  .buyMode {
    position: relative;
    height: 40px;
    background: #ffffff
  }

  .buyMode img {
    width: 20px;
    float: right;
    margin: 10px
  }

  .carType {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.4);
    z-index: 999;
  }

  .carType-title {
    padding: 5px 15px 0 15px;
    font-weight: bold
  }

  .carColor {
    padding: 3px 10px;
    border: 0;
    border-radius: 20px;
    border: 1px solid #ffffff;
    background: #f1f3f5;
    margin: 7px;
    outline: none;
  }

  .activeColor {
    color: #f5ca1d;
    border: 1px solid #f5ca1d;
    background: #fffbeb;
  }
</style>
