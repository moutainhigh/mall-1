<template>
  <div>
    <head-top :goBack="false">
      <div slot="search" style="width: 100%;">
        <div class="search-con">
          <p class="search-text">输入搜索内容</p>
        </div>
      </div>
      <div slot="local" style="width: 7rem;">
        <p style="margin-top: 0.8rem">定位</p>
      </div>
    </head-top>

    <div style="background-color: #FFFFFF;padding: 0.1rem 0;">
      <div style="height: 10rem;background-color: #999999;margin: 1rem;border-radius: 0.5rem;">

      </div>
      <div style="height: 10rem;background-color: #999999;margin: 1rem;border-radius: 0.5rem;">

      </div>
    </div>

    <div style="background-color: #FFFFFF;height: 20rem;margin-top: 0.5rem;">
        <div style="height: 4rem; background-color: #cccccc">
        </div>
        <div style="text-align: center;">
          <div style="overflow:hidden;display: inline-block;height: 7rem;width: 28%; background-color: #dcdcdc;margin: 0 2px;border-radius: 8px;">

          </div>
          <div style="overflow:hidden;display: inline-block;height: 7rem;width: 28%; background-color: #dcdcdc;margin: 0 2px;border-radius: 8px;">

          </div>
          <div style="overflow:hidden;display: inline-block;height: 7rem;width: 28%; background-color: #dcdcdc;margin: 0 2px;border-radius: 8px;">

          </div>
        </div>
        <div style="overflow: scroll;overflow-y: hidden;width: 100%;">
          <div style="width: 1000px;height: 50px;"></div>
        </div>
      <div class="list">

      </div>
    </div>
  </div>
</template>

<script>
  import headTop from "../../components/header/head"

  export default {
    name: "Home",
    components: {
      headTop
    },
    data() {
      return {
        headTitle: '首页'
      }
    }
  }
</script>

<style scoped>

  .search-con {
    height: 2rem;
    width: 100%;
    background-color: #F1F3F5;
    border-radius: 1rem;
    margin-top: 0.5rem;
  }

  .search-text {
    color: #999999;
    font-size: 1rem;
    text-align: left;
    margin: 0;
    padding: 0.3rem 0 0 2.5rem;
  }

  ::-webkit-scrollbar {/*隐藏滚轮*/
    display: none;
  }
</style>
