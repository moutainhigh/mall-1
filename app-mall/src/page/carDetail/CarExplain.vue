<template>
  <div style="padding: 1rem;background: #fff;">
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
