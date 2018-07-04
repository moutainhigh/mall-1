<template>
  <div>
    <div class="title" style="margin-top: 0;">
      被保人信息
    </div>
    <group label-width="7em" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model="form.name"></x-input>
      <popup-picker title="证件类型" placeholder="请选择证件类型" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <x-input title="证件号码" placeholder="请输入证件号" v-model="form.documentNum"></x-input>
      <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="form.documentNum"></x-input>
      <popup-picker title="国籍" placeholder="请选择国籍" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <x-input title="身高(cm)" placeholder="请输入身高"  v-model="form.documentNum"></x-input>
      <x-input title="体重(kg)" placeholder="请输入体重"  v-model="form.documentNum"></x-input>
      <x-input title="年收入(万元)" placeholder="请输入年收入"  v-model="form.documentNum"></x-input>
      <popup-picker title="婚姻状况" placeholder="请选择婚姻状况" :data="maritalStatus" v-model="form.marital" value-text-align="left"></popup-picker>
      <div>
        <div style="border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" placeholder="请输入固定电话" v-model="form.documentNum"></x-input>
      <x-input title="移动电话" placeholder="请输入移动电话" v-model="form.documentNum"></x-input>
      <x-input title="E-mail" placeholder="请输入E-mail" v-model="form.documentNum"></x-input>
      <x-address title="家庭住址" v-model="addressValue" raw-value :list="addressData" value-text-align="left"></x-address>
      <x-input title="详细地址" placeholder="请输入详细地址" v-model="form.documentNum"></x-input>
      <popup-picker title="投保人是被保人的" placeholder="请选择投被保人的关系" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
    </group>

    <div class="title">
      投保人信息
    </div>
    <group label-width="7em" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model="form.name"></x-input>
      <div class="i-input">
        <div class="i-input-item">性别</div>
        <div class="i-input-radio">
          <div class="radio-div">
            <span>男</span>
            <img src="../assets/img/case-on.png"/>
          </div>
          <div class="radio-div">
            <span>女</span>
            <img src="../assets/img/case-off.png"/>
          </div>
        </div>
      </div>
      <popup-picker title="投保人职业" placeholder="请选择职业" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <datetime title="出生日期" minYear="1956" :endDate="endDate" v-model="time1" value-text-align="left"></datetime>
      <popup-picker title="证件类型" placeholder="请选择证件类型" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <x-input title="证件号码" placeholder="请输入证件号" v-model="form.documentNum"></x-input>
      <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="form.documentNum"></x-input>
      <popup-picker title="国籍" placeholder="请选择国籍" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <x-input title="身高(cm)" placeholder="请输入身高"  v-model="form.documentNum"></x-input>
      <x-input title="体重(kg)" placeholder="请输入体重"  v-model="form.documentNum"></x-input>
      <x-input title="年收入(万元)" placeholder="请输入年收入"  v-model="form.documentNum"></x-input>
      <popup-picker title="婚姻状况" placeholder="请选择婚姻状况" :data="maritalStatus" v-model="form.marital" value-text-align="left"></popup-picker>
      <div>
        <div style="    border-top: 1px solid #D9D9D9;margin-left:15px;font-size: 10px;padding: 10px 10px;color: #19ae00;">
          温馨提示：固定电话与移动电话可任填其中一项
        </div>
      </div>
      <x-input title="固定电话" placeholder="请输入固定电话" v-model="form.documentNum"></x-input>
      <x-input title="移动电话" placeholder="请输入移动电话" v-model="form.documentNum"></x-input>
      <x-input title="E-mail" placeholder="请输入E-mail" v-model="form.documentNum"></x-input>
      <x-address title="家庭住址" v-model="addressValue" raw-value :list="addressData" value-text-align="left"></x-address>
      <x-input title="详细地址" placeholder="请输入详细地址" v-model="form.documentNum"></x-input>
      <popup-picker title="涉税人身份信息" placeholder="请选择婚姻状况" :data="maritalStatus" v-model="form.marital" value-text-align="left"></popup-picker>
    </group>

    <div class="title" style="color: #2c3e50;">
      <div class="title-select">
        <img src="../assets/img/selected.png"/>受益人：法定受益人
      </div>
      <div class="title-add">
        <img src="../assets/img/add.png"/>新增受益人
      </div>
    </div>
    <div class="add">受益人信息 <span style="float: right;">删除</span></div>
    <group label-width="7em" label-margin-right="2em" label-align="left" style="font-size: 15px;">
      <x-input title="姓名" placeholder="请输入姓名" v-model="form.name"></x-input>
      <div class="i-input">
        <div class="i-input-item">性别</div>
        <div class="i-input-radio">
          <div class="radio-div">
            <span>男</span>
            <img src="../assets/img/case-on.png"/>
          </div>
          <div class="radio-div">
            <span>女</span>
            <img src="../assets/img/case-off.png"/>
          </div>
        </div>
      </div>
      <popup-picker title="国籍" placeholder="" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <popup-picker title="受益顺序" placeholder="" :data="maritalStatus" v-model="form.marital" value-text-align="left"></popup-picker>
      <x-input title="姓名" placeholder="请输入姓名" v-model="form.name"></x-input>
      <datetime title="出生日期" minYear="1956" :endDate="endDate" v-model="time1" value-text-align="left"></datetime>
      <popup-picker title="证件类型" placeholder="请选择证件类型" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
      <x-input title="证件号码" placeholder="请输入证件号" v-model="form.documentNum"></x-input>
      <x-input title="证件有效期" placeholder="请选择证件有效期" v-model="form.documentNum"></x-input>
      <popup-picker title="是被保人的" placeholder="请选择关系" :data="list" v-model="form.documentType" value-text-align="left"></popup-picker>
    </group>
    <div style="height: 50px;">
      <button class="i-footer" style="width: 50%;left: 0;background-color: #e0e0e0;" @click="comeBack">
        <div>上一步</div>
      </button>
      <button class="i-footer" style="width: 50%;right: 0">
        <router-link to="infoMatters">
        <div>下一步</div>
        </router-link>
      </button>
    </div>
  </div>

</template>

<script>
  import { GroupTitle, Group, Cell, XInput, Selector, PopupPicker, Datetime, XNumber, ChinaAddressData, XAddress, XTextarea, XSwitch } from 'vux'

  export default {
    components: {
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
        form: {
          name: '',
          documentType: ['身份证'],
          documentNum:'',
          marital:['未婚']

        },
        list: [['身份证', '驾驶证', '护照']],
        maritalStatus:[['未婚','已婚']],
        addressData: ChinaAddressData,
        endDate: new Date()
      }
    },
    methods: {
      comeBack(){
        this.$router.back();
      }
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
    font-size: 14px;
  }

  a {
    text-decoration: unset;
  }
</style>
