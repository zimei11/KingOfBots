<template>
<h1>Bot昵称：{{bot_name}}</h1>
<h1>Bot战力：{{bot_rating}}</h1>
  <router-view></router-view>
</template>

<script>
import $ from 'jquery';
import {ref} from 'vue';

export default{
  name:"App",
  setup:()=>{
    let bot_name=ref("");
    let bot_rating=ref("");

    $.ajax({
      url:"http://localhost:3000/pk/getbotinfo/",
      type:"get",
      success:resp=>{
        bot_name.value=resp.name;
        bot_rating.value=resp.rating;
      }
    })
    return {
      bot_name,
      bot_rating
    }
  }
}
</script>
<style>
body ,html{
  background-image: url("https://img.zimei.xyz/202207041852809.jpg");
  background-size: cover;
  background-repeat: no-repeat;
}
</style>
