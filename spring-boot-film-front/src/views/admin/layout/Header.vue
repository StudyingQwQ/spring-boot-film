<template>
  <div id="header">
    <div class="logo-box">FILM</div>
    <div class="right-box">
      <div class="title">后台管理系统</div>
      <div class="avatar-box">
        <el-popover
            :width="275"
            popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
        >
          <template #reference>
            <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
          </template>
          <template #default>
            <div
                class="demo-rich-content"
            >
              <el-avatar
                  :size="60"
                  src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
                  style="margin-bottom: 8px"
              />
              <div class="user-info">
                <div style="font-size: 14px">用户名:{{ username }}</div>
                <div style="font-size: 14px; color: grey">邮箱:{{ email }}</div>
                <div style="font-size: 14px; color: grey">用户权限:{{ userRole }}</div>
                <div style="font-size: 14px; color: grey">积分:{{ points }}</div>
                <el-button type="primary" @click="userLogout">退出登录</el-button>
              </div>
            </div>
          </template>
        </el-popover>
      </div>
    </div>
  </div>
</template>

<script setup>
import { logout } from '@/net'
import router from "@/router";

const username = localStorage.getItem('username')
const email = localStorage.getItem('email')
const userRole = localStorage.getItem('role')
const points = localStorage.getItem('points')

function userLogout() {
  logout(() => router.push("/"))
}
</script>

<style>
.demo-rich-content {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-info {
  display: flex;
  flex-direction: column;
}
 #header {
   background-color: #2b2c44;
   color: #fff;
   display: flex;
   justify-content: space-between;
 }

#header .logo-box {
  width: 200px;
  text-align: center;
  line-height: 60px;
  font-size: 22px;
  font-weight: bold;
  color: #e73267;
}

#header .right-box {
  flex: 1;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
}

#header .right-box .el-avatar {
  margin-right: 10px;
}
</style>
