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
        <dd class="myorderList" v-for="record in records">
          <img v-if="isEdit && checkList.indexOf(record.commodityId) > -1" class="checkIcon" src="../../assets/img/common/Checkmark_sele.png"
                @click="unCheck(record.commodityId)">
          <img v-if="isEdit && checkList.indexOf(record.commodityId) == -1" class="checkIcon" src="../../assets/img/common/Checkmark_nor.png"
               @click="checkCommodity(record.commodityId)">
          <img class="imgs" :src="record.defaultPicPath">
          <div class="carInfo" v-bind:class="{'isEdit': isEdit}" @click="openDetail(record.productVo.productId)">
            <p class="title">{{record.commodityName}}</p>
            <p class="price">￥<span>{{setTranPrice(record.sellPrice)}}</span>万</p>
            <p class="carLocal"><img src="../../assets/img/common/ic_list_location.png">{{record.sellerVo.sellerAddress}}</p>
          </div>
        </dd>
      </dl>
    </div>

    <div style="height: 3.125rem" v-if="isEdit">
      <div class="editFooter">
        <img v-if="isEdit && checkList.length == records.length" class="allCheckIcon" src="../../assets/img/common/Checkmark_sele.png"
             @click="allClearCheck">
        <img v-if="isEdit && checkList.length != records.length" class="allCheckIcon" src="../../assets/img/common/Checkmark_nor.png"
             @click="allCheck">
        <span style="display: inline-block; padding-top: 0.9rem">全选</span>
        <button class="deleteButton" @click="deleteRecord">删除</button>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import storage from "../../store/storage";
  import {tranPrice} from "../../config/dataFormat";

  export default {
    name: "MyBrowse",
    components: {
      headTop
    },
    data() {
      return {
        headTitle: '我的浏览',
        isEdit: false,
        check: 0,
        records: [],
        collectList: [],
        checkList: []
      }
    },
    methods: {
      //价格转换格式
      setTranPrice(price) {
        return tranPrice(price);
      },
      //跳转商品详情页
      openDetail(productId) {
        this.$router.push({
          path: '/car-detail',
          query: {productId: productId}
        })
      },
      //勾选商品
      checkCommodity(commodityId) {
        this.checkList.push(commodityId);
      },
      //取消勾选商品
      unCheck(commodityId) {
        let index = this.checkList.indexOf(commodityId);
        if (index > -1) {
          this.checkList.splice(index, 1);
        }
      },
      //全选勾选
      allCheck() {
        this.checkList = [];
        for (let i = 0; i < this.records.length; i++) {
          this.checkList.push(this.records[i].commodityId);
        }
      },
      //取消全选勾选
      allClearCheck() {
        this.checkList = [];
      },
      //删除勾选数据
      deleteRecord() {
        let removeList = [];
        for(let j = 0; j < this.checkList.length; j++) {
          for (let i = 0; i < this.records.length; i++) {
            if (this.checkList[j] == this.records[i].commodityId) {
              removeList.push(this.records[i]);
            }
          }
        }
        for (let record in removeList) {
          this.records.splice(record, 1);
        }
        storage.save("records", this.records);
      }
    },
    created() {
      this.records = storage.fetch("records");
      //倒序排列
      this.records.reverse();
      console.log(this.records)
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
    padding: 0.625rem 0.875rem;
    position: relative;
    margin-left: 0;
    background: #ffffff;
  }

  .user-order-list .myorderList .imgs {
    float: left;
    width: 35%;
    height: 5rem;
    margin-right: 0.625rem;
    border-radius: 5px;
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
