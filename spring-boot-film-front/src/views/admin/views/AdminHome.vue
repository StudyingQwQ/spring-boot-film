<template>
  <div class="wrap">
    <AdminOverview class="item"></AdminOverview>
    <Export class="item" />
    <BarChart class="item" />
    <PieChart class="item" />
  </div>
</template>

<script setup lang="ts">
import AdminOverview from "./home/UserMessage.vue";
import Export from "./home/ExportManagement.vue"
import BarChart from "./home/BarChart.vue";
import PieChart from "./home/PieChart.vue";
</script>

<style scoped>
.wrap {
  display: flex;
  flex-wrap: wrap;
  box-shadow: var(--el-box-shadow-light);
}
.wrap .item {
  width: 48%;
  height: calc((100vh - 100px) / 2);
  min-height: 360px;
  padding: 20px;
  border: 1px solid #f5f5f7;
}
</style>
