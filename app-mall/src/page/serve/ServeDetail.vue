<template>
  <div>
    <head-top :headTitle="'服务单详情'"></head-top>
    <div style="margin-top: 3rem;">
      <div>
        <div class="order-head">
          <div>进行退款中</div>
          <div style="font-size: 0.8rem">您的售后服务已审核通过，进行退款中！</div>
        </div>
        <img src="../../assets/img/common/aftersale_bg.png" width="100%">
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
        <div class="info-course">
          <div class="course-img">
            <img class="state" src="../../assets/img/serve/ic_aftersale_jindu_sele.png">
            <img class="state-line" src="../../assets/img/serve/progressbar01.png" height="2">
            <div style="height: 4rem;;">
              <div style="font-size: 0.8rem">提交申请</div>
              <div style="font-size: 0.6rem">2018-07-20</div>
              <div style="font-size: 0.6rem">01:10:40</div>
            </div>
          </div>
          <div class="course-img">
            <img class="state" src="../../assets/img/serve/ic_aftersale_jindu_sele.png">
            <img class="state-line" src="../../assets/img/serve/progressbar01.png" height="2">
            <div style="height: 4rem;;">
              <div style="font-size: 0.8rem">提交申请</div>
              <div style="font-size: 0.6rem">2018-07-20</div>
              <div style="font-size: 0.6rem">01:10:40</div>
            </div>
          </div>
          <div class="course-img">
            <img class="state" src="../../assets/img/serve/ic_aftersale_jindu_sele.png">
            <img class="state-line" src="../../assets/img/serve/progressbar01.png" height="2">
            <div style="height: 4rem;;">
              <div style="font-size: 0.8rem">提交申请</div>
              <div style="font-size: 0.6rem">2018-07-20</div>
              <div style="font-size: 0.6rem">01:10:40</div>
            </div>
          </div>
          <div class="course-img">
            <img class="state" src="../../assets/img/serve/ic_aftersale_jindu_sele.png">
            <div style="height: 4rem;;">
              <div style="font-size: 0.8rem">完成</div>
            </div>
          </div>
        </div>
        <div class="info-title" style="border: 0">
          退货原因：<span>个人原因</span>
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

  .order-head {
    position: absolute;
    margin: 1rem 1rem 1rem 1.5rem;
    color: #fff;
    line-height: 2
  }

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
      font-size: 0.9rem;
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

    .info-course {
      margin: 1rem 1rem 0;
      font-size: 0;
      .course-img {
        display: inline-block;
        width: 25%;
        text-align: center;
        vertical-align: top;
      }
      .state {
        width: 35%;
        vertical-align: middle;
      }
      .state-line {
        vertical-align: middle;
        position: absolute;
        width: 10%;
        margin-left: 2.25%;
        margin-top: 4%;
      }
    }
  }
</style>
