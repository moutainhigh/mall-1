<template>
  <div>
    <div class="title">
      扣款
    </div>
    <group label-width="7em" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="投保单号" placeholder="13132156132165" readonly></x-input>
      <x-input title="账户姓名" :placeholder="insured.insuredName" readonly></x-input>
      <x-input title="交易金额(元)" placeholder="200000" readonly></x-input>
      <x-input title="手机号码" :placeholder="insured.insuredMobile" readonly></x-input>
      <popup-picker title="开户行" placeholder="请选择开户行" value-text-align="left"></popup-picker>
      <popup-picker title="开户行位置" placeholder="请选择开户行位置" :data="cities" value-text-align="left"></popup-picker>
      <popup-picker title="账户类型" placeholder="请选择账户类型" v-model="bank.accountType" :data="types" value-text-align="left"></popup-picker>
      <x-input title="账户号码" placeholder="请输入账户号码" v-model="bank.accountNo"></x-input>
      <div class="i-input">
        <div class="i-input-item">实时转账</div>
        <div style="float: right;margin: 8px;">
          <div style="position: absolute;margin-left: 34px;font-size: 10px;margin-top: 8px;">关闭</div>
          <img src="../assets/img/switch-off.png" style="width: 64px;">
        </div>
      </div>
      <div>
        <div class="input-tip">
          温馨提示：实时转账即时返回收费结果，非实时转账由保险公司统一收费
        </div>
      </div>
      <div class="input-ver">
        <x-input title="验证码" placeholder="请输入验证码" :max="6" style="width: 65%;"></x-input>
        <div class="input-vile" @click="getVaild">获取验证码</div>
      </div>
    </group>
    <div style="height: 48px;">
      <button class="i-footer" @click="submit">
        <div>确认扣款</div>
      </button>
    </div>
  </div>
</template>

<script>
  import {ChinaAddressData, Datetime, Group, PopupPicker, XInput} from 'vux'
  import storage from "../store/storage";

  export default {
    name: "Payment",
    components: {
      Group,
      XInput,
      PopupPicker,
      Datetime,
    },
    data() {
      return {
        list: [['居民身份证', '驾驶证', '护照']],
        cities: ChinaAddressData,
        types: [['银行卡', '存折']],
        insured: storage.fetch("insured"),
        bank: storage.fetch('bank')
      }
    },
    methods: {
      submit() {
        this.$router.push('policy')
      },
      getVaild() {

      }
    },
    watch: {
      bank: function (newVal, oldVal) {
        if (bank.length === 0) {
          storage.save('bank', Admin.bank);
          this.bank = storage.fetch('bank');
        }
      }
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
    float: right;
    font-size: 10px;
    margin-top: -34px;
    margin-right: 10px;
    border: #c01212 solid 1px;
    padding: 4px 8px;
    border-radius: 6px;
    color: #c01212;
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
