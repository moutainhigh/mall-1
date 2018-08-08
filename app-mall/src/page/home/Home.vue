<template>
  <div>
    <head-top :goClose="true" :local="true">
      <div slot="search" style="width: 100%;">
        <div class="search-con" @click="toSearch">
          <img src="../../assets/img/common/ic_search.png" style="width: 1rem;position: absolute;margin: 0.5rem 0 0 0.8rem;">
          <p class="search-text">输入搜索内容</p>
        </div>
      </div>
    </head-top>

    <div style="background-color: #FFFFFF;padding: 0.1rem 0;font-size: 0;margin-top: 3rem;">
      <div style="background-color: #999999;margin: 1rem;border-radius: 0.5rem;overflow: hidden">
        <swiper :aspect-ratio="350/800" auto style="margin:0 auto;" dots-position="center">
          <swiper-item class="swiper-demo-img" v-for="(item, index) in homeList" :key="index"><img width="100%" style="border-radius: 0.5rem;" :src="item.picPath" @click="openBrand(item)"></swiper-item>
        </swiper>
      </div>
      <div style="margin: 1rem;border-radius: 0.5rem;">
        <div class="car-type" v-for="brand in brandList" @click="openCarListByBrand(brand)">
          <img :src="brand.picPath" style="width: 3rem;height: 3rem;">
          <div style="font-size: 0.8rem;line-height: 3;">{{brand.brandName}}</div>
        </div>
        <div class="car-type" @click="goCarBrand">
          <img src="../../assets/img/home/ic_more.png" style="width: 3rem;height: 3rem;">
          <div style="font-size: 0.8rem;line-height: 3;">更多</div>
        </div>
      </div>
    </div>

    <div class="card-adv">
      <img src="../../assets/img/home/home_floor_title01.png" width="100%"/>
      <div style="display:flex; padding: 0 1rem;">
        <div class="adv-ban" v-for="item in categoryThreeList" @click="openCarListByCategory(item)">
          <img :src="item.iconPath" width="100%"/>
        </div>
      </div>
      <div class="adv-scroll">
        <div style="padding-left: 0.7rem;" v-bind:style="{ width: milldeList.length* 16.2 + 'rem' }">
          <div style="display: inline-block;width:47%;margin: 0 5px;" v-for="millde in milldeList">
            <img :src="millde.picPath" width="100%" style="border-radius: 8px;">
          </div>
        </div>
      </div>

      <div class="card-list">
        <div style="font-size: 1rem;color: #999999;">
          热门车系
        </div>
        <div class="cont" v-for="item in categoryFiveList" @click="openCarListByCategory(item)">
          <div style="flex: 0 0 8rem;">
            <img :src="item.iconPath" width="100%" style="border-radius: 4px;height: 5.4rem;">
          </div>
          <div style="margin-left:0.8rem;height: 5.5rem;width: 100%;border-bottom: #ECECEC solid 1px;padding-bottom: 0.5rem;">
            <div style="font-size: 1rem;height: 1.5rem;">
              {{item.categoryName}}
            </div>
            <div style="height: 1.5rem;font-size: 0.9rem;color: #888;">
              {{item.description}}
            </div>
            <div style="color: #F54E4E;font-size: 1.3rem;font-weight: bold;margin-top: 0.2rem;">
              <span style="font-size: 0.9rem;">￥</span>{{item.lowestPrice}}
                <span style="margin: 0 -0.1rem;font-weight: normal">-</span>
              {{item.highestPrice}}万
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from "../../components/header/head"
  import {getIndex} from "../../service/getData";
  import { Swiper,SwiperItem} from 'vux'
  import AMap from 'AMap';
  import storage from "../../store/storage";
  import {CAR_LIST_SESSION} from "../../config/constant";

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
      openCarListByBrand(brand){
        this.$router.push({
          path:"/car-list",
          query:{
            brandId:brand.brandId,
            brandName:brand.brandName
          }
        })
      },
      openCarListByCategory(category){
        this.$router.push({
          path:'/car-list',
          query:{
            categoryId:category.categoryId,
            categoryName:category.categoryName
          }
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
          var citySearch = new AMap.CitySearch();
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
      },
      goCarBrand(){
        this.$router.push({
          path:"/choose-brand"
        })
      }
    },
    created() {
      let _this = this;
      //获取当前城市定位
      // this.selectLocal();
      _this.$vux.loading.show({
        text: '加载中...'
      });
      getIndex().then(res=>{
        if (res.result == 'SUCCESS'){
          _this.homeList = res.data.homeList;
          _this.brandList = res.data.brandList;
          _this.categoryThreeList = res.data.categoryThreeList;
          _this.categoryFiveList = res.data.categoryFiveList;
          _this.milldeList = res.data.milldeList;
        }
        _this.$vux.loading.hide();
      })
    },

    beforeRouteLeave(to, from, next) {
      // 下个页面为列表页 刷新
      if(to.path=='/car-list'){
        storage.saveSession(CAR_LIST_SESSION,true);
      }
      next();
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
    width: 6rem;
    background-color: #dcdcdc;
    margin: 0 0.125rem 0.1rem 0.125rem;
    border-radius: 8px;
    flex: 1;
  }

  .car-type {
    display: inline-block;
    height: 5rem;
    width: 25%;
    vertical-align: middle;
    text-align: center;
    margin: 0.5rem 0;
  }

  .adv-scroll {
    overflow: scroll;
    overflow-y: hidden;
    width: 100%;
    margin-top: 0.5rem;
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
