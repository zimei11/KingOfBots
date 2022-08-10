<template>
  <div class="playground">
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="block">
          <div class="user-photo">
            <img :src="$store.state.user.photo"/>
          </div>
          <div class="user-username">{{ $store.state.user.username }}</div>
        </div>
      </el-col>
      <el-col :span="12">
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

export default {
  name: "MatchGround",
  setup(){
    const store=useStore();

    let match_btn_info=ref("开始匹配");

    const click_match_btn=()=>{
      if(match_btn_info.value==="开始匹配"){
        match_btn_info.value="取消";
        store.state.pk.socket.send(JSON.stringify({
          event:"start-matching",
        }))
      }else{
        match_btn_info.value="开始匹配";
        store.state.pk.socket.send(JSON.stringify({
          event:"stop-matching",
        }))
      }
    }

    return {
      match_btn_info,
      click_match_btn,
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
</style>