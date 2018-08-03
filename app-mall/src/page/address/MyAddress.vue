<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>
    <div style="height: 3rem"></div>
    <div v-if="addressList.length == 0" class="addAddress-item"
         style="margin-top: 0.875rem; background: #ffffff; line-height: 3">
      <p style="margin-left: 0.875rem;font-size: 1rem" @click="addAddress">
        <img style="width: 1.125rem; float: left; margin-top: 0.875rem; margin-right: 0.46rem"
             src="../../assets/img/common/ic_aftersale_underway.png">
        新增地址
        <img style="width: 1.125rem; float: right; margin-right: 0.875rem; margin-top: 0.875rem"
             src="../../assets/img/common/ic_right.png"></p>
    </div>

    <div v-if="addressList.length > 0" style="margin-bottom: 4rem;">
      <div style="margin-top: 0.875rem;border-top: 1px #DCDCDC solid;border-bottom:1px #DCDCDC solid; " v-for="address in addressList">
        <div style="border-bottom: 1px solid #DCDCDC">
          <p style="background: #ffffff; line-height: 1.5; padding-left: 0.75rem; font-size: 1rem; padding-top: 10px">
            {{address.consigneeName}}<span style="padding-left: 10px">{{address.consigneeMobile}}</span></p>
          <p
            style="background: #ffffff; line-height: 1.5; padding-left: 0.75rem; font-size: 0.875rem; color: #999999; padding-bottom: 10px">
            {{address.consigneeAddress}}</p>
        </div>
        <div style="background: #ffffff; height: 2.75rem; position: relative">
          <img v-if="address.defaultAddress"
               style="width: 1.125rem; float: left; padding-top: 0.81rem; margin-right: 0.46rem; margin-left: 0.75rem"
               src="../../assets/img/common/Checkmark_sele.png">
          <img v-if="!address.defaultAddress"
               style="width: 1.125rem; float: left; padding-top: 0.81rem; margin-right: 0.46rem; margin-left: 0.75rem"
               src="../../assets/img/common/Checkmark_nor.png">
          <p style="font-size: 0.875rem; padding-top: 0.8rem; display: inline-block">
            默认地址
          </p>
          <img style="width: 1.125rem; position: absolute; right: 7.35rem; margin-top: 0.8rem"
               src="../../assets/img/common/ic_edit.png">
          <span style="font-size: 0.875rem; position: absolute; right: 4.875rem; margin-top: 0.8rem;"
                @click="editAddress(address.addressId)">编辑</span>
          <img style="width: 1.125rem; position: absolute; right: 3.125rem; padding-top: 0.8rem"
               src="../../assets/img/common/ic_delete.png">
          <span style="font-size: 0.875rem; position: absolute; right: 0.6875rem; margin-top: 0.8rem;"
                @click="deleteAddress(address.addressId)">删除</span>
        </div>
      </div>
    </div>

    <footer v-if="addressList.length > 0" @click="addAddress"
            style="position: fixed; width: 100vw; bottom: 0; text-align: center; line-height: 3; background: #ffffff;border-top: 1px #DCDCDC solid;">
      <img src="../../assets/img/common/ic_add.png" style="vertical-align: middle;width: 1.2rem;margin-right: 0.2rem;">
      <span style="vertical-align: middle">新增地址</span>
    </footer>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import {deleteDeliveryAddressByAdderssId, getDeliveryAddress} from "../../service/getData";

  export default {
    name: "MyAddress",
    components: {
      headTop,
    },
    data() {
      return {
        headTitle: '管理我的地址',
        addressList: [],
      }
    },
    methods: {
      addAddress() {
        this.$router.push({
          path: "/add-address"
        })
      },
      editAddress(addressId) {
        this.$router.push({
          path: "/edit-address",
          query: {addressId: addressId}
        })
      },
      deleteAddress(addressId) {
        deleteDeliveryAddressByAdderssId(addressId).then(res => {
          if (res.result == 'SUCCESS') {
            getDeliveryAddress().then(res => {
              if (res.result == 'SUCCESS') {
                this.addressList = res.data;
              }
            })
          }
        })
      }
    },
    created() {
      getDeliveryAddress().then(res => {
        if (res.result == 'SUCCESS') {
          this.addressList = res.data;
        }
      })
    }
  }
</script>

<style scoped>

</style>
