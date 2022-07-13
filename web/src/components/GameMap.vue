<template>
<div ref="parent" class="gamemap">
  <canvas ref="canvas"></canvas>
</div>
</template>

<script>
export default {
  name: "GameMap"
}
</script>

<style scoped>
div.gamemap{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>