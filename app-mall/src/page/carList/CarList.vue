<template>
  <div>
    <head-top :go-back="true" :local="true" v-bind:style="{ 'z-index' : enFocus? 10 : 13  }">
      <div slot="search" style="width: 100%;">
        <div class="search-con" @click="toSearch">
          <img src="../../assets/img/common/ic_search.png" style="width: 1rem;position: absolute;margin: 0.5rem 0 0 0.8rem;">
          <p class="search-text">输入搜索内容</p>
        </div>
      </div>
    </head-top>
    <div style="margin-top: 5rem;">
      <section id="section" class="sort_container">
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
                <div class="sort_list_li" @click="sortList('','默认排序',true)">
                  <p :class="{sort_select: sortTitle == '默认排序'}">
                    <span>默认排序</span>
                  </p>
                  <img v-if="sortTitle == '默认排序'" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
                <div class="sort_list_li" @click="sortList('saleNum','最畅销',false)">
                  <p :class="{sort_select: sortTitle == '最畅销'}">
                    <span>最畅销</span>
                  </p>
                  <img v-if="sortTitle == '最畅销'" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
                <div class="sort_list_li" @click="sortList('sellPrice','价格最高',false)">
                  <p :class="{sort_select: sortTitle == '价格最高'}">
                    <span>价格最高</span>
                  </p>
                  <img v-if="sortTitle == '价格最高'" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
                <div class="sort_list_li" @click="sortList('sellPrice','价格最低',true)">
                  <p :class="{sort_select: sortTitle == '价格最低'}">
                    <span>价格最低</span>
                  </p>
                  <img v-if="sortTitle == '价格最低'" src="../../assets/img/common/ic_screen_select.png" width="1rem" style="flex: 0 0 1rem;margin-right: 1rem">
                </div>
              </div>
            </section>
          </transition>
        </div>
        <div class="sort_item" @click="chooseBrand()">
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
                  <input @focus="enFocus = true" @blur="enFocus = false" type="number" v-model="priceSect.lowPrice"/>
                  <div style="position: relative;margin-left: -1.2rem;">万</div>
                  <span style="margin: 0 0.2rem 0 0.5rem;">—</span>
                  <input @focus="enFocus = true" @blur="enFocus = false" type="number" v-model="priceSect.highPrice"/>
                  <div style="position: relative;margin-left: -1.2rem;">万</div>
                  <div class="input_button" :class="{input_button_sele:(priceSect.lowPrice && priceSect.highPrice)}" @click="setPriceSect"><p>确定</p></div>
                </div>
              </section>
              <section style="width: 100%;background-color: #ffffff;">
                <div style="display: flex;padding: 1rem;flex-wrap: wrap">
                  <div class="choose_flex_type" @click="choosePrice(-1)">
                    <p :class="{choose_type_sele: priceSection.chooseIndex == -1}">不限</p>
                  </div>
                  <div class="choose_flex_type" v-for="(section,index) in priceSection.value" @click="choosePrice(index)">
                    <p :class="{choose_type_sele: priceSection.chooseIndex == index}">{{setTranPrice(section.startPrice)}}-{{setTranPrice(section.endPrice)}}万</p>
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
                <div class="activity_type" v-for="condition in conditions">
                  <div class="type_title">
                    {{condition.specName}}
                  </div>
                  <div class="type_list">
                    <div class="type_item" v-for="(item,index) in condition.value" @click="chooseItem(condition,index)">
                      <p :class="{choose_item_sele: condition.showNum == index}">{{item}}</p>
                    </div>
                  </div>
                </div>
                <div class="activity_submit">
                  <div style="padding: 0.5rem;">
                    <button @click="resItem">重置</button>
                    <button class="submit_true" @click="submitItem">确认</button>
                  </div>
                </div>
              </div>
            </section>
          </transition>
        </div>
      </section>
      <div style="position:absolute;height: 100%;width: 100%;z-index: -1;background-color: #fff;overflow: hidden;" :style="{height: scrollerHeight + 'px'}">
        <div class="car_type_list" id="typeDivId">
          <div class="type_item" v-for="(item,index) in selecteds">
            <p>
              {{item.val}}
            </p>
            <img src="../../assets/img/common/ic_screen_close.png" @click="delSelected(item,index)">
          </div>
        </div>
        <scroller style="font-size: 12px;position:relative;" :style="{height:scrollerHeight - marginHeight + 'px' }"
                  :on-refresh="refresh"
                  :on-infinite="infinite"
                  refresh-layer-color="#f5ca1d"
                  loading-layer-color="#f5ca1d">
          <svg class="spinner" style="fill: #f5ca1d;" slot="refresh-spinner" viewBox="0 0 64 64">
            <g>
              <circle cx="16" cy="32" stroke-width="0" r="3">
                <animate attributeName="fill-opacity" dur="750ms" values=".5;.6;.8;1;.8;.6;.5;.5"
                         repeatCount="indefinite"></animate>
                <animate attributeName="r" dur="750ms" values="3;3;4;5;6;5;4;3" repeatCount="indefinite"></animate>
              </circle>
              <circle cx="32" cy="32" stroke-width="0" r="3.09351">
                <animate attributeName="fill-opacity" dur="750ms" values=".5;.5;.6;.8;1;.8;.6;.5"
                         repeatCount="indefinite"></animate>
                <animate attributeName="r" dur="750ms" values="4;3;3;4;5;6;5;4" repeatCount="indefinite"></animate>
              </circle>
              <circle cx="48" cy="32" stroke-width="0" r="4.09351">
                <animate attributeName="fill-opacity" dur="750ms" values=".6;.5;.5;.6;.8;1;.8;.6"
                         repeatCount="indefinite"></animate>
                <animate attributeName="r" dur="750ms" values="5;4;3;3;4;5;6;5" repeatCount="indefinite"></animate>
              </circle>
            </g>
          </svg>
          <div class="car_list">
            <div class="list_item" v-for="commodity in commodities" @click="openDetail(commodity.defaultProduct)">
              <div class="cont_img">
                <img src="../../assets/img/home/1.png" width="100%">
              </div>
              <div class="cont">
                <div class="cont_title">
                  {{commodity.commodityName}}
                </div>
                <div class="cont_price">
                  <span style="font-size: 1rem;">￥</span>{{setTranPrice(commodity.sellPrice)}}万
                </div>
                <div class="cont_local">
                  <img src="../../assets/img/common/ic_nav_ocation.png" style="width: 1rem;">
                  <span style="vertical-align: middle">{{commodity.seller.sellerName}}</span>
                </div>
              </div>
            </div>
          </div>
          <svg class="spinner" style="fill: #f5ca1d;" slot="infinite-spinner" viewBox="0 0 64 64">
            <g>
              <circle cx="16" cy="32" stroke-width="0" r="3">
                <animate attributeName="fill-opacity" dur="750ms" values=".5;.6;.8;1;.8;.6;.5;.5"
                         repeatCount="indefinite"></animate>
                <animate attributeName="r" dur="750ms" values="3;3;4;5;6;5;4;3" repeatCount="indefinite"></animate>
              </circle>
              <circle cx="32" cy="32" stroke-width="0" r="3.09351">
                <animate attributeName="fill-opacity" dur="750ms" values=".5;.5;.6;.8;1;.8;.6;.5"
                         repeatCount="indefinite"></animate>
                <animate attributeName="r" dur="750ms" values="4;3;3;4;5;6;5;4" repeatCount="indefinite"></animate>
              </circle>
              <circle cx="48" cy="32" stroke-width="0" r="4.09351">
                <animate attributeName="fill-opacity" dur="750ms" values=".6;.5;.5;.6;.8;1;.8;.6"
                         repeatCount="indefinite"></animate>
                <animate attributeName="r" dur="750ms" values="5;4;3;3;4;5;6;5" repeatCount="indefinite"></animate>
              </circle>
            </g>
          </svg>
        </scroller>
      </div>

    </div>
  </div>

</template>

<script>
  import headTop from "../../components/header/head"
  import {categorySearch, getSearch} from "../../service/getData";
  import {delArrayAll, delArrayOne} from "../../config/mUtils";
  import {tranPrice, tranThouOfPrice} from "../../config/dataFormat";
  import storage from "../../store/storage";
  import {CAR_LIST_SESSION} from "../../config/constant";

  export default {
    name: "Carlist",
    components: {
      headTop
    },
    data() {
      return {
        commodities: [],
        sortBy: '', // 筛选的条件
        sortTitle: '默认排序', //排序名称
        sortByType: '', // 根据何种方式排序
        enFocus: false,//是否焦点在input上
        selecteds: [], //已选择的所有条件
        priceSection: {chooseIndex: -1, value: []}, //价格区间块
        priceSect: {lowPrice: '', highPrice: ''}, //自定义价格区间
        conditions: [],//展示的筛选
        chooseConditions: [],//确定选择的筛选
        searchVo: {
          size: 10,
          page: 0,
          totalPage: 0,
          brandId: null,
          categoryId: null,
          sellerId: null,
          lowestPrice: '',
          highestPrice: '',
          priceSection: null,
          commoditySpecs: [],
          sortBy: null,
          direction: String
        },
        isInfinite: true,
        scrollerHeight: '',
        marginHeight: ''
      }
    },
    methods: {
      // 点击顶部三个选项，展示不同的列表，选中当前选项进行展示，同时收回其他选项
      async chooseType(type) {
        //重置自定义价格区间
        if (!this.searchVo.lowestPrice) {
          this.priceSect = {
            lowPrice: '',
            highPrice: ''
          }
        }
        //重置筛选数据
        this.conditions = [];
        for (let item of this.chooseConditions) {
          this.conditions.push({
            specName: item.specName,
            value: item.value,
            showNum: item.showNum
          })
        }
        if (this.sortBy !== type) {
          this.sortBy = type;
        } else {
          this.sortBy = '';
        }
      },
      //点击某个排序方式，获取事件对象的data值，并根据获取的值重新获取数据渲染
      sortList(sort, title, asc) {
        if (this.sortTitle != title) {
          this.sortByType = sort;
          this.sortTitle = title;
          if (this.sortByType == '') {
            this.searchVo.sortBy = null;
          } else {
            this.searchVo.sortBy = sort;
          }
          if (asc || this.sortByType == '') {
            this.searchVo.direction = "ASC";
          } else {
            this.searchVo.direction = "DESC";
          }
          this.getData();
        }
        this.sortBy = '';
      },
      delSelected(selected, index) {
        if (selected.type == 'commoditySpecs') {
          console.log(this.searchVo.commoditySpecs);
          this.searchVo.commoditySpecs = delArrayOne(this.searchVo.commoditySpecs, selected.key, 'specName');
          for (let condition of this.chooseConditions) {
            if (condition.specName == selected.key) {
              condition.showNum = -1;
              break;
            }
          }
        } else {
          this.searchVo[selected.type] = null;
        }
        this.selecteds.splice(index, 1);
        this.getData();
      },
      //跳转商品详情页
      openDetail(productId) {
        this.$router.push({
          path: '/car-detail',
          query: {productId: productId}
        })
      },
      //点击选择筛选项
      chooseItem(condition, index) {
        if (condition.showNum != index) {
          condition.showNum = index;
        } else {
          condition.showNum = -1;
        }
      },
      //筛选确认
      submitItem() {
        this.sortBy = '';
        //重新录入筛选选项
        this.chooseConditions = [];
        this.searchVo.commoditySpecs = [];
        this.selecteds = delArrayAll(this.selecteds, 'commoditySpecs', 'type');
        for (let item of this.conditions) {
          this.chooseConditions.push({
            specName: item.specName,
            value: item.value,
            showNum: item.showNum
          });
          if (item.showNum >= 0) {
            this.searchVo.commoditySpecs.push({
              specName: item.specName,
              value: item.value[item.showNum],
            });
            this.selecteds.push({
              key: item.specName,
              val: item.value[item.showNum],
              type: 'commoditySpecs'
            });
          }
        }
        //重新请求数据
        this.getData();
      },
      //重置筛选
      resItem() {
        for (let condition of this.conditions) {
          condition.showNum = -1;
        }
        this.selecteds = delArrayAll(this.selecteds, 'commoditySpecs', 'type');
        this.searchVo.commoditySpecs = [];
      },
      choosePrice(index) {
        if (this.priceSection.chooseIndex != index) {
          this.priceSection.chooseIndex = index;
          if (index == -1) {
            this.searchVo.priceSection = null;
          } else {
            this.searchVo.priceSection = this.priceSection.value[index];
          }
          this.searchVo.lowestPrice = '';
          this.searchVo.highestPrice = '';
          this.getData();
        }
        this.sortBy = '';
      },
      setPriceSect() {
        if (this.priceSect.lowPrice && this.priceSect.highPrice) {
          this.searchVo.lowestPrice = tranThouOfPrice(this.priceSect.lowPrice) ;
          console.log(this.searchVo.lowestPrice);
          this.searchVo.highestPrice = tranThouOfPrice(this.priceSect.highPrice);
          this.searchVo.priceSection = null;
          this.priceSection.chooseIndex = -2;
          this.sortBy = '';
          this.getData();
        }
      },
      setTranPrice(price) {
        return tranPrice(price);
      },
      refresh(done) {
        this.getData(done);
      },
      infinite(done) {
        if (!this.isInfinite) {
          done(true);
          return;
        } else {
          let _this = this;
          this.searchVo.page++;
          this.getData(done, true);
        }
      },
      async getData(done, isInf) {
        let _this = this;
        if (!isInf) {
          this.searchVo.page = 0;
        }
        this.$vux.loading.show("加载中");
        await categorySearch(this.searchVo).then(res => {
          if (res.result == 'SUCCESS') {
            if (res.data.pageFinder) {
              if (!isInf) {
                _this.commodities = res.data.pageFinder.data;
              } else {
                _this.commodities = _this.commodities.concat(res.data.pageFinder.data);
              }
              _this.searchVo.totalPage = res.data.pageFinder.pageCount;
              if (_this.searchVo.page >= _this.searchVo.totalPage - 1) {
                _this.isInfinite = false;
              }
            } else {
              _this.commodities = [];
            }
          } else {
            if (isInf) {
              _this.isInfinite = false;
            }
          }
        });
        _this.$vux.loading.hide();
        if (done) {
          done();
        }
      },
      async getSearchVo() {
        getSearch().then(res => {
          if (res.result == 'SUCCESS') {
            if (res.data.condition) {
              for (let item in res.data.condition) {
                this.conditions.push({
                  specName: item,
                  value: res.data.condition[item],
                  showNum: -1
                });
              }
              this.chooseConditions = JSON.parse(JSON.stringify(this.conditions));
            }
            this.priceSection.value = res.data.priceSection;
          }
        })
      },
      toSearch() {
        this.$router.push({
          path: "/search"
        })
      },
      chooseBrand() {
        this.$router.push({
          path: "/choose-brand"
        })
      },
      createdData(){
        if (this.$route.query.categoryId) {
          let categoryId = this.$route.query.categoryId;
          let categoryName = this.$route.query.categoryName;
          this.searchVo.categoryId = categoryId;
          this.selecteds.push({
            key: categoryId,
            val: categoryName,
            type: 'categoryId'
          })
        }else if (this.$route.query.brandId) {
          let brandId = this.$route.query.brandId;
          let brandName = this.$route.query.brandName;
          this.searchVo.brandId = brandId;
          this.selecteds.push({
            key: brandId,
            val: brandName,
            type: 'brandId'
          })
        } else {
          this.selecteds.push({
            key: 0,
            val: '全部',
            type: 'all'
          })
        }
        this.getData();
        this.getSearchVo();
      }
    },
    watch: {
      selecteds(newVal) {
        this.scrollerHeight = document.documentElement.clientHeight - document.getElementById("section").offsetTop - document.getElementById("section").clientHeight;
        this.marginHeight = document.getElementById("typeDivId").clientHeight;
      }
    },
    created() {
      this.createdData();
    },
    activated() {
      //重置数据
      if (storage.fetchSession(CAR_LIST_SESSION) && storage.fetchSession(CAR_LIST_SESSION).length != 0) {
        this.searchVo = {
          size: 10,
          page: 0,
          totalPage: 0,
          brandId: null,
          categoryId: null,
          sellerId: null,
          lowestPrice: '',
          highestPrice: '',
          priceSection: null,
          commoditySpecs: [],
          sortBy: null,
          direction: String
        };
        this.sortBy = '';
        this.sortByType = '';
        this.sortTitle= '默认排序';
        this.selecteds = [];
        this.conditions = [];
        this.priceSection= {chooseIndex: -1, value: []};
        this.priceSect= {lowPrice: '', highPrice: ''};
        this.isInfinite = false;
        this.createdData();
      }
    },
    beforeRouteLeave(to, from, next) {
      storage.removeSession(CAR_LIST_SESSION);
      next();
    },
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
            outline: unset;
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
          padding: 0.5rem 1.3rem 0.5rem 0.5rem;
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
    padding: 0.2rem 0.5rem;
    background-color: #f5f5f5;
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
        flex: 0 0 9rem;;
        img {
          border-radius: 4px;
          height: 6rem;
        }
      }
      .cont {
        flex: 1;
        padding: 0 0 0.7rem 0.7rem;
        height: 6rem;
        border-bottom: #ececec 1px solid;
        .cont_title {
          height: 3rem;
          font-size: 1rem;
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          -webkit-line-clamp: 2;
        }
        .cont_price {
          color: #F54E4E;
          font-size: 1.3rem;
          font-weight: bold;
        }

        .cont_local {
          font-size: 0.8rem;
          color: #999;
          overflow: hidden;
          text-overflow: ellipsis;
          display: -webkit-box;
          -webkit-box-orient: vertical;
          -webkit-line-clamp: 1;
          img {
            vertical-align: middle;
          }
        }
      }
    }
  }

  .choose_type_sele {
    border: #f5ca1d 1px solid;
    color: #f5ca1d;
    background-color: #FFFBEB !important;
  }

  .choose_item_sele {
    border: #f5ca1d 1px solid;
    color: #f5ca1d !important;
    background-color: #FFFBEB !important;
  }

  .focus_input {
    position: fixed !important;
    z-index: 99 !important;
    top: 0 !important;
  }
</style>
