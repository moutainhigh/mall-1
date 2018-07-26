<template>
  <div style="background: #fff">
    <group label-width="6rem" label-align="left" style="font-size: 15px;">
      <datetime title="出生日期" style="margin-right: 15px" v-model="birthday" startDate="1950-01-01" :endDate="startDate"
                placeholder="请选择出生日期"
                value-text-align="left" v-bind:class="{'errorInput': $v.birthday.$error}"></datetime>
      <div class="error" v-if="!$v.birthday.required && $v.birthday.$dirty">
        出生日期不能为空
      </div>

      <div class="i-input"
           style="padding: 0 15px 0 0; border-top: 1px solid #ececec; margin-left: 15px; margin-right: 15px">
        <div class="i-input-item" style="width: 6rem">性别</div>
        <div class="i-input-radio" style="top: 0;">
          <div class="radio-div" style="margin-right: 0" @click="changeGender(true)">
            <button v-if="gender" class="check-on">男</button>
            <button v-if="!gender" class="check-off">男</button>
          </div>
          <div class="radio-div" style="margin-right: 0" @click="changeGender(false)">
            <button v-if="!gender" class="check-on" style="margin-left: 15px">女</button>
            <button v-if="gender" class="check-off" style="margin-left: 15px">女</button>
          </div>
        </div>
      </div>

      <x-input class="x-input-over" style="margin-right: 15px" title="被保人职业" placeholder="请选择职业" v-model="career"
               v-bind:class="{'errorInput': $v.career.$error}"
               @input="$v.career.$touch()"></x-input>
      <div style="position:absolute;width: 100%;height: 42px;margin-top: -42px;" @click="goToSelect"></div>
      <div class="error" v-if="!$v.career.required && $v.career.$dirty">请选择职业</div>
    </group>
    <div style="width: 100%; height: 10px; background: #f5f5f5"></div>


    <!--</div>-->
    <div class="i-card" style="margin-bottom: 0">
      <div class="i-card-tip">
        {{title}}
        <div class="i-list-right">
          <img src="../assets/img/risk.png" height="18">
          <span style="color: #333">主险</span>
        </div>
      </div>
      <div class="i-input">
        <div class="i-input-item">保险期间</div>
        <div class="i-input-val">保障终身</div>
      </div>
      <div class="i-input">
        <div class="i-input-item">保障年限</div>
        <div class="i-input-val">十年</div>
      </div>
      <div class="i-input">
        <div class="i-input-item">基本保额</div>
        <div class="i-input-radio" v-if="proId == 1">
          <div class="radio-div" @click="priceId = 1">
            <button v-if="priceId ===1" class="check-on">2万</button>
            <button v-if="priceId !==1" class="check-off">2万</button>
          </div>
          <div class="radio-div" @click="priceId = 2">
            <button v-if="priceId ===2" class="check-on">5万</button>
            <button v-if="priceId !==2" class="check-off">5万</button>
          </div>
          <div class="radio-div" @click="priceId = 3">
            <button v-if="priceId ===3" class="check-on">10万</button>
            <button v-if="priceId !==3" class="check-off">10万</button>
          </div>
        </div>
        <div class="i-input-radio" v-if="proId == 2">
          <div style="display: inline-block;color: #c01212;">
            <div class="radio-div" @click="priceId = 4">
              <button v-if="priceId ===4" class="check-on">2万</button>
              <button v-if="priceId !==4" class="check-off">2万</button>
            </div>
            <div class="radio-div" @click="priceId = 5">
              <button v-if="priceId ===5" class="check-on">5万</button>
              <button v-if="priceId !==5" class="check-off">5万</button>
            </div>
            <div class="radio-div" @click="priceId = 6">
              <button v-if="priceId ===6" class="check-on">10万</button>
              <button v-if="priceId !==6" class="check-off">10万</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div style="height: 1px; margin: 0 15px; background: #ececec"></div>
    <div class="i-card" style="height: 190px">
      <div style="font-size: 15px;color: #333;">
        投保须知
      </div>
      <div class="i-message" @click="state = !state">
        <img v-if="!state" class="checkIcon" src="../assets/img/unselect.png">
        <img v-if="state" class="checkIcon" src="../assets/img/selected.png">
        <div><p>欢迎使用富德生命投保，请您仔细阅读人身保险投保提示书、产品说明书及保险条款，如实填写各项投保信息并确保为本人签名。保险合同将以此为依据，否则可能影响所签合同的法律效力。</p></div>
      </div>
    </div>
    <div style="height: 60px">
      <div class="i-footer">
        <button @click="submit">
          <div>完善投保信息</div>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
  import storage from "../store/storage"
  import {Datetime, PopupPicker} from 'vux'
  import XInput from "vux/src/components/x-input/index";
  import {required} from 'vuelidate/lib/validators'
  import {dateFormat} from "../config/mUtils";

  export default {
    name: "holder-basic",
    components: {
      XInput,
      PopupPicker,
      Datetime
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
        startDate: dateFormat(new Date(), "yyyy-MM-dd"),
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
          window.alert('请勾选投保须知')
          return false;
        } else {
          this.$router.push('/insured');
        }
      },
      goToSelect() {
        this.$router.push({
          path: '/careerSelect',
          query: {
            type: 'insured',
            key: 'insuredCareer'
          }
        })
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
      }
    },
    created: function () {
      let query = this.$route.query;
      this.title = query.title;
      this.proId = query.id;
      let order;
      if (storage.fetch('order').length != 0) {
        order = storage.fetch('order');
      } else {
        order = this.Admin.order
      }
      if (order.insuranceProduct.prodId != this.proId) {
        order.insuranceProduct.prodId = this.proId;
        if (this.proId == 1) {
          order.insuranceProductPrice.priceId = 1;
          this.priceId = 1;
        }
        if (this.proId == 2) {
          order.insuranceProductPrice.priceId = 4;
          this.priceId = 4;
        }
      } else {
        this.priceId = order.insuranceProductPrice.priceId;
      }

      let holder = storage.fetch("holder");
      order.insuranceOrderPolicyholderBank.bankCardImg = holder.bankCardImg;
      storage.save('order', order);

      let insured = storage.fetch("insured");
      this.birthday = insured.insuredBirthday;
      this.gender = insured.insuredGender;
      this.career = insured.careerName;
    },
    activated() {
      let insured = storage.fetch("insured");
      this.career = insured.careerName;
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
    font-size: 15px !important;
    cursor: pointer;
    padding: 8px 6px 8px 0;
  }

  button {
    outline: unset;
  }

  .i-input .i-input-item {
    width: 6rem;
    font-size: 16px !important;
  }

  .i-input .i-input-val {
    font-size: 16px !important;
  }

  a {
    text-decoration: unset;
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
