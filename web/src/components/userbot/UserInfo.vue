<template>
  <el-card class="box-card">
    <el-row :gutter="10">
     <el-col :span="10">
       <el-avatar shape="square" :size="50" :src=$store.state.user.photo>
       </el-avatar>
     </el-col>
      <el-col :span="14">
        <span class="user-text"><p/>Hello Bots!</span>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "UserInfo"
}
</script>

<style scoped>
.user-text{
  text-align: center;
}
</style>