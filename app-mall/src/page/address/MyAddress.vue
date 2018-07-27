<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>
    <div v-if="addressList.length == 0" style="margin-top: 3.875rem; background: #ffffff; line-height: 3">
      <p style="margin-left: 0.875rem;font-size: 1rem" @click="addAddress">
        <img style="width: 1.125rem; float: left; margin-top: 0.875rem; margin-right: 0.46rem"
             src="../../assets/img/common/ic_aftersale_underway.png">
        新增地址
        <img style="width: 1.125rem; float: right; margin-right: 0.875rem; margin-top: 0.687rem"
             src="../../assets/img/common/ic_right.png"></p>
    </div>

    <div style="margin-top: 3.875rem" v-if="addressList.length > 0">
      <div style="border-bottom: 1px solid #DCDCDC">
        <p style="background: #ffffff; line-height: 1.5; padding-left: 0.75rem; font-size: 1rem; padding-top: 10px">孙生<span style="padding-left: 10px">15965856952</span></p>
        <p style="background: #ffffff; line-height: 1.5; padding-left: 0.75rem; font-size: 1rem; color: #999999; padding-bottom: 10px">深圳市福田区金田路富德生命保险大厦507室</p>
      </div>
      <div style="background: #ffffff; height: 2.75rem; position: relative">
        <img v-if="defaultAddress == 0"
             style="width: 1.125rem; float: left; padding-top: 0.81rem; margin-right: 0.46rem; margin-left: 0.75rem"
             src="../../assets/img/common/Checkmark_sele.png">
        <img v-if="defaultAddress != 0" style="width: 1.125rem; float: left; margin-top: 0.81rem; margin-right: 0.46rem"
             src="../../assets/img/common/Checkmark_nor.png">
        <p style="font-size: 0.875rem; padding-top: 0.8rem; display: inline-block">
          默认地址
        </p>
        <img style="width: 1.125rem; position: absolute; right: 7.35rem; margin-top: 0.8rem"
             src="../../assets/img/common/ic_right.png">
        <span style="font-size: 0.875rem; position: absolute; right: 4.875rem; margin-top: 0.8rem;">编辑</span>
        <img style="width: 1.125rem; position: absolute; right: 3.125rem; padding-top: 0.8rem"
             src="../../assets/img/common/ic_right.png">
        <span style="font-size: 0.875rem; position: absolute; right: 0.6875rem; margin-top: 0.8rem;">删除</span>
      </div>
    </div>

    <footer v-if="addressList.length > 0" style="position: fixed; width: 100vw; bottom: 0; text-align: center; line-height: 3; background: #ffffff">
      <img src="">
      新增地址
    </footer>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "MyAddress",
    components: {
      headTop,
    },
    data() {
      return {
        headTitle: '管理我的地址',
        addressList: [],
        defaultAddress: 0
      }
    },
    methods: {
      addAddress() {
        this.$router.push({
          path: "/add-address"
        })
      }
    },
    created() {
      console.log(this.addressList.length)
    }
  }
</script>

<style scoped>

</style>
