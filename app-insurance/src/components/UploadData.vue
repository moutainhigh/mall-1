<template>
  <div>
    <div class="title">投保资料上传</div>
    <p class="uploadTitle">投保人银行卡正面上传（必传）</p>
    <div class="card">
      <img src="../assets/img/upload.png">
    </div>
    <p class="uploadTitle">投保人身份证正面上传（必传）</p>
    <div class="card">
      <img src="../assets/img/takeIdCard.png">
    </div>
    <p class="uploadTitle">投保人身份证背面上传（必传）</p>
    <div class="card">
      <img src="../assets/img/takeEmblem.png">
    </div>
    <p class="uploadTitle">其它资料（资料）</p>
    <div class="card">
      <img src="../assets/img/upload.png">
    </div>
    <p class="uploadTitle">其它资料（资料）</p>
    <div class="card">
      <img src="../assets/img/upload.png">
    </div>
    <p class="uploadTitle">其它资料（资料）</p>
    <div class="card">
      <img src="../assets/img/upload.png">
    </div>
    <div style="height: 48px;">
      <button class="i-footer">
        <router-link to="policy">
          <div>下一步</div>
        </router-link>
      </button>
    </div>
  </div>
</template>

<script>
  export default {
    name: "UploadData"
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
