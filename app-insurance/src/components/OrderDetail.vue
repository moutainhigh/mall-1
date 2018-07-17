<template>
  <div>

  </div>
</template>

<script>
  import {getOrderDetail} from "../service/getData";

  export default {
    name: "orderDetail",
    created() {
      let order;
      let orderCode = this.$route.query.orderCode;
      if (orderCode) {
        getOrderDetail(orderCode).then(res => {
          order = res.data;
          console.log(res);
        })
      }
    }
  }
</script>

<style scoped>

</style>
