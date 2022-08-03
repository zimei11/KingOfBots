<template>
  <el-button link type="primary" @click="dialogVisible = true">
    修改
  </el-button>
  <el-dialog
      v-model="dialogVisible"
      title="Tips"
      width="70%"
      >
    <span>This is a message</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button @click="dialogVisible = false" type="primary" >
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import {ref} from "vue";

export default {
  name: "BotInfo",
  setup() {
    const dialogVisible = ref(false)
    return {
      dialogVisible,
    }
  }
}
</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>