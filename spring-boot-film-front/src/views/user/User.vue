<template>
  <div style="font-size: 14px">用户名:{{ username }}</div>
  <div style="font-size: 14px; color: grey">邮箱:{{ email }}</div>
  <div style="font-size: 14px; color: grey">用户权限:{{ userRole }}</div>
  <div style="font-size: 14px; color: grey">积分:{{ points }}</div>
  <el-button @click="router.push('/index')">跳转测试</el-button>
</template>

<script setup>
import router from "@/router";
const username = localStorage.getItem('username')
const email = localStorage.getItem('email')
const userRole = localStorage.getItem('role')
const points = localStorage.getItem('points')
</script>