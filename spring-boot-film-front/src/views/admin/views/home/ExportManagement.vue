<template>
  <el-button type="success" round @click="dialogVisible = true">
    报表生成连接
  </el-button>
  <el-dialog
      v-model="dialogVisible"
      title="报表连接"
      width="30%"
      center
  >
    <span>电影报表:http://localhost:8080/api/export/films</span><br>
    <span>用户报表:http://localhost:8080/api/export/account</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false" type="primary">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'

const dialogVisible = ref(false)

</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
