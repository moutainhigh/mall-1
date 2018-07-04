<template>
  <div>
    <div class="title">告知事项<span>全选否</span></div>
    <div class="content">
      <p>1.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>2.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>3.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>4.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>5.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>6.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3">7.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <span>被保人</span>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div style="height: 50px;">
      <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;">
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
    name: "infoMatters",
    data() {
      return {
        state: false
      }
    },
    methods: {
      changeState() {
        this.state = !this.state;
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
    -webkit-margin-before: 1em !important;
    -webkit-margin-after: 1em !important;
  }

  .content span {
    font-size: 13px;
    margin-left: 10vw;
    margin-top: 15px;
  }

  img {
    width: 15vw;
  }
</style>
