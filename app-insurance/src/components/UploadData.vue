<template>
  <div>
    <div class="title">投保资料上传</div>
    <p class="uploadTitle">投保人银行卡正面上传（必传）
      <button v-if="imgUrls.image1 !== '' && imgUrls.image1 !== undefined" @click='delImage(1)'>清除</button>
    </p>
    <div class="card">
      <div v-if="imgUrls.image1 === '' || imgUrls.image1 === undefined" @click.stop="addPic1">
        <div style="position: absolute; width: 100%; height: 120px"></div>
        <img src="../assets/img/upload.png">
      </div>
      <input id="image1" type="file" accept="image/*" capture="camera" @change="onFileChange"
             style="display: none;">
      <div style="height: 140px; overflow: hidden; position: relative" v-if="imgUrls.image1 !== '' && imgUrls.image1 !== undefined">
        <!--<img style="width: 30px; position: absolute; right: 20vw; top: -1rem; padding: 0; z-index: 999" src="../assets/img/close.png">-->
        <img style="padding: 0;" :src="imgUrls.image1" v-preview="imgUrls.image1" preview-nav-enable="false">
      </div>
    </div>

    <p class="uploadTitle">投保人身份证件正面上传（必传）
      <button v-if="imgUrls.image2 !== '' && imgUrls.image2 !== undefined" @click='delImage(2)'>清除</button>
    </p>
    <div class="card">
      <div v-if="imgUrls.image2 === '' || imgUrls.image2 === undefined" @click.stop="addPic2">
        <div style="position: absolute; width: 100%; height: 120px"></div>
        <img src="../assets/img/takeIdCard.png">
      </div>
      <input id="image2" type="file" accept="image/*" capture="camera" @change="onFileChange"
             style="display: none;">
      <div style="height: 140px; overflow: hidden" v-if="imgUrls.image2 !== '' && imgUrls.image2 !== undefined">
        <img style="padding: 0;" :src="imgUrls.image2" v-preview="imgUrls.image2" preview-nav-enable="false">
      </div>
    </div>

    <p class="uploadTitle">投保人身份证件背面上传（必传）
      <button v-if="imgUrls.image3 !== '' && imgUrls.image3 !== undefined" @click='delImage(3)'>清除</button>
    </p>
    <div class="card">
      <div v-if="imgUrls.image3 === '' || imgUrls.image3 === undefined" @click.stop="addPic3">
        <div style="position: absolute; width: 100%; height: 120px"></div>
        <img src="../assets/img/takeEmblem.png">
      </div>
      <input id="image3" type="file" accept="image/*" capture="camera" @change="onFileChange"
             style="display: none;">
      <div style="height: 140px; overflow: hidden" v-if="imgUrls.image3 !== '' && imgUrls.image3 !== undefined">
        <img style="padding: 0;" :src="imgUrls.image3" v-preview="imgUrls.image3" preview-nav-enable="false">
      </div>
    </div>

    <p class="uploadTitle">其它资料（资料）
      <button v-if="imgUrls.image4 !== '' && imgUrls.image4 !== undefined" @click='delImage(4)'>清除</button>
    </p>
    <div class="card">
      <div v-if="imgUrls.image4 === '' || imgUrls.image4 === undefined" @click.stop="addPic4">
        <div style="position: absolute; width: 100%; height: 120px"></div>
        <img src="../assets/img/upload.png">
      </div>
      <input id="image4" type="file" accept="image/*" capture="camera" @change="onFileChange"
             style="display: none;">
      <div style="height: 140px; overflow: hidden" v-if="imgUrls.image4 !== '' && imgUrls.image4 !== undefined">
        <img style="padding: 0;" :src="imgUrls.image4" v-preview="imgUrls.image4" preview-nav-enable="false">
      </div>
    </div>

    <p class="uploadTitle">其它资料（资料）
      <button v-if="imgUrls.image5 !== '' && imgUrls.image5 !== undefined" @click='delImage(5)'>清除</button>
    </p>
    <div class="card">
      <div v-if="imgUrls.image5 === '' || imgUrls.image5 === undefined" @click.stop="addPic5">
        <div style="position: absolute; width: 100%; height: 120px"></div>
        <img src="../assets/img/upload.png">
      </div>
      <input id="image5" type="file" accept="image/*" capture="camera" @change="onFileChange"
             style="display: none;">
      <div style="height: 140px; overflow: hidden" v-if="imgUrls.image5 !== '' && imgUrls.image5 !== undefined">
        <img style="padding: 0;" :src="imgUrls.image5" v-preview="imgUrls.image5" preview-nav-enable="false">
      </div>
    </div>

    <p class="uploadTitle">其它资料（资料）
      <button v-if="imgUrls.image6 !== '' && imgUrls.image6 !== undefined" @click='delImage(6)'>清除</button>
    </p>
    <div class="card">
      <div v-if="imgUrls.image6 === '' || imgUrls.image6 === undefined" @click.stop="addPic6">
        <div style="position: absolute; width: 100%; height: 120px"></div>
        <img src="../assets/img/upload.png">
      </div>
      <input id="image6" type="file" accept="image/*" capture="camera" @change="onFileChange"
             style="display: none;">
      <div style="height: 140px; overflow: hidden" v-if="imgUrls.image6 !== '' && imgUrls.image6 !== undefined">
        <img style="padding: 0;" :src="imgUrls.image6" v-preview="imgUrls.image6" preview-nav-enable="false">
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
  import '../lib/lrz.all.bundle'
  import storage from "../store/storage";
  import {uploadImage} from "../service/getData";


  //缓存
  let holder = storage.fetch("holder");
  let order = storage.fetch("order");

  export default {
    components: {
      Toast
    },
    name: "UploadData",
    data() {
      return {
        imgUrls: {
          image1: storage.fetch("order").insuranceOrderPolicyholderBank.bankCardImg,
          image2: storage.fetch("holder").cardPositiveImg,
          image3: storage.fetch("holder").cardNegativeImg,
          image4: storage.fetch("holder").otherImg1,
          image5: storage.fetch("holder").otherImg2,
          image6: storage.fetch("holder").otherImg3,
        },
        showPositionValue: false,
        toastText: '',
      }
    },
    methods: {
      //添加图片
      addPic1: function () {
        document.getElementById("image1").click();
        return false;
      },
      addPic2: function () {
        document.getElementById("image2").click();
        return false;
      },
      addPic3: function () {
        document.getElementById("image3").click();
        return false;
      },
      addPic4: function () {
        document.getElementById("image4").click();
        return false;
      },
      addPic5: function () {
        document.getElementById("image5").click();
        return false;
      },
      addPic6: function () {
        document.getElementById("image6").click();
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
        let timeout = setTimeout(function () {
          vm.$vux.loading.hide();
          vm.showPositionValue = true;
          vm.toastText = "上传失败，请稍后重试";
        }, 15000);
        await lrz(file[0], {width: 480}).then(function (rst) {
          rst.base64 = rst.base64.split(',')[1];
          uploadImage(rst.base64).then(function (result) {
            let holder = storage.fetch("holder");
            switch (e.target.id) {
              case 'image1' :
                vm.imgUrls.image1 = result.data;
                let order = storage.fetch("order");
                order.insuranceOrderPolicyholderBank.bankCardImg = result.data;
                storage.save("order", order);
                break;
              case 'image2' :
                vm.imgUrls.image2 = result.data;
                holder.cardPositiveImg = result.data;
                storage.save("holder", holder);
                break;
              case 'image3' :
                vm.imgUrls.image3 = result.data;
                holder.cardNegativeImg = result.data;
                storage.save("holder", holder);
                break;
              case 'image4' :
                vm.imgUrls.image4 = result.data;
                holder.otherImg1 = result.data;
                storage.save("holder", holder);
                break;
              case 'image5' :
                vm.imgUrls.image5 = result.data;
                holder.otherImg2 = result.data;
                storage.save("holder", holder);
                break;
              case 'image6' :
                vm.imgUrls.image6 = result.data;
                holder.otherImg3 = result.data;
                storage.save("holder", holder);
                break;
            }
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
      delImage: function (index) {
        let vm = this;
        let holder = storage.fetch("holder");
        let order = storage.fetch("order");
        switch (index) {
          case 1:
            vm.imgUrls.image1 = '';
            order.insuranceOrderPolicyholderBank.bankCardImg = '';
            break;
          case 2:
            vm.imgUrls.image2 = '';
            holder.cardPositiveImg = '';
            break;
          case 3:
            vm.imgUrls.image3 = '';
            holder.cardNegativeImg = '';
            break;
          case 4:
            vm.imgUrls.image4 = '';
            holder.otherImg1 = '';
            break;
          case 5:
            vm.imgUrls.image5 = '';
            holder.otherImg2 = '';
            break;
          case 6:
            vm.imgUrls.image6 = '';
            holder.otherImg3 = '';
            break;
        }
        storage.save("holder", holder);
        storage.save("order", order);
      },
      next() {
        if (!this.imgUrls.image1) {
          this.showPositionValue = true;
          this.toastText = "请上传银行卡正面图片";
          return ;
        }
        if (!this.imgUrls.image2) {
          this.showPositionValue = true;
          this.toastText = "请上传身份证件正面图片";
          return ;
        }
        if (!this.imgUrls.image3) {
          this.showPositionValue = true;
          this.toastText = "请上传身份证件背面图片";
          return ;
        }
        let holder = storage.fetch("holder");
        if (holder.policyholderCity !== '440300') {
          this.$router.push("differentPlaces");
        }else {
          let order = storage.fetch("order");
          order.insuranceOrderOffsite = null;
          storage.save("order",order);
          this.$router.push("payment")
        }
      },
    },
    created: function () {
    }
  }
</script>

<style scoped>
  .title {
    margin-bottom: 10px;
    background-color: #ffffff;
    padding: 15px;
    font-size: 13px;
  }

  .uploadTitle {

    background: #ffffff;
    margin: 0;
    color: #c01212;
    font-size: 13px;
    text-align: center;
    padding: 10px;
    border-bottom: 1px solid #f3f3f3;
  }

  .uploadTitle button {
    float: right;
    color: #999;
    border-radius: 5px;
    border: 1px solid #dcdcdc;
    background: #EDEDED;
    margin-top: -2px
  }

  .card {
    width: 100%;
    background: #ffffff;
    margin-bottom: 10px;
  }

  .card img {
    width: 50vw;
    margin-left: 25vw;
    padding: 20px 0;
  }

  .limitImg {
    max-height: 144px;
    width: 40vw;
    margin-left: 30%;
    overflow: hidden;
  }

  .limitImg img {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
  }
</style>
