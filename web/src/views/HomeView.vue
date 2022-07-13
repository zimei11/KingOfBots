<template>
  <ContentBase>
    <el-row :gutter="12">
      <el-col :xs="24" :sm="18">
        <el-alert title="欢迎来到 Bots ZiMei" type="success" description="你可以创建游戏桌来进行对局。" center />
        <el-card shadow="always">
          <p>●咏唱春天，真的，在共同的心跳当中，我们都是心灵的流浪者。</p>
          <p>这最寒冷的季节，早被我们孕育出新的火焰，</p>
          <p>当雪花融为眼中最具激情的诗意，孕育着一个个美好的风情，</p>
          <p>春天，已永驻字里行间。然后，再度背负起青春故事，</p>
          <p>以及那些祝福和干粮，我们又将开始新的征程。</p>
          <p>且看脚步如犁，辛勤播种并耕耘又一场绿意，</p>
          <p>向着新的年轮，让我们情系壮美家园，共咏诗意春天！</p>
          <p>共同着我和你、他和她每一个春景的诗情画意。</p>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="6">
        <el-card shadow="always">
          <p>
            欲查规则？点击
            <el-link type="primary" href="#" class="card-link">游戏规则</el-link>
            进入游戏一览。
          </p>
          <p>
            参加比赛？点击
            <el-link type="primary" href="#" class="card-link">比赛</el-link>
            进入小组一览。
          </p>
          <p>
            提交程序？点击
            <el-link type="primary" href="#" class="card-link">程序</el-link>
            或顶栏按钮管理你的 Bot。
          </p>
        </el-card>
      </el-col>
    </el-row>
  </ContentBase>
</template>

<script setup>
import ContentBase from '@/components/ContentBase.vue';
</script>

<style scoped>
</style>
