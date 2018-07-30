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
      <scroller style="top: 0;font-size: 12px !important;margin-top: 3rem"
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
        <dl>
          <dd class="myorderList" v-for="(collect, index) in collectList">
            <img v-if="isEdit && checkList.indexOf(collect.favoriteId) > -1" class="checkIcon"
                 src="../../assets/img/common/Checkmark_sele.png"
                 @click="unCheck(collect.favoriteId)">
            <img v-if="isEdit && checkList.indexOf(collect.favoriteId) == -1" class="checkIcon"
                 src="../../assets/img/common/Checkmark_nor.png"
                 @click="checkCommodity(collect.favoriteId)">
            <img class="imgs" :src="collect.commodityVo.defaultPicPath">
            <div class="carInfo" v-bind:class="{'isEdit': isEdit}">
              <p class="title">{{collect.commodityVo.commodityTitle}}</p>
              <p class="price">￥<span>{{collect.salePrice}}</span>万</p>
              <p class="carLocal"><img src="../../assets/img/common/ic_list_location.png">深圳中升汇宝宝马4S店</p>
            </div>
          </dd>
        </dl>
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

    <div style="height: 3.125rem" v-if="isEdit">
      <div class="editFooter">
        <img v-if="isEdit && checkList.length == collectList.length" class="allCheckIcon"
             src="../../assets/img/common/Checkmark_sele.png"
             @click="allClearCheck">
        <img v-if="isEdit && checkList.length != collectList.length" class="allCheckIcon"
             src="../../assets/img/common/Checkmark_nor.png"
             @click="allCheck">
        <span style="display: inline-block; padding-top: 0.9rem">全选</span>
        <button class="deleteButton" @click="delFavoriteList">删除</button>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import {delFavoriteListByFavoriteIds, getCustomerFavorite} from "../../service/getData";

  export default {
    name: "CollectList",
    components: {
      headTop,
    },
    data() {
      return {
        headTitle: '我的收藏',
        isEdit: false,
        check: 0,
        pageQuery: {
          pageNo: 1,
          pageSize: 10
        },
        collectList: [],
        checkList: []
      }
    },
    methods: {
      refresh(done) {

        done()
      },

      infinite(done) {
        done(true);
      },
      getCollects() {
        let _this = this;
        console.log(this.pageQuery);
        getCustomerFavorite(_this.pageQuery).then(res => {
          if (res.result == 'SUCCESS') {
            _this.collectList = res.data.data;
          }
        })
      },
      //勾选商品
      checkCommodity(favoriteId) {
        this.checkList.push(favoriteId);
      },
      //取消勾选商品
      unCheck(favoriteId) {
        let index = this.checkList.indexOf(favoriteId);
        if (index > -1) {
          this.checkList.splice(index, 1);
        }
      },
      //全选勾选
      allCheck() {
        this.checkList = [];
        for (let i = 0; i < this.collectList.length; i++) {
          this.checkList.push(this.collectList[i].favoriteId);
        }
      },
      //取消全选勾选
      allClearCheck() {
        this.checkList = [];
      },
      //删除勾选的收藏商品
      delFavoriteList() {
        delFavoriteListByFavoriteIds(this.checkList).then(res => {
            console.log(res);
        })
      }
    },
    created() {
      this.getCollects();
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
