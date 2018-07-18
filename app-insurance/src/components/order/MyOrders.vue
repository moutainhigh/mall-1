<template>
  <div>
    <scroller style="top: 0;font-size: 12px !important;"
              :on-refresh="refresh"
              :on-infinite="infinite"
              refresh-layer-color="#f5ca1d"
              loading-layer-color="#f5ca1d"
    >
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
      <div class="i-list" style="margin-top: 10px" v-for="order in orders" @click="detail(order.orderCode)">
        <div class="list-title">
          <div style="margin-left: 16px">保单号：{{order.orderCode}}</div>
          <div style="float: right;margin-right: 16px;color: #c01212;" v-text="orderState(order.orderState)"></div>
        </div>
        <div style="display: inline-block;">
          <img :src="order.insuranceProduct.prodImg">
        </div>
        <div class="i-list-detail">
          <div class="dt-title">{{order.insuranceProduct.prodName}}</div>
          <div class="dt-intro">{{order.insuranceProduct.description}}</div>
          <div class="dt-content">{{order.insuranceProduct.tags}}</div>
          <div class="dt-price">
            <div>
              <div class="dt-price-pro">
                ￥{{order.insuranceProductPrice.price}}
              </div>
              <!--<button>-->
              <!--立即投保-->
              <!--</button>-->
            </div>
          </div>
        </div>
      </div>
      <!-- custom infinite spinner -->
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
</template>

<script>
  import {getOrders} from "../../service/getData";
  import storage from "../../store/storage";

  export default {
    name: "myOrders",
    meta: {
      title: '我的保单'
    },
    data() {
      return {
        orders: [],
        pageQuery: {
          page: 1,
          pageSize: 10,
          total: 1
        },
        isInfinite: true
      }
    },
    methods: {
      refresh(done) {
        this.pageQuery.page = 1;
        getOrders(this.pageQuery).then(res => {
          if (res.result == 'SUCCESS') {
            this.orders = res.data.content;
            this.pageQuery.total = res.data.totalPages;
            this.pageQuery.page++;
          }
          done()
        });
      },

      infinite(done) {
        if (this.pageQuery.page > this.pageQuery.total || !this.isInfinite) {
          done(true);
          return;
        }
        this.getOrders(done);
      },
      getOrders(done) {
        getOrders(this.pageQuery).then(res => {
          console.log(res);
          if (res.status == 401) {
            this.isInfinite = false;
          }
          if (res.result == 'SUCCESS') {
            this.orders = this.orders.concat(res.data.content);
            this.pageQuery.total = res.data.totalPages;
            this.pageQuery.page++;
          } else {
            done(true);
          }
          done()
        });
      },
      orderState(state) {
        switch (state) {
          case "UN_PAID":
            return "待支付";
          case "ON_PAID":
            return "已支付";
          case "BEEN_COMPLETED":
            return "已完成";
          case "UN_SURRENDER":
            return "退保审核";
          case "ON_SURRENDER":
            return "已退保";
        }
      },
      detail(orderCode) {
        // window.location.href = "http://mall.999shuijingqiu.com//admin/insurance/prints.do?orderId=" + orderId;
        this.$router.push({
          path:'/order-detail',
          query:{
            orderCode:orderCode
          }
        })
      }
    },
    created() {
      if (this.$route.query.token) {
        storage.save("token", this.$route.query.token);
      }
    }
  }
</script>

<style scoped>
  .list-title div {
    display: inline-block;
    margin-top: 10px;
    font-size: 14px;
  }

  ._v-container > ._v-content > .pull-to-refresh-layer {
    width: 100%;
    height: 60px;
    margin-top: -60px;
    text-align: center;
    font-size: 12px !important;
    color: #AAA;
  }

  ._v-container > ._v-content > .loading-layer {
    width: 100%;
    height: 60px;
    text-align: center;
    font-size: 12px !important;
    line-height: 60px;
    color: #AAA;
    position: relative;
  }
</style>
