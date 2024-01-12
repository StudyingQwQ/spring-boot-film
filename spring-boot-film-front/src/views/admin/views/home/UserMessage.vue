<template>
  <div id="overview">
    <div class="userinfo">
      <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
      <span class="username">{{ username }}</span>
    </div>
    <div class="overview-list">
      <div
          class="overview-item"
          v-for="item in overviewData?.allData"
          :key="item.id"
      >
        <div class="title">{{ item.name }}</div>
        <div class="number">{{ item.number }}</div>
      </div>
    </div>
    <div class="overview-list">
      <div
          class="overview-item"
          v-for="item in overviewData?.orderData"
          :key="item.id"
      >
        <div class="title">{{ item.name }}</div>
        <div class="number">{{ item.number }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const username = localStorage.getItem('username')
const email = localStorage.getItem('email')
const userRole = localStorage.getItem('role')
const points = localStorage.getItem('points')

interface SingleData {
  id: number;
  name: string;
  number: string;
}

interface OverviewData {
  allData: SingleData[];
  orderData: SingleData[];
}
const overviewData = ref<OverviewData>({
  allData: [
    {
      id: 1,
      name: "总观看量",
      number: "836",
    },
    {
      id: 2,
      name: "总访问量",
      number: "2568",
    },
    {
      id: 3,
      name: "总下载量",
      number: "1024",
    },
    {
      id: 4,
      name: "总收藏量",
      number: "450",
    },
  ],
  orderData: [
    {
      id: 1,
      name: "今日访问量",
      number: "26",
    },
    {
      id: 2,
      name: "累计观看量",
      number: "35",
    },
    {
      id: 3,
      name: "本月访问量",
      number: "90",
    },
    {
      id: 4,
      name: "累计观看量",
      number: "123",
    },
  ],
});
</script>

<style scoped>
#overview .userinfo {
  display: flex;
  align-items: center;
}
#overview .userinfo .username {
  margin-left: 10px;
}
#overview .overview-list {
  margin-top: 40px;
  display: flex;
  justify-content: space-between;
}
#overview .overview-list .overview-item {
  width: 22%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  box-shadow: var(--el-box-shadow-light);
  border-radius: 10px;
  padding: 20px;
}
#overview .overview-list .overview-item .title {
  font-weight: 600;
  margin-bottom: 10px;
}
#overview .overview-list .overview-item .number {
  font-size: 18px;
}
</style>