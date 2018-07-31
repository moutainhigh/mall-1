<template>
  <div>
    <head-top :headTitle="'售后申请'"></head-top>
    <div style="position:absolute;top: 0; height: 100%; width: 100%;z-index: 0">

    </div>
    <div style="position:absolute;top: 0;width: 100%;margin-top: 3rem;z-index: 1;">
      <group style="padding-top: 0.5rem">
        <popup-picker style="font-size: 1rem" id="relation2" title="退货原因" popup-title="请选择原因" placeholder="请选择" :data="relates" value-text-align="right"></popup-picker>
      </group>

      <div class="quest-des">
        <div class="quest-title">
          问题描述
        </div>
        <div style="display: flex;padding-bottom: 1rem;">
          <div style="flex:0 0 1rem;"></div>
          <div class="quest-text">
            <textarea title="问题描述"></textarea>
          </div>
          <div style="flex:0 0 1rem;"></div>
        </div>
        <div class="num">0/500</div>
      </div>

      <div class="base-info">
        <div class="info-item">
          <div style="flex: 0 0 5rem;color: #999;">姓名：</div>
          <div style="color: #333;">张思</div>
        </div>
        <div class="info-item">
          <div style="flex: 0 0 5rem;color: #999;">手机号：</div>
          <div style="color: #333;">18088886666</div>
        </div>
      </div>

      <div class="div-btn">
        <button>
          提交
        </button>
      </div>
    </div>
    <div style="position: absolute;top: 0;z-index: -1;height: 100%;width: 100%;">
    </div>
  </div>
</template>

<script>
  import {PopupPicker} from 'vux'
  import headTop from '../../components/header/head'

  export default {
    name: "Refund",
    components: {
      PopupPicker,
      headTop
    },
    data() {
      return {
        relates: [["个人原因", "物品原因", "其他原因"]],
      }
    }, created() {

    }
  }
</script>

<style lang="scss" scoped>
  @import '../../assets/css/mixin';
  .quest-des {
    background-color: #fff;
    margin-top: 0.5rem;
    .quest-title {
      padding: 0.5rem 1rem;
    }

    .num {
      position: absolute;
      margin-top: -2.1rem;
      right: 1.5rem;
      z-index: 99;
      font-size: 0.7rem;
      color: #999999;
    }

    .quest-text {
      width: 100%;
      height: 6rem;
      border-radius: 8px;
      background-color: #FAFAFA;
      padding: 0.7rem;

      textarea {
        resize: none;
        width: 100%;
        height: 5rem;
        outline: unset;
        border: 0;
        background-color: #FAFAFA;
        font-size: 1.1rem;
      }
    }

  }

  .base-info {
    margin-top: 0.5rem;
    background-color: #fff;

    .info-item {
      display: flex;
      padding: 1rem 0;
      margin: 0 1rem;
      border-bottom: #ececec 1px solid;
    }
  }

  .div-btn {
    text-align: center;
    margin-top: 1rem;
    button {
      background-color: #DDDDDD;
      border: 0;
      padding: 0.8rem;
      width: 90%;
      border-radius: 8px;
      font-size: 1rem;
      color: #BBBBBB;
      outline: unset;
    }

    .select-btn {
      background-color: $yc;
      color: $fc;
    }
  }
</style>
