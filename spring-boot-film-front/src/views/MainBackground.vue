<template>
<div>
  <el-container>
    <el-header height="60px" weight="1200px">
     <el-row height="60px" weight="1200px">
     <el-col :span="1">
     <div><img src="/favicon.ico"></div>
     </el-col>
       <el-col :span="3">


      <div class="demo-basic--circle">
      <router-link to="/">


        <div class="block"><el-avatar :size="50" :src="circleUrl"></el-avatar></div>
        <div></div>
        </router-link>


       </div></el-col>
       <el-col :span="10">
            <el-input v-model="searchId" placeholder="请输入电影id"></el-input>
       </el-col>
<el-col :span="4">
<el-button type="primary" @click="SearchById(searchId)">搜索</el-button>
</el-col>
     </el-row>
    </el-header>

    <el-container>
      <el-aside width="200px">
      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"

      >

          <template slot="title"><i class="el-icon-location"></i>导航一</template>
          <el-menu-item index="1-1">热播</el-menu-item>
          <el-menu-item index="1-2">类型</el-menu-item>


          <template slot="title"><i class="el-icon-menu"></i>导航二</template>
          <el-menu-item index="2-1">地区</el-menu-item>
          <el-menu-item index="2-2">评分</el-menu-item>

        <!-- 更多菜单项... -->
      </el-menu>
      </el-aside>
      <el-main height="800px">
        <div><router-view v-slot="{ Component }">
                     <transition name="el-fade-in-linear" mode="out-in">
                       <component :is="Component" style="height: 100%"/>
                     </transition>
                   </router-view></div>
      </el-main>
    </el-container>
  </el-container>
</div>
</template>

<style>

 .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
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
    line-height: 800px;
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

  .el-menu-item{
  background-color: #D3DCE6;
      color: #333;
      text-align: center;
  }
</style>

<script>
import axios from 'axios';
import router from "@/router";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
  export default {

    data () {
      return {
        circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
        squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
        sizeList: ["large"],
        searchId:'',
        token:'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoicXdlciIsImlkIjo0LCJleHAiOjE3MDU1ODAxNzcsImlhdCI6MTcwNDk3NTM3NywianRpIjoiN2E4ZjdlYWItMDVmZi00ZmNkLTg0ZDUtMDk0M2Y2ZDU3NjRjIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl19.jmwNCltUVSXSNR6vC1kWEvUvhwTfU-ZJMEyifmWtTqM',
      }
    },
    methods: {

        async SearchById() {
        if(this.token==''){
        this.$router.push('/');
        }
        console.log(this.token);
            try {
                    const response = await axios.post('http://localhost:8080/api/film/getFilmbyId'
                     , {
                        headers: {
                        Authorization: 'Bearer ' + this.token// 这里的token是你的Bearer token
                                }
                     }
                    ,{
                        params:{
                            id:this.searchId
                        }
                    });
                    console.log(response.data);
                    if (response.data.success) {
                      this.$router.push('/targetPage');
                    } else {
                      // 处理错误
                    }
                  } catch (error) {
                    console.error(error);
                  }
                }
        }
      }

</script>