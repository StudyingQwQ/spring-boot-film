<!--<template>-->
<!--  <el-button type="success" @click="userLogout">退出登录</el-button>-->
<!--</template>-->

<!--<script setup>-->
<!--import { logout } from '@/net'-->
<!--import router from "@/router";-->

<!--function userLogout() {-->
<!--  logout(() => router.push("/"))-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->
<template>
  <div id="home">
    <el-container>
      <el-header>
        <AdminHeader></AdminHeader>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-affix><AdminMenu></AdminMenu></el-affix>
        </el-aside>
        <el-main>
          <RouterView></RouterView>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import AdminHeader from "@/views/admin/layout/Header.vue";
import AdminMenu from "@/views/admin/layout/Menu.vue";
</script>

<style>
#home {
  min-height: 100vh;
}
.el-header {
  background-color: #2b2c44;
  padding: 0;
}

.el-aside {
  background-color: #2b2c44;
  min-height: calc(100vh - 60px);
}
</style>
