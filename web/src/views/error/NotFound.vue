<template>
  <ContentBase>
    <el-alert title="404 ◑﹏◐" type="error" center show-icon />
    <el-card shadow="always" style="text-align: center">
      <p><strong>糟糕……我们貌似遇到了些问题</strong><br>Oops! We may have encountered some problems...</p>
      <p>错误代码 / Error code：404</p>
      <p></p>
      <p>错误详情 / Error details：对象不存在：请求的物事不在服务器上。</p>
    </el-card>
  </ContentBase>
</template>

<script setup>
import ContentBase from "@/components/ContentBase";
</script>

<style scoped>
.el-alert {
  margin: 20px 0 0;
}
.el-alert:first-child {
  margin-top: 0px;
  margin-bottom: 20px;
}
</style>