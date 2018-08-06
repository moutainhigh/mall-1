<template>
  <div id="contact">
    <head-top :go-back="true" :head-title="'选择品牌'"></head-top>

    <div class="list-view" style="padding-top: 3rem" ref="listView">
      <div style="background: #ffffff" class="list-group">
        <p class="cityTitle">热门品牌</p>
        <div style="display: inline-block; width: 25vw; text-align: center; height: 5.5rem"
             v-for="brand in hotBrands">
          <img style="width: 3rem;" :src="brand.picPath">
          <p style="padding-bottom: 1rem">{{brand.brandName}}</p>
        </div>
      </div>
      <div>
        <div v-for="(group, key) in brandList" class="list-group" ref="listGroup">
          <h2 class="list-group-title" :ref="`key_${key}`">{{ key }}</h2>
          <div>
            <div v-for="category in group" class="list-group-item" :key="parseInt(category.categoryId)"
                 @click="toCarType(category.categoryId, category.categoryName)">
              <img style="width: 2rem; height: 2rem; margin-right: 1rem" :src="category.iconPath">
              <span class="name">{{ category.categoryName }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="initial-bar"><span @click="toPs(key)" v-for="(group, key) in shortcutList">{{key}}</span></div>
  </div>
</template>

<script>
  import headTop from "../../components/header/head"
  import BScroll from 'better-scroll'
  import {carBrand, carHotBrand} from "../../service/getData";

  export default {
    name: "ChooseCarBrand",
    components: {
      headTop
    },
    data() {
      return {
        scrollY: 0,
        currentIndex: 0,
        brandList: [],
        hotBrands: [],
      }
    },
    methods: {
      toPs(i) {
        console.log(this.$refs['key_' + i][0].offsetTop)
        window.scrollTo(0, this.$refs['key_' + i][0].offsetTop)
      },
      toCarType(categoryId, categoryName) {
        this.$router.push({
          path: '/choose-type',
          query: {
            categoryId: categoryId,
            categoryName: categoryName
          }
        })
      }
    },
    created() {
      carBrand().then(res => {
        if (res.result == 'SUCCESS') {
          this.brandList = res.data;
        }
      });
      carHotBrand().then(res => {
        if (res.result == 'SUCCESS') {
          this.hotBrands = res.data;
        }
      })
    },
    computed: {
      shortcutList() {
        return this.brandList;
      }
    },
  }
</script>

<style scoped lang="scss">
  #contact {
    .initial-bar {
      position: fixed;
      top: 50%;
      font-size: 11px;
      color: #f5ca1d;
      line-height: 1.5;
      right: 0.5rem;
      width: 10px;
      transform: translate3d(0, -50%, 0);
      span {
        display: block;
        text-align: left;
      }
    }
  }

  .box {
    position: fixed;
    width: 100%;
    height: 100%;
  }

  .cityTitle {
    font-size: 1rem;
    color: #333333;
    font-weight: bold;
    padding: 0.62rem 1rem
  }

  .locationCity {
    background: none;
    border: 1px solid #DDDDDD;
    border-radius: 2px;
    width: 26vw;
    height: 2.5rem;
    font-size: 1rem;
    margin-left: 1rem
  }

  .hotCity {
    background: none;
    border: 1px solid #DDDDDD;
    border-radius: 2px;
    width: 26vw;
    height: 2.5rem;
    font-size: 1rem;
    margin-left: 1rem;
    margin-bottom: 10px
  }

  .list-view {
    position: relative;
    width: 100%;
    height: 100%;
    overflow: auto;
    background: #ffffff;
    .list-group {
      .list-group-title {
        height: 30px;
        line-height: 30px;
        padding-left: 1rem;
        font-size: 0.91rem;
        color: #888888;
        background: #F5F5F5;
        margin: 0;
      }
      .list-group-item {
        display: flex;
        align-items: center;
        /*padding: 1rem 0 1rem 0;*/
        line-height: 3;
        margin-left: 1rem;
        border-bottom: 1px solid #ececec;
        .avatar {
          width: 50px;
          height: 50px;
          border-radius: 5%;
        }
        .name {
          color: black;
          font-size: 1rem;
        }
      }
    }
    .list-shortcut {
      position: absolute;
      z-index: 30;
      right: 0;
      top: 50%;
      transform: translateY(-50%);
      width: 20px;
      padding: 20px 0;
      border-radius: 10px;
      text-align: center;
      background: none;
      font-family: Helvetica;
      .item {
        padding: 3px;
        line-height: 1;
        color: #f5ca1d;
        font-size: 11px;
        &.current {
          color: #f5ca1d;;
        }
      }
    }
  }

  .initial-bar {
    position: fixed;
    top: 50%;
    font-size: 11px;
    line-height: 1.2;
    right: 2px;
    width: 10px;
    -webkit-transform: translate3d(0, -50%, 0);
    transform: translate3d(0, -50%, 0)
  }
</style>
