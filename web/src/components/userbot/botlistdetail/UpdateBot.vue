<template>
  <span>
    <el-button link type="primary" @click="dialogVisible = true">
      修改
    </el-button>
    <el-dialog
        v-model="dialogVisible"
        title="修改Bot"
        width="70%"
        @close="closeDialog"
    >
      <el-form label-width="120px" label-position="top">
        <el-form-item label="Bot名称">
          <el-input type="text" v-model="botUpdate.title" placeholder="请输入Bot名称"/>
        </el-form-item>
        <el-form-item label="Bot简介">
          <el-input
              v-model="botUpdate.description"
              maxlength="300"
              placeholder="请输入Bot简介"
              show-word-limit
              type="textarea"
              rows="3"
          />
        </el-form-item>
        <el-form-item label="Bot代码">
          <el-input
              v-model="botUpdate.content"
              maxlength="10000"
              placeholder="请输入Bot代码"
              show-word-limit
              type="textarea"
              rows="7"
          />
        </el-form-item>
        <p style="color:red" class="error-message">{{ botUpdate.error_message }}</p>
      </el-form>
      <template #footer>
           <span class="dialog-footer">
              <el-button @click="add_bot" type="primary">
                确定
              </el-button>
              <el-button id="cancelButton" @click="dialogVisible = false">
                 取消
              </el-button>
          </span>
      </template>


    </el-dialog>
  </span>
</template>

<script>
import {reactive, ref} from "vue";
import $ from 'jquery';
import {useStore} from "vuex";
import {ElNotification} from "element-plus";

export default {
  name: "UpdateBot",
  props:{
    row: {
      type: Object,
      required: true,
    },
  },
  setup(props, context) {
    const dialogVisible = ref(false);
    const store = useStore();


    const botUpdate = reactive({
      title: props.row.title,
      description: props.row.description,
      content: props.row.content,
      error_message: "",
    });

    const successMessage=()=>{
      ElNotification({
        title: '成功',
        message: 'Bot修改成功。',
        type: 'success',
      })
    }

    const add_bot = () => {
      botUpdate.error_message = "";
      $.ajax({
        url: ("http://localhost:3000/user/bot/update/"),
        type: "post",
        data: {
          bot_id: props.row.id,
          title: botUpdate.title,
          description: botUpdate.description,
          content: botUpdate.content,
        },
        headers: {
          'Authorization': "Bearer " + store.state.user.token,
        },
        success(resp) {
          if (resp.error_message === "success") {
            successMessage();
            context.emit('refresh_bots');
            $("#cancelButton").trigger('click');
          } else {
            botUpdate.error_message = resp.error_message;
            // console.log("push2")
          }
        }
      })
    }

    const closeDialog = () => {
      // botUpdate.title = "";
      // botUpdate.description = "";
      // botUpdate.content = "";
      botUpdate.error_message = "";
    }

    return {
      dialogVisible,
      botUpdate,
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