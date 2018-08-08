<template>
  <header id='head_top' style="display: flex;">
    <section class="head_goback" v-if="goBack" @click="back">
      <img src="../../assets/img/common/back.png" height="23" style="vertical-align: middle;margin-left: 5px;">
    </section>
    <section class="head_goback" style="margin-left: 0;" v-if="goClose">
      <img src="../../assets/img/common/ic_nav_close.png" height="23" style="vertical-align: middle;margin-left: 5px;">
    </section>
    <slot name='search' class="head_search"></slot>
    <slot name="local" style="flex: 0 0 2rem;" v-if="local">
      <div slot="local" style="flex: none;margin-top: 0.8rem;padding: 0 0.8rem;" @click="location">
        <img src="../../assets/img/common/ic_nav_ocation.png" style="width: 1.2rem;vertical-align: middle;margin-top: -5px;">
        <span>
          {{localCity}}
        </span>
      </div>
    </slot>
    <section class="title_head ellipsis" v-if="headTitle">
      <span class="title_text">{{headTitle}}</span>
    </section>
    <slot name="head-tab"></slot>
    <slot name="edit" v-if="edit">
      <span style="position: absolute; right: 1rem; top: 0.8rem;">编辑</span>
    </slot>
    <section class="head_share" style="margin-left: 0;" v-if="share">
      <img src="../../assets/img/common/ic_nav_share.png" height="23" style="vertical-align: middle;margin-left: 5px;">
    </section>
  </header>
</template>

<script>
  import AMap from 'AMap';
  import storage from "../../store/storage";
  import {LOCATION} from "../../config/constant";

  export default {
    data() {
      return {
        localCity:'定位'
      }
    },
    props: ['headTitle', 'goBack', 'local', 'edit' , 'goClose', 'share'],
    methods:{
      back(){
        this.$router.go(-1);
      },
      location() {
        this.$router.push({
          path:"/location"
        })
      }
    },
    created(){
      let vm = this;
      console.log(storage.fetchSession(LOCATION))
      if (storage.fetchSession(LOCATION).length <= 0) {
        AMap.plugin('AMap.CitySearch', function () {
          var citySearch = new AMap.CitySearch();
          citySearch.getLocalCity(function (status, result) {
            if (status === 'complete' && result.info === 'OK') {
              vm.localCity = result.city.substr(0, result.city.lastIndexOf("市"));
              storage.saveSession(LOCATION,vm.localCity);
            }
          })
        })
      }else {
        this.localCity = storage.fetchSession(LOCATION);
      }


    }
  }

</script>

<style lang="scss" scoped>
  @import '../../assets/css/mixin';

  #head_top {
    background-color: #ffffff;
    position: fixed;
    z-index: 13;
    left: 0;
    top: 0;
    @include wh(100%, 3rem);
  }

  .head_goback {
    left: 0.4rem;
    @include wh(0.6rem, 1rem);
    line-height: 2.8rem;
    margin-left: .4rem;
    flex: 0 0 2.3rem;
  }

  .head_share {
    @include wh(0.6rem, 1rem);
    line-height: 2.8rem;
    flex: 0 0 2.3rem;
    position: absolute;
    right: 2rem;
  }

  .head_search {
    flex: 1;
  }

  .title_head {
    @include center;
    width: 50%;
    color: $head;
    text-align: center;
    .title_text {
      @include sc(1.2rem, $head);
      text-align: center;
      font-family: PingFang-SC-Medium;
    }
  }
</style>
