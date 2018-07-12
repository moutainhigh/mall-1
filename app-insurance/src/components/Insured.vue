<template>
  <div>
    <div class="title" style="margin-top: 0;">
      被保人信息
    </div>
    <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model.trim="insured.insuredName"
               v-bind:class="{'errorInput': $v.insured.insuredName.$error}"
               @input="$v.insured.insuredName.$touch()"></x-input>
      <div class="error" v-if="!$v.insured.insuredName.required && $v.insured.insuredName.$dirty">姓名不能为空</div>
      <div class="error" v-if="!$v.insured.insuredName.minLength">姓名最少为 {{$v.insured.insuredName.$params.minLength.min}}
        个字符
      </div>
      <div class="error" v-if="!$v.insured.insuredName.maxLength">姓名最多不超过32位数</div>

      <popup-picker title="证件类型" placeholder="请选择证件类型" :data="cardTypes" v-model="insured.insuredCardType"
                    v-bind:class="{'errorInput': $v.insured.insuredCardType.$error}"
                    value-text-align="left"></popup-picker>
      <div class="error" v-if="!$v.insured.insuredCardType.required && $v.insured.insuredCardType.$dirty">证件类型不能为空</div>

      <x-input title="证件号码" placeholder="请输入证件号" v-model="insured.insuredCardNo"
               v-bind:class="{'errorInput': $v.insured.insuredCardNo.$error}"
               @input="$v.insured.insuredCardNo.$touch()"></x-input>
      <div class="error" v-if="!$v.insured.insuredCardNo.required && $v.insured.insuredCardNo.$dirty">证件号码不能为空</div>
      <div class="error" v-if="!$v.insured.insuredCardNo.cardVali">请输入正确的证件号码</div>

      <datetime title="证件有效期" v-model="insured.insuredCardPeriod" :startDate="startDate" endDate="2199-12-31"
                placeholder="请选择证件有效期"
                value-text-align="left" v-bind:class="{'errorInput': $v.insured.insuredCardPeriod.$error}"></datetime>
      <div class="error" v-if="!$v.insured.insuredCardPeriod.required && $v.insured.insuredCardPeriod.$dirty">
        证件有效期不能为空
      </div>

      <x-input title="国籍" placeholder="请输入国籍" v-model="insured.insuredCountry"
               v-bind:class="{'errorInput': $v.insured.insuredCountry.$error}"
               @input="$v.insured.insuredCountry.$touch()"></x-input>
      <div class="error" v-if="!$v.insured.insuredCountry.required && $v.insured.insuredCountry.$dirty">请输入国籍</div>
      <div class="error" v-if="!$v.insured.insuredCountry.maxLength">国籍最多不超过64位数</div>

      <div v-bind:class="{'errorInput': $v.insured.insuredHeight.$error}">
        <div class="input-ver">
          <x-input title="身高" placeholder="请输入身高" v-model="insured.insuredHeight" class="input-ver-x"
                   @input="$v.insured.insuredHeight.$touch()"></x-input>
          <div class="input-vile">cm</div>
        </div>
      </div>
      <div class="error" v-if="!$v.insured.insuredHeight.required && $v.insured.insuredHeight.$dirty">身高不能为空</div>
      <div class="error" v-if="!$v.insured.insuredHeight.int">请输入正确的身高（正整数）</div>
      <div class="error" v-if="!$v.insured.insuredHeight.maxLength">最大不超过3位数</div>

      <div v-bind:class="{'errorInput': $v.insured.insuredBodyWeight.$error}">
        <div class="input-ver">
          <x-input title="体重" placeholder="请输入体重" v-model="insured.insuredBodyWeight"
                   @input="$v.insured.insuredBodyWeight.$touch()" class="input-ver-x"></x-input>
          <div class="input-vile">kg</div>
        </div>
      </div>

      <div class="error" v-if="!$v.insured.insuredBodyWeight.required && $v.insured.insuredBodyWeight.$dirty">体重不能为空
      </div>
      <div class="error" v-if="!$v.insured.insuredBodyWeight.int">请输入正确的体重（正整数）</div>
      <div class="error" v-if="!$v.insured.insuredBodyWeight.maxLength">最大不超过3位数</div>

      <div v-bind:class="{'errorInput': $v.insured.insuredIncome.$error}">
        <div class="input-ver">
          <x-input title="年收入" placeholder="请输入年收入" v-model="insured.insuredIncome" class="input-ver-x"
                   @input="$v.insured.insuredIncome.$touch()"></x-input>
          <div class="input-vile">万元</div>
        </div>
      </div>
      <div class="error" v-if="!$v.insured.insuredIncome.required && $v.insured.insuredIncome.$dirty">请输入年收入</div>
      <div class="error" v-if="!$v.insured.insuredIncome.int">请输入年收入，单位万元（正整数）</div>
      <div class="error" v-if="!$v.insured.insuredIncome.maxLength">最大不超过6位数</div>

      <popup-picker title="婚姻状况" placeholder="请选择婚姻状况" :data="maritalStatus" value-text-align="left"
                    v-model="insured.insuredMarriage"
                    v-bind:class="{'errorInput': $v.insured.insuredMarriage.$error}"></popup-picker>
      <div class="error" v-if="!$v.insured.insuredMarriage.required && $v.insured.insuredMarriage.$dirty">
        婚姻状况不能为空
      </div>
      <div style="background-color: #f5f5f5">
        <div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 13px;padding: 10px 10px 10px 0;color: #888;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" placeholder="请输入固定电话" v-model="insured.insuredTel"
               v-bind:class="{'errorInput': $v.insured.insuredMobile.$error}"></x-input>
      <!--<div class="error" v-if="!$v.insured.insuredTel.required && $v.insured.insuredTel.$dirty">-->
      <!--请输入固定电话或移动电话-->
      <!--</div>-->
      <div class="error" v-if="!$v.insured.insuredTel.fixedTel">请输入正确的固定电话号码</div>

      <x-input title="移动电话" placeholder="请输入移动电话" v-model="insured.insuredMobile"
               v-bind:class="{'errorInput': $v.insured.insuredMobile.$error}"></x-input>
      <!--<div class="error" v-if="!$v.insured.insuredMobile.required && $v.insured.insuredMobile.$dirty">-->
      <!--请输入固定电话或移动电话-->
      <!--</div>-->
      <div class="error" v-if="!$v.insured.insuredMobile.mobile">请输入正确的手机号码</div>

      <x-input title="E-mail" placeholder="请输入邮箱（选填）" v-model="insured.insuredEmail"></x-input>
      <div class="error" v-if="!$v.insured.insuredEmail.mail">请输入正确邮箱地址</div>

      <x-address title="家庭住址" placeholder="请选择地址" :list="addressData" v-model="insured.insuredPCD"
                 value-text-align="left" v-bind:class="{'errorInput': $v.insured.insuredPCD.$error}"></x-address>
      <div class="error" v-if="!$v.insured.insuredPCD.required && $v.insured.insuredPCD.$dirty">
        家庭住址不能为空
      </div>

      <x-input title="详细地址" placeholder="请输入详细地址" v-model="insured.insuredAddress"
               v-bind:class="{'errorInput': $v.insured.insuredAddress.$error}"
               @input="$v.insured.insuredAddress.$touch()"></x-input>
      <div class="error" v-if="!$v.insured.insuredAddress.required && $v.insured.insuredAddress.$dirty">请输入详细地址</div>
      <div class="error" v-if="!$v.insured.insuredAddress.maxLength">详细地址最多不超过255位数</div>

      <popup-picker title="是被保人的" placeholder="请选择关系" :data="relates" value-text-align="left"
                    v-model="insured.insuredRelation"
                    v-bind:class="{'errorInput': $v.insured.insuredRelation.$error}"></popup-picker>
      <div class="error" v-if="!$v.insured.insuredRelation.required && $v.insured.insuredRelation.$dirty">
        投保关系不能为空
      </div>
    </group>
    <toast v-model="showPositionValue" type="text" :time="800" is-show-mask position="middle">{{toastText}}</toast>
    <!--<div style="height: 50px;">-->
    <!--<button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;color: #e1bb3a" @click="comeBack">-->
    <!--<div>上一步</div>-->
    <!--</button>-->
    <!--<button class="i-footer" style="width: 50%;right: 0" @click="next">-->
    <!--<div>下一步</div>-->
    <!--</button>-->
    <!--</div>-->
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
  import {ChinaAddressData, Datetime, Group, PopupPicker, Selector, XAddress, XInput, Toast} from 'vux'
  import storage from "../store/storage";
  import {required, minLength, maxLength, email, helpers} from 'vuelidate/lib/validators'
  import {dateFormat} from "../config/mUtils";
  import {
    idCardVali,
    householdVali,
    birthVali,
    hkmcPassVali,
    taiwanPassVali,
    passportVali,
    permanentResidenceVali,
    int,
    fixedTel,
    mobile,
    mail
  } from "../admin/validate";

  export default {
    components: {
      Group,
      XInput,
      Selector,
      PopupPicker,
      XAddress,
      Datetime,
      Toast
    },
    name: "Insured",
    data() {
      return {
        insured: storage.fetch('insured'),
        cardTypes: [['居民身份证', '居民户口簿', '军人身份证', '港澳居民往来内地通行证', '出生证', '台湾居民往来内地通行证', '外国护照', '外国人永久居留身份证', '武警身份证', '其他证件']],
        maritalStatus: [['未婚', '已婚', '丧偶', '离婚']],
        relates: [["本人", "配偶", "父母", "子女", "兄弟姐妹", "祖父母", "外祖父母", "祖孙", "外祖孙", "其他"]],
        addressData: ChinaAddressData,
        countries: this.Admin.countries,
        endDate: new Date(),
        //提交表单时校验状态
        submitStatus: null,
        showPositionValue: false,
        toastText: '',
        startDate: dateFormat(new Date(), "yyyy-MM-dd"),
        vali: ''
      }
    },
    validations() {
      return {
        insured: {
          insuredName: {required, minLength: minLength(2), maxLength: maxLength(32)},
          insuredCardType: {required},
          insuredCardNo: {required, cardVali: this.vali},
          insuredCardPeriod: {required},
          insuredCountry: {required, maxLength: maxLength(64)},
          insuredHeight: {required, int, maxLength: maxLength(3)},
          insuredBodyWeight: {required, int, maxLength: maxLength(3)},
          insuredIncome: {required, int, maxLength: maxLength(6)},
          insuredMarriage: {required},
          insuredTel: {fixedTel},
          insuredMobile: {mobile},
          insuredEmail: {mail},
          insuredPCD: {required},
          insuredAddress: {required, maxLength: maxLength(255)},
          insuredRelation: {required}
        }
      }
    },
    methods: {
      comeBack() {
        this.$router.back();
      },
      next() {
        this.$v.$touch();
        if (this.insured.insuredTel === '' && this.insured.insuredMobile === '') {
          this.showPositionValue = true;
          this.toastText = "请填写固定电话或手机号码";
          return false;
        }
        if (this.$v.insured.$invalid) {
          this.submitStatus = 'ERROR';
          this.showPositionValue = true;
          this.toastText = "信息填写有误"
        } else {
          this.submitStatus = 'PENDING';
          this.$router.push("holder-detail");
        }
      },
    },
    watch: {
      insured: {
        handler(newVal, oldVal) {
          if (newVal.insuredPCD && newVal.insuredPCD.length === 3) {
            newVal.insuredProvince = this.insured.insuredPCD[0];
            newVal.insuredCity = this.insured.insuredPCD[1];
            newVal.insuredDistrict = this.insured.insuredPCD[2];
          }
          if (newVal.insuredCardType) {
            switch (newVal.insuredCardType[0]) {
              case '居民身份证' :
                this.vali = idCardVali;
                break;
              case '居民户口簿' :
                this.vali = householdVali;
                break;
              case '军人身份证' :
                this.vali = idCardVali;
                break;
              case '港澳居民往来内地通行证' :
                this.vali = hkmcPassVali;
                break;
              case '出生证' :
                this.vali = birthVali;
                break;
              case '台湾居民往来内地通行证' :
                this.vali = taiwanPassVali;
                break;
              case '外国护照' :
                this.vali = passportVali;
                break;
              case '外国人永久居留身份证' :
                this.vali = permanentResidenceVali;
                break;
              case '武警身份证' :
                this.vali = idCardVali;
                break;
              case '其他证件' :
                this.vali = '';
                break;
            }
          }
          storage.save('insured', newVal);
        },
        immediate: true,
        deep: true
      },
    },
    mounted() {
      let isiOS = /(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent);
      if (isiOS) {
        let elementsByClassName = document.getElementsByClassName("vux-cell-primary");
        let elementsByClassName2 = document.getElementsByClassName("i-input-radio");
        for (let i = 0; i < elementsByClassName.length; i++) {
          elementsByClassName[i].style.setProperty('margin-left', '7px', 'important');
        }
        for (let i = 0; i < elementsByClassName2.length; i++) {
          elementsByClassName2[i].setAttribute('style', 'margin-left:10px;');
        }
      }
    },
  }
</script>

<style scoped>
  .title {
    margin: 10px 0 0 0;
    background-color: #ffffff;
    padding: 15px;
    font-size: 16px;
    color: #e1bb3a;
  }

  .i-input-radio {
    display: inline-block;
    position: relative;
    top: 10px;
    left: -5px;
    margin-left: 0;
  }

  .i-input .i-input-radio span {
    position: absolute;
    font-size: 16px;
    height: 28px;
    width: 28px;
    text-align: center;
    margin-top: 3px;
  }

  .i-input .i-input-radio .radio-div img {
    height: 28px;
  }

  .i-input {
    padding-top: 0;
    font-size: 15px;
    border-bottom: #d9d9d9 solid 1px;
    border-top: #d9d9d9 solid 1px;
    margin-left: 15px;
    margin-bottom: -1px;
  }

  .i-input-item {
    font-size: 14px !important;
    width: 7rem !important;
    margin: 13px 2em 13px 0 !important;
  }

  .title-select img {
    width: 14px;
    position: relative;
    top: 2px;
    margin-right: 10px;
  }

  .title-add {
    margin-top: 10px;
  }

  .title-add img {
    width: 20px;
    position: relative;
    top: 5px;
    margin-right: 10px;
    font-size: 16px;
  }

  .add {
    background-color: #ffffff;
    padding: 10px 15px;
    margin-top: 10px;
    font-size: 14px;
  }

  a {
    text-decoration: unset;
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
    border-radius: 5px;
  }

  .input-vile {
    width: 40px;
    text-align: right;
    float: right;
    font-size: 16px;
    margin-top: -36px;
    margin-right: 16px;
    padding: 4px 8px;
    color: #bfbfbf
  }

  .input-ver {
    margin-left: 16px;
    border-top: #d9d9d9 solid 1px;
  }

  .input-ver-x {
    width: 83%;
    padding-left: 0;
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
