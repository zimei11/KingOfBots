<template>
  <ContentBase>
    <el-card>
      <template #header>
        <div class="card-header">
          <span>排行榜</span>
        </div>
      </template>
      <el-table
          :data="users"
          style="width: 100%"
          stripe
      >

        <el-table-column prop="photo" label="玩家" show-overflow-tooltip>
          <template #default="scope">
            <img :src="scope.row.photo" class="record-user-photo"/>
            &nbsp;
            <span>{{scope.row.username}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="rating" label="天梯分" width=""/>
      </el-table>
    </el-card>
    <div class="foot-pagination">
      <el-pagination
          :page-size="10"
          layout="prev, pager, next"
          :total="state.total_users"
          @current-change="pull_page"
      />
    </div>
  </ContentBase>
</template>

<script setup>
import ContentBase from "@/components/ContentBase";
import {useStore} from "vuex";
import $ from 'jquery';
import {reactive, ref} from "vue";

const store = useStore();
let users = ref([]);

const state=reactive({
  current_page : 1,
  total_users : 0,
})

const pull_page = page => {
  state.current_page = page;
  $.ajax({
    url: ("http://127.0.0.1:3000/ranklist/getlist/"),
    data: {
      page,
    },
    type: "get",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success(resp) {
      users.value = resp.users;
      state.total_users = resp.users_count;
      // console.log(resp);
      // console.log("success");
    },
    error(resp) {
      console.log(resp);
    }
  });
}

pull_page(state.current_page);

</script>

<style scoped>
.record-user-photo {
  width: 4.2vh;
  border-radius: 50%;
  display: inline-block;
  vertical-align: middle;
}

.foot-pagination{
  margin-top: 10px;
  float:right;
  margin-bottom: 20px;
}
</style>