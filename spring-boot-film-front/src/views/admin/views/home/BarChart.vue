<template>
  <v-chart class="chart" :option="option" autoresize />
</template>

<script setup>
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { BarChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent,
} from 'echarts/components';
import VChart from 'vue-echarts';
import { ref, onMounted } from 'vue';
import axios from 'axios';

use([
  CanvasRenderer,
  BarChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent,
]);

const option = ref(null);

onMounted(async () => {
  const data = await getData();
  drawBarChart(data);
});

async function getData() {
  const response = await axios.get('http://localhost:8080/api/film/getRegion');
  return response.data.data;
}

function drawBarChart(data) {
  option.value = {
    title: {
      text: '电影地区分布',
      left: 'center',
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    xAxis: {
      type: 'category',
      data: Object.keys(data)
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '电影数量',
        type: 'bar',
        data: Object.values(data)
      }
    ]
  };
}
</script>

<style scoped>
.chart {
  height: 100vh;
}
</style>