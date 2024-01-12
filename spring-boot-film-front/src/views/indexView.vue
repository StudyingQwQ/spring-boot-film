<template>
  <div class="index-container">
<div class="start-bt">
    <el-button style="margin-bottom: 20px; height:60px; width:130px" type="success" @click="router.push('/main')">开始电影之旅</el-button>
   <el-button   type="danger" plain @click="userLogout">退出登录</el-button>
   </div>
   <div>

  </div>
  </div>
</template>

<script setup>
import { logout } from '@/net'
import router from "@/router";

function userLogout() {
  logout(() => router.push("/"))
}
</script>

<style scoped>
.index-container{
position: fixed;

  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: url(/public/background.jpg) no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
.start-bt{
display: flex;

flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 120vh;
}

</style>
