<template>
  <div>
    <div class="title" style="margin-top: 0;">
      被保人信息
    </div>
    <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model.trim="insured.insuredName"></x-input>
      <div class="error" v-if="!$v.insured.insuredName.required">姓名不能为空</div>
      <div class="error" v-if="!$v.insured.insuredName.minLength">姓名最小为 {{$v.insured.insuredName.$params.minLength.min}}
        个汉字
      </div>

      <popup-picker title="证件类型" placeholder="请选择证件类型" :data="cardTypes" v-model="insured.insuredCardType"
                    value-text-align="left"></popup-picker>

      <x-input title="证件号码" placeholder="请输入证件号" v-model="insured.insuredCardNo"></x-input>
      <div class="error" v-if="!$v.insured.insuredCardNo.required">身份证号码不能为空</div>
      <div class="error" v-if="!$v.insured.insuredCardNo.idCardVali">请输入正确的身份证号码</div>

      <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="insured.insuredCardPeriod"></x-input>
      <div class="error" v-if="!$v.insured.insuredCardPeriod.required">证件有效期不能为空</div>
      <div class="error" v-if="!$v.insured.insuredCardPeriod.minLength">最小不小于1位数</div>
      <div class="error" v-if="!$v.insured.insuredCardPeriod.maxLength">最大不超过2位数</div>
      <div class="error" v-if="!$v.insured.insuredCardPeriod.numeric">证件有效期应为数字</div>

      <x-input title="国籍" placeholder="请输入国籍" v-model="insured.insuredCountry"></x-input>
      <x-input title="身高(cm)" placeholder="请输入身高" v-model="insured.insuredHeight"></x-input>
      <div class="error" v-if="!$v.insured.insuredBodyWeight.required">身高不能为空</div>
      <div class="error" v-if="!$v.insured.insuredHeight.decimal">请输入身高数字，支持小数点后两位</div>

      <x-input title="体重(kg)" placeholder="请输入体重" v-model="insured.insuredBodyWeight"></x-input>
      <div class="error" v-if="!$v.insured.insuredBodyWeight.required">体重不能为空</div>
      <div class="error" v-if="!$v.insured.insuredBodyWeight.decimal">请输入体重数字，支持小数点后两位</div>

      <x-input title="年收入(万元)" placeholder="请输入年收入" v-model="insured.insuredIncome"></x-input>
      <div class="error" v-if="!$v.insured.insuredIncome.required">请输入年收入</div>
      <div class="error" v-if="!$v.insured.insuredIncome.decimal">请输入年收入，单位万元，支持小数点后两位</div>

      <popup-picker title="婚姻状况" placeholder="请选择婚姻状况" :data="maritalStatus" value-text-align="left"
                    v-model="insured.insuredMarriage"></popup-picker>
      <div>
        <div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" placeholder="请输入固定电话" v-model="insured.insuredTel"></x-input>
      <div class="error" v-if="!$v.insured.insuredTel.fixedTel">请输入正确的固定电话号码</div>

      <x-input title="移动电话" placeholder="请输入移动电话" v-model="insured.insuredMobile"></x-input>
      <div class="error" v-if="!$v.insured.insuredMobile.mobile">请输入正确的手机号码</div>

      <x-input title="E-mail" placeholder="非必填项" v-model="insured.insuredEmail"></x-input>
      <div class="error" v-if="!$v.insured.insuredEmail.email">请输入正确邮箱地址</div>

      <x-address title="家庭住址" placeholder="请选择地址" raw-value :list="addressData" value-text-align="left"></x-address>

      <x-input title="详细地址" placeholder="请输入详细地址" v-model="insured.insuredAddress"></x-input>
      <div class="error" v-if="!$v.insured.insuredAddress.required">请输入详细地址</div>

      <popup-picker title="投保人是被保人的" placeholder="请选择投被保人的关系" :data="relates" value-text-align="left"
                    v-model="insured.insuredRelation"></popup-picker>
    </group>

    <div class="title">
      投保人信息
    </div>
    <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model="holder.policyholderName"></x-input>
      <div class="i-input">
        <div class="i-input-item">性别</div>
        <div class="i-input-radio">
          <div class="radio-div" @click="holder.policyholderGender = true">
            <span>男</span>
            <img v-if="holder.policyholderGender" src="../assets/img/case-on.png"/>
            <img v-if="!holder.policyholderGender" src="../assets/img/case-off.png"/>
          </div>
          <div class="radio-div" @click="holder.policyholderGender = false">
            <span>女</span>
            <img v-if="!holder.policyholderGender" src="../assets/img/case-on.png"/>
            <img v-if="holder.policyholderGender" src="../assets/img/case-off.png"/>
          </div>
        </div>
      </div>
      <popup-picker title="投保人职业" placeholder="请选择职业" value-text-align="left"></popup-picker>
      <datetime title="出生日期" v-model="holder.policyholderBirthday" placeholder="请输入生日日期"
                value-text-align="left"></datetime>
      <popup-picker title="证件类型" placeholder="请选择证件类型" v-model="holder.policyholderCardType" :data="cardTypes"
                    value-text-align="left"></popup-picker>
      <x-input title="证件号码" v-model="holder.policyholderCardNo" placeholder="请输入证件号"></x-input>
      <div class="error" v-if="!$v.holder.policyholderCardNo.required">身份证号码不能为空</div>
      <div class="error" v-if="!$v.holder.policyholderCardNo.idCardVali">请输入正确的身份证号码</div>

      <x-input title="证件有效期" v-model="holder.policyholderCardPeroid" placeholder="请选择证件有效期"></x-input>
      <div class="error" v-if="!$v.holder.policyholderCardPeroid.required">证件有效期不能为空</div>
      <div class="error" v-if="!$v.holder.policyholderCardPeroid.minLength">最小不小于1位数</div>
      <div class="error" v-if="!$v.holder.policyholderCardPeroid.maxLength">最大不超过2位数</div>
      <div class="error" v-if="!$v.holder.policyholderCardPeroid.numeric">证件有效期应为数字</div>

      <x-input title="国籍" placeholder="请输入国籍" v-model="holder.insuredCountry"></x-input>
      <div class="error" v-if="!$v.holder.insuredCountry.required">请输入国籍</div>

      <x-input title="身高(cm)" v-model="holder.policyholderHeight" placeholder="请输入身高"></x-input>
      <div class="error" v-if="!$v.holder.policyholderHeight.required">身高不能为空</div>
      <div class="error" v-if="!$v.holder.policyholderHeight.decimal">请输入身高数字，支持小数点后两位</div>

      <x-input title="体重(kg)" v-model="holder.policyholderBodyWeight" placeholder="请输入体重"></x-input>
      <div class="error" v-if="!$v.holder.policyholderBodyWeight.required">体重不能为空</div>
      <div class="error" v-if="!$v.holder.policyholderBodyWeight.decimal">请输入体重数字，支持小数点后两位</div>

      <x-input title="年收入(万元)" v-model="holder.policyholderIncome" placeholder="请输入年收入"></x-input>
      <div class="error" v-if="!$v.holder.policyholderIncome.required">请输入年收入</div>
      <div class="error" v-if="!$v.holder.policyholderIncome.decimal">请输入年收入，单位万元，支持小数点后两位</div>

      <popup-picker title="婚姻状况" v-model="holder.policyholderMarriage" placeholder="请选择婚姻状况" :data="maritalStatus"
                    value-text-align="left"></popup-picker>
      <div>
        <div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" v-model="holder.policyholderTel" placeholder="请输入固定电话"></x-input>
      <div class="error" v-if="!$v.holder.policyholderTel.fixedTel">请输入正确的固定电话号码</div>

      <x-input title="移动电话" v-model="holder.policyholderMobile" placeholder="请输入移动电话"></x-input>
      <div class="error" v-if="!$v.holder.policyholderMobile.mobile">请输入正确的手机号码</div>

      <x-input title="E-mail" v-model="holder.policyholderEmail" placeholder="非必填项"></x-input>
      <div class="error" v-if="!$v.holder.policyholderEmail.email">请输入正确邮箱地址</div>

      <x-address title="家庭住址" v-model="addressValue" placeholder="请选择地址" raw-value :list="addressData"
                 value-text-align="left"></x-address>

      <x-input title="详细地址" v-model="holder.policyholderAddress" placeholder="请输入详细地址"></x-input>
      <div class="error" v-if="!$v.holder.policyholderAddress.required">请输入详细地址</div>

      <popup-picker title="涉税人身份信息" v-model="holder.policyholderTaxRelated" placeholder="请选择涉税人信息" :data="taxRelates"
                    value-text-align="left"></popup-picker>
    </group>

    <div class="title" style="color: #2c3e50;">
      <div class="title-select" @click="changeLegal">
        <img v-if="legalBeneficiary" src="../assets/img/selected.png"/>
        <img v-if="!legalBeneficiary" src="../assets/img/unselect.png"/>
        受益人：法定受益人
      </div>
      <div class="title-add" v-if="beneficiaries.length < 2" @click="addBene">
        <img src="../assets/img/add.png"/>新增受益人
      </div>
    </div>
    <div v-for="(beneficiary, index) in beneficiaries" v-if="!legalBeneficiary">
      <div class="add">受益人信息 <span style="float: right;" @click="delBene(index)">删除</span></div>
      <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
        <x-input title="姓名" placeholder="请输入姓名" v-model="beneficiary.beneficiaryName"></x-input>
        <div class="i-input">
          <div class="i-input-item">性别</div>
          <div class="i-input-radio">
            <div class="radio-div" @click="beneficiary.beneficiaryGender = true">
              <span>男</span>
              <img v-if="beneficiary.beneficiaryGender" src="../assets/img/case-on.png"/>
              <img v-if="!beneficiary.beneficiaryGender" src="../assets/img/case-off.png"/>
            </div>
            <div class="radio-div" @click="beneficiary.beneficiaryGender = false">
              <span>女</span>
              <img v-if="!beneficiary.beneficiaryGender" src="../assets/img/case-on.png"/>
              <img v-if="beneficiary.beneficiaryGender" src="../assets/img/case-off.png"/>
            </div>
          </div>
        </div>
        <x-input title="国籍" placeholder="请输入国籍" v-model="beneficiary.beneficiaryCountry"
                 value-text-align="left"></x-input>
        <popup-picker title="受益顺序" placeholder="请输入受益顺序" :data="orders" v-model="beneficiary.beneficiaryOrder"
                      value-text-align="left"></popup-picker>
        <x-input title="受益份额" placeholder="请输入受益份额" v-model="beneficiary.beneficiaryProportion"></x-input>
        <datetime title="出生日期" placeholder="请选择出生日期" v-model="beneficiary.beneficiaryBirthday"
                  value-text-align="left"></datetime>
        <popup-picker title="证件类型" placeholder="请选择证件类型" v-model="beneficiary.beneficiaryCardType"
                      value-text-align="left"></popup-picker>
        <x-input title="证件号码" placeholder="请输入证件号" v-model="beneficiary.beneficiaryCardNo"></x-input>
        <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="beneficiary.beneficiaryCardPeroid"></x-input>
        <popup-picker title="是被保人的" placeholder="请选择关系" v-model="beneficiary.insuredRelation" value-text-align="left"
                      :data="relates"></popup-picker>
      </group>
    </div>
    <div style="height: 50px;">
      <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;color: #e1bb3a" @click="comeBack">
        <div>上一步</div>
      </button>
      <button class="i-footer" style="width: 50%;right: 0" @click="next">
        <div>下一步</div>
      </button>
    </div>
  </div>

</template>

<script>

  import {Group, XInput, Selector, PopupPicker, Datetime, ChinaAddressData, XAddress} from 'vux'
  import storage from "../store/storage";
  import {required, minLength, maxLength, between, helpers, numeric, email} from 'vuelidate/lib/validators'

  //身份证正则校验
  const idCardVali = helpers.regex('idCardVali', /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/);
  //小数点后两位校验
  const decimal = helpers.regex('decimal', /^\d{0,8}\.{0,1}(\d{1,2})?$/);
  //固定电话校验
  const fixedTel = helpers.regex('fixedTel', /0\d{2}-\d{7,8}/);
  //手机号码校验
  const mobile = helpers.regex('mobile', /^[1][3,4,5,7,8][0-9]{9}$/);

  export default {
    components: {
      Group,
      XInput,
      Selector,
      PopupPicker,
      XAddress,
      Datetime,
    },
    name: "holder-detail",
    data() {
      return {
        addressValue: [],
        holder: storage.fetch('holder'),
        insured: storage.fetch('insured'),
        beneficiaries: storage.fetch('beneficiaries'),
        //是否法定受益人
        legalBeneficiary: true,
        cardTypes: [['居民身份证', '居民户口簿', '军人身份证', '港澳居民往来内地通行证', '出生证', '台湾居民往来内地通行证', '外国护照', '外国人永久居留身份证', '武警身份证', '其他证件']],
        maritalStatus: [['未婚', '已婚', '丧偶', '离婚']],
        relates: [["本人", "配偶", "父母", "子女", "兄弟姐妹", "祖父母", "外祖父母", "祖孙", "外祖孙", "其他"]],
        taxRelates: [["仅为中国税收居民", "仅为非居民", "既是中国税收居民又是其他国家（地区）税收居民"]],
        orders: [['1', '2']],
        addressData: ChinaAddressData,
        countries: this.Admin.countries,
        endDate: new Date()
      }
    },
    validations: {
      insured: {
        insuredName: {
          required,
          minLength: minLength(2)
        },
        insuredCardNo: {
          required,
          idCardVali,
        },
        insuredCardPeriod: {
          required,
          minLength: minLength(1),
          maxLength: maxLength(2),
          numeric
        },
        insuredHeight: {
          required,
          decimal,
        },
        insuredBodyWeight: {
          required,
          decimal,
        },
        insuredIncome: {
          required,
          decimal,
        },
        insuredTel: {
          fixedTel
        },
        insuredMobile: {
          mobile
        },
        insuredEmail: {
          email,
        },
        insuredAddress: {
          required,
        }
      },
      holder: {
        policyholderCardNo: {
          required,
          idCardVali,
        },
        policyholderCardPeroid: {
          required,
          minLength: minLength(1),
          maxLength: maxLength(2),
          numeric
        },
        insuredCountry: {
          required
        },
        policyholderHeight: {
          required,
          decimal,
        },
        policyholderBodyWeight: {
          required,
          decimal,
        },
        policyholderIncome: {
          required,
          decimal,
        },
        policyholderTel: {
          fixedTel,
        },
        policyholderMobile: {
          mobile
        },
        policyholderEmail: {
          email
        },
        policyholderAddress: {
          required,
        }
      }
    },
    methods: {
      comeBack() {
        this.$router.back();
      },
      next() {
        this.$router.push("infoMatters")
      },
      addBene() {
        if (this.beneficiaries.length < 2) {
          let beneficiary = {
            beneficiaryName: '',
            beneficiaryGender: '',
            beneficiaryBirthday: '',
            beneficiaryCardType: [],
            beneficiaryCardNo: '',
            beneficiaryCardPeroid: '',
            insuredRelation: [],
            beneficiaryOrder: [],
            beneficiaryProportion: '',
            beneficiaryCountry: ''
          };
          this.beneficiaries.push(beneficiary);
          storage.save("beneficiaries", this.beneficiaries);
          this.legalBeneficiary = false;
        }
      },
      delBene(index) {
        this.beneficiaries.splice(index, 1);
        storage.save("beneficiaries", this.beneficiaries);
        if (this.beneficiaries.length === 0) {
          this.legalBeneficiary = !this.legalBeneficiary;
        }
      },
      changeLegal() {
        this.legalBeneficiary = !this.legalBeneficiary;
        let order = storage.fetch("order");
        order.legalBeneficiary = this.legalBeneficiary;
        storage.save("order", order);
      }
    },
    watch: {
      addressValue: function (val, oldVal) {
      },
      holder: {
        handler(newVal, oldVal) {
          if (oldVal != null || oldVal !== undefined) {
            storage.save('holder', newVal);
          }
        },
        immediate: true,
        deep: true
      },
      beneficiaries: {
        handler(newVal, oldVal) {
          storage.save('beneficiaries', newVal);
        },
        immediate: true,
        deep: true
      },
      insured: {
        handler(newVal, oldVal) {
          storage.save('insured', newVal);
        },
        immediate: true,
        deep: true
      }
    },
    created: function () {
      let order = storage.fetch("order");
      this.legalBeneficiary = order.legalBeneficiary;
    }
  }
</script>

<style scoped>
  .title {
    margin: 10px 0;
    background-color: #ffffff;
    padding: 15px;
    font-size: 13px;
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
    font-size: 14px;
    width: 7rem;
    margin-right: 2em;
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
    color: #c01212;
    font-size: 12px;
    margin-left: 155px;
  }
</style>
