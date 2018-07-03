<template>
  <div>
    <div class="title">告知事项<span>全选否</span></div>
    <div class="content">
      <p>1.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <span>被保人</span>
      <img v-if="!state" @click="check" src="../assets/img/switch-off.png">
      <img v-if="!state" @click="check" src="../assets/img/switch-on.png">
    </div>

    <div class="content">
      <p>2.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
    </div>

    <div class="content">
      <p>3.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
    </div>

    <div class="content">
      <p>4.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
    </div>

    <div class="content">
      <p>5.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
    </div>

    <div class="content">
      <p>6.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
    </div>

    <div class="content">
      <p>7.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
    </div>

    <div style="height: 50px;">
      <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;" @click="comeBack">
        <div>上一步</div>
      </button>
      <button class="i-footer" style="width: 50%;right: 0">
        <div>下一步</div>
      </button>
    </div>
  </div>
</template>

<script>
    export default {
        name: "InfoMatters"
    }
</script>

<style scoped>
  .title {
    margin-bottom: 10px;
    background-color: #ffffff;
    padding: 15px;
    font-size: 13px;
    color: red;
  }

  .title span {
    text-decoration: underline;
    color: #000;
    font-size: 13px;
    float: right;
  }

  .content {
    width: 100%;
    background: #ffffff;
    padding: 15px;
    margin-bottom: 10px;
  }

  .content p {
    margin: 0 0 15px 0;
    font-size: 13px;
    -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
  }

  .content span {
    font-size: 13px;
    margin-left: 10vw;
    margin-top: 15px;
  }
</style>
