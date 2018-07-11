<template>
  <div>
    <img src="../assets/img/detail.png" width="100%"/>
    <!--<img v-if="id === 2" src="../assets/img/detail3.png" width="100%"/>-->
    <div style="height: 60px;">
      <div class="i-footer">
        <button @click="next">
          <div>立即投保</div>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "pro-detail",
    // data() {
    //   return {
    //     id: null
    //   }
    // },
    methods: {
      next: function () {
        let query = this.$route.query;
        this.$router.push({
          path: '/holder-basic',
          query: query
        })
      }
    },
    // created: function () {
    //   let query = this.$route.query;
    //   this.id = query.id;
    // }
  }
</script>

<style scoped>

</style>
