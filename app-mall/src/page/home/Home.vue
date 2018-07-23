<template>
  <div>
    <head-top>
      <div slot="search" style="width: 100%;">
        <div class="search-con">
          <img src="../../assets/img/common/ic_search.png" style="width: 1rem;position: absolute;margin: 0.5rem 0 0 0.8rem;">
          <p class="search-text">输入搜索内容</p>
        </div>
      </div>
      <div slot="local" style="flex: none;margin-top: 0.8rem;padding: 0 0.8rem;">
        <img src="../../assets/img/common/ic_nav_ocation.png" style="width: 1.2rem;vertical-align: middle;">
        <span>
          定位
        </span>
      </div>
    </head-top>

    <div style="background-color: #FFFFFF;padding: 0.1rem 0;font-size: 0;">
      <div style="background-color: #999999;margin: 1rem;border-radius: 0.5rem;overflow: hidden">
          <img src="../../assets/img/home/banner.png" width="100%" style="">
      </div>
      <div style="height: 10rem;margin: 1rem;border-radius: 0.5rem;">
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
        <div class="car-type">
          <img src="../../assets/img/home/home_logo_bc.png" style="width: 50%;">
          <div style="font-size: 0.8rem;line-height: 3;">奔驰</div>
        </div>
      </div>
    </div>

    <div class="card-adv">
      <img src="../../assets/img/home/home_floor_title01.png" width="100%"/>
      <div style="text-align: center;">
        <div class="adv-ban">
          <img src="../../assets/img/home/home_floor_banner_01.png" width="100%"/>
        </div>
        <div class="adv-ban">
          <img src="../../assets/img/home/home_floor_banner_02.png" width="100%"/>
        </div>
        <div class="adv-ban">
          <img src="../../assets/img/home/home_floor_banner_03.png" width="100%"/>
        </div>
      </div>
      <div class="adv-scroll">
        <div style="height: 5rem;width:680px;padding-left: 0.7rem;">
          <div style="display: inline-block;height: 5rem;">
            <img src="../../assets/img/home/banner2.png" height="100%" style="margin: 0 5px;border-radius: 8px;">
          </div>
          <div style="display: inline-block;height: 5rem;">
            <img src="../../assets/img/home/banner2.png" height="100%" style="margin: 0 5px;border-radius: 8px;">
          </div>
          <div style="display: inline-block;height: 5rem;">
            <img src="../../assets/img/home/banner2.png" height="100%" style="margin: 0 5px;border-radius: 8px;">
          </div>
        </div>
      </div>

      <div class="card-list">
        <div style="font-size: 1rem;color: #999999;">
          猜你喜欢
        </div>
        <div class="cont">
          <div style="height: 5rem;flex: 0 0 7rem;">
            <img src="../../assets/img/home/1.png" width="100%" style="border-radius: 4px">
          </div>
          <div style="margin-left:0.8rem;height: 5rem;width: 100%;border-bottom: #ECECEC solid 1px;padding-bottom: 0.5rem;">
            <div style="font-size: 1rem;height: 3rem;">
              2018款 240TURBO自动两驱舒适版
            </div>
            <div style="color: #F54E4E;font-size: 1rem;font-weight: bold;">
              <span style="font-size: 0.8rem;">￥</span>8.98万
            </div>
          </div>
        </div>
        <div class="cont">
          <div style="height: 5rem;flex: 0 0 7rem;">
            <img src="../../assets/img/home/1.png" width="100%" style="border-radius: 4px">
          </div>
          <div style="margin-left:0.8rem;height: 5rem;width: 100%;border-bottom: #ECECEC solid 1px;padding-bottom: 0.5rem;">
            <div style="font-size: 1rem;height: 3rem;">
              2018款 240TURBO自动两驱舒适版
            </div>
            <div style="color: #F54E4E;font-size: 1rem;font-weight: bold;">
              <span style="font-size: 0.8rem;">￥</span>8.98万
            </div>
          </div>
        </div>
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

  .card-adv {
    background-color: #FFFFFF;
    margin-top: 0.5rem;
    font-size: 0;
  }

  .card-adv .adv-ban {
    overflow: hidden;
    display: inline-block;
    width: 28%;
    background-color: #dcdcdc;
    margin: 0 2px;
    border-radius: 8px;
  }

  .car-type {
    display: inline-block;
    height: 5rem;
    width: 25%;
    vertical-align: middle;
    text-align: center;
  }

  .adv-scroll {
    overflow: scroll;
    overflow-y: hidden;
    width: 100%;
    margin-top: 1rem;
  }

  .card-list {
    margin: 0.8rem;
  }

  .card-list .cont {
    margin-top: 0.6rem;
    display: flex;
  }

  ::-webkit-scrollbar { /*隐藏滚轮*/
    display: none;
  }
</style>
