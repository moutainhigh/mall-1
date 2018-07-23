<template>
  <div>
    <head-top :headTitle="headTitle">
      <div slot="head-tab" class="head-tab" v-if="scroll > 350 || tab != 1">
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
      <div style="width: 100%; height: 280px">
        <img src="../../assets/logo.png" style="height: 100%; width: 100%; background: #999" v-preview="imgdata" :key="1" :alt="'123'">
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
        <div style="padding: 6px 14px">
          <button v-for="(carColor, index) in carColors" class="carColor" :class="{'activeColor': index == activeColor}"
                  @click="checkColor(index)">
            {{carColor}}
          </button>
        </div>
        <p class="carType-title">排量</p>
        <div style="padding: 6px 14px">
          <button v-for="(displacement, index) in displacements" class="carColor"
                  :class="{'activeColor': index == activeVolume}"
                  @click="checkVolume(index)">
            {{displacement}}
          </button>
        </div>
        <p class="carType-title">变速箱</p>
        <div style="padding: 6px 14px">
          <button v-for="(am, index) in autoManual" class="carColor" :class="{'activeColor': index == activeAM}"
                  @click="checkAM(index)">
            {{am}}
          </button>
        </div>
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
        show: false,
        imgdata: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyNpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDE0IDc5LjE1Njc5NywgMjAxNC8wOC8yMC0wOTo1MzowMiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OTk2QkI4RkE3NjE2MTFFNUE4NEU4RkIxNjQ5MTYyRDgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OTk2QkI4Rjk3NjE2MTFFNUE4NEU4RkIxNjQ5MTYyRDgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NjU2QTEyNzk3NjkyMTFFMzkxODk4RDkwQkY4Q0U0NzYiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NjU2QTEyN0E3NjkyMTFFMzkxODk4RDkwQkY4Q0U0NzYiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5WHowqAAAXNElEQVR42uxda4xd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'
      }
    },
    methods: {
      checkTab(tabNum) {
        this.tab = tabNum;
        this.scroll = document.documentElement.scrollTop || document.body.scrollTop;
        if (this.scroll <= 350 && this.tab == 1) {
          this.headTitle = '汽车详情';
        } else {
          this.headTitle = '';
        }
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
        if (this.scroll <= 350 && this.tab == 1) {
          this.headTitle = '汽车详情';
        } else {
          this.headTitle = '';
        }
      }
    },
    watch: {
      tab: {}
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
    /*width: 100%;*/
    height: 70px;
    position: relative;
    background: #fff;
    padding: 0 14px;
  }

  .price {
    width: 80%;
    height: 50px;
    float: left;
    padding: 3% 0
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
    /*width: 14%;*/
    height: 50px;
    float: right;
  }

  .collect img {
    width: 18px;
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
    padding: 0 14px;
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
    padding: 10px 14px;
    color: #999;
    float: left;
    font-size: 14px
  }

  .rank-detail {
    padding: 10px;
    float: left;
  }

  .selectItem {
    position: relative;
    height: 40px;
    background: #ffffff;
    margin-bottom: 10px
  }

  .selectItem img {
    width: 18px;
    float: right;
    margin: 10px 14px
  }

  .selectItem-title {
    padding: 10px 14px;
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
    width: 18px;
    float: right;
    margin: 10px 14px
  }

  .shopInfo {
    position: relative;
    height: 80px;
    background: #ffffff;
  }

  .shopName {
    padding: 10px 14px 0 14px;
  }

  .shopName span {
    float: right;
    font-size: 14px;
    color: #f5ca1d
  }

  .shopAddress {
    padding: 0 14px;
    color: #999;
    font-size: 14px
  }

  .shopTel {
    padding: 0 14px;
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
    padding: 10px 14px;
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
    padding: 0 14px;
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
    height: 480px;
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
    padding: 14px;
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
    width: 18px;
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
    padding: 5px 14px 0 14px;
    font-weight: bold
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
