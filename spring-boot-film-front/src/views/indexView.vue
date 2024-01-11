<template>
  <div>
    <el-button @click="userLogout">退出登录</el-button>
    <el-button @click="router.push('/main')">跳转测试</el-button>
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

</style>
