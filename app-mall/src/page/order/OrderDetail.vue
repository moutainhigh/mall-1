<template>
  <div>
    <head-top :headTitle="'订单详情'"></head-top>
    <div style="margin-top: 3rem;">
      <div style="width: 100%;height: 5rem;background-color: #f5ca1d;">

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

      <div class="good-info">
        <img src="../../assets/img/home/1.png" class="good-img">
        <div class="good-detail">
          <div style="height: 2.6rem;">2018款 240TURBO自动两驱舒适版</div>
          <div class="good-cate">天漠金 <span>2.0L自动</span> <span style="float: right;line-height: 1.6">x 1</span></div>
        </div>
      </div>

      <div class="good-total">
        <div style="display: inline-block;padding: 1rem;">共<span> 1 </span>件商品　合计：<span style="color: #F54E4E;">￥23.98 万</span></div>
      </div>

      <div class="order-info">
        <div class="info-title">订单信息</div>
        <div class="info-detail">
          <div>订单编号：<span style="">123132312</span>
            <div class="info-copy">复制</div>
          </div>
          <div>提交时间：<span>2018-01-01 11:19</span></div>
          <div>支付方式：<span>线下支付</span></div>
        </div>
      </div>
    </div>

    <footer class="order-pay">
      <div class="order-price">
        应付金额：<span>￥23.98万</span>
      </div>
      <div class="order-btn">取消订单
      </div>
    </footer>

  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "OrderDetail",
    components: {
      headTop
    },

  }
</script>

<style lang="scss" scoped>

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
      font-size: 0.9rem;
      color: #666;
      float: right;
      border: #DCDCDC 1px solid;
      border-radius: 2.5rem;
      margin: 0.5rem;
      padding: 0.2rem 0.5rem;
    }
  }
</style>
