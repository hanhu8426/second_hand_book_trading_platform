<script>
import NavGation from "@/components/Common/NavGation.vue";
import NavHeader from "@/components/Common/NavHeader.vue";
import FooTer from "@/components/Common/FooTer.vue";
import Carousel from "@/components/Index/Carousel.vue";
import GalleryBook from "@/components/Index/GalleryBook.vue";
import SpikeBox from "@/components/Index/SpikeBox.vue";

export default{
    name:"InDex",
    components:{
        NavGation,NavHeader,FooTer,Carousel,GalleryBook,SpikeBox
    },
    data(){
        return{
            recommended:"recommend",
            newProduct:"newProduct",
            bookTopicList:[
               "assets/image/20.jpg",
                "assets/image/21.jpg",

                "assets/image/22.jpg",

                "assets/image/23.jpg",

            ],

            single:0,
            current:0,
            seen:false,

            activeName:'first',
            imgS:[],
            bookSortList:['小说','文学','地理','教科书','生物','科学','玄幻','言情','计算机','男频','女频'],
            options:['历史 |  政治', '文学 |  艺术', '科学 | 艺术', '商业  |  经济', '心理 | 自助', '旅游 | 地理', '宗教 | 哲学',],
        };

    },

    methods:{
        handleClick(tab, event) {
            console.log(tab, event);
        },

        enter(index){
            // console.log(this.seen);
            this.seen = true;
            this.current = index;
            // console.log("鼠标进入了！");
            // console.log("index:"+index)
        },
        out(){
            this.seen = false;
            this.current = null;
        },
    }
}
</script>

<template>
    <div >
        <NavGation></NavGation>
        <NavHeader></NavHeader>
        <div class="first_content">
            <div class="content_main">
                <div style="width: 100%">
                    <el-tabs v-model="activeName" @tab-click="handleClick">
                        <el-tab-pane label="首页" name="first">
                            <div class="content_main_left">
                                <div class="tab">
                                  <div v-for="(item,index) in options" :key="index" class="tab_list" >
                                    <div>
                                      <router-link :to="{ path: '/search', query: { kind:index+1 } }" >
                                        <span style="color: black">{{item}}</span>
                                      </router-link>
                                    </div>
                                  </div>
                                    <div class="communicate">
                                        <i class="el-icon-phone"></i><span>书店客服中心</span><br>
                                        <span>预约电话 123-321-123</span>
                                    </div>
                                </div>
                            </div>
                            <div class="content_main_right">
                                <Carousel :imgList="bookTopicList"></Carousel>
                                <div class="gallery-book_title">
                                    <span>最新出版</span>
                                </div>
                                <GalleryBook></GalleryBook>
                                <SpikeBox></SpikeBox>
                            </div>
                        </el-tab-pane>
                        <el-tab-pane label="新书区" name="second">新书区</el-tab-pane>
                        <el-tab-pane label="书单区" name="third">书单区</el-tab-pane>
                    </el-tabs>
                </div>
            </div>
        </div>

        <FooTer></FooTer>
    </div>
    <router-view></router-view>
</template>

<style scoped>
.first_content{
    width: 100%;
    background-color: #f7f7f6;
}
.content_main{
    margin:  auto;
    width: 1240px;
    height: 1450px;
    background-color: #f7f7f6;
}
.content_main_left{
    width: 17%;
    height: 100%;
    float: left;
}
.content_main_right{
    width: 82%;
    height: 100%;
    float: right;
}
.gallery-book_title{
    padding-top: 10px;
    width: 100%;
    height: 32px;
    margin-bottom: 25px;
    border-bottom: 1px solid #ccc;
    margin-top: 20px;
    padding-bottom: 34px;
    font-size: 22px;
}
.second_content{
    width: 100%;
    background-color: #ffffff;
}
.book_box{
    width: 1240px;
    height: 700px;
    margin: auto;
}
.tab_page a{
    font-size: 19px;
    color: #000000;
    text-decoration: none;
}

/*/deep/ .el-breadcrumb__inner a, .el-breadcrumb__inner.is-link{*/
/*  !*font-weight: 700;*!*/
/*  !*text-decoration: none;*!*/
/*  !*-webkit-transition: color .2s cubic-bezier(.645,.045,.355,1);*!*/
/*  !*transition: color .2s cubic-bezier(.645,.045,.355,1);*!*/
/*  color: #be1f89 !important;*/
/*}*/

/deep/ .el-breadcrumb__item .el-breadcrumb__inner{
    /*color: #393a42 !important;*/
    font-weight: 300;
}

.router-link-active {
    text-decoration: none;
}
.el-breadcrumb__item{
    line-height: 30px;
}
.tab{
    width: 100%;
    height: 420px;
}
.tab_list{
    padding-left: 5px;
    width: 100%;
    height: 40px;
    line-height: 40px;
    background-color: #ffffff;
    margin: auto;
    border: none !important;
}
.tab_list:hover{
    background-color: #f2f1ea;
}
.tab_page{
    /*display: none;*/
    padding: 25px 30px;
    width: 832px;
    position: absolute;
    left: 209px;
    top: 0;
    z-index: 24;
    height: 410px;
    border: 1px solid #e0e0e0;
    border-left: 0;
    background: #fff;
    -webkit-box-shadow: 0 2px 4px rgba(0,0,0,.18);
    box-shadow: 0 2px 4px rgba(0,0,0,.18);
}
.tab_page_list{
    margin-left: 15px;
    margin-right: 15px;
    margin-bottom: 20px;
    position: relative;
}


.communicate{
    clear: both;
    position: relative;
    width: 100%;
    height: 100px;
    border: 1px #acb4bc solid;
    padding: 2px 5px;
    line-height: 30px;
}
/deep/ .el-tabs__item {
    height: 60px;
    line-height: 60px;
    font-size: 20px;
}

</style>