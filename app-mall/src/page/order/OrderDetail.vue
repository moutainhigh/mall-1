<template>
<div>
  <head-top :headTitle="'订单详情'"></head-top>
  <div style="margin-top: 3rem;">
      <div style="width: 100%;height: 5rem;background-color: #f5ca1d;">

      </div>
      <div class="goods-info">
        <div style="padding: 0.5rem 1rem;">
          <div>
            商品信息
          </div>
          <div></div>
        </div>
      </div>
  </div>
</div>
</template>

<script>
  import headTop from '../../components/header/head'
    export default {
        name: "OrderDetail",
      components:{
        headTop
      },

    }
</script>

<style lang="scss" scoped>

  .goods-info {
    margin-top: .5rem;
    background-color: #fff;
  }
</style>
