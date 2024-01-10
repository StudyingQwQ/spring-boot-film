<template>
<div>
  <el-container>
    <el-header>
      <el-row>
        <el-col >
      <el-input v-model="search" placeholder="请输入电影名字" width="500px"></el-input>
        </el-col>
        <el-col>




              <el-avatar :size="size" :src="circleUrl"></el-avatar>


        </el-col>
      </el-row>
    </el-header>

    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-main>
        <div><img src="/public/favicon.ico"></div>
      </el-main>
    </el-container>
  </el-container>
</div>
</template>

<style>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 1600px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>