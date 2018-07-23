<template>
  <div>
    <head-top :headTitle="headTitle">
      <div slot="head-tab" class="head-tab" v-if="scroll > 350">
        <div v-bind:class="{'activeTab': tab == 1}" @click="checkTab(1)">
          详情
        </div>
        <div :class="{'activeTab': tab == 2}" @click="checkTab(2)">
          配置
        </div>
        <div :class="{'activeTab': tab == 3}" @click="checkTab(3)">
          说明
        </div>
      </div>
    </head-top>

    <div v-if="tab == 1">
      <div style="width: 100%; height: 40vh">
        <img src="../../assets/logo.png" style="height: 100%; width: 100%; background: #999">
      </div>
      <div class="carPrice">
        <div class="price">
          <p class="presentPrice">￥<span>19.98-33.68</span>万</p>
          <p class="guidePrice" style="">指导价：￥19.88 - 36.98万</p>
        </div>
        <div class="collect" @click="isCollect = !isCollect">
          <div style="position: relative;">
            <img v-if="!isCollect" src="../../assets/img/cardetail/ic_collect_nor.png">
            <img v-if="isCollect" src="../../assets/img/cardetail/ic_collect_sele.png">
          </div>
          <p v-if="!isCollect">收藏</p>
          <p v-if="isCollect">已收藏</p>
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
        <p v-if="standard[0] != ''" class="selectItem-detail">2018款 {{standard[0]}} {{standard[1]}} {{standard[2]}}
          1辆</p>
        <img src="../../assets/img/cardetail/ic_right.png">
      </div>
      <div class="buyMode" @click="checkType = 'mode'">
        <p class="selectItem-title">支付方式</p>
        <p v-if="mode == ''" class="selectItem-detail">请选择</p>
        <p v-if="mode != ''" class="selectItem-detail" style="color: #f5ca1d">{{mode}}</p>
        <img src="../../assets/img/cardetail/ic_right.png">
      </div>
      <div style="background: #f3f3f3; height: 1px; margin-left: 10px; margin-right: 10px"></div>

      <div class="shopInfo">
        <p class="shopName">深圳中升汇宝宝马4S店<span>【现货】</span></p>
        <p class="shopAddress">广东省深圳市龙华区油松社区中裕冠大道1号1018号</p>
        <p class="shopTel">联系电话：0755-88962645</p>
      </div>
      <div class="shopEnsure">
        <p><img src="../../assets/img/cardetail/ic_particulars_service.png">店铺发货</p>
        <p><img src="../../assets/img/cardetail/ic_particulars_service.png">全国联保</p>
        <p><img src="../../assets/img/cardetail/ic_particulars_service.png">认证商家</p>
      </div>
      <div class="introTitle">
        <p>商品介绍</p>
      </div>
      <!--<button @click="show =!show">测试</button>-->
      <!--<transition name="slide-fade">-->
      <!--<p v-if="show">ceshi</p>-->
      <!--</transition>-->
      <div style="height: 700px; width: 100%; background: #ffffff"></div>

      <div style="height: 40px; background: #fff"></div>
      <div style="height: 70px">
        <div class="i-footer">
          <button>
            <div>立即抢购</div>
          </button>
        </div>
      </div>

    </div>

    <div v-if="tab == 2">
      <CarConfig></CarConfig>
    </div>

    <div v-if="tab == 3">
      <CarExplain></CarExplain>
    </div>

    <div v-if="checkType != 'none'" class="carType">
      <div v-if="checkType == 'standard'" class="type-standard">
        <div class="car-sale">
          <img class="car-thumbnail" src="../../assets/logo.png">
          <img class="car-close" src="../../assets/img/cardetail/ic_sku_close.png" @click="checkType = 'none'">
          <p class="sale-price">￥<span>23.98</span>万</p>
          <p class="sale-no">商品编号：1212454878</p>
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

      <div v-if="checkType == 'mode'" class="type-mode">
        <div class="modeTitle">
          <span>支付方式</span>
          <img src="../../assets/img/cardetail/ic_sku_close.png" @click="checkType = 'none'">
        </div>
        <button v-for="(buyMode, index) in buyModes" class="carColor" :class="{'activeColor': index == activeMode}"
                @click="checkMode(index)">
          {{buyMode}}
        </button>
        <div style="height: 70px">
          <div class="i-footer">
            <button @click="selectMode()">
              <div>确认</div>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "CarDetail",
    components: {
      headTop
    },
    data() {
      return {
        headTitle: '汽车详情',
        scroll: '',
        tab: 1,
        checkType: 'none',
        isCollect: false,
        carColors: ['雪山白', '墨尔本红', '雪松灰', '矿石白', '勃艮第红'],
        displacements: ['1.5L', '2.0L', '3.0L'],
        autoManual: ['手动', '自动'],
        buyModes: ['贷款支付', '在线支付'],
        activeColor: 0,
        activeVolume: 0,
        activeAM: 0,
        activeMode: 0,
        standard: ['', '', ''],
        mode: '',
        show: false
      }
    },
    methods: {
      checkTab(tabNum) {
        this.tab = tabNum;
      },
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
        this.standard[0] = this.carColors[this.activeColor];
        this.standard[1] = this.displacements[this.activeVolume];
        this.standard[2] = this.autoManual[this.activeAM];
        this.checkType = 'none';
      },
      selectMode() {
        this.mode = this.buyModes[this.activeMode];
        this.checkType = 'none';
      },
      menu() {
        this.scroll = document.documentElement.scrollTop || document.body.scrollTop;
        if (this.scroll <= 350) {
          this.headTitle = '汽车详情';
        } else {
          this.headTitle = '';
        }
      }
    },
    mounted() {
      window.addEventListener('scroll', this.menu)
    },
  }
</script>

<style scoped>
  .head-tab {
    position: absolute;
    top: 1rem;
    left: 0;
    right: 0;
    padding: 0 0 0.5rem 0;
    z-index: -1;
    text-align: center;
  }

  .head-tab div {
    display: inline-block;
    padding: 0 0 0.5rem 0;
    margin: 0 10px;
  }

  .activeTab {
    border-bottom: #f5ca1d solid 3px;
  }

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

  .presentPrice span {
    font-size: 26px;
    font-weight: bold;
  }

  .guidePrice {
    color: #888888;
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

  .shopInfo {
    position: relative;
    height: 80px;
    background: #ffffff;
  }

  .shopName {
    padding: 10px 10px 0 10px;
  }

  .shopName span {
    float: right;
    font-size: 14px;
    color: #f5ca1d
  }

  .shopAddress {
    padding: 0 10px;
    color: #999;
    font-size: 14px
  }

  .shopTel {
    padding: 0 10px;
    color: #f5ca1d;
    font-size: 14px
  }

  .shopEnsure {
    position: relative;
    height: 40px;
    background: #FAFAFA;
    margin-bottom: 10px
  }

  .shopEnsure p {
    padding: 10px;
    font-size: 14px;
    color: #999;
    float: left
  }

  .shopEnsure img {
    width: 13px;
    margin-right: 5px
  }

  .introTitle {
    position: relative;
    height: 20px;
    background: #ffffff;
    border-bottom: 1px solid #f3f3f3;
    padding: 10px 0
  }

  .introTitle p {
    padding: 0 10px;
    border-left: 4px solid #f5ca1d;
  }

  .carType {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.6);
    z-index: 999;
  }

  .type-standard {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 450px;
    background: #fff;
  }

  .type-mode {
    position: fixed;
    bottom: 0;
    width: 100%;
    height: 250px;
    background: #fff;
  }

  .car-sale {
    padding: 15px;
    position: relative;
    height: 100px
  }

  .car-thumbnail {
    height: 100%;
    border-radius: 4px;
    border: 1px solid #f3f3f3;
    float: left
  }

  .car-close {
    width: 14px;
    float: right
  }

  .sale-price {
    color: red;
    font-size: 16px;
    padding-top: 45px;
    margin-left: 110px
  }

  .sale-price span {
    color: red;
    font-size: 24px;
    font-weight: bold
  }

  .sale-no {
    margin-left: 110px;
    font-size: 14px
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

  .slide-fade-enter-active {
    transition: all .3s ease;
  }

  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .slide-fade-enter, .slide-fade-leave-to
    /* .slide-fade-leave-active for below version 2.1.8 */
  {
    transform: translateY(500px);
    opacity: 0;
  }
</style>
