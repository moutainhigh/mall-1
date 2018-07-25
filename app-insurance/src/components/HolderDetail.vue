<template>
  <div>
    <div class="title" style="text-align: left;margin-top: 0; font-weight: bold; font-size: 17px">
      投保人信息
    </div>
    <group label-width="6rem" label-align="left">
      <div style="margin-right: 15px">
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
          <div class="i-input-item" style="margin: 13px 0 !important; width: 6rem !important;">性别</div>
          <div class="i-input-radio">
            <div class="radio-div" @click="holder.policyholderGender = true">
              <button v-if="holder.policyholderGender" class="check-on">男</button>
              <button v-if="!holder.policyholderGender" class="check-off">男</button>
            </div>
            <div class="radio-div" @click="holder.policyholderGender = false">
              <button v-if="!holder.policyholderGender" class="check-on" style="margin-left: 15px">女</button>
              <button v-if="holder.policyholderGender" class="check-off" style="margin-left: 15px">女</button>
            </div>
          </div>
        </div>
        <x-input title="投保人职业" placeholder="请选择职业" v-model="holder.careerName"
                 v-bind:class="{'errorInput': $v.holder.careerName.$error}"
                 @input="$v.holder.careerName.$touch()"></x-input>
        <div style="position:absolute;width: 100%;height: 42px;margin-top: -42px;" @click="goToSelect"></div>
        <div class="error" v-if="!$v.holder.careerName.required && $v.holder.careerName.$dirty">职业不能为空
        </div>
        <div class="error" v-if="!$v.holder.careerName.minLength">职业最少为
          {{$v.holder.careerName.$params.minLength.min}}
          个字符
        </div>

        <datetime title="出生日期" v-model="holder.policyholderBirthday" startDate="1950-01-01" :endDate="startDate"
                  placeholder="请选择出生日期"
                  value-text-align="left"
                  v-bind:class="{'errorInput': $v.holder.policyholderBirthday.$error}"></datetime>
        <div class="error" v-if="!$v.holder.policyholderBirthday.required && $v.holder.policyholderBirthday.$dirty">
          出生日期不能为空
        </div>

        <x-input title="证件类型" v-model="holder.policyholderCardType"
                 v-bind:class="{'errorInput': $v.holder.policyholderCardType.$error}"
                 @input="$v.holder.policyholderCardType.$touch()" readonly></x-input>
        <div class="error" v-if="!$v.holder.policyholderCardType.required && $v.holder.policyholderCardType.$dirty">
          证件类型不能为空
        </div>

        <x-input title="证件号码" v-model="holder.policyholderCardNo" placeholder="请输入证件号"
                 v-bind:class="{'errorInput': $v.holder.policyholderCardNo.$error}"
                 @input="$v.holder.policyholderCardNo.$touch()" readonly></x-input>
        <div class="error" v-if="!$v.holder.policyholderCardNo.required && $v.holder.policyholderCardNo.$dirty">
          证件号码不能为空
        </div>
        <div class="error" v-if="!$v.holder.policyholderCardNo.vali">请输入正确的证件号码</div>
        <div class="error" v-if="!$v.holder.policyholderCardNo.maxLength">证件号码最多不超过32位数</div>

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


        <div v-bind:class="{'errorInput': $v.holder.policyholderHeight.$error}">
          <div class="input-ver">
            <x-input title="身高" v-model="holder.policyholderHeight" placeholder="请输入身高"
                     class="input-ver-x"
                     @input="$v.holder.policyholderHeight.$touch()"></x-input>
            <div class="input-vile">cm</div>
          </div>
        </div>
        <div class="error" v-if="!$v.holder.policyholderHeight.required && $v.holder.policyholderHeight.$dirty">身高不能为空
        </div>
        <div class="error" v-if="!$v.holder.policyholderHeight.int">请输入正确的身高（正整数）</div>
        <div class="error" v-if="!$v.holder.policyholderHeight.maxLength">最大不超过3位数</div>

        <div v-bind:class="{'errorInput': $v.holder.policyholderBodyWeight.$error}">
          <div class="input-ver">
            <x-input title="体重" v-model="holder.policyholderBodyWeight" placeholder="请输入体重" class="input-ver-x"
                     @input="$v.holder.policyholderBodyWeight.$touch()"></x-input>
            <div class="input-vile">kg</div>
          </div>
        </div>

        <div class="error" v-if="!$v.holder.policyholderBodyWeight.required && $v.holder.policyholderBodyWeight.$dirty">
          体重不能为空
        </div>
        <div class="error" v-if="!$v.holder.policyholderBodyWeight.int">请输入正确的体重（正整数）</div>
        <div class="error" v-if="!$v.holder.policyholderBodyWeight.maxLength">最大不超过3位数</div>

        <div v-bind:class="{'errorInput': $v.holder.policyholderIncome.$error}">
          <div class="input-ver">
            <x-input title="年收入" v-model="holder.policyholderIncome" placeholder="请输入年收入" class="input-ver-x"
                     @input="$v.holder.policyholderIncome.$touch()"></x-input>
            <div class="input-vile">万元</div>
          </div>
        </div>

        <div class="error" v-if="!$v.holder.policyholderIncome.required && $v.holder.policyholderIncome.$dirty">请输入年收入
        </div>
        <div class="error" v-if="!$v.holder.policyholderIncome.int">请输入年收入，单位万元（正整数）</div>
        <div class="error" v-if="!$v.holder.policyholderIncome.maxLength">最大不超过6位数</div>

        <popup-picker id="marriage" title="婚姻状况" v-model="holder.policyholderMarriage" placeholder="请选择婚姻状况"
                      :data="maritalStatus"
                      value-text-align="left"
                      v-bind:class="{'errorInput': $v.holder.policyholderMarriage.$error}"></popup-picker>
        <div class="error" v-if="!$v.holder.policyholderMarriage.required && $v.holder.policyholderMarriage.$dirty">
          婚姻状况不能为空
        </div>
        <!--<div style="background-color: #f5f5f5">-->
        <!--<div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 13px;padding: 10px 10px 10px 0;color: #888;">-->
        <!--温馨提示：固定电话与移动电话可任填其中一项-->
        <!--</div>-->
        <!--</div>-->
        <x-input title="固定电话" v-model="holder.policyholderTel" placeholder="请输入固定电话"
                 v-bind:class="{'errorInput': $v.holder.policyholderTel.$error}"
                 @input="$v.holder.policyholderTel.$touch()"></x-input>
        <div class="error" v-if="!$v.holder.policyholderTel.fixedTel">请输入正确的固定电话号码</div>
        <div class="error" v-if="!$v.holder.policyholderTel.maxLength">固定电话最多不超过15位数</div>

        <x-input title="移动电话" v-model="holder.policyholderMobile" placeholder="请输入移动电话"
                 v-bind:class="{'errorInput': $v.holder.policyholderMobile.$error}"
                 @input="$v.holder.policyholderMobile.$touch()" readonly></x-input>
        <div class="error" v-if="!$v.holder.policyholderMobile.mobile">请输入正确的手机号码</div>

        <x-input title="E-mail" v-model="holder.policyholderEmail" placeholder="请输入邮箱地址（选填）"
                 v-bind:class="{'errorInput': $v.holder.policyholderEmail.$error}"
                 @input="$v.holder.policyholderEmail.$touch()"></x-input>
        <div class="error" v-if="!$v.holder.policyholderEmail.mail">请输入正确邮箱地址</div>
      </div>

      <div class="address" @click="holder.unifyAddr = !holder.unifyAddr">
        <img src="../assets/img/unselect.png" v-if="!holder.unifyAddr"/>
        <img src="../assets/img/selected.png" v-if="holder.unifyAddr"/>
        与被保人同一地址
      </div>

      <div style="margin-right: 15px">
        <div v-if="!this.holder.unifyAddr">
          <x-address v-if="!holder.unifyAddr" title="家庭住址" placeholder="请选择地址" :list="addressData"
                     v-model="holder.holderPCD"
                     value-text-align="left" v-bind:class="{'errorInput': $v.holder.holderPCD.$error}"></x-address>
          <div class="error" v-if="!$v.holder.holderPCD.required && $v.holder.holderPCD.$dirty">家庭住址不能为空</div>

          <x-input v-if="!holder.unifyAddr" title="详细地址" v-model="holder.policyholderAddress" placeholder="请输入详细地址"
                   v-bind:class="{'errorInput': $v.holder.policyholderAddress.$error && !holder.unifyAddr}"
                   @input="$v.holder.policyholderAddress.$touch()"></x-input>
          <div class="error"
               v-if="!$v.holder.policyholderAddress.required && $v.holder.policyholderAddress.$dirty && !holder.unifyAddr">
            请输入详细地址
          </div>
          <div class="error" v-if="!$v.holder.policyholderAddress.maxLength">详细地址最多不超过255位数</div>
        </div>

        <popup-picker id="relation" title="涉税人信息" v-model="holder.policyholderTaxRelated" placeholder="请选择涉税人信息"
                      :data="taxRelates"
                      value-text-align="left"
                      v-bind:class="{'errorInput': $v.holder.policyholderTaxRelated.$error, 'taxRelated': this.holder.unifyAddr}"></popup-picker>
        <div class="error" v-if="!$v.holder.policyholderTaxRelated.required && $v.holder.policyholderTaxRelated.$dirty">
          涉税人信息不能为空
        </div>
      </div>
    </group>

    <div class="title" style="color: #2c3e50; padding: 10px">
      <div class="title-select" @click="changeLegal">
        <img v-if="legalBeneficiary" src="../assets/img/selected.png"/>
        <img v-if="!legalBeneficiary" src="../assets/img/unselect.png"/>
        受益人：法定受益人
      </div>
      <div style="width: 100%; height: 1px; background: #eeeeee; margin-top: 10px"></div>
    </div>
    <div v-show="addBene1">
      <div id="addBene1" class="add">受益人信息 <span style="float: right;color: #666;" @click="delBene(1)">删除</span>
      </div>
      <group label-width="6rem" label-align="left" style="font-size: 15px;">
        <div style="margin-right: 15px">
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
            <div class="i-input-item" style="margin: 13px 0 !important; width: 6rem !important;">性别</div>
            <div class="i-input-radio">
              <div class="radio-div" @click="beneficiary1.beneficiaryGender = true">
                <button v-if="beneficiary1.beneficiaryGender" class="check-on">男</button>
                <button v-if="!beneficiary1.beneficiaryGender" class="check-off">男</button>
              </div>
              <div class="radio-div" @click="beneficiary1.beneficiaryGender = false">
                <button v-if="!beneficiary1.beneficiaryGender" class="check-on" style="margin-left: 15px">女</button>
                <button v-if="beneficiary1.beneficiaryGender" class="check-off" style="margin-left: 15px">女</button>
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

          <popup-picker id="order1" title="受益顺序" placeholder="请选择受益顺序" :data="orders"
                        v-model="beneficiary1.beneficiaryOrder"
                        value-text-align="left"
                        v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryOrder.$error || (valiOrderB1 === valiOrderB2 && addBene2)}"></popup-picker>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryOrder.required && $v.beneficiary1.beneficiaryOrder.$dirty">受益顺序不能为空
          </div>
          <div class="error"
               v-if="valiOrderB1 === valiOrderB2 && addBene2">受益顺序不正确
          </div>

          <x-input title="受益份额" placeholder="请输入受益份额" v-model="beneficiary1.beneficiaryProportion"
                   v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryProportion.$error || valiProportion}"
                   @input="$v.beneficiary1.beneficiaryProportion.$touch()"></x-input>
          <div class="input-vile">%</div>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryProportion.required && $v.beneficiary1.beneficiaryProportion.$dirty">
            请输入受益份额
          </div>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryProportion.between && $v.beneficiary1.beneficiaryProportion.$dirty">
            请输入受益份额，在1%到100%之间
          </div>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryProportion.numeric && $v.beneficiary1.beneficiaryProportion.$dirty">
            请输入整数
          </div>
          <div class="error" v-if="valiProportion">请重新分配受益份额</div>

          <datetime title="出生日期" placeholder="请选择出生日期" startDate="1950-01-01" :endDate="startDate"
                    v-model="beneficiary1.beneficiaryBirthday"
                    v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryBirthday.$error}"
                    value-text-align="left"></datetime>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryBirthday.required && $v.beneficiary1.beneficiaryBirthday.$dirty">
            出生日期不能为空
          </div>

          <popup-picker id="cardType1" title="证件类型" placeholder="请选择证件类型" v-model="beneficiary1.beneficiaryCardType"
                        value-text-align="left" :data="cardTypes"
                        v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardType.$error}"></popup-picker>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryCardType.required && $v.beneficiary1.beneficiaryCardType.$dirty">
            证件类型不能为空
          </div>

          <x-input title="证件号码" placeholder="请输入证件号" v-model="beneficiary1.beneficiaryCardNo"
                   v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardNo.$error}"
                   @input="$v.beneficiary1.beneficiaryCardNo.$touch()"></x-input>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryCardNo.required && $v.beneficiary1.beneficiaryCardNo.$dirty">请输入证件号
          </div>
          <div class="error" v-if="!$v.beneficiary1.beneficiaryCardNo.vali">请输入正确的证件号码</div>
          <div class="error" v-if="!$v.beneficiary1.beneficiaryCardNo.maxLength">证件号码最多不超过32位数</div>

          <!--<x-input title="证件有效期" placeholder="请选择证件有效期" v-model="beneficiary1.beneficiaryCardPeroid" v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardPeroid.$error}"-->
          <!--@input="$v.beneficiary1.beneficiaryCardPeroid.$touch()"></x-input>-->
          <datetime title="证件有效期" v-model="beneficiary1.beneficiaryCardPeroid" :startDate="startDate"
                    endDate="2199-12-30"
                    placeholder="请选择证件有效期" v-bind:class="{'errorInput': $v.beneficiary1.beneficiaryCardPeroid.$error}"
                    value-text-align="left"></datetime>
          <div class="error"
               v-if="!$v.beneficiary1.beneficiaryCardPeroid.required && $v.beneficiary1.beneficiaryCardPeroid.$dirty">
            证件有效期不能为空
          </div>

          <popup-picker id="relation1" title="是被保人的" placeholder="请选择关系" v-model="beneficiary1.insuredRelation"
                        value-text-align="left"
                        :data="relates"
                        v-bind:class="{'errorInput': $v.beneficiary1.insuredRelation.$error}"></popup-picker>
          <div class="error" v-if="!$v.beneficiary1.insuredRelation.required && $v.beneficiary1.insuredRelation.$dirty">
            与被保人的关系不能为空
          </div>
        </div>
      </group>
    </div>

    <div v-show="addBene2">
      <div id="addBene2" class="add">受益人信息 <span style="float: right;color: #666;" @click="delBene(2)">删除</span>
      </div>
      <group label-width="6rem" label-align="left" style="font-size: 15px;">
        <div style="margin-right: 15px">
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
            <div class="i-input-item" style="margin: 13px 0 !important; width: 6rem !important;">性别</div>
            <div class="i-input-radio">
              <div class="radio-div" @click="beneficiary2.beneficiaryGender = true">
                <button v-if="beneficiary2.beneficiaryGender" class="check-on">男</button>
                <button v-if="!beneficiary2.beneficiaryGender" class="check-off">男</button>
              </div>
              <div class="radio-div" @click="beneficiary2.beneficiaryGender = false">
                <button v-if="!beneficiary2.beneficiaryGender" class="check-on" style="margin-left: 15px">女</button>
                <button v-if="beneficiary2.beneficiaryGender" class="check-off" style="margin-left: 15px">女</button>
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

          <popup-picker id="order2" title="受益顺序" placeholder="请选择受益顺序" :data="orders"
                        v-model="beneficiary2.beneficiaryOrder"
                        value-text-align="left"
                        v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryOrder.$error || (valiOrderB1 === valiOrderB2 && addBene1)}"></popup-picker>
          <div class="error"
               v-if="!$v.beneficiary2.beneficiaryOrder.required && $v.beneficiary2.beneficiaryOrder.$dirty">受益顺序不能为空
          </div>
          <div class="error"
               v-if="valiOrderB1 === valiOrderB2 && addBene1">受益顺序不正确
          </div>

          <x-input title="受益份额" placeholder="请输入受益份额" v-model="beneficiary2.beneficiaryProportion"
                   v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryProportion.$error || valiProportion}"
                   @input="$v.beneficiary2.beneficiaryProportion.$touch()"></x-input>
          <div class="input-vile">%</div>
          <div class="error"
               v-if="!$v.beneficiary2.beneficiaryProportion.required && $v.beneficiary2.beneficiaryProportion.$dirty">
            请输入受益份额
          </div>
          <div class="error"
               v-if="!$v.beneficiary2.beneficiaryProportion.between && $v.beneficiary2.beneficiaryProportion.$dirty">
            请输入受益份额，在1%到100%之间
          </div>
          <div class="error"
               v-if="!$v.beneficiary2.beneficiaryProportion.numeric && $v.beneficiary2.beneficiaryProportion.$dirty">
            请输入整数
          </div>
          <div class="error" v-if="valiProportion">请重新分配受益份额</div>

          <datetime title="出生日期" placeholder="请选择出生日期" startDate="1950-01-01" :endDate="startDate"
                    v-model="beneficiary2.beneficiaryBirthday"
                    v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryBirthday.$error}"
                    value-text-align="left"></datetime>
          <div class="error"
               v-if="!$v.beneficiary2.beneficiaryBirthday.required && $v.beneficiary2.beneficiaryBirthday.$dirty">出生日期
            不能为空
          </div>

          <popup-picker id="cardType2" title="证件类型" placeholder="请选择证件类型" :data="cardTypes"
                        v-model="beneficiary2.beneficiaryCardType"
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
          <div class="error" v-if="!$v.beneficiary2.beneficiaryCardNo.vali">请输入正确的证件号码</div>
          <div class="error" v-if="!$v.beneficiary2.beneficiaryCardNo.maxLength">证件号码最多不超过32位数</div>

          <!--<x-input title="证件有效期" placeholder="请选择证件有效期" v-model="beneficiary2.beneficiaryCardPeroid" v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryCardPeroid.$error}"-->
          <!--@input="$v.beneficiary2.beneficiaryCardPeroid.$touch()"></x-input>-->
          <datetime title="证件有效期" v-model="beneficiary2.beneficiaryCardPeroid" endDate="2199-12-30"
                    :startDate="startDate"
                    placeholder="请选择证件有效期"
                    value-text-align="left"
                    v-bind:class="{'errorInput': $v.beneficiary2.beneficiaryCardPeroid.$error}"></datetime>
          <div class="error"
               v-if="!$v.beneficiary2.beneficiaryCardPeroid.required && $v.beneficiary2.beneficiaryCardPeroid.$dirty">
            证件有效期不能为空
          </div>

          <popup-picker id="relation2" title="是被保人的" placeholder="请选择关系" v-model="beneficiary2.insuredRelation"
                        value-text-align="left"
                        :data="relates"
                        v-bind:class="{'errorInput': $v.beneficiary2.insuredRelation.$error}"></popup-picker>
          <div class="error"
               v-if="!$v.beneficiary2.insuredRelation.required && $v.beneficiary2.insuredRelation.$dirty">
            与被保人关系不能为空
          </div>
          <div style="height: 10px; width: 100%"></div>
        </div>
      </group>
    </div>
    <div v-if="insuredSixteenYear && holderSixteenYear">
      <div class="title-add" @click="addBene" v-if="!addBene1 || !addBene2">
        <div>
          <img src="../assets/img/add.png"/>新增受益人
        </div>
      </div>
    </div>
    <div style="width: 100%; height: 5px; background: #fff"></div>
    <toast v-model="showPositionValue" type="text" :time="800" is-show-mask position="middle">{{toastText}}</toast>
    <!--<div style="height: 50px;">-->
    <!--<button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;color: #f5ca1d" @click="comeBack">-->
    <!--<div>上一步</div>-->
    <!--</button>-->
    <!--<button class="i-footer" style="width: 50%;right: 0" @click="next">-->
    <!--<div>下一步</div>-->
    <!--</button>-->
    <!--</div>-->
    <div style="height: 70px;">
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
  import {required, minLength, maxLength, between, helpers, numeric} from 'vuelidate/lib/validators'
  import {dateFormat, wipeArray} from "../config/mUtils";
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
    mail,
    emoji
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
        startDate: dateFormat(new Date(), "yyyy-MM-dd"),
        valiHolder: '',
        valiBene1: '',
        valiBene2: '',
        valiOrderB1: 1,
        valiOrderB2: null,
        valiProportion: false,
        valiProportion2: false,
        insuredSixteenYear: true,
        holderSixteenYear: true
      }
    },
    validations() {
      if (this.holder.unifyAddr) {
        return {
          holder: {
            policyholderName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            careerName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            policyholderBirthday: {required},
            policyholderCardType: {required},
            policyholderCardNo: {required, vali: this.valiHolder, maxLength: maxLength(32)},
            policyholderCardPeroid: {required},
            policyholderCountry: {required, maxLength: maxLength(64)},
            policyholderHeight: {required, int, maxLength: maxLength(3)},
            policyholderBodyWeight: {required, int, maxLength: maxLength(3)},
            policyholderIncome: {required, int, maxLength: maxLength(6)},
            policyholderMarriage: {required},
            policyholderTel: {fixedTel, maxLength: maxLength(15)},
            policyholderMobile: {mobile},
            policyholderEmail: {mail},
            // holderPCD: {required},
            // policyholderAddress: {required, maxLength: maxLength(255)},
            policyholderTaxRelated: {required}
          },
          beneficiary1: {
            beneficiaryName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            beneficiaryCountry: {required, maxLength: maxLength(64)},
            beneficiaryOrder: {required},
            beneficiaryProportion: {required, between: between(1, 100), numeric},
            beneficiaryBirthday: {required},
            beneficiaryCardType: {required},
            beneficiaryCardNo: {required, vali: this.valiBene1, maxLength: maxLength(32)},
            beneficiaryCardPeroid: {required},
            insuredRelation: {required}
          },
          beneficiary2: {
            beneficiaryName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            beneficiaryCountry: {required, maxLength: maxLength(64)},
            beneficiaryOrder: {required},
            beneficiaryProportion: {required, between: between(1, 100), numeric},
            beneficiaryBirthday: {required},
            beneficiaryCardType: {required},
            beneficiaryCardNo: {required, vali: this.valiBene2, maxLength: maxLength(32)},
            beneficiaryCardPeroid: {required},
            insuredRelation: {required}
          },
        }
      } else {
        return {
          holder: {
            policyholderName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            careerName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            policyholderBirthday: {required},
            policyholderCardType: {required},
            policyholderCardNo: {required, vali: this.valiHolder, maxLength: maxLength(32)},
            policyholderCardPeroid: {required},
            policyholderCountry: {required, maxLength: maxLength(64)},
            policyholderHeight: {required, int, maxLength: maxLength(3)},
            policyholderBodyWeight: {required, int, maxLength: maxLength(3)},
            policyholderIncome: {required, int, maxLength: maxLength(6)},
            policyholderMarriage: {required},
            policyholderTel: {fixedTel, maxLength: maxLength(15)},
            policyholderMobile: {mobile},
            policyholderEmail: {mail},
            holderPCD: {required},
            policyholderAddress: {required, maxLength: maxLength(255)},
            policyholderTaxRelated: {required}
          },
          beneficiary1: {
            beneficiaryName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            beneficiaryCountry: {required, maxLength: maxLength(64)},
            beneficiaryOrder: {required},
            beneficiaryProportion: {required, between: between(1, 100), numeric},
            beneficiaryBirthday: {required},
            beneficiaryCardType: {required},
            beneficiaryCardNo: {required, vali: this.valiBene1, maxLength: maxLength(32)},
            beneficiaryCardPeroid: {required},
            insuredRelation: {required}
          },
          beneficiary2: {
            beneficiaryName: {required, minLength: minLength(2), maxLength: maxLength(32)},
            beneficiaryCountry: {required, maxLength: maxLength(64)},
            beneficiaryOrder: {required},
            beneficiaryProportion: {required, between: between(1, 100), numeric},
            beneficiaryBirthday: {required},
            beneficiaryCardType: {required},
            beneficiaryCardNo: {required, vali: this.valiBene2, maxLength: maxLength(32)},
            beneficiaryCardPeroid: {required},
            insuredRelation: {required}
          },
        }
      }

    },
    methods: {
      comeBack() {
        this.$router.back();
      },
      next() {
        //判断表情包输入
        let input = document.getElementsByTagName("input");
        for (let i = 0; i < input.length; i++) {
          if (emoji.test(input[i].value)) {
            alert("输入信息不得带表情");
            return false;
          }
        }

        if (this.holder.unifyAddr) {
          let insured = storage.fetch("insured");
          let holder = storage.fetch("holder");
          holder.policyholderProvince = insured.insuredPCD[0];
          holder.policyholderCity = insured.insuredPCD[1];
          holder.policyholderDistrict = insured.insuredPCD[2];
          holder.policyholderAddress = insured.insuredAddress;
          storage.save("holder", holder);
        }

        this.$v.$touch();
        if (this.holder.policyholderTel === '' && this.holder.policyholderMobile === '') {
          this.showPositionValue = true;
          this.toastText = "请填写固定电话或手机号码";
          return false;
        }

        //判断受益份额不为100时不通过
        if (!this.legalBeneficiary) {
          if (this.beneficiary2.beneficiaryProportion === '' && parseInt(this.beneficiary1.beneficiaryProportion) !== 100) {
            this.submitStatus = 'ERROR';
            this.showPositionValue = true;
            this.toastText = "受益份额不足100";
            return false;
          }
          if (this.beneficiary1.beneficiaryProportion === '' && parseInt(this.beneficiary2.beneficiaryProportion) !== 100) {
            this.submitStatus = 'ERROR';
            this.showPositionValue = true;
            this.toastText = "受益份额不足100";
            return false;
          }
        }
        //holder验证不通过则返回
        if (this.$v.holder.$invalid) {
          this.submitStatus = 'ERROR';
          this.showPositionValue = true;
          this.toastText = "信息填写有误";
          return false;
        }
        let beneficiaries = [];
        //addBene1为true时保存beneficiary1进数组
        if (this.addBene1) {
          if (this.$v.beneficiary1.$invalid) {
            this.submitStatus = 'ERROR';
            this.showPositionValue = true;
            this.toastText = "信息填写有误";
            return false;
          } else {
            let beneficiary1 = storage.fetch("beneficiary1");
            beneficiaries.push(beneficiary1);
            wipeArray(storage.save("beneficiaries", beneficiaries))
          }
        }
        //addBene2为true时保存beneficiary2进数组
        if (this.addBene2) {
          if (this.$v.beneficiary2.$invalid) {
            this.submitStatus = 'ERROR';
            this.showPositionValue = true;
            this.toastText = "信息填写有误";
            return false;
          } else {
            let beneficiary2 = storage.fetch("beneficiary2");
            beneficiaries.push(beneficiary2);
            wipeArray(storage.save("beneficiaries", beneficiaries));
          }
        }
        //验证通过跳转页面
        this.submitStatus = 'PENDING';
        this.$router.push("infoMatters");
      },
      addBene() {
        if (this.addBene1 === false) {
          this.addBene1 = true;
          if (storage.fetch('beneficiary1') instanceof Array || storage.fetch('beneficiary1') == null) {
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
            storage.save("beneficiary1", beneficiary1);
          }
          document.getElementById("addBene1").scroll(0, 0);
        } else if (this.addBene1 === true && this.addBene2 === false) {
          this.addBene2 = true;
          if (storage.fetch('beneficiary2') instanceof Array || storage.fetch('beneficiary2') == null) {
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
            storage.save("beneficiary2", beneficiary2);
          }
          document.getElementById("addBene2").scroll(0, 0);
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
        if (!this.insuredSixteenYear || !this.holderSixteenYear) {
          this.legalBeneficiary = true;
          this.showPositionValue = true;
          this.toastText = "当被保人或投保人小于16周岁时，受益人只能为法定受益人";
          return false;
        }
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
      },
      goToSelect() {
        this.$router.push({
          path: '/careerSelect',
          query: {
            type: 'holder',
            key: 'policyholderCareer'
          }
        })
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
          if (newVal.policyholderCardType) {
            switch (newVal.policyholderCardType[0]) {
              case '居民身份证' :
                this.valiHolder = idCardVali;
                break;
              case '居民户口簿' :
                this.valiHolder = householdVali;
                break;
              case '军人身份证' :
                this.valiHolder = idCardVali;
                break;
              case '港澳居民往来内地通行证' :
                this.valiHolder = hkmcPassVali;
                break;
              case '出生证' :
                this.valiHolder = birthVali;
                break;
              case '台湾居民往来内地通行证' :
                this.valiHolder = taiwanPassVali;
                break;
              case '外国护照' :
                this.valiHolder = passportVali;
                break;
              case '外国人永久居留身份证' :
                this.valiHolder = permanentResidenceVali;
                break;
              case '武警身份证' :
                this.valiHolder = idCardVali;
                break;
              case '其他证件' :
                this.valiHolder = '';
                break;
            }
          }
          storage.save('holder', newVal);
          //判断投保人是否小于16周岁
          if (newVal.policyholderBirthday !== '') {
            let newDate = new Date();
            let birthday = new Date(newVal.policyholderBirthday.replace(/-/, "/"));
            let time = (newDate.valueOf() - birthday.valueOf());
            //如果小于16周岁，设为false
            if (time < 24 * 60 * 60 * 1000 * 365 * 16) {
              this.holderSixteenYear = false;
            } else {
              this.holderSixteenYear = true;
            }
            if (!this.insuredSixteenYear || !this.holderSixteenYear) {
              this.legalBeneficiary = true;
            }
            if (this.legalBeneficiary) {
              this.addBene1 = false;
              this.addBene2 = false;
            } else {
              this.addBene1 = true;
              this.addBene2 = true;
            }
          }
        },
        immediate: true,
        deep: true
      },
      beneficiary1: {
        handler(newVal, oldVal) {
          console.log(newVal)
          if (newVal.beneficiaryCardType) {
            switch (newVal.beneficiaryCardType[0]) {
              case '居民身份证' :
                this.valiBene1 = idCardVali;
                break;
              case '居民户口簿' :
                this.valiBene1 = householdVali;
                break;
              case '军人身份证' :
                this.valiBene1 = idCardVali;
                break;
              case '港澳居民往来内地通行证' :
                this.valiBene1 = hkmcPassVali;
                break;
              case '出生证' :
                this.valiBene1 = birthVali;
                break;
              case '台湾居民往来内地通行证' :
                this.valiBene1 = taiwanPassVali;
                break;
              case '外国护照' :
                this.valiBene1 = passportVali;
                break;
              case '外国人永久居留身份证' :
                this.valiBene1 = permanentResidenceVali;
                break;
              case '武警身份证' :
                this.valiBene1 = idCardVali;
                break;
              case '其他证件' :
                this.valiBene1 = '';
                break;
            }
          }
          //判断受益顺序
          if (newVal.beneficiaryOrder) {
            if (newVal.beneficiaryOrder[0] === "1") {
              this.valiOrderB2 = 2;
            }
            if (newVal.beneficiaryOrder[0] === "2") {
              this.valiOrderB2 = 1
            }
          }
          //判断受益份额
          if (newVal.beneficiaryProportion) {
            console.log(newVal.beneficiaryProportion)
            if (parseInt(newVal.beneficiaryProportion) + parseInt(this.beneficiary2.beneficiaryProportion) === 100 || this.beneficiary2.beneficiaryProportion === '') {
              this.valiProportion = false;
            } else {
              this.valiProportion = true;
            }
          }
          storage.save('beneficiary1', newVal);
        },
        immediate: true,
        deep: true
      },
      beneficiary2: {
        handler(newVal, oldVal) {
          if (newVal.beneficiaryCardType) {
            switch (newVal.beneficiaryCardType[0]) {
              case '居民身份证' :
                this.valiBene2 = idCardVali;
                break;
              case '居民户口簿' :
                this.valiBene2 = householdVali;
                break;
              case '军人身份证' :
                this.valiBene2 = idCardVali;
                break;
              case '港澳居民往来内地通行证' :
                this.valiBene2 = hkmcPassVali;
                break;
              case '出生证' :
                this.valiBene2 = birthVali;
                break;
              case '台湾居民往来内地通行证' :
                this.valiBene2 = taiwanPassVali;
                break;
              case '外国护照' :
                this.valiBene2 = passportVali;
                break;
              case '外国人永久居留身份证' :
                this.valiBene2 = permanentResidenceVali;
                break;
              case '武警身份证' :
                this.valiBene2 = idCardVali;
                break;
              case '其他证件' :
                this.valiBene2 = '';
                break;
            }
          }
          //判断受益顺序
          if (newVal.beneficiaryOrder) {
            if (newVal.beneficiaryOrder[0] === "1") {
              this.valiOrderB1 = 2;
            }
            if (newVal.beneficiaryOrder[0] === "2") {
              this.valiOrderB1 = 1;
            }
          }
          //判断受益份额
          if (newVal.beneficiaryProportion) {
            if (parseInt(newVal.beneficiaryProportion) + parseInt(this.beneficiary1.beneficiaryProportion) === 100 || this.beneficiary1.beneficiaryProportion === '') {
              this.valiProportion = false;
            } else {
              this.valiProportion = true;
            }
          }
          storage.save('beneficiary2', newVal);
        },
        immediate: true,
        deep: true
      },
      unifyAddr: function (newVal, oldVal) {
        if (newVal) {
          let holder = storage.fetch("holder");

        }
      }
    },
    created: function () {
      let order = storage.fetch("order");
      let beneficiary1 = storage.fetch("beneficiary1");
      let beneficiary2 = storage.fetch("beneficiary2");
      if (beneficiary1 instanceof Array) {
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
      if (beneficiary2 instanceof Array) {
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
      //判断被保人周岁是否大于16周岁
      let insured = storage.fetch("insured");
      let newDate = new Date();
      let birthday = new Date(insured.insuredBirthday.replace(/-/, "/"));
      let time = (newDate.valueOf() - birthday.valueOf());
      //如果小于16周岁，设为false
      if (time < 24 * 60 * 60 * 1000 * 365 * 16) {
        this.insuredSixteenYear = false;
        this.legalBeneficiary = true;
      } else {
        this.insuredSixteenYear = true;
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
    },
    activated() {
      this.holder = storage.fetch("holder");
    }
  }
</script>

<style scoped>
  .title {
    margin-top: 10px;
    background-color: #ffffff;
    padding: 15px;
    font-size: 16px;
    color: #f5ca1d;
    text-align: center;
  }

  .i-input-radio {
    display: inline-block;
    position: relative;
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
    border-bottom: #ececec solid 1px;
    border-top: #ececec solid 1px;
    margin-left: 15px;
    margin-bottom: -1px;
  }

  .i-input-item {
    font-size: 16px !important;
    width: 7rem !important;
    margin: 13px 2em 13px 0 !important;
  }

  .title-select {
    font-size: 16px;
    display: inline-block;
  }

  .title-select img {
    width: 20px;
    position: relative;
    top: 4px;
    margin-right: 10px;
  }

  .title-add {
    padding: 10px 0;
    text-align: center;
    font-size: 17px;
    color: #f5ca1d;
    background-color: #ffffff;
  }

  .title-add div {
    width: 240px;
    display: inline-block;
    border: #f5ca1d solid 1px;
    border-radius: 20px;
    padding: 6px 0;
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
    font-size: 16px;
    color: #f5ca1d;
  }

  .address {
    background-color: #f5f5f5;
    padding: 10px 0 10px 20px;
    font-size: 14px;
    color: #f5ca1d;
  }

  .address img {
    width: 18px;
    position: relative;
    top: 4px;
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
    margin-right: 10px;
    padding: 4px 0px;
    color: #bfbfbf
  }

  .input-ver {
    margin-left: 16px;
    border-top: #ececec solid 1px;
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

  .taxRelated:before {
    border-top: none;
  }

  #cardType:before {
    width: 96%;
    padding-right: 0;
  }

  #cardType1:before {
    width: 96%;
    padding-right: 0;
  }

  #cardType2:before {
    width: 96%;
    padding-right: 0;
  }

  #marriage:before {
    width: 96%;
  }

  #relation:before {
    width: 96%;
  }

  #relation1:before {
    width: 96%;
  }

  #relation2:before {
    width: 96%;
  }

  #order1:before {
    width: 96%;
  }

  #order2:before {
    width: 96%;
  }
</style>
