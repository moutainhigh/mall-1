<template>
  <div>
    <head-top :headTitle="headTitle"></head-top>
  </div>
</template>

<script>
  import headTop from '../../components/header/head'

  export default {
    name: "AddAddress",
    components: {
      headTop,
    },
    data() {
      return {
        headTitle: '新增收货地址'
      }
    }
  }
</script>

<style scoped>

</style>
