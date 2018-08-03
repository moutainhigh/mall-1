<template>
  <div>
    <head-top :head-title="'选择品牌'"></head-top>

    <div class="list-view" ref="listView">
      <div v-if="singers">
        <div v-for="(group, index) in singers" class="list-group" :key="parseInt(group.id)" ref="listGroup">
          <h2 v-if="index != 0 && index !=1" class="list-group-title">{{ group.title }}</h2>
          <div v-if="index != 0 && index !=1">
            <div v-for="item in group.items" class="list-group-item" :key="parseInt(item.id)">
              <span class="name">{{ item.name }}</span>
            </div>
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
               :key="parseInt(item.id)"
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
  import headTop from "../../components/header/head"
  import BScroll from 'better-scroll'

  export default {
    name: "ChooseCarBrand",
    components: {
      headTop
    },
    data() {
      return {
        singers: null,
        scrollY: 0,
        currentIndex: 0,
        hotCitys: ['北京', '深圳', '上海', '武汉', '长沙', '中山', '成都', '厦门', '珠海'],
        searchContent: '',
        localCity: ''
      }
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
    },
    computed: {
      shortcutList() {
        // return this.singers.map((group) => {
        //   return group.title.substr(0, 1)
        // })
      }
    },
  }
</script>

<style scoped>

</style>
