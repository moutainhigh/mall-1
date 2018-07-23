<template>
  <div>
    <head-top :head-title="'选择车型'">
    </head-top>
    <div style="background-color: #FFFFFF;height: 100%;">
      <div class="ct-title">全部车系</div>
      <div class="ct-list">
        <div class="ct-list-title">
          奥迪(进口)
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
      </div>
      <div class="ct-list">
        <div class="ct-list-title">
          奥迪(进口)
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
        <div class="ct-list-item">
          <div class="item-left">奥迪A3</div>
          <div class="item-right">19.08-25.30万</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from "../../components/header/head"

  export default {
    name: "ChooseCarType",
    components: {
      headTop
    },
    data() {
      return {
      }
    }
  }
</script>

<style lang="scss" scoped>
  .ct-title {
    padding: 1rem;
    font-size: 1rem;
  }

  .ct-list {
    background-color: #ffffff;
    .ct-list-title {
      background-color: #F5F5F5;
      padding: 0.5rem 1rem;
    }
    .ct-list-item {
      margin: 0 1rem -0.1rem;
      padding: 1rem 0;
      font-size: 1rem;
      border-bottom: #ececec 1px solid;
      .item-left {
        display: inline-block;
      }
      .item-right {
        float: right;
        color: #F54E4E;
      }
    }
  }



</style>
