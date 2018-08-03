<template>
  <div>
    <head-top :headTitle="'选择地址'" :go-back="true"></head-top>
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

    <div v-if="addressList.length > 0">
      <div style="margin-top: 0.875rem" v-for="address in addressList" @click="chooseAddr(address)">
        <div style="border-bottom: 1px solid #DCDCDC">
          <p style="background: #ffffff; line-height: 1.5; padding: 10px 0.75rem; font-size: 1rem;">
            {{address.consigneeName}}<span style="padding-left: 10px">{{address.consigneeMobile}}</span>
            <span v-if="address.defaultAddress" style="float: right;color: #f5ca1d;font-size: 0.8rem;line-height: 1.8;">[ 默认地址 ]</span>
          </p>
          <p style="background: #ffffff; line-height: 1.5; padding-left: 0.75rem; font-size: 0.875rem; color: #999999; padding-bottom: 10px">
            {{address.consigneeAddress}}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'
  import {deleteDeliveryAddressByAdderssId, getDeliveryAddress} from "../../service/getData";
  import storage from "../../store/storage";
  import {ADDRESS} from "../../config/constant";

  export default {
    name: "ChooseAddress",
    components: {
      headTop,
    },
    data() {
      return {
        addressList: [],
      }
    },
    methods: {
      addAddress() {
        this.$router.push({
          path: "/add-address"
        })
      },
      chooseAddr(address){
        storage.saveSession(ADDRESS,address);
        this.$router.go(-1);
      },
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
