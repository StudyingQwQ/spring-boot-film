<template>
  <div id="menu">
    <el-menu
        active-text-color="#fff"
        background-color="#2b2c43"
        default-active="/admin"
        text-color="#babccd"
        router
    >
      <component
          :is="item.children ? ElSubMenu : ElMenuItem"
          v-for="item in menulist"
          :key="item.id"
          :index="item.index"
      >
        <template v-if="item.children" #title>
          <el-icon v-if="item.icon">
            <component :is="item.icon"></component>
          </el-icon>
          <span>{{ item.name }}</span>
        </template>
        <span v-if="!item.children">
          <el-icon v-if="item.icon">
            <component :is="item.icon"></component>
          </el-icon>
          <span>{{ item.name }}</span>
        </span>
        <el-menu-item
            :index="subItem.index"
            v-for="subItem in item.children"
            :key="subItem.id"
        >{{ subItem.name }}
        </el-menu-item>
      </component>
    </el-menu>
  </div>
</template>

<script setup lang="ts">
import router from "@/router";
import { House, User, Goods } from "@element-plus/icons-vue";
import { ref } from "vue";
import { ElSubMenu, ElMenuItem } from "element-plus";
import type { Component } from "vue";
import {icons} from "@element-plus/icons-vue/global";
// const defaultActive = ref<string>(router.currentRoute.value.path);

interface MenuItem {
  name: string;
  id: number;
  index: string;
  icon?: Component;
  children?: MenuItem[];
}
//菜单假数据
const menulist: MenuItem[] = [
  {
    id: 1,
    name: "首页",
    index: "/admin/home",
    icon: House,
  },
  {
    id: 2,
    name: "用户管理",
    index: "/admin/users",
    icon: User,
  },
  {
    id: 101,
    name: "电影管理",
    index: "/prouduct-management",
    icon: Goods,
    children: [
      {
        id: 102,
        name: "电影列表",
        index: "/admin/filmlist",
      },
      {
        id: 103,
        name: "电影上传",
        index: "/admin/uploadfilm",
      },
    ],
  },

];
</script>
