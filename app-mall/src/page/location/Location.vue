<template>
  <div class="box">
    <div style="height: 3rem"></div>
    <div class="search">
      <div style="width: 85%;flex: 1; padding-left: 1rem">
        <div class="search-con">
          <img src="../../assets/img/common/ic_search.png"
               style="width: 1rem;position: absolute;margin: 0.5rem 0 0 0.8rem;">
          <input type="text" class="search-text" :class="{'text': searchContent != ''}" v-model="searchContent"
                 placeholder="输入城市或拼音">
          <img style="position: absolute; right: 4.5rem; top: 1rem; width: 16px" v-if="searchContent != ''"
               src="../../assets/img/common/search_ic_eliminate.png" @click="clearInput">
        </div>
      </div>
      <button class="cancel" @click="back">取消</button>
    </div>

    <div class="list-view" ref="listView" v-if="searchContent == ''">
      <div>
        <div v-for="(sort, index) in sortedData" class="list-group" :key="sort.detail.id" ref="listGroup">
          <h2 v-if="index != 0 && index != 1" class="list-group-title">{{ sort.initials }}</h2>
          <div v-if="index != 0 && index != 1">
            <div v-for="detail in sort.detail" class="list-group-item" :key="detail.id"
                 @click="chooseCity(detail.city)">
              <span class="name">{{ detail.city }}</span>
            </div>
          </div>

          <div v-if="index == 0" style="background: #ffffff">
            <p class="cityTitle">定位城市</p>
            <button class="locationCity">{{localCity}}</button>
          </div>
          <div v-if="index == 1" style="background: #ffffff">
            <p class="cityTitle">热门城市</p>
            <button class="hotCity" v-for="city in hotCitys" @click="chooseCity(city)">{{city}}</button>
          </div>
        </div>
      </div>
      <div class="list-shortcut">
        <div>
          <div v-for="(item, index) in shortcutList"
               class="item"
               :data-index="index"
               :key="item.id"
               @touchstart="onShortcutStart"
               @touchmove.stop.prevent="onShortcutMove"
               :class="{'current': currentIndex === index}"
          >
            {{ item }}
          </div>
        </div>
      </div>
    </div>

    <div class="history" v-if="searchContent != ''">
      <div class="listItem" v-for="result in resultList" @click="chooseCity(result)"><p>{{result}}</p></div>
    </div>
  </div>
</template>

<script>
  import BScroll from 'better-scroll'
  import {cityData} from '../../js/cityList.js'
  import AMap from 'AMap';
  import storage from "../../store/storage";
  import {LOCATION} from "../../config/constant";

  var pinyin = require("pinyin");

  export default {
    name: "Location",
    data() {
      return {
        singers: cityData,
        scrollY: 0,
        currentIndex: 0,
        hotCitys: ['北京', '深圳', '上海', '武汉', '长沙', '中山', '成都', '厦门', '珠海'],
        searchContent: '',
        localCity: '',
        sortedData: [],
        resultList: [],
      }
    },
    created() {
      // 传入的汉字属性名必须为name
      const pinyinData = this.singers.map(item => ({
        detail: item,
        initials: pinyin(item.city, {
          style: pinyin.STYLE_FIRST_LETTER
        })[0][0].toUpperCase()
      }));
      //定位与热门
      let local = '定位';
      let hot = '热门';
      this.sortedData.push({initials: local});
      this.sortedData.push({initials: hot});

      // todo 生成26个字母的数组对象
      this.letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
      for (let letter of this.letters) {
        this.sortedData.push({initials: letter});
      }
      // todo 过滤掉没有信息的字母对象
      this.sortedData = this.sortedData.filter(item => {
        item.detail = [];
        //自定义定位与热门
        if (item.initials === '定位') {
          item.detail.push('');
        }
        if (item.initials === '热门') {
          item.detail.push('');
        }
        pinyinData.map(jtem => {
          if (item.initials === jtem.initials) {
            item.detail.push(jtem.detail);
          }
        });
        return item.detail.length > 0;
      });


      this.touch = {}
      // 初始化 better-scroll 必须要等 dom 加载完毕
      setTimeout(() => {
        this._initSrcoll()
        this._calculateHeight()
      }, 20)
    },
    methods: {
      _initSrcoll() {
        this.scroll = new BScroll(this.$refs.listView, {
          probeType: 3,
          click: true
        })

        this.scroll.on('scroll', (pos) => {
          this.scrollY = pos.y
        })
      },
      onShortcutStart(e) {
        // 获取到绑定的 index
        let index = e.target.getAttribute('data-index')
        // 使用 better-scroll 的 scrollToElement 方法实现跳转
        this.scrollToElement(index)

        // 记录一下点击时候的 Y坐标 和 index
        let firstTouch = e.touches[0].pageY
        this.touch.y1 = firstTouch
        this.touch.anchorIndex = index
      },
      onShortcutMove(e) {
        // 再记录一下移动时候的 Y坐标，然后计算出移动了几个索引
        let touchMove = e.touches[0].pageY
        this.touch.y2 = touchMove
        // 这里的 16.7 是索引元素的高度
        let delta = Math.floor((this.touch.y2 - this.touch.y1) / 16.7)

        // 计算最后的位置
        // * 1 是因为 this.touch.anchorIndex 是字符串，用 * 1 偷懒的转化一下
        let index = this.touch.anchorIndex * 1 + delta
        this.scrollToElement(index)
      },
      scrollToElement(index) {
        // 处理边界情况
        // 因为 index 通过滑动距离计算出来的
        // 所以向上滑超过索引框框的时候就会 < 0，向上就会超过最大值
        if (index < 0) {
          return
        } else if (index > this.listHeight.length - 2) {
          index = this.listHeight.length - 2
        }
        // listHeight 是正的， 所以加个 -
        this.scrollY = -this.listHeight[index]
        this.scroll.scrollToElement(this.$refs.listGroup[index])
      },
      _calculateHeight() {
        this.listHeight = []
        const list = this.$refs.listGroup
        let height = 0
        this.listHeight.push(height)
        for (let i = 0; i < list.length; i++) {
          let item = list[i]
          height += item.clientHeight
          this.listHeight.push(height)
        }
      },
      clearInput() {
        this.searchContent = '';
      },
      local() {
        let vm = this;
        if (storage.fetchSession(LOCATION).length <= 0) {
          AMap.plugin('AMap.CitySearch', function () {
            var citySearch = new AMap.CitySearch();
            citySearch.getLocalCity(function (status, result) {
              if (status === 'complete' && result.info === 'OK') {
                vm.localCity = result.city.substr(0, 2);
                storage.saveSession(LOCATION, vm.localCity);
              }
            })
          })
        } else {
          this.localCity = storage.fetchSession(LOCATION);
        }
      },
      chooseCity(city) {
        this.clearInput();
        this.localCity = city;
        storage.saveSession(LOCATION, city);
      },
      back() {
        this.$router.go(-1);
      }
    },
    computed: {
      shortcutList() {
        return this.sortedData.map((sorted) => {
          return sorted.initials
        })
      }
    },
    watch: {
      searchContent: {
        handler(newVal, oldVal) {
          for (let sort of this.sortedData) {
            if (sort.detail.length > 0) {
              for (let detail of sort.detail) {
                if (detail.city) {
                  if (detail.city.indexOf(newVal) != -1) {
                    this.resultList.push(detail.city);
                  }
                }
              }
            }
          }
        }
      }
    },
    mounted() {
      this.local();
    }
  }
</script>

<style scoped lang="scss">
  .search-con {
    height: 2rem;
    width: 100%;
    background-color: #F1F3F5;
    border-radius: 1rem;
    margin-top: 0.5rem;
  }

  .search-text {
    color: #333333;
    font-size: 1rem;
    text-align: left;
    margin: 0;
    padding: 0.5rem 0 0 2.5rem;
  }

  .box {
    position: fixed;
    width: 100%;
    height: 100%;
  }

  .search {
    height: 52px;
    width: 100%;
    background: #ffffff;
    position: fixed;
    z-index: 100;
    top: 0;
    display: flex;
  }

  .search input {
    background: #F1F3F5;
    border: 0;
    border-radius: 36px;
    width: 69%;
    margin: 0;
    outline: none;
    font-size: 1rem;
  }

  .search input::-webkit-input-placeholder {
    color: #999999 !important;
  }

  .cancel {
    border: 0;
    background: none;
    color: #333333;
    height: 100%;
    padding: 0 1rem;
    outline: none;
    font-size: 1rem;
  }

  .search-magnifier {
    background: none;
    border: 0;
    position: absolute;
    outline: none;
  }

  .search-magnifier img {
    height: 18px;
    width: 18px;
    margin-left: 28px;
    margin-top: 17px
  }

  .cityTitle {
    font-size: 1rem;
    color: #888888;
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
    overflow: hidden;
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
        padding: 1rem 0 1rem 0;
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
      width: 30px;
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

  .history {
    /*height: 450px;*/
    padding-top: 7px;
    background: #ffffff
  }

  .history-clear {
    color: #f5ca1d;
    float: right;
    font-size: 15px;
  }

  .listItem {
    height: 50px;
    margin: 0 16px;
    border-bottom: 1px solid #ECECEC
  }

  .listItem p {
    padding: 18px 0;
    font-size: 1rem;
    color: #666666;
  }

  .text {
    padding-top: 0.4rem!important;
  }
</style>
