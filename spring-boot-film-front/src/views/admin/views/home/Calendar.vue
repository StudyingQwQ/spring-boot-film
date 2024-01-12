<!--<template>-->
<!--  <el-calendar v-model="value" />-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref } from 'vue'-->
<!--const value = ref(new Date())-->
<!--</script>-->
<template>
  <el-calendar ref="calendar">
    <template #header="{ date }">
      <span>日历</span>
      <span>{{ date }}</span>
      <el-button-group>
        <el-button size="small" @click="selectDate('prev-year')">
          上一年
        </el-button>
        <el-button size="small" @click="selectDate('prev-month')">
          上一月
        </el-button>
        <el-button size="small" @click="selectDate('today')">今天</el-button>
        <el-button size="small" @click="selectDate('next-month')">
          下一月
        </el-button>
        <el-button size="small" @click="selectDate('next-year')">
          下一年
        </el-button>
      </el-button-group>
    </template>
  </el-calendar>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import type { CalendarDateType, CalendarInstance } from 'element-plus'

const calendar = ref<CalendarInstance>()
const selectDate = (val: CalendarDateType) => {
  if (!calendar.value) return
  calendar.value.selectDate(val)
}
</script>
