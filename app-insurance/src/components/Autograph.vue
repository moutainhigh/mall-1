<template>
  <div>
    <!--<button @click="save">保存</button>-->
    <!--<button @click="clear">清除</button>-->
    <!--<button @click="undo">撤销</button>-->

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
      <button v-if="clickSign" @click="clear">清除</button>
    </div>
    <div class="canvas">
      <div class="sign" v-if="!clickSign" @click="checkSign">点击签名
      </div>
      <div v-if="clickSign">
        <Signature ref="signature" :sigOption="option" :w="'92vw'" :h="'20vh'"></Signature>
      </div>
    </div>


    <div class="title" style="color: #e1bb3a">投保单签名</div>
    <div class="headPhoto">
      <img src="../assets/img/headPhotograph.png"/>
      <p style="font-size: 13px">请点击此处，拍摄投保人正面头像</p>
    </div>
    <div class="title-sign">投保人签名
      <button v-if="clickSign1" @click="clear1">清除</button>
    </div>
    <div class="canvas">
      <div class="sign" v-if="!clickSign1" @click="checkSign1">点击签名</div>
      <div v-if="clickSign1">
        <Signature ref="signature1" :sigOption="option" :w="'92vw'" :h="'20vh'"></Signature>
      </div>
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
  import Signature from './Signature.vue'

  export default {
    name: 'Autograph',
    data() {
      return {
        state: false,
        clickSign: false,
        clickSign1: false,
        option: {
          penColor: "rgb(0, 0, 0)",
          backgroundColor: "#dcdcdc",

        }
      }
    },
    components: {Signature},
    methods: {
      check() {
        this.state = !this.state;
      },
      checkSign() {
        this.clickSign = !this.clickSign;
      },
      checkSign1() {
        this.clickSign1 = !this.clickSign1;
      },
      save() {
        var _this = this;
        var png = _this.$refs.signature.save()
        var jpeg = _this.$refs.signature.save('image/jpeg')
        var svg = _this.$refs.signature.save('image/svg+xml');
        console.log(png);
        console.log(jpeg)
        console.log(svg)
      },
      clear() {
        var _this = this;
        _this.$refs.signature.clear();
      },
      clear1() {
        var _this = this;
        _this.$refs.signature1.clear();
      },
      fromDataURL(url) {
        var _this = this;
        _this.$refs.signature.fromDataURL("data:image/png;base64,iVBORw0K...");
      }
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
    padding: 15px 15px 2px 15px;
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
    padding: 15px 0;
  }

  .sign {
    background: #dcdcdc;
    width: 92%;
    height: 20vh;
    border-radius: 10px;
    margin: 0 15px;
    text-align: center;
    line-height: 20vh;
    color: #999;
    font-size: 13px;
  }

  .sign button {
    float: right;
    color: #999;
    border-radius: 5px;
    border: 1px solid #dcdcdc;
    background: #EDEDED;
    margin-top: -2px
  }
</style>
