<template>
  <div>
    <div v-for="img in imgs">
      <!--<img src="../assets/img/detail.png" width="100%"/>-->
      <img :src="img" width="100%"/>
      <!--<img src="../assets/img/detail1/img1.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/img2.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/img3.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/img4.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/img5.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/canvas1.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/canvas2.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/canvas3.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/canvas4.png" width="100%"/>-->
      <!--<img src="../assets/img/detail1/canvas5.png" width="100%"/>-->
    </div>
    <!--<div v-if="id === 2">-->
      <!--<img src="../assets/img/detail2/banner.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img1.jpg" width="100%"/>-->
      <!--&lt;!&ndash;<img src="../assets/img/detail2/img2.jpg" width="100%"/>&ndash;&gt;-->
      <!--<img src="../assets/img/detail2/img3.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img4.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img5.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img6.jpg" width="100%"/>-->
      <!--&lt;!&ndash;<img src="../assets/img/detail2/img7.jpg" width="100%"/>&ndash;&gt;-->
      <!--<img src="../assets/img/detail2/img8.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img9.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img10.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img11.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img12.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img13.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img14.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img15.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img16.jpg" width="100%"/>-->
      <!--<img src="../assets/img/detail2/img17.jpg" width="100%"/>-->
    <!--</div>-->
    <div style="height: 75px;">
      <div class="i-footer">
        <button @click="next">
          <div>立即投保</div>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
  import storage from "../store/storage";
  import {getProDetail} from "../service/getData";

  export default {
    name: "pro-detail",
    data() {
      return {
        id: 1,
        imgs: []
      }
    },
    methods: {
      next: function () {
        let query = this.$route.query;
        this.$router.push({
          path: '/holder-basic',
          query: query
        })
      }
    },
    created: function () {
      this.id = storage.fetch("packetId");
      getProDetail(this.id).then(res => {
        if (res.result == 'SUCCESS') {
          this.imgs = res.data;
        }
      })
    }
  }
</script>

<style scoped>
  div {
    line-height: 0;
  }
</style>
