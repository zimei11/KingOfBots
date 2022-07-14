<template>
  <el-carousel :interval="5000" arrow="always" class="picture" height="100px">
    <el-carousel-item v-for="item in 4" :key="item">
      <h3 text="2xl" justify="center">{{ item }}</h3>
    </el-carousel-item>
  </el-carousel>
</template>

<script>
export default {
  name: "HomePicture"
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 100px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background: linear-gradient(90deg, #8EC5FC 0%, #E0C3FC 33%, #e0c3fc 66%, #8ec5fc 100%);
}

.el-carousel__item:nth-child(2n + 1) {
  background-image: linear-gradient(90deg, #9599E2 0%, #8BC6EC 33%, #8bc6ec 66%, #9599e2 100%);
}

.picture{
  margin-bottom: 20px;

}
</style>