<template>
  <div class="box">
    <div style="height: 3rem"></div>
    <div class="search">
      <button class="search-magnifier">
        <img src="../../assets/img/common/ic_search.png">
      </button>
      <input type="text" style="padding-left: 40px" v-model="searchContent" placeholder="输入城市或拼音">
      <img style="position: absolute; right: 70px; top: 18px; width: 16px" v-if="searchContent != ''"
           src="../../assets/img/common/search_ic_eliminate.png" @click="clearInput">
      <button class="cancel">取消</button>
    </div>

    <div class="list-view" ref="listView">
      <div>
        <div v-for="(group, index) in singers" class="list-group" :key="group.id" ref="listGroup">
          <h2 v-if="index != 0 && index !=1" class="list-group-title">{{ group.title }}</h2>
          <div v-if="index != 0 && index !=1">
            <div v-for="item in group.items" class="list-group-item" :key="item.id">
              <span class="name">{{ item.name }}</span>
            </div>
          </div>
          <div v-if="index == 0" style="background: #ffffff">
            <p class="cityTitle">定位城市</p>
            <button class="locationCity">深圳</button>
          </div>
          <div v-if="index == 1" style="background: #ffffff">
            <p class="cityTitle">热门城市</p>
            <button class="hotCity" v-for="city in hotCitys">{{city}}</button>
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
  </div>
</template>

<script>
  import BScroll from 'better-scroll'
  import {cityData} from '../../js/city.js'

  export default {
    name: "Location",
    data() {
      return {
        singers: cityData,
        scrollY: 0,
        currentIndex: 0,
        hotCitys: ['北京', '深圳', '上海', '武汉', '长沙', '中山', '成都', '厦门', '珠海'],
        searchContent: ''
      }
    },
    created() {
      this.touch = {}
      // 初始化 better-scroll 必须要等 dom 加载完毕
      setTimeout(() => {
        this._initSrcoll()
        this._calculateHeight()
      }, 20)
    },
    methods: {
      _initSrcoll() {
        // console.log('didi')
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
        console.log(index,'index')
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
    },
    watch: {
      scrollY(newVal) {
        // 向下滑动的时候 newVal 是一个负数，所以当 newVal > 0 时，currentIndex 直接为 0
        if (newVal > 0) {
          this.currentIndex = 0
          return
        }
        // 计算 currentIndex 的值
        for (let i = 0; i < this.listHeight.length - 1; i++) {
          let height1 = this.listHeight[i]
          let height2 = this.listHeight[i + 1]

          if (-newVal >= height1 && -newVal < height2) {
            this.currentIndex = i
            return
          }
        }
        // 当超 -newVal > 最后一个高度的时候
        // 因为 this.listHeight 有头尾，所以需要 - 2
        this.currentIndex = this.listHeight.length - 2
      },
    },
    computed: {
      shortcutList() {
        return this.singers.map((group) => {
          return group.title.substr(0, 1)
        })
      }
    }
  }
</script>

<style scoped lang="scss">
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
    top: 0
  }

  .search input {
    background: #F1F3F5;
    border: 0;
    height: 36px;
    border-radius: 36px;
    width: 69%;
    margin: 8px 12px 8px 16px;
    outline: none;
  }

  .cancel {
    border: 0;
    background: none;
    color: #333333;
    height: 100%;
    padding: 0;
    outline: none;
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
</style>
