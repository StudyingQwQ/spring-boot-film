<template>
<div>
    <ul>

      <li v-for="item in items" :key="item.id" >

        <img src="/public/favicon.ico" @click="goToDetail(item.id)" style="width: 250px; height: 300px;">

       <div class="text">

         <div  class="title" style="display: block;">{{item.name}}</div>

         <div  class="intro" style="display: block;">{{item.intro}}</div>
       </div>






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

display: flex;
font-size: 20px; /* 根据需要调整字体大小 */
    font-weight: bold; /* 字体加粗 */
margin-top:-700px;

}

.intro{
font-size: 18px;
display: flex;
margin-top:-700px;
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