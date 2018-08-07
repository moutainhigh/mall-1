<template>
  <div style="padding: 1rem;background: #fff;">
    <div name="settingContent" v-html="settingContent"></div>
  </div>
</template>

<script>
  export default {
    name: "CarConfig",
    props: {
      settingContent: null,
    },
  }
</script>

<style scoped>

</style>
