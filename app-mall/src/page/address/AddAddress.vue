<template>
  <div>
    <div class="headTitle">
      <span class="cancel" @click="cancelAdd">取消</span>
      新增收货地址
      <span class="complete" @click="save">完成</span>
    </div>

    <group style="margin-top: 3.625rem; background: #ffffff">
      <x-input title="联系人" placeholder="名字" v-model="addressVo.consigneeName"></x-input>
      <x-input title="手机号码" placeholder="11位手机码" v-model="addressVo.consigneeMobile"></x-input>
      <x-address title="选择地区" placeholder="地区信息" :list="addressData" value-text-align="left" v-model="pcd"></x-address>
      <x-textarea title="详细地址" placeholder="街道门牌信息" v-model="addressVo.consigneeAddress"></x-textarea>
      <x-input title="邮政编码" placeholder="邮政编码" v-model="addressVo.postCode"></x-input>
    </group>
    <p style="line-height: 2.7; background: #ffffff; padding-left: 1rem">
      设置成默认收货地址
      <img v-if="!addressVo.defaultAddress" class="isDefault" src="../../assets/img/common/Checkmark_nor.png"
           @click="addressVo.defaultAddress = !addressVo.defaultAddress">
      <img v-if="addressVo.defaultAddress" class="isDefault" src="../../assets/img/common/Checkmark_sele.png"
           @click="addressVo.defaultAddress = !addressVo.defaultAddress">
    </p>
  </div>
</template>

<script>
  import {ChinaAddressData, Group, XAddress, XInput, XTextarea, Toast} from 'vux'
  import {saveDeliveryAddress} from "../../service/getData";

  export default {
    name: "AddAddress",
    components: {
      Group,
      XInput,
      XAddress,
      XTextarea,
      Toast
    },
    data() {
      return {
        addressData: ChinaAddressData,
        isDefault: false,
        addressVo: '',
        pcd: []
      }
    },
    methods: {
      cancelAdd() {
        this.$router.go(-1);
      },
      save() {
        saveDeliveryAddress(this.addressVo).then(res => {
          if (res.result == 'SUCCESS') {
            this.$router.go(-1);
          } else {
            alert(res.message);
          }
        })
      }
    },
    created() {
      this.addressVo = {
        consigneeName: '',
        consigneeMobile: '',
        province: '',
        city: '',
        district: '',
        consigneeAddress: '',
        postCode: '',
        defaultAddress: false,
        customerId: 189 // 调试用，之后需去掉
      }
    },
    watch: {
      pcd: {
        handler(newVal, oldVal) {
          if (newVal && newVal.length === 3) {
            this.addressVo.province = newVal[0];
            this.addressVo.city = newVal[1];
            this.addressVo.district = newVal[2];
          }
        },
        immediate: true,
        deep: true
      }
    }
  }
</script>

<style scoped>
  .headTitle {
    position: fixed;
    top: 0;
    background: #ffffff;
    line-height: 3rem;
    width: 100vw;
    text-align: center;
    font-size: 1.2rem
  }

  .cancel {
    float: left;
    font-size: 1rem;
    margin-left: 1rem
  }

  .complete {
    float: right;
    font-size: 1rem;
    margin-right: 1rem
  }

  .isDefault {
    width: 1.12rem;
    float: right;
    margin: 0.812rem
  }
</style>
