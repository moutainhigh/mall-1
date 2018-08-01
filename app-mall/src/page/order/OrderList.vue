<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>
    <div class="tab">
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == ''}" @click="select('')">全部</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 'PENDING_PAYMENT'}" @click="select('PENDING_PAYMENT')">待付款</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 'OUT_STOCK'}" @click="select('OUT_STOCK')">待收货</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 'SUCCESS'}" @click="select('SUCCESS')">已完成</p></div>
      <div class="tabItem"><p class="itemButton" :class="{'active': tab == 'CANCELED'}" @click="select('CANCELED')">已取消</p></div>
    </div>
    <div style="position: absolute;top: 0;width: 100%;height: 100%;overflow: hidden;z-index: -1;">
      <scroller style="margin-top: 5.7rem;font-size: 12px !important;position:relative;"
                :on-refresh="refresh"
                :on-infinite="infinite"
                refresh-layer-color="#f5ca1d"
                loading-layer-color="#f5ca1d">
        <svg class="spinner" style="fill: #f5ca1d;" slot="refresh-spinner" viewBox="0 0 64 64">
          <g>
            <circle cx="16" cy="32" stroke-width="0" r="3">
              <animate attributeName="fill-opacity" dur="750ms" values=".5;.6;.8;1;.8;.6;.5;.5"
                       repeatCount="indefinite"></animate>
              <animate attributeName="r" dur="750ms" values="3;3;4;5;6;5;4;3" repeatCount="indefinite"></animate>
            </circle>
            <circle cx="32" cy="32" stroke-width="0" r="3.09351">
              <animate attributeName="fill-opacity" dur="750ms" values=".5;.5;.6;.8;1;.8;.6;.5"
                       repeatCount="indefinite"></animate>
              <animate attributeName="r" dur="750ms" values="4;3;3;4;5;6;5;4" repeatCount="indefinite"></animate>
            </circle>
            <circle cx="48" cy="32" stroke-width="0" r="4.09351">
              <animate attributeName="fill-opacity" dur="750ms" values=".6;.5;.5;.6;.8;1;.8;.6"
                       repeatCount="indefinite"></animate>
              <animate attributeName="r" dur="750ms" values="5;4;3;3;4;5;6;5" repeatCount="indefinite"></animate>
            </circle>
          </g>
        </svg>
        <div class="user-order-list" v-for="order in orders"  @click="openDetail(order.orderId)">
          <dl>
            <dt style="border-top: 0;padding-left: 0.875rem">
              <span class="orderNo">{{order.sellerAddress}}</span>
              <span class="orderState">{{setState(order.orderState)}}</span>
            </dt>
            <div class="car_list">
              <div class="list_item" v-for="product in order.orderItemDetails">
                <div class="cont_img">
                  <img src="../../assets/img/home/1.png" width="100%">
                </div>
                <div class="cont">
                  <div class="cont_title">
                    {{product.commodityTitle}}
                  </div>
                  <div class="cont_spec">
                    {{setSpec(product.productName)}}
                    <span style="float: right">X {{product.productNum}}</span>
                  </div>
                </div>
              </div>
            </div>
            <dt style="border-top: 0">
              <p style="float: right; font-size: 0.81rem; margin-right: 15px">共1件商品 合计：￥<span
                style="font-size: 0.93rem; font-weight: bold">23</span>.98万</p>
            </dt>
            <div>
              <button v-if="order.orderState == 'PENDING_PAYMENT'" class="order-button" @click="cancelOrder = true">取消订单</button>
              <button v-if="order.orderState == 'OUT_STOCK' || order.orderState == 'PAID_PAYMENT'" class="order-button" @click="cancelOrder = true">确认收货</button>
              <!--<button class="order-button" @click="cancelOrder = true">申请售后</button>-->
            </div>
          </dl>
        </div>
        <svg class="spinner" style="fill: #f5ca1d;" slot="infinite-spinner" viewBox="0 0 64 64">
          <g>
            <circle cx="16" cy="32" stroke-width="0" r="3">
              <animate attributeName="fill-opacity" dur="750ms" values=".5;.6;.8;1;.8;.6;.5;.5"
                       repeatCount="indefinite"></animate>
              <animate attributeName="r" dur="750ms" values="3;3;4;5;6;5;4;3" repeatCount="indefinite"></animate>
            </circle>
            <circle cx="32" cy="32" stroke-width="0" r="3.09351">
              <animate attributeName="fill-opacity" dur="750ms" values=".5;.5;.6;.8;1;.8;.6;.5"
                       repeatCount="indefinite"></animate>
              <animate attributeName="r" dur="750ms" values="4;3;3;4;5;6;5;4" repeatCount="indefinite"></animate>
            </circle>
            <circle cx="48" cy="32" stroke-width="0" r="4.09351">
              <animate attributeName="fill-opacity" dur="750ms" values=".6;.5;.5;.6;.8;1;.8;.6"
                       repeatCount="indefinite"></animate>
              <animate attributeName="r" dur="750ms" values="5;4;3;3;4;5;6;5" repeatCount="indefinite"></animate>
            </circle>
          </g>
        </svg>
      </scroller>
    </div>
    <div class="carType" v-if="cancelOrder">
      <div style="background: #ffffff; position: fixed; bottom: 0.875rem; left: 0.875rem; right: 0.875rem; border-radius: 10px">
        <div style="text-align: center; line-height: 3; border-bottom: 1px solid #ECECEC; position: relative">
          <img style="float: right; width: 0.93rem; position: absolute; right: 1rem; top: 1rem;" src="../../assets/img/common/ic_screen_close.png" @click="cancelOrder = false">
          取消订单
        </div>
        <p style="padding: 0.875rem; font-size: 0.93rem">取消订单后，本单享有的优惠会一并取消，是否继续？</p>
        <p style="font-size: 0.81rem; color: #999999; padding: 0.81rem 0.875rem">请选择取消订单的原因（必选）：</p>
        <div style="font-size: 0.81rem; color: #999999; padding-left: 0.875rem; line-height: 2">
          发票信息有误
        </div>
        <div style="font-size: 0.81rem; color: #999999; padding-left: 0.875rem; line-height: 2">
          商品买错了
        </div>
        <div style="font-size: 0.81rem; color: #999999; padding-left: 0.875rem; line-height: 2">
          重复下单/误下单
        </div>
        <div style="color: #F54E4E; text-align: center; border-top: 1px solid #ECECEC; line-height: 2;padding: 0.3rem;">
          确定
        </div>
      </div>
    </div>
    <!--<div style="height: 50px">-->
    <!--<div class="all">全部</div>-->
    <!--</div>-->
  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import {getCustomerOrder} from "../../service/getData";
  import {goodsSpec, orderState} from "../../config/dataFormat";

  export default {
    name: "OrderList",
    components: {
      headTop
    },
    data() {
      return {
        orders: [],
        headTitle: '我的订单',
        tab: '',
        cancelOrder: false,
        pageQuery: {
          pageNo: 0,
          pageSize: 10,
          orderState: ''
        },
        isInfinite: true
      }
    },
    methods: {
      refresh(done) {
        this.pageQuery.pageNo = 1;
        getCustomerOrder(this.pageQuery).then(res => {
          if (res.result == 'SUCCESS') {
            this.orders = res.data.data;
            if (res.data.pageCount <= this.pageQuery.pageNo) {
              this.isInfinite = false;
            } else {
              this.pageQuery.page++;
              this.isInfinite = true;
            }
          }
          done()
        });
      },

      infinite(done) {
        if (!this.isInfinite) {
          done(true);
          return;
        }
        this.getOrderList(done);
      },
      select(index) {
        if (this.tab != index) {
          this.tab = index;
          this.pageQuery = {
            pageNo: 1,
            pageSize: 10,
            orderState: index
          };
          this.orders = [];
          this.getOrderList();
        }
      },
      async getOrderList(done) {
        let _this = this;
        await getCustomerOrder(this.pageQuery).then(res => {
          if (res.result == 'SUCCESS') {
            this.orders = this.orders.concat(res.data.data);
            if (res.data.pageCount <= _this.pageQuery.pageNo) {
              _this.isInfinite = false;
            } else {
              _this.pageQuery.pageNo++;
              this.isInfinite = true;
            }
          } else {
            _this.isInfinite = false;
          }
          done();
        })
      },
      openDetail(orderId){
        this.$router.push({
          path:'/order-detail',
          query:{orderId:orderId}
        })
      },
      setState(state){
        return orderState(state);
      },
      setSpec(spec){
        return goodsSpec(spec);
      }
    },
    created() {
    }
  }
</script>

<style lang="scss" scoped>
  .tab {
    position: relative;
    width: 100vw;
    background: #ffffff;
    text-align: center;
    height: 42px;
    border-bottom: 1px solid #ececec;
    margin-top: 3rem;
  }

  .tabItem {
    float: left;
    width: 20vw;
    background: #ffffff
  }

  .active {
    color: #f5ca1d;
    border-bottom: 2px solid #f5ca1d;
    margin: 0 20px
  }

  .itemButton {
    font-size: 0.91rem;
    padding: 0.56rem 0;
    margin: 0;
    display: inline-block;
  }

  .user-order-list {
    background: #ffffff;
    overflow: hidden;
    margin-bottom: 0.5rem;
  }

  .user-order-list dl {
    background: #fff;
    padding: 0;
    margin-bottom: 0;
    margin-top: 0;
    border-bottom: solid 1px #ECECEC;
  }

  .user-order-list dl dt {
    border-top: solid 1px #E0E0E0;
    line-height: 3;
    overflow: hidden;
  }

  .user-order-list .myorderList {
    overflow: hidden;
    padding: 0.625rem 0.875rem;
    position: relative;
    background: #f9f9f9;
    margin-left: 0;
  }

  .user-order-list .myorderList .imgs {
    float: left;
    width: 7.75rem;
    height: 5.52rem;
    margin-right: 0.625rem;
  }

  .orderNo {
    color: #000;
    float: none;
    font-size: 0.91rem
  }

  .orderState {
    color: #F54E4E;
    float: right;
    padding-right: 15px;
    width: auto;
    font-size: 0.91rem
  }

  .order-button {
    background: none;
    border-radius: 29px;
    padding: 3px 1rem;
    border: solid 1px #DCDCDC;
    font-size: 0.93rem;
    color: #666666;
    float: right;
    margin: 10px 1rem;
    outline: none;
  }

  .all {
    position: fixed;
    bottom: 0;
    text-align: center;
    width: 100%;
    background-color: #ffffff;
    height: 44px;
    color: #F54E4E;
    line-height: 44px
  }

  .carType {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    z-index: 999;
  }

  .good-info {
    display: flex;
    height: 7rem;
    background-color: #F5F5F5;
    .good-detail {
      flex: 1;
      padding: 1rem 0.6rem 0 0;
      color: #333;
      font-size: 1rem;
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

  .car_list {
    background-color: #fff;
    .list_item {
      background-color: #f5f5f5;
      display: flex;
      padding: 0.5rem;
      line-height: 1.5;
      overflow: hidden;
      .cont_img {
        flex: 0 0 8rem;;
        img {
          border-radius: 4px;
          height: 5.3rem;
        }
      }
      .cont {
        flex: 1;
        padding: 0 0 0.7rem 0.7rem;
        height: 5rem;
        .cont_title {
          font-size: 1rem;
          height: 3rem;
          overflow:hidden;
          text-overflow:ellipsis;
          display:-webkit-box;
          -webkit-box-orient:vertical;
          -webkit-line-clamp:2;
        }
        .cont_spec {
          margin-top: 0.2rem;
          color: #999999;
          font-size: 0.8rem;
        }
      }
    }
  }
</style>
