<template>
  <div>
    <img src="../assets/img/detail.png" width="100%"/>
    <div style="height: 60px;" >
      <div class="i-footer">
        <button  @click="next" >
          <div>立即投保</div>
        </button>
      </div>
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
