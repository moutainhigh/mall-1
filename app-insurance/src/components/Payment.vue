<template>
  <div>
    <div class="title">
      填写银行账号信息
    </div>
    <div style="position:absolute;top: 0; height: 100%; width: 100%;z-index: 0">

    </div>
    <group label-width="7em" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="账户姓名" placeholder="请输入账户姓名" v-model="bank.count"></x-input>
      <x-input class="price" title="交易金额(元)" :placeholder="price + '.00'" readonly></x-input>
      <x-input title="手机号码" placeholder="请输入手机号码" v-model="bank.bankMobile"></x-input>
      <popup-picker title="开户行" placeholder="请选择开户行" :data="list" value-text-align="left" v-model="bank.bankName"></popup-picker>
      <x-address title="开户行位置" placeholder="请选择开户行位置" :list="cities" v-model="address" value-text-align="left"></x-address>
      <popup-picker title="账户类型" placeholder="请选择账户类型" v-model="bank.accountType" :data="types" value-text-align="left"></popup-picker>
      <x-input title="账户号码" placeholder="请输入账户号码" v-model="bank.accountNo"></x-input>
      <!--<div class="i-input">-->
      <!--<div class="i-input-item">实时转账</div>-->
      <!--<div style="float: right;margin: 8px;">-->
      <!--<div style="position: absolute;margin-left: 34px;font-size: 10px;margin-top: 8px;">关闭</div>-->
      <!--<img src="../assets/img/switch-off.png" style="width: 64px;">-->
      <!--</div>-->
      <!--</div>-->
      <!--<div>-->
      <!--<div class="input-tip">-->
      <!--温馨提示：实时转账即时返回收费结果，非实时转账由保险公司统一收费-->
      <!--</div>-->
      <!--</div>-->
      <div class="input-ver">
        <x-input title="验证码" placeholder="请输入验证码" :max="6" style="width: 65%;padding-left: 0;" v-model="code"></x-input>
        <div class="input-vile" @click.prevent="getVerifyCode">{{computedTime === 0? "获取验证码" : computedTime +"s"}}</div>
      </div>
      <toast v-model="showPositionValue" type="text" :time="800" is-show-mask position="middle" :title="toastText"></toast>
    </group>
    <div style="height: 48px;">
      <button class="i-footer" @click="submit">
        <div>确认提交</div>
      </button>
    </div>
  </div>
</template>

<script>
  import {ChinaAddressData, Datetime, Group, PopupPicker, XInput, Toast} from 'vux'
  import storage from "../store/storage";
  import XAddress from "vux/src/components/x-address/index";
  import {getVaildData,submitOrder} from '../service/getData'

  export default {
    name: "Payment",
    components: {
      XAddress,
      Group,
      XInput,
      PopupPicker,
      Datetime,
      Toast
    },
    data() {
      return {
        price:storage.fetch('order').insuranceProductPrice.price,
        list: [["兴业银行","中国工商银行","招商银行","上海浦东发展银行","中国邮政储蓄银行有限责任公司","中信银行","中国民生银行","中国银行","广东发展银行","平安银行","中国农业银行","交通银行","中国建设银行"]],
        cities: ChinaAddressData,
        types: [['银行卡', '存折']],
        insured: storage.fetch("insured"),
        bank: storage.fetch('order').insuranceOrderPolicyholderBank,
        address: [],
        toastText: '',
        showPositionValue: false,
        validate_token: '',
        computedTime: 0,
        code:''
      }
    },
    methods: {
      async submit() {
        // this.$router.push('policy')
        let order =await submitOrder(this.code);
      },
      async getVerifyCode() {
        if (this.bank.bankMobile) {
          if (this.computedTime === 0){
            this.computedTime = 60;
            //倒计时
            let timer = setInterval(() => {
              this.computedTime--;
              if (this.computedTime === 0) {
                clearInterval(timer)
              }
            }, 1000);
            //获取验证信息
            let getCode = await getVaildData(this.bank.bankMobile);
            this.toastText = "发送成功";
            this.showPositionValue = true;
          }
        }
      },
    },
    watch: {
      bank: {
        handler(newVal, oldVal) {
          let order = storage.fetch('order');
          order.insuranceOrderPolicyholderBank = newVal;
          storage.save('order', order);
        },
        immediate: true,
        deep: true
      },
      address: {
        handler(newVal, oldVal) {
          if (newVal.length === 3) {
            this.bank.bankProvince = newVal[0];
            this.bank.bankCity = newVal[1];
          }
        },
        immediate: true,
        deep: true
      }
    },
    created: function () {
      this.bank.amount = storage.fetch('order').insuranceProductPrice.price;
    }
  }
</script>

<style scoped>
  .i-input {
    padding-top: 0;
    font-size: 15px;
    border-bottom: #d9d9d9 solid 1px;
    border-top: #d9d9d9 solid 1px;
    margin-left: 15px;
    margin-bottom: -1px;
  }

  .i-input-item {
    font-size: 14px;
    color: #2c3e50;
  }

  .input-tip {
    border-top: 1px solid rgb(217, 217, 217);
    border-bottom: 1px solid rgb(217, 217, 217);
    margin-left: 15px;
    font-size: 10px;
    padding: 10px 10px 10px 0px;
    color: rgb(25, 174, 0);
  }

  .input-vile {
    width: 60px;
    text-align: center;
    float: right;
    font-size: 10px;
    margin-top: -34px;
    margin-right: 10px;
    border: #c01212 solid 1px;
    padding: 4px 8px;
    border-radius: 6px;
    color: #c01212;
  }

  .input-ver {
    margin-left: 16px;
    border-top: #d9d9d9 solid 1px;
  }

  .input-ver .weui-input {
    width: 60% !important;
  }

  .i-footer {
    background-color: #c01212;
    color: #ffffff;
  }

  .title {
    color: #c01212;
    background-color: #ffffff;
    margin-left: 0;
    border-bottom: 1px solid rgb(217, 217, 217);
    font-size: 13px;
    padding: 10px 10px 10px 16px;
  }
</style>
