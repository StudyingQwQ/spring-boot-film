<template>
  <v-chart class="chart" :option="option" autoresize />
</template>

<script setup>
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from 'echarts/components';
import VChart from 'vue-echarts';
import { ref, onMounted } from 'vue';
import axios from 'axios';

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
]);

const option = ref(null);

onMounted(async () => {
  const data = await getData();
  drawPieChart(data);
});

async function getData() {
  const response = await axios.get('http://localhost:8080/api/film/getType');
  return response.data.data;
}

function drawPieChart(data) {
  option.value = {
    title: {
      text: '电影类型占比',
      left: 'center',
    },
    tooltip: {
      trigger: 'item',
      formatter: '{a} <br/>{b} : {c} ({d}%)',
    },
    legend: {
      orient: 'vertical',
      left: 'left',
      data: Object.keys(data),
    },
    series: [
      {
        name: '电影类型',
        type: 'pie',
        radius: '55%',
        center: ['50%', '60%'],
        data: Object.entries(data).map(([name, value]) => ({ name, value })),
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)',
          },
        },
      },
    ],
  };
}
</script>

<style scoped>
.chart {
  height: 100vh;
}
</style>