<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span>我的Bot</span>

        <AddBot @refresh_bots="refresh_bots"/>

      </div>

      <el-table :data="bots"  stripe style="width: 100%">
        <el-table-column fixed prop="title" label="Bot名称" width="120"/>
        <el-table-column prop="description" label="简介" width="300"/>
        <el-table-column prop="rating" label="积分" width="120"/>
        <el-table-column prop="createTime" label="创建时间" width="165"/>
        <el-table-column prop="modifyTime" label="修改时间" width="165"/>
        <el-table-column fixed="right" label="操作" width="120">
          <template #default>
            <el-button link type="primary" @click="handleClick">
              修改
            </el-button>
            <RemoveBot :bots="bots" @refresh_bots="refresh_bots"></RemoveBot>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </el-card>
</template>

<script>
import {ref} from "vue";
import {useStore} from "vuex";
import $ from 'jquery';
import AddBot from "@/components/userbot/botlistdetail/AddBot";
import RemoveBot from "@/components/userbot/botlistdetail/RemoveBot";

export default {
  name: "BotList",
  components: {RemoveBot, AddBot},
  setup() {
    const store = useStore();
    let bots = ref([]);

    const refresh_bots = () => {
      $.ajax({
        url: ("http://localhost:3000/user/bot/getlist/"),
        type: "get",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          bots.value = resp;
        }
      })
    }

    refresh_bots();

    const handleClick = () => {
      console.log('click')
    }

    return {
      handleClick,
      bots,
      refresh_bots,
    }
  }
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>