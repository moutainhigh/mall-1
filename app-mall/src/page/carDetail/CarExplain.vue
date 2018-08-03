<template>
  <div style="background: #ffffff">
    <div name="explainContent" v-html="explainContent"></div>
  </div>
</template>

<script>
  export default {
    props: ['explainContent']
  }
</script>

<style scoped>

</style>
