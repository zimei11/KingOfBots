<template>
  <ContentBase>
    <el-card>
      <template #header>
        <div class="card-header">
          <span class="title-text">我的Bot</span>
        </div>

        <el-table
            :data="records"
            style="width: 100%"
            :row-class-name="tableRowClassName"
        >

          <el-table-column prop="a_photo" label="蓝色方" show-overflow-tooltip>
            <template #default="scope">
              <img :src="scope.row.a_photo" class="record-user-photo"/>
            </template>
          </el-table-column>
          <el-table-column prop="a_username" label="玩家A" show-overflow-tooltip/>
          <el-table-column prop="b_photo" label="红色方" show-overflow-tooltip>
            <template #default="scope">
              <img :src="scope.row.b_photo" class="record-user-photo"/>
            </template>
          </el-table-column>
          <el-table-column prop="b_username" label="玩家B" show-overflow-tooltip/>
          <el-table-column prop="result" label="对战结果" width=""/>
          <el-table-column prop="record.createtime" label="对战时间" width=""/>
          <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
              <el-button link type="primary" @click="open_record_content(scope.row.record.id)">
                对局回放
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </el-card>
  </ContentBase>
</template>

<script setup>
import ContentBase from "@/components/ContentBase";
import {useStore} from "vuex";
import $ from 'jquery';
import {ref} from "vue";
import router from "@/router/index";

const store = useStore();
let records = ref([]);
let current_page = 1;
let total_records = 0;

console.log(total_records);

const pull_page = page => {
  current_page = page;
  $.ajax({
    url: ("http://127.0.0.1:3000/record/getlist/"),
    data: {
      page,
    },
    type: "get",
    headers: {
      Authorization: "Bearer " + store.state.user.token,
    },
    success(resp) {
      records.value = resp.records;
      total_records = resp.records_count;
    },
    error(resp) {
      console.log(resp);
    }
  });
}

pull_page(current_page);

const stringTo2D = (map) => {
  let g = [];
  for (let i = 0, k = 0; i < 13; i++) {
    let line = [];
    for (let j = 0; j < 14; j++, k++) {
      if (map[k] === '0') line.push(0);
      else line.push(1);
    }
    g.push(line);
  }
  return g;
}

const tableRowClassName = ({
                             row,
                             // rowIndex,
                           }
) => {
  if (row.result === "B胜") {
    // console.log(row.result);
    return 'danger-row'
  } else if (row.result === "A胜") {
    return 'primary-row'
  }
  // console.log(row.result);
  return ''
};

const open_record_content = (recordId) => {
  for (const record of records.value) {
    if (record.record.id === recordId) {
      store.commit("updateIsRecord", true);
      // console.log(record);
      store.commit("updateGame",{
        map:stringTo2D(record.record.map),
        a_id:record.record.aid,
        a_sx:record.record.asx,
        a_sy:record.record.asy,
        b_id:record.record.bid,
        b_sx:record.record.bsx,
        b_sy:record.record.bsy
      });
      store.commit("updateSteps",{
        a_steps:record.record.asteps,
        b_steps:record.record.bsteps,
      });
      store.commit("updateRecordLoser",record.record.loser);
      router.push({
        name: "record_content",
        params: {
          recordId
        }
      })
      break;
    }
  }
}
</script>

<style scoped>
.record-user-photo {
  width: 4.2vh;
  border-radius: 50%;
  display: inline-block;
  vertical-align: middle;
}

.title-text {
  /*font-size: 20px;*/
  /*font-weight: 400;*/
  /*font-style: italic;*/
  margin-bottom: 10px;
}
</style>