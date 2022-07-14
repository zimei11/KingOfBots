## top-menu模板

```vue
<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" :ellipsis="false" router
           @select="handleSelect">
    <el-menu-item index="1" route="/">Bots ZiMei</el-menu-item>

    <!--下拉菜单响应式-->
    <el-sub-menu index="2" class="hidden-md-and-up">
      <template #title>菜单</template>
      <el-menu-item index="/">首页</el-menu-item>
      <el-menu-item index="2.2">对战</el-menu-item>
      <el-menu-item index="2.3">对战列表</el-menu-item>
      <el-menu-item index="2.4">排行榜</el-menu-item>
    </el-sub-menu>

    <!--大尺寸菜单响应式-->
    <el-menu-item index="/" class="hidden-sm-and-down">首页</el-menu-item>
    <el-menu-item index="4" class="hidden-sm-and-down">对战</el-menu-item>
    <el-menu-item index="5" class="hidden-sm-and-down">对局列表</el-menu-item>
    <el-menu-item index="6" class="hidden-sm-and-down">排行榜</el-menu-item>

    <div class="flex-grow"></div>

    <el-sub-menu index="3" >
      <template #title>ZiMei</template>
      <el-menu-item index="3.1">我的Bot</el-menu-item>
      <el-menu-item index="3.2">退出</el-menu-item>
    </el-sub-menu>

    <el-button circle @click="toggleDark()" style="margin-top:10px;margin-left:10px;margin-right:10px">
      <el-icon>
        <Moon/>
      </el-icon>
    </el-button>
  </el-menu>
</template>

<script setup>
import 'element-plus/theme-chalk/display.css'
import {useRoute} from "vue-router"
import {toggleDark} from '@/composables/dark.js'
import {Moon} from '@element-plus/icons-vue'

const route = useRoute();
const activeIndex = route.path;

const handleSelect = (key, keyPath) => {
  console.log(key, keyPath);
}

</script>

<style scoped>
.flex-grow {
  flex-grow: 1;
}
</style>
```

## 阴影

```css
style="box-shadow: 3px 5px 5px #efefef;"
```

