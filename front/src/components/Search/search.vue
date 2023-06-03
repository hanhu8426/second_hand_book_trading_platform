<template>
  <div class="content">
    <Nav></Nav>
    <HeadNav></HeadNav>
    <div class="box_title">
      <p>{{this.total}} 条结果 </p>
    </div>
    <div class="box">
      <div class="book_sort">
        <div class="tab">
          <div class="tab_head">分类</div>
            <div v-for="(item,index) in sortList" :key="index" class="tab_list" :class="{ selected: item.isSelected && selectedCategory === index }">
              <div @click="selectCategory(item,index)">>
                <router-link :to="{ path: '/search', query: { kind:index+1 } }" class="custom-link">
                  <span style="color: black">{{item.name}}</span>
                </router-link>
              </div>
            </div>
        </div>
      </div>
      <div class="book_info">
        <div class="book_content" v-for="book in this.bookList" :key="book.bookId">
          <router-link :to="'/BookInfo/' + book.bookId">
            <div class="book_content_img">
              <el-image
                  style="width: 82%; height: 150px;margin:5px 9%"
                  :src="book.image"
                  fit="fill"></el-image>
            </div>
          </router-link>
          <div class="book_content_info">
            <div class="book_name">{{book.name}}</div>
            <div class="book_list_content">作者: 	{{book.author}}</div>
            <div class="book_list_content">校区:
              <span v-if="book.campus === 1">九龙湖校区</span>
              <span v-if="book.campus === 2">四牌楼校区</span>
              <span v-if="book.campus === 3">丁家桥校区</span>
            </div>
            <div class="book_list_content">售价: 	{{book.price}}</div>
            <div>
              <el-button class="plainBtn" plain>立即购买</el-button>
            </div>
          </div>
        </div>
        <div class="block" v-if="total>0" style="float: right;padding: 0 30px 10px">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[5, 10, 20, 50]"
              :page-size="page_size"
              background
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
        <div v-else style="width:100%;height: 50px;line-height: 50px;padding: 0 20px">
          <h3>不好意思，此分类暂时还没有图书......</h3>
        </div>
      </div>
      <div style="clear: both;"></div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Nav from "../../components/Common/NavGation.vue";
import HeadNav from "../../components/Common/NavHeader.vue";
import Footer from "../../components/Common/FooTer.vue";
import {reqGetBookListByName, reqGetBookListBySort} from "@/api/book";


export default {
  name: "SearchBook",
  components:{Nav,HeadNav,Footer},
  data(){
    return{
      currentPage: 1,
      page_size: 10,
      total:100,
      sortName:"分类名称",
      selectedCategory: null,
      sortList:[
        {name:'历史 | 政治', isSelected: false},
        {name:'文学 | 艺术', isSelected: false},
        {name:'科学 | 艺术', isSelected: false},
        {name:'商业 | 经济', isSelected: false},
        {name:'心理 | 自助', isSelected: false},
        {name:'旅游 | 地理', isSelected: false},
        {name:'宗教 | 哲学',isSelected: false},
      ],
      bookList: [],
      type:null,
      recInput:null,
      recSelect:null,
    }
  },

  methods: {
    selectCategory(category, index) {
      this.sortList.forEach((item) => {
        item.isSelected = false;
      });

      category.isSelected = true;

      // 调用 Vuex 的 mutation 设置选中的分类项索引
      this.$store.commit('setSelectedCategory', index);
    },



    async fetchBookList() {
      try {
        const response = await this.getBookList(this.type, 1, 10);
        this.total = response.data.data.total;
        this.bookList = response.data.data.rows; // 更新bookList的值
      } catch (error) {
        console.log(error);
      }
    },

    handleClick(tab, event) {
      console.log(tab, event);
    },
    //得到图书列表
    // getBookList(sortId,page,pageSize){
    //   reqGetBookListBySort(sortId,page,pageSize).then(response=>{
    //     if(response.code==200){
    //       this.total = response.total;
    //       console.log(this.total);
    //       this.bookList = response.bookList;
    //     }
    //     console.log(response);
    //   }).catch(err=>{
    //     console.log(err);
    //   })
    // },
    getBookList(type, page, pageSize) {
      return new Promise((resolve,reject) => {
        if (type !== undefined) {
          // 根据分类ID搜索图书
          reqGetBookListBySort(type, page, pageSize)
              .then((response) => {
                // 处理返回的图书列表数据
                const code = response.data.code;
                if (code===1) {
                  this.total = response.data.data.total;
                  this.bookList = response.data.data.rows;
                  resolve(response.data.data); // 返回响应数据
                }
                else {
                  reject(new Error("请求失败！"));
                }
                console.log("准备打印response");
                console.log(response);
                console.log("打印response中的bookList");
                console.log(this.bookList);
              })
              .catch((err) => {
                reject(err);
                console.log(err);
              });
        } else if (this.selectResult !== undefined && this.inputContent !== undefined) {
          // 根据其他条件搜索图书
          // 假设调用 reqGetBookListByName 函数来根据书名搜索图书
          reqGetBookListByName({input: this.inputContent, page: page, pageSize: pageSize})
              .then((response) => {
                // 处理返回的图书列表数据
                if (response.data.code === 1) {
                  this.total = response.data.data.total;
                  this.bookList = response.data.data.rows;
                  resolve(response.data.data);
                }
                else {
                  reject(new Error("请求失败！"));
                }
                console.log(response);
              })
              .catch((err) => {
                console.log(err);
              });
        }
      });
    },

    //分页函数
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.page_size = val;
      this.getBookList(this.type,1,this.page_size).then(response => {
        this.total = response.data.total;
        this.bookList = response.data.data.rows;
      }).catch(error => {
            console.log(error);
          });
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      console.log(this.currentPage+":"+this.page_size);
      this.getBookList(this.type,this.currentPage,this.page_size).then(response => {
        this.total = response.data.total;
        this.bookList = response.data.data.rows;
      }).catch(error => {
            console.log(error);
          });
    },
  },

  created() {
    const selectedCategory = this.$store.state.selectedCategory;
    if (selectedCategory !== null) {
      this.selectedCategory = selectedCategory;
      this.sortList[selectedCategory].isSelected = true;
    }

    this.type = this.$route.query.kind;
    this.sortName = this.$route.query.name;
    this.recSelect = this.$route.params.selectResult;
    this.recInput = this.$route.params.inputContent;
    console.log("进入测试函数，准备打印数据");
    console.log("type:"+this.type);
    console.log("this.$route.query.name:"+this.$route.query.name);
    console.log("this.$route.query.kind:"+this.$route.query.kind);
    console.log("接收到的结果：" +this.$route.params.inputContent );

    this.fetchBookList();
  },
  watch: {
    $route() {
      this.type = this.$route.query.kind;
      this.sortName = this.$route.query.name;
      this.recSelect = this.$route.params.selectResult;
      this.recInput = this.$route.params.inputContent;

      console.log("this.$route.query.name:"+this.$route.query.name);
      console.log("this.$route.query.kind:"+this.$route.query.kind);
      this.fetchBookList();
    }
  },
}
</script>

<style scoped>

.tab_list.selected {
  background-color: brown; /* 背景色为棕色 */
  color: black; /* 文字颜色为黑色 */
}

.content{
  background-color: #ffffff;
}

.box_title {
  margin-bottom: 10px;
  width: 100%;
  color: #545c64;
  padding: 5px 10px;
}

.box {
  display: flex;
  align-items: flex-start;
  margin: 10px auto;
  width: 1240px;
}

.book_sort {
  flex: 0 0 200px;
  margin-right: 10px;
  border-right: 1px #f3f0e9 solid;
}

.book_info {
  flex: 1;
  background-color: #f7f7f6;
}

.tab{
  width: 100%;
}
.tab_head{
  padding-left: 5px;
  width: 100%;
  height: 40px;
  line-height: 40px;
  background-color: #f3f0e9;
  margin: 1px 0px;
}
.tab_list{
  padding-left: 5px;
  width: 100%;
  height: 40px;
  line-height: 40px;
  background-color: #ffffff;
  margin: 1px 0px;
}
.tab_list:hover{
  background-color: #a0a9b1;
}

.book_content{
  border: 1px #f3f0e9 solid;
  padding: 5px;
  margin: 10px auto;
  width: 940px;
  height: 190px;
  max-height: 240px;
  line-height: 35px;
  border: 1px solid #cacaca;
  padding: 15px;
}
.book_content:hover{
  background-color: white;
}

.book_content_img{
  float: left;
  width: 150px;
  height: 160px;
  /*border: 1px solid black;*/
}
.book_content_info{
  float: right;
  width: 740px;
  height: 160px;
  color: black;
  /*border: 1px solid black;*/
}


.book_name{
  margin: 5px auto;
  width: 740px;
  line-height: 30px;
  font-size: 18px;
  color: #9d232c;
  overflow: hidden;
}

.book_list_content{
  width: 32%;
  display: inline-block;
  height: 20px;
  overflow: hidden;
  line-height: 20px;
  font-size: 18px;
}

.plainBtn{
  width: 100px;
  color: #9d232c;
  background-color: #f3f0e9;
  border: 1px #f3f0e9 solid;
  float: right;
  margin: 0px 5px;
  font-size: 14px;
}
.plainBtn:hover{
  background-color: white;
  color: #9d232c;
  border: 1px #b2bac2 solid;
}
.cartBtn{
  width: 140px;
  background-color: #9d232c;
  border: 1px #9d232c solid;
  float: right;
  margin: 0px 5px;
  font-size: 14px;
}
.cartBtn:hover{
  background-color: #f52b21;
}

.custom-link {
  text-decoration: none; /* 移除下划线 */
}
</style>

