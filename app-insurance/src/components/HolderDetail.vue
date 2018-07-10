<template>
  <div>
    <div class="title">
      投保人信息
    </div>
    <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model="holder.policyholderName"
               v-bind:class="{'errorInput': $v.holder.policyholderName.$error}"
               @input="$v.holder.policyholderName.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderName.required && $v.holder.policyholderName.$dirty">姓名不能为空</div>
      <div class="error" v-if="!$v.holder.policyholderName.minLength">姓名最少为
        {{$v.holder.policyholderName.$params.minLength.min}}
        个字符
      </div>
      <div class="error" v-if="!$v.holder.policyholderName.maxLength">姓名最多不超过32位数</div>
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
      <x-input title="投保人职业" placeholder="请输入您的职业" v-model="holder.policyholderCareer"
               v-bind:class="{'errorInput': $v.holder.policyholderCareer.$error}"
               @input="$v.holder.policyholderCareer.$touch()"></x-input>
      <!--<popup-picker title="投保人职业" placeholder="请选择职业" value-text-align="left"></popup-picker>-->
      <div class="error" v-if="!$v.holder.policyholderCareer.required && $v.holder.policyholderCareer.$dirty">职业不能为空
      </div>
      <div class="error" v-if="!$v.holder.policyholderCareer.minLength">职业最少为
        {{$v.holder.policyholderCareer.$params.minLength.min}}
        个字符
      </div>
      <div class="error" v-if="!$v.holder.policyholderCareer.maxLength">职业最多不超过32位数</div>

      <datetime title="出生日期" v-model="holder.policyholderBirthday" startDate="1950-01-01" :endDate="startDate"
                placeholder="请选择出生日期"
                value-text-align="left" v-bind:class="{'errorInput': $v.holder.policyholderBirthday.$error}"></datetime>
      <div class="error" v-if="!$v.holder.policyholderBirthday.required && $v.holder.policyholderBirthday.$dirty">
        出生日期不能为空
      </div>

      <popup-picker title="证件类型" placeholder="请选择证件类型" v-model="holder.policyholderCardType" :data="cardTypes"
                    value-text-align="left"
                    v-bind:class="{'errorInput': $v.holder.policyholderCardType.$error}"></popup-picker>
      <div class="error" v-if="!$v.holder.policyholderCardType.required && $v.holder.policyholderCardType.$dirty">
        证件类型不能为空
      </div>

      <x-input title="证件号码" v-model="holder.policyholderCardNo" placeholder="请输入证件号"
               v-bind:class="{'errorInput': $v.holder.policyholderCardNo.$error}"
               @input="$v.holder.policyholderCardNo.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderCardNo.required && $v.holder.policyholderCardNo.$dirty">
        证件号码不能为空
      </div>
      <div class="error" v-if="!$v.holder.policyholderCardNo.idCardVali">请输入正确的证件号码</div>

      <!--<x-input title="证件有效期" v-model="holder.policyholderCardPeroid" placeholder="请选择证件有效期" v-bind:class="{'errorInput': $v.holder.policyholderCardPeroid.$error}"-->
      <!--@input="$v.holder.policyholderCardPeroid.$touch()"></x-input>-->
      <datetime title="证件有效期" v-model="holder.policyholderCardPeroid" :startDate="startDate" endDate="2199-12-30"
                placeholder="请选择证件有效期"
                value-text-align="left"
                v-bind:class="{'errorInput': $v.holder.policyholderCardPeroid.$error}"></datetime>
      <div class="error" v-if="!$v.holder.policyholderCardPeroid.required && $v.holder.policyholderCardPeroid.$dirty">
        证件有效期不能为空
      </div>

      <x-input title="国籍" placeholder="请输入国籍" v-model="holder.policyholderCountry"
               v-bind:class="{'errorInput': $v.holder.policyholderCountry.$error}"
               @input="$v.holder.policyholderCountry.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderCountry.required && $v.holder.policyholderCountry.$dirty">请输入国籍
      </div>
      <div class="error" v-if="!$v.holder.policyholderCountry.maxLength">国籍最多不超过64位数</div>

      <x-input title="身高(cm)" v-model="holder.policyholderHeight" placeholder="请输入身高"
               v-bind:class="{'errorInput': $v.holder.policyholderHeight.$error}"
               @input="$v.holder.policyholderHeight.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderHeight.required && $v.holder.policyholderHeight.$dirty">身高不能为空
      </div>
      <div class="error" v-if="!$v.holder.policyholderHeight.int">请输入正确的身高（正整数）</div>
      <div class="error" v-if="!$v.holder.policyholderHeight.maxLength">最大不超过3位数</div>

      <x-input title="体重(kg)" v-model="holder.policyholderBodyWeight" placeholder="请输入体重"
               v-bind:class="{'errorInput': $v.holder.policyholderBodyWeight.$error}"
               @input="$v.holder.policyholderBodyWeight.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderBodyWeight.required && $v.holder.policyholderBodyWeight.$dirty">
        体重不能为空
      </div>
      <div class="error" v-if="!$v.holder.policyholderBodyWeight.int">请输入正确的体重（正整数）</div>
      <div class="error" v-if="!$v.holder.policyholderBodyWeight.maxLength">最大不超过3位数</div>

      <x-input title="年收入(万元)" v-model="holder.policyholderIncome" placeholder="请输入年收入"
               v-bind:class="{'errorInput': $v.holder.policyholderIncome.$error}"
               @input="$v.holder.policyholderIncome.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderIncome.required && $v.holder.policyholderIncome.$dirty">请输入年收入
      </div>
      <div class="error" v-if="!$v.holder.policyholderIncome.int">请输入年收入，单位万元（正整数）</div>
      <div class="error" v-if="!$v.holder.policyholderIncome.maxLength">最大不超过6位数</div>

      <popup-picker title="婚姻状况" v-model="holder.policyholderMarriage" placeholder="请选择婚姻状况" :data="maritalStatus"
                    value-text-align="left"
                    v-bind:class="{'errorInput': $v.holder.policyholderMarriage.$error}"></popup-picker>
      <div class="error" v-if="!$v.holder.policyholderMarriage.required && $v.holder.policyholderIncome.$dirty">
        婚姻状况不能为空
      </div>
      <div>
        <div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" v-model="holder.policyholderTel" placeholder="请输入固定电话"
               v-bind:class="{'errorInput': $v.holder.policyholderTel.$error}"
               @input="$v.holder.policyholderTel.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderTel.fixedTel">请输入正确的固定电话号码</div>

      <x-input title="移动电话" v-model="holder.policyholderMobile" placeholder="请输入移动电话"
               v-bind:class="{'errorInput': $v.holder.policyholderMobile.$error}"
               @input="$v.holder.policyholderMobile.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderMobile.mobile">请输入正确的手机号码</div>

      <x-input title="E-mail" v-model="holder.policyholderEmail" placeholder="非必填项"
               v-bind:class="{'errorInput': $v.holder.policyholderEmail.$error}"
               @input="$v.holder.policyholderEmail.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderEmail.email">请输入正确邮箱地址</div>

      <x-address title="家庭住址" placeholder="请选择地址" :list="addressData" v-model="holder.holderPCD"
                 value-text-align="left" v-bind:class="{'errorInput': $v.holder.holderPCD.$error}"></x-address>
      <div class="error" v-if="!$v.holder.holderPCD.required && $v.holder.holderPCD.$dirty">家庭住址不能为空</div>

      <x-input title="详细地址" v-model="holder.policyholderAddress" placeholder="请输入详细地址"
               v-bind:class="{'errorInput': $v.holder.policyholderAddress.$error}"
               @input="$v.holder.policyholderAddress.$touch()"></x-input>
      <div class="error" v-if="!$v.holder.policyholderAddress.required && $v.holder.policyholderAddress.$dirty">
        请输入详细地址
      </div>
      <div class="error" v-if="!$v.holder.policyholderAddress.maxLength">详细地址最多不超过255位数</div>

      <popup-picker title="涉税人身份信息" v-model="holder.policyholderTaxRelated" placeholder="请选择涉税人信息" :data="taxRelates"
                    value-text-align="left"
                    v-bind:class="{'errorInput': $v.holder.policyholderTaxRelated.$error}"></popup-picker>
      <div class="error" v-if="!$v.holder.policyholderTaxRelated.required && $v.holder.policyholderTaxRelated.$dirty">
        涉税人信息不能为空
      </div>
    </group>

    <div class="title" style="color: #2c3e50;">
      <div class="title-select" @click="changeLegal">
        <img v-if="legalBeneficiary" src="../assets/img/selected.png"/>
        <img v-if="!legalBeneficiary" src="../assets/img/unselect.png"/>
        受益人：法定受益人
      </div>
      <div class="title-add" @click="addBene">
        <img src="../assets/img/add.png"/>新增受益人
      </div>
    </div>
    <div v-show="addBene1">
      <div class="add">受益人信息 <span style="float: right;color: #c01212;" @click="delBene(1)">删除</span></div>
      <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
        <x-input title="姓名" placeholder="请输入姓名" v-model="beneficiary1.beneficiaryName"
                 v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryName.$error}"
                 @input="$v.beneficiary1.beneficiaryName.$touch()"></x-input>
        <div class="error" v-if="!$v.beneficiary1.beneficiaryName.required && $v.beneficiary1.beneficiaryName.$dirty">
          姓名不能为空
        </div>
        <div class="error" v-if="!$v.beneficiary1.beneficiaryName.minLength">姓名最少为
          {{$v.beneficiary1.beneficiaryName.$params.minLength.min}}
          个字符
        </div>
        <div class="error" v-if="!$v.beneficiary1.beneficiaryName.maxLength">姓名最多不超过32位数</div>

        <div class="i-input">
          <div class="i-input-item">性别</div>
          <div class="i-input-radio">
            <div class="radio-div" @click="beneficiary1.beneficiaryGender = true">
              <span>男</span>
              <img v-if="beneficiary1.beneficiaryGender" src="../assets/img/case-on.png"/>
              <img v-if="!beneficiary1.beneficiaryGender" src="../assets/img/case-off.png"/>
            </div>
            <div class="radio-div" @click="beneficiary1.beneficiaryGender = false">
              <span>女</span>
              <img v-if="!beneficiary1.beneficiaryGender" src="../assets/img/case-on.png"/>
              <img v-if="beneficiary1.beneficiaryGender" src="../assets/img/case-off.png"/>
            </div>
          </div>
        </div>
        <x-input title="国籍" placeholder="请输入国籍" v-model="beneficiary1.beneficiaryCountry"
                 v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCountry.$error}"
                 @input="$v.beneficiary1.beneficiaryCountry.$touch()"
                 value-text-align="left"></x-input>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryCountry.required && $v.beneficiary1.beneficiaryCountry.$dirty">请输入国籍
        </div>
        <div class="error" v-if="!$v.beneficiary1.beneficiaryCountry.maxLength">国籍最多不超过64位数</div>

        <popup-picker title="受益顺序" placeholder="请输入受益顺序" :data="orders" v-model="beneficiary1.beneficiaryOrder"
                      value-text-align="left"
                      v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryOrder.$error}"></popup-picker>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryOrder.required && $v.beneficiary1.beneficiaryOrder.$dirty">受益顺序不能为空
        </div>

        <x-input title="受益份额" placeholder="请输入受益份额" v-model="beneficiary1.beneficiaryProportion"
                 v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryProportion.$error}"
                 @input="$v.beneficiary1.beneficiaryProportion.$touch()"></x-input>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryProportion.required && $v.beneficiary1.beneficiaryProportion.$dirty">
          请输入受益份额
        </div>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryProportion.between && $v.beneficiary1.beneficiaryProportion.$dirty">
          请输入受益份额，在0%到100%之间
        </div>

        <datetime title="出生日期" placeholder="请选择出生日期" startDate="1950-01-01" :endDate="startDate"
                  v-model="beneficiary1.beneficiaryBirthday"
                  v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryBirthday.$error}"
                  value-text-align="left"></datetime>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryBirthday.required && $v.beneficiary1.beneficiaryBirthday.$dirty">出生日期不能为空
        </div>

        <popup-picker title="证件类型" placeholder="请选择证件类型" v-model="beneficiary1.beneficiaryCardType"
                      value-text-align="left" :data="cardTypes"
                      v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardType.$error}"></popup-picker>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryCardType.required && $v.beneficiary1.beneficiaryCardType.$dirty">证件类型不能为空
        </div>

        <x-input title="证件号码" placeholder="请输入证件号" v-model="beneficiary1.beneficiaryCardNo"
                 v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardNo.$error}"
                 @input="$v.beneficiary1.beneficiaryCardNo.$touch()"></x-input>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryCardNo.required && $v.beneficiary1.beneficiaryCardNo.$dirty">请输入证件号
        </div>
        <div class="error" v-if="!$v.beneficiary1.beneficiaryCardNo.idCardVali">请输入正确的身份证号码</div>

        <!--<x-input title="证件有效期" placeholder="请选择证件有效期" v-model="beneficiary1.beneficiaryCardPeroid" v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardPeroid.$error}"-->
        <!--@input="$v.beneficiary1.beneficiaryCardPeroid.$touch()"></x-input>-->
        <datetime title="证件有效期" v-model="beneficiary1.beneficiaryCardPeroid" :startDate="startDate" endDate="2199-12-30"
                  placeholder="请选择证件有效期" v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardPeroid.$error}"
                  value-text-align="left"></datetime>
        <div class="error"
             v-if="!$v.beneficiary1.beneficiaryCardPeroid.required && $v.beneficiary1.beneficiaryCardPeroid.$dirty">
          证件有效期不能为空
        </div>

        <popup-picker title="是被保人的" placeholder="请选择关系" v-model="beneficiary1.insuredRelation" value-text-align="left"
                      :data="relates"
                      v-bind:class="{'errorInput': $v.beneficiary1.insuredRelation.$error}"></popup-picker>
        <div class="error" v-if="!$v.beneficiary1.insuredRelation.required && $v.beneficiary1.insuredRelation.$dirty">
          与被保人的关系不能为空
        </div>
      </group>
    </div>

    <div v-show="addBene2">
      <div class="add">受益人信息 <span style="float: right;color: #c01212;" @click="delBene(2)">删除</span></div>
      <group label-width="7rem" label-margin-right="2em" label-align="left" style="font-size: 15px;">
        <x-input title="姓名" placeholder="请输入姓名" v-model="beneficiary2.beneficiaryName"
                 v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryName.$error}"
                 @input="$v.beneficiary2.beneficiaryName.$touch()"></x-input>
        <div class="error" v-if="!$v.beneficiary2.beneficiaryName.required && $v.beneficiary2.beneficiaryName.$dirty">
          姓名不能为空
        </div>
        <div class="error" v-if="!$v.beneficiary2.beneficiaryName.minLength">姓名最少为
          {{$v.beneficiary2.beneficiaryName.$params.minLength.min}}
          个字符
        </div>
        <div class="error" v-if="!$v.beneficiary2.beneficiaryName.maxLength">姓名最多不超过32位数</div>

        <div class="i-input">
          <div class="i-input-item">性别</div>
          <div class="i-input-radio">
            <div class="radio-div" @click="beneficiary2.beneficiaryGender = true">
              <span>男</span>
              <img v-if="beneficiary2.beneficiaryGender" src="../assets/img/case-on.png"/>
              <img v-if="!beneficiary2.beneficiaryGender" src="../assets/img/case-off.png"/>
            </div>
            <div class="radio-div" @click="beneficiary2.beneficiaryGender = false">
              <span>女</span>
              <img v-if="!beneficiary2.beneficiaryGender" src="../assets/img/case-on.png"/>
              <img v-if="beneficiary2.beneficiaryGender" src="../assets/img/case-off.png"/>
            </div>
          </div>
        </div>
        <x-input title="国籍" placeholder="请输入国籍" v-model="beneficiary2.beneficiaryCountry"
                 value-text-align="left" v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryCountry.$error}"
                 @input="$v.beneficiary2.beneficiaryCountry.$touch()"></x-input>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryCountry.required && $v.beneficiary2.beneficiaryCountry.$dirty">请输入国籍
        </div>
        <div class="error" v-if="!$v.beneficiary2.beneficiaryCountry.maxLength">国籍最多不超过32位数</div>

        <popup-picker title="受益顺序" placeholder="请输入受益顺序" :data="orders" v-model="beneficiary2.beneficiaryOrder"
                      value-text-align="left"
                      v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryOrder.$error}"></popup-picker>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryOrder.required && $v.beneficiary2.beneficiaryOrder.$dirty">受益顺序不能为空
        </div>

        <x-input title="受益份额" placeholder="请输入受益份额" v-model="beneficiary2.beneficiaryProportion"
                 v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryProportion.$error}"
                 @input="$v.beneficiary2.beneficiaryProportion.$touch()"></x-input>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryProportion.required && $v.beneficiary2.beneficiaryProportion.$dirty">
          请输入受益份额
        </div>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryProportion.between && $v.beneficiary2.beneficiaryProportion.$dirty">
          请输入受益份额，在0%到100%之间
        </div>

        <datetime title="出生日期" placeholder="请选择出生日期" startDate="1950-01-01" :endDate="startDate"
                  v-model="beneficiary2.beneficiaryBirthday"
                  v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryBirthday.$error}"
                  value-text-align="left"></datetime>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryBirthday.required && $v.beneficiary2.beneficiaryBirthday.$dirty">出生日期
          不能为空
        </div>

        <popup-picker title="证件类型" placeholder="请选择证件类型" :data="cardTypes" v-model="beneficiary2.beneficiaryCardType"
                      value-text-align="left"
                      v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryCardType.$error}"></popup-picker>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryCardType.required && $v.beneficiary2.beneficiaryCardType.$dirty">
          证件类型不能为空
        </div>

        <x-input title="证件号码" placeholder="请输入证件号" :startDate="startDate"
                 v-model="beneficiary2.beneficiaryCardNo"
                 v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryCardNo.$error}"
                 @input="$v.beneficiary2.beneficiaryCardNo.$touch()"></x-input>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryCardNo.required && $v.beneficiary2.beneficiaryCardNo.$dirty">请输入证件号
        </div>
        <div class="error" v-if="!$v.beneficiary2.beneficiaryCardNo.idCardVali">请输入正确的身份证号码</div>

        <!--<x-input title="证件有效期" placeholder="请选择证件有效期" v-model="beneficiary2.beneficiaryCardPeroid" v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryCardPeroid.$error}"-->
        <!--@input="$v.beneficiary2.beneficiaryCardPeroid.$touch()"></x-input>-->
        <datetime title="证件有效期" v-model="beneficiary2.beneficiaryCardPeroid" endDate="2199-12-30" placeholder="请选择证件有效期"
                  value-text-align="left"
                  v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryCardPeroid.$error}"></datetime>
        <div class="error"
             v-if="!$v.beneficiary2.beneficiaryCardPeroid.required && $v.beneficiary2.beneficiaryCardPeroid.$dirty">
          证件有效期不能为空
        </div>

        <popup-picker title="是被保人的" placeholder="请选择关系" v-model="beneficiary2.insuredRelation" value-text-align="left"
                      :data="relates"
                      v-bind:class="{'errorInput': $v.beneficiary2.insuredRelation.$error}"></popup-picker>
        <div class="error"
             v-if="!$v.beneficiary2.insuredRelation.required && $v.beneficiary2.insuredRelation.$dirty">
          与被保人关系不能为空
        </div>
      </group>
    </div>
    <toast v-model="showPositionValue" type="text" :time="800" is-show-mask position="middle">{{toastText}}</toast>
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

  import {ChinaAddressData, Datetime, Group, PopupPicker, Selector, XAddress, XInput, Toast} from 'vux'
  import storage from "../store/storage";
  import {required, minLength, maxLength, between, helpers, numeric, email} from 'vuelidate/lib/validators'
  import {dateFormat, wipeArray} from "../config/mUtils";
  import {idCardVali, int, fixedTel, mobile} from "../admin/validate";

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
    name: "holder-detail",
    data() {
      return {
        holder: storage.fetch('holder'),
        beneficiary1: storage.fetch('beneficiary1'),
        beneficiary2: storage.fetch('beneficiary2'),
        //是否法定受益人
        legalBeneficiary: true,
        cardTypes: [['居民身份证', '居民户口簿', '军人身份证', '港澳居民往来内地通行证', '出生证', '台湾居民往来内地通行证', '外国护照', '外国人永久居留身份证', '武警身份证', '其他证件']],
        maritalStatus: [['未婚', '已婚', '丧偶', '离婚']],
        relates: [["本人", "配偶", "父母", "子女", "兄弟姐妹", "祖父母", "外祖父母", "祖孙", "外祖孙", "其他"]],
        taxRelates: [["仅为中国税收居民", "仅为非居民", "既是中国税收居民又是其他国家（地区）税收居民"]],
        orders: [['1', '2']],
        addressData: ChinaAddressData,
        countries: this.Admin.countries,
        endDate: new Date(),
        //增加受益人
        addBene1: false,
        addBene2: false,
        //提交表单时校验状态
        submitStatus: null,
        showPositionValue: false,
        toastText: '',
        startDate: dateFormat(new Date(), "yyyy-MM-dd")
      }
    },
    validations: {
      holder: {
        policyholderName: {required, minLength: minLength(2), maxLength: maxLength(32)},
        policyholderCareer: {required, minLength: minLength(2), maxLength: maxLength(32)},
        policyholderBirthday: {required},
        policyholderCardType: {required},
        policyholderCardNo: {required, idCardVali,},
        policyholderCardPeroid: {required},
        policyholderCountry: {required, maxLength: maxLength(64)},
        policyholderHeight: {required, int, maxLength: maxLength(3)},
        policyholderBodyWeight: {required, int, maxLength: maxLength(3)},
        policyholderIncome: {required, int, maxLength: maxLength(6)},
        policyholderMarriage: {required},
        policyholderTel: {fixedTel},
        policyholderMobile: {mobile},
        policyholderEmail: {email},
        holderPCD: {required},
        policyholderAddress: {required, maxLength: maxLength(255)},
        policyholderTaxRelated: {required}
      },
      beneficiary1: {
        beneficiaryName: {required, minLength: minLength(2), maxLength: maxLength(32)},
        beneficiaryCountry: {required, maxLength: maxLength(64)},
        beneficiaryOrder: {required},
        beneficiaryProportion: {required, between: between(0, 100)},
        beneficiaryBirthday: {required},
        beneficiaryCardType: {required},
        beneficiaryCardNo: {required, idCardVali,},
        beneficiaryCardPeroid: {required},
        insuredRelation: {required}
      },
      beneficiary2: {
        beneficiaryName: {required, minLength: minLength(2), maxLength: maxLength(32)},
        beneficiaryCountry: {required, maxLength: maxLength(64)},
        beneficiaryOrder: {required},
        beneficiaryProportion: {required, between: between(0, 100)},
        beneficiaryBirthday: {required},
        beneficiaryCardType: {required},
        beneficiaryCardNo: {required, idCardVali},
        beneficiaryCardPeroid: {required},
        insuredRelation: {required}
      },
    },
    methods: {
      comeBack() {
        this.$router.back();
      },
      next() {
        this.$v.$touch();
        if (this.$v.holder.$invalid) {
          this.submitStatus = 'ERROR';
          this.showPositionValue = true;
          this.toastText = "信息填写有误"
        } else if (this.addBene1) {
          if (this.$v.beneficiary1.$invalid) {
            this.submitStatus = 'ERROR';
            this.showPositionValue = true;
            this.toastText = "信息填写有误"
          } else {
            this.submitStatus = 'PENDING'
            this.$router.push("infoMatters");
            let beneficiary1 = storage.fetch("beneficiary1");

            let beneficiaries = [];
            beneficiaries.push(beneficiary1);
            wipeArray(storage.save("beneficiaries", beneficiaries))
          }
        } else if (this.addBene2) {
          if (this.$v.beneficiary2.$invalid) {
            this.submitStatus = 'ERROR';
            this.showPositionValue = true;
            this.toastText = "信息填写有误"
          }
        } else {
          // do your submit logic here
          let beneficiaries = storage.fetch("beneficiaries");
          let beneficiary2 = storage.fetch("beneficiary2");
          beneficiaries.push(beneficiary2);
          wipeArray(storage.save("beneficiaries", beneficiaries))
          this.submitStatus = 'PENDING'
          this.$router.push("infoMatters");
        }
      },
      addBene() {
        if (this.addBene1 === false) {
          this.addBene1 = true;
          let beneficiary1 = {
            beneficiaryName: '',
            beneficiaryGender: true,
            beneficiaryBirthday: '',
            beneficiaryCardType: [],
            beneficiaryCardNo: '',
            beneficiaryCardPeroid: '',
            insuredRelation: [],
            beneficiaryOrder: [],
            beneficiaryProportion: '',
            beneficiaryCountry: ''
          };
        } else if (this.addBene1 === true && this.addBene2 === false) {
          this.addBene2 = true;
          let beneficiary2 = {
            beneficiaryName: '',
            beneficiaryGender: true,
            beneficiaryBirthday: '',
            beneficiaryCardType: [],
            beneficiaryCardNo: '',
            beneficiaryCardPeroid: '',
            insuredRelation: [],
            beneficiaryOrder: [],
            beneficiaryProportion: '',
            beneficiaryCountry: ''
          };
        } else if (this.addBene1 && this.addBene2) {
          this.toastText = "新增受益人最多为两个";
          this.showPositionValue = true;
        }
        this.legalBeneficiary = false;
        let order = storage.fetch("order");
        order.legalBeneficiary = this.legalBeneficiary;
        storage.save("order", order);
      },
      delBene(index) {
        if (index === 1) {
          this.addBene1 = false;
          this.beneficiary1 = {
            beneficiaryName: '',
            beneficiaryGender: true,
            beneficiaryBirthday: '',
            beneficiaryCardType: [],
            beneficiaryCardNo: '',
            beneficiaryCardPeroid: '',
            insuredRelation: [],
            beneficiaryOrder: [],
            beneficiaryProportion: '',
            beneficiaryCountry: ''
          };
          storage.save('beneficiary1', null);
        } else {
          this.addBene2 = false;
          this.beneficiary2 = {
            beneficiaryName: '',
            beneficiaryGender: true,
            beneficiaryBirthday: '',
            beneficiaryCardType: [],
            beneficiaryCardNo: '',
            beneficiaryCardPeroid: '',
            insuredRelation: [],
            beneficiaryOrder: [],
            beneficiaryProportion: '',
            beneficiaryCountry: ''
          };
          storage.save('beneficiary2', null);
        }

        if (!this.addBene1 && !this.addBene2) {
          this.changeLegal();
        }
      },
      changeLegal() {
        this.legalBeneficiary = !this.legalBeneficiary;
        let order = storage.fetch("order");
        order.legalBeneficiary = this.legalBeneficiary;
        storage.save("order", order);
        if (this.legalBeneficiary) {
          this.addBene1 = false;
          this.addBene2 = false;
        } else {
          this.addBene1 = true;
          this.addBene2 = true;
        }
      }
    },
    watch: {
      holder: {
        handler(newVal, oldVal) {
          if (newVal.holderPCD && newVal.holderPCD.length === 3) {
            newVal.policyholderProvince = this.holder.holderPCD[0];
            newVal.policyholderCity = this.holder.holderPCD[1];
            newVal.policyholderDistrict = this.holder.holderPCD[2];
          }
          storage.save('holder', newVal);
        },
        immediate: true,
        deep: true
      },
      beneficiary1: {
        handler(newVal, oldVal) {
          storage.save('beneficiary1', newVal);
        },
        immediate: true,
        deep: true
      },
      beneficiary2: {
        handler(newVal, oldVal) {
          storage.save('beneficiary2', newVal);
        },
        immediate: true,
        deep: true
      },
    },
    created: function () {
      let order = storage.fetch("order");
      let beneficiary1 = storage.fetch("beneficiary1");
      let beneficiary2 = storage.fetch("beneficiary2");
      if (beneficiary1) {
        this.beneficiary1 = {
          beneficiaryName: '',
          beneficiaryGender: true,
          beneficiaryBirthday: '',
          beneficiaryCardType: [],
          beneficiaryCardNo: '',
          beneficiaryCardPeroid: '',
          insuredRelation: [],
          beneficiaryOrder: [],
          beneficiaryProportion: '',
          beneficiaryCountry: ''
        };
        storage.save('beneficiary1', this.beneficiary1);
      }
      if (beneficiary2) {
        this.beneficiary2 = {
          beneficiaryName: '',
          beneficiaryGender: true,
          beneficiaryBirthday: '',
          beneficiaryCardType: [],
          beneficiaryCardNo: '',
          beneficiaryCardPeroid: '',
          insuredRelation: [],
          beneficiaryOrder: [],
          beneficiaryProportion: '',
          beneficiaryCountry: ''
        };
        storage.save('beneficiary2', this.beneficiary2);
      }
      this.legalBeneficiary = order.legalBeneficiary;
      if (!this.legalBeneficiary) {
        this.addBene1 = true;
        this.addBene2 = true;
      }
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
    }
  }
</script>

<style scoped>
  .title {
    margin: 0 0 10px 0;
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
