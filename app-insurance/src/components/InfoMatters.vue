<template>
  <div>
    <div class="title">告知事项<span>全选否</span></div>
    <div class="content" style="height: auto">
      <p>1.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>2.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>3.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>4.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>5.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>6.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">7.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0; margin-bottom: 0">a.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">e.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">f.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">g.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
    </div>

    <div class="content">
      <p>8.a.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p>9.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <p class="recognizee">被保人</p>
      <div style="display: inline" @click="changeState">
        <img v-if="!state" src="../assets/img/switch-off.png">
        <img v-if="state" src="../assets/img/switch-on.png">
      </div>
    </div>

    <div class="content">
      <p style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">10.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
      <div style="border-bottom: 1px solid #f3f3f3; padding-bottom: 10px">
        <p style="padding: 15px 0">a.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">b.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">c.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">d.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
      <div style="border-bottom: 1px solid #f3f3f3;padding: 0 0 15px 0;">
        <p style="padding: 15px 0">e.您是否已经拥有或正在申请其他保险公司的保险合同？</p>
        <p class="recognizee">被保人</p>
        <div style="display: inline" @click="changeState">
          <img v-if="!state" src="../assets/img/switch-off.png">
          <img v-if="state" src="../assets/img/switch-on.png">
        </div>
      </div>
    </div>

    <div style="height: 50px;">
      <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;" @click="comeBack">
        <div>上一步</div>
      </button>
      <button class="i-footer" style="width: 50%;right: 0">
        <router-link to="autograph">
          <div>下一步</div>
        </router-link>
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
      comeBack() {
        this.$router.back();
      },
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
    color: #c01212;
  }

  .title span {
    text-decoration: underline;
    color: #000;
    font-size: 13px;
    float: right;
  }

  .content {
    background: #ffffff;
    padding: 15px;
    margin-bottom: 10px;
  }

  .content p {
    margin: 0 0 15px 0;
    font-size: 13px;
    margin: 0;
  }

  .recognizee {
    padding-top: 15px;
    float: left;
    font-size: 13px;
    padding-left: 15px;
  }

  img {
    width: 15vw;
    padding-top: 10px;
    padding-left: 20px;
  }
</style>
