<template>
  <el-card>
    <template #header>
      <div class="card-header">
        <span>我的Bot</span>

        <AddBot @refresh_bots="refresh_bots"/>

      </div>

      <el-table :data="bots" stripe style="width: 100%"
      >
        <el-table-column fixed prop="title" label="Bot名称" width="120" show-overflow-tooltip/>
        <el-table-column prop="description" label="简介" width="300" show-overflow-tooltip/>
        <el-table-column prop="rating" label="积分" width="120"/>
        <el-table-column prop="createTime" label="创建时间" width="165"/>
        <el-table-column prop="modifyTime" label="修改时间" width="165"/>
        <el-table-column fixed="right" label="操作" width="120">
          <template #default="scope">

           <UpdateBot :row="scope.row" @refresh_bots="refresh_bots"></UpdateBot>

            <el-popconfirm title="你确定要删除这个Bot?"
                           confirm-button-text="删除"
                           confirm-button-type="danger"
                           cancel-button-text="取消"
                           :icon="InfoFilled"
                           icon-color="#fb5430"
                           @confirm="removeBot(scope.row)"
            >
              <template #reference>
                <el-button link type="danger">
                  删除
                </el-button>
              </template>
            </el-popconfirm>
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
import {InfoFilled} from '@element-plus/icons-vue'
import UpdateBot from "@/components/userbot/botlistdetail/UpdateBot";

export default {
  name: "BotList",
  components: {UpdateBot, AddBot},
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


    const removeBot = (row) => {
      //console.log(row);
      $.ajax({
        url: ("http://localhost:3000/user/bot/remove/"),
        type: "post",
        data: {
          bot_id: row.id,
        },
        headers: {
          'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            refresh_bots();
          }
        }
      })
    }

    return {
      bots,
      refresh_bots,
      removeBot,
      InfoFilled,
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