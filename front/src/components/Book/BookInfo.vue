<template>
  <div class = "content">
    <Nav></Nav>
    <NavHead></NavHead>
    <div class = "box book_box">
      <div class = "book_img">
        <!-- 放照片 -->
        <CarouselBtn :imgList="book.imgSrc"></CarouselBtn>
      </div>
      <div class="book_buy">
        <!-- 书籍的一些基本信息 -->
        <div class="book_name">{{book.bookName}}</div>
        <div class="book_content book_buy_content">
          <div class="book_list_content">作者： {{book.author}}</div>
          <div class="book_list_content">出版社： {{book.publish}}</div>
          <div class="book_list_content">类别： {{ book.category }}</div>
          <div class="book_list_content">校区： {{ book.campus }}</div>
        </div>
        <div class="book_content book_buy_price">
          <div class="book_buy_info">
            <span class="labelSpan">售价</span>
            <span class="labelPrice">￥{{ book.price}}</span>
          </div>
        </div>
        <div class="book_content">
          <el-button class="plainBtn" plain @click="goBuyPage(book.id)">立即购买</el-button>
        </div>
      </div>
    </div>
    <!-- 下半部分 -->
    <div class="box">
      <!-- 书籍详情 -->
      <div class="book_info">
        <h3>书籍描述</h3>
        <textarea name="bookDes" cols="300" rows="10" v-model="book.description"></textarea>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script >
import Nav from "../../components/Common/NavGation"
import NavHead from "../../components/Common/NavHeader"
import Footer from "../../components/Common/FooTer"
import {reqGetBook} from "@/api/book";
import {reqGetSortList} from "@/api/sort";
import CarouselBtn from "@/components/Book/CarouselIBtn.vue";
import 'github-markdown-css'

export default{
  name:"BookInfo",
  components:{CarouselBtn, Nav,NavHead,Footer},
  data(){
    return{
      result: "",
      book: {
        id: null,
        bookName: '',
        author: '',
        publish: '',
        price: '',
        description: '',
        imgSrc: [],
        put: true,
        coverImg: '',
        rank: '',
        newProduct: true,
        recommend: true,
        bookSort: [],//图书分类的绑定值
      },
      fileList:null,
      bookId:null,
      sortList:[
        {
          upperSort: {
            sortName:null,
          },
          children:null
        }
      ],
      activeName:'first'
    }
  },
  methods: {
    getSortList() {
      reqGetSortList().then(response => {
        if(response.code==200){
          this.sortList = response.sortResponseList;
        }
      });
    },

    getBook(bookId){
      reqGetBook(bookId).then(response=>{
        // console.log(response.book);
        this.book = response.book;
        // console.log("this.book.imgSrc:"+response.book.imgSrc);
        let MarkdownIt = require("markdown-it");
        let md = new MarkdownIt();
        let result = md.render(this.book.description);
        this.book.description = result;
      }).catch(err=>{
        console.log(err);
      })
    },

    goBuyPage(id){
      let arr = [];
      arr.push(id);
      arr.push(0);
      let ids = JSON.stringify(arr);
      this.$router.push({
        path: "/buyPage",
        query: {
          ids: ids
        }
      })
    }
  },
  created() {
    this.bookId = this.$route.query.id;
    this.getBook(this.bookId);
    this.getSortList();
  }
}

</script>

<style scoped>
.content{
  background-color: #ffffff;
}
.box{
  margin: 10px auto;
  width: 1240px;
}
.book_box{
  height: 500px;
}
.book_img{
  margin: 10px;
  width: 450px;
  height: 480px;
  float: left;
}
.book_buy{
  margin: 10px;
  width: 700px;
  height: 480px;
  float: right;
  padding: 1px;
}
.book_buy_content{
  border: 1px #f3f0e9 solid;
  padding: 5px;
}
.book_content{
  margin: 10px auto;
  width: 740px;
  max-height: 120px;
  line-height: 35px;
}

.book_name{
  margin: 10px auto;
  width: 740px;
  line-height: 35px;
  font-size: 18px;
  overflow: hidden;
}

.book_list_content{
  width: 32%;
  display: inline-block;
  height: 20px;
  overflow: hidden;
  line-height: 20px;
  font-size: 15px;
}
.book_buy_info{
  width: 100%;
  line-height: 40px;
  font-size: 15px;
}
.book_buy_price{
  background-color: #f3f0e9;
  padding: 5px;
}
.labelSpan{
  color: #999;
}
.labelPrice{
  color: #9d232c;
  font-size: 17px;
}
.plainBtn{
  width: 150px;
  color: #9d232c;
  background-color: #f3f0e9;
  border: 1px #f3f0e9 solid;
}
.plainBtn:hover{
  background-color: white;
  color: #9d232c;
  border: 1px #b2bac2 solid;
}

.book_info{
  margin: 10px 10px;
  width: 900px;
  /*height: 780px;*/
  float: left;
}
.tab{
  width: 100%;
  /*height: 420px;*/
}
.tab_head{
  padding-left: 5px;
  width: 100%;
  height: 40px;
  line-height: 40px;
  background-color: #f3f0e9;
  margin: 1px ;
}
.tab_list{
  padding-left: 5px;
  width: 100%;
  height: 40px;
  line-height: 40px;
  background-color: #ffffff;
  margin: 1px ;
}
.tab_list:hover{
  background-color: #a0a9b1;
}

</style>
