<template>
  <ContentBase>
<!--    <el-alert title="用户动态" type="success" description="你可以查看用户动态" center/>-->
    <el-col>
      <el-row :gutter="10">
        <el-col :sm="8" :md="6">
          <UserInfo></UserInfo>
        </el-col>
        <el-col :sm="16" :md="18">
          <BotList></BotList>
        </el-col>
      </el-row>
    </el-col>
  </ContentBase>
</template>

<script setup>
import ContentBase from "@/components/ContentBase";
import UserInfo from "@/components/userbot/UserInfo";
import BotList from "@/components/userbot/BotList";
</script>

<style scoped>

</style>