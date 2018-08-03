<template>
  <div>
    <!--投保提示书签名-->
    <div class="popContainer" v-if="clickSign1 && !show1">
      <div style="position: relative">
        <span
          style="position: absolute; color: #f5ca1d; right: 0; top: 0; margin: 20px 20px; transform:rotate(90deg);"
          v-if="clickSign1"
          @click="clear1">清除</span>
        <span
          style="position: absolute; color: #f5ca1d; right: 0; bottom: 0; margin: 20px 20px; transform:rotate(90deg);"
          v-if="clickSign1"
          @click="doneButton1">完成</span>
        <Signature ref="signature1" :sigOption="option" :w="'92vw'" :h="'90vh'"></Signature>
      </div>
      <!--<button class="doneButton" @click="doneButton1">完成</button>-->
    </div>
    <!--投保单签名-->
    <div class="popContainer" v-if="clickSign && !show">
      <div style="position: relative">
           <span
             style="position: absolute; color: #f5ca1d; right: 0; top: 0; margin: 20px 20px; transform:rotate(90deg);"
             v-if="clickSign"
             @click="clear">清除</span>
        <span
          style="position: absolute; color: #f5ca1d; right: 0; bottom: 0; margin: 20px 20px; transform:rotate(90deg);"
          v-if="clickSign"
          @click="doneButton">完成</span>
        <Signature ref="signature" :sigOption="option" :w="'92vw'" :h="'90vh'"></Signature>
      </div>
      <!--<button class="doneButton" @click="doneButton">完成</button>-->
    </div>

    <!--页面主体-->
    <div v-bind:class="{'animateGraph': (clickSign1 && !show1) || (clickSign && !show)}" style="position: relative">
      <div class="title">投保单资料签署</div>
      <div id="page1"></div>
      <div>
        <img v-if="!state" @click="check" class="checkIcon" src="../assets/img/unselect.png">
        <img v-if="state" @click="check" class="checkIcon" src="../assets/img/selected.png">
        <div class="tip">
          <p>本人已阅读并理解<span @click="imsurancePolicy">《投保须知》</span><span @click="loadclause">《保险条款》</span><span
            @click="insureNote">《投保提示书》</span>，已充分了解并认可保险责任、责任免除、保险范围、理赔程序、退保等相关条款。</p>
        </div>
      </div>
      <div class="title" style="color: #000; font-weight: normal; margin-bottom: 0">投保提示书签名</div>
      <div class="canvas">
        <span style="position: absolute; margin: 10px 30px; color: #666; font-size: 14px">投保人签名：</span>
        <div class="sign" v-if="!clickSign1 && !show1" @click="checkSign1"
             v-bind:style="{ background: submissionColor}">
          <p style="padding-top: 15vh" v-if="submissionSign === ''">点击签名（请用正楷进行签名）</p>
          <img v-if="submissionSign !== ''" class="sign"
               style="height: 30vh; width: auto; margin-left: 10vw; transform:rotate(-90deg);"
               :src="submissionSign">
        </div>
        <div class="sign" style="background: #ffffff; height: auto; line-height: normal; border: 1px solid #f5f5f5"
             @click="checkShow1"
             v-if="clickSign1 && show1">
          <img class="sign" style="height: 30vh; width: auto; margin-left: 10vw; transform:rotate(-90deg);"
               :src="submissionSign">
        </div>
      </div>

      <div class="title" style="color: #000; font-weight: normal; margin-bottom: 0">投保单签名</div>
      <div class="headPhoto" v-if="imgUrl === '' || imgUrl === undefined || imgUrl === null" @click.stop="addPic">
        <div style="position: relative">
          <div class="carmerBorder"></div>
          <img src="../assets/img/headPhotograph.png"/>
        </div>
        <p style="font-size: 13px">上传投保人正面头像</p>
      </div>
      <button class="clearButton" v-if="imgUrl !== undefined && imgUrl !== ''" @click='delImage'>清除</button>
      <input id="image" type="file" accept="image/*" capture="camera" @change="onFileChange"
             style="display: none;">
      <div v-if="imgUrl !== undefined && imgUrl !== ''">
        <img class="headPhoto-img" :src="imgUrl">
      </div>

      <div class="canvas">
        <span style="position: absolute; margin: 10px 30px; color: #666; font-size: 14px">投保人签名：</span>
        <div class="sign" v-if="!clickSign && !show" @click="checkSign" v-bind:style="{ background: policyholderColor}">
          <p style="padding-top: 15vh" v-if="policyholderSign === ''">点击签名（请用正楷进行签名）</p>
          <img v-if="policyholderSign !== ''" class="sign"
               style="height: 30vh; width: auto; margin-left: 10vw; transform:rotate(-90deg);"
               :src="policyholderSign">
        </div>

        <div class="sign" style="background: #ffffff;height: auto; line-height: normal; border: 1px solid #f5f5f5"
             @click="checkShow"
             v-if="clickSign && show">
          <img class="sign" style="height: 30vh; width: auto; margin-left: 10vw; transform:rotate(-90deg);"
               :src="policyholderSign">
        </div>
      </div>
      <toast v-model="showPositionValue" type="text" :time="800" is-show-mask position="middle">{{toastText}}</toast>
      <div style="height: 60px;">
        <div class="i-footer">
          <button @click="next">
            <div>下一步</div>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {Toast} from 'vux'
  import Signature from './Signature.vue'
  import {uploadImage} from "../service/getData";
  import storage from "../store/storage";

  export default {
    name: 'Autograph',
    data() {
      return {
        state: false,
        clickSign: false,
        clickSign1: false,
        imgUrl: storage.fetch("holder").policyholderAvatar,
        option: {
          penColor: "rgb(0, 0, 0)",
          backgroundColor: "#ffffff",
        },
        toastText: '',
        showPositionValue: false,
        submissionSign: storage.fetch("holder").submissionSign,
        policyholderSign: storage.fetch("holder").policyholderSign,
        show1: false,
        show: false,
        submissionColor: '#ffffff',
        policyholderColor: '#ffffff'
      }
    },
    components: {Signature, Toast},
    methods: {
      //添加图片
      addPic: function () {
        document.getElementById("image").click();
        return false;
      },
      onFileChange: function (e) {
        let files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.createImage(files, e);
      },
      async createImage(file, e) {
        let vm = this;
        vm.$vux.loading.show({
          text: 'Loading'
        });
        let timeout = setTimeout(function () {
          vm.$vux.loading.hide();
          vm.showPositionValue = true;
          vm.toastText = "上传失败，请稍后重试";
        }, 15000);
        await lrz(file[0], {width: 480}).then(function (rst) {
          rst.base64 = rst.base64.split(',')[1];
          uploadImage(rst.base64).then(function (result) {
            vm.imgUrl = result.data;
            let holder = storage.fetch("holder");
            holder.policyholderAvatar = result.data;
            storage.save("holder", holder);
            vm.$vux.loading.hide();
            clearTimeout(timeout);
          });
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
        let holder = storage.fetch("holder");
        holder.policyholderAvatar = '';
        storage.save("holder", holder);
      },

      check() {
        this.state = !this.state;
      },
      checkSign() {
        this.clickSign = !this.clickSign;
      },
      checkShow() {
        this.show = !this.show;
      },
      checkSign1() {
        this.clickSign1 = !this.clickSign1;
      },
      checkShow1() {
        this.show1 = !this.show1;
      },
      doneButton() {
        var _this = this;
        var jpeg = _this.$refs.signature.save('image/jpeg').split(',')[1];
        uploadImage(jpeg).then(function (result) {
          let holder = storage.fetch("holder");
          _this.policyholderSign = result.data;
          holder.policyholderSign = result.data;
          storage.save("holder", holder);
        });
        this.show = !this.show;
      },
      doneButton1() {
        var _this = this;
        var jpeg1 = _this.$refs.signature1.save('image/jpeg').split(',')[1];
        uploadImage(jpeg1).then(function (result) {
          let holder = storage.fetch("holder");
          _this.submissionSign = result.data;
          holder.submissionSign = result.data;
          storage.save("holder", holder);
        });
        this.show1 = !this.show1;
      },
      save() {
        var _this = this;
        var jpeg = _this.$refs.signature.save('image/jpeg').split(',')[1];
        var jpeg1 = _this.$refs.signature1.save('image/jpeg').split(',')[1];
        uploadImage(jpeg).then(function (result) {
          let holder = storage.fetch("holder");
          holder.policyholderSign = result.data;
          storage.save("holder", holder);
        });
        uploadImage(jpeg1).then(function (result) {
          let holder = storage.fetch("holder");
          holder.submissionSign = result.data;
          storage.save("holder", holder);
        });
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
        if (this.state !== true) {
          this.showPositionValue = true;
          this.toastText = "请勾选同意条款";
          return false;
        }
        if (this.imgUrl === '' || this.imgUrl === undefined) {
          this.showPositionValue = true;
          this.toastText = "请拍摄投保人正面头像";
          return false;
        }
        let holder = storage.fetch("holder");
        if (!holder.policyholderSign) {
          this.showPositionValue = true;
          this.toastText = "请签署投保单签名";
          return false;
        }
        if (!holder.submissionSign) {
          this.showPositionValue = true;
          this.toastText = "请签署投保提示书签名";
          return false;
        }

        // this.save();
        this.$router.push("upload-data")
      },
      imsurancePolicy() {
        this.$router.push("insurancePolicy")
      },
      insureNote() {
        this.$router.push("insureNote")
      },
      loadclause() {
        let packetId = storage.fetch("packetId");
        if (packetId === 1) {
          this.$router.push("lifeLongClause");
        } else {
          this.$router.push("clause");
        }

      }
    },
    watch: {
      submissionSign: {
        handler(newVal, oldVal) {
          if (newVal != '') {
            this.submissionColor = '#ffffff'
          } else {
            this.submissionColor = '#f3f5f7'
          }
        },
        immediate: true,
        deep: true
      },
      policyholderSign: {
        handler(newVal, oldVal) {
          if (newVal != '') {
            this.policyholderColor = '#ffffff'
          } else {
            this.policyholderColor = '#f3f5f7'
          }
        },
        immediate: true,
        deep: true
      }
    },
    created: function () {

    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .title {
    margin-bottom: 10px;
    background-color: #ffffff;
    padding: 12px 15px;
    font-size: 17px;
    font-weight: bold;
    color: #f5ca1d;
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
    color: #888;
  }

  .tip span {
    color: #f5ca1d;
  }

  .headPhoto {
    border-bottom: 2px solid #f3f3f3;
    padding: 0 4vw;
    background: #ffffff;
  }

  .headPhoto img {
    width: 15vw;
    padding-top: 5vh;
    margin-left: 42%;
    margin-bottom: 10px;
  }

  .headPhoto p {
    padding: 6vh 0 2vh 0;
    text-align: center;
  }

  .headPhoto-img {
    width: 50vw;
    margin-left: 25vw;
    padding: 10px 0;
  }

  .limitImg {
    max-height: 144px;
    width: 40vw;
    margin-left: 30%;
    overflow: hidden;
  }

  .limitImg img {
    width: 100%;
    height: 100%
  }

  .carmerBorder {
    position: absolute;
    border: 1px solid #dddddd;
    width: 55%;
    left: 0;
    right: 0;
    margin: auto;
    height: 120px;
    border-radius: 16px;
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
    background: #f3f5f7;
    width: 92%;
    height: 30vh;
    border-radius: 10px;
    margin: 0 15px;
    text-align: center;
    /*line-height: 30vh;*/
    color: #bfbfbf;
    font-size: 14px;
  }

  .sign button {
    float: right;
    color: #999;
    border-radius: 5px;
    border: 1px solid #dcdcdc;
    background: #EDEDED;
    margin-top: -2px
  }

  .popContainer {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.3);
    z-index: 999;
    padding-top: 20px;
  }

  .doneButton {
    left: 0;
    right: 0;
    margin: auto;
    background: #fff;
    border: 2px solid #f5ca1d;
    border-radius: 50px;
    width: 50px;
    height: 50px;
    margin-left: 15px;
    color: #f5ca1d;
    transform: rotate(90deg);
  }

  /*签名动态样式*/
  .animateGraph {
    transition: transform .5s cubic-bezier(.2, 1, .3, 1), -webkit-transform .5s cubic-bezier(.2, 1, .3, 1);
    transform: perspective(1000px) translate3d(0, 60vh, 0) rotate3d(1, 0, 0, 30deg);
  }
</style>
