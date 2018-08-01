<template>
  <div>
    <div style="height: 3rem"></div>
    <head-top :headTitle="headTitle">
      <div slot="head-tab" class="head-tab" v-if="scroll > 90 || tab != 1">
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
      <div>
        <swiper :aspect-ratio="0.749" auto style="margin:0 auto;" dots-position="center">
          <swiper-item class="swiper-demo-img" v-for="(img, index) in commodityData.imageSet" :key="index">
            <img width="100%" :src="img" v-preview="img"></swiper-item>
        </swiper>
      </div>

      <div style="display: none">
        <img :src="commodityData.defaultPicPath" v-preview="commodityData.defaultPicPath" :key="0">
      </div>
      <div class="carPrice">
        <div class="price">
          <p class="presentPrice">￥<span>{{commodityData.sellPrice}}</span>万</p>
          <p class="guidePrice" style="">指导价：￥{{commodityData.priceSectionVo.startPrice}} -
            {{commodityData.priceSectionVo.endPrice}}万</p>
        </div>
        <div class="collect" @click="collectCommodity">
          <div style="position: relative; width: 40px">
            <img v-if="!isCollect" src="../../assets/img/cardetail/ic_collect_nor.png">
            <img v-if="isCollect" src="../../assets/img/cardetail/ic_collect_sele.png">
          </div>
          <p v-if="!isCollect">收藏</p>
          <p v-if="isCollect">已收藏</p>
        </div>
      </div>
      <div class="carIntro">
        <p>{{commodityData.commodityTitle}}</p>
      </div>

      <div style="background: #f3f3f3; height: 1px; margin-left: 10px; margin-right: 10px;"></div>

      <div class="rank">
        <p class="rank-title">级别：</p>
        <p class="rank-detail">{{commodityData.specs.级别}}</p>
      </div>
      <div class="selectItem" @click="checkProducts">
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
        <p class="shopName">{{commodityData.sellerVo.sellerName}}<span>【现货】</span></p>
        <p class="shopAddress">{{commodityData.sellerVo.sellerAddress}}</p>
        <p class="shopTel">联系电话：{{commodityData.sellerVo.mobile}}</p>
      </div>
      <div class="shopEnsure">
        <p><img src="../../assets/img/cardetail/ic_particulars_service.png">店铺发货</p>
        <p><img src="../../assets/img/cardetail/ic_particulars_service.png">全国联保</p>
        <p><img src="../../assets/img/cardetail/ic_particulars_service.png">认证商家</p>
      </div>
      <div class="introTitle">
        <p>商品介绍</p>
      </div>
      <div class="content">
        <div v-html="commodityData.content"></div>
      </div>

      <div style="height: 40px; background: #fff"></div>
      <div style="height: 70px">
        <div class="i-footer">
          <button @click="toOrderComfirm">
            <div>立即抢购</div>
          </button>
        </div>
      </div>

    </div>

    <div v-if="tab == 2">
      <CarConfig></CarConfig>
    </div>

    <div v-if="tab == 3">
      <carExplain :explainContent="commodityData.explainContent"></carExplain>
    </div>

    <div v-show="checkType != 'none'" class="carType">
      <transition name="toggle-cart">
        <div v-if="checkType == 'standard'" class="type-standard">
          <div class="car-sale">
            <div style="width: 27.7vw; float: left">
              <img class="car-thumbnail" :src="commodityData.defaultPicPath">
            </div>
            <img class="car-close" src="../../assets/img/cardetail/ic_sku_close.png" @click="checkType = 'none'">
            <div style="width: 60vw; float: right">
              <p class="sale-price">￥<span>23.98</span>万</p>
              <p class="sale-no">商品编号：{{commodityData.commodityCode}}</p>
            </div>
          </div>
          <div style="overflow-y: auto; height: 300px">
            <div v-for="product in products">
              <p class="carType-title">{{product.groupName}}</p>
              <div style="padding: 0px 14px">
                <button v-for="(attribute, index) in product.attributes" class="carColor"
                        :class="{'activeColor': index == activeColor}"
                        @click="checkColor(index)">
                  {{attribute.attributeName}}
                </button>
              </div>
            </div>
          </div>
          <div style="height: 70px">
            <div class="i-footer">
              <button @click="selectStandard()">
                <div>确认</div>
              </button>
            </div>
          </div>
        </div>
      </transition>

      <transition name="toggle-cart">
        <div v-if="checkType == 'mode'" class="type-mode">
          <div class="modeTitle">
            <span>支付方式</span>
            <img src="../../assets/img/cardetail/ic_sku_close.png" @click="checkType = 'none'">
          </div>
          <div style="margin-left: 14px">
            <button v-for="type in commodityData.paymentType" class="carColor"
                    :class="{'activeColor': type == activeMode}"
                    @click="checkMode(type)">
              {{type}}
            </button>
          </div>
          <div style="height: 70px">
            <div class="i-footer">
              <button @click="selectMode()">
                <div>确认</div>
              </button>
            </div>
          </div>
        </div>
      </transition>
    </div>

  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import carExplain from './CarExplain'
  import {
    addCommodityFavorite,
    delFavoriteByFavoriteId,
    getCommdityDetailById, getProductsByCommodityId
  } from "../../service/getData";
  import {Swiper, SwiperItem} from 'vux'

  export default {
    name: "CarDetail",
    components: {
      headTop,
      carExplain,
      Swiper,
      SwiperItem,
    },
    data() {
      return {
        headTitle: '汽车详情',
        scroll: '',
        tab: 1,
        checkType: 'none',
        isCollect: false,
        commodityData: {},
        products: [],
        activeColor: 0,
        activeVolume: 0,
        activeAM: 0,
        activeMode: '',
        standard: ['', '', ''],
        mode: '',
        show: false,
        favoriteId: null,
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
      checkProducts() {
        this.popupVisible = true;
        this.checkType = 'standard';
        getProductsByCommodityId(this.commodityData.commodityId).then(res => {
          if (res.result == 'SUCCESS') {
            this.products = res.data;
          }
        })
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
      checkMode(type) {
        this.activeMode = type;
      },
      selectStandard() {

        this.checkType = 'none';
      },
      selectMode() {
        this.mode = this.activeMode;
        this.checkType = 'none';
      },
      menu() {
        this.scroll = document.documentElement.scrollTop || document.body.scrollTop;
        if (this.scroll <= 90 && this.tab == 1) {
          this.headTitle = '汽车详情';
        } else {
          this.headTitle = '';
        }
      },
      toOrderComfirm() {
        this.$router.push({
          path: "/order-comfirm"
        })
      },
      //收藏
      collectCommodity() {
        if (!this.isCollect) {
          let favoriteVo = {
            commodityId: this.commodityData.commodityId,
            salePrice: this.commodityData.sellPrice
          };
          addCommodityFavorite(favoriteVo).then(res => {
            if (res.result == 'SUCCESS') {
              this.favoriteId = res.data.favoriteId;
              this.isCollect = !this.isCollect;
            }
          })
        } else {
          if (this.favoriteId) {
            delFavoriteByFavoriteId(this.favoriteId).then(res => {
              if (res.result == 'SUCCESS') {
                this.isCollect = !this.isCollect;
                console.log('取消收藏成功');
              }
            })
          }
        }
      },
    },
    watch: {
      tab: {},
    },
    created() {
      let productId = 476;
      getCommdityDetailById(productId).then(res => {
        if (res.result == 'SUCCESS') {
          this.commodityData = res.data;
          if (this.commodityData.favoriteVo) {
            this.isCollect = true;
            this.favoriteId = this.commodityData.favoriteVo.favoriteId;
          }
        }
      });
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
    position: absolute;
    right: 10px;
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
    height: 70vh;
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
    width: 27.7vw;
    height: 27.7vw;
    border-radius: 4px;
    border: 1px solid #f3f3f3;
    float: left
  }

  .car-close {
    position: absolute;
    width: 18px;
    right: 1rem;
  }

  .sale-price {
    color: red;
    font-size: 16px;
    padding-top: 45px;
    /*padding-left: 15px*/
  }

  .sale-price span {
    color: red;
    font-size: 24px;
    font-weight: bold
  }

  .sale-no {
    /*padding-left: 15px;*/
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

  .content {
    width: 100% !important;
  }

  .content >>>img {
    max-width: 100%;
  }

  .toggle-cart-enter-active, .toggle-cart-leave-active {
    transition: all .3s ease-out;
  }

  .toggle-cart-enter, .toggle-cart-leave-active {
    transform: translateY(100%);
  }
</style>
