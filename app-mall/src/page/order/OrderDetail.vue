<template>
  <div>
    <head-top :headTitle="'订单详情'"></head-top>
    <div style="margin-top: 3rem;">
      <div>
        <div class="order-head">
          <img style="vertical-align: middle" src="../../assets/img/order/ic_aftersale_finish.png"/>
          <div style="display: inline-block;margin-left: 0.5rem;vertical-align: middle">进行退款中</div>
        </div>
        <div style="position:absolute;right: 0;margin: 2rem 1rem;font-size: 0.8rem;color: #ffffff;">
          剩余59分 50秒
        </div>
        <img src="../../assets/img/common/aftersale_bg.png" width="100%">
      </div>
      <div class="goods-info">
        <div style="padding:0.8rem 1rem;">
          <div class="info-title">
            商品信息
          </div>
          <div class="info-addr">
            <span>深圳东通上汽大众</span>
          </div>
        </div>
      </div>

      <div class="good-info" v-for="item in order.orderItemDetails">
        <!--<img src="../../assets/img/home/1.png" class="good-img">-->
        <img :src="item.productImg" class="good-img">
        <div class="good-detail">
          <div style="height: 2.6rem;word-break: break-all;">{{item.commodityTitle}}</div>
          <div class="good-cate">{{setSpec(item.productName)}} <span style="float: right;line-height: 1.6">x {{item.productNum}}</span></div>
        </div>
      </div>

      <div class="good-total">
        <div style="display: inline-block;padding: 1rem;">共<span> {{order.prodQuantity}} </span>件商品　合计：<span style="color: #F54E4E;">￥23.98 万</span></div>
      </div>

      <div class="order-info">
        <div class="info-title">订单信息</div>
        <div class="info-detail">
          <div>订单编号：<span style="">{{order.orderCode}}</span>
            <div class="info-copy">复制</div>
          </div>
          <div>提交时间：<span>{{order.createTime}}</span></div>
          <div>支付方式：<span>{{setPayType(order.paymentType)}}</span></div>
          <div v-if="order.paymentTime">付款时间：<span>{{order.paymentTime}}</span></div>
        </div>
      </div>
    </div>

    <footer class="order-pay" v-if="order.orderState == 'PAID_PAYMENT' || order.orderState == 'OUT_STOCK'">
      <div class="order-btn" @click="takeGood">
        确认收货
      </div>
      <div class="order-btn" @click="openServe">
        申请售后
      </div>
    </footer>
    <footer class="order-pay" v-if="order.orderState == 'PENDING_PAYMENT'">
      <div class="order-price">
        应付金额：<span>￥{{order.totalPrice}}万</span>
      </div>
      <div class="order-btn" @click="cancelOrder">
        取消订单
      </div>
    </footer>

  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import {cancelOrder, confirmOrder, getOrderDetailById} from "../../service/getData";
  import {goodsSpec, orderState, payType} from "../../config/dataFormat";

  export default {
    name: "OrderDetail",
    components: {
      headTop
    },
    data(){
      return {
        order : {}
      }
    },
    methods:{
      setState(state){
        return orderState(state);
      },
      setSpec(spec){
        return goodsSpec(spec);
      },
      setPayType(type){
        return payType(type);
      },
      takeGood(){
        let _this = this;
        this.$vux.alert({
          title: "提示",
          message: '确定收货？',
          showCancelButton: true
        }).then(() => {
          confirmOrder(_this.order.orderId).then(res=>{
            if (res.result == 'SUCCESS') {
              this.$vux.toast.text('修改成功','middle');
              _this.order.orderState = 'RECEIVED';
            } else {
              this.$vux.toast.text('修改失败，请稍后重试！','middle');
            }
          });
        }).catch(() => {
        })
      },
      openServe(){
        let _this = this;
        this.$router.push({
          path:'/refund',
          query:{
            orderId:_this.order.orderId
          }
        })
      },
      cancelOrder(){
        this.$vux.alert({
          title: "提示",
          message: '确定要取消订单？',
          showCancelButton: true
        }).then(() => {
          cancelOrder(_this.order.orderId).then(res=>{
            if (res.result == 'SUCCESS') {
              Toast({
                duration: 1000,
                message: '取消成功',
                type: 'text'
              });
              _this.order.orderState = 'CANCELED';
            } else {
              Toast({
                duration: 1000,
                message: '取消成功，请稍后重试！',
                type: 'text'
              })
            }
          });
        }).catch(() => {
        })
      }
    },
    created(){
      let orderId = this.$route.query.orderId;
      getOrderDetailById(orderId).then(res=>{
        if (res.result == 'SUCCESS') {
          this.order = res.data;
        }
      });
    }
  }
</script>

<style lang="scss" scoped>

  .order-head {
    position: absolute;
    margin: 1.5rem;
    color: #fff;
    line-height: 2
  }

  .goods-info {
    margin-top: .5rem;
    background-color: #fff;

    .info-title {
      display: inline-block;
      font-size: 0.8rem;
    }
    .info-addr {
      float: right;
      font-size: 0.8rem;
      color: #999;
    }
  }

  .good-info {
    display: flex;
    height: 7rem;

    .good-detail {
      flex: 1;
      padding: 1rem 0.6rem 0 0;
      color: #333;
      .good-cate {
        margin-top: 0.7rem;
        font-size: 0.8rem;
        color: #999;
      }
    }
    .good-img {
      width: 7.5rem;
      padding: 1rem 0.5rem 1rem 1rem;
    }
  }

  .good-total {
    background-color: #fff;
    font-size: 0.8rem;
    text-align: right;
    color: #333;
  }

  .order-info {
    margin-top: 0.5rem;
    background-color: #fff;
    font-size: 0.8rem;

    .info-title {
      padding: 0.8rem 1rem;
      border-bottom: #ececec 1px solid;
    }

    .info-detail {
      padding: 0.8rem 1rem;
      line-height: 2;

      .info-copy {
        display: inline-block;
        float: right;
        color: #f5ca1d;
        border: #f5ca1d 1px solid;
        border-radius: 5px;
        font-size: 0.5rem;
        line-height: 1.2;
        padding: 0 0.4rem;
      }

      span {
        padding-left: 0.2rem;
        color: #999
      }
    }
  }

  .order-pay {
    background: #ffffff;
    bottom: 0;
    position: absolute;
    width: 100%;

    .order-price {
      display: inline-block;
      padding: 0.5rem 1rem;
      font-size: 0.8rem;
      line-height: 2.1;

      span {
        color: #F54E4E;
      }
    }

    .order-btn {
      display: inline-block;
      font-size: 0.8rem;
      color: #666;
      float: right;
      border: #DCDCDC 1px solid;
      border-radius: 2.5rem;
      margin: 0.5rem;
      padding: 0.2rem 0.5rem;
    }
  }
</style>
