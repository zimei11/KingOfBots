<template>
  <div class="common-layout">

    <el-container>

      <el-main>
        <h1 style="text-align:center;">用户注册</h1>
        <el-divider/>
        <el-row>
          <el-col :xs="6" :sm="8" :md="9">
            <div class="grid-content ep-bg-purple"></div>
          </el-col>
          <el-col :xs="12" :sm="8" :md="6">

            <el-card>
              <el-form label-width="120px" label-position="top">
                <el-form-item label="用户名">
                  <el-input type="text" v-model="username" placeholder="输入用户名"/>
                </el-form-item>
                <el-form-item label="密码">
                  <el-input type="password" v-model="password" placeholder="输入密码（不少于6位）"/>
                </el-form-item>
                <el-form-item label="确定密码">
                  <el-input type="password" v-model="confirmedPassword" placeholder="再次输入密码（不少于6位）"/>
                </el-form-item>
                <p style="color:red" class="error-message">{{ error_message }}</p>
                <el-form-item>
                  <el-col :xs="24" :sm="12">
                    <el-button class="button" type="primary" @click="register" style="width:100%">注册
                    </el-button>
                  </el-col>
                  <el-col :xs="24" :sm="12">
                    <el-button class="button" @click="cancel" style="width:100%">取消</el-button>
                  </el-col>
                </el-form-item>
              </el-form>
            </el-card>
          </el-col>
          <el-col :xs="6" :sm="8" :md="9">
            <div class="grid-content ep-bg-purple"></div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import {useStore} from 'vuex';
import router from '@/router/index';
import $ from 'jquery';

const store = useStore();
let username = ref('');
let password = ref('');
let confirmedPassword = ref('');
let error_message = ref('');

const register = () => {
  error_message.value = "";
  $.ajax({
    url: "http://localhost:3000/user/account/register/",
    type: "POST",
    data: {
      username: username.value,
      password: password.value,
      confirmedPassword: confirmedPassword.value,
    },
    success(resp) {
      if (resp.error_message === "success") {
        store.dispatch("login", {
          username: username.value,
          password: password.value,
          success() {
            store.dispatch("getinfo", {
              success() {
                router.push({name: 'home'});
              },
              error() {
                error_message.value = "系统异常，请稍后重试";
              },
            })
          },
        });
      } else {
        error_message.value = resp.error_message;
      }
    }
  })
};

const cancel = () => {
  router.push({name: 'home'});
}
</script>

<style scoped>
.button {
  margin-bottom: 10px;
}

.el-container {
  height: 100%;
}

.el-main {
  height: 100%;
  background: linear-gradient(200deg, var(--el-color-danger-light-8), var(--el-color-primary-light-5));
}

.common-layout {
  height: 100%;
}

/* .el-card{
    background-image: url('https://img.zimei.fun/202207101321009.jpg');
    background-size:100%;
} */
</style>