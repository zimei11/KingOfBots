<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <TopMenu/>
      </el-header>
    </el-container>
  </div>

  <PlayGround/>
</template>

<script setup>
import PlayGround from "@/components/PlayGround";
import TopMenu from "@/components/menu/TopMenu";
</script>

<style scoped>

</style>