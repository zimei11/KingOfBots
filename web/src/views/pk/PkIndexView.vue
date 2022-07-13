<template>
  <br>
  <div class="button_center">
    <el-button type="primary" class="button" @click="cancel">返回</el-button>
  </div>
  <PlayGround/>
</template>

<script setup>
import PlayGround from "@/components/PlayGround";
import router from "@/router/index";

const cancel = () => {
  router.push({name: 'home'});
}
</script>

<style scoped>
.button_center{
  text-align: center;
}
</style>