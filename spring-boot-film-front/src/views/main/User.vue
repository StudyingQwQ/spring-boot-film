<template>
    <div class="avatar-container"><el-avatar class="user-avatar" src="/public/avatar.jpg"></el-avatar></div>
  <div class="user-info" style="font-size: 14px">用户名:{{ username }}</div>
  <div class="user-info" style="font-size: 14px; color: grey">邮箱:{{ email }}</div>
  <div class="user-info" style="font-size: 14px; color: grey">用户权限:{{ userRole }}</div>
  <div class="user-info" style="font-size: 14px; color: grey">积分:{{ points }}</div>
  <div class="user-info"><el-button @click="applyForVip()">申请VIP</el-button></div>
  <div class="user-info"><el-button @click="router.replace('/main')">返回主页面</el-button></div>
</template>

<style>
.avatar-container{
height:200px;
display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
.user-avatar{
margin-top:10px;
height:200px;
width:200px;
}
.user-info{
font-size: 20px; /* 根据需要调整字体大小 */
    font-weight: bold; /* 字体加粗 */
height:50px;
width:1200px;
line-height:50px;
margin-top:10px;
 text-align: left;
}
</style>

<script setup>
import router from "@/router";
import axios from 'axios'
const username = localStorage.getItem('username')
const email = localStorage.getItem('email')
const userRole = localStorage.getItem('role')
const points = localStorage.getItem('points')

async function applyForVip(){
if(points<1000){
    window.alert("积分不足，努力赚取积分吧！");
    return;
}

  try {
    const response = await axios.get(`http://localhost:8080/api/auth/vip?email=${email}`);
    console.log(response);
  } catch (error) {
    console.error(error);
  }
}

</script>