<template>
  <div class="common-layout" v-if="!store.state.user.pulling_info">

    <el-container>

      <el-main>
        <h1 style="text-align:center;">用户登录</h1>
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
                  <el-input type="password" v-model="password" placeholder="输入密码"/>
                </el-form-item>
                <p style="color:red" class="error-message">{{ error_message }}</p>
                <el-form-item>
                  <el-col :xs="24" :sm="12">
                    <el-button class="button" type="primary" @click="login" style="width:100%">登录
                    </el-button>
                  </el-col>
                  <el-col :xs="24" :sm="12">
                    <el-button class="button" @click="cancel" style="width:100%">取消</el-button>
                  </el-col>
                </el-form-item>
              </el-form>

              <div class="register">
                还没有账号？去&nbsp;<el-link type="primary" @click="toRegister">注册</el-link>
              </div>

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
import {ref} from 'vue'
import {useStore} from 'vuex'
import router from "@/router/index";

const store = useStore();
let username = ref("");
let password = ref("");
let error_message = ref("");

const cancel = () => {
  router.push({name: 'home'});
}

const toRegister=()=>{
  router.push({name:'user_account_register'})
}

//每次刷新网页，如果登录过会跳转到首页
const jwt_token=localStorage.getItem("jwt_token");
if(jwt_token){
  store.commit("updateToken",jwt_token);
  store.dispatch("getinfo",{
    success(){
      router.push({name:"home"});
      store.commit("updatePullingInfo",false);
    },
    error(){
      store.commit("updatePullingInfo",false);
    }
  })
}else{
  store.commit("updatePullingInfo",false);
}

const login = () => {
  error_message.value = "";
  store.dispatch("login", {
    username: username.value,
    password: password.value,
    success() {
      store.dispatch("getinfo",{
        success(){
          router.push({name: 'home'});
        }
      })
    },
    error() {
      error_message.value = "用户名或密码错误";
    }
  })
  // console.log(username.value,password.value);
};
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
    background-image: url('https://img.zimei.xyz/202207101321009.jpg');
    background-size:100%;
} */

.register{
  text-align: center;
}
</style>