<template>
  <el-carousel :interval="5000" arrow="always" class="picture" height="50px" direction="vertical">
    <el-carousel-item v-for="item in message" :key="item.url">
      <h3 text="2xl" justify="center"><el-link type="default" class="link" :href="item.url">{{ item.text }}</el-link></h3>
    </el-carousel-item>
  </el-carousel>
</template>

<script>

export default {
  name: "HomePicture",
  data() {
    return {
      message: [
        {text: "今日热门游戏", url: "https://www.acwing.com/", id: 1},
        {text: "这款AI居然战无不胜ˋ( ° ▽、° ) ", url: "https://www.acwing.com/", id: 2},
        {text: "打赏下孩子吧(￣﹃￣)", url: "https://www.acwing.com/", id: 3},
      ]
    }
  },
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 50px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background: linear-gradient(90deg, #8EC5FC 0%, #E0C3FC 33%, #e0c3fc 66%, #8ec5fc 100%);
}

.el-carousel__item:nth-child(2n + 1) {
  background-image: linear-gradient(90deg, #9599E2 0%, #8BC6EC 33%, #8bc6ec 66%, #9599e2 100%);
}

.picture {
  margin-bottom: 20px;

}

.link{
  color: #222222;
  font-size: 18px;
}
</style>