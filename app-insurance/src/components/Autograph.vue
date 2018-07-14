<template>
  <div style="position: relative">

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
      <span style="position: absolute; color: #f5ca1d; right: 0; margin: 10px 30px" v-if="clickSign1"
            @click="clear1">清除</span>
      <div class="sign" v-if="!clickSign1" @click="checkSign1">点击签名
      </div>
      <div v-if="clickSign1">
        <Signature ref="signature1" :sigOption="option" :w="'92vw'" :h="'20vh'"></Signature>
      </div>
    </div>

    <div class="title" style="color: #000; font-weight: normal; margin-bottom: 0">投保单签名</div>
    <div class="headPhoto" v-if="imgUrl === '' || imgUrl === undefined || imgUrl === null" @click.stop="addPic">
      <div class="carmerBorder"></div>
      <img src="../assets/img/headPhotograph.png"/>
      <p style="font-size: 13px">拍摄或上传投保人正面头像</p>
    </div>
    <button class="clearButton" v-if="imgUrl !== undefined && imgUrl !== ''" @click='delImage'>清除</button>
    <input id="image" type="file" accept="image/*" @change="onFileChange"
           style="display: none;">
    <div v-if="imgUrl !== undefined && imgUrl !== ''">
      <img class="headPhoto-img" :src="imgUrl">
    </div>

    <div class="canvas">
      <span style="position: absolute; margin: 10px 30px; color: #666; font-size: 14px">投保人签名：</span>
      <span style="position: absolute; color: #f5ca1d; right: 0; margin: 10px 30px" v-if="clickSign"
            @click="clear">清除</span>
      <div class="sign" v-if="!clickSign" @click="checkSign">点击签名
      </div>
      <div v-if="clickSign">
        <Signature ref="signature" :sigOption="option" :w="'92vw'" :h="'20vh'"></Signature>
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
          backgroundColor: "#f3f5f7",
        },
        toastText: '',
        showPositionValue: false,
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
        var files = e.target.files || e.dataTransfer.files;
        if (!files.length) return;
        this.createImage(files, e);
      },
      async createImage(file, e) {
        let vm = this;
        vm.$vux.loading.show({
          text: 'Loading'
        });
        await lrz(file[0], {width: 480}).then(function (rst) {
          rst.base64 = rst.base64.split(',')[1];
          console.log(rst.base64);
          uploadImage(rst.base64).then(function (result) {
            vm.imgUrl = result.data;
            let holder = storage.fetch("holder");
            holder.policyholderAvatar = result.data;
            storage.save("holder", holder);
          });
          vm.$vux.loading.hide();
          return rst;
        }).always(function () {
          // 清空文件上传控件的值
          e.target.value = null;
          vm.$vux.loading.hide();
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
      checkSign1() {
        this.clickSign1 = !this.clickSign1;
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
        if (this.clickSign === false) {
          this.showPositionValue = true;
          this.toastText = "请签署投保单签名";
          return false;
        }
        if (this.clickSign1 === false) {
          this.showPositionValue = true;
          this.toastText = "请签署投保提示书签名";
          return false;
        }
        this.save();
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
    width: 23vw;
    padding-top: 3vh;
    margin-left: 38%;
    margin-bottom: 10px;
  }

  .headPhoto p {
    padding: 4vh 0 2vh 0;
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
    width: 50%;
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
    height: 20vh;
    border-radius: 10px;
    margin: 0 15px;
    text-align: center;
    line-height: 20vh;
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
</style>
