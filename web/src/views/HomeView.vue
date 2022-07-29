<template>
  <ContentBase>
    <el-row :gutter="12">
      <el-col :xs="24" :sm="18">
        <el-alert title="欢迎来到 Bots ZiMei" type="success" description="你可以创建游戏桌来进行对局。" center/>
        <HomePicture/>
        <HomeMessage/>

      </el-col>

      <el-col :xs="24" :sm="6">
        <HomeNotice/>
      </el-col>
    </el-row>
  </ContentBase>
</template>

<script>
import ContentBase from '@/components/ContentBase.vue';
import HomeNotice from "@/components/home/HomeNotice";
import HomeMessage from "@/components/home/HomeMessage";
import HomePicture from "@/components/home/HomePicture";
import router from "@/router";
import {useStore} from "vuex";

export default {
  name: 'HomeView',
  components: {HomePicture, HomeMessage, HomeNotice, ContentBase},
  setup() {
    const store = useStore();
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
  }
}
</script>

<style scoped>
</style>
