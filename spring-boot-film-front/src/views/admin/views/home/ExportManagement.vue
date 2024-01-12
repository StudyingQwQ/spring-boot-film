<template>
  <el-button text @click="dialogVisible = true">
    报表生成连接
  </el-button>

  <el-dialog
      v-model="dialogVisible"
      title="Tips"
      width="30%"
  >
    <span>电影报表:http://localhost:8080/api/export/films</span><br>
    <span>用户报表:http://localhost:8080/api/export/account</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary">
          Confirm
        </el-button>
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
