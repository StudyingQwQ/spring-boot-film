<template>
<div class="film-title">{{items.name}}</div>
<div class="film-container">
    <video v-if="showFilm" class="film-play" :src="items.filmurl" controls></video>
  </div>
  <div class="film-info">{{items.type+' '+items.time+' '+items.region}}</div>
  <div class="film-info">{{items.actor+' '+items.good}}</div>
  <div class="film-info"><img :src="items.picurl">
  <div class="film-info">{{items.intro}}</div>
  </div>
</template>

<style>
.film-container{
 height:800px;
    width:1200px
}
.film-play{
    height:800px;
    width:1200px;
}
.film-title{
font-size: 30px; /* 根据需要调整字体大小 */
    font-weight: bold; /* 字体加粗 */
height:50px;
width:1200px;
line-height:50px;
margin-bottom:10px;
margin-left:auto;
}
.film-info{
font-size: 20px; /* 根据需要调整字体大小 */
    font-weight: bold; /* 字体加粗 */
height:50px;
width:1200px;
line-height:50px;
margin-top:10px;
margin-left:auto;
}
</style>

<script>
import axios from 'axios';

export default {
  data() {
    return {
    id:'',
    token:'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoicXdlciIsImlkIjo0LCJleHAiOjE3MDU1ODAxNzcsImlhdCI6MTcwNDk3NTM3NywianRpIjoiN2E4ZjdlYWItMDVmZi00ZmNkLTg0ZDUtMDk0M2Y2ZDU3NjRjIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl19.jmwNCltUVSXSNR6vC1kWEvUvhwTfU-ZJMEyifmWtTqM',
      items: ''
    };
  },
watch: {
  $route (to, from) {
    this.$router.go(0);
  },
},
  async created() {
            this.id=this.$route.query.id;

        console.log(this.id);
                    try {
                            const response = await axios.post('http://localhost:8080/api/film/getFilmbyId'
                             , {
                                headers: {
                                Authorization: 'Bearer ' + this.token// 这里的token是你的Bearer token
                                        }
                             }
                            ,{
                                params:{
                                    id:this.id
                                }
                            });
                           this.items = response.data.data;
                          } catch (error) {
                            console.error(error);
                          }
                          console.log(this.items)
  },
  computed: {
    showFilm() {
      const role = localStorage.getItem('role');
      return role !== 'USER';
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