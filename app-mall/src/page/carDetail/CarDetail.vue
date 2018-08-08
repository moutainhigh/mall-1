<template>
  <div>
    <div style="height: 3rem"></div>
    <head-top :go-back="true" :headTitle="headTitle" :share="true" v-bind:style="{ opacity: opacity }">
      <img style="width: 20px; position: absolute" src="../../assets/img/common/ic_nav_share.png">
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

    <div :ref="`detail`">
      <div>
        <swiper :aspect-ratio="0.749" auto style="margin:0 auto;" dots-position="center">
          <swiper-item class="swiper-demo-img" v-for="(img, index) in commodityData.imageSet" :key="index">
            <img width="100%" :src="img" v-preview="img"></swiper-item>
        </swiper>
      </div>

      <div class="carPrice">
        <div class="price">
          <p class="presentPrice">￥<span>{{setTranPrice(commodityData.sellPrice)}}</span>万</p>
          <p class="guidePrice" v-if="commodityData.priceSectionVo">
            指导价：￥{{setTranPrice(commodityData.priceSectionVo.startPrice)}} -
            {{setTranPrice(commodityData.priceSectionVo.endPrice)}}万</p>
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
        <p class="rank-detail">{{commodityData.showLevel}}</p>
      </div>
      <div class="selectItem" @click="checkProducts">
        <p class="selectItem-title">规格选择</p>
        <p v-if="standard[0] == ''" class="selectItem-detail">请选择</p>
        <p v-if="standard[0] != ''" class="selectItem-detail">
          <span>{{standard[0]}} {{standard[1]}} {{standard[2]}}</span></p>
        <img src="../../assets/img/cardetail/ic_right.png">
      </div>
      <div class="buyMode" @click="checkType = 'mode'">
        <p class="selectItem-title">支付方式</p>
        <p v-if="mode == ''" class="selectItem-detail">请选择</p>
        <p v-if="mode != ''" class="selectItem-detail" style="color: #f5ca1d">{{commodityData.paymentType[mode]}}</p>
        <img src="../../assets/img/cardetail/ic_right.png">
      </div>
      <div style="background: #f3f3f3; height: 1px; margin-left: 10px; margin-right: 10px"></div>

      <div class="shopInfo">
        <p class="shopName" v-if="commodityData.sellerVo">{{commodityData.sellerVo.sellerName}}<span>【现货】</span></p>
        <p class="shopAddress" v-if="commodityData.sellerVo">{{commodityData.sellerVo.sellerAddress}}</p>
        <p class="shopTel" v-if="commodityData.sellerVo">联系电话：{{commodityData.sellerVo.mobile}}</p>
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

      <div :ref="`config`">
        <carConfig :TableData="commodityData.specs"></carConfig>
      </div>

      <div :ref="`explain`">
        <carExplain :explainContent="commodityData.explainContent"></carExplain>
      </div>

      <div style="height: 70px">
        <div class="i-footer">
          <button @click="toOrderComfirm">
            <div>立即抢购</div>
          </button>
        </div>
      </div>
    </div>

    <div v-show="checkType != 'none'" class="carType">
      <div style="height: 30vh; width: 100vw" @click="checkType = 'none'"></div>
      <transition name="toggle-cart">
        <div v-if="checkType == 'standard'" class="type-standard">
          <div class="car-sale">
            <div style="width: 27.7vw; float: left">
              <img class="car-thumbnail" :src="commodityData.defaultPicPath">
            </div>
            <img class="car-close" src="../../assets/img/cardetail/ic_sku_close.png" @click="checkType = 'none'">
            <div style="width: 60vw; float: right">
              <p class="sale-price">￥<span>{{setTranPrice(commodityData.sellPrice)}}</span>万</p>
              <p class="sale-no">商品编号：{{commodityData.commodityCode}}</p>
            </div>
          </div>
          <div style="overflow-y: auto; height: 300px">
            <div v-for="(productGroup, index2) in productGroups" :key="index2">
              <p class="carType-title">{{productGroup.groupName}}</p>
              <div style="padding: 0 14px">
                <button v-for="(attribute, index) in productGroup.attributes" class="carColor" :id='attribute.attributeName' :index='index' :name='attribute.attributeName'
                        :class="{'activeColor':index === iac[index2] && disabledButton.indexOf(attribute.attributeName) != -1  }"
                        @click="checkAttribute(index2, index)"
                        :disabled="disabledButton.indexOf(attribute.attributeName) == -1 && index2 !== 0">
                  {{attribute.attributeName}}
                </button>

                    <!-- <button v-for="(attribute, index) in productGroup.attributes" class="carColor"
                        :class="{'activeColor':index2 == ceilIndex && index == activeIndex}"
                        @click="checkAttribute(index2, index)"
                        >
                        && disabledButton.indexOf(attribute.attributeName) < 0
                  {{attribute.attributeName}}
                </button> -->
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
            <button v-for="(type, key) in commodityData.paymentType" class="carColor"
                    :class="{'activeColor': key == activeMode}"
                    @click="checkMode(key)">
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
  import carConfig from './CarConfig'
  import {
    addCommodityFavorite,
    delFavoriteByFavoriteId,
    getCommdityDetailById, getProductsByCommodityId
  } from "../../service/getData";
  import {Swiper, SwiperItem} from 'vux'
  import {tranPrice} from "../../config/dataFormat";
  import storage from "../../store/storage";

  export default {
    name: "CarDetail",
    components: {
      headTop,
      carExplain,
      carConfig,
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
        productGroups: [],
        defaultAttribute: [],
        iac: [],
        disabledButton: [],
        checkProductId: null,
        standard: [],
        activeMode: '',
        mode: '',
        show: false,
        favoriteId: null,
        opacity: 1, //顶部透明度
        Data:[],
      }
    },
    methods: {
      checkTab(tabNum) {
        this.tab = tabNum;
        switch (tabNum) {
          case 1:
            window.scrollTo(0, this.$refs['detail'].offsetTop - 50);
            break;
          case 2:
            window.scrollTo(0, this.$refs['config'].offsetTop - 50);
            break;
          case 3:
            window.scrollTo(0, this.$refs['explain'].offsetTop - 50);
            break
        }
      },
      //根据商品id获取货品
      getProducts() {
        getProductsByCommodityId(this.commodityData.commodityId).then(res => {
          if (res.result == 'SUCCESS') {
            this.productGroups = res.data;
            this.Data= this.commodityData.productVo.productName;
            this.defaultAttribute = this.commodityData.productVo.productName.split("&");
            this.disabledButton = this.commodityData.productVo.productName;
            for (let i = 0; i < this.productGroups.length; i++) {
              for (let j = 0; j < this.productGroups[i].attributes.length; j++) {
                //判断默认货品
                if (this.defaultAttribute[i].indexOf(this.productGroups[i].groupName) > -1 && this.defaultAttribute[i].indexOf(this.productGroups[i].attributes[j].attributeName) > -1) {
                  this.iac[i] = j;
                  this.iac = this.iac.concat([]);
                }
              }
            }

            let name = '';
            //判断默认货品属性
            for (let i = 0; i < this.iac.length; i++) {
              if (this.productGroups[i].attributes[this.iac[i]]) {
                if (name != '') {
                  name = name + '&'
                }
                name = name + this.productGroups[i].groupName + '：' + this.productGroups[i].attributes[this.iac[i]].attributeName;
                //页面显示选中信息
                this.standard.push(this.productGroups[i].attributes[this.iac[i]].attributeName);
              }
            }
          }
        })
      },
      checkProducts() {
        this.checkType = 'standard';
      },
      checkAttribute(index2, index) {
        this.iac[index2] = index;
        this.iac = this.iac.concat([]);
        // console.log(this.iac)
        this.disabledButton ='';

        if(index2 ==0){
          this.Data = [];
          //表示第一项，通过颜色去筛选数据
          for (let k = 0; k < this.commodityData.productVos.length; k++){
             if (this.commodityData.productVos[k].productName.indexOf(this.productGroups[index2].attributes[index].attributeName) !=  -1 ) {
                this.Data.push(this.commodityData.productVos[k].productName);
            }
          }
        }

        if(index2 == 1 || index2 == 2){
          //表示不是第一项
          for (let i = 0; i < this.Data.length; i++){
            //拿到第一项选择颜色后的数据,选择排量
              if (this.Data[i].indexOf(this.productGroups[index2].attributes[index].attributeName) !=  -1 ) {
                 this.Data = this.Data[i];
              }
          }
        }
        //用于判断是否置灰，禁止点击
        for(let m = 0;m <this.Data.length;m++){

            this.disabledButton += this.Data[m];
        }
        for (let i = 0; i < this.productGroups.length; i++) {
              for (let j = 0; j < this.productGroups[i].attributes.length; j++) {
                //判断选择的货品

                if (this.Data[0].indexOf(this.productGroups[i].groupName) > -1 && this.Data[0].indexOf(this.productGroups[i].attributes[j].attributeName) > -1) {
                  this.iac[i] = j;
                  this.iac = this.iac.concat([]);
                }

              }
        }
      },
      checkMode(key) {
        this.activeMode = key;
      },
      //规格选择
      selectStandard() {
        let name = '';
        this.standard = [];


        //判断选中货品属性
        for (let i = 0; i < this.iac.length; i++) {
          if (this.productGroups[i].attributes[this.iac[i]]) {
            if (name != '') {
              name = name + '&'
            }
            name = name + this.productGroups[i].groupName + '：' + this.productGroups[i].attributes[this.iac[i]].attributeName;
            //页面显示选中信息
            this.standard.push(this.productGroups[i].attributes[this.iac[i]].attributeName);
          }
        }
        if(this.Data.length > 1 ){
          if(this.Data.length > 20){
            this.Data = this.Data;
          }else{
               this.Data = this.Data[0];
          }
        }
        if(this.Data != name ){
              this.$vux.toast.text("请选择排量");
               return;

        }

        for (let j = 0; j < this.commodityData.productVos.length; j++) {
          if (name == this.commodityData.productVos[j].productName) {
            this.checkProductId = this.commodityData.productVos[j].productId;
          }
        }
        this.checkType = 'none';
      },
      //支付方式
      selectMode() {
        this.mode = this.activeMode;
        this.checkType = 'none';
      },
      //滑动
      menu() {
        this.scroll = document.documentElement.scrollTop || document.body.scrollTop;
        if (this.scroll <= 90 && this.tab == 1) {
          this.headTitle = '汽车详情';
        } else {
          this.headTitle = '';
        }
        //透明度
        if (this.scroll <= 200 && this.scroll >60){
          this.opacity = 0.9 - ((this.scroll - 60) / 20 * 0.1);
        } else if (this.scroll > 200 && this.scroll < 360) {
          this.opacity = 1 - ((360 - this.scroll) / 20 * 0.1);
        }

        //监听页面滚动切换tab
        if (this.scroll >= 90 && this.scroll < this.$refs['config'].offsetTop) {
          this.tab = 1;
        }
        if (this.scroll >= this.$refs['config'].offsetTop - 50 && this.scroll < this.$refs['explain'].offsetTop - 50) {
          this.tab = 2;
        }
        if (this.scroll >= this.$refs['explain'].offsetTop - 50) {
          this.tab = 3;
        }
      },
      //立即抢购
      toOrderComfirm() {
        if (this.mode == '') {
          this.$vux.alert.show({
            content: '请选择支付方式',
          });
          return false;
        }
        this.$router.push({
          path: "/order-comfirm",
          query: {
            productId: this.checkProductId,
            payType: this.mode
          }
        })
      },
      //价格转换格式
      setTranPrice(price) {
        return tranPrice(price);
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
      //初始化根据货品id获取商品数据
      getCommodityDetail(productId) {
        getCommdityDetailById(productId).then(res => {
          if (res.result == 'SUCCESS') {
            this.commodityData = res.data;
            this.checkProductId = this.commodityData.productVo.productId;
            if (this.commodityData.favoriteVo) {
              this.isCollect = true;
              this.favoriteId = this.commodityData.favoriteVo.favoriteId;
            }
            //获取货品属性
            this.getProducts();
            //添加一条浏览记录
            this.saveBrowseRecords(this.commodityData);
          }
        });
      },
      //浏览记录
      saveBrowseRecords(commodityData) {
        let records = storage.fetch("records");
        if (records.length == 0) {
          let list = [];
          list.push(commodityData);
          storage.save("records", list);
        } else {
          if (records.length == 20) {
            records.splice(0, 1);
            records.push(commodityData);
            storage.save("records", records);
          }
        }
      }
    },
    created() {
      let query = this.$route.query;
      this.getCommodityDetail(query.productId);
    },
    mounted() {
      window.addEventListener('scroll', this.menu)
    },
    destroyed(){
      window.removeEventListener('scroll', this.menu)
    },

    beforeRouteLeave(to, from, next) {
      // 设置下一个路由的 meta
      if (to.path == '/car-list') {
        to.meta.keepAlive = true;  // B 跳转到 A 时，让 A 缓存，即不刷新
      }
      next();
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

  .content >>> img {
    max-width: 100%;
  }

  .toggle-cart-enter-active, .toggle-cart-leave-active {
    transition: all .3s ease-out;
  }

  .toggle-cart-enter, .toggle-cart-leave-active {
    transform: translateY(100%);
  }
</style>
