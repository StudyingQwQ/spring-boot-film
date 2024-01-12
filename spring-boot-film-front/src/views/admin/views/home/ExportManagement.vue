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
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="openExportAccount" type="primary" round>用户报表生成</el-button>
        <el-button @click="openExportFilm" type="primary" round>电影报表生成</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

const dialogVisible = ref(false)

const openExportFilm = () => {
  const url = 'http://localhost:8080/api/export/film'
  window.open(url, '_blank')
  ElMessage.success("电影报表生成成功")
}

const openExportAccount = () => {
  const url = 'http://localhost:8080/api/export/account'
  window.open(url, '_blank')
  ElMessage.success("用户报表生成成功")
}

</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>