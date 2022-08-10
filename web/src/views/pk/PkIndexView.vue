<template>
  <div class="background_style">
    <TopMenu/>
    <PlayGround v-if="$store.state.pk.status==='playing'"/>
    <MatchGround v-if="$store.state.pk.status=='matching'"/>
  </div>
</template>

<script setup>
import PlayGround from "@/components/PlayGround";
import TopMenu from "@/components/menu/TopMenu";
import {onMounted, onUnmounted} from "vue";
import {useStore} from 'vuex'
import MatchGround from "@/components/MatchGround";
import {ElNotification} from 'element-plus'

const store = useStore();
const socketUrl = `ws://localhost:3000/websocket/${store.state.user.token}/`;

let socket = null;
onMounted(() => {
  store.commit("updateOpponent", {
    username: "我的对手",
    photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png"
  })
  socket = new WebSocket(socketUrl);
  socket.onopen = () => {
    // console.log("connected");
    store.commit("updateSocket", socket);
  }

  socket.onmessage = msg => {
    const data = JSON.parse(msg.data);
    if (data.event === "start-matching") {//匹配成功
      store.commit("updateOpponent", {
        username: data.opponent_username,
        photo: data.opponent_photo,
      });

      successMessage();

      setTimeout(() => {
        store.commit("updateStatus", "playing");
      }, 2000);
      store.commit("updateGamemap", data.gamemap);
    }
  }

  socket.onclose = () => {
    // console.log("disconnected");
  }
});

onUnmounted(() => {
  socket.close();
  store.commit("updateStatus", "matching");
});

const successMessage = () => {
  ElNotification({
    title: '匹配成功',
    message: '两秒后游戏开始！╰(￣ω￣ｏ)',
    type: 'success',
  })
};
</script>

<style scoped>
.background_style {
  height: 100%;

  background-image: url("https://img.zimei.fun/202201272327591.jpg");
  /* 背景图垂直、水平均居中 */
  background-position: center center;
  /* 背景图不平铺 */
  background-repeat: no-repeat;
  /* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 */
  background-attachment: fixed;
  /* 让背景图基于容器大小伸缩 */
  background-size: cover;
  /* 设置背景颜色，背景图加载过程中会显示背景色 */
  background-color: #464646;
}
</style>