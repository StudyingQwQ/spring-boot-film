<template>
<div>
    <ul>

      <li v-for="item in items" :key="item.id" >

        <img :src="item.picurl"  style="width: 250px; height: 300px;">

       <div class="text">

         <div  class="title" style="display: block;">{{item.name}}</div>
        <div  class="actor" style="display: block;">{{item.type+' '+item.actor+' '+item.region}}</div>
         <div  class="type" style="display: block;">评分：{{item.good}}</div>
         <div  class="intro" style="display: block;">{{item.intro}}</div>

       </div>

       <el-button class="bt" type="success" @click="goToDetail(item.id)">查看详情</el-button>







      </li>
    </ul>

  </div>
</template>

<style>
li{
 height: 310px;
width: 1160px;


}
li img {
display: flex;
    margin-right: auto; /* 靠左边 */
  }

  .text{
  width: 1160px;
  display:flex;
  flex-direction: column;
  }
.title{
height:20px;
width: 1160px;
display: flex;
font-size: 25px; /* 根据需要调整字体大小 */
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
.bt{
margin-top:-880px;
margin-left:500xp;
}
</style>

<script>
import axios from 'axios';

export default {
  data() {
    return {
    key:'',
    type:'',
      items: []
    };
  },
watch: {
  $route (to, from) {
    this.$router.go(0);
  },

},
  async mounted() {
        this.key=this.$route.query.key;
        this.type=this.$route.query.type
        console.log(this.type+' '+this.key);
                    try {
                            const response = await axios.post('http://localhost:8080/api/film/getFilmListbyType'
                             , {
                                headers: {
                                Authorization: 'Bearer ' + this.token// 这里的token是你的Bearer token
                                        }
                             }
                            ,{
                                params:{
                                type:this.type,
                                key:this.key
                                }
                            });
                           this.items = response.data.data;
                          } catch (error) {
                            console.error(error);
                          }
  },
methods: {
    goToDetail(id) {
      // 使用你的路由库跳转到详情页面，例如：
      this.$router.push({ path: 'detail', query: { id:id } });
      console.log(`Go to details of ${id}`);
    }
  }
};
</script>