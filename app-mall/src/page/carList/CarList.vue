<template>
  <div>
    <head-top :local="true"  v-bind:style="{ 'z-index' : enFocus? 10 : 13  }">
      <div slot="search" style="width: 100%;">
        <div class="search-con">
          <img src="../../assets/img/common/ic_search.png" style="width: 1rem;position: absolute;margin: 0.5rem 0 0 0.8rem;">
          <p class="search-text">输入搜索内容</p>
        </div>
      </div>
    </head-top>
    <div style="margin-top: 5rem;">
      <section class="sort_container">
        <div @click="chooseType('')" v-show="sortBy != ''" style="position:fixed; z-index:2;height: 100%;width: 100%;background-color: rgba(0,0,0,0.3);"></div>
        <div class="sort_item" :class="{choose_type:sortBy == 'sort'}">
          <div class="sort_item_container" @click="chooseType('sort')">
            <div class="sort_item_border">
              <span :class="{category_title: sortBy == 'sort'}">{{sortTitle}}</span>
              <img v-show="sortBy != 'sort'" class="choose_img" src="../../assets/img/common/ic_scree_arrows_nor.png">
              <img v-show="sortBy == 'sort'" class="choose_img" src="../../assets/img/common/ic_scree_arrows_sele.png">
            </div>
          </div>
          <transition name="showlist">
            <section v-show="sortBy == 'sort'" class="sort_detail_type">
              <div class="sort_list_container">
                <div class="sort_list_li" @click="sortList(0,'默认排序')">
                  <p :class="{sort_select: sortByType == 0}">
                    <span>默认排序</span>
                  </p>
                  <img v-if="sortByType == 0" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
                <div class="sort_list_li" @click="sortList(1,'最畅销')">
                  <p data="1" :class="{sort_select: sortByType == 1}">
                    <span>最畅销</span>
                  </p>
                  <img v-if="sortByType == 1" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
                <div class="sort_list_li" @click="sortList(2,'价格最高')">
                  <p data="2" :class="{sort_select: sortByType == 2}">
                    <span>价格最高</span>
                  </p>
                  <img v-if="sortByType == 2" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
                <div class="sort_list_li" @click="sortList(3,'价格最低')">
                  <p data="3" :class="{sort_select: sortByType == 3}">
                    <span>价格最低</span>
                  </p>
                  <img v-if="sortByType == 3" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
              </div>
            </section>
          </transition>
        </div>
        <div class="sort_item">
          <div class="sort_item_container">
            <div class="sort_item_border">
              <span :class="{category_title: sortBy == 'sort'}">品牌</span>
              <img class="choose_img" src="../../assets/img/common/ic_scree_arrows_nor.png">
            </div>
          </div>
        </div>
        <div class="sort_item" :class="{choose_type:sortBy == 'price'}">
          <div class="sort_item_container" @click="chooseType('price')">
            <div class="sort_item_border">
              <span :class="{category_title: sortBy == 'price'}">价格</span>
              <img v-show="sortBy != 'price'" class="choose_img" src="../../assets/img/common/ic_scree_arrows_nor.png">
              <img v-show="sortBy == 'price'" class="choose_img" src="../../assets/img/common/ic_scree_arrows_sele.png">
            </div>
          </div>
          <transition name="showlist">
            <section v-show="sortBy == 'price'" class="sort_detail_type filter_container" :class="{focus_input:enFocus}">
              <section style="width: 100%;background-color: #ffffff;">
                <header class="filter_header_style">自定义</header>
                <div class="choose_input">
                  <input @focus="enFocus = true" @blur="enFocus = false"/>
                  <div style="position: relative;margin-left: -1.2rem;">万</div>
                  <span style="margin: 0 0.2rem 0 0.5rem;">—</span>
                  <input @focus="enFocus = true" @blur="enFocus = false"/>
                  <div style="position: relative;margin-left: -1.2rem;">万</div>
                  <div class="input_button" :class="{input_button_sele:true}"><p>确定</p></div>
                </div>
              </section>
              <section style="width: 100%;background-color: #ffffff;">
                <div style="display: flex;padding: 1rem;flex-wrap: wrap">
                  <div class="choose_flex_type">
                    <p :class="{choose_type_sele: true}">不限</p>
                  </div>
                  <div class="choose_flex_type">
                    <p>15-25万</p>
                  </div>
                  <div class="choose_flex_type">
                    <p>25-50万</p>
                  </div>
                </div>
              </section>
            </section>
          </transition>
        </div>
        <div class="sort_item" :class="{choose_type:sortBy == 'activity'}">
          <div class="sort_item_container" @click="chooseType('activity')">
            <span :class="{category_title: sortBy == 'activity'}">筛选</span>
            <img class="choose_img" src="../../assets/img/common/ic_scree_arrows_nor.png">
          </div>
          <transition name="showlist">
            <section v-show="sortBy == 'activity'" class="sort_detail_type sort_activity">
              <div style="overflow-x: scroll;height: 75%;">
                <div class="activity_type">
                  <div class="type_title">
                    国别
                  </div>
                  <div class="type_list">
                    <div class="type_item">
                      <p>自主</p>
                    </div>
                    <div class="type_item">
                      <p>合资</p>
                    </div>
                    <div class="type_item">
                      <p>进口</p>
                    </div>
                    <div class="type_item">
                      <p>德系</p>
                    </div>
                    <div class="type_item">
                      <p>韩系</p>
                    </div>
                    <div class="type_item">
                      <p>自主</p>
                    </div>
                    <div class="type_item">
                      <p>合资</p>
                    </div>
                    <div class="type_item">
                      <p>自主</p>
                    </div>
                    <div class="type_item">
                      <p>合资</p>
                    </div>
                    <div class="type_item">
                      <p>自主</p>
                    </div>
                    <div class="type_item">
                      <p>合资</p>
                    </div>
                    <div class="type_item">
                      <p>自主</p>
                    </div>
                    <div class="type_item">
                      <p>合资</p>
                    </div>
                  </div>
                </div>
                <div class="activity_type">
                  <div class="type_title">
                    车型
                  </div>
                  <div class="type_list">
                    <div class="type_item">
                      <p>轿车</p>
                    </div>
                    <div class="type_item">
                      <p>SUV</p>
                    </div>
                    <div class="type_item">
                      <p>MVP</p>
                    </div>
                    <div class="type_item">
                      <p>跑车</p>
                    </div>
                    <div class="type_item">
                      <p>微面</p>
                    </div>
                  </div>
                </div>
                <div class="activity_type">
                  <div class="type_title">
                    坐席
                  </div>
                  <div class="type_list">
                    <div class="type_item">
                      <p>两座</p>
                    </div>
                    <div class="type_item">
                      <p>4-5座</p>
                    </div>
                    <div class="type_item">
                      <p>6座</p>
                    </div>
                    <div class="type_item">
                      <p>7座</p>
                    </div>
                    <div class="type_item">
                      <p>7座以上</p>
                    </div>
                  </div>
                </div>
                <div class="activity_submit">
                  <div style="padding: 0.5rem;">
                    <button>重置</button>
                    <button class="submit_true">确认</button>
                  </div>
                </div>
              </div>
            </section>
          </transition>
        </div>
      </section>
      <div class="car_type_list">
        <div class="type_item">
          <p>
            日系
          </p>
          <img src="../../assets/img/common/ic_screen_close.png">
        </div>
        <div class="type_item">
          <p>
            20-35万
          </p>
          <img src="../../assets/img/common/ic_screen_close.png">
        </div>
        <div class="type_item">
          <p>
            20-35万
          </p>
          <img src="../../assets/img/common/ic_screen_close.png">
        </div>
      </div>
      <div class="car_list">
        <div class="list_item">
          <div class="cont_img">
            <img src="../../assets/img/home/1.png" width="100%">
          </div>
          <div class="cont">
            <div class="cont_title">
              2018款 240TURBO自动两驱舒适版
            </div>
            <div class="cont_price">
              <span style="font-size: 1rem;">￥</span>8.98万
            </div>
            <div class="cont_local">
              <img src="../../assets/img/common/ic_nav_ocation.png" style="width: 1rem;">
              <span>深圳中升汇宝宝马4S店</span>
            </div>
          </div>
        </div>
        <div class="list_item">
          <div class="cont_img">
            <img src="../../assets/img/home/1.png" width="100%">
          </div>
          <div class="cont">
            <div class="cont_title">
              2018款 240TURBO自动两驱舒适版
            </div>
            <div class="cont_price">
              <span style="font-size: 1rem;">￥</span>8.98万
            </div>
            <div class="cont_local">
              <img src="../../assets/img/common/ic_nav_ocation.png" style="width: 1rem;">
              <span>深圳中升汇宝宝马4S店</span>
            </div>
          </div>
        </div>
        <div class="list_item">
          <div class="cont_img">
            <img src="../../assets/img/home/1.png" width="100%">
          </div>
          <div class="cont">
            <div class="cont_title">
              2018款 240TURBO自动两驱舒适版
            </div>
            <div class="cont_price">
              <span style="font-size: 1rem;">￥</span>8.98万
            </div>
            <div class="cont_local">
              <img src="../../assets/img/common/ic_nav_ocation.png" style="width: 1rem;">
              <span>深圳中升汇宝宝马4S店</span>
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
    name: "Carlist",
    components: {
      headTop
    },
    data() {
      return {
        sortBy: '', // 筛选的条件
        sortTitle:'默认排序', //排序名称
        sortByType: 0, // 根据何种方式排序
        enFocus:false,//是否焦点在input上
        conditions:[], //已选择的条件
      }
    },
    methods: {
      // 点击顶部三个选项，展示不同的列表，选中当前选项进行展示，同时收回其他选项
      async chooseType(type) {
        if (this.sortBy !== type) {
          this.sortBy = type;
          //food选项中头部标题发生改变，需要特殊处理
          if (type == 'food') {
            this.foodTitle = '分类';
          } else {
            //将foodTitle 和 headTitle 进行同步
            this.foodTitle = this.headTitle;
          }
        } else {
          //再次点击相同选项时收回列表
          this.sortBy = '';
          if (type == 'food') {
            //将foodTitle 和 headTitle 进行同步
            this.foodTitle = this.headTitle;
          }
        }
      },
      //点击某个排序方式，获取事件对象的data值，并根据获取的值重新获取数据渲染
      sortList(sort,title) {
        this.sortByType = sort;
        this.sortTitle = title;
        this.sortBy = '';
      },
    }
  }
</script>

<style lang="scss" scoped>
  @import "../../assets/css/mixin";

  .food_container {
    padding-top: 3.6rem;
  }

  .sort_container {
    background-color: #fff;
    border-bottom: 0.025rem solid #f1f1f1;
    position: fixed;
    top: 3rem;
    right: 0;
    width: 100%;
    display: flex;
    z-index: 10;
    box-sizing: border-box;
    .sort_item {
      @include sc(0.8rem, #444);
      @include wh(33.3%, 2rem);
      text-align: center;
      line-height: 1.6rem;
      margin: 0 0 0 -0.1rem;
      .choose_img {
        width: 1rem;
        position: absolute;
        margin-top: 0.3rem;
      }
      .sort_item_container {
        @include wh(100%, 100%);
        position: relative;
        z-index: 14;
        background-color: #fff;
        box-sizing: border-box;
        padding-top: .3rem;
        .sort_item_border {
          height: 1.5rem;
          //border-right: 0.025rem solid $bc;
        }
      }
      .sort_icon {
        vertical-align: middle;
        transition: all .3s;
        fill: #666;
      }
      .sort_activity {
        display: block;
        position: fixed;
        top: 4.6rem;
        height: 100%;
        .activity_type {
          padding-top: 1rem;
          width: 100%;
          text-align: left;
          .type_title {
            font-size: 1rem;
            padding-left: 1rem;
            margin-bottom: 0.5rem;
            font-weight: bold;
          }
          .type_list {
            display: flex;
            flex-wrap: wrap;
            padding: 0 0.4rem;
            .type_item {
              flex: 0 0 33%;
              margin: 0.5rem 0;
              p {
                text-align: center;
                background-color: #F1F3F5;
                color: #333333;
                border-radius: 4px;
                padding: 0.3rem;
                font-size: 0.9rem;
                margin: 0 0.4rem;
              }
            }
          }
        }
        .activity_submit {
          position: absolute;
          bottom: 5.5rem;
          width: 100%;
          background-color: #ffffff;
          button {
            font-size: 1rem;
            padding: 0.8rem 1rem;
            width: 46%;
            margin: 0 1%;
            color: #666666;
            background-color: #ffffff;
            box-sizing: border-box;
            border: 1px #BBBBBB solid;
            border-radius: 4px;
          }

          .submit_true {
            background: #F5CA1D;
            color: #FEFEFE;
            border: 1px #F5CA1D solid;
          }
        }
      }
    }
    .choose_type {
      .sort_item_container {

        .category_title {
          color: $yc;
        }
        .sort_icon {
          transform: rotate(180deg);
          fill: $yc;
        }
      }
    }
    .showlist-enter-active, .showlist-leave-active {
      transition: all .3s;
      transform: translateY(0);
    }
    .showlist-enter, .showlist-leave-active {
      opacity: 0;
      transform: translateY(-100%);
    }
    .sort_detail_type {
      margin-top: 0.39rem;
      z-index: 3;
      width: 100%;
      position: absolute;
      display: flex;
      top: 1.6rem;
      left: 0;
      border-top: 0.025rem solid $bc;
      background-color: #fff;
      ul {
        padding-left: 0;
        margin-bottom: 0;
        z-index: 3;
        background-color: #ffffff;
      }

      ul :active {
        display: none;
      }
    }
    .category_container {
      .category_left {
        flex: 1;
        background-color: #f1f1f1;
        height: 16rem;
        overflow-y: auto;
        span {
          @include sc(0.5rem, #666);
          line-height: 1.8rem;
        }
        .category_left_li {
          @include fj;
          padding: 0 0.5rem;
          .category_icon {
            @include wh(.8rem, .8rem);
            vertical-align: middle;
            margin-right: .2rem;
          }
          .category_count {
            background-color: #ccc;
            @include sc(.4rem, #fff);
            padding: 0 .1rem;
            border: 0.025rem solid #ccc;
            border-radius: 0.8rem;
            vertical-align: middle;
            margin-right: 0.25rem;
          }
          .category_arrow {
            vertical-align: middle;
          }
        }
        .category_active {
          background-color: #fff;
        }
      }
      .category_right {
        flex: 1;
        background-color: #fff;
        padding-left: 0.5rem;
        height: 16rem;
        overflow-y: auto;
        .category_right_li {
          @include fj;
          height: 1.8rem;
          line-height: 1.8rem;
          padding-right: 0.5rem;
          border-bottom: 0.025rem solid $bc;
          span {
            color: #666;
          }
        }
        .category_right_choosed {
          span {
            color: $yc;
          }
        }
      }
    }
    .sort_list_container {
      width: 100%;
      .sort_list_li {
        height: 2.5rem;
        display: flex;
        align-items: center;
        p {
          line-height: 2.5rem;
          flex: auto;
          text-align: left;
          text-indent: 0.25rem;
          //border-bottom: 0.025rem solid $bc;
          @include fj;
          align-items: center;
          span {
            color: #666;
            padding-left: 1rem;
          }
        }
        .sort_select {
          span {
            color: $yc;
          }
        }
      }

      .sort_list_li :active {
        background-color: #ECECEC;
      }
    }
    .filter_container {
      margin-top: 0.41rem;
      flex-direction: column;
      align-items: flex-start;
      background-color: #f1f1f1;
      .filter_header_style {
        @include sc(0.8rem, #666);
        line-height: 1.5rem;
        height: 1.5rem;
        text-align: left;
        padding: 0.5rem 0 0.2rem 1rem;
        background-color: #fff;
      }
      .filter_ul {
        display: flex;
        flex-wrap: wrap;
        padding: 0 0.5rem;
        background-color: #fff;
        .filter_li {
          display: flex;
          align-items: center;
          border: 0.025rem solid #eee;
          @include wh(4.7rem, 1.4rem);
          margin-right: 0.25rem;
          border-radius: 0.125rem;
          padding: 0 0.25rem;
          margin-bottom: 0.25rem;
          svg {
            @include wh(.8rem, .8rem);
            margin-right: 0.125rem;
          }
          span {
            @include sc(0.4rem, #333);
          }
          .filter_icon {
            @include wh(.8rem, .8rem);
            font-size: 0.5rem;
            border: 0.025rem solid $bc;
            border-radius: 0.15rem;
            margin-right: 0.25rem;
            line-height: .8rem;
            text-align: center;
          }
          .activity_svg {
            margin-right: .25rem;
          }
          .selected_filter {
            color: $yc;
          }
        }
      }
      .choose_input {
        display: flex;
        text-align: left;
        margin: 0 1rem;
        padding: 0.5rem 0;
        border-bottom: #ECECEC solid 1px;
        line-height: 2.6;
        color: #333333;
        input {
          padding: 0.5rem;
          border-radius: 5px;
          border: 1px solid #DDD;
          width: 100%;
          caret-color: #f5ca1d;
        }
        input:focus {
          outline: none;
          border-radius: 4px;
          border-color: #f5ca1d;
        }
        .input_button {
          flex: 0 0 25%;
          margin-left: 1rem;
          border-radius: 4px;
          background-color: #DDD;
          color: #BBB;
          p {
            text-align: center;
          }
        }
        .input_button_sele {
          background-color: #F5CA1D;
          color: #FFF;
        }
      }

      .choose_flex_type {
        flex: 4 0 33%;
        line-height: 2.5;
        color: #333333;
        p {
          box-sizing: border-box;
          margin: 0 0.2rem;
          border-radius: 4px;
          background-color: #F1F3F5;
        }

        .choose_type_sele {
          border: #f5ca1d 1px solid;
          color: #f5ca1d;
          background-color: #FFFBEB;
        }
      }
      .confirm_filter {
        display: flex;
        background-color: #f1f1f1;
        width: 100%;
        padding: .3rem .2rem;
        .filter_button_style {
          @include wh(50%, 1.8rem);
          font-size: 0.8rem;
          line-height: 1.8rem;
          border-radius: 0.2rem;
        }
        .clear_all {
          background-color: #fff;
          margin-right: .5rem;
          border: 0.025rem solid #fff;
        }
        .confirm_select {
          background-color: #56d176;
          color: #fff;
          border: 0.025rem solid #56d176;
          span {
            color: #fff;
          }
        }
      }
    }
  }

  .showcover-enter-active, .showcover-leave-active {
    transition: opacity .3s
  }

  .showcover-enter, .showcover-leave-active {
    opacity: 0
  }

  .back_cover {
    position: fixed;
    @include wh(100%, 100%);
    z-index: 10;
    background-color: rgba(0, 0, 0, 0.3);
  }

  .head-tab {
    position: absolute;
    top: 1rem;
    left: 0;
    right: 0;
    padding: 0 0 0.5rem 0;
    z-index: -1;
  }

  .head-tab div {
    display: inline-block;
    border-bottom: #f5ca1d solid 3px;
    padding: 0 0 0.5rem 0;
    margin: 0 8px;
  }

  .car_type_list {
    padding: 0.5rem;
    .type_item {
      border: #DDDDDD 1px solid;
      display: inline-block;
      margin: 0.2rem 0.1rem;
      font-size: 0.8rem;
      background-color: #fff;
      p {
        padding: 0.2rem 0 0.2rem 0.3rem;
        display: inline-block;
        vertical-align: middle;
      }

      img {
        margin-right: 0.2rem;
        vertical-align: middle;
        width: 0.8rem;
      }
    }
  }

  .car_list {
    background-color: #fff;
    .list_item {
      display: flex;
      padding: 0.5rem;
      line-height: 1.5;
      overflow: hidden;
      .cont_img {
        flex: 0 0 40%;
        img {
          border-radius: 4px;
        }
      }
      .cont {
        flex: 0 0 60%;
        padding: 0 0.7rem 0.7rem 0.7rem;
        height: 6rem;
        border-bottom: #ececec 1px solid;
        .cont_title {
          overflow:hidden;
          text-overflow:ellipsis;
          display:-webkit-box;
          -webkit-box-orient:vertical;
          -webkit-line-clamp:2;
        }
        .cont_price {
          color: #F54E4E;
          font-size: 1.3rem;
          font-weight: bold;
        }

        .cont_local {
          font-size: 0.8rem;
          color: #999;
          img {
            vertical-align: middle;
          }
          span {
            vertical-align: middle;
          }
        }
      }
    }
  }

  .focus_input {
    position: fixed !important;
    z-index: 99 !important;
    top: 0 !important;
  }
</style>
