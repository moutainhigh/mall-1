<template>
  <div>
    <head-top :headTitle="'服务单详情'"></head-top>
    <div style="margin-top: 3rem;">
      <div style="width: 100%;height: 5rem;background-color: #f5ca1d;">

      </div>
      <div class="order-info">
        <div class="info-detail">
          <div>服务单号：<span style="">123132312</span></div>
          <div>申请时间：<span>2018-01-01 11:19</span></div>
        </div>
      </div>
      <div class="order-info">
        <div class="info-title">
          售后进度
        </div>
      </div>

      <div class="order-info">
        <div class="info-title">
          问题描述
        </div>
        <div class="quest">
          <div>我不想买这辆车了，想买其他车</div>
          <div class="time">2018-01-01 11:19</div>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
  import headTop from '../../components/header/head'
    export default {
        name: "ServeDetail",
      components: {
        headTop
      },
    }
</script>

<style lang="scss" scoped>

  .order-info {
    margin-top: 0.5rem;
    background-color: #fff;
    font-size: 1rem;

    .info-title {
      padding: 0.8rem 0;
      margin: 0 1rem;
      border-bottom: #ececec 1px solid;
    }

    .info-detail {
      padding: 0.8rem 1rem;
      line-height: 2;
      font-size: 0.8rem;
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

    .quest {
      color: #999;
      padding: 0.5rem 1rem;
      .time {
        margin-top: 0.5rem;
        font-size: 0.7rem;
      }
    }
  }
</style>
