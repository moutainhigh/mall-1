<template>
  <div>
    <div class="i-card">
      <!--<div class="i-card-tip">-->
      <!--请填写被保人信息，用于保费计算-->
      <!--</div>-->
      <div class="i-input">
        <div class="i-input-item">出生日期：</div>
        <div class="i-input-select" @click="showPlugin">
          <div v-if="birthday === ''">请选择出生日期</div>
          <div v-if="birthday !== ''">{{birthday}}</div>
          <img src="../assets/img/drop-down.png"/>
        </div>
        <div class="error" v-if="!$v.birthday.required && $v.birthday.$dirty">请选择出生日期</div>
      </div>
      <div class="i-input">
        <div class="i-input-item">性别：</div>
        <div class="i-input-radio">
          <div class="radio-div" @click="changeGender(true)">
            <span>男</span>
            <img v-if="gender" src="../assets/img/case-on.png"/>
            <img v-if="!gender" src="../assets/img/case-off.png"/>
          </div>
          <div class="radio-div" @click="changeGender(false)">
            <span>女</span>
            <img v-if="!gender" src="../assets/img/case-on.png"/>
            <img v-if="gender" src="../assets/img/case-off.png"/>
          </div>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">被保人职业：</div>
        <!--<group>-->
        <!--<popup-picker title="受益顺序" placeholder="" :data="list" v-model="test"  value-text-align="left"></popup-picker>-->
        <!--</group>-->
        <div class="i-input-select" v-bind:class="{'errorInput': $v.career.$error}">
          <input class="input" placeholder="请输入职业" v-model="career"
                 @input="$v.career.$touch()"/>
        </div>
        <div class="error" v-if="!$v.career.required && $v.career.$dirty">请输入职业</div>
      </div>
    </div>
    <div class="i-card">
      <div class="i-card-tip">
        {{title}}
        <div class="i-list-right">
          <img src="../assets/img/risk.png" height="18">
          <span>主险</span>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">保险期间：</div>
        <div class="i-input-val">保障终身</div>
      </div>
      <div class="i-input">
        <div class="i-input-item">保险年限：</div>
        <div class="i-input-val">终身</div>
      </div>
      <div class="i-input">
        <div class="i-input-item">基本保额：</div>
        <div class="i-input-radio" v-if="proId == 1">
          <div class="radio-div" @click="priceId = 1">
            <span>2万</span>
            <img v-if="priceId ===1" src="../assets/img/case-on.png" height="100"/>
            <img v-if="priceId !==1" src="../assets/img/case-off.png" height="100"/>
          </div>
          <div class="radio-div" @click="priceId = 2">
            <span>5万</span>
            <img v-if="priceId ===2" src="../assets/img/case-on.png" height="100"/>
            <img v-if="priceId !==2" src="../assets/img/case-off.png" height="100"/>
          </div>
          <div class="radio-div" @click="priceId = 3">
            <span>10万</span>
            <img v-if="priceId ===3" src="../assets/img/case-on.png" height="100"/>
            <img v-if="priceId !==3" src="../assets/img/case-off.png" height="100"/>
          </div>
        </div>
        <div class="i-input-radio" v-if="proId == 2">
          <div style="display: inline-block;color: #c01212;">
            <div class="radio-div" @click="priceId = 4">
              <span>2万</span>
              <img v-if="priceId ===4" src="../assets/img/case-on.png" height="100"/>
              <img v-if="priceId !==4" src="../assets/img/case-off.png" height="100"/>
            </div>
            <div class="radio-div" @click="priceId = 5">
              <span>5万</span>
              <img v-if="priceId ===5" src="../assets/img/case-on.png" height="100"/>
              <img v-if="priceId !==5" src="../assets/img/case-off.png" height="100"/>
            </div>
            <div class="radio-div" @click="priceId = 6">
              <span>10万</span>
              <img v-if="priceId ===6" src="../assets/img/case-on.png" height="100"/>
              <img v-if="priceId !==6" src="../assets/img/case-off.png" height="100"/>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="i-card">
      <div class="i-card-tip">
        ※ 投保须知
      </div>
      <div class="i-message" @click="state = !state">
        <img v-if="!state" class="checkIcon" src="../assets/img/checkoff.png">
        <img v-if="state" class="checkIcon" src="../assets/img/checkon.png">
        <div>&emsp;欢迎使用富德生命投保，请您仔细阅读人身保险投保提示书、产品说明书及保险条款，如实填写各项投保信息并确保为本人签名。保险合同将以此为依据，否则可能影响所签合同的法律效力。</div>
      </div>
    </div>
    <div style="height: 48px;">
      <button class="i-footer" @click="submit">
        <div>完善投保信息</div>
      </button>
    </div>
  </div>
</template>

<script>
  import storage from "../store/storage"
  import {PopupPicker, Toast} from 'vux'
  import XInput from "vux/src/components/x-input/index";
  import {required} from 'vuelidate/lib/validators'

  export default {
    name: "holder-basic",
    components: {
      XInput,
      PopupPicker
    },
    data() {
      return {
        birthday: '',
        gender: true,
        priceId: 1,
        profession: [],
        list: [['居民身份证', '驾驶证', '护照']],
        title: '',
        proId: '',
        career: '',
        state: false,
        toastText: '',
        showPositionValue: false,
      }
    },
    validations: {
      birthday: {
        required
      },
      career: {
        required
      }
    },
    methods: {
      showPlugin() {
        let _this = this;
        this.$vux.datetime.show({
          cancelText: '取消',
          confirmText: '确定',
          format: 'YYYY-MM-DD',
          value: new Date().getVarDate,
          minYear: '1950',
          endDate: new Date(),
          onConfirm(val) {
            _this.birthday = val;
          },
          onShow() {
            console.log('plugin show')
          },
          onHide() {
            console.log('plugin hide')
          }
        })
      },
      changeGender: function (val) {
        this.gender = val;
        let insured = storage.fetch('insured');
        insured.insuredGender = val;
        storage.save('insured', insured);
      },
      submit() {
        this.$v.$touch();
        if (this.$v.$invalid) {
          this.showPositionValue = true;
          this.toastText = "请完善账号信息"
          return false;
        }
        if (this.state !== true) {
          alert('请勾选投保须知');
          return false;
        } else {
          this.$router.push('/holder-detail');
        }
      }
    },
    watch: {
      birthday: {
        handler(newVal, oldVal) {
          let insured = storage.fetch('insured');
          if (newVal.length !== 0) {
            insured.insuredBirthday = newVal;
          }
          storage.save('insured', insured);
        },
        immediate: true,
        deep: true
      },
      gender: function (newVal, oldVal) {
        let insured = storage.fetch('insured');
        insured.insuredGender = newVal;
        storage.save('insured', insured);
      },
      priceId: function (newVal, oldVal) {
        let order = storage.fetch('order');
        order.insuranceProductPrice.priceId = newVal;
        switch (newVal) {
          case 1 :
          case 4 :
            order.insuranceProductPrice.price = 20000.00;
            break;
          case 2:
          case 5 :
            order.insuranceProductPrice.price = 50000.00;
            break;
          case 3:
          case 6 :
            order.insuranceProductPrice.price = 100000.00;
            break;
        }
        storage.save('order', order);
      },
      career: function (newVal, oldVal) {
        let insured = storage.fetch("insured");
        insured.insuredCareer = newVal;
        storage.save('insured', insured);
      }
    },
    created: function () {
      let query = this.$route.query;
      this.title = query.title;
      this.proId = query.id;

      let order = this.Admin.order;
      order.insuranceProduct.prodId = this.proId;
      if (this.proId === 1) {
        order.insuranceProductPrice.priceId = 1;
        this.priceId = 1;
      }
      if (this.proId === 2) {
        order.insuranceProductPrice.priceId = 4;
        this.priceId = 4;
      }
      storage.save('order', order);

      let insured = storage.fetch("insured");
      this.birthday = insured.insuredBirthday;
      this.gender = insured.insuredGender;
      this.career = insured.insuredCareer;
    }
  }
</script>

<style scoped>
  .input {
    border: unset;
    margin-left: 10px;
    text-rendering: unset;
    width: 80%;
    outline: none;
    font-size: 14px;
    cursor: pointer;
    padding: 8px 6px 8px 0;
  }

  .checkIcon {
    position: absolute;
    width: 4vw;
  }

  .error {
    color: #f79483;
    font-size: 12px;
    text-align: right;
    margin-right: 10px;
  }

  .errorInput {
    animation-name: shakeError;
    animation-duration: .6s;
    animation-timing-function: ease-in-out;
    border: 1px solid #f79483;
  }

  @keyframes shakeError {
    0% {
      transform: translateX(0);
    }
    15% {
      transform: translateX(0.375rem);
    }
    30% {
      transform: translateX(-0.375rem);
    }
    45% {
      transform: translateX(0.375rem);
    }
    60% {
      transform: translateX(-0.375rem);
    }
    75% {
      transform: translateX(0.375rem);
    }
    90% {
      transform: translateX(-0.375rem);
    }
    100% {
      transform: translateX(0);
    }
  }
</style>
