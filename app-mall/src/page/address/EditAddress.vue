<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>

    <group style="margin-top: 3.625rem; background: #ffffff">
      <x-input title="联系人" placeholder="名字"></x-input>
      <x-input title="手机号码" placeholder="11位手机码"></x-input>
      <x-address title="选择地区" placeholder="地区信息" :list="addressData" value-text-align="left"></x-address>
      <x-textarea title="详细地址" placeholder="街道门牌信息"></x-textarea>
      <x-input title="邮政编码" placeholder="邮政编码"></x-input>
    </group>
    <p style="line-height: 2.7; background: #ffffff; padding-left: 1rem">
      设置成默认收货地址
      <img v-if="!isDefault" class="isDefault" src="../../assets/img/common/Checkmark_nor.png"
           @click="isDefault = !isDefault">
      <img v-if="isDefault" class="isDefault" src="../../assets/img/common/Checkmark_sele.png"
           @click="isDefault = !isDefault">
    </p>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import {ChinaAddressData, Group, XAddress, XInput, XTextarea, Toast} from 'vux'

  export default {
    name: "EditAddress",
    components: {
      headTop,
      Group,
      XInput,
      XAddress,
      XTextarea,
      Toast
    },
    data() {
      return {
        headTitle: '编辑收货地址',
        addressData: ChinaAddressData,
        isDefault: false
      }
    }
  }
</script>

<style scoped>
  .isDefault {
    width: 1.12rem;
    float: right;
    margin: 0.812rem
  }
</style>
