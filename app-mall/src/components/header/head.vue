<template>
  <header id='head_top' style="display: flex;">
    <section class="head_goback" @click="$router.go(-1)">
      <img src="../../assets/img/back.png" height="18" style="vertical-align: middle;">
    </section>
    <slot name='search' class="head_search"></slot>
    <slot name="local" style="flex: 0 0 2rem;"></slot>
    <section class="title_head ellipsis" v-if="headTitle">
      <span class="title_text">{{headTitle}}</span>
    </section>
    <slot name="head-tab"></slot>
  </header>
</template>

<script>
  export default {
    data() {
      return {}
    },
    props: ['signinUp', 'headTitle', 'goBack'],
  }

</script>

<style lang="scss" scoped>
  @import '../../assets/css/mixin';

  #head_top {
    background-color: #ffffff;
    position: fixed;
    z-index: 100;
    left: 0;
    top: 0;
    box-shadow: 0 1px 5px #d1d1d1;
    @include wh(100%, 3rem);
  }

  .head_goback {
    left: 0.4rem;
    @include wh(0.6rem, 1rem);
    line-height: 2.8rem;
    margin-left: .4rem;
    flex: 0 0 2.3rem;
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
