<template>
  <div class="playground">
    <el-row :gutter="10">
      <el-col :span="8">
        <div class="block">
          <div class="user-photo">
            <img :src="$store.state.user.photo"/>
          </div>
          <div class="user-username">{{ $store.state.user.username }}</div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="selectOne">
          <el-select v-model="value" class="m-2" placeholder="出战方式" size="large">
            <el-option
                value="-1"
                label="亲自出马"
            />
            <el-option
                v-for="bot in bots"
                :key="bot.id"
                :label="bot.title"
                :value="bot.id"
            />
          </el-select>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="block">
          <div class="user-photo">
            <img :src="$store.state.pk.opponent_photo"/>
          </div>
          <div class="user-username">{{ $store.state.pk.opponent_username }}</div>
        </div>
      </el-col>
      <el-col :span="24">
        <div class="pk-button">
          <el-button type="warning" @click="click_match_btn" round size="large">{{ match_btn_info }}</el-button>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {ref} from "vue";
import {useStore} from "vuex";
import $ from 'jquery'

export default {
  name: "MatchGround",
  setup() {
    const store = useStore();
    let match_btn_info = ref("开始匹配");
    let bots=ref([]);

    const click_match_btn = () => {
      if (match_btn_info.value === "开始匹配") {
        match_btn_info.value = "取消";
        // console.log(value.value);输出当前选中的出战方式id
        store.state.pk.socket.send(JSON.stringify({
          event: "start-matching",
          bot_id:value.value,
        }))
      } else {
        match_btn_info.value = "开始匹配";
        store.state.pk.socket.send(JSON.stringify({
          event: "stop-matching",
        }))
      }
    };

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

    const value = ref('-1')

    return {
      match_btn_info,
      click_match_btn,
      value,
      bots,
    }
  }
}
</script>

<style scoped>
div.playground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
  background-color: rgba(50, 50, 50, 0.5);
  border-radius: 5px;
}

.user-photo {
  margin-top: 10vh;
  text-align: center;
}

.user-photo > img {
  width: 12vw;
  border-radius: 50%;
}

.user-username {
  padding-top: 2vh;
  text-align: center;
  font-size: 24px;
  font-weight: 600;
  color: white;
}

.pk-button {
  text-align: center;
  padding-top: 15vh;
}

.m-2 {
  padding-top: 20vh;
}

.selectOne {
  width: 60%;
  margin: 0 auto;
}
</style>