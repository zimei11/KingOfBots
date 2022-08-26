<template>
  <el-divider>
    <el-icon>
      <star-filled/>
    </el-icon>
  </el-divider>
  <div style="text-align: center">
    <span>
      <img class="img_a" src="https://img.zimei.fun/202208261204917.png" alt="苏网安备"/>
      <el-link
          href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32030302000933"
      >
        苏公网安备 32030302000933号
      </el-link>
    </span>
    <el-divider direction="vertical"/>
    <span>
      <el-link href="https://beian.miit.gov.cn/">
        苏ICP备2021049515号-2
      </el-link>
    </span>
  </div>
  <br/>
  <div style="text-align: center;font-size: 14px">
    <span>一只子美</span>
    <el-divider direction="vertical"/>
    <span>&copy;zimei</span>
    <el-divider direction="vertical"/>
    <span>All rights reserved. Hosted By <el-link href="https://www.aliyun.com/minisite/goods?taskPkg=1212cpz&pkgSid=182176&userCode=eso7wf2g" style="font-size:90%">Aliyun ECS</el-link></span>
  </div>
  <br/>
</template>

<script setup>
import {StarFilled} from '@element-plus/icons-vue'
</script>

<style scoped>
.img_a {
  height: 15px;
  width: 15px;
}
</style>