<template>
  <div style="font-size: 14px">用户名:{{ username }}</div>
  <div style="font-size: 14px; color: grey">邮箱:{{ email }}</div>
  <div style="font-size: 14px; color: grey">用户权限:{{ userRole }}</div>
  <div style="font-size: 14px; color: grey">积分:{{ points }}</div>
</template>

<script setup>
const username = localStorage.getItem('username')
const email = localStorage.getItem('email')
const userRole = localStorage.getItem('role')
const points = localStorage.getItem('points')
</script>