<template>
  <div>
    <head-top :go-back="true" :head-title="'选择车型'">
    </head-top>
    <div style="background-color: #FFFFFF;height: 100%;margin-top: 3rem;">
      <div class="ct-title" @click="openCarList()">全部车系</div>
      <div class="ct-list">
        <div class="ct-list-title" v-if="carTypeList.length != 0">
          {{categoryName}}
        </div>
        <div class="ct-list-item" v-for="carType in carTypeList" @click="openCarList(carType)">
          <div class="item-left">{{carType.categoryName}}</div>
          <div class="item-right">{{carType.lowestPrice}}-{{carType.highestPrice}}万</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from "../../components/header/head"
  import {getCarSeries} from "../../service/getData";

  export default {
    name: "ChooseCarType",
    components: {
      headTop
    },
    data() {
      return {
        carTypeList:[],
        categoryName:''
      }
    },
    methods: {
      openCarList(carType){
        if (carType) {
          this.$router.push({
            path:'/car-list',
            query: {
              categoryId : carType.categoryId,
              categoryName : carType.categoryName
            }
          });
        }else {
          this.$router.push({
            path:'/car-list'
          });
        }
      }
    },
    created(){
      let query = this.$route.query;
      let categoryId = query.categoryId;
      this.categoryName = query.categoryName;
      getCarSeries(categoryId).then(res=>{
        if (res.result == 'SUCCESS') {
          this.carTypeList = res.data;
        }
      });
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
