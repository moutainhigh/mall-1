<template>
  <div style="position: relative">
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
    <div class="headPhoto" v-if="isPhoto" @click.stop="addPic">
      <div style="position: absolute; width: 100%; height: 120px"></div>
      <img src="../assets/img/headPhotograph.png"/>
      <p style="font-size: 13px">请点击此处，拍摄投保人正面头像</p>
    </div>
    <button class="clearButton" v-if="!isPhoto" @click='delImage'>清除</button>
    <input id="image" type="file" accept="image/*" capture="camera" @change="onFileChange"
           style="display: none;">
    <div v-if="!isPhoto">
      <img class="headPhoto-img" :src="imgUrl">
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
      <button class="i-footer" @click="next">
        <div>下一步</div>
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
        imgUrl: '',
        isPhoto: true,
        option: {
          penColor: "rgb(0, 0, 0)",
          backgroundColor: "#dcdcdc",

        }
      }
    },
    components: {Signature},
    watch: {
      'imgUrl': {
        handler(newurl, oldurl) {
          this.toggleAddPic();
        },
        deep: true
      }
    },
    methods: {
      toggleAddPic: function () {
        let vm = this;
        if (vm.imgUrl !== '') {
          vm.isPhoto = false;
        }
      },
      //添加图片
      addPic: function () {
        document.getElementById("image").click();
        return false;
      },
      onFileChange: function (e) {
        var files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.createImage(files, e);
      },
      createImage: function (file, e) {
        let vm = this;
        lrz(file[0], {width: 480}).then(function (rst) {
          vm.imgUrl = rst.base64;
          return rst;
        }).always(function () {
          // 清空文件上传控件的值
          e.target.value = null;
        });
      },
      //删除图片
      delImage: function () {
        let vm = this;
        vm.imgUrl = '';
        vm.isPhoto = true;
      },
      saveImage: function () {
        let vm = this;
        let urlArr = [],
          imgUrl = this.imgUrl;
        if (imgUrl.indexOf('file') == -1) {
          urlArr.push(imgUrl.split(',')[1]);
        } else {
          urlArr.push(imgUrl);
        }
        //数据传输操作
      },

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
        var jpeg = _this.$refs.signature.save('image/jpeg')
        var jpeg1 = _this.$refs.signature1.save('image/jpeg')
        console.log(jpeg);
        console.log(jpeg1);
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
      },
      next() {
        if (this.state !== true){
          alert('请勾选同意条款');
          return false;
        }
        if (this.clickSign === false) {
          alert('请签署投保提示书签名');
          return false;
        }
        if (this.clickSign1 === false) {
          alert('请签署投保单签名');
          return false;
        }
        // this.save();
        this.$router.push("upload-data")
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

  .headPhoto-img {
    width: 50vw;
    margin-left: 25vw;
    padding: 10px 0;
  }

  .clearButton {
    float: right;
    color: #999;
    border-radius: 5px;
    border: 1px solid #dcdcdc;
    background: #EDEDED;
    margin-right: 10px;
    margin-top: 10px;
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
