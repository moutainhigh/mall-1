<template>
  <div>
    <div class="hello">
      <!--touchstart,touchmove,touchend,touchcancel 这-->
      <button type="" v-on:click="clear">清除</button>
      <button v-on:click="save">保存</button>
      <canvas id="canvas" width="300" height="600" style="border:1px solid black">Canvas画板</canvas>
      <img v-bind:src="url" alt="">
    </div>

    <div class="title">投保单资料签署</div>
    <div @click="check">
      <img v-if="!state" class="checkIcon" src="../assets/img/checkoff.png">
      <img v-if="state" class="checkIcon" src="../assets/img/checkon.png">
      <div class="tip">
        <p>本人已阅读并理解<span>《投保须知》、《保险条款》、《投保提示书》</span>，已充分了解并认可保险责任、责任免除、保险范围、理赔程序、退保等相关条款。</p>
      </div>
    </div>
    <div class="title" style="color: #e1bb3a">投保提示书签名</div>
    <div class="title-sign">投保人签名
      <button>清除</button>
    </div>
    <div class="canvas">
      <canvas id="myCanvas1"></canvas>
      <span>点击签名</span>
    </div>
    <div class="title" style="color: #e1bb3a">投保单签名</div>
    <div class="headPhoto">
      <img src="../assets/img/headPhotograph.png"/>
      <p style="font-size: 13px">请点击此处，拍摄投保人正面头像</p>
    </div>
    <div class="title-sign">投保人签名
      <button>清除</button>
    </div>
    <div class="canvas">
      <canvas id="myCanvas2"></canvas>
      <span>点击签名</span>
    </div>
    <div style="height: 48px;">
      <button class="i-footer">
        <router-link to="upload-data">
          <div>下一步</div>
        </router-link>
      </button>
    </div>
  </div>
</template>

<script>
  var draw;
  var preHandler = function (e) {
    e.preventDefault();
  }

  class Draw {
    constructor(el) {
      this.el = el
      this.canvas = document.getElementById(this.el)
      this.cxt = this.canvas.getContext('2d')
      this.stage_info = canvas.getBoundingClientRect()
      this.path = {
        beginX: 0,
        beginY: 0,
        endX: 0,
        endY: 0
      }
    }

    init(btn) {
      var that = this;

      this.canvas.addEventListener('touchstart', function (event) {
        document.addEventListener('touchstart', preHandler, false);
        that.drawBegin(event)
      })
      this.canvas.addEventListener('touchend', function (event) {
        document.addEventListener('touchend', preHandler, false);
        that.drawEnd()

      })
      this.clear(btn)
    }

    drawBegin(e) {
      var that = this;
      window.getSelection() ? window.getSelection().removeAllRanges() : document.selection.empty()
      this.cxt.strokeStyle = "#000"
      this.cxt.beginPath()
      this.cxt.moveTo(
        e.changedTouches[0].clientX - this.stage_info.left,
        e.changedTouches[0].clientY - this.stage_info.top
      )
      this.path.beginX = e.changedTouches[0].clientX - this.stage_info.left
      this.path.beginY = e.changedTouches[0].clientY - this.stage_info.top
      canvas.addEventListener("touchmove", function () {
        that.drawing(event)
      })
    }

    drawing(e) {
      this.cxt.lineTo(
        e.changedTouches[0].clientX - this.stage_info.left,
        e.changedTouches[0].clientY - this.stage_info.top
      )
      this.path.endX = e.changedTouches[0].clientX - this.stage_info.left
      this.path.endY = e.changedTouches[0].clientY - this.stage_info.top
      this.cxt.stroke()
    }

    drawEnd() {
      document.removeEventListener('touchstart', preHandler, false);
      document.removeEventListener('touchend', preHandler, false);
      document.removeEventListener('touchmove', preHandler, false);
      //canvas.ontouchmove = canvas.ontouchend = null
    }

    clear(btn) {
      this.cxt.clearRect(0, 0, 300, 600)
    }

    save() {
      return canvas.toDataURL("image/png")
    }
  }

  export default {
    name: 'Autograph',
    data() {
      return {
        state: false,
        msg: 'Welcome to Your Vue.js App',
        val: true,
        url: ""
      }
    },
    mounted() {
      draw = new Draw('canvas');
      draw.init();
    },
    methods: {
      check() {
        this.state = !this.state;
      },
      clear: function () {
        draw.clear();
      },
      save: function () {
        var data = draw.save();
        this.url = data;
        console.log(data)
      },
      mutate(word) {
        this.$emit("input", word);
      },
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .title {
    margin-bottom: 10px;
    background-color: #ffffff;
    padding: 15px;
    font-size: 13px;
  }

  .title-sign {
    background-color: #ffffff;
    padding: 15px 15px 0 15px;
    font-size: 13px;
  }

  .title-sign button {
    float: right;
    color: #999;
    border-radius: 5px;
    border: 1px solid #dcdcdc;
    background: #EDEDED;
    margin-top: -2px
  }

  .checkIcon {
    float: left;
    width: 4vw;
    margin-left: 15px;
    margin-top: 15px
  }

  .tip {
    background-color: #ffffff;
    padding: 15px 15px 0 40px;
    margin-bottom: 10px;
  }

  .tip img {
    width: 4vw;
  }

  .tip p {
    font-size: 12px;
    margin: 0;
    padding-bottom: 15px;
    text-align: left;
  }

  .tip span {
    color: #c01212;
  }

  .headPhoto {
    border-bottom: 2px solid #f3f3f3;
    padding: 0 4vw;
    background: #ffffff;
  }

  .headPhoto img {
    width: 23vw;
    padding-top: 4vh;
    margin-left: 38%;
    margin-bottom: 10px;
  }

  .headPhoto p {
    padding-bottom: 2vh;
    text-align: center;
  }

  .canvas {
    position: relative;
    height: auto;
    margin-bottom: 10px;
    background: #ffffff;
  }

  #myCanvas1 {
    background: #dcdcdc;
    width: 92%;
    height: 20vh;
    border-radius: 10px;
    margin: 15px;
  }

  #myCanvas2 {
    background: #dcdcdc;
    width: 92%;
    height: 20vh;
    border-radius: 10px;
    margin: 15px;
  }

  .canvas span {
    position: absolute;
    left: 0;
    right: 0;
    top: 45%;
    margin: auto;
    color: #999;
    text-align: center;
    font-size: 13px;
  }

  .message {
    width: 100%;
    height: 20vh;
  }
</style>
