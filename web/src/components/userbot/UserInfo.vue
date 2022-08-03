<template>
  <el-card class="box-card">
    <el-avatar shape="square" :size="50" :src=$store.state.user.photo></el-avatar>
  </el-card>
</template>

<script>
export default {
  name: "UserInfo"
}
</script>

<style scoped>

</style>