<template>
  <el-button link type="danger" @click="remove_bot">删除</el-button>
</template>

<script>
import $ from "jquery";
import {useStore} from "vuex";

export default {
  name: "RemoveBot",
  props: {
    bots: {
      type: Object,
      required: true,
    },
  },
  setup(props) {
    const store = useStore();

    const remove_bot = () => {
      $.ajax({
        url: ("http://localhost:3000/user/bot/remove/"),
        type: "post",
        data: {
          bot_id: props.bots.bot_id,
        },
        headers: {
          'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp, context) {
          if (resp.error_message === "success") {
            context.emit('refresh_bots');
          } else {
            // console.log("非法请求")
          }
        }
      })
    }

    return {
      remove_bot,
    }
  }
}
</script>

<style scoped>

</style>