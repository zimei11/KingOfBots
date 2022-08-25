<template>
  <el-card shadow="always">
    <h3>Bots ZiMei 是什么？<small>网站功能</small></h3>
    <p>Bots ZiMei 是在线的程序对抗平台，AI 可以根据已有的游戏规则进行比赛一决胜负。同时支持人机，人人对战</p>
    <el-divider />
    <h3>我不知道怎么用 Bots ZiMei 啊？<small>使用方法</small></h3>
    <ol>
      <li>注册 Bots ZiMei 账户</li>
      <li>
        点击
        <el-link type="primary" href="/rules/Snake.html" class="card-link">游戏规则</el-link>
        查看Snake游戏规则,按照游戏说明写自己的 AI 程序
      </li>
<!--      <li>找一个你想参加的游戏，按照游戏说明写自己的 AI 程序（目前只有一个游戏）</li>-->
      <li>点击右上角账户名，进入我的 Bot 页面，创建 Bot 并上传程序</li>
      <li>回到对战页面，点击开始匹配，与他人或其他 Bot 开始对战</li>
    </ol>
  </el-card>
</template>

<script>
export default {
  name: "HomeMessage"
}
</script>

<style scoped>

</style>