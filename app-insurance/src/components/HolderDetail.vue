<template>
  <div>
    <div class="title" style="margin-top: 0;">
      被保人信息
    </div>
    <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model="insured.insuredName"></x-input>
      <popup-picker title="证件类型" placeholder="请选择证件类型" :data="cardTypes" v-model="insured.insuredCardType" value-text-align="left"></popup-picker>
      <x-input title="证件号码" placeholder="请输入证件号" v-model="insured.insuredCardNo"></x-input>
      <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="insured.insuredCardPeriod"></x-input>
      <popup-picker title="国籍" placeholder="请选择国籍" :data="countries" value-text-align="left"></popup-picker>
      <x-input title="身高(cm)" placeholder="请输入身高" v-model="insured.insuredHeight"></x-input>
      <x-input title="体重(kg)" placeholder="请输入体重" v-model="insured.insuredBodyWeight"></x-input>
      <x-input title="年收入(万元)" placeholder="请输入年收入" v-model="insured.insuredIncome"></x-input>
      <popup-picker title="婚姻状况" placeholder="请选择婚姻状况" :data="maritalStatus" value-text-align="left" v-model="insured.insuredMarriage"></popup-picker>
      <div>
        <div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" placeholder="请输入固定电话" v-model="insured.insuredTel"></x-input>
      <x-input title="移动电话" placeholder="请输入移动电话" v-model="insured.insuredMobile"></x-input>
      <x-input title="E-mail" placeholder="非必填项" v-model="insured.insuredEmail"></x-input>
      <x-address title="家庭住址" placeholder="请选择地址" raw-value :list="addressData" value-text-align="left"></x-address>
      <x-input title="详细地址" placeholder="请输入详细地址" v-model="insured.insuredAddress"></x-input>
      <popup-picker title="投保人是被保人的" placeholder="请选择投被保人的关系" :data="relates" value-text-align="left" v-model="insured.insuredRelation"></popup-picker>
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
      <datetime title="出生日期" v-model="holder.policyholderBirthday" placeholder="请输入生日日期" value-text-align="left"></datetime>
      <popup-picker title="证件类型" placeholder="请选择证件类型" v-model="holder.policyholderCardType" :data="cardTypes" value-text-align="left"></popup-picker>
      <x-input title="证件号码" v-model="holder.policyholderCardNo" placeholder="请输入证件号"></x-input>
      <x-input title="证件有效期" placeholder="请选择证件有效期"></x-input>
      <popup-picker title="国籍" placeholder="请选择国籍" :data="countries" value-text-align="left"></popup-picker>
      <x-input title="身高(cm)" v-model="holder.policyholderHeight" placeholder="请输入身高"></x-input>
      <x-input title="体重(kg)" v-model="holder.policyholderBodyWeight" placeholder="请输入体重"></x-input>
      <x-input title="年收入(万元)" v-model="holder.policyholderIncome" placeholder="请输入年收入"></x-input>
      <popup-picker title="婚姻状况" v-model="holder.policyholderMarriage" placeholder="请选择婚姻状况" :data="maritalStatus" value-text-align="left"></popup-picker>
      <div>
        <div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" v-model="holder.policyholderTel" placeholder="请输入固定电话"></x-input>
      <x-input title="移动电话" v-model="holder.policyholderMobile" placeholder="请输入移动电话"></x-input>
      <x-input title="E-mail" v-model="holder.policyholderEmail" placeholder="非必填项"></x-input>
      <x-address title="家庭住址" v-model="addressValue" placeholder="请选择地址" raw-value :list="addressData" value-text-align="left"></x-address>
      <x-input title="详细地址" v-model="holder.policyholderAddress" placeholder="请输入详细地址"></x-input>
      <popup-picker title="涉税人身份信息" v-model="holder.policyholderTaxRelated" placeholder="请选择涉税人信息" :data="taxRelates" value-text-align="left"></popup-picker>
    </group>

    <div class="title" style="color: #2c3e50;">
      <div class="title-select" @click="legalBeneficiary = !legalBeneficiary">
        <img v-if="legalBeneficiary" src="../assets/img/selected.png"/>
        <img v-if="!legalBeneficiary" src="../assets/img/unselect.png"/>
        受益人：法定受益人
      </div>
      <div class="title-add" v-if="beneficiaries.length < 2" @click="addBene">
        <img src="../assets/img/add.png"/>新增受益人
      </div>
    </div>
    <div v-for="beneficiary in beneficiaries" v-if="!legalBeneficiary">
      <div class="add">受益人信息 <span style="float: right;" @click="delBene">删除</span></div>
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
        <popup-picker title="国籍" placeholder="请选择国籍" value-text-align="left"></popup-picker>
        <popup-picker title="受益顺序" placeholder="请输入受益顺序" :data="maritalStatus" v-model="beneficiary.beneficiaryOrder" value-text-align="left"></popup-picker>
        <x-input title="受益份额" placeholder="请输入受益份额" v-model="beneficiary.beneficiaryProportion"></x-input>
        <datetime title="出生日期" placeholder="请选择出生日期" v-model="beneficiary.beneficiaryBirthday" value-text-align="left"></datetime>
        <popup-picker title="证件类型" placeholder="请选择证件类型" v-model="beneficiary.beneficiaryCardType"  value-text-align="left"></popup-picker>
        <x-input title="证件号码" placeholder="请输入证件号" v-model="beneficiary.beneficiaryCardNo" ></x-input>
        <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="beneficiary.beneficiaryCardPeroid" ></x-input>
        <popup-picker title="是被保人的" placeholder="请选择关系" v-model="beneficiary.insuredRelation" value-text-align="left" :data="relates"></popup-picker>
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

  import {PopupRadio, GroupTitle, Group, Cell, XInput, Selector, PopupPicker, Datetime, XNumber, ChinaAddressData, XAddress, XTextarea, XSwitch} from 'vux'
  import storage from "../store/storage";
  import countries from "../../static/countries"

  export default {
    components: {
      PopupRadio,
      Group,
      GroupTitle,
      Cell,
      XInput,
      Selector,
      PopupPicker,
      XAddress,
      Datetime,
      XNumber,
      XTextarea,
      XSwitch
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
        countries: [['中国', '美国', '日本']],
        relates: [["本人", "配偶", "父母", "子女", "兄弟姐妹", "祖父母", "外祖父母", "祖孙", "外祖孙", "其他"]],
        taxRelates: [["仅为中国税收居民", "仅为非居民", "既是中国税收居民又是其他国家（地区）税收居民"]],
        addressData: ChinaAddressData,
        endDate: new Date()
      }
    },
    methods: {
      comeBack() {
        this.$router.back();
      },
      next() {
        this.$router.push("group")
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
            insuredRelation: '',
            beneficiaryOrder: [],
            beneficiaryProportion: ''
          };
          this.beneficiaries.push(beneficiary);
          this.legalBeneficiary = false;
        }
      },
      delBene(index) {
        this.beneficiaries.splice(index, 1);
        if (this.beneficiaries.length === 0) {
          this.legalBeneficiary = !this.legalBeneficiary;
        }
      }
    },
    watch: {
      addressValue: function (val, oldVal) {
        console.log(oldVal)
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
          storage.save('beneficiaries',newVal);
        },
        immediate: true,
        deep: true
      },
      insured: {
        handler(newVal, oldVal) {
          storage.save('insured',newVal);
        },
        immediate: true,
        deep: true
      }
    },
    created:function () {
      console.log(countries);
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

  .i-input .i-input-radio {
    margin-left: 1rem;
  }

  .i-input-radio {
    display: inline-block;
    position: relative;
    top: 10px;
    left: 28px;
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
    color: #2c3e50;
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
</style>
