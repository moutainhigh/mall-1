<template>
  <div>
    <head-top :go-back="true" :headTitle="headTitle"></head-top>
    <div style="margin-top: 3rem;" v-if="isShow">
      <div style="background-color: #ffffff" @click="chooseAddr">
        <div style="font-size: 1.2rem;padding: 0.9rem 0.8rem 0.5rem 0.8rem;font-weight: bold">
          {{tempOrder.deliveryAddressVO.consigneeName}} {{tempOrder.deliveryAddressVO.consigneeMobile}}
        </div>
        <div style="padding: 0 0.9rem;font-size: 1rem;">
          <div style="float:right;"><img src="../../assets/img/common/ic_right.png" style="width: 1.2rem;"></div>
          <span class="default_addr" v-if="tempOrder.deliveryAddressVO.defaultAddress">默认</span>{{tempOrder.deliveryAddressVO.consigneeAddress}}
        </div>
        <img src="../../assets/img/order/ic_indent_sitetx.png" width="100%" style="vertical-align: bottom;">
      </div>

      <div class="buyMode" @click="checkType = true">
        <p class="selectItem-title">支付方式</p>
        <p class="selectItem-detail" style="color: #f5ca1d">{{tempOrder.paymentType[orderConfirm.paymentType]}}</p>
        <img src="../../assets/img/cardetail/ic_right.png" style="width: 1.2rem;">
      </div>

      <div style="background: #ffffff">
        <p class="shopName">{{tempOrder.sellerVo.sellerName}}</p>
        <div class="orderIntro">
          <img :src="tempOrder.tempOrderItemVO.defaultPicPath">
          <p class="orderCarTitle">{{tempOrder.commodityTitle}}</p>
          <span class="orderCarConfig">{{setSpec(tempOrder.tempOrderItemVO.productName)}}</span>
          <p class="orderCarPrice">￥<span>{{setTranPrice(tempOrder.tempOrderItemVO.salePrice)}}</span>万</p>
        </div>
        <div style="padding: 2px 0">
          <p class="carInfo" v-for="(spec,key) in tempOrder.specs">{{key}}<span>{{spec}}</span></p>
        </div>
      </div>
      <p class="agree">点击提交即视为同意<span style="color: #333333">《隐私政策》</span>
      </p>
      <div style="height: 70px">
        <div class="i-footer">
          <button @click="submit">
            <div>提交</div>
          </button>
        </div>
      </div>
      <alert v-model="isAlert" :title="'温馨提示'" :content="'您的信用额度不够，无法贷款购买此款车型，请选择其他车型'" :button-text="'我知道了'"></alert>

      <div v-if="checkType" @click="openChoose" style="background-color: rgba(0,0,0,0.6);position:fixed;top: 0;width: 100%;height: 100%;z-index: 1"></div>

      <div class="type-mode" style="z-index: 3" v-if="checkType" >
        <div class="modeTitle">
          <span>支付方式</span>
          <img @click="checkType = false" src="../../assets/img/cardetail/ic_sku_close.png">
        </div>
        <div style="margin-left: 14px">
          <button v-for="(type,key) in tempOrder.paymentType" class="carColor" :class="{'activeColor': key == buyType}"
                  @click="checkMode(key)">
            {{type}}
          </button>
        </div>
        <div style="height: 70px">
          <div class="i-footer">
            <button @click="subType">
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
  import {XInput, Cell, Alert} from 'vux'
  import {addOrder, getTempOrder} from "../../service/getData";
  import {goodsSpec, tranPrice} from "../../config/dataFormat";
  import storage from "../../store/storage";
  import {ADDRESS} from "../../config/constant";

  export default {
    name: "OrderComfirm",
    components: {
      headTop,
      XInput,
      Cell,
      Alert
    },
    data() {
      return {
        headTitle: '订单确认',
        buyType: '',
        checkType: false,
        isAlert: false,
        isShow:false,
        tempOrder:'',
        orderConfirm:{
          paymentType:null,
          addressId:0,
          sellerId:0,
          orderConfirmProductList:[{
            productNum:1,
            productId:0
          }]
        }
      }
    },
    methods: {
      checkMode(key) {
        this.buyType = key;
      },
      subType(){
        this.orderConfirm.paymentType = this.buyType;
        this.checkType = false;
      },
      openChoose(){
        this.checkType = false;
        this.buyType = this.orderConfirm.paymentType;
      }
      ,
      chooseAddr() {
        this.$router.push({
          path: '/choose-address'
        })
      },
      submit() {
        addOrder(this.orderConfirm).then(res=>{
          if (res.result == 'SUCCESS') {
            this.$router.push({
              path:'/order-success'
            })
          }else {
            this.$vux.toast.text("提交失败,请稍后重试！",'middle');
          }
        });
      },
      async getCustomerAddr(productId,payType) {
        await getTempOrder(productId,payType).then(res=>{
          if (res.result == 'SUCCESS') {
            this.tempOrder = res.data;
            this.orderConfirm.sellerId = this.tempOrder.sellerVo.sellerId;
            if (storage.fetchSession(ADDRESS) && storage.fetchSession(ADDRESS).length != 0) {
              this.tempOrder.deliveryAddressVO = storage.fetchSession(ADDRESS);
              this.orderConfirm.addressId = this.tempOrder.deliveryAddressVO.addressId;
              this.isShow = true;
            }else if (this.tempOrder.deliveryAddressVO) {
              this.orderConfirm.addressId = this.tempOrder.deliveryAddressVO.addressId;
              this.isShow = true;
            }else {
              let _this = this;
              this.$vux.confirm.show({
                title:'未填写地址',
                content:'立即去填写地址?',
                // 组件除show外的属性
                onCancel () {
                  _this.$router.go(-1);
                },
                onConfirm () {
                  _this.$router.push({
                    path:"/my-address"
                  });
                }
              })
            }
          }
        });
      },
      setTranPrice(price) {
        return tranPrice(price);
      },
      setSpec(spec){
        return goodsSpec(spec);
      },
    },
    created() {
      let productId = this.$route.query.productId;
      let payType = this.$route.query.payType;
      if (productId && payType){
        this.getCustomerAddr(productId,payType);
        this.orderConfirm.orderConfirmProductList[0].productId = productId;
        this.orderConfirm.paymentType = payType;
        this.buyType = payType;
      }else {
        let _this = this;
        this.$vux.alert.show({
          content: '未能获取到订单信息',
          onHide () {
            _this.$router.go(-1);
          }
        })
      }
    },
    beforeDestroy(){
      storage.removeSession(ADDRESS);
    }
  }
</script>

<style scoped>
  .buyMode {
    position: relative;
    height: 40px;
    background: #ffffff;
    margin-bottom: 10px;
    margin-top: 10px;
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

  .shopName {
    font-size: 16px;
    padding: 12px 14px
  }

  .orderIntro {
    height: 106px;
    line-height: 1.5;
    background: #FAFAFA
  }

  .orderIntro img {
    width: 78px;
    height: 78px;
    padding: 14px;
    float: left;
    border-radius: 6px
  }

  .orderCarTitle {
    margin: 0;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    padding-top: 14px
  }

  .orderCarConfig {
    font-size: 14px;
    color: #888888
  }

  .orderCarPrice {
    color: #F54E4E
  }

  .orderCarPrice span {
    font-size: 25px;
    font-weight: bold
  }

  .carInfo {
    padding: 7px 15px;
    font-size: 15px
  }

  .carInfo span {
    font-weight: bold;
    float: right
  }

  .agree {
    color: #888888;
    font-size: 13px;
    padding: 8px 14px
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

  .default_addr {
    font-size: 0.8rem;
    background-color: #f5ca1d;
    color: #fff;
    padding: 0.1rem 0.4rem;
    border-radius: 25px;
    margin-right: 0.2rem;
  }
</style>
