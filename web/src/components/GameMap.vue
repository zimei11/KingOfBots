<template>
  <div ref="parent" class="gamemap">
    <canvas ref="canvas"></canvas>
  </div>
</template>

<script>
import {GameMap} from "@/assets/scripts/GameMap";
import {ref, onMounted} from 'vue';

export default {
  name: "GameMap",
  setup() {
    let parent = ref(null);
    let canvas = ref(null);

    //组件挂载完需要执行的哪些操作
    onMounted(()=>{
      new GameMap(canvas.value.getContext('2d'),parent.value);
    })

    return {
      parent,
      canvas
    }
  }
}
</script>

<style scoped>
div.gamemap {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  /*background-color: lightblue;*/
}
</style>