<template>
  <div>
    <div class="title">投保单资料签署</div>
    <div @click="check">
      <img v-if="!state" class="checkIcon" src="../assets/img/checkoff.png">
      <img v-if="state" class="checkIcon" src="../assets/img/checkon.png">
      <div class="tip">
        <p>本人已阅读并理解<span>《投保须知》、《保险条款》、《投保提示书》</span>，已充分了解并认可保险责任、责任免除、保险范围、理赔程序、退保等相关条款。</p>
      </div>
    </div>
    <div class="title" style="color: #e1bb3a">投保提示书签名</div>
    <div class="title-sign">投保人签名</div>
    <div class="canvas">
      <canvas id="myCanvas1"></canvas>
      <span>点击签名</span>
    </div>
    <div class="title" style="color: #e1bb3a">投保单签名</div>
    <div class="headPhoto">
      <img src="../assets/img/headPhotograph.png"/>
      <p style="font-size: 13px">请点击此处，拍摄投保人正面头像</p>
    </div>
    <div class="title-sign">投保人签名</div>
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
  export default {
    name: 'Autograph',
    data() {
      return {
        state: false
      }
    },
    methods: {
      check() {
        this.state = !this.state;
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
    padding: 15px 15px 0 15px;
    font-size: 13px;
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
