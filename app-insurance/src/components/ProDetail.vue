<template>
  <div>
    <img src="../assets/img/detail.png" width="100%"/>
    <div style="height: 48px;" @click="next">
        <button class="i-footer">
          立即投保
        </button>
    </div>
  </div>
</template>

<script>
    export default {
        name: "pro-detail",
      methods: {
          next: function () {
            let query = this.$route.query;
            this.$router.push({
              path:'/holder-basic',
              query:query
            })
          }
      }
    }
</script>

<style scoped>

</style>
