<template>
  <div>
    <head-top :local="true">
      <div slot="search" style="width: 100%;">
        <div class="search-con" @click="toSearch">
          <img src="../../assets/img/common/ic_search.png" style="width: 1rem;position: absolute;margin: 0.5rem 0 0 0.8rem;">
          <p class="search-text">输入搜索内容</p>
        </div>
      </div>
      <slot style="flex: 0 0 2rem;">
        <div slot="local" style="flex: none;margin-top: 0.8rem;padding: 0 0.8rem;">
          <img src="../../assets/img/common/ic_nav_ocation.png" style="width: 1.2rem;vertical-align: middle;">
          <span @click="location()">
          {{localCity}}
        </span>
        </div>
      </slot>
    </head-top>

    <div style="background-color: #FFFFFF;padding: 0.1rem 0;font-size: 0;margin-top: 3rem;">
      <div style="background-color: #999999;margin: 1rem;border-radius: 0.5rem;overflow: hidden">
        <swiper :aspect-ratio="350/800" auto style="margin:0 auto;" dots-position="center">
          <swiper-item class="swiper-demo-img" v-for="(item, index) in homeList" :key="index"><img width="100%" :src="item.picPath"></swiper-item>
        </swiper>
      </div>
      <div style="margin: 1rem;border-radius: 0.5rem;">
        <div class="car-type" v-for="brand in brandList" @click="openCarList(brand.brandId)">
          <img :src="brand.picPath" style="width: 3rem;height: 3rem;">
          <div style="font-size: 0.8rem;line-height: 3;">{{brand.brandName}}</div>
        </div>
      </div>
    </div>

    <div class="card-adv">
      <img src="../../assets/img/home/home_floor_title01.png" width="100%"/>
      <div style="text-align: center;">
        <div class="adv-ban" v-for="item in categoryThreeList">
          <img :src="item.iconPath" width="100%"/>
        </div>
      </div>
      <div class="adv-scroll">
        <div style="height: 5rem;padding-left: 0.7rem;" v-bind:style="{ width: milldeList.length* 14 + 'rem' }">
          <div style="display: inline-block;height: 5rem;" v-for="millde in milldeList">
            <img src="../../assets/img/home/banner2.png" height="100%" style="margin: 0 5px;border-radius: 8px;">
          </div>
        </div>
      </div>

      <div class="card-list">
        <div style="font-size: 1rem;color: #999999;">
          猜你喜欢
        </div>
        <div class="cont" v-for="item in categoryFiveList" @click="openCarDetail">
          <div style="height: 5rem;flex: 0 0 7rem;">
            <img :src="item.iconPath" width="100%" style="border-radius: 4px">
          </div>
          <div style="margin-left:0.8rem;height: 5rem;width: 100%;border-bottom: #ECECEC solid 1px;padding-bottom: 0.5rem;">
            <div style="font-size: 1rem;height: 3rem;">
              {{item.categoryName}}
            </div>
            <div style="color: #F54E4E;font-size: 1rem;font-weight: bold;">
              <span style="font-size: 0.8rem;">￥</span>{{item.lowestPrice}}万
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from "../../components/header/head"
  import {getIndex, getVaildData} from "../../service/getData";
  import { Swiper,SwiperItem} from 'vux'
  import AMap from 'AMap';

  export default {
    name: "Home",
    components: {
      headTop,
      Swiper,
      SwiperItem
    },
    data() {
      return {
        headTitle: '首页',
        homeList:[],
        brandList:[],
        milldeList:[],
        categoryFiveList:[],
        categoryThreeList:[],
        localCity:'定位'
      }
    },
    methods: {
      openCarDetail(){
        this.$router.push({
          path:"/car-detail"
        })
      },
      openCarList(){
        this.$router.push({
          path:"/car-list"
        })
      },
      toSearch() {
        this.$router.push({
          path:"/search"
        })
      },
      selectLocal() {
        let vm = this;
        AMap.plugin('AMap.CitySearch', function () {
          var citySearch = new AMap.CitySearch()
          citySearch.getLocalCity(function (status, result) {
            if (status === 'complete' && result.info === 'OK') {
              vm.localCity = result.city.substr(0, 2);
            }
          })
        })
      },
      location() {
        this.$router.push({
          path:"/location"
        })
      }
    },
    created() {
      let _this = this;
      //获取当前城市定位
      this.selectLocal();
      getIndex().then(res=>{
        if (res.result == 'SUCCESS'){
          _this.homeList = res.data.homeList;
          _this.brandList = res.data.brand.brandList;
          _this.categoryThreeList = res.data.categoryThree.categoryThreeList;
          _this.categoryFiveList = res.data.categoryFive.categoryFiveList;
          _this.milldeList = res.data.milldeList;
        }
        console.log(res);
      })
    },
  }
</script>

<style scoped>

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
    margin: 0 0.2rem 0.1rem 0.2rem;
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
    margin: 0.8rem 0.8rem 0;
  }

  .card-list .cont {
    margin-top: 0.6rem;
    display: flex;
  }

  ::-webkit-scrollbar { /*隐藏滚轮*/
    display: none;
  }
</style>
