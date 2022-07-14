<template>
  <div class="background_style">
    <div class="common-layout">
      <el-container>
        <el-header>
          <TopMenu/>
        </el-header>
      </el-container>
    </div>


    <PlayGround/>
  </div>
</template>

<script setup>
import PlayGround from "@/components/PlayGround";
import TopMenu from "@/components/menu/TopMenu";
</script>

<style scoped>
.background_style {
  height: 100%;

  background-image: url("https://img.zimei.xyz/202201272327591.jpg");
  /* 背景图垂直、水平均居中 */
  background-position: center center;
  /* 背景图不平铺 */
  background-repeat: no-repeat;
  /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
  background-attachment: fixed;
  /* 让背景图基于容器大小伸缩 */
  background-size: cover;
  /* 设置背景颜色，背景图加载过程中会显示背景色 */
  background-color: #464646;
}
</style>