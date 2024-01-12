<!--<template>-->
<!--  <el-table :data="tableData" style="width: 100%">-->
<!--    <el-table-column prop="id" label="Date" width="180" />-->
<!--    <el-table-column prop="name" label="Name" width="180" />-->
<!--    <el-table-column prop="intro" label="Address" />-->
<!--  </el-table>-->
<!--</template>-->

<!--<script setup>-->
<!--import { reactive, onMounted } from 'vue';-->
<!--import { get } from "@/net";-->

<!--const tableData = reactive([]);-->

<!--const getFIlmData = () => {-->
<!--  get(`/api/film/getFilm`), (data) => {-->
<!--    if (data.code === 200) {-->
<!--      tableData.value = data.data-->
<!--    }-->
<!--  }, (err) => {-->
<!--    console.log(err)-->
<!--  }-->
<!--}-->
<!--onMounted(() => {-->
<!--  getFIlmData()-->
<!--})-->

<!--</script>-->
<template>
  <div>电影列表</div>
</template>
