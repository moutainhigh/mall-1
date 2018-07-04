<template>
  <div>
    <div class="title">投保资料上传</div>
    <p class="uploadTitle">投保人银行卡正面上传（必传）
      <button v-if="!isPhoto1" @click='delImage(1)'>清除</button>
    </p>
    <div class="card">
      <div @click.stop="addPic1">
        <img v-if="isPhoto1" src="../assets/img/upload.png">
      </div>
      <input id="image1" type="file" accept="image/jpeg,image/jpg,image/png" capture="camera" @change="onFileChange"
             style="display: none;">
      <div v-if="!isPhoto1">
        <img
          style="padding: 0; transform:rotate(7deg);-ms-transform:rotate(-90deg);-moz-transform:rotate(-90deg);-o-transform:rotate(-90deg);  -webkit-transform:rotate(-90deg);"
          :src="imgUrls.image1">
      </div>
    </div>

    <p class="uploadTitle">投保人身份证正面上传（必传）
      <button v-if="!isPhoto2" @click='delImage(2)'>清除</button>
    </p>
    <div class="card">
      <div @click.stop="addPic2">
        <img v-if="isPhoto2" src="../assets/img/takeIdCard.png">
      </div>
      <input id="image2" type="file" accept="image/jpeg,image/jpg,image/png" capture="camera" @change="onFileChange"
             style="display: none;">
      <div v-if="!isPhoto2">
        <img
          style="padding: 0; transform:rotate(7deg);-ms-transform:rotate(-90deg);-moz-transform:rotate(-90deg);-o-transform:rotate(-90deg);  -webkit-transform:rotate(-90deg);"
          :src="imgUrls.image2">
      </div>
    </div>

    <p class="uploadTitle">投保人身份证背面上传（必传）
      <button v-if="!isPhoto3" @click='delImage(3)'>清除</button>
    </p>
    <div class="card">
      <div @click.stop="addPic3">
        <img v-if="isPhoto3" src="../assets/img/takeEmblem.png">
      </div>
      <input id="image3" type="file" accept="image/jpeg,image/jpg,image/png" capture="camera" @change="onFileChange"
             style="display: none;">
      <div v-if="!isPhoto3">
        <img
          style="padding: 0; transform:rotate(7deg);-ms-transform:rotate(-90deg);-moz-transform:rotate(-90deg);-o-transform:rotate(-90deg);  -webkit-transform:rotate(-90deg);"
          :src="imgUrls.image3">
      </div>
    </div>

    <p class="uploadTitle">其它资料（资料）
      <button v-if="!isPhoto4" @click='delImage(4)'>清除</button>
    </p>
    <div class="card">
      <div @click.stop="addPic4">
        <img v-if="isPhoto4" src="../assets/img/upload.png">
      </div>
      <input id="image4" type="file" accept="image/jpeg,image/jpg,image/png" capture="camera" @change="onFileChange"
             style="display: none;">
      <div v-if="!isPhoto4">
        <img
          style="padding: 0;"
          :src="imgUrls.image4">
      </div>
    </div>

    <p class="uploadTitle">其它资料（资料）
      <button v-if="!isPhoto5" @click='delImage(5)'>清除</button>
    </p>
    <div class="card">
      <div @click.stop="addPic5">
        <img v-if="isPhoto5" src="../assets/img/upload.png">
      </div>
      <input id="image5" type="file" accept="image/jpeg,image/jpg,image/png" capture="camera" @change="onFileChange"
             style="display: none;">
      <div v-if="!isPhoto5">
        <img
          style="padding: 0;"
          :src="imgUrls.image5">
      </div>
    </div>

    <p class="uploadTitle">其它资料（资料）
      <button v-if="!isPhoto6" @click='delImage(6)'>清除</button>
    </p>
    <div class="card">
      <div @click.stop="addPic6">
        <img v-if="isPhoto6" src="../assets/img/upload.png">
      </div>
      <input id="image6" type="file" accept="image/jpeg,image/jpg,image/png" capture="camera" @change="onFileChange"
             style="display: none;">
      <div v-if="!isPhoto6">
        <img
          style="padding: 0;"
          :src="imgUrls.image6">
      </div>
    </div>

    <div style="height: 48px;">
      <button class="i-footer">
        <router-link to="payment">
          <div>下一步</div>
        </router-link>
      </button>
    </div>
  </div>
</template>

<script>
  import '../lib/lrz.all.bundle'


  export default {
    name: "UploadData",
    data() {
      return {
        imgUrls: {
          image1: '',
          image2: '',
          image3: '',
          image4: '',
          image5: '',
          image6: '',
        },
        urlArr: [],
        isPhoto1: true,
        isPhoto2: true,
        isPhoto3: true,
        isPhoto4: true,
        isPhoto5: true,
        isPhoto6: true,
        btnTitle: '',
        isModify: false,
        previewImg: '',
        isPreview: false
      }
    },
    watch: {
      'imgUrls': {
        handler(newurl, oldurl) {
          this.toggleAddPic();
        },
        deep: true
      }
    },
    methods: {
      toggleAddPic: function () {
        let vm = this;
        if (Object.keys(vm.imgUrls).length >= 1) {
          if (vm.imgUrls.image1 !== '') {
            vm.isPhoto1 = false;
          }
          if (vm.imgUrls.image2 !== '') {
            vm.isPhoto2 = false;
          }
          if (vm.imgUrls.image3 !== '') {
            vm.isPhoto3 = false;
          }
          if (vm.imgUrls.image4 !== '') {
            vm.isPhoto4 = false;
          }
          if (vm.imgUrls.image5 !== '') {
            vm.isPhoto5 = false;
          }
          if (vm.imgUrls.image6 !== '') {
            vm.isPhoto6 = false;
          }
        } else {
          // vm.isPhoto = true;
        }
        console.log(vm.isPhoto2)
      },
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
      createImage: function (file, e) {
        let vm = this;
        lrz(file[0], {width: 480}).then(function (rst) {
          switch (e.target.id) {
            case 'image1' :
              vm.imgUrls.image1 = rst.base64;
              break;
            case 'image2' :
              vm.imgUrls.image2 = rst.base64;
              break;
            case 'image3' :
              vm.imgUrls.image3 = rst.base64;
              break;
            case 'image4' :
              vm.imgUrls.image4 = rst.base64;
              break;
            case 'image5' :
              vm.imgUrls.image5 = rst.base64;
              break;
            case 'image6' :
              vm.imgUrls.image6 = rst.base64;
              break;
          }
          return rst;
        }).always(function () {
          // 清空文件上传控件的值
          e.target.value = null;
        });
      },
      //删除图片
      delImage: function (index) {
        let vm = this;
        switch (index) {
          case 1:
            vm.imgUrls.image1 = '';
            vm.isPhoto1 = true;
            break;
          case 2:
            vm.imgUrls.image2 = '';
            vm.isPhoto2 = true;
            break;
          case 3:
            vm.imgUrls.image3 = '';
            vm.isPhoto3 = true;
            break;
          case 4:
            vm.imgUrls.image4 = '';
            vm.isPhoto4 = true;
            break;
          case 5:
            vm.imgUrls.image5 = '';
            vm.isPhoto5 = true;
            break;
          case 6:
            vm.imgUrls.image6 = '';
            vm.isPhoto6 = true;
            break;
        }
        console.log(vm.imgUrls)
        // vm.imgUrls.splice(index, 1);
      },

      //上传投保资料
      saveImage: function () {
        let vm = this;
        let urlArr = [],
          imgUrls = this.imgUrls;

        for (let i = 0; i < imgUrls.length; i++) {
          if (imgUrls[i].indexOf('file') == -1) {
            urlArr.push(imgUrls[i].split(',')[1]);
          } else {
            urlArr.push(imgUrls[i]);
          }
        }

        //数据传输操作
      }
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
</style>
