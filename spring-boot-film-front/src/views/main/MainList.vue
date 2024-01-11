<template>
<div>
    <ul>

      <li v-for="item in items" :key="item.id" >

        <img src="/public/favicon.ico" @click="goToDetail(item.id)" style="width: 250px; height: 300px;">

       <div class="text">

         <div  class="title" style="display: block;">{{item.name}}</div>
        <div  class="actor" style="display: block;">{{item.actor+'        '+item.region}}</div>
         <div  class="type" style="display: block;">{{item.actor+'        '+item.region}}</div>
         <div  class="intro" style="display: block;">{{item.intro}}</div>
       </div>

       <el-button type="success">查看详情</el-button>







      </li>
    </ul>

  </div>
</template>
<style>
li{
 height: 310px;



}
li img {
display: flex;
    margin-right: auto; /* 靠左边 */
  }

  .text{
  display:flex;
  flex-direction: column;
  }
.title{
height:20px;
display: flex;
font-size: 20px; /* 根据需要调整字体大小 */
    font-weight: bold; /* 字体加粗 */
margin-top:-700px;

}
.actor{
height:10px;
display: flex;
font-size: 19px; /* 根据需要调整字体大小 */
    font-weight: bold; /* 字体加粗 */
margin-top:10px;

}
.type{
height:10px;
display: flex;
font-size: 19px; /* 根据需要调整字体大小 */
    font-weight: bold; /* 字体加粗 */
margin-top:10px;

}
.intro{

font-size: 18px;
display: flex;
margin-top:450px;
margin-left:350px;
width: 600px; /* 你可以根据需要调整这个值 */
  word-wrap: break-word;
  line-height: 1.2;
}
.el-button{
margin-top:-900px;
margin-left:auto;
}
</style>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: []
    };
  },
  async created() {
    const response = await axios.get('http://localhost:8080/api/film/getFilm');
    this.items = response.data.data;
console.log(response.data.data);
  },
methods: {
    goToDetail(id) {
      // 使用你的路由库跳转到详情页面，例如：
      router.push({ path: 'detail', params: { id } });
      console.log(`Go to details of ${id}`);
    }
  }
};
</script>