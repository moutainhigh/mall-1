<template>
  <div>
    <head-top :headTitle="headTitle" :edit="true">
      <div slot="edit">
        <span v-if="isEdit == false" style="position: absolute; right: 1rem; top: 0.8rem; color: #888888"
              @click="isEdit = true">编辑</span>
        <span v-if="isEdit == true" style="position: absolute; right: 1rem; top: 0.8rem; color: #888888"
              @click="isEdit = false">完成</span>
      </div>
    </head-top>
    <div class="user-order-list" style="margin-top: 3rem">
      <dl>
        <dd class="myorderList">
          <img v-if="isEdit && check" class="checkIcon" src="../../assets/img/common/Checkmark_sele.png"
               @click="check = false">
          <img v-if="isEdit && !check" class="checkIcon" src="../../assets/img/common/Checkmark_nor.png"
               @click="check = true">
          <img class="imgs" src="../../assets/logo.png">
          <div class="carInfo" v-bind:class="{'isEdit': isEdit}">
            <p class="title">2018款 240TURBO自动两驱舒适版</p>
            <p class="price">￥<span>8.98</span>万</p>
            <p class="carLocal"><img src="../../assets/img/common/ic_list_location.png">深圳中升汇宝宝马4S店</p>
          </div>
        </dd>
      </dl>
    </div>

    <div style="height: 3.125rem" v-if="isEdit">
      <div class="editFooter">
        <img v-if="isEdit && check" class="allCheckIcon" src="../../assets/img/common/Checkmark_sele.png"
             @click="check = false">
        <img v-if="isEdit && !check" class="allCheckIcon" src="../../assets/img/common/Checkmark_nor.png"
             @click="check = true">
        <span style="display: inline-block; padding-top: 0.9rem">全选</span>
        <button class="deleteButton">删除</button>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "CollectList",
    components: {
      headTop
    },
    data() {
      return {
        headTitle: '我的收藏',
        isEdit: false,
        check: false
      }
    }
  }
</script>

<style scoped>
  .user-order-list {
    background: #ffffff;
    overflow: hidden;
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
    padding: 0.625rem 0.875rem 0 0.875rem;
    position: relative;
    margin-left: 0;
    background: #ffffff;
  }

  .user-order-list .myorderList .imgs {
    float: left;
    width: 7.75rem;
    height: 5.52rem;
    margin-right: 0.625rem;
    border-radius: 5px;
    border: 1px solid #000;
  }

  .isEdit {
    width: 50% !important;
  }

  .checkIcon {
    float: left;
    width: 1.5rem;
    margin-right: 0.5rem;
    margin-top: 2rem
  }

  .carInfo {
    border-bottom: 1px solid #ececec;
    width: 60%;
    float: right;
    text-align: left;
    padding-bottom: 15px;
  }

  .title {
    white-space: normal;
    font-size: 0.93rem
  }

  .price {
    font-size: 0.93rem;
    color: #F54E4E;
  }

  .price span {
    font-size: 1.56rem;
    font-weight: bold
  }

  .carLocal {
    font-size: 0.81rem;
    color: #999;
  }

  .carLocal img {
    width: 0.8rem;
    padding-right: 0.4rem
  }

  .editFooter {
    position: fixed;
    bottom: 0;
    height: 3.125rem;
    width: 100%;
    background-color: #ffffff;
  }

  .allCheckIcon {
    float: left;
    width: 1.5rem;
    margin-right: 0.5rem;
    margin-top: 0.8rem;
    margin-left: 1rem
  }

  .deleteButton {
    height: 100%;
    background: #f5ca1d;
    color: #ffffff;
    border: 0;
    width: 45vw;
    float: right;
    font-size: 1rem
  }
</style>
