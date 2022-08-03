<template>
  <div>
    <el-button type="primary" @click="dialogVisible = true">
      创建Bot
    </el-button>
    <el-dialog
        v-model="dialogVisible"
        title="新增Bot"
        width="70%"
        @close="closeDialog"
    >
      <el-form label-width="120px" label-position="top">
        <el-form-item label="Bot名称">
          <el-input type="text" v-model="botAdd.title" placeholder="请输入Bot名称"/>
        </el-form-item>
        <el-form-item label="Bot简介">
          <el-input
              v-model="botAdd.description"
              maxlength="300"
              placeholder="请输入Bot简介"
              show-word-limit
              type="textarea"
              rows="3"
          />
        </el-form-item>
        <el-form-item label="Bot代码">
          <el-input
              v-model="botAdd.content"
              maxlength="10000"
              placeholder="请输入Bot代码"
              show-word-limit
              type="textarea"
              rows="7"
          />
        </el-form-item>
        <p style="color:red" class="error-message">{{ botAdd.error_message }}</p>
      </el-form>
      <template #footer>
           <span class="dialog-footer">
              <el-button @click="add_bot" type="primary">
                确定
              </el-button>
              <el-button @click="dialogVisible = false">
                 取消
              </el-button>
          </span>
      </template>


    </el-dialog>
  </div>
</template>

<script>
import {reactive, ref} from "vue";
import $ from 'jquery';
import {useStore} from "vuex";
import router from "@/router";

export default {
  name: "AddBot",
  setup(props, context) {
    const dialogVisible = ref(false);
    const store = useStore();


    const botAdd = reactive({
      title: "",
      description: "",
      content: "",
      error_message: "",
    });

    const add_bot = () => {
      botAdd.error_message = "";
      $.ajax({
        url: ("http://localhost:3000/user/bot/add/"),
        type: "post",
        data: {
          title: botAdd.title,
          description: botAdd.description,
          content: botAdd.content,
        },
        headers: {
          'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            context.emit('refresh_bots');
            router.push({name: 'user_bot_index'});
          } else {
            botAdd.error_message = resp.error_message;
            // console.log("push2")
          }
        }
      })
    }

    const closeDialog = () => {
      botAdd.title = "";
      botAdd.description = "";
      botAdd.content = "";
      botAdd.error_message = "";
    }

    return {
      dialogVisible,
      botAdd,
      add_bot,
      closeDialog,
    }
  }
}
</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>