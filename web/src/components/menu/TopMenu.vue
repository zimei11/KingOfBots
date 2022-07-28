<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false" router
           @select="handleSelect">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <el-menu-item index="1" route="/">Bots ZiMei</el-menu-item>

    <!--下拉菜单响应式-->
    <el-sub-menu index="2" class="hidden-md-and-up">
      <template #title>菜单</template>
      <el-menu-item index="/">首页</el-menu-item>
      <el-menu-item index="/pk/" route="/pk/">对战</el-menu-item>
      <el-menu-item index="/record/" route="/record/">对战列表</el-menu-item>
      <el-menu-item index="/ranklist/" route="/ranklist/">排行榜</el-menu-item>
    </el-sub-menu>

    <!--大尺寸菜单响应式-->
    <el-menu-item index="/" class="hidden-sm-and-down">首页</el-menu-item>
    <el-menu-item index="/pk/" class="hidden-sm-and-down" route="/pk/">对战</el-menu-item>
    <el-menu-item index="/record/" class="hidden-sm-and-down" route="/record/">对局列表</el-menu-item>
    <el-menu-item index="/ranklist/" class="hidden-sm-and-down" route="/ranklist/">排行榜</el-menu-item>

    <div class="flex-grow"></div>

    <el-menu-item index="/user/account/login/" v-if="!$store.state.user.is_login">登录</el-menu-item>
    <el-menu-item index="/user/account/register/" v-if="!$store.state.user.is_login">注册</el-menu-item>

    <el-sub-menu index="3" v-if="$store.state.user.is_login">
      <template #title>
        <img class="img-fluid" :src=$store.state.user.photo alt="头像">
        <div class="sub-title">{{ $store.state.user.username }}</div>
      </template>
      <el-menu-item index="/user/bot/" route="/user/bot/">我的Bot</el-menu-item>
      <el-menu-item index="3.2" route="/" @click="logout" v-if="$store.state.user.is_login">退出</el-menu-item>
    </el-sub-menu>

    <el-button circle @click="toggleDark()" style="margin-top:10px;margin-left:10px;margin-right:10px">
      <el-icon>
        <Moon/>
      </el-icon>
    </el-button>

    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

  </el-menu>
</template>

<script setup>
import 'element-plus/theme-chalk/display.css'
import {useRoute} from "vue-router"
import {toggleDark} from '@/composables/dark.js'
import {Moon} from '@element-plus/icons-vue'
import {useStore} from "vuex";

const route = useRoute();
const activeIndex = route.path;

const handleSelect = (key, keyPath) => {
  console.log(key, keyPath);
}

const store = useStore();
const logout = () => {
  store.commit("logout");
};
</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}

img {
  border-radius: 50%;
  height: 35px;
  width: 35px;
  margin-right: 20px;
}

.img-fluid {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>